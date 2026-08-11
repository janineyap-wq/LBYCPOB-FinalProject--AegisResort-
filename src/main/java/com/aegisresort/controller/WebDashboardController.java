package com.aegisresort.controller;

import com.aegisresort.facility.Amenity;
import com.aegisresort.model.Guest;
import com.aegisresort.service.SecurityScannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebDashboardController {

    private final SecurityScannerService service;

    public WebDashboardController(SecurityScannerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lostItems", service.getAllLostItems());
        return "index";
    }

    @PostMapping("/web/scan")
    public String scanAccess(@RequestParam String guestId,
                             @RequestParam String facilityId,
                             Model model) {
        Guest guest = service.getGuest(guestId);
        Amenity facility = service.getFacility(facilityId);

        String auditLog = service.generateAuditLog(guest, facility);
        boolean allowed = service.verifyEntry(guest, facility);

        model.addAttribute("scanResult", auditLog);
        model.addAttribute("scanAllowed", allowed);
        model.addAttribute("lostItems", service.getAllLostItems());
        return "index";
    }

    @PostMapping("/web/lost-item")
    public String reportLostItem(@RequestParam String desc,
                                 @RequestParam String loc,
                                 @RequestParam(required = false) String guestId) {
        service.registerLostItem(desc, loc, guestId);
        return "redirect:/";
    }

    @PostMapping("/web/claim/{itemId}")
    public String claimLostItem(@PathVariable String itemId) {
        service.claimLostItem(itemId);
        return "redirect:/";
    }
}

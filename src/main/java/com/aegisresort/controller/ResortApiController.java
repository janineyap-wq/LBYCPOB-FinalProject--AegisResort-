package com.aegisresort.controller;

import com.aegisresort.facility.Amenity;
import com.aegisresort.model.Guest;
import com.aegisresort.model.LostItemEntity;
import com.aegisresort.service.SecurityScannerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResortApiController {

    private final SecurityScannerService service;

    public ResortApiController(SecurityScannerService service) {
        this.service = service;
    }

    @GetMapping("/scan")
    public String scanEntry(@RequestParam String guestId, @RequestParam String facilityId) {
        Guest guest = service.getGuest(guestId);
        Amenity facility = service.getFacility(facilityId);
        return service.generateAuditLog(guest, facility);
    }

    @PostMapping("/lost-item")
    public LostItemEntity logLostItem(@RequestParam String desc, @RequestParam String loc, @RequestParam(required = false) String guestId) {
        return service.registerLostItem(desc, loc, guestId);
    }

    @GetMapping("/lost-item/guest/{guestId}")
    public List<LostItemEntity> getLostItemsByGuest(@PathVariable String guestId) {
        return service.findItemsByGuest(guestId);
    }

    @PutMapping("/claim/{itemId}")
    public String claimItem(@PathVariable String itemId) {
        boolean success = service.claimLostItem(itemId);
        return success ? "Item marked as CLAIMED." : "Item not found or already claimed.";
    }
}
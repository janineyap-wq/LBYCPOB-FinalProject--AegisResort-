package com.aegisresort.controller;

import com.aegisresort.facility.Amenity;
import com.aegisresort.model.Guest;
import com.aegisresort.model.UserAccount;
import com.aegisresort.service.AuthService;
import com.aegisresort.service.SecurityScannerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebDashboardController {

    private final SecurityScannerService scannerService;
    private final AuthService authService;

    public WebDashboardController(SecurityScannerService scannerService, AuthService authService) {
        this.scannerService = scannerService;
        this.authService = authService;
    }

    // --- Authentication Routes ---

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        Optional<UserAccount> user = authService.authenticate(username, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // --- Protected Dashboard Routes ---

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        UserAccount currentUser = (UserAccount) session.getAttribute("user");
        if (currentUser == null) return "redirect:/login";

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("lostItems", scannerService.getAllLostItems());
        return "index";
    }

    @PostMapping("/web/scan")
    public String scanAccess(@RequestParam String guestId,
                             @RequestParam String facilityId,
                             HttpSession session,
                             Model model) {
        UserAccount currentUser = (UserAccount) session.getAttribute("user");
        if (currentUser == null) return "redirect:/login";

        Guest guest = scannerService.getGuest(guestId);
        Amenity facility = scannerService.getFacility(facilityId);

        String auditLog = scannerService.generateAuditLog(guest, facility);
        boolean allowed = scannerService.verifyEntry(guest, facility);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("scanResult", auditLog);
        model.addAttribute("scanAllowed", allowed);
        model.addAttribute("lostItems", scannerService.getAllLostItems());
        return "index";
    }

    @PostMapping("/web/lost-item")
    public String reportLostItem(@RequestParam String desc,
                                 @RequestParam String loc,
                                 @RequestParam(required = false) String guestId,
                                 HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";

        scannerService.registerLostItem(desc, loc, guestId);
        return "redirect:/";
    }

    @PostMapping("/web/claim/{itemId}")
    public String claimLostItem(@PathVariable String itemId, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        scannerService.claimLostItem(itemId);
        return "redirect:/";
    }
}
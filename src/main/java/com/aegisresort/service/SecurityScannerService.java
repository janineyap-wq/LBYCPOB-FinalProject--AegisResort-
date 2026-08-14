package com.aegisresort.service;

import com.aegisresort.facility.*;
import com.aegisresort.model.*;
import com.aegisresort.repository.LostItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SecurityScannerService implements AccessController, LogisticsService {

    private final LostItemRepository lostItemRepository;
    private final Map<String, Guest> guests = new HashMap<>();
    private final Map<String, Amenity> facilities = new HashMap<>();
    private int itemCounter = 100;

    public SecurityScannerService(LostItemRepository lostItemRepository) {
        this.lostItemRepository = lostItemRepository;
        seedInitialData();
    }

    private void seedInitialData() {
        facilities.put("ROOM-101", new Room("Room 101"));
        facilities.put("GYM-01", new Gym("Resort Gym", 10));
        facilities.put("REST-01", new Restaurant("Resort Restaurant", 50));
        facilities.put("EVENT-01", new EventRoom("Grand Hall Event Room", 100));
        facilities.put("PARK-01", new Parking("Resort Parking Lot", 30));

        Guest g1 = new Guest("G-01", "Alice", PackageTier.DAY_TOUR);
        Guest g2 = new Guest("G-02", "Bob", PackageTier.OVERNIGHT);
        Guest g3 = new Guest("G-03", "Charlie", PackageTier.VIP);

        guests.put(g1.guestId(), g1);
        guests.put(g2.guestId(), g2);
        guests.put(g3.guestId(), g3);
    }

    public Guest getGuest(String guestId) {
        return guests.get(guestId);
    }

    public Amenity getFacility(String facilityId) {
        return facilities.get(facilityId);
    }

    @Override
    public boolean verifyEntry(Guest guest, Amenity facility) {
        if (guest == null || facility == null) return false;
        if (facility.isFull()) return false;
        return facility.checkAccess(guest);
    }

    @Override
    public String generateAuditLog(Guest guest, Amenity facility) {
        boolean pass = verifyEntry(guest, facility);
        return String.format("[%s] SCAN %s | Guest: %s (%s) -> Facility: %s",
                LocalDateTime.now(),
                (pass ? "APPROVED" : "DENIED"),
                guest != null ? guest.name() : "UNKNOWN",
                guest != null ? guest.packageTier().getDisplayName() : "N/A",
                facility != null ? facility.getFacilityName() : "UNKNOWN");
    }


    @Override
    public LostItemEntity registerLostItem(String description, String location, String guestId) {
        String id = "ITEM-" + (++itemCounter);
        LostItemEntity item = new LostItemEntity(id, description, location, guestId);
        return lostItemRepository.save(item);
    }

    @Override
    public List<LostItemEntity> findItemsByGuest(String guestId) {
        return lostItemRepository.findByAssociatedGuestIdIgnoreCase(guestId);
    }

    @Override
    public List<LostItemEntity> getAllLostItems() {
        return lostItemRepository.findAll();
    }

    @Override
    public boolean claimLostItem(String itemId) {
        Optional<LostItemEntity> itemOpt = lostItemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            LostItemEntity item = itemOpt.get();
            if (!item.isClaimed()) {
                item.setClaimed(true);
                lostItemRepository.save(item);
                return true;
            }
        }
        return false;
    }
}
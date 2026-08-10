package com.aegisresort.service;

import com.aegisresort.facility.EventRoom;
import com.aegisresort.facility.Gym;
import com.aegisresort.facility.Restaurant;
import com.aegisresort.model.Guest;
import com.aegisresort.model.LostItemEntity;
import com.aegisresort.model.PackageTier;
import com.aegisresort.repository.LostItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityScannerServiceTest {

    @Mock
    private LostItemRepository lostItemRepository;

    @InjectMocks
    private SecurityScannerService scannerService;

    private Guest dayTourGuest;
    private Guest vipGuest;

    @BeforeEach
    void setUp() {
        dayTourGuest = new Guest("G-01", "Alice", PackageTier.DAY_TOUR, LocalDate.now(), LocalDate.now(), true);
        vipGuest = new Guest("G-03", "Charlie", PackageTier.VIP, LocalDate.now(), LocalDate.now().plusDays(5), true);
    }

    // --- Access Control & Polymorphism Tests ---

    @Test
    @DisplayName("Day Tour Guest should be DENIED entry to the Gym")
    void dayTourGuestDeniedGym() {
        Gym gym = new Gym("GYM-01", 10);

        boolean accessGranted = scannerService.verifyEntry(dayTourGuest, gym);

        assertFalse(accessGranted, "Day Tour guests must be denied entry to the gym.");
    }

    @Test
    @DisplayName("VIP Guest should be GRANTED entry to the Event Room")
    void vipGuestAllowedEventRoom() {
        EventRoom eventRoom = new EventRoom("EVENT-01", 100);

        boolean accessGranted = scannerService.verifyEntry(vipGuest, eventRoom);

        assertTrue(accessGranted, "VIP guests must be allowed into the Event Room.");
    }

    @Test
    @DisplayName("Day Tour Guest should be GRANTED entry to the Restaurant")
    void dayTourGuestAllowedRestaurant() {
        Restaurant restaurant = new Restaurant("REST-01", 50);

        boolean accessGranted = scannerService.verifyEntry(dayTourGuest, restaurant);

        assertTrue(accessGranted, "All active guest tiers must be allowed into the Restaurant.");
    }

    @Test
    @DisplayName("Full facility must DENY entry regardless of Guest Tier")
    void fullFacilityDeniesEntry() {
        Gym gym = new Gym("GYM-01", 1);
        gym.incrementOccupancy(); // Capacity becomes 1/1 (Full)

        boolean accessGranted = scannerService.verifyEntry(vipGuest, gym);

        assertFalse(accessGranted, "Entry must be denied when the facility is at max capacity.");
    }

    // --- Logistics & Mockito Repository Tests ---

    @Test
    @DisplayName("Registering a lost item should save the entity via repository")
    void registerLostItemSavesToRepository() {
        LostItemEntity mockItem = new LostItemEntity("ITEM-101", "Rolex Watch", "Poolside", "G-03");
        when(lostItemRepository.save(any(LostItemEntity.class))).thenReturn(mockItem);

        LostItemEntity created = scannerService.registerLostItem("Rolex Watch", "Poolside", "G-03");

        assertNotNull(created);
        assertEquals("Rolex Watch", created.getDescription());
        verify(lostItemRepository, times(1)).save(any(LostItemEntity.class));
    }

    @Test
    @DisplayName("Claiming an unclaimed item should update status and save")
    void claimLostItemSuccess() {
        LostItemEntity unclaimedItem = new LostItemEntity("ITEM-101", "Sunglasses", "Lobby", "G-01");
        when(lostItemRepository.findById("ITEM-101")).thenReturn(Optional.of(unclaimedItem));

        boolean claimed = scannerService.claimLostItem("ITEM-101");

        assertTrue(claimed, "Claiming an unclaimed item should succeed.");
        assertTrue(unclaimedItem.isClaimed(), "Item status should update to claimed.");
        verify(lostItemRepository, times(1)).save(unclaimedItem);
    }
}

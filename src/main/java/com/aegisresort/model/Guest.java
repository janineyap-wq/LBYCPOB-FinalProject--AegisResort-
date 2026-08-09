package com.aegisresort.model;

import java.time.LocalDate;

public record Guest(
        String guestId,
        String name,
        PackageTier packageTier,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        boolean isActive
) {
    public Guest {
        if (guestId == null || guestId.isBlank()) {
            throw new IllegalArgumentException("Guest ID cannot be empty.");
        }
    }
}

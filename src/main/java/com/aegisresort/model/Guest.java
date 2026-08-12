package com.aegisresort.model;

public record Guest(
        String guestId,
        String name,
        PackageTier packageTier
) {

    public Guest {
        if (guestId == null || guestId.isBlank()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty.");
        }
        if (packageTier == null) {
            packageTier = PackageTier.DAY_TOUR;
        }
    }
}
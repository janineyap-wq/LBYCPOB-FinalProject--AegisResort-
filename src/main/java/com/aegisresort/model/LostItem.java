package com.aegisresort.model;

import java.time.LocalDateTime;

public class LostItem {
    private final String itemId;
    private final String description;
    private final String locationFound;
    private final LocalDateTime dateLogged;
    private final String associatedGuestId;
    private boolean isClaimed;

    public LostItem(String itemId, String description, String locationFound, String associatedGuestId) {
        this.itemId = itemId;
        this.description = description;
        this.locationFound = locationFound;
        this.associatedGuestId = associatedGuestId;
        this.dateLogged = LocalDateTime.now();
        this.isClaimed = false;
    }

    public String getItemId() { return itemId; }
    public String getDescription() { return description; }
    public String getLocationFound() { return locationFound; }
    public LocalDateTime getDateLogged() { return dateLogged; }
    public String getAssociatedGuestId() { return associatedGuestId; }
    public boolean isClaimed() { return isClaimed; }

    public void markClaimed() {
        this.isClaimed = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Location: %s | Guest ID: %s | Status: %s",
                itemId, description, locationFound,
                (associatedGuestId != null ? associatedGuestId : "UNLINKED"),
                (isClaimed ? "CLAIMED" : "UNCLAIMED"));
    }
}


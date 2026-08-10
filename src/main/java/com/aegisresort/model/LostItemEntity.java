package com.aegisresort.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "lost_items")
public class LostItemEntity {

    @Id
    private String itemId;
    private String description;
    private String locationFound;
    private LocalDateTime dateLogged;
    private String associatedGuestId;
    private boolean isClaimed;

    // Default constructor required by JPA
    public LostItemEntity() {}

    public LostItemEntity(String itemId, String description, String locationFound, String associatedGuestId) {
        this.itemId = itemId;
        this.description = description;
        this.locationFound = locationFound;
        this.associatedGuestId = associatedGuestId;
        this.dateLogged = LocalDateTime.now();
        this.isClaimed = false;
    }

    // Getters and Setters
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocationFound() { return locationFound; }
    public void setLocationFound(String locationFound) { this.locationFound = locationFound; }

    public LocalDateTime getDateLogged() { return dateLogged; }
    public void setDateLogged(LocalDateTime dateLogged) { this.dateLogged = dateLogged; }

    public String getAssociatedGuestId() { return associatedGuestId; }
    public void setAssociatedGuestId(String associatedGuestId) { this.associatedGuestId = associatedGuestId; }

    public boolean isClaimed() { return isClaimed; }
    public void setClaimed(boolean claimed) { isClaimed = claimed; }
}

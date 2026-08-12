package com.aegisresort.model;

import java.time.LocalDateTime;

public class LostItem {
    private String id;
    private String description;
    private String location;
    private String guestId;
    private String status; // e.g., "UNCLAIMED", "CLAIMED"
    private LocalDateTime dateReported;

    public LostItem() {
        this.dateReported = LocalDateTime.now();
        this.status = "UNCLAIMED";
    }

    public LostItem(String id, String description, String location, String guestId, String status) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.guestId = guestId;
        this.status = status != null ? status : "UNCLAIMED";
        this.dateReported = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateReported() {
        return dateReported;
    }

    public void setDateReported(LocalDateTime dateReported) {
        this.dateReported = dateReported;
    }
}
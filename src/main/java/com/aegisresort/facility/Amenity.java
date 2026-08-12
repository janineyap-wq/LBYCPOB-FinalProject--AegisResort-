package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public abstract class Amenity {
    private String name;
    private int capacity;
    private int currentOccupancy;
    private boolean active;

    // Constructor matching subclass super(name, capacity) calls
    public Amenity(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.active = true;
    }

    // Abstract method that every facility subclass must implement
    public abstract boolean checkAccess(Guest guest);

    // Business & Access Helper Methods
    public boolean isFull() {
        return currentOccupancy >= capacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getFacilityName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }
}
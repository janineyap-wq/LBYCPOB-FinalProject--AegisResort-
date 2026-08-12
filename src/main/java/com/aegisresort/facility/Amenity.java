package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public abstract class Amenity {
    private String name;
    private int capacity;
    private int currentOccupancy;
    private boolean active;

    public Amenity(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.active = true;
    }


    public abstract boolean checkAccess(Guest guest);


    public boolean isFull() {
        return currentOccupancy >= capacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


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
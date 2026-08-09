package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public abstract class Amenity {
    private final String facilityId;
    private final String facilityName;
    private final int maxCapacity;
    private int currentOccupancy;

    public Amenity(String facilityId, String facilityName, int maxCapacity) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.maxCapacity = maxCapacity;
        this.currentOccupancy = 0;
    }

    public String getFacilityId() { return facilityId; }
    public String getFacilityName() { return facilityName; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentOccupancy() { return currentOccupancy; }

    public boolean isFull() {
        return currentOccupancy >= maxCapacity;
    }

    public boolean incrementOccupancy() {
        if (!isFull()) {
            currentOccupancy++;
            return true;
        }
        return false;
    }

    public abstract boolean checkAccess(Guest guest);

    @Override
    public String toString() {
        return String.format("%s (%s) - Occupancy: %d/%d", facilityName, facilityId, currentOccupancy, maxCapacity);
    }
}

package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class EventRoom extends Amenity {

    // Single-argument constructor for default capacity
    public EventRoom(String name) {
        super(name, 50); // Default capacity of 50
    }

    // Two-argument constructor matching super(name, capacity)
    public EventRoom(String name, int maxCapacity) {
        super(name, maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null) {
            return false;
        }

        // Exclusive access reserved only for VIP package holders
        return guest.packageTier() == PackageTier.VIP;
    }
}
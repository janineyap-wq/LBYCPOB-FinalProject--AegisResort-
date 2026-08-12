package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class EventRoom extends Amenity {

    public EventRoom(String name) {
        super(name, 50); // Default capacity of 50
    }

    public EventRoom(String name, int maxCapacity) {
        super(name, maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null) {
            return false;
        }

        return guest.packageTier() == PackageTier.VIP;
    }
}
package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Room extends Amenity {

    public Room(String name) {
        super(name, 4);
    }

    public Room(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null) {
            return false;
        }

        PackageTier tier = guest.packageTier();
        return tier == PackageTier.OVERNIGHT || tier == PackageTier.VIP;
    }
}
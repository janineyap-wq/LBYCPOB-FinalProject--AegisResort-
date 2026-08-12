package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Room extends Amenity {

    // Default constructor setting standard room name and capacity
    public Room(String name) {
        super(name, 4); // Default capacity of 4 guests per room
    }

    // Constructor with custom capacity
    public Room(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null) {
            return false;
        }

        // Rooms are restricted to OVERNIGHT and VIP guests
        PackageTier tier = guest.packageTier();
        return tier == PackageTier.OVERNIGHT || tier == PackageTier.VIP;
    }
}
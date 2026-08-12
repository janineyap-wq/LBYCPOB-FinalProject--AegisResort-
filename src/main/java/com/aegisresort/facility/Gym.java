 package com.aegisresort.facility;
import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Gym extends Amenity {

    // Default constructor setting standard gym name and capacity
    public Gym(String name) {
        super(name, 20); // Default capacity of 20
    }

    // Constructor with custom capacity
    public Gym(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null) {
            return false;
        }

        // Gym access is granted to OVERNIGHT and VIP guests
        PackageTier tier = guest.packageTier();
        return tier == PackageTier.OVERNIGHT || tier == PackageTier.VIP;
    }
}
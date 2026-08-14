 package com.aegisresort.facility;
import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Gym extends Amenity {

    public Gym(String name) {
        super(name, 20);
    }


    public Gym(String name, int capacity) {
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
package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Gym extends Amenity {

    public Gym(String facilityId, int maxCapacity) {
        super(facilityId, "Fitness Center", maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null || !guest.isActive()) return false;
        return guest.packageTier() == PackageTier.OVERNIGHT || guest.packageTier() == PackageTier.VIP;
    }
}

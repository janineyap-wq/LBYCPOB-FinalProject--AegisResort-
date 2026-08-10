package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class EventRoom extends Amenity {

    public EventRoom(String facilityId, int maxCapacity) {
        super(facilityId, "Grand Hall Event Room", maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null || !guest.isActive()) return false;
        return guest.packageTier() == PackageTier.VIP;
    }
}

package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Parking extends Amenity {

    public Parking(String facilityId, int maxCapacity) {
        super(facilityId, "Resort Parking Lot", maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        return guest != null && guest.isActive();
    }
}

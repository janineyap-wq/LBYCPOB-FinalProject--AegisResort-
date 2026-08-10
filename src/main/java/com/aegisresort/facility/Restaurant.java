package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Restaurant extends Amenity {

    public Restaurant(String facilityId, int maxCapacity) {
        super(facilityId, "Resort Restaurant", maxCapacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        return guest != null && guest.isActive();
    }
}

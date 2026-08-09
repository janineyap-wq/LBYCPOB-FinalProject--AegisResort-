package com.aegisresort.facility;

import com.aegisresort.model.Guest;
import com.aegisresort.model.PackageTier;

public class Room extends Amenity {
    private final String roomNumber;

    public Room(String facilityId, String roomNumber) {
        super(facilityId, "Guest Room " + roomNumber, 4);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }

    @Override
    public boolean checkAccess(Guest guest) {
        if (guest == null || !guest.isActive()) return false;
        return guest.packageTier() == PackageTier.OVERNIGHT || guest.packageTier() == PackageTier.VIP;
    }
}

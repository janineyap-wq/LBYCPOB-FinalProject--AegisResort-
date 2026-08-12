package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Parking extends Amenity {

    public Parking(String name) {
        super(name, 100);
    }


    public Parking(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        return guest != null;
    }
}
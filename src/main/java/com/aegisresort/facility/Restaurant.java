package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Restaurant extends Amenity {

    public Restaurant(String name) {
        super(name, 80);
    }


    public Restaurant(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {

        return guest != null;
    }
}
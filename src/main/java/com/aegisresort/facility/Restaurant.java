package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Restaurant extends Amenity {

    // Default constructor
    public Restaurant(String name) {
        super(name, 80); // Default capacity of 80 guests
    }

    // Constructor with custom capacity
    public Restaurant(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        // Universal access: dining is open to all registered guests regardless of tier
        return guest != null;
    }
}
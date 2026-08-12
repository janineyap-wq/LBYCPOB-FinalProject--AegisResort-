package com.aegisresort.facility;

import com.aegisresort.model.Guest;

public class Parking extends Amenity {

    // Default constructor
    public Parking(String name) {
        super(name, 100); // Default capacity of 100 vehicles
    }

    // Constructor with custom capacity
    public Parking(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean checkAccess(Guest guest) {
        // Parking is available to all registered resort guests
        return guest != null;
    }
}
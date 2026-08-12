package com.aegisresort.service;

import com.aegisresort.facility.Amenity;
import com.aegisresort.model.Guest;

public interface AccessController {


    boolean verifyEntry(Guest guest, Amenity facility);


    String generateAuditLog(Guest guest, Amenity facility);
}
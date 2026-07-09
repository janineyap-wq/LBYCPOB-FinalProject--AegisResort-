# LBYCPOB-FinalProject-AegisResort
# AegisResort: Dynamic Amenity & Guest Management System

## PROJECT PROPOSAL: RESORT MANAGEMENT SYSTEM
**PROJECT TITLE:** AegisResort: Dynamic Amenity & Guest Management System

---

## TEAM MEMBERS
*   **Aquino, Anthony** - ant6769
*   **Mazo, Cassandra Denise** - cassandramazo-sys
*   **Yap, Janine Lizette** - janineyap-wq

---

## PROBLEM STATEMENT & GOALS
Resorts frequently experience severe operational inefficiencies and revenue leakage due to highly fragmented amenity management systems that rely on manual checks or disconnected databases. Tracking guest permissions across sprawling resort zones—including premium rooms, fitness facilities, parking bays, event halls, and restaurants—creates a significant administrative burden that often results in front-line staff misinterpreting tier-based package inclusions. This lack of real-time communication allows guests to access restricted areas outside their purchased packages, causes conflicting itinerary overlaps where a guest is double-booked for two activities simultaneously, and leaves housekeeping teams without a unified guest timeline to trace and return lost belongings found on the property.

The primary objective of this system is to engineer a centralized, intelligent property management platform that eliminates manual oversight by modeling physical resort facilities, dynamic guest packages, and operational logistics through an integrated object-oriented architecture. By establishing an automated permission matrix where guest profiles are strictly bound to their selected booking tiers, the system dynamically permits or denies entry at amenity scanners based on real-time authorization checks. Furthermore, it implements a real-time conflict detection engine to block overlapping time slots across all assets, applies location-tagging algorithms to cross-reference historical room registries for fast lost-and-found recovery, and encapsulates all transactions into a single, secure digital pass that guests can use to seamlessly navigate the resort.

---

## TARGET USER
*   **Resort Front Desk & Staff:** To check guests in, issue passes, and manage facility availability.
*   **Resort Security/Gatekeepers:** To verify if a specific guest has access to a particular amenity.

---

## BRIEF DESCRIPTION
VibeResort is an automated management application that handles room bookings, facility access, and lost-and-found logging. The core feature is a dynamic permission system: when a guest buys a "Day Tour" package, the system grants them access only to the Restaurant and Pool, while restricting them from Rooms, the Gym, or Event Rooms.

---

## CORE OOP CONCEPTS
*   **Encapsulation:** Each amenity class (e.g., Rooms, Gym) encapsulates its own private data, such as capacity, isOccupied, or operatingHours, exposing them only through public getter and setter methods. Guest profiles hide sensitive details like bookingID and billing totals, updating them strictly via secure class methods.
*   **Inheritance:** A base class Amenity or Facility will contain common properties like facilityName and maxCapacity. The specific classes (Rooms, Gym, Parking, EventRoom, Restaurant) will inherit from this base class to reuse code efficiently.
*   **Polymorphism:** The base class Amenity will feature an abstract method checkAccess(Guest guest). Each subclass overrides this method to implement its own validation logic. For example, Restaurant.checkAccess() returns true for both Day Tour and Overnight guests, whereas Rooms.checkAccess() returns false for Day Tour guests.
*   **Abstraction:** An abstract class or interface named AccessController hides the complex backend permission matrices. The user interface simply calls a clean method like verifyEntry(guest, amenity), completely hiding the underlying conditional logic from the end-user.

---

## INITIAL CLASS IDEAS
*   **Guest / Package (Context Class):** Holds guest details and their specific tier (e.g., DayTour, Overnight, VIP) which dictates their access rights.
*   **Rooms:** Inherits from Amenity. Manages room numbers, room types (Deluxe, Suite), check-in/check-out statuses, and restricts entry to overnight/VIP tiers.
*   **Gym:** Inherits from Amenity. Tracks active gym users and limits access to premium or overnight tiers.
*   **Parking:** Inherits from Amenity. Manages available vehicle slots and cross-references active guest check-ins.
*   **EventRoom:** Inherits from Amenity. Handles specific event bookings, schedules, and restricted private party access.
*   **Restaurant:** Inherits from Amenity. Manages seating availability and allows universal access (or tier-specific discounts) for all package holders.
*   **LostAndFound:** A utility class that logs reported items, item status (Claimed/Missing), and connects them to a specific Guest ID.

---

## USER STORIES
*   **As a Front Desk Agent,** I want to assign a Day Tour package to a walking guest so that the system automatically restricts them from checking into a room.
*   **As a Resort Security Guard,** I want to scan or check a guest's package type at the Gym entrance so that I can instantly see if they are permitted to enter.
*   **As a Guest,** I want to report a misplaced item to the staff so that it can be logged into the Lost & Found system under my profile.

---

## CORE FEATURES
*   **Tier-Based Access Validation:** A rule engine that automatically approves or denies access to specific resort zones (Gym, Rooms, etc.) based on the guest's active package.
*   **Amenity Status Tracking:** Real-time tracking of capacity and occupancy for the EventRoom, Parking, and Rooms.
*   **Lost & Found Registry:** A centralized log to record, update, and match lost items with registered guests.

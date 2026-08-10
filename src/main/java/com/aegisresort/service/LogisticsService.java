package com.aegisresort.service;

import com.aegisresort.model.LostItem;
import java.util.List;

public interface LogisticsService {
    LostItem registerLostItem(String description, String location, String guestId);
    List<LostItem> findItemsByGuest(String guestId);
    boolean claimLostItem(String itemId);
}

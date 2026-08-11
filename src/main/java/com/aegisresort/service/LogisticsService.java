package com.aegisresort.service;

import com.aegisresort.model.LostItemEntity;
import java.util.List;

public interface LogisticsService {
    LostItemEntity registerLostItem(String description, String location, String guestId);
    List<LostItemEntity> findItemsByGuest(String guestId);
    List<LostItemEntity> getAllLostItems();
    boolean claimLostItem(String itemId);
}

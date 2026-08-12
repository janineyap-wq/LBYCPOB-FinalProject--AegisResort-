package com.aegisresort.repository;

import com.aegisresort.model.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItemEntity, String> {

    // Primary search query
    List<LostItemEntity> findByAssociatedGuestIdIgnoreCase(String associatedGuestId);

    // Exact match search query (fixes SecurityScannerService calls)
    List<LostItemEntity> findByAssociatedGuestId(String associatedGuestId);
}
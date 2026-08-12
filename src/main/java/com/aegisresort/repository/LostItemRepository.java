package com.aegisresort.repository;

import com.aegisresort.model.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItemEntity, String> {

    List<LostItemEntity> findByAssociatedGuestIdIgnoreCase(String associatedGuestId);

    List<LostItemEntity> findByAssociatedGuestId(String associatedGuestId);
}
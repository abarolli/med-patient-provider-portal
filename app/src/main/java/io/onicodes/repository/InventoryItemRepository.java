package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.InventoryItem;
import io.onicodes.entity.InventoryItemId;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, InventoryItemId> {
    
}

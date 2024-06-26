package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    String createInventory(InventoryDto inventoryDto);
    String updateInventory(InventoryDto inventoryDto, Long inventoryId);
    List<InventoryDto> getAllInventories();
    InventoryDto getInventoryById(Long inventoryId);
    void deleteInventory(Long inventoryId);

}

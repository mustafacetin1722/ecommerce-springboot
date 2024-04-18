package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.InventoryDto;
import com.mustafa.ecommercespringboot.model.Inventory;
import com.mustafa.ecommercespringboot.repository.InventoryRepository;
import com.mustafa.ecommercespringboot.service.InventoryService;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Override
    public String createInventory(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setReferenceCode(inventoryDto.getReferenceCode());
        this.inventoryRepository.save(inventory);
        return "Inventory successfully created.";
    }

    @Override
    public String updateInventory(InventoryDto inventoryDto, Long inventoryId) {
        Inventory inventory = this.inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found."));;
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setReferenceCode(inventoryDto.getReferenceCode());
        this.inventoryRepository.save(inventory);
        return "Inventory successfully updated.";
    }

    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventoryList = this.inventoryRepository.findAll();
        List<InventoryDto> inventoryDtoList = inventoryList.stream()
                .map(inventory -> new InventoryDto(inventory.getQuantity(), inventory.getReferenceCode()))
                .collect(Collectors.toList());
        return inventoryDtoList;
    }

    @Override
    public InventoryDto getInventoryById(Long inventoryId) {
        Inventory inventory = this.inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found."));
        InventoryDto inventoryDto = new InventoryDto(inventory.getQuantity(), inventory.getReferenceCode());
        return inventoryDto;
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = this.inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found."));
        this.inventoryRepository.delete(inventory);
    }
}

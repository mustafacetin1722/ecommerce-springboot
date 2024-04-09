package com.mustafa.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String uuid;
    private String description;
    private double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String categoryUuid;
    private List<PromotionDto> promotionList;
}

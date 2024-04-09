package com.mustafa.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private String uuid;
    private String name;
    private String description;
    private double discount;
    private String code;
    private List<ProductDto> productList;
}

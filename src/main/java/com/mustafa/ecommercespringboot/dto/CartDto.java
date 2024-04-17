package com.mustafa.ecommercespringboot.dto;

import com.mustafa.ecommercespringboot.model.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class CartDto {
    private String uuid;
    private LocalDate localDate;
    private String userUuid;
    private String promotionUuid;
    private List<String> productUuids;
    private List<ProductDto> productDtoList;
}

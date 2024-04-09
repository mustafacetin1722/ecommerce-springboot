package com.mustafa.ecommercespringboot.dto;

import java.time.LocalDate;
import java.util.List;

public class CartDto {
    private String uuid;
    private LocalDate orderDate;
    private String userUuid;
    private String promotionUuid;
    private String[] productUuids;
    private List<ProductDto> productDtoList;
}

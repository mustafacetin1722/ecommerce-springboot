package com.mustafa.ecommercespringboot.dto.reponse;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponse {
    private List<ProductDto> productList;
    private String userUuid;}

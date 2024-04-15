package com.mustafa.ecommercespringboot.dto.reponse;

import com.mustafa.ecommercespringboot.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponse {
    private List<Product> productList;
    private String userUuid;}

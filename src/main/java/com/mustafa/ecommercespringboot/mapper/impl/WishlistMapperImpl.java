package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.reponse.WishlistResponse;
import com.mustafa.ecommercespringboot.mapper.ProductMapper;
import com.mustafa.ecommercespringboot.mapper.WishlistMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Wishlist;

import java.util.List;
import java.util.stream.Collectors;

public class WishlistMapperImpl implements WishlistMapper {
    private final ProductMapper productMapper;

    public WishlistMapperImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public WishlistResponse convertToResponse(Wishlist wishlist) {
        WishlistResponse response = new WishlistResponse();
        if (wishlist != null) {
            List<ProductDto> productDtoList = wishlist.getProductList()
                    .stream().map(product -> this.productMapper.toProductDto(product))
                    .collect(Collectors.toList());
            response.setProductList(productDtoList);
            response.setUserUuid(wishlist.getUuid());
        }
        return response;
    }
}

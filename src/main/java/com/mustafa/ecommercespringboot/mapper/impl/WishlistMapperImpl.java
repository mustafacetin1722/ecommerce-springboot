package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.reponse.WishlistResponse;
import com.mustafa.ecommercespringboot.mapper.WishlistMapper;
import com.mustafa.ecommercespringboot.model.Wishlist;

public class WishlistMapperImpl implements WishlistMapper {
    @Override
    public WishlistResponse convertToResponse(Wishlist wishlist) {
        WishlistResponse response = new WishlistResponse();
        if (wishlist != null) {
            response.setProductList(wishlist.getProductList());
            response.setUserUuid(wishlist.getUuid());
        }
        return response;
    }
}

package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.reponse.WishlistResponse;
import com.mustafa.ecommercespringboot.model.Wishlist;

public interface WishlistMapper {
    WishlistResponse convertToResponse(Wishlist wishlist);
}

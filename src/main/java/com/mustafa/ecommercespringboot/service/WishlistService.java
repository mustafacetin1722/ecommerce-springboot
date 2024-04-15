package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.reponse.WishlistResponse;

public interface WishlistService {
    String addToWishlist(String userUuid, String productUuid);

    String removeFromWishlist(String userUuid, String productUuid);

    WishlistResponse getWishlistByUserUuid(String userUuid);

    WishlistResponse getWishlistByUuid(String wishlistUuid);
}

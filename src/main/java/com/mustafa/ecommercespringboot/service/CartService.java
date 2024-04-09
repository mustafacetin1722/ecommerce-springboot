package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;

public interface CartService {
    CartResponse saveCart(CartDto cartDto);
    CartResponse updateCart(CartDto cartDto, String cartUuid);
    void deleteCart(String cartUuid);
    CartDto getCartByUuid(String cartUuid);
    CartDto getCartByUserUuid(String userUuid);
}

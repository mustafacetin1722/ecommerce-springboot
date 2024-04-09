package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;
import com.mustafa.ecommercespringboot.repository.CartRepository;
import com.mustafa.ecommercespringboot.repository.PromotionRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    public CartServiceImpl(CartRepository cartRepository
                         , UserRepository userRepository
                         , PromotionRepository promotionRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public CartResponse saveCart(CartDto cartDto) {
        return null;
    }

    @Override
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        return null;
    }

    @Override
    public void deleteCart(String cartUuid) {

    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        return null;
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid) {
        return null;
    }
}

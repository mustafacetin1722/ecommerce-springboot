package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;
import com.mustafa.ecommercespringboot.mapper.CartMapper;
import com.mustafa.ecommercespringboot.repository.CartRepository;
import com.mustafa.ecommercespringboot.repository.PromotionRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.service.CartService;
import com.mustafa.ecommercespringboot.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;
    private final ProductService productService;
    private final CartMapper cartMapper;
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository,
                           PromotionRepository promotionRepository, ProductService productService,
                           CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.promotionRepository = promotionRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
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

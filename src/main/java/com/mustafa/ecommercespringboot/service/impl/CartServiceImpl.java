package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;
import com.mustafa.ecommercespringboot.mapper.CartMapper;
import com.mustafa.ecommercespringboot.model.Cart;
import com.mustafa.ecommercespringboot.model.Promotion;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.repository.CartRepository;
import com.mustafa.ecommercespringboot.repository.PromotionRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.service.CartService;
import com.mustafa.ecommercespringboot.service.ProductService;
import com.mustafa.ecommercespringboot.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

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
        Optional<User> userOptional = userRepository.findByUuid(cartDto.getUserUuid());
        Optional<Promotion> promotionOptional = Optional.ofNullable(promotionRepository.findByUuid(cartDto.getPromotionUuid()));
        if (userOptional.isEmpty() && promotionOptional.isEmpty()) {
            throw new RuntimeException("User or Promotion not found.");
        } else if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        } else if (promotionOptional.isEmpty()) {
            throw new RuntimeException("Promotion not found.");
        }
        Cart cart = new Cart();
        cart.setUuid(UUID.randomUUID().toString());
        cart.setOrderDate(new Timestamp(System.currentTimeMillis()));
        cart.setUser(userOptional.get());
        cart.setPromotion(promotionOptional.get());
        cart.setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(cart);
        return cartMapper.createResponse(cart);
    }

    @Override
    public CartResponse updateCart(CartDto cartDto, String cartUuid) {
        Optional<Cart> optionalCart = cartRepository.findByUuid(cartUuid);
        Optional<Promotion> optionalPromotion = Optional.ofNullable(promotionRepository.findByUuid(cartDto.getPromotionUuid()));
        if (optionalCart.isEmpty() && optionalPromotion.isEmpty()){
            throw new RuntimeException("Cart or Promotion not found.");
        }
        optionalCart.get().setPromotion(optionalPromotion.get());
        optionalCart.get().setProductUUIDs(CommonUtils.arrayToCommaSeparatedString(cartDto.getProductUuids()));
        cartRepository.save(optionalCart.get());
        return cartMapper.createResponse(optionalCart.get());
    }

    @Override
    public void deleteCart(String cartUuid) {
        Optional<Cart> optionalCart = cartRepository.findByUuid(cartUuid);
        if (optionalCart.isEmpty()){
            throw new RuntimeException("Cart not found.");
        }
        cartRepository.delete(optionalCart.get());
    }

    @Override
    public CartDto getCartByUuid(String cartUuid) {
        Optional<Cart> optionalCart = cartRepository.findByUuid(cartUuid);
        if (optionalCart.isEmpty()){
            throw new RuntimeException("Cart not found.");
        }
        return cartMapper.convertToDto(optionalCart.get());
    }

    @Override
    public CartDto getCartByUserUuid(String userUuid) {
        Optional<Cart> optionalCart = cartRepository.findByUserUuid(userUuid);
        if (optionalCart.isEmpty()){
            throw new RuntimeException("User not found.");
        }
        return cartMapper.convertToDto(optionalCart.get());
    }
}

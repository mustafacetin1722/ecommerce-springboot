package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ReturnDto;
import com.mustafa.ecommercespringboot.mapper.ReturnMapper;
import com.mustafa.ecommercespringboot.model.*;
import com.mustafa.ecommercespringboot.repository.CartRepository;
import com.mustafa.ecommercespringboot.repository.OrderRepository;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ReturnMapperImpl implements ReturnMapper {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public ReturnMapperImpl(UserRepository userRepository,
                            ProductRepository productRepository,
                            CartRepository cartRepository,
                            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ReturnDto toReturnDto(Return returnModel) {
        ReturnDto returnDto = new ReturnDto();
        returnDto.setAddress(returnModel.getAddress());
        returnDto.setReason(returnModel.getReason());
        returnDto.setReturnDate(returnModel.getReturnDate());
        returnDto.setUserUuid(returnModel.getUser().getUuid());
        returnDto.setProductUuid(returnModel.getProduct().getUuid());
        returnDto.setCartId(returnModel.getCart().getId());
        returnDto.setOrderId(returnModel.getOrder().getId());
        return returnDto;
    }

    @Override
    public Return toReturn(ReturnDto returnDto) {
        Return returnModel = new Return();
        returnModel.setAddress(returnDto.getAddress());
        returnModel.setReason(returnDto.getReason());
        returnModel.setReturnDate(returnDto.getReturnDate());
        User user = this.userRepository.findByUuid(returnDto.getUserUuid())
                .orElseThrow(() -> new RuntimeException("User not found with UUID: " + returnDto.getUserUuid()));
        returnModel.setUser(user);
        Product product = this.productRepository.findByUuid(returnDto.getProductUuid())
                .orElseThrow(() -> new RuntimeException("Product not found with UUID: " + returnDto.getProductUuid()));
        returnModel.setProduct(product);
        Cart cart = this.cartRepository.findById(returnDto.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + returnDto.getCartId()));
        returnModel.setCart(cart);
        Order order = this.orderRepository.findById(returnDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + returnDto.getOrderId()));
        returnModel.setOrder(order);
        return returnModel;
    }

    @Override
    public List<ReturnDto> toReturnDtoList(List<Return> returnList) {
        return returnList.stream()
                .map(returnModel -> toReturnDto(returnModel)).collect(Collectors.toList());
    }
}

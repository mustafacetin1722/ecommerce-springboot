package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.OrderDto;
import com.mustafa.ecommercespringboot.mapper.OrderMapper;
import com.mustafa.ecommercespringboot.model.Order;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapperImpl implements OrderMapper {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderMapperImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order toOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());
        User user = this.userRepository.findByUuid(orderDto.getUserUuid())
                .orElseThrow(() -> new RuntimeException("User not found."));
        order.setUser(user);
        List<Product> productList = new ArrayList<>();
        for (String productUuid : orderDto.getProductUuid()){
            Product product = this.productRepository.findByUuid(productUuid)
                    .orElseThrow(() -> new RuntimeException("Product not found."));
            productList.add(product);
        }
        order.setProductList(productList);
        return order;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUuid(order.getUuid());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setOrderDate(order.getOrderDate().toLocalDateTime().toLocalDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUserUuid(order.getUser().getUuid());
        List<String> productUuid = order.getProductList().stream()
                .map(product -> product.getUuid()).collect(Collectors.toList());
        orderDto.setProductUuid(productUuid);
        return orderDto;
    }
}

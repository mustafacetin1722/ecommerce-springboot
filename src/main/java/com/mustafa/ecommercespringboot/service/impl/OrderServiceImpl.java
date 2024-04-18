package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.OrderDto;
import com.mustafa.ecommercespringboot.mapper.OrderMapper;
import com.mustafa.ecommercespringboot.model.Order;
import com.mustafa.ecommercespringboot.repository.OrderRepository;
import com.mustafa.ecommercespringboot.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public String saveOrder(OrderDto orderDto) {
        Order order = this.orderMapper.toOrder(orderDto);
        this.orderRepository.save(order);
        return "Order saved successfully.";
    }

    @Override
    public String updateOrder(OrderDto orderDto, String orderUuid) {
        return null;
    }

    @Override
    public void deleteOrder(String orderUuid) {
        Order order = this.orderRepository.findByUuid(orderUuid)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        this.orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = this.orderRepository.findAll();
        List<OrderDto> orderDtoList = orderList.stream()
                .map(order -> this.orderMapper.toOrderDto(order)).collect(Collectors.toList());
        return orderDtoList;
    }

    @Override
    public OrderDto getOrderByUuid(String orderUuid) {
        Order order = this.orderRepository.findByUuid(orderUuid)
                .orElseThrow(() -> new RuntimeException("Order not found."));
        OrderDto orderDto = this.orderMapper.toOrderDto(order);
        return orderDto;
    }

    @Override
    public List<OrderDto> getAllOrdersByUserUuid(String userUuid) {
        List<Order> orderList = this.orderRepository.findByUserUuid(userUuid);
        if (orderList.isEmpty()){
            throw new RuntimeException("User not found");
        }
        List<OrderDto> orderDtoList = orderList.stream()
                .map(order -> this.orderMapper.toOrderDto(order)).collect(Collectors.toList());
        return orderDtoList;
    }
}

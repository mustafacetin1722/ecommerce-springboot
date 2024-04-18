package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.CategoryDto;
import com.mustafa.ecommercespringboot.dto.OrderDto;
import com.mustafa.ecommercespringboot.model.Category;
import com.mustafa.ecommercespringboot.model.Order;

public interface OrderMapper {
    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);
}

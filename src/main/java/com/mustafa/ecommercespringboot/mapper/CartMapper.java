package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;
import com.mustafa.ecommercespringboot.model.Cart;

public interface CartMapper {
    CartDto convertToDto(Cart cart);
    CartResponse createResponse(Cart cart);
}

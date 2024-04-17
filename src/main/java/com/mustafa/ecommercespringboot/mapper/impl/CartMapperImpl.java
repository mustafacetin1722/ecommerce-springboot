package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.CartDto;
import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.reponse.CartResponse;
import com.mustafa.ecommercespringboot.mapper.CartMapper;
import com.mustafa.ecommercespringboot.model.Cart;
import com.mustafa.ecommercespringboot.service.ProductService;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CartMapperImpl implements CartMapper {
    private final ProductService productService;
    public CartMapperImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public CartDto convertToDto(Cart cart) {

        String[] uuids = cart.getProductUUIDs().split(",");
        List<ProductDto> productDtoList = new ArrayList<>();
        for (String uuid : uuids){
            productDtoList.add(productService.getProductByUuid(uuid));
        }
        CartDto cartDto = new CartDto();
        cartDto.setUuid(cart.getUuid());
        cartDto.setLocalDate(cart.getOrderDate().toLocalDateTime().toLocalDate());
        cartDto.setUserUuid(cart.getUser().getUuid());
        cartDto.setPromotionUuid(cart.getPromotion().getUuid());
        cartDto.setProductUuids(Arrays.asList(cart.getProductUUIDs()));
        cartDto.setProductDtoList(productDtoList);
        return cartDto;
    }

    @Override
    public CartResponse createResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        String[] uuids = cart.getProductUUIDs().split(",");
        double actualAmount = 0.0;
        for (String uuid : uuids){
            actualAmount+=productService.getProductByUuid(uuid).getPrice();
        }
        double discount = cart.getPromotion().getDiscount();
        cartResponse.setTotalActualAmount(actualAmount);
        cartResponse.setPromotionAmount(discount);
        cartResponse.setTotalAmountAfterPromotion(actualAmount-discount);
        return cartResponse;
    }
}

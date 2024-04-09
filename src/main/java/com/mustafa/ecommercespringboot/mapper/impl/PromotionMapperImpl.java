package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.PromotionDto;
import com.mustafa.ecommercespringboot.mapper.PromotionMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionMapperImpl implements PromotionMapper {
    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {

        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setUuid(promotionDto.getUuid());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotionDto.getDescription());
        promotionDto.setDiscount(promotionDto.getDiscount());
        promotionDto.setCode(promotionDto.getCode());

        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : promotion.getProductList()){
            ProductDto productDto = new ProductDto();
            productDto.setUuid(product.getUuid());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }
        promotionDto.setProductList(productDtoList);

        return promotionDto;
    }

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {

        Promotion promotion = new Promotion();
        promotion.setUuid(promotionDto.getUuid());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setCode(promotionDto.getCode());

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : promotionDto.getProductList()) {
            Product product = new Product();
            product.setUuid(productDto.getUuid());
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            productList.add(product);
        }
        promotion.setProductList(productList);

        return promotion;
    }

    @Override
    public List<PromotionDto> toPromotionDtoList(List<Promotion> promotionList) {
        return promotionList.stream()
                .map(promotion -> toPromotionDto(promotion)).collect(Collectors.toList());
    }
}

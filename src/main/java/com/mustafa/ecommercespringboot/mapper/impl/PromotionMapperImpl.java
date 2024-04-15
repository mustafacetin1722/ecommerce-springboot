package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.PromotionDto;
import com.mustafa.ecommercespringboot.mapper.PromotionMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Promotion;
import com.mustafa.ecommercespringboot.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionMapperImpl implements PromotionMapper {
    private final ProductRepository productRepository;

    public PromotionMapperImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {

        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setUuid(promotionDto.getUuid());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotionDto.getDescription());
        promotionDto.setDiscount(promotionDto.getDiscount());
        promotionDto.setCode(promotionDto.getCode());

        List<String> productUuid = promotion.getProductList().stream()
                .map(product -> product.getUuid()).collect(Collectors.toList());
        promotionDto.setProductUuid(productUuid);

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
        for (String productUuid : promotionDto.getProductUuid()) {
            Product product = productRepository.findByUuid(productUuid)
                    .orElseThrow(() -> new RuntimeException("product not found"));
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

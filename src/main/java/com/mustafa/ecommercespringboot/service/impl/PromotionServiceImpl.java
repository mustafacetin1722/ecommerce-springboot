package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.ProductDto;
import com.mustafa.ecommercespringboot.dto.PromotionDto;
import com.mustafa.ecommercespringboot.mapper.PromotionMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.Promotion;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.PromotionRepository;
import com.mustafa.ecommercespringboot.service.PromotionService;

import java.util.ArrayList;
import java.util.List;

public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final ProductRepository productRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository,
                                PromotionMapper promotionMapper,
                                ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.promotionMapper = promotionMapper;
        this.productRepository = productRepository;
    }

    @Override
    public String savePromotion(PromotionDto promotionDto) {
        Promotion promotion = promotionMapper.toPromotion(promotionDto);

        List<Product> products = new ArrayList<>();
        for (String productUuid : promotionDto.getProductUuid()){
            Product product = productRepository.findByUuid(productUuid)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            products.add(product);
        }
        promotion.setProductList(products);
        promotionRepository.save(promotion);
        return "Successfully";
    }

    @Override
    public String updatePromotion(PromotionDto promotionDto) {
        Promotion promotion = promotionRepository.findByUuid(promotionDto.getUuid());
        if (promotion==null){
            throw new RuntimeException("Promotion not found");
        }
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscount(promotionDto.getDiscount());

        List<Product> products = new ArrayList<>();
        for (String productUuid : promotionDto.getProductUuid()){
            Product product = productRepository.findByUuid(productUuid)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            products.add(product);
        }
        promotion.setProductList(products);
        return null;
    }

    @Override
    public void deletePromotion(String promotionUuid) {
        Promotion promotion = promotionRepository.findByUuid(promotionUuid);
        if (promotion==null){
            throw new RuntimeException("Promotion not found");
        }
        promotionRepository.delete(promotion);
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotionList = promotionRepository.findAll();
        List<PromotionDto> promotionDtoList = promotionMapper.toPromotionDtoList(promotionList);
        return promotionDtoList;
    }

    @Override
    public PromotionDto getPromotionByUuid(String promotionUuid) {
        Promotion promotion = promotionRepository.findByUuid(promotionUuid);
        if (promotion==null){
            throw new RuntimeException("Promotion not found");
        }
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAllPromotionsByProductUuid(String productUuid) {

        return null;
    }
}

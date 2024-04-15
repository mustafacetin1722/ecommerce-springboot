package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    String savePromotion(PromotionDto promotionDto);
    String updatePromotion(PromotionDto promotionDto);
    void deletePromotion(String promotionUuid);
    List<PromotionDto> getAllPromotions();
    PromotionDto getPromotionByUuid(String promotionUuid);
    List<PromotionDto> getAllPromotionsByProductUuid(String productUuid);
}

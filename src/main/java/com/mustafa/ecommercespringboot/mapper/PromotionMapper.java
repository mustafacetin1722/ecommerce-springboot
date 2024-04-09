package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.PromotionDto;
import com.mustafa.ecommercespringboot.model.Promotion;

import java.util.List;

public interface PromotionMapper {
    PromotionDto toPromotionDto(Promotion promotion);
    Promotion toPromotion(PromotionDto promotionDto);
    List<PromotionDto> toPromotionDtoList(List<Promotion> promotionList);
}

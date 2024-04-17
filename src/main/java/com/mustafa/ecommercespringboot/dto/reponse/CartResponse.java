package com.mustafa.ecommercespringboot.dto.reponse;

import lombok.Data;

@Data
public class CartResponse {
    private Double totalActualAmount;
    private Double promotionAmount;
    private Double totalAmountAfterPromotion;
}

package com.mustafa.ecommercespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDto {
    private String address;
    private Date shippedAt;
    private String senderUuid;
    private String orderUuid;
}

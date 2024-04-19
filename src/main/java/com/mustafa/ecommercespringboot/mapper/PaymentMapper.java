package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.PaymentDto;
import com.mustafa.ecommercespringboot.model.Payment;

public interface PaymentMapper {
    Payment toPayment(PaymentDto paymentDto);
    PaymentDto toPaymentDto(Payment payment);
}

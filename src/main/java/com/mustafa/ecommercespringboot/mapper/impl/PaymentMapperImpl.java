package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.PaymentDto;
import com.mustafa.ecommercespringboot.mapper.PaymentMapper;
import com.mustafa.ecommercespringboot.model.Payment;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;

public class PaymentMapperImpl implements PaymentMapper {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PaymentMapperImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Payment toPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setUuid(paymentDto.getUuid());
        payment.setName(paymentDto.getName());
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setExpirationDate(paymentDto.getExpirationDate());
        payment.setCvc(paymentDto.getCvc());
        payment.setAmount(paymentDto.getAmount());
        payment.setUser(userRepository.findByUuid(paymentDto.getUserUuid())
                .orElseThrow(() -> new RuntimeException("User not found")));
        payment.setProduct(productRepository.findByUuid(paymentDto.getProductUuid())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        return payment;
    }

    @Override
    public PaymentDto toPaymentDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUuid(payment.getUuid());
        paymentDto.setName(payment.getName());
        paymentDto.setCardNumber(payment.getCardNumber());
        paymentDto.setExpirationDate(payment.getExpirationDate());
        paymentDto.setCvc(payment.getCvc());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setUserUuid(payment.getUser().getUuid());
        paymentDto.setProductUuid(payment.getProduct().getUuid());
        return paymentDto;
    }
}

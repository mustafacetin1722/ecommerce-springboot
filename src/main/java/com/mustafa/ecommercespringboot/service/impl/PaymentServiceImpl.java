package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.PaymentDto;
import com.mustafa.ecommercespringboot.mapper.PaymentMapper;
import com.mustafa.ecommercespringboot.model.Payment;
import com.mustafa.ecommercespringboot.repository.PaymentRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(UserRepository userRepository,
                              PaymentRepository paymentRepository,
                              PaymentMapper paymentMapper) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment saveCustomerCartDetails(PaymentDto paymentDto) {
        Payment payment = this.paymentMapper.toPayment(paymentDto);
        this.paymentRepository.save(payment);
        return payment;
    }

    @Override
    public String updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto) {
        Payment payment = this.paymentRepository.findByUserUuid(userUuid);
        if (payment==null){
            throw new RuntimeException("User not found.");
        }
        payment.setName(updatedPaymentDto.getName());
        payment.setCardNumber(updatedPaymentDto.getCardNumber());
        payment.setExpirationDate(updatedPaymentDto.getExpirationDate());
        payment.setCvc(updatedPaymentDto.getCvc());
        this.paymentRepository.save(payment);
        return "Cart successfully updated.";
    }

    @Override
    public void deletePaymentByUserUuid(String userUuid) {
        Payment payment = this.paymentRepository.findByUserUuid(userUuid);
        if (payment==null){
            throw new RuntimeException("User not found.");
        }
        this.paymentRepository.delete(payment);
    }

    @Override
    public PaymentDto getUserCartDetailsWithUserUuid(String userUuid) {
        Payment payment = this.paymentRepository.findByUserUuid(userUuid);
        if (payment==null){
            throw new RuntimeException("User not found.");
        }
        PaymentDto paymentDto = this.paymentMapper.toPaymentDto(payment);
        return paymentDto;
    }

    @Override
    public String returnPaymentBackToUser(String userUuid, PaymentDto paymentDto) {
        Payment payment = this.paymentRepository.findByUserUuid(userUuid);
        if (payment==null){
            throw new RuntimeException("User not found.");
        }
        double newAmount = payment.getAmount() - paymentDto.getAmount();
        if (newAmount < 0) {
            throw new RuntimeException("Insufficient funds for return");
        }
        payment.setAmount(newAmount);
        this.paymentRepository.save(payment);
        return "Payment successfully return";
    }

    @Override
    public PaymentDto getPaymentWithPaymentUuid(String paymentUuid) {
        Payment payment = this.paymentRepository.findByUuid(paymentUuid);
        if (payment==null){
            throw new RuntimeException("UUID not found.");
        }
        PaymentDto paymentDto = this.paymentMapper.toPaymentDto(payment);
        return paymentDto;
    }

    /*@Override
    public List<PaymentDto> getPaymentsBeforeDate(LocalDate date) {
        List<Payment> payments = paymentRepository.findByOrderDateBefore(date.);
        return payments.stream()
                .map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<PaymentDto> getPaymentsAfterDate(LocalDate date) {
        List<Payment> payments = paymentRepository.findByOrderDateAfter(date);
        return payments.stream()
                .map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }
*/
}

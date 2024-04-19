package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.PaymentDto;
import com.mustafa.ecommercespringboot.model.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    Payment saveCustomerCartDetails(PaymentDto paymentDto);
    String updatePaymentByUserUuid(String userUuid, PaymentDto updatedPaymentDto);
    void deletePaymentByUserUuid(String userUuid);
    PaymentDto getUserCartDetailsWithUserUuid(String userUuid);
    String returnPaymentBackToUser(String userUuid, PaymentDto paymentDto);
    PaymentDto getPaymentWithPaymentUuid(String paymentUuid);
   /* List<PaymentDto> getPaymentsBeforeDate(LocalDate date);
    List<PaymentDto> getPaymentsAfterDate(LocalDate date);*/
}

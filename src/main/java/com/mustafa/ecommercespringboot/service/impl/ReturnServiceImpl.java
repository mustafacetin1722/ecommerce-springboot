package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.PaymentDto;
import com.mustafa.ecommercespringboot.dto.ReturnDto;
import com.mustafa.ecommercespringboot.mapper.ReturnMapper;
import com.mustafa.ecommercespringboot.model.Inventory;
import com.mustafa.ecommercespringboot.model.Return;
import com.mustafa.ecommercespringboot.repository.ReturnRepository;
import com.mustafa.ecommercespringboot.service.PaymentService;
import com.mustafa.ecommercespringboot.service.ReturnService;

import java.util.List;

public class ReturnServiceImpl implements ReturnService {
    private final ReturnMapper returnMapper;
    private final ReturnRepository returnRepository;
    private final PaymentService paymentService;

    public ReturnServiceImpl(ReturnMapper returnMapper,
                             ReturnRepository returnRepository,
                             PaymentService paymentService) {
        this.returnMapper = returnMapper;
        this.returnRepository = returnRepository;
        this.paymentService = paymentService;
    }

    @Override
    public String createReturn(ReturnDto returnDto, String paymentUuid) {
        PaymentDto paymentDto = this.paymentService.getPaymentWithPaymentUuid(paymentUuid);
        if (paymentDto != null){
            boolean isPaymentReturned = Boolean.parseBoolean(this.paymentService.returnPaymentBackToUser(returnDto.getUserUuid(), paymentDto));
        if (isPaymentReturned){
            Return returnModel = this.returnMapper.toReturn(returnDto);
            this.returnRepository.save(returnModel);
            return "Return successfully created.";
        }else {
            throw new RuntimeException("Error occurred while returning payment.");
           }
        }else {
            throw new RuntimeException("Payment details not found.");
        }
    }

    @Override
    public String updateReturn(ReturnDto returnDto) {
        return null;
    }

    @Override
    public List<ReturnDto> getAllReturns() {
        return null;
    }

    @Override
    public ReturnDto getReturnById(Long returnId) {
        return null;
    }

    @Override
    public void deleteReturn(Long returnId) {

    }
}

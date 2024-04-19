package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.ReturnDto;

import java.util.List;

public interface ReturnService {
    String createReturn(ReturnDto returnDto, String paymentUuid);
    String updateReturn(ReturnDto returnDto);
    List<ReturnDto> getAllReturns();
    ReturnDto getReturnById(Long returnId);
    void deleteReturn(Long returnId);
}

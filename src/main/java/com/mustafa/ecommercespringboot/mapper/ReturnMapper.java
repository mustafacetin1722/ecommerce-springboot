package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.ReturnDto;
import com.mustafa.ecommercespringboot.model.Return;

import java.util.List;

public interface ReturnMapper {
    ReturnDto toReturnDto(Return returnModel);
    Return toReturn(ReturnDto returnDto);
    List<ReturnDto> toReturnDtoList(List<Return> returnList);
}

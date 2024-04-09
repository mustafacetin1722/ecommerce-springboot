package com.mustafa.ecommercespringboot.mapper;

import com.mustafa.ecommercespringboot.dto.UserDto;
import com.mustafa.ecommercespringboot.model.User;

import java.util.List;

public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
    List<UserDto> toUserDtoList(List<User> userList);
}

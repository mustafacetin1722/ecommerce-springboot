package com.mustafa.ecommercespringboot.mapper.impl;

import com.mustafa.ecommercespringboot.dto.UserDto;
import com.mustafa.ecommercespringboot.mapper.UserMapper;
import com.mustafa.ecommercespringboot.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUuid(user.getUuid());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setTelephone(user.getTelephone());
        userDto.setAddress(user.getAddress());
        userDto.setUserRole(user.getUserRole());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setUuid(userDto.getUuid());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());
        user.setUserRole(userDto.getUserRole());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;    }

    @Override
    public List<UserDto> toUserDtoList(List<User> userList) {
        return userList.stream()
                .map(user -> toUserDto(user)).collect(Collectors.toList());
    }
}

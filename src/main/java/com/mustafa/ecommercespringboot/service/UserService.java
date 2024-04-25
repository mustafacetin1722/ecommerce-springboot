package com.mustafa.ecommercespringboot.service;

import com.mustafa.ecommercespringboot.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByUuid(String uuid);
    void saveUser(UserDto userDto);
    void updateUser(String uuid, UserDto updatedUserDto);
    void deleteUser(String uuid);
    void updateWishlistForUser(String userUuid, List<String> productUuids);
}

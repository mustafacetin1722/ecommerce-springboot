package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.UserDto;
import com.mustafa.ecommercespringboot.enums.UserRole;
import com.mustafa.ecommercespringboot.mapper.UserMapper;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtoList(users);
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid).
                orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDto(user);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        userRepository.save(user);
    }

    @Override
    public void updateUser(String uuid, UserDto updatedUserDto) {
        User user = userRepository.findByUuid(uuid).
                orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(updatedUserDto.getUserName());
        user.setEmail(updatedUserDto.getEmail());
        user.setPassword(updatedUserDto.getPassword());
        user.setTelephone(updatedUserDto.getTelephone());
        user.setAddress(updatedUserDto.getAddress());
        user.setUserRole(UserRole.valueOf(updatedUserDto.getUserRole().toString()));
        user.setCreatedAt(updatedUserDto.getCreatedAt());
        user.setUpdatedAt(updatedUserDto.getUpdatedAt());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String uuid) {
        User user = userRepository.findByUuid(uuid).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}

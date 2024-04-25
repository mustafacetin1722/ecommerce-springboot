package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.UserDto;
import com.mustafa.ecommercespringboot.enums.UserRole;
import com.mustafa.ecommercespringboot.mapper.UserMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.model.Wishlist;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.repository.WishlistRepository;
import com.mustafa.ecommercespringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
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

    @Override
    public void updateWishlistForUser(String userUuid, List<String> productUuids) {
        Wishlist wishlist = this.wishlistRepository.findByUserUuid(userUuid);
        if (wishlist == null){
            throw new RuntimeException("WishList not found for user with UUID: "+userUuid);
        }
        List<Product> productList = new ArrayList<>();
        for (String uuid : productUuids){
            Product product = this.productRepository.findByUuid(uuid)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            productList.add(product);
            wishlist.setProductList(productList);
            this.wishlistRepository.save(wishlist);
        }
    }
}

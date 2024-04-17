package com.mustafa.ecommercespringboot.service.impl;

import com.mustafa.ecommercespringboot.dto.reponse.WishlistResponse;
import com.mustafa.ecommercespringboot.mapper.WishlistMapper;
import com.mustafa.ecommercespringboot.model.Product;
import com.mustafa.ecommercespringboot.model.User;
import com.mustafa.ecommercespringboot.model.Wishlist;
import com.mustafa.ecommercespringboot.repository.ProductRepository;
import com.mustafa.ecommercespringboot.repository.UserRepository;
import com.mustafa.ecommercespringboot.repository.WishlistRepository;
import com.mustafa.ecommercespringboot.service.WishlistService;

import java.util.List;
import java.util.stream.Collectors;

public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository,
                               WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public String addToWishlist(String userUuid, String productUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found."));
        Product product = productRepository.findByUuid(productUuid)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        Wishlist wishlist = wishlistRepository.findByUserUuid(userUuid);
        // Eğer kullanıcının bir istek listesi yoksa, yeni bir istek listesi oluştur
        if (wishlist==null){
            wishlist = new Wishlist();
            wishlist.setUser(user);
        }
        wishlist.getProductList().add(product);
        wishlistRepository.save(wishlist);
        return "Product added to the Wishlist.";
    }

    @Override
    public String removeFromWishlist(String userUuid, String productUuid) {
        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("User not found."));
        Wishlist wishlist = wishlistRepository.findByUserUuid(userUuid);
        if (wishlist==null){
            throw new RuntimeException("Wishlist not found for User.");
        }
        List<Product> productList = wishlist.getProductList().stream()
                .filter(product -> !product.getUuid().equals(productUuid)).
                collect(Collectors.toList());
        wishlist.setProductList(productList);
        wishlistRepository.save(wishlist);
        return "Product removed from the Wishlist.";
    }

    @Override
    public WishlistResponse getWishlistByUserUuid(String userUuid) {
        Wishlist wishlist = wishlistRepository.findByUserUuid(userUuid);
        if (wishlist==null){
            throw new RuntimeException("User not found");
        }
        return wishlistMapper.convertToResponse(wishlist);
    }

    @Override
    public WishlistResponse getWishlistByUuid(String wishlistUuid) {
        Wishlist wishlist = wishlistRepository.findByUuid(wishlistUuid);
        if (wishlist==null){
            throw new RuntimeException("User not found");
        }
        return wishlistMapper.convertToResponse(wishlist);
    }
}

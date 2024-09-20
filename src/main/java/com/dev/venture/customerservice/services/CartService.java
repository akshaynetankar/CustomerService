package com.dev.venture.customerservice.services;

import com.dev.venture.customerservice.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private Map<Long, CartDto> cartStorage = new HashMap<>();

    public String addToCart(CartDto cartDto){
        cartStorage.put(cartDto.getCustomerId(), cartDto);
        return "Item added to cart!";

    }

    public CartDto getCart(Long customerId){
        return cartStorage.get(customerId);
    }

    public String clearCart(Long customerId){
        cartStorage.remove(customerId);
        return "Cart Cleared...!";
    }












}

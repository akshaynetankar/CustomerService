package com.dev.venture.customerservice.controllers;

import com.dev.venture.customerservice.dto.CartDto;
import com.dev.venture.customerservice.dto.CustomerDto;
import com.dev.venture.customerservice.feignClient.ItemClient;
import com.dev.venture.customerservice.services.CartService;
import com.dev.venture.customerservice.services.CustomerService;
import com.dev.venture.item.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    //Customer registeration
    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto registeredCustomer = customerService.registerCustomer(customerDto);
        return ResponseEntity.ok(registeredCustomer);
    }

    // Get available items for purchase
    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>>getAvailableItems(){
        List<ItemDto> items = (List<ItemDto>) itemClient.getAvailbaleItems();
        return ResponseEntity.ok(items);
    }

    //add items to cart
    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto){
        String response = cartService.addToCart(cartDto);
        return ResponseEntity.ok(response);
    }

    //Manage Cart(view and clear)
    @GetMapping("/cart/view/{customerId}")
    public ResponseEntity<CartDto> viewCart(@PathVariable Long customerId){
        CartDto cart = cartService.getCart(customerId);
        return ResponseEntity.ok(cart);
    }

    // add shipment address
    @PostMapping("/add-address/{customerId}")
    public ResponseEntity<String> addShipmentAddress(@PathVariable Long customerId, @RequestParam String address){
        String response = customerService.addShipmentAddress(customerId, address);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>>getItemsForPurchase(){
        return itemClient.getAvailbaleItems();

    }

}

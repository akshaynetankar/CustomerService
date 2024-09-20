package com.dev.venture.customerservice.services;

import com.dev.venture.customerservice.dto.CustomerDto;
import com.dev.venture.customerservice.model.Customer;
import com.dev.venture.customerservice.repository.CustomerRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //method to register a new customer
    public CustomerDto registerCustomer(CustomerDto customerDto){
        // Convert DTO to entity (you can implement a helper method or use a library like MapStruct)

        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword("encryptedPassword");
        customer.setAddresses(Collections.singletonList(customerDto.getAddress()));
        customer.setRole("CUSTOMER");

        Customer savedCustomer = customerRepository.save(customer);

        return convertToDto(savedCustomer);
    }

    public Optional<Customer> getCustomer(Long customerId){
        return customerRepository.findById(customerId);
    }

    public String addShipmentAddress(Long customerId, String address) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setAddresses(Collections.singletonList(address));
            customerRepository.save(customer);
            return "Shipment address added !";
        }

        return "Customer Not Found";
    }

    //Helper method to convert customer entity to dto
    private CustomerDto convertToDto(Customer customer) {

        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(String.valueOf(customer.getAddresses().stream().toList()))
                .role(customer.getRole())
                .build();

    }

}

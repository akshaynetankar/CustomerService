package com.dev.venture.customerservice.repository;

import com.dev.venture.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //custom method to find a customer by email(used for login or fetching user details)
    Optional<Customer>findByEmail(String email);


}

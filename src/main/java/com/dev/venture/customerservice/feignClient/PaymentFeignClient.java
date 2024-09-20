package com.dev.venture.customerservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service")
public interface PaymentFeignClient {

    @PostMapping("/payment/process")
    String processPayment(Long orderId, Double amount);
}

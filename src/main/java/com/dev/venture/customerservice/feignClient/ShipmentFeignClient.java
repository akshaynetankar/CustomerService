package com.dev.venture.customerservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "shipment-service")
public interface ShipmentFeignClient {

    @PostMapping("/shipment/addAddress")
    String addShipmentAddress(Long customerId, String address);

}

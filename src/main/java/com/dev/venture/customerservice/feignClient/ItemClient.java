package com.dev.venture.customerservice.feignClient;

import com.dev.venture.item.service.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name ="item-service")
public interface ItemClient {

    @GetMapping("/items/available")
    ResponseEntity<List<ItemDto>> getAvailbaleItems();


}

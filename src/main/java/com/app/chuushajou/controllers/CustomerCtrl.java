package com.app.chuushajou.controllers;



import com.app.chuushajou.libs.PageInfo;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.models.Customer;
import com.app.chuushajou.dtos.CustomerDTO;
import com.app.chuushajou.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerCtrl {
    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> getProducts (
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit)
    {
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        Page<CustomerDTO> customerPage = customerService.getCustomers(pageRequest);
        return ResponseEntity.ok(
                PageInfo.of(customerPage, page, limit)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long customerId) {
        try {
            Customer customer = customerService.getCustomerById(customerId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", CustomerDTO.getCustomerFromModel(customer))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

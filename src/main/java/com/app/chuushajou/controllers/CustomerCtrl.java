package com.app.chuushajou.controllers;



import com.app.chuushajou.libs.PageInfo;
import com.app.chuushajou.libs.ResMap;
import com.app.chuushajou.dtos.CustomerDTO;
import com.app.chuushajou.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerCtrl {
    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> getCustomers (
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "search", required = false) String search
    )
    {
        PageRequest pageRequest = PageRequest.of(page-1, limit);
        Page<CustomerDTO> customerPage = customerService.getCustomers(search, pageRequest);
        return ResponseEntity.ok(
                PageInfo.of(customerPage, page, limit)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") Long customerId) {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(customerId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", ResMap.of("customer", customerDTO))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @GetMapping("/phone/{phone_num}")
    public ResponseEntity<?> getCustomer(@PathVariable("phone_num") String phoneNum) {
        try {
            CustomerDTO customerDTO = customerService.getCustomer(phoneNum);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", ResMap.of("customer", customerDTO))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO newCustomerDTO = customerService.createCustomer(customerDTO);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", newCustomerDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") Long customerId) {
        try {
            CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerDTO, customerId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "data", updatedCustomerDTO)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long customerId) {
        try {
            CustomerDTO removedCustomer = customerService.deleteCustomer(customerId);
            return ResponseEntity.ok(
                    ResMap.of(
                            "status", "success",
                            "message", "Customer removed successfully",
                            "data", removedCustomer)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResMap.of("status", "error", "message", e.getMessage())
            );
        }
    }

}

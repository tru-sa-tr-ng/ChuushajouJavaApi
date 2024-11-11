package com.app.chuushajou.services;

import com.app.chuushajou.models.Customer;

import com.app.chuushajou.repositories.CustomerRepository;
import com.app.chuushajou.dtos.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Page<CustomerDTO> getCustomers(PageRequest pageRequest){
        return customerRepository.findAll(pageRequest).map(CustomerDTO::getCustomerFromModel);
    }

    public Customer getCustomerById(long id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow();
    }
}

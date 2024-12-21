package com.app.chuushajou.services;


import com.app.chuushajou.dtos.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Page<CustomerDTO> getCustomers(String search, PageRequest pageRequest);

    CustomerDTO getCustomer(long id);

    CustomerDTO getCustomer(String phone_num);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO, long id);

    CustomerDTO deleteCustomer(long id);
}

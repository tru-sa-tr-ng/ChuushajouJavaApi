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

    public CustomerDTO getCustomerById(long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return CustomerDTO.getCustomerFromModel(customer);
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setCustomer_name(customerDTO.getCustomer_name());
        customer.setPhone_number(customerDTO.getPhone_number());
        customer.setAddress(customerDTO.getAddress());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());

        return CustomerDTO.getCustomerFromModel(customerRepository.save(customer));
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        customer.setCustomer_name(customerDTO.getCustomer_name());
        customer.setPhone_number(customerDTO.getPhone_number());
        customer.setAddress(customerDTO.getAddress());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());

        return CustomerDTO.getCustomerFromModel(customerRepository.save(customer));
    }

    public void removeCustomer(long id) {
        customerRepository.deleteById(id);
    }
}

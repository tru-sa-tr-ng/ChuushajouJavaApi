package com.app.chuushajou.services.implement;

import com.app.chuushajou.models.Customer;
import com.app.chuushajou.repositories.CustomerRepository;
import com.app.chuushajou.dtos.CustomerDTO;
import com.app.chuushajou.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Page<CustomerDTO> getCustomers(String search, PageRequest pageRequest){
        return customerRepository.findCustomers(search, pageRequest).map(CustomerDTO::getCustomerFromModel);
    }
    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return CustomerDTO.getCustomerFromModel(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setCustomer_name(customerDTO.getCustomer_name());
        customer.setPhone_number(customerDTO.getPhone_number());
        customer.setAddress(customerDTO.getAddress());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());

        return CustomerDTO.getCustomerFromModel(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (customerDTO.getCustomer_name() != null)
            customer.setCustomer_name(customerDTO.getCustomer_name());

        if (customerDTO.getPhone_number() != null)
            customer.setPhone_number(customerDTO.getPhone_number());

        if (customerDTO.getAddress() != null)
            customer.setAddress(customerDTO.getAddress());

        if (customerDTO.getCreatedAt() != null)
            customer.setCreatedAt(customerDTO.getCreatedAt());

        if (customerDTO.getUpdatedAt() != null)
            customer.setUpdatedAt(customerDTO.getUpdatedAt());

        if (customerDTO.getRemain() != null)
            customer.setRemain(customerDTO.getRemain());

        return CustomerDTO.getCustomerFromModel(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        customerRepository.delete(customer);

        return CustomerDTO.getCustomerFromModel(customer);
    }
}

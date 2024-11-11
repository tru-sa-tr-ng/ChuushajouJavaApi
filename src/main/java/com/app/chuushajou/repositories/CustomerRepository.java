package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Page<Customer> findAll(Pageable pageable);
}

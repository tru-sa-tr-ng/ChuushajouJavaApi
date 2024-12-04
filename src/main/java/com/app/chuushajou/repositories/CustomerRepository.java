package com.app.chuushajou.repositories;

import com.app.chuushajou.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE " +
            "(:search IS NULL OR c.customer_name LIKE %:search%) OR " +
            "(:search IS NULL OR c.phone_number LIKE %:search%)")
    Page<Customer> findCustomers(@Param("search") String search,
                                 Pageable pageable);
}

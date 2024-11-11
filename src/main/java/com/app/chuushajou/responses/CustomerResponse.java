package com.app.chuushajou.responses;

import com.app.chuushajou.models.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
        "customer_id",
        "customer_name",
        "phone_number",
        "address",
        "createdAt",
        "updatedAt"
})
public class CustomerResponse {
        @JsonProperty("customer_id")
        private long customerId;

        private String customer_name;
        private String phone_number;
        private String address;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static CustomerResponse getCustomerFromModel(Customer customer){
            return new CustomerResponse(
                    customer.getId(),
                    customer.getCustomer_name(),
                    customer.getPhone_number(),
                    customer.getAddress(),
                    customer.getCreatedAt(),
                    customer.getUpdatedAt()
            );
        }
}

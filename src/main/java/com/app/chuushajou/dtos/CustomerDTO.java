package com.app.chuushajou.dtos;

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
        "id",
        "customer_name",
        "phone_number",
        "address",
        "createdAt",
        "updatedAt"
})
public class CustomerDTO {
        @JsonProperty("id")
        private long customerId;

        private String customer_name;
        private String phone_number;
        private String address;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static CustomerDTO getCustomerFromModel(Customer customer){
            return new CustomerDTO(
                    customer.getId(),
                    customer.getCustomer_name(),
                    customer.getPhone_number(),
                    customer.getAddress(),
                    customer.getCreatedAt(),
                    customer.getUpdatedAt()
            );
        }
}

package com.mircoservices.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
@Data

public class Customer {
    @Id
    private String id;
    @NotBlank(message = "Name can not be blank")
    @Size(min = 5, max = 50)
    private String name;
    @Min(value = 18, message = "Age should be greater than 18")
    @NotNull(message = "Age can not be Null")
    private Integer age;
    @Length(min = 10, max = 15)
    private String phoneNumber;
    @NotBlank(message = "Email can not be blank")
    @Email(message = "EmailId ")
    private String email;
    private Boolean isActive;

    public Customer(String name, Integer age, String phoneNumber, String email, Address address, Boolean isActive) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
    }
}

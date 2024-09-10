package com.paway.spring.data.kmoneta.supplier.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@Document(collection = "providers")
public class Provider {

    @Id
    private String id;
    private Address address;
    private ContactInfo contactInfo;
    private String name;

    @Setter
    @Getter
    public static class Address {
        // Getters and Setters
        private String city;
        private String state;
        private String street;
        private String zipCode;

    }

    @Setter
    @Getter
    public static class ContactInfo {
        // Getters and Setters
        private String email;
        private String phone;

    }
}

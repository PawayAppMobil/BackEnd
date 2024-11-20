package com.paway.spring.data.kmoneta.inventory.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class ProductDTO {
    private String description;
    private double price;
    private String productName;
    private int stock;
    private String providerId;


}

package com.paway.spring.data.kmoneta.inventory.controller;

import com.paway.spring.data.kmoneta.inventory.model.ProductDTO;
import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductDTO productDTO) {
        try {
            byte[] imageBytes = null;

            if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
                imageBytes = productDTO.getImage().getBytes();
            }

            // Crear un nuevo producto
            Product product = new Product(
                    productDTO.getDescription(),
                    productDTO.getPrice(),
                    productDTO.getProductName(),
                    productDTO.getStock(),
                    imageBytes,
                    productDTO.getProviderId()
            );

            // Guardar el producto en la base de datos
            Product savedProduct = productRepository.save(product);

            // Devolver el producto guardado
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            // Manejo específico de IOException
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Manejo general de otras excepciones
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String productName) {
        try {
            List<Product> products = new ArrayList<>();

            if (productName == null) {
                productRepository.findAll().forEach(products::add);
            } else {
                productRepository.findByProductNameContaining(productName).forEach(products::add);
            }

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") String id,
            @ModelAttribute ProductDTO productDTO) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setProductName(productDTO.getProductName());
            _product.setDescription(productDTO.getDescription());
            _product.setPrice(productDTO.getPrice());
            _product.setStock(productDTO.getStock());

            if (productDTO.getImage() != null && !productDTO.getImage().isEmpty()) {
                try {
                    _product.setImage(productDTO.getImage().getBytes());
                } catch (IOException e) {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

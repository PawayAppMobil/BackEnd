package com.paway.spring.data.kmoneta.inventory.controller;

import com.paway.spring.data.kmoneta.inventory.model.Message;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping(value="/products", consumes = "multipart/form-data")
    public ResponseEntity<Product> createProduct(
            @RequestParam String description,
            @RequestParam String userId,
            @RequestParam double price,
            @RequestParam String productName,
            @RequestParam int stock,
            @RequestPart MultipartFile image,
            @RequestParam String providerId) {
        try {
            byte[] imageBytes = null;
            if (image != null && !image.isEmpty()) {
                imageBytes = image.getBytes();
            }

            Product product = new Product(
                    description,
                    userId,
                    price,
                    productName,
                    stock,
                    imageBytes,
                    providerId
            );

            Product savedProduct = productRepository.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
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

    @GetMapping("/products/provider/{providerId}")
    public ResponseEntity<List<Product>> getProductsByProviderId(@PathVariable("providerId") String providerId) {
        try {
            List<Product> products = productRepository.findByProviderId(providerId);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/products/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable("userId") String userId) {
        try {
            List<Product> products = productRepository.findByUserId(userId);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") String id,
            @RequestBody ProductDTO productDTO){
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setProductName(productDTO.getProductName());
            _product.setDescription(productDTO.getDescription());
            _product.setPrice(productDTO.getPrice());
            _product.setStock(productDTO.getStock());
            Product updatedProduct = productRepository.save(_product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value="/products/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<Product> updateProductImage(
            @PathVariable("id") String id,
            @RequestPart MultipartFile image) {
        try {
            Optional<Product> productData = productRepository.findById(id);
            if (productData.isPresent()) {
                Product _product = productData.get();
                byte[] imageBytes = null;
                if (image != null && !image.isEmpty()) {
                    imageBytes = image.getBytes();
                }
                _product.setImage(imageBytes);
                Product updatedProduct = productRepository.save(_product);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Message> deleteProduct(@PathVariable("id") String id) {
        try {
            if(!productRepository.existsById(id)){
                Message message = new Message("Objeto no encontrado");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            productRepository.deleteById(id);
            Message message = new Message("Objeto eliminado exitosamente");
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            Message message = new Message("Error al eliminar el objeto");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/stock/{providerId}")
    public List<Integer> getStockByProvider(@PathVariable String providerId) {
        List<Product> products = productRepository.findByProviderId(providerId);
        return products.stream()
                .map(Product::getStock)
                .collect(Collectors.toList());
    }
}

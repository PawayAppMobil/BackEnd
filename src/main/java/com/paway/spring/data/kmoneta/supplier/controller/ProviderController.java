package com.paway.spring.data.kmoneta.supplier.controller;

import com.paway.spring.data.kmoneta.inventory.model.ProviderProductCount;
import com.paway.spring.data.kmoneta.inventory.model.ProviderStock;
import com.paway.spring.data.kmoneta.inventory.repository.ProductRepository;
import com.paway.spring.data.kmoneta.supplier.model.Provider;
import com.paway.spring.data.kmoneta.supplier.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/providers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProviderController {
    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Provider> addProvider(@RequestBody Provider provider) {
        Provider savedProvider = providerRepository.save(provider);
        return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable String id, @RequestBody Provider providerDetails) {
        Optional<Provider> providerOptional = providerRepository.findById(id);
        if (providerOptional.isPresent()) {
            Provider provider = providerOptional.get();
            provider.setName(providerDetails.getName());
            provider.setAddress(providerDetails.getAddress());
            provider.setContactInfo(providerDetails.getContactInfo());
            Provider updatedProvider = providerRepository.save(provider);
            return new ResponseEntity<>(updatedProvider, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable String id) {
        Optional<Provider> providerOptional = providerRepository.findById(id);
        if (providerOptional.isPresent()) {
            providerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> providers = providerRepository.findAll();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }


    @GetMapping("/searchName")
    public ResponseEntity<List<Provider>> getProvidersByName(@RequestParam String name) {
        List<Provider> providers = providerRepository.findByName(name);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @GetMapping("/searchByCity")
    public ResponseEntity<List<Provider>> getProvidersByCity(@RequestParam String city) {
        List<Provider> providers = providerRepository.findByAddress_City(city);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }


    @GetMapping("/searchByState")
    public ResponseEntity<List<Provider>> getProvidersByState(@RequestParam String state) {
        List<Provider> providers = providerRepository.findByAddress_State(state);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }
    @GetMapping("/total-stock")
    public List<ProviderStock> getTotalStockByProvider() {
        return productRepository.getTotalStockByProvider();
    }

}

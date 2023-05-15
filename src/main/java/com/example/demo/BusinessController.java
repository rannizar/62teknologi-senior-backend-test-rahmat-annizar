package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {
    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Business> create(@RequestBody Business business) {
        Business savedBusiness = businessService.save(business);
        return ResponseEntity.ok(savedBusiness);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<Business> update(@RequestBody Business business) {
        Business savedBusiness = businessService.save(business);
        return ResponseEntity.ok(savedBusiness);
    }
    
    @DeleteMapping
    @Transactional
    public ResponseEntity<Business> delete(@RequestBody Business business) {
        businessService.delete(business);
        return ResponseEntity.ok(business);
    }

    @GetMapping
    public ResponseEntity<List<Business>> findAll() {
        List<Business> businesses = businessService.findAll();
        return ResponseEntity.ok(businesses);
    }
}

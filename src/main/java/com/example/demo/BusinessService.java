package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Business save(Business business) {
        return businessRepository.save(business);
    }
    
    public void delete(Business business) {
        businessRepository.delete(business);
    }

    public List<Business> findAll() {
        return businessRepository.findAll();
    }
}

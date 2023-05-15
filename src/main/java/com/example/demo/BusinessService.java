package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    List<Business> findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(
            String locale, String location, String term, Integer limit){
    	return businessRepository.findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(locale, location, term, limit);
    }
    
    public Optional<Business> findById(Long id) {
        return businessRepository.findById(id);
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

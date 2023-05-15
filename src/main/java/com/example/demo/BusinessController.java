package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Business> update(@RequestBody Business updatedBusiness) {
    	 // Cek apakah bisnis sudah ada dalam database berdasarkan ID
    	if(updatedBusiness.getId()==null) {
    		return ResponseEntity.badRequest().build();
    	}
        Optional<Business> existingBusiness = businessService.findById(updatedBusiness.getId());
        if (!existingBusiness.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        // Update data bisnis dengan data yang diterima dari body request
        Business business = existingBusiness.get();
        business.setName(updatedBusiness.getName());
        business.setLocation(updatedBusiness.getLocation());
        business.setLatitude(updatedBusiness.getLatitude());
        business.setLongitude(updatedBusiness.getLongitude());
        business.setCategory(updatedBusiness.getCategory());
        // Update atribut lain sesuai kebutuhan

        // Simpan perubahan ke dalam database
        business = businessService.save(business);
        return ResponseEntity.ok(business);
    }
    
    @DeleteMapping
    @Transactional
    public ResponseEntity<Business> delete(@RequestBody Business business) {
   	 // Cek apakah bisnis sudah ada dalam database berdasarkan ID
    	if(business.getId()==null) {
    		return ResponseEntity.badRequest().build();
    	}
       Optional<Business> existingBusiness = businessService.findById(business.getId());
       if (!existingBusiness.isPresent()) {
           return ResponseEntity.notFound().build();
       }else {
    	   businessService.delete(business);
           return ResponseEntity.ok().build();
       }
    }

    @GetMapping
    public ResponseEntity<List<Business>> search(
    		@RequestParam(required = true) String location,
    		@RequestParam(required = false) String locale,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String sort_by,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) Boolean open_now,
            @RequestParam(required = false) String term
    		) {
        List<Business> businesses = businessService.findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(locale, location, term, limit);
        return ResponseEntity.ok(businesses);
    }
}

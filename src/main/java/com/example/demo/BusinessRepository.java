package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {

	Optional<Business> findById(Long id);
	
	List<Business> findByLocaleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndTermContainingIgnoreCaseAndLimit(
            String locale, String location, String term, Integer limit);
	
}

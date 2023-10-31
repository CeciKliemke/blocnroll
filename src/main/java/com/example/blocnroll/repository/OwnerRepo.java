package com.example.blocnroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blocnroll.entities.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
    
}

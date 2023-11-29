package com.example.blocnroll.repository;

import com.example.blocnroll.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
    
}

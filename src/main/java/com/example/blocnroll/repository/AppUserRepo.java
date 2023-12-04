package com.example.blocnroll.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blocnroll.security.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long>{
    Optional<AppUser> findByEmail(String email);
}

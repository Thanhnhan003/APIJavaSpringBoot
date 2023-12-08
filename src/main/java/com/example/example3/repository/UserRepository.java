package com.example.example3.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.example3.entity.User;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    // Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);
}


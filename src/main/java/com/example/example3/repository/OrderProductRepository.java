package com.example.example3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.example3.entity.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    Page<OrderProduct> findAll(Pageable pageable);
}

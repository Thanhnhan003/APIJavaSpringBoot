package com.example.example3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.example3.entity.OrderProduct;

public interface OrderProductService {
    OrderProduct createOrderProduct(OrderProduct orderproduct);

    OrderProduct getOrderProductById(Long orderproductId);

    Page<OrderProduct> getAllOrderProduct(Pageable pageable);

    OrderProduct updateOrderProduct(OrderProduct orderproduct);

    void deleteOrderProduct(Long orderproductId);
}

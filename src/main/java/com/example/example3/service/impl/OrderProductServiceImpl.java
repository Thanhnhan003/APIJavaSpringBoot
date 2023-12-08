package com.example.example3.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.example3.entity.OrderProduct;
import com.example.example3.service.OrderProductService;

import com.example.example3.repository.OrderProductRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    private OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct getOrderProductById(Long orderProductId) {
        Optional<OrderProduct> optionalOrderProduct = orderProductRepository.findById(orderProductId);
        return optionalOrderProduct.get();
    }

    @Override
    public Page<OrderProduct> getAllOrderProduct(Pageable pageable) {
        return orderProductRepository.findAll(pageable);
    }

    @Override
    public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
        OrderProduct existingOrderProduct = orderProductRepository.findById(orderProduct.getId()).get();
        // Cập nhật thông tin của đơn hàng ở đây
        existingOrderProduct.setName(orderProduct.getName());
        existingOrderProduct.setChocolate(orderProduct.getChocolate());
        existingOrderProduct.setSize(orderProduct.getSize());
        existingOrderProduct.setQuantity(orderProduct.getQuantity());
        existingOrderProduct.setTotalPrice(orderProduct.getTotalPrice());
        existingOrderProduct.setPhoto(orderProduct.getPhoto());
        OrderProduct updatedOrderProduct = orderProductRepository.save(existingOrderProduct);
        return updatedOrderProduct;

    }

    @Override
    public void deleteOrderProduct(Long orderProductId) {
        orderProductRepository.deleteById(orderProductId);
    }
}

package com.example.example3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String chocolate;

    @Column(nullable = false)
    private double totalPrice;

    // Thêm các trường khác liên quan đến đơn hàng (nếu cần)

}

package com.example.userOrderAssignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderId;

    @JoinColumn(name="userId")
    @ManyToOne
    User userId;

    @JoinColumn(name="product_id")
    @OneToOne
    Product productId;

    @JoinColumn(name="address_id")
    @OneToOne
    Address addressId;
    int productQuantity;

}

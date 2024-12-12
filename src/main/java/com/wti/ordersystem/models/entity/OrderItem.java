package com.wti.ordersystem.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private Double price;

  @Column(name = "placed", nullable = false)
  private boolean placed = false;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_cart_id", nullable = false)
  private OrderCart orderCart;
}

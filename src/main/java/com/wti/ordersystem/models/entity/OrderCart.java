package com.wti.ordersystem.models.entity;

import com.wti.ordersystem.enums.ECartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_cart")
public class OrderCart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "address")
  private String address;

  @Column(name = "status")
  private ECartStatus status;

  @OneToMany(mappedBy = "orderCart")
  private List<OrderItem> orderItems;
}

package com.wti.ordersystem.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderCartRequestDto {
  private Long productId;
  private Integer quantity;
  private String userName;
  private String address;
}

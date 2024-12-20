package com.wti.ordersystem.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
  private Long productId;
  private String bookName;
  private String type;
  private Double pricePerQty;
  private Integer quantity;
  private Double price;
}

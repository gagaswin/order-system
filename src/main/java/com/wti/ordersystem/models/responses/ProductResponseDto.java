package com.wti.ordersystem.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
  private Long id;
  private String name;
  private String type;
  private Integer stock;
  private Double price;
}

package com.wti.ordersystem.models.responses;

import com.wti.ordersystem.enums.ECartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCartResponseDto {
  private Long id;
  private String userName;
  private String address;
  private ECartStatus status;
  private List<OrderItemDto> orderItems;
}

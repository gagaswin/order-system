package com.wti.ordersystem.services;

import com.wti.ordersystem.models.requests.AddOrderCartRequestDto;
import com.wti.ordersystem.models.requests.PlaceOrderRequestDto;
import com.wti.ordersystem.models.responses.OrderCartResponseDto;

public interface OrderCartService {
  OrderCartResponseDto addProductToCart(AddOrderCartRequestDto addOrderCartRequestDto);

  OrderCartResponseDto getOrderCart(String userName);

  OrderCartResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto);
}

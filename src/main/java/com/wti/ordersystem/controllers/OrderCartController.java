package com.wti.ordersystem.controllers;

import com.wti.ordersystem.models.requests.AddOrderCartRequestDto;
import com.wti.ordersystem.models.requests.PlaceOrderRequestDto;
import com.wti.ordersystem.models.responses.OrderCartResponseDto;
import com.wti.ordersystem.services.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-cart")
public class OrderCartController {
  private final OrderCartService orderCartService;

  @PostMapping
  public ResponseEntity<OrderCartResponseDto> addProductToCart(@RequestBody AddOrderCartRequestDto addOrderCartRequestDto) {
    OrderCartResponseDto orderCartResponseDto = orderCartService.addProductToCart(addOrderCartRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderCartResponseDto);
  }

  @GetMapping
  public ResponseEntity<OrderCartResponseDto> getOrderCart(@RequestParam String userName) {
    OrderCartResponseDto orderCart = orderCartService.getOrderCart(userName);
    return ResponseEntity.status(HttpStatus.OK).body(orderCart);
  }

  @PostMapping("/place")
  public ResponseEntity<OrderCartResponseDto> placeOrder(@RequestBody PlaceOrderRequestDto placeOrderRequestDto) {
    OrderCartResponseDto orderCartResponseDto = orderCartService.placeOrder(placeOrderRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderCartResponseDto);
  }
}

package com.wti.ordersystem.services.impl;

import com.wti.ordersystem.enums.ECartStatus;
import com.wti.ordersystem.exceptions.ResourceNotFoundException;
import com.wti.ordersystem.models.entity.OrderCart;
import com.wti.ordersystem.models.entity.OrderItem;
import com.wti.ordersystem.models.entity.Product;
import com.wti.ordersystem.models.requests.AddOrderCartRequestDto;
import com.wti.ordersystem.models.requests.PlaceOrderRequestDto;
import com.wti.ordersystem.models.responses.OrderCartResponseDto;
import com.wti.ordersystem.models.responses.OrderItemDto;
import com.wti.ordersystem.repository.OrderCartRepository;
import com.wti.ordersystem.repository.OrderItemRepository;
import com.wti.ordersystem.repository.ProductRepository;
import com.wti.ordersystem.services.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCartServiceImpl implements OrderCartService {
  private final OrderCartRepository orderCartRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;

  @Override
  public OrderCartResponseDto addProductToCart(AddOrderCartRequestDto addOrderCartRequestDto) {
    Product product = productRepository.findById(addOrderCartRequestDto.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    if (product.getStock() < addOrderCartRequestDto.getQuantity()) {
      throw new IllegalArgumentException("Insufficient stock product");
    }

    OrderCart orderCart = orderCartRepository.findByUserNameAndStatus(addOrderCartRequestDto.getUserName(), ECartStatus.DRAFT)
        .orElseGet(() -> OrderCart.builder()
            .userName(addOrderCartRequestDto.getUserName())
            .address(addOrderCartRequestDto.getAddress())
            .status(ECartStatus.DRAFT)
            .orderItems(new ArrayList<>())
            .build());

    OrderItem orderItem = OrderItem.builder()
        .product(product)
        .quantity(addOrderCartRequestDto.getQuantity())
        .price(product.getPrice() * addOrderCartRequestDto.getQuantity())
        .orderCart(orderCart)
        .build();

    orderCart.getOrderItems().add(orderItem);
    product.setStock(product.getStock() - addOrderCartRequestDto.getQuantity());

    productRepository.save(product);
    OrderCart savedOrderCart = orderCartRepository.save(orderCart);
    orderItemRepository.save(orderItem);

    List<OrderItemDto> itemsDto = savedOrderCart.getOrderItems().stream()
        .map(item -> OrderItemDto.builder()
            .productId(item.getProduct().getId())
            .quantity(item.getQuantity())
            .price(item.getPrice())
            .build())
        .toList();

    return OrderCartResponseDto.builder()
        .id(savedOrderCart.getId())
        .userName(savedOrderCart.getUserName())
        .address(savedOrderCart.getAddress())
        .status(savedOrderCart.getStatus())
        .orderItems(itemsDto)
        .build();
  }

  @Override
  public OrderCartResponseDto getOrderCart(String userName) {
    OrderCart orderCart = orderCartRepository.findByUserNameAndStatus(userName, ECartStatus.DRAFT)
        .orElseThrow(() -> new ResourceNotFoundException("No active cart found for this user"));

    List<OrderItemDto> itemsDto = orderCart.getOrderItems().stream()
        .map(item -> OrderItemDto.builder()
            .productId(item.getProduct().getId())
            .quantity(item.getQuantity())
            .price(item.getPrice())
            .build())
        .toList();

    return OrderCartResponseDto.builder()
        .id(orderCart.getId())
        .userName(orderCart.getUserName())
        .address(orderCart.getAddress())
        .status(orderCart.getStatus())
        .orderItems(itemsDto)
        .build();
  }

  @Override
  public OrderCartResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) {
    OrderCart orderCart = orderCartRepository.findByIdAndStatus(placeOrderRequestDto.getCartId(), ECartStatus.DRAFT)
        .orElseThrow(() -> new ResourceNotFoundException("No active cart found for this user"));

    List<OrderItem> selectedItems = orderCart.getOrderItems().stream()
        .filter(item -> placeOrderRequestDto.getItemsId().contains(item.getId()))
        .toList();

    if (selectedItems.isEmpty()) {
      throw new IllegalArgumentException("No items selected for placing order");
    }

    selectedItems.forEach(item -> {
      item.setPlaced(true);
      orderItemRepository.save(item);
    });

    if (orderCart.getOrderItems().stream().allMatch(OrderItem::isPlaced)) {
      orderCart.setStatus(ECartStatus.PLACED);
    }
    OrderCart savedOrderCart = orderCartRepository.save(orderCart);

    List<OrderItemDto> itemsDto = selectedItems.stream()
        .map(item -> OrderItemDto.builder()
            .productId(item.getProduct().getId())
            .quantity(item.getQuantity())
            .price(item.getPrice())
            .build())
        .toList();

    return OrderCartResponseDto.builder()
        .id(savedOrderCart.getId())
        .userName(savedOrderCart.getUserName())
        .address(savedOrderCart.getAddress())
        .status(savedOrderCart.getStatus())
        .orderItems(itemsDto)
        .build();
  }
}

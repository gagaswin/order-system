package com.wti.ordersystem.services;

import com.wti.ordersystem.models.requests.CreateProductRequestDto;
import com.wti.ordersystem.models.requests.UpdateProductRequestDto;
import com.wti.ordersystem.models.responses.ProductResponseDto;
import org.springframework.data.domain.Page;

public interface ProductService {
  ProductResponseDto create(CreateProductRequestDto createProductRequestDto);

  ProductResponseDto get(Long id);

  Page<ProductResponseDto> getAll(int page, int size);

  ProductResponseDto update(Long id, UpdateProductRequestDto updateProductRequestDto);

  void delete(Long id);
}

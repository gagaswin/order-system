package com.wti.ordersystem.services.impl;

import com.wti.ordersystem.exceptions.ResourceNotFoundException;
import com.wti.ordersystem.models.entity.Product;
import com.wti.ordersystem.models.requests.CreateProductRequestDto;
import com.wti.ordersystem.models.requests.UpdateProductRequestDto;
import com.wti.ordersystem.models.responses.ProductResponseDto;
import com.wti.ordersystem.repository.ProductRepository;
import com.wti.ordersystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public ProductResponseDto create(CreateProductRequestDto createProductRequestDto) {
    Product product = Product.builder()
        .name(createProductRequestDto.getName())
        .type(createProductRequestDto.getType())
        .stock(createProductRequestDto.getStock())
        .price(createProductRequestDto.getPrice())
        .build();
    Product savedproduct = productRepository.save(product);

    return ProductResponseDto.builder()
        .id(savedproduct.getId())
        .name(savedproduct.getName())
        .type(savedproduct.getType())
        .stock(savedproduct.getStock())
        .price(savedproduct.getPrice())
        .build();
  }

  @Override
  public ProductResponseDto get(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .type(product.getType())
        .stock(product.getStock())
        .price(product.getPrice())
        .build();
  }

  @Override
  public Page<ProductResponseDto> getAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Product> productPage = productRepository.findAll(pageable);

    return productPage.map(product -> ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .type(product.getType())
        .stock(product.getStock())
        .price(product.getPrice())
        .build());
  }

  @Override
  public ProductResponseDto update(Long id, UpdateProductRequestDto updateProductRequestDto) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    product = Product.builder()
        .id(product.getId())
        .name(updateProductRequestDto.getName() != null ?
            updateProductRequestDto.getName() : product.getName())
        .type(updateProductRequestDto.getType() != null ?
            updateProductRequestDto.getType() : product.getType())
        .stock(updateProductRequestDto.getStock() != null ?
            updateProductRequestDto.getStock() : product.getStock())
        .price(updateProductRequestDto.getPrice() != null ?
            updateProductRequestDto.getPrice() : product.getPrice())
        .build();

    Product savedProduct = productRepository.save(product);

    return ProductResponseDto.builder()
        .id(savedProduct.getId())
        .name(savedProduct.getName())
        .type(savedProduct.getType())
        .stock(savedProduct.getStock())
        .price(savedProduct.getPrice())
        .build();
  }

  @Override
  public void delete(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    productRepository.deleteById(product.getId());

    if (productRepository.existsById(product.getId())) {
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something when wrong");
    }
  }
}

package com.wti.ordersystem.controllers;

import com.wti.ordersystem.models.requests.CreateProductRequestDto;
import com.wti.ordersystem.models.requests.UpdateProductRequestDto;
import com.wti.ordersystem.models.responses.PageResponseWrapper;
import com.wti.ordersystem.models.responses.ProductResponseDto;
import com.wti.ordersystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
    ProductResponseDto productResponseDto = productService.create(createProductRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
  }

  @GetMapping
  public ResponseEntity<PageResponseWrapper<ProductResponseDto>> getProducts(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<ProductResponseDto> productsResponseDto = productService.getAll(page, size);
    PageResponseWrapper<ProductResponseDto> responseWrapper = new PageResponseWrapper<>(productsResponseDto);

    return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
    ProductResponseDto productResponseDto = productService.get(id);
    return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                          @RequestBody UpdateProductRequestDto updateProductRequestDto) {
    ProductResponseDto updatedProduct = productService.update(id, updateProductRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

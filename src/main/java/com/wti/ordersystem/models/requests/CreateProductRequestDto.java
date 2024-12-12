package com.wti.ordersystem.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {
  @NotBlank
  @Size(min = 1, max = 100, message = "Product name must be at least 1 character and a maximum of 100 characters.")
  private String name;

  private String type;

  @NotBlank
  private Integer stock;

  @NotBlank
  @Size(min = 1, message = "Minimum price 1.")
  private Double price;
}

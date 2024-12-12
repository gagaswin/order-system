package com.wti.ordersystem.config;

import com.wti.ordersystem.models.entity.Product;
import com.wti.ordersystem.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDataInitializer {
  @Bean
  public CommandLineRunner initData(ProductRepository productRepository) {
    return args -> {
      if (productRepository.count() == 0) {
        productRepository.save(Product.builder().name("Product 1").type("Type 1").stock(10).price(100.0).build());
        productRepository.save(Product.builder().name("Product 2").type("Type 2").stock(15).price(200.0).build());
        productRepository.save(Product.builder().name("Product 3").type("Type 3").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 4").type("Type 4").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 5").type("Type 1").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 6").type("Type 1").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 7").type("Type 2").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 8").type("Type 2").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 9").type("Type 3").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 10").type("Type 3").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 11").type("Type 4").stock(5).price(50.0).build());
        productRepository.save(Product.builder().name("Product 12").type("Type 4").stock(5).price(50.0).build());
        System.out.println("Default products initialized.");
      } else {
        System.out.println("Products already exist in the database.");
      }
    };
  }
}

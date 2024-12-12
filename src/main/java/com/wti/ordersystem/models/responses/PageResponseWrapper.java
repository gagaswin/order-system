package com.wti.ordersystem.models.responses;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponseWrapper<T> {
  private List<T> data;
  private Long totalElements;
  private Integer totalPages;
  private Integer page;
  private Integer size;

  public PageResponseWrapper(Page<T> page) {
    this.data = page.getContent();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.page = page.getNumber();
    this.size = page.getSize();
  }
}

package com.douzon.blooming.product.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductDto {

  private Long productNo;
  private String productCode;
  private String productName;
  private String standard;
  private int unit;
  private int weight;
  private int price;
}
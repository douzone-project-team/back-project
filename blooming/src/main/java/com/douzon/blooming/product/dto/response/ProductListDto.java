package com.douzon.blooming.product.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductListDto {

  private Long productNo;
  private String productCode;
  private String productName;
  private int unit;
  private int price;
}

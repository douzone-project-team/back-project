package com.douzon.blooming.main.dto.request;

import java.io.Serializable;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MainPageDataDto implements Serializable {

  private CurrentSituation instruction;
  private CurrentSituation delivery;
  private List<ExpirationDateNearInstruction> expirationDateNearInstruction;
  private List<MainPageCustomerDto> customer;

}

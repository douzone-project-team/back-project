package com.douzon.blooming.delivery.dto.response;

import com.douzon.blooming.delivery.dto.DeliveryStatus;
import com.douzon.blooming.delivery_instruction.dto.response.DeliveryListInstructionDto;
import com.douzon.blooming.instruction.dto.response.ListInstructionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDeliveryDto {
    private String deliveryNo;
    private String employeeName;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;
    @Setter
    List<DeliveryListInstructionDto> instructions;
}
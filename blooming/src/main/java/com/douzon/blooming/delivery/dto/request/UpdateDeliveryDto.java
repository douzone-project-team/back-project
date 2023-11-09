package com.douzon.blooming.delivery.dto.request;

import com.douzon.blooming.delivery_instruction.dto.request.UpdateInstructionProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateDeliveryDto {
    private List<UpdateInstructionProductDto> instructions;
    private String deliveryDate;
}
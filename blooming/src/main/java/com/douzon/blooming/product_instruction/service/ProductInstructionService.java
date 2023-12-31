package com.douzon.blooming.product_instruction.service;

import com.douzon.blooming.product_instruction.dto.request.AddProductInstructionDto;
import com.douzon.blooming.product_instruction.dto.request.UpdateProductInstructionDto;
import com.douzon.blooming.product_instruction.dto.response.ResponseProductRemainAmountDto;

public interface ProductInstructionService {

  void addProductInstruction(AddProductInstructionDto dto, String instructionNo);

  void updateProductInstruction(UpdateProductInstructionDto dto, Long productNo,
      String instructionNo);

  void deleteProductInstruction(Long productNo, String instructionNo);

  ResponseProductRemainAmountDto getRemainAmount(String instructionNo, Integer productNo);
}

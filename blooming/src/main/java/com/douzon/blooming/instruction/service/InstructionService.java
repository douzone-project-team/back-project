package com.douzon.blooming.instruction.service;

import com.douzon.blooming.PageDto;
import com.douzon.blooming.instruction.dto.request.InstructionSearchDto;
import com.douzon.blooming.instruction.dto.request.RequestInstructionDto;
import com.douzon.blooming.instruction.dto.request.UpdateInstructionDto;
import com.douzon.blooming.instruction.dto.response.ListInstructionDto;
import com.douzon.blooming.instruction.dto.response.ResponseInstructionDto;
import com.douzon.blooming.instruction.dto.response.ResponseMyInstructionDto;
import com.douzon.blooming.instruction.dto.response.ResponseMyInstructionListDto;

public interface InstructionService {

  PageDto<ListInstructionDto> findInstructions(InstructionSearchDto searchDto);

  ResponseInstructionDto findInstruction(String instructionNo);

  String addInstruction(RequestInstructionDto dto);

  void updateInstruction(UpdateInstructionDto dto, String instructionNo);

  void deleteInstruction(String instructionNo);

  ResponseMyInstructionListDto findMyInstruction();
}

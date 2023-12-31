package com.douzon.blooming.log.instruction.dto;

import com.douzon.blooming.log.LogType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InstructionLogDto {

  private String ipAddress;
  private Long modifierNo;
  private String instructionNo;
  private LogType type;
}

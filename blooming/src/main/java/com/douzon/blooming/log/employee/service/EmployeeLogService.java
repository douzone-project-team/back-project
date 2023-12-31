package com.douzon.blooming.log.employee.service;

import com.douzon.blooming.log.LogService;
import com.douzon.blooming.log.employee.dto.EmployeeLogDto;
import com.douzon.blooming.log.employee.repo.EmployeeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeLogService implements LogService<EmployeeLogDto> {

  private final EmployeeLogRepository employeeLogRepository;

  @Override
  public void addLog(EmployeeLogDto employeeLogDto) {
    employeeLogRepository.insertEmployeeLogByEmployeeLogDto(employeeLogDto);
  }
}

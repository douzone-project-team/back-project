package com.douzon.blooming.auth.service;

import com.douzon.blooming.auth.EmployeeDetails;
import com.douzon.blooming.employee.dto.response.EmployeeDto;
import com.douzon.blooming.employee.exception.NotFoundEmployeeException;
import com.douzon.blooming.employee.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return employeeRepository.findEmployeeById(username)
        .map(this::createEmployeeDetails)
        .orElseThrow(NotFoundEmployeeException::new);
  }

  private EmployeeDetails createEmployeeDetails(EmployeeDto employeeDto) {
    return new EmployeeDetails(
        employeeDto.getEmployeeNo(),
        employeeDto.getId(),
        employeeDto.getName(),
        employeeDto.getPassword(),
        employeeDto.getRole());
  }
}
package com.douzon.blooming.auth.controller;

import com.douzon.blooming.auth.dto.request.IdCheckDto;
import com.douzon.blooming.auth.dto.request.NoCheckDto;
import com.douzon.blooming.auth.dto.response.ResponseCheckDto;
import com.douzon.blooming.employee.dto.request.AuthUpdateEmployeeDto;
import com.douzon.blooming.employee.dto.request.InsertEmployeeDto;
import com.douzon.blooming.employee.dto.request.UpdateEmployeeDto;
import com.douzon.blooming.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/employees")
@RequiredArgsConstructor
public class EmployeeAuthController {

  private final EmployeeService employeeService;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody InsertEmployeeDto insertEmployeeDto) {
    employeeService.signup(insertEmployeeDto);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{employeeNo}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeNo) {
    employeeService.removeEmployee(employeeNo);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{employeeNo}")
  public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeNo,
      @RequestBody AuthUpdateEmployeeDto authUpdateEmployeeDto) {
    employeeService.updateEmployee(authUpdateEmployeeDto, employeeNo);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/no/check")
  public ResponseEntity<ResponseCheckDto> noCheck(@ModelAttribute NoCheckDto noCheckDto) {
    return ResponseEntity.ok(
        new ResponseCheckDto(!employeeService.employeeNoCheck(noCheckDto.getEmployeeNo())));
  }

  @GetMapping("/id/check")
  public ResponseEntity<ResponseCheckDto> idCheck(@ModelAttribute IdCheckDto idCheckDto) {
    return ResponseEntity.ok(new ResponseCheckDto(!employeeService.idCheck(idCheckDto.getId())));
  }
}

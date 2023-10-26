package com.douzon.blooming.employee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployeeDto {
    private Long employeeNo;
    private String id;
    private String password;
    private String name;
    private String img;
    private String tel;
    private String email;
}
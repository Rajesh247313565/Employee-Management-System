package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    String createEmployee(EmployeeDto employeeDto); // why string means return string value
    EmployeeDto getEmployeeById(Long employeeId); // why class name return all this fileds
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
    void deleteEmployee(Long employeeId);




}

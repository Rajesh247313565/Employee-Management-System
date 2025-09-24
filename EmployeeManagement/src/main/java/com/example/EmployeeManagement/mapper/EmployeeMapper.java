package com.example.EmployeeManagement.mapper;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.entity.Employee;
import lombok.Data;

@Data
public class EmployeeMapper {
    // Converts an Employee object to an EmployeeDto.
    // Its used when fetching data from database to frontend
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }
    // Converts an EmployeeDto back to an Employee.
    // used for frontend to sql or backend
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}

/*
Entity ➡️ DTO: Employee → EmployeeDto

DTO ➡️ Entity: EmployeeDto → Employee

This is useful when you want to:

Separate database objects (Entity) from what you expose via REST APIs (DTO).

Improve security, performance, and decoupling.


 */

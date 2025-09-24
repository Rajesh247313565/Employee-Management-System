package com.example.EmployeeManagement.service.impl;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.exception.ResourceNotFoundException;
import com.example.EmployeeManagement.mapper.EmployeeMapper;
import com.example.EmployeeManagement.repository.EmployeeRepo;
import com.example.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public String createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> existingEmployee = employeeRepo.findByEmail(employeeDto.getEmail());

        if (existingEmployee.isPresent()){
            return "Email already exists please please login with " + employeeDto.getEmail();
        }
        else {
            Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
            Employee savedEmployee = employeeRepo.save(employee);
            return "Successfully created account and user id is "+ employee.getId();
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not found with given id " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepo.findAll();
       return employees.stream().map((employee) ->EmployeeMapper.mapToEmployeeDto(employee))
               .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Employee is not exists given id " + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updateObj = employeeRepo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not found with given id " + employeeId));
        employeeRepo.deleteById(employeeId);
    }

    /*
    * public Empleoyee createEmployee(Employee employee){
    * return employee.save(employe);
    * Optional<T> is a container object which may or may not contain a non-null value. It is used to avoid null pointer exceptions (NPE) and make your code more readable and intentional when dealing with values that might be absent.
     * */

}

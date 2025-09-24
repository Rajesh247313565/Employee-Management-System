package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// once we mention this annotation then this class becomes the spring mvc controller and this is capable to handle http request
@RestController
// the base url for all the rest api that we are going to build within this controller
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeController {

    @Autowired
    private EmployeeService employeeService;
    // Add Employee Rest api
    // first build a method then add to this rest api methods so it will become the rest api method
    // @RequestBody its accepts the frontend json data and to pass to entity(EmployeDto)

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto){
       String result = employeeService.createEmployee(employeeDto);
       if (result.equals("Email already exists")){
           return new ResponseEntity<>(result,HttpStatus.CONFLICT);
       }
       return new ResponseEntity<>(result,HttpStatus.CREATED);
    }
    // The url template id is bind with the method arguments
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtos);
    }
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee delete successfully");
    }
}

package com.example.demo.employee;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployee(){
    return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Long employeeId){
    return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void addEmployee(@Valid @RequestBody Employee employee){
    employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "{empId}")
    public void deleteEmployeeById(@PathVariable("empId") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping(path = "{empId}")
    public void updateEmployeeById(@PathVariable("empId") Long employeeId,  @RequestBody Employee employee){
        employeeService.updateEmployeeById(employeeId, employee);
    }
}

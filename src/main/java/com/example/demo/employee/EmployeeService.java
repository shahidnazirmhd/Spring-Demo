package com.example.demo.employee;

import com.example.demo.employee.exception.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException( "Student with id " + empId + " does not exists"));
    }

    public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
    }
}

package com.example.demo.employee;

import com.example.demo.employee.exception.EmployeeNotFoundException;
import com.example.demo.employee.exception.NoChangeException;
import com.example.demo.employee.exception.ResourceAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + empId + " does not exists"));
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long empId) {
        employeeRepository.findById(empId).ifPresentOrElse(r -> employeeRepository.deleteById(r.getId()),
                () -> {
                    throw new EmployeeNotFoundException("Employee with id " + empId + " does not exists");
                });
    }

    public void updateEmployeeById(Long empId, Employee emp) {
        Employee employee = getEmployeeById(empId);
        Optional.ofNullable(emp.getEmail())
                .filter(email -> !email.equals(employee.getEmail()))
                .ifPresentOrElse(email -> {
                            employeeRepository.selectExistsEmail(empId, email).ifPresent(r -> {
                                throw new ResourceAlreadyExistException("Email: " + r.getEmail() + " | Already taken");
                            });
                            employee.setEmail(email);
                            employeeRepository.save(employee);
                        },
                        () -> {
                            throw new NoChangeException("Attempting to update with same value");
                        });

        Optional.ofNullable(emp.getMobileNo())
                .filter(mobile -> !mobile.equals(employee.getMobileNo()))
                .ifPresentOrElse(mobile -> {
                            employeeRepository.selectExistsMobileNo(empId, mobile).ifPresent(r -> {
                                throw new ResourceAlreadyExistException("Email: " + r.getMobileNo() + " | Already taken");
                            });
                            employee.setMobileNo(mobile);
                            employeeRepository.save(employee);
                        },
                        () -> {
                            throw new NoChangeException("Attempting to update with same value");
                        });
    }
}

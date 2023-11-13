package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.id <> ?1 AND e.email = ?2")
    Optional<Employee> selectExistsEmail(Long id, String email);

    @Query("UPDATE Employee e SET e.email = ?2 WHERE e.id = ?1")
    void updateEmployeeEmail(Long id, String email);
}
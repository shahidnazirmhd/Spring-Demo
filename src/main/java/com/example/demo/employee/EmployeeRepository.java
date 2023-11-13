package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.id <> ?1 AND e.email = ?2")
    Optional<Employee> selectExistsEmail(Long id, String email);

    @Query("SELECT e FROM Employee e WHERE e.id <> ?1 AND e.mobileNo = ?2")
    Optional<Employee> selectExistsMobileNo(Long id, Long mobileNo);
}
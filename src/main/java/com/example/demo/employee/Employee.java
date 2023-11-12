package com.example.demo.employee;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "Employee")
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "employee_email_unique", columnNames = "email")
})
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @NotBlank
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "mobile_no", nullable = false)
    private Long mobileNo;
    @Column(name = "age", nullable = false)
    private Integer age;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "gender")
    private Gender gender;

    @OneToOne(mappedBy = "employee", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private EmployeeIdCard employeeIdCard;

    public Employee(String firstName, String lastName, String email, Long mobileNo, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setEmployeeIdCard(EmployeeIdCard employeeIdCard) {
        this.employeeIdCard = employeeIdCard;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

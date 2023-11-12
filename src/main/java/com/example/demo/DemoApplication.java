package com.example.demo;

import com.example.demo.employee.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//Initially add employees (Data) to DB
	@Bean
	CommandLineRunner commandLineRunner(
			EmployeeRepository employeeRepository
	) {
		return args -> {
			//initializeData(employeeRepository);
		};
	}

	private static void initializeData(EmployeeRepository employeeRepository) {
		List<Employee> employeeList = new ArrayList<>();
		IntStream.range(0,5).forEach(r->{
			Faker fake = new Faker();
			String fName = fake.name().firstName();
			String lName = fake.name().lastName();
			String email = String.format("%s.%s@example.com", fName, lName);
			Integer age  = fake.number().numberBetween(17, 55);
			Long mobileNo = fake.number().randomNumber(10, true);
			Gender gender = fake.options().option(Gender.class);
			Employee employee = new Employee(fName,lName,email,mobileNo,age, gender);
			employee.setEmployeeIdCard(new EmployeeIdCard(String.valueOf(Year.now()) + String.valueOf(mobileNo), employee));
			employeeList.add(employee);
		});
		employeeRepository.saveAll(employeeList);
	}
}

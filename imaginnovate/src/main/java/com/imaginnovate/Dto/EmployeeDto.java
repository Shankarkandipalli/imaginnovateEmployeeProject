package com.imaginnovate.Dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
	private Long id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> phoneNumbers;
	private LocalDate doj;
	private Double salary;

}

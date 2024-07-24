package com.imaginnovate.Service;

import java.util.List;

import com.imaginnovate.Dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createProduct(EmployeeDto employeeDto);

	EmployeeDto EmployeeGetById(Long EmployeeId);

	List<EmployeeDto> getAllEmployees();

	double calculateYearlySalary(EmployeeDto employeeDto);

	public double calculateTax(double yearlySalary);

	public double calculateCess(double yearlySalary);

}

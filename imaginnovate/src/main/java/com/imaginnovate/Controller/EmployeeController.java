package com.imaginnovate.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.Dto.EmployeeDto;
import com.imaginnovate.Service.EmployeeServiceImpl;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping
	public ResponseEntity<EmployeeDto> createProduct(@Valid @RequestBody EmployeeDto employeeDto) {
		EmployeeDto createProduct = employeeServiceImpl.createProduct(employeeDto);
		return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> productGetById(@PathVariable("id") Long EmployeeId) {
		EmployeeDto productDtos = employeeServiceImpl.EmployeeGetById(EmployeeId);
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> getallProduct = employeeServiceImpl.getAllEmployees();
		return new ResponseEntity<>(getallProduct, HttpStatus.OK);

	}

	@GetMapping("/{id}/tax-deduction")
	public ResponseEntity<Double> getEmployeeTaxDeduction(@PathVariable Long EmployeeId) {
		EmployeeDto employee = employeeServiceImpl.EmployeeGetById(EmployeeId);
		double yearlySalary = employeeServiceImpl.calculateYearlySalary(employee);
		double tax = employeeServiceImpl.calculateTax(yearlySalary);
		double cess = employeeServiceImpl.calculateCess(yearlySalary);
		return ResponseEntity.ok(cess);

	}
}

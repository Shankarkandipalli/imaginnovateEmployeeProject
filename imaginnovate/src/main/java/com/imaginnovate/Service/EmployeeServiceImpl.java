package com.imaginnovate.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.imaginnovate.Dto.EmployeeDto;
import com.imaginnovate.Exception.EmployeeNotFoundException;
import com.imaginnovate.Repository.EmployeeRepository;
import com.imaginnovate.entity.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

	@Override
	public EmployeeDto createProduct(EmployeeDto employeeDto) {
		Employee savedEmployee = modelMapper.map(employeeDto, Employee.class);
		Employee saveEmployees = employeeRepository.save(savedEmployee);
		EmployeeDto productDtosave = modelMapper.map(saveEmployees, EmployeeDto.class);
		return productDtosave;
	}

	@Override
	public EmployeeDto EmployeeGetById(Long EmployeeId) {

		Employee producs = employeeRepository.findById(EmployeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Id is Not found :" + EmployeeId));
		return modelMapper.map(producs, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> getAllProducts = employeeRepository.findAll();
		return getAllProducts.stream().map((pro) -> modelMapper.map(pro, EmployeeDto.class))
				.collect(Collectors.toList());
		
	}
	 public double calculateYearlySalary(EmployeeDto EmployeeDto) {
	        LocalDate startOfYear = LocalDate.of(LocalDate.now().getYear(), 4, 1);
	        LocalDate endOfYear = LocalDate.of(LocalDate.now().getYear() + 1, 3, 31);

	        long monthsWorked = ChronoUnit.MONTHS.between(EmployeeDto.getDoj(), endOfYear);
	        double totalSalary = monthsWorked * EmployeeDto.getSalary();

	        return totalSalary;
	    }

	    public double calculateTax(double yearlySalary) {
	        double tax = 0.0;

	        if (yearlySalary <= 250000) {
	            tax = 0.0;
	        } else if (yearlySalary <= 500000) {
	            tax = (yearlySalary - 250000) * 0.05;
	        } else if (yearlySalary <= 1000000) {
	            tax = 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
	        } else {
	            tax = 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
	        }

	        return tax;
	    }

	    public double calculateCess(double yearlySalary) {
	        if (yearlySalary > 2500000) {
	            return (yearlySalary - 2500000) * 0.02;
	        }
	        return 0.0;
	    }

}

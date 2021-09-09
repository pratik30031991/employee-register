package com.thymeleaf.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.demo.model.Employee;
import com.thymeleaf.demo.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmployee() {
	 
		return empRepo.findAll();
		
	}

	@Override
	public void addEmp(Employee em) {
		
		this.empRepo.save(em);
		System.out.println("New emp details saved");
		
	}

	@Override
	public Employee getEmpById(int id) {
		
		Optional<Employee> optional = empRepo.findById(id);
		
		Employee emp = null;
		
		if(optional.isPresent()) {
			 emp = optional.get();
		}else {
			throw new RuntimeException("Employee Not found for id : "+id);
		}
		
		return emp;
	}

	@Override
	public void deleteEmpById(int id) {
		
		this.empRepo.deleteById(id);
		
	}
	
	
	
	
}

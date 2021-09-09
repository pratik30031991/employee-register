package com.thymeleaf.demo.service;

import java.util.List;

import com.thymeleaf.demo.model.Employee;

public interface EmployeeService {
	
 List<Employee> getAllEmployee();
	
 public void addEmp(Employee em);
 
 public Employee getEmpById(int id);

 public void deleteEmpById(int id);

}

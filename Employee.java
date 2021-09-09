package com.thymeleaf.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Emp_Details_Table")
public class Employee {
	
	@Id
	private int empId;
	
	@Column
	private String empName;
	
	@Column
	private String empDept;
	
	@Column
	private String empEmail;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empId, String empName, String empDept, String empEmail) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empEmail = empEmail;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empDept=" + empDept + ", empEmail=" + empEmail
				+ "]";
	}
	
	
	
	
	
	

}

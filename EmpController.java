package com.thymeleaf.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thymeleaf.demo.model.Employee;
import com.thymeleaf.demo.service.EmployeeService;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmpController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		model.addAttribute("EmployeeList", empService.getAllEmployee());
		
		return "index";
		
	}
	
	@GetMapping("/newEmpForm")
	public String showNewEmpFormPage(Model model) {
		
		Employee emd = new Employee();
		
		model.addAttribute("empData",emd);
		
		return "empFormPage";
		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("emd") Employee emp) {
			
		empService.addEmp(emp);
		
		return "redirect:/";
			
	}
	
	@GetMapping("showUpdateForm/{id}")
	public String ShowUpdateForm(@PathVariable(value="id") int id, Model model ) {
		
		System.out.println("inside showUpdateForm method....");
		Employee emd = empService.getEmpById(id);
//		this is to get the emp of this perticular id and save it in an obj
		
		model.addAttribute("empData",emd);
//		this is where we pass this employee obj data to updateEmp.html page
		
		
		return "updateEmp";
		
	}
	
	
	@GetMapping("deleteEmp/{id}")
	public String DeleteEmployee(@PathVariable(value="id") int id, Model model ) {
		
		
		this.empService.deleteEmpById(id);
		
		
		return "redirect:/";
		
	}
	
	
	

}

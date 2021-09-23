package com.thymeleaf.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.thymeleaf.demo.helper.FileUploadHelper;
import com.thymeleaf.demo.model.Employee;
import com.thymeleaf.demo.service.EmployeeService;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmpController {
	
//	String url; String user; String password; 
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@GetMapping("/")
	public String viewLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/index")
	public String viewIndexPage(Model model) {
		
		model.addAttribute("EmployeeList", empService.getAllEmployee()); //here we send the data from DB to index.html page 
		
		return "index";
		
	}

	@GetMapping("/newEmpForm")
	public String showNewEmpFormPage(Model model) {
		
		Employee emd = new Employee();
		
		model.addAttribute("empData",emd); //sending empty object to empFormPage.html
		
		return "empFormPage";
		
	}
//	@GetMapping("/openUploadform")
//	public String openImageUploadForm() {
//		
//		return "uploadImage";
//	}
	
//	@PostMapping("/uploadImage")
//	public String uploadEmpImage(Model model,
//            @RequestParam("fileToUpload") MultipartFile multipartFile) throws IOException {
//		
//		String imageName = multipartFile.getOriginalFilename();
//		
//		model.addAttribute("img",imageName);
//		
//		fileUploadHelper.uploadFile(multipartFile);
//		
//		
//		return "empFormPage";
//		
//	} @RequestParam("fileToUpload") MultipartFile multipartFile

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("emd") Employee emp)  throws IOException, SQLException{
		
		File file = new File("D:\\STS work\\thymeleafDemo\\src\\main\\resources\\empData.json");
		
		JSONObject jsonObj = new JSONObject(); //created a json objc to add values
		
		jsonObj.put("empId", emp.getEmpId());
		jsonObj.put("empName", emp.getEmpName());
		jsonObj.put("empDept", emp.getEmpDept());
		jsonObj.put("empEmail", emp.getEmpEmail());
		
		
		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(jsonObj.toString());
		fileWriter.flush();
		fileWriter.close();
		
		System.out.println("Data successfully added into the empData.json file");
		
		empService.addEmp(emp);
		
		
		return "redirect:/index";
		
//		String url = "jdbc:postgresql://localhost:5432/pratik_DB";
//	    String user = "postgres";
//	    String password = "tiger";
//	    Connection con =null;
//	    
//	    try {
//	    con = DriverManager.getConnection(url, user, password);
//	    }catch(SQLException e) {System.out.println(e);}
//	    
//	    String imageName = multipartFile.getOriginalFilename();
//		
//		File imageFile = new File(imageName);
//		
//		FileInputStream fis = new FileInputStream(imageFile);
//		
//		PreparedStatement pst = con.prepareStatement("insert into new_emp_table values (?,?,?,?,?)");
//		pst.setInt(1, emp.getEmpId());
//		pst.setString(2, emp.getEmpName() );
//		pst.setString(3, emp.getEmpEmail());
//		pst.setString(4, emp.getEmpDept());
//		pst.setBinaryStream(5, fis);
//		
//		int rc = pst.executeUpdate();
//		
//		if(rc==0) {System.out.println("Record Not Inserted");} else {System.out.println("Record Inserted successfully");}
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
	public String DeleteEmployee(@PathVariable(value="id") int id, Model model) {
		
		
		this.empService.deleteEmpById(id);
		
		
		return "redirect:/index";
		
	}
	
	
	

}

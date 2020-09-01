package com.vipin.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vipin.pma.dao.EmployeeRepository;
import com.vipin.pma.dao.ProjectRepository;
import com.vipin.pma.dto.ChartData;
import com.vipin.pma.dto.EmployeeProject;
import com.vipin.pma.entities.Project;
import com.vipin.pma.springexample.Car;

@Controller
public class HomeController {
	
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	Car car;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired                         // field injection
	EmployeeRepository empRepo;
	


	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
      Map<String, Object> map = new HashMap<String, Object>();
		//we are querying the database for projects
	  List<Project> projects = proRepo.findAll();
	  model.addAttribute("projectsList", projects);
	  
	  List<ChartData> projectData = proRepo.getProjectStatus();
	  
	  //Lets convert projectData object into json structure for use in javascript
	  ObjectMapper objectMapper = new ObjectMapper();
	  String jsonString =  objectMapper.writeValueAsString(projectData);
	 //{["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 3]}
	  
	  model.addAttribute("projectStatusCnt",jsonString);
      
	  
	  
	  //we are querying the database for employees
      List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
	  model.addAttribute("employeesProjectCnt", employeesProjectCnt);
	  
	  return "main/home";
		
	}

}




//public HomeController(EmployeeRepository empRepo) {           // constructor injection we don't need to do @Autowired
//	super();
//	this.empRepo = empRepo;
//}
//
//@Autowired
//public void setEmpRepo(EmployeeRepository empRepo) {         // setter injection we need to  provide @Autowired!
//	this.empRepo = empRepo;
//}



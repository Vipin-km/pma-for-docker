package com.vipin.pma.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Qualifier("staffRepositoryImp1")   //field injection
	iStaffRepository iStaffRepo;

	
	@Autowired                         //setter injection 
	@Qualifier("staffRepositoryImp1")
	public void setiStaffRepo(iStaffRepository iStaffRepo) {
		this.iStaffRepo = iStaffRepo;
	}
	
	

//	public EmployeeService(@Qualifier("staffRepositoryImp1")  iStaffRepository iStaffRepo) { //constructor injection
//		super();
//		this.iStaffRepo = iStaffRepo;
//		
//		
//	}
//	
	
	
	
	
	

}

package com.travel.travtronics.request;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Employee;

public class EmpContactRequest {
	
	private CustomerContactNew contactModel;
	private Employee empModel;
	private Long empId;
	
	
	
	public EmpContactRequest(CustomerContactNew contactModel, Employee empModel, Long empId) {
		this.contactModel = contactModel;
		this.empModel = empModel;
		this.empId = empId;
	}
	
	public CustomerContactNew getContactModel() {
		return contactModel;
	}
	public void setContactModel(CustomerContactNew contactModel) {
		this.contactModel = contactModel;
	}
	public Employee getEmpModel() {
		return empModel;
	}
	public void setEmpModel(Employee empModel) {
		this.empModel = empModel;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	
}

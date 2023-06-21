package com.travel.travtronics.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Employee;

@JsonInclude(Include.NON_NULL)
public class EmpContactResponse {

	private CustomerContactNew contactModel;
	private Employee empModel;
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
}

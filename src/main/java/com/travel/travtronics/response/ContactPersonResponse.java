package com.travel.travtronics.response;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.PersonModel;

public class ContactPersonResponse {
	
	private PersonModel personInfo;
	private CustomerContactNew contactInfo;
	public PersonModel getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonModel personInfo) {
		this.personInfo = personInfo;
	}
	public CustomerContactNew getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(CustomerContactNew contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	

}

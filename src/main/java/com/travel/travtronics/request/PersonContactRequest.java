package com.travel.travtronics.request;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.PersonModel;


public class PersonContactRequest {

	private CustomerContactNew contactInfo;
	private PersonModel personInfo;
	private Long personId;

	public CustomerContactNew getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(CustomerContactNew contactInfo) {
		this.contactInfo = contactInfo;
	}

	public PersonModel getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonModel personInfo) {
		this.personInfo = personInfo;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}

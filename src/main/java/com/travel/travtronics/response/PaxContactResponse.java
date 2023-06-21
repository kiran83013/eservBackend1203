package com.travel.travtronics.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Pax;


@JsonInclude(Include.NON_NULL)
public class PaxContactResponse {
	private CustomerContactNew contactModel;
	private Pax paxModel;
	public CustomerContactNew getContactModel() {
		return contactModel;
	}
	public void setContactModel(CustomerContactNew contactModel) {
		this.contactModel = contactModel;
	}
	public Pax getPaxModel() {
		return paxModel;
	}
	public void setPaxModel(Pax paxModel) {
		this.paxModel = paxModel;
	}
	
	
}

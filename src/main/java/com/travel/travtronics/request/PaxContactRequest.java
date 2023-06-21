package com.travel.travtronics.request;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Pax;

public class PaxContactRequest {

	
	private CustomerContactNew contactModel;
	private Pax paxModel;

	private Long paxId;

	public PaxContactRequest(CustomerContactNew contactModel, Pax paxModel, Long paxId) {
		super();
		this.contactModel = contactModel;
		this.paxModel = paxModel;
		this.paxId = paxId;
	}

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

	public Long getPaxId() {
		return paxId;
	}

	public void setPaxId(Long paxId) {
		this.paxId = paxId;
	}

}

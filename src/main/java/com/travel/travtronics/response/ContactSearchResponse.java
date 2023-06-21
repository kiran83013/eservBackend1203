package com.travel.travtronics.response;

import com.travel.travtronics.eserv.model.CustomerContactNew;

public class ContactSearchResponse extends CustomerContactNew {

	private Integer buId;

	private String buName;

	private Integer ccId;

	private String ccName;

	private Integer locId;

	private String locName;

	private String orgName;

	private Integer orgId;

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}

package com.travel.travtronics.eserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.travel.travtronics.enums.StatusActive;

//@Data
@Entity
@Table(name = " transition",uniqueConstraints = { @UniqueConstraint(columnNames = "Organization", name = "organizationkey"),
		@UniqueConstraint(columnNames = "ModuleName", name = "moduleName"),@UniqueConstraint(columnNames = "FromStatus", name = "fromStatus"),
		@UniqueConstraint(columnNames = "ToStatus", name = "toStatus") })
public class Transition extends WhoColumnsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransitionId")
	private Long transitionId;

	@Column(name = "Organization")
	private Long organization;

	@Column(name = "DeafultStatus")
	private String deafultStatus;

	@Column(name = "FromStatus")
	private Integer fromStatus;

	@Column(name = "ModuleName")
	private String moduleName;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusActive status;

	@Column(name = "ToStatus")
	private Integer toStatus;

	public Long getTransitionId() {
		return transitionId;
	}

	public void setTransitionId(Long transitionId) {
		this.transitionId = transitionId;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public Integer getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(Integer fromStatus) {
		this.fromStatus = fromStatus;
	}

	public Integer getToStatus() {
		return toStatus;
	}

	public void setToStatus(Integer toStatus) {
		this.toStatus = toStatus;
	}

	public String getDeafultStatus() {
		return deafultStatus;
	}

	public void setDeafultStatus(String deafultStatus) {
		this.deafultStatus = deafultStatus;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public StatusActive getStatus() {
		return status;
	}

	public void setStatus(StatusActive status) {
		this.status = status;
	}

}

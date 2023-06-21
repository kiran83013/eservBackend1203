package com.travel.travtronics.eserv.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.converter.StringConverter;
import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "lead_interested_properties")
public class LeadInterestedProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "UnitId")
	private Long unitId;
	
	@Column(name = "BusinessId")
	private Long businessId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Convert(converter = StringConverter.class)
	@Column(name = "Images")
	private List<String> images;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name = "CreatedBy", updatable = false)
	private Long createdBy;
	
	@CreationTimestamp
	@Column(name = "CreatedDate", updatable = false)
	private Date createdDate;
	
	@Column(name = "UpdatedBy")
	private Long updatedBy;
	
	@UpdateTimestamp
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	@Column(name = "CreatedChannel", updatable = false)
	private String createdChannel;
	
	@Column(name = "UpdatedChannel")
	private String updatedChannel;
}

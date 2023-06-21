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

import com.travel.travtronics.converter.StringConverter;
import com.travel.travtronics.util.EnumStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "master_localization")
public class Localization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Long id;
	
	@Column(name = "OrganizationId")
	private Long organizationId;
	
	@Column(name = "RoleType")
	private String roleType;
	
	@Column(name = "Language")
	private String language;
	
	@Convert(converter = StringConverter.class)
	@Column(name = "Role")
	private List<String> role;
	
//	@Column(name = "Status")
//	private com.travel.travtronics.enums.Status status;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	@Column(name = "KeyTrnsl")
	private String keyTrnsl;
	
	@Column(name = "ValueTrnsl")
	private String valueTrnsl;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "CreatedDate")
	private Date createdDate;
	
	@Column(name = "UpdatedBy")
	private String updatedBy;
	
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
}

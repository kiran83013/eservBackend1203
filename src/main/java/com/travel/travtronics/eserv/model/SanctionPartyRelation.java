package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "sanction_party_relation")
public class SanctionPartyRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "orgid")
	@NotNull(message = "OrgId is required")
	@Min(value = 1, message = "OrgId must be greater than 0")
	private Long orgId;
	
	@Column(name = "sanctionid")
	@NotNull(message = "SanctionId is requried")
	@Min(value = 1, message = "SanctionId must be greater than 0")
	private Long sanctionId;
	
	@Column(name = "bizid")
	@NotNull(message = "BizId is requried")
	@Min(value = 1, message = "BizId must be greater than 0")
	private Long bizId;
	
//	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "created_by")
	@NotNull(message = "CreatedBy is requried")
	@Min(value = 1, message = "CreatedBy must be greater than 0")
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "valid_from_date")
	private Date validFromDate;
	
	@Column(name = "valid_to_date")
	private Date validToDate;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
	
}

package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "sanction_group")
public class SanctionGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
}

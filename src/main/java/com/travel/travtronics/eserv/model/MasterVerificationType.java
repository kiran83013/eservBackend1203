package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "master_verification_type")
public class MasterVerificationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@Column(name = "description",columnDefinition = "LONGTEXT")
	private String description;
	
	@Column(name = "created_by",updatable = false)
	private Long createdBy;
	
	@CreationTimestamp
	@Column(name = "created_date",updatable = false)
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "Status")
	private Boolean status;

}

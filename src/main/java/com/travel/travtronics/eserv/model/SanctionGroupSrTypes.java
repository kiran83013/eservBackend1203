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
@Table(name = "sanction_group_sr_types")
public class SanctionGroupSrTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sanction_group")
	private Long sanctionGroup;
	
	@Column(name = "sr_type")
	private Long srType;
	
	@Column(name = "is_online")
	private Boolean isOnline;
	
	@Column(name = "is_offline")
	private Boolean isOffline;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "updated_by", updatable = false)
	private Long updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;
}

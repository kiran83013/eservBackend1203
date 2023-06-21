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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sanction_views")
@Getter
@Setter
public class SanctionViewSetUpModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "view_description")
	private String viewDescription;

	@Column(name = "view_name")
	private String viewName;

	@Column(name = "status")
	private String status;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	@Column(name = "updated_by")
	private Long updatedBy;
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Date updatedDate;

}

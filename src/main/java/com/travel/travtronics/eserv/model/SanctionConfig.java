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
@Table(name = "sanction_config")
public class SanctionConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "business_unit")
	private Long businessUnit;
	
	@Column(name = "cost_center")
	private Long costCenter;
	
	@Column(name = "location")
	private Long location;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sanction_group")
	private Long sanctionGroup;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "sanction_image")
	private String sanctionImage;
	
	@Column(name = "sanction_view")
	private String sanction_view;
	
	@Column(name = "sanction_view_value")
	private String sanctionViewValue;
	
	@Column(name = "from_operator")
	private String fromOperator;
	
	@Column(name = "from_value")
	private String fromValue;
	
	@Column(name = "to_operator")
	private String toOperator;
	
	@Column(name = "to_value")
	private String toValue;
	
	@Column(name = "type")
	private String type;
	
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

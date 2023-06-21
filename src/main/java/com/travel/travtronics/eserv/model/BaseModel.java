package com.travel.travtronics.eserv.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {

	@Column(name = "created_by", updatable = false)
	private Integer createdBy;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

}

package com.travel.travtronics.eserv.model;

import com.travel.travtronics.enums.Status;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "status_owner_mapping")
public class StatusOwnerMapping  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "organization")
    private Long organization;

    @Column(name = "module")
    private Long module;

    @Column(name = "default_status")
    private Long defaultStatus;

    @Column(name = "team")
    private Long team;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    
    @Column(name = "created_by", updatable = false)
	private String createdBy;
    
	@Column(name = "updated_by")
	private String updatedBy;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	
	@Column(name = "updated_date")
	private Date updatedDate;

}

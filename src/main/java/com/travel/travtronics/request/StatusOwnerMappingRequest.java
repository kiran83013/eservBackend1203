package com.travel.travtronics.request;

import java.util.Date;

import com.travel.travtronics.enums.Status;

import lombok.Data;

@Data
public class StatusOwnerMappingRequest {
	private Long id;

    private Long organization;

    private Long module;

    private Long defaultStatus;

    private Long team;

    private Date startDate;

    private Date endDate;

    private Status status;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
    
//    private String organizationName;

    private String moduleName;

    private String defaultStatusName;

    private String teamName;

}

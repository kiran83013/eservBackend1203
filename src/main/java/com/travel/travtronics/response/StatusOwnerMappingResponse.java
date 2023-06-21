package com.travel.travtronics.response;

import com.travel.travtronics.enums.Status;
import lombok.Data;
import java.util.Date;

@Data
public class StatusOwnerMappingResponse {

    private Long id;

    private Long organization;

    private Long module;

    private String defaultStatus;

    private String team;

    private Date startDate;

    private Date endDate;

    private Status status;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}

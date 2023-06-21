package com.travel.travtronics.request;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.util.EnumStatus;

import lombok.Data;

@Data
public class LetterTemplateLinesRequest {

	private Long letterLineId;

	private Long letterId;

	private Long organizationId;

	private Long languageId;

	private String htmlEditor;

	private Long createdBy;

	private Date createdDate;

	private Long updatedBy;

	private Date updatedDate;

	private EnumStatus status;
	
	private List<String> images;

}

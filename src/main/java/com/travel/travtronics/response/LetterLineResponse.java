package com.travel.travtronics.response;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterLineResponse {

	private String status;

	private BigInteger createdBy;

	private BigInteger organizationId;

	private Date createdDate;

	private BigInteger updatedBy;

	private BigInteger letterId;

	private BigInteger languageId;

	private Date updatedDate;

	private BigInteger letterLineId;

	private String[] images;

	private String htmlEditor;

	private String organizationName;
	private String letterName;
	private String languageName;

}

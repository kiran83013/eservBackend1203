package com.travel.travtronics.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.travtronics.enums.Status;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface EmpListDto {

	Long getId();

	Long getPrefix();

	String getFirstName();

	String getMiddleName();

	String getLastName();

	Long getOrganizationId();

	Long getBusinessUnitId();

	Long getDepartmentId();

	Long getDesignationId();

	String getDesignationName();

	String getPrimaryEmail();

	Long getPrimaryPhoneNumber();

	String getSecondaryEmail();

	Long getSecondaryPhoneNumber();

	Long getTelephoneNumber();

	Long getNationality();

	Date getDob();

	String getPassport();

	Long getIssuedCountry();

	String getRemarksAndNotes();

	Date getStartDate();

	Date getEndDate();

	String getDpImageUrl();

	Long getCreatedBy();

	Date getCreatedDate();

	Long getUpdatedBy();

	Date getUpdatedDate();

	Status getStatus();

	String getPrefixName();

	String getIssuedCountryName();

	String getNationalityName();

	String getOrganizationName();

	String getDepartmentName();

	String getbusinessUnitName();
	
	Long getPrimaryCcp();
	
	Long getSecondaryCcp();
	


}

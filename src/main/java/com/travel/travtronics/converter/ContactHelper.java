package com.travel.travtronics.converter;

import java.util.Date;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.eserv.model.PersonModel;

public class ContactHelper {

	public static CustomerContactNew ConvertEmpToContact(Employee emp) {

		CustomerContactNew contact = new CustomerContactNew();
		contact.setPrefix(emp.getPrefix());
		contact.setEmpId(emp.getId());
		contact.setCustomerId(emp.getCustomersId());
		contact.setFirstName(emp.getFirstName());
		contact.setMiddleName(emp.getMiddleName());
		contact.setLastName(emp.getLastName());
		contact.setDesignationId(emp.getDesignationId());
		contact.setDesignationName(emp.getDesignationName());
		contact.setPrimaryEmail(emp.getPrimaryEmail());
		contact.setPrimaryPhoneNumber(emp.getPrimaryPhoneNumber());
		contact.setPrimaryCCP(emp.getPrimaryCcp());
		contact.setSecondaryCCP(emp.getSecondaryCcp());
		contact.setSecondaryEmail(emp.getSecondaryEmail());
		contact.setSecondaryPhoneNumber(emp.getSecondaryPhoneNumber());
		contact.setRemarksAndNotes(emp.getRemarksAndNotes());
		contact.setStartDate(emp.getStartDate());
		contact.setEndDate(emp.getEndDate());
		contact.setDob(emp.getDob());
		contact.setPassport(emp.getPassport());
		contact.setNationality(emp.getNationality());
		contact.setIssuedCountry(emp.getIssuedCountry());
		contact.setCreatedBy(Long.valueOf(emp.getCreatedBy()));
		contact.setCreatedDate(emp.getCreatedDate());
		contact.setUpdatedBy(Long.valueOf(emp.getUpdatedBy()));
		contact.setUpdatedDate(emp.getUpdatedDate());
		contact.setDpImageUrl(emp.getDpImageUrl());
		contact.setTelephoneNumber(emp.getTelephoneNumber());
		contact.setStatus(emp.getStatus().equals(com.travel.travtronics.enums.Status.Active));
		return contact;
	}

	public static CustomerContactNew ConvertPersonToContact(PersonModel person) {
		CustomerContactNew contact = new CustomerContactNew();
		contact.setPrefix(person.getPrefix() != null ? person.getPrefix() : 0L);
//contact.setEmpId(person.getId());
		contact.setCustomerId(person.getCustomersId() != null ? person.getCustomersId() : 0L);
		contact.setFirstName(person.getFirstName() != null ? person.getFirstName() : "");
		contact.setMiddleName(person.getMiddleName() != null ? person.getMiddleName() : "");
		contact.setLastName(person.getLastName() != null ? person.getLastName() : "");
		contact.setDesignationId(person.getDesignationId() != null ? person.getDesignationId() : 0L);
		contact.setDesignationName(person.getDesignationName() != null ? person.getDesignationName() : "");
		contact.setPrimaryEmail(person.getPrimaryEmail() != null ? person.getPrimaryEmail() : "");
		contact.setPrimaryPhoneNumber(person.getPrimaryPhoneNumber() != null ? person.getPrimaryPhoneNumber() : 0L);
		contact.setPrimaryCCP(person.getPrimaryCcp() != null ? person.getPrimaryCcp() : 0L);
		contact.setSecondaryCCP(person.getSecondaryCcp() != null ? person.getSecondaryCcp() : 0L);
		contact.setSecondaryEmail(person.getSecondaryEmail() != null ? person.getSecondaryEmail() : "");
		contact.setSecondaryPhoneNumber(
				person.getSecondaryPhoneNumber() != null ? person.getSecondaryPhoneNumber() : 0L);
		contact.setRemarksAndNotes(person.getRemarksAndNotes() != null ? person.getRemarksAndNotes() : "");
		contact.setStartDate(person.getStartDate() != null ? person.getStartDate() : null);
		contact.setEndDate(person.getEndDate() != null ? person.getEndDate() : null);
		contact.setDob(person.getDob() != null ? person.getDob() : null);
		contact.setPassport(person.getPassport() != null ? person.getPassport() : null);
		contact.setNationality(person.getNationality() != null ? person.getNationality() : 0L);
		contact.setIssuedCountry(person.getIssuedCountry() != null ? person.getIssuedCountry() : 0L);
		contact.setCreatedBy(person.getCreatedBy() != null ? Long.valueOf(person.getCreatedBy()) : 0L);
		contact.setCreatedDate(person.getCreatedDate()!=null ? person.getCreatedDate():new Date());
//		contact.setUpdatedBy(Long.valueOf(person.getUpdatedBy()));
//		contact.setUpdatedDate(person.getUpdatedDate());
		contact.setDpImageUrl(person.getDpImageUrl() != null ? person.getDpImageUrl() : null);
		contact.setTelephoneNumber(person.getTelephoneNumber() != null ? person.getTelephoneNumber() : null);
		contact.setStatus(person.getStatus().equals(com.travel.travtronics.enums.Status.Active));
		contact.setPersonId(person.getId());
		contact.setOrganizationId(person.getOrganizationId() != null ? person.getOrganizationId() : 0L);
		return contact;
	}
}

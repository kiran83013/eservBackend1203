package com.travel.travtronics.eserv.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.travel.travtronics.eserv.model.Employee;

@Component
public class EmpFiltersServiceImpl implements EmpFilterRepository {

	@Autowired
	@Qualifier(value = "eservEntityManager")
	EntityManager entityManager;

	@Override
	public List<Employee> empFilters(Long organizationId, Long businessUnitId, Long departmentId, Long telephoneNumber,
			String firstName, String designationName, String primaryEmail, Long nationality, Date dob,
			String lastName) {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT * FROM emp_profile WHERE 1=1\t");
		if (organizationId != null) {
			sb.append("AND organizationId =  :organizationId \t");
		}
		if (businessUnitId != null) {
			sb.append("AND businessUnitId = :businessUnitId \t");
		}
		if (departmentId != null) {
			sb.append("AND departmentId = :departmentId \t");
		}
		if (telephoneNumber != null) {
			sb.append("AND telephoneNumber = :telephoneNumber \t");
		}
		if (firstName != null && !firstName.isBlank()) {
			sb.append("AND firstName = :firstName \t");
		}
		if (lastName != null && !lastName.isBlank()) {
			sb.append("AND lastName = :lastName \t");
		}
		if (designationName != null && !designationName.isBlank()) {
			sb.append("AND designationName = :designationName \t");
		}
		if (primaryEmail != null && !primaryEmail.isBlank()) {
			sb.append("AND primaryEmail = :primaryEmail \t");
		}
		if (nationality != null) {
			sb.append("AND nationality = :nationality \t");
		}
		if (dob != null) {
			sb.append("AND DateOfBirth= :dob \t");
		}
		Query q = entityManager.createNativeQuery(sb.toString(), Employee.class);
		if (organizationId != null) {
			q.setParameter("organizationId", organizationId);
		}
		if (businessUnitId != null) {
			q.setParameter("businessUnitId", businessUnitId);
		}
		if (departmentId != null) {
			q.setParameter("departmentId", departmentId);
		}
		if (telephoneNumber != null) {
			q.setParameter("telephoneNumber", telephoneNumber);
		}
		if (firstName != null && !firstName.isBlank()) {
			q.setParameter("firstName", firstName);
		}
		if (lastName != null && !lastName.isBlank()) {
			q.setParameter("lastName", lastName);
		}
		if (designationName != null && !designationName.isBlank()) {
			q.setParameter("designationName", designationName);
		}
		if (primaryEmail != null && !primaryEmail.isBlank()) {
			q.setParameter("primaryEmail", primaryEmail);
		}
		if (nationality != null) {
			q.setParameter("nationality", nationality);
		}
		if (dob != null) {
			q.setParameter("dob", dob);
		}
		List<Employee> empfil = q.getResultList();
		return empfil;
	}

}

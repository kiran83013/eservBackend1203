package com.travel.travtronics.eserv.repository;

import java.util.Date;
import java.util.List;

import com.travel.travtronics.eserv.model.Employee;

public interface EmpFilterRepository {

	List<Employee> empFilters(Long organizationId, Long businessUnitId, Long departmentId, Long telephoneNumber,
			String firstName, String designationName, String primaryEmail, Long nationality, Date dob, String lastName);

}

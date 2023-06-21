package com.travel.travtronics.eserv.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;

public interface EmployeeCustomPaginationRepository {

	Page<Employee> fetchEmployeePagination(Long organizationId, Long businessUnitId, Long departmentId,
			Long primaryPhoneNumber, String firstName, String designationName, String primaryEmail, Long nationality,
			Date dob, String lastName, Pageable pageable, String sortBy, SortType sortType);

	Page<UnitShiftsSlotAssignmentModel> fetchUnitShiftSlotPagination(Long orgId, Long shiftId, Long slotTemplateId,
			Long unitId, Pageable pageable, String sortBy, SortType sortType);

}

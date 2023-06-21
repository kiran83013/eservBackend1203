package com.travel.travtronics.eserv.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.eserv.model.UnitShiftsSlotAssignmentModel;

@Component
public class EmployeePaginationImpl implements EmployeeCustomPaginationRepository {

	@PersistenceContext
	@Autowired
	EntityManager em;

	@Override
	public Page<Employee> fetchEmployeePagination(Long organizationId, Long businessUnitId, Long departmentId,
			Long primaryPhoneNumber, String firstName, String designationName, String primaryEmail, Long nationality,
			Date dob, String lastName, Pageable pageable, String sortBy, SortType sortType) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> createQuery = builder.createQuery(Employee.class);
		Root<Employee> root = createQuery.from(Employee.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (organizationId != null && organizationId != 0) {
			predicates.add(builder.equal(root.<Long>get("organizationId"), organizationId));
		}
		if (businessUnitId != null && businessUnitId != 0) {
			predicates.add(builder.equal(root.<Long>get("businessUnitId"), businessUnitId));
		}
		if (departmentId != null && departmentId != 0) {
			predicates.add(builder.equal(root.<Long>get("departmentId"), departmentId));
		}
		if (primaryPhoneNumber != null && primaryPhoneNumber != 0) {
			predicates.add(builder.equal(root.<Long>get("primaryPhoneNumber"), primaryPhoneNumber));
		}
		if (firstName != null && !firstName.isBlank()) {

			predicates.add(
					builder.like(builder.lower(root.<String>get("firstName")), "%" + firstName.toLowerCase() + "%"));

		}
		if (designationName != null && !designationName.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("designationName")),
					"%" + designationName.toLowerCase() + "%"));

		}
		if (primaryEmail != null && !primaryEmail.isBlank()) {

			predicates.add(builder.like(builder.lower(root.<String>get("primaryEmail")),
					"%" + primaryEmail.toLowerCase() + "%"));

		}
		if (nationality != null && nationality != 0) {
			predicates.add(builder.equal(root.<Long>get("nationality"), nationality));
		}
		if (dob != null) {

			Expression<Date> function = builder.function("date", Date.class, root.get("dob"));

			predicates.add(builder.equal(function, dob));

		}
		if (lastName != null && !lastName.isBlank()) {

			predicates
					.add(builder.like(builder.lower(root.<String>get("lastName")), "%" + lastName.toLowerCase() + "%"));

		}
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}
		List<Employee> resultList = em.createQuery(createQuery).setFirstResult(Math.toIntExact(pageable.getOffset()))
				.setMaxResults(pageable.getPageSize()).getResultList();

		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);

		Root<Employee> serviceRootCount = countQuery.from(Employee.class);
		countQuery.select(builder.count(serviceRootCount))
				.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		Long count = em.createQuery(countQuery).getSingleResult();
		Page<Employee> finalList = new PageImpl<>(resultList, pageable, count);
		return finalList;
	}

	@Override
	public Page<UnitShiftsSlotAssignmentModel> fetchUnitShiftSlotPagination(Long orgId, Long shiftId,
			Long slotTemplateId, Long unitId,  Pageable pageable, String sortBy, SortType sortType) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UnitShiftsSlotAssignmentModel> createQuery = builder.createQuery(UnitShiftsSlotAssignmentModel.class);
		Root<UnitShiftsSlotAssignmentModel> root = createQuery.from(UnitShiftsSlotAssignmentModel.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (orgId != null && orgId != 0) {
			predicates.add(builder.equal(root.<Long>get("orgId"), orgId));
		}
		if (shiftId != null && shiftId != 0) {
			predicates.add(builder.equal(root.<Long>get("shiftId"), shiftId));
		}
		if (slotTemplateId != null && slotTemplateId != 0) {
			predicates.add(builder.equal(root.<Long>get("slotTemplateId"), slotTemplateId));
		}
		if (unitId != null && unitId != 0) {
			predicates.add(builder.equal(root.<Long>get("unitId"), unitId));
		}
		
		
		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.asc(root.get(sortBy)));
		}
		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
			createQuery.orderBy(builder.desc(root.get(sortBy)));
		}
		List<UnitShiftsSlotAssignmentModel> resultList = em.createQuery(createQuery).setFirstResult(Math.toIntExact(pageable.getOffset()))
				.setMaxResults(pageable.getPageSize()).getResultList();

		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);

		Root<UnitShiftsSlotAssignmentModel> serviceRootCount = countQuery.from(UnitShiftsSlotAssignmentModel.class);
		countQuery.select(builder.count(serviceRootCount))
				.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		Long count = em.createQuery(countQuery).getSingleResult();
		Page<UnitShiftsSlotAssignmentModel> finalList = new PageImpl<>(resultList, pageable, count);
		return finalList;
	}

}

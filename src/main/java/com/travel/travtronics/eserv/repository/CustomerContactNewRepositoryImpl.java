package com.travel.travtronics.eserv.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travel.travtronics.eserv.model.CustomerContactNew;
import com.travel.travtronics.eserv.model.Employee;
import com.travel.travtronics.request.SearchResponseDto;

import antlr.StringUtils;

@Component
public class CustomerContactNewRepositoryImpl implements CustomerContactNewCustom {

	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public List<SearchResponseDto> findAllBySearch(String firstName, String lastName, String primaryEmail,
			Long primaryPhoneNumber, Long customersId) {

		Map<String, Object> params = new HashMap<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT e.ID AS id,\r\n" + "e.BusinessUnitId AS businessUnitId,\r\n"
				+ "bu.Name AS businessUnitName,\r\n" + "e.DepartmentId AS departmentId,\r\n"
				+ "md.Name AS departmentName,\r\n" + "e.CreatedBy AS createdBy,\r\n"
				+ "e.CreatedDate AS createdDate,\r\n" + "e.DesignationId AS designationId,\r\n"
				+ "e.DesignationName AS designationName,\r\n" + "e.DateOfBirth AS dateOfBirth,\r\n"
				+ "e.ImageUrl AS imageUrl,\r\n" + "e.EndDate AS endDate,\r\n" + "e.FirstName AS firstName,\r\n"
				+ "e.IssuedCountry AS issuedCountry,\r\n" + "mc.Name AS issuedCountryName,\r\n"
				+ "e.MiddleName AS middleName,\r\n" + "e.LastName AS lastName,\r\n"
				+ "e.Nationality AS nationality,\r\n" + "e.OrganizationId AS organizationId,\r\n"
				+ "mo.Name AS organizationName,\r\n" + "e.Passport AS passport,e.Prefix AS prefix,\r\n"
				+ "e.PrimaryCCP AS primaryCCP,\r\n" + "e.PrimaryEmail AS primaryEmail,\r\n"
				+ "e.PrimaryPhoneNumber AS primaryPhoneNumber,\r\n" + "e.RemarksAndNotes AS remarksAndNotes,\r\n"
				+ "e.SecondaryCCP AS secondaryCCP,\r\n" + "e.SecondaryEmail AS secondaryEmail,\r\n"
				+ "e.SecondaryPhoneNumber AS secondaryPhoneNumber,\r\n" + "e.StartDate AS startDate,\r\n"
				+ "e.Status AS STATUS,\r\n" + "e.TelephoneNumber AS telephoneNumber,\r\n"
				+ "e.UpdatedBy AS updatedBy,\r\n" + "e.UpdatedDate AS updatedDate,\r\n"
				+ "e.CustomersId AS customersId,\r\n" + "ci.BusinessName AS businessName,\r\n"
				+ "con.ID AS contactId,\r\n" + "con.status AS contactStatus\r\n" + "FROM emp_profile e\r\n"
				+ "LEFT JOIN country_master mc ON e.IssuedCountry=mc.ID\r\n"
				+ "LEFT JOIN customer_contact con ON e.id=con.EmpId\r\n"
				+ "LEFT JOIN customer_info ci ON ci.CUSTOMERID = e.CustomersId\r\n"
				+ "LEFT JOIN business_unit bu ON bu.BusinessUnitId = e.BusinessUnitId\r\n"
				+ "LEFT JOIN master_departments md ON md.DepartmentId = e.DepartmentId\r\n"
				+ "LEFT JOIN master_organization mo ON mo.OrganizationId = e.OrganizationId\r\n" + "WHERE 1=1\n");

		if (customersId != 0) {
			sql.append("AND   e.CustomersId =  :customersId\n");
			params.put("customersId", customersId);
		}

		if (firstName != null) {
			sql.append(" AND e.FirstName LIKE :firstName \n");
			params.put("firstName", "%" + firstName + "%");
		}

		if (lastName != null) {
			sql.append("AND e.LastName LIKE :lastName \n");
			params.put("lastName", "%" + lastName + "%");
		}

		if (primaryEmail != null) {
			sql.append("AND e.PrimaryEmail =:primaryEmail \n");
			params.put("primaryEmail", primaryEmail);
		}

		if (primaryPhoneNumber != null && primaryPhoneNumber != 0) {
			sql.append("AND e.PrimaryPhoneNumber=:primaryPhoneNumber ");
			params.put("primaryPhoneNumber", primaryPhoneNumber);
		}

		@SuppressWarnings("deprecation")
		Query query = entityManager.createNativeQuery(sql.toString()).unwrap(NativeQuery.class)
				.addScalar("id", LongType.INSTANCE).addScalar("businessUnitId", LongType.INSTANCE)
				.addScalar("businessUnitName", StringType.INSTANCE).addScalar("departmentId", LongType.INSTANCE)
				.addScalar("departmentName", StringType.INSTANCE).addScalar("createdBy", LongType.INSTANCE)
				.addScalar("createdDate", DateType.INSTANCE).addScalar("designationId", LongType.INSTANCE)
				.addScalar("designationName", StringType.INSTANCE).addScalar("dateOfBirth", DateType.INSTANCE)
				.addScalar("imageUrl", StringType.INSTANCE).addScalar("endDate", DateType.INSTANCE)
				.addScalar("firstName", StringType.INSTANCE).addScalar("issuedCountry", LongType.INSTANCE)
				.addScalar("issuedCountryName", StringType.INSTANCE).addScalar("middleName", StringType.INSTANCE)
				.addScalar("lastName", StringType.INSTANCE).addScalar("nationality", LongType.INSTANCE)
				.addScalar("organizationId", LongType.INSTANCE).addScalar("organizationName", StringType.INSTANCE)
				.addScalar("passport", StringType.INSTANCE).addScalar("prefix", LongType.INSTANCE)
				.addScalar("primaryCCP", LongType.INSTANCE).addScalar("primaryEmail", StringType.INSTANCE)
				.addScalar("primaryPhoneNumber", LongType.INSTANCE).addScalar("remarksAndNotes", StringType.INSTANCE)
				.addScalar("secondaryCCP", LongType.INSTANCE).addScalar("secondaryEmail", StringType.INSTANCE)
				.addScalar("secondaryPhoneNumber", LongType.INSTANCE).addScalar("startDate", DateType.INSTANCE)
				.addScalar("status", StringType.INSTANCE).addScalar("telephoneNumber", LongType.INSTANCE)
				.addScalar("updatedBy", LongType.INSTANCE).addScalar("updatedDate", DateType.INSTANCE)
				.addScalar("customersId", LongType.INSTANCE).addScalar("businessName", StringType.INSTANCE)
				.addScalar("contactId", LongType.INSTANCE).addScalar("contactStatus", BooleanType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(SearchResponseDto.class));

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}

		@SuppressWarnings("unchecked")
		List<SearchResponseDto> resultList = query.getResultList();

		return resultList;

	}

	@Override
	public List<Employee> findContactBySearchParameters(String name, Long customerId, String primaryEmail,
			Long primaryPhoneNumber, String firstName, String lastName) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

		Root<Employee> contact = cq.from(Employee.class);

		List<Predicate> andpredicates = new ArrayList<>();

		List<Predicate> orpredicates = new ArrayList<>();

		if (customerId != null && customerId != 0) {
			andpredicates.add(cb.equal(contact.get("customersId"), customerId));
		}
		if (primaryEmail != null && !primaryEmail.isEmpty()) {
			andpredicates.add(cb.equal(contact.get("primaryEmail"), primaryEmail));
		}
		if (primaryPhoneNumber != null && primaryPhoneNumber != 0) {
			andpredicates.add(cb.equal(contact.get("primaryPhoneNumber"), primaryPhoneNumber));
		}

		if (firstName != null && !firstName.isBlank()) {
			andpredicates.add(cb.like(contact.get("firstName"), "%" + firstName + "%"));
		}
		if (lastName != null && !lastName.isBlank()) {
			andpredicates.add(cb.like(contact.get("lastName"), "%" + lastName + "%"));
		}

		/*
		 * or Predicates
		 */

		if (name != null && !name.isEmpty()) {
			orpredicates.add(cb.like(contact.get("firstName"), "%" + name + "%"));
		}
		if (name != null && !name.isEmpty()) {

			orpredicates.add(cb.like(contact.get("lastName"), "%" + name + "%"));
		}
		if (name != null && !name.isEmpty()) {

			orpredicates.add(cb.like(contact.get("middleName"), "%" + name + "%"));
		}
		if (name != null && !name.isEmpty()) {// firstname concat with last name search param
			Expression<String> contactExpression = cb.concat(cb.concat(contact.<String>get("firstName"), " "),
					contact.<String>get("lastName"));
			orpredicates.add(cb.like(contactExpression, name));
		}
		if (andpredicates.size() > 0 && orpredicates.size() == 0) {// only and predicates
			cq.where(andpredicates.toArray((new Predicate[andpredicates.size()])));

		} else if (orpredicates.size() > 0 && andpredicates.size() == 0) {// only or predicates
			Predicate p = cb.disjunction();
			p = cb.or(orpredicates.toArray(new Predicate[orpredicates.size()]));
			cq.where(p);
		} else {// both and && or predicates
			Predicate andPred = cb.and(andpredicates.toArray((new Predicate[andpredicates.size()])));
			Predicate orPred = cb.or(orpredicates.toArray(new Predicate[orpredicates.size()]));

			cq.where(andPred, orPred);
		}

		List<Employee> resultList = entityManager.createQuery(cq).getResultList();

		return resultList;

	}

}

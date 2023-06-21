package com.travel.travtronics.util;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.travel.travtronics.eserv.model.CustomerSearchEntity;

public class SearchEntityContact {

	public static Specification<CustomerSearchEntity> findByContactSpecifications(String firstName, String lastName,
			String primaryEmail, Long primaryPhoneNumber, Long customerId) {
		return (root, query, criteriaBuilder) -> {
			final Collection<Predicate> predicates = new ArrayList<>();

			if (firstName != null && !firstName.trim().isEmpty()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
						"%" + firstName.toLowerCase() + "%"));
			}
			if (lastName != null && !lastName.trim().isEmpty()) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
						"%" + lastName.toLowerCase() + "%"));
			}

			if (primaryEmail != null && !primaryEmail.trim().isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("primaryEmail"), primaryEmail));
			}

			if (primaryPhoneNumber != null && primaryPhoneNumber != 0) {

				predicates.add(criteriaBuilder.equal(root.get("primaryPhoneNumber"), primaryPhoneNumber));
			}

			if (customerId != null && customerId != 0) {
				predicates.add(criteriaBuilder.equal(root.get("customerId"), customerId));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

	}

}

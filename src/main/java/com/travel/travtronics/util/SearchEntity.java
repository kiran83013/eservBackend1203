package com.travel.travtronics.util;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.travel.travtronics.eserv.model.PersonModel;

public class SearchEntity {

	public static Specification<PersonModel> findByPersonSpecifications(String firstName, String lastName, Long phoneNo,
			String email, Long customerId) {
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

			if (phoneNo != null && phoneNo != 0) {

				predicates.add(criteriaBuilder.equal(root.get("primaryPhoneNumber"), phoneNo));
			}

			if (email != null && !email.trim().isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("primaryEmail"), email));
			}
			if (customerId != null && customerId != 0) {
				predicates.add(criteriaBuilder.equal(root.get("customersId"), customerId));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

		};

	}

}

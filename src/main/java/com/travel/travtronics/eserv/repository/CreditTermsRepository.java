package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.eserv.model.CreditTerms;

public interface CreditTermsRepository extends JpaRepository<CreditTerms, Long>{

	Optional<CreditTerms> findByCustomerIdAndOrganization(Long customerId, Long organization);

	List<CreditTerms> findAllByOrganization(Long organization);

	Page<CreditTerms> findByOrganization(Long organization, Pageable pageable);

}


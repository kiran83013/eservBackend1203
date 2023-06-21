package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.BpfConfigHeader;

public interface BpfConfigHeaderRepository extends JpaRepository<BpfConfigHeader, Long> {

	@Query(value = "SELECT bch.ID AS id,bch.ConfigName AS configName,bch.ActionType AS actionType,bch.Status AS status,\r\n"
			+ "bch.CreatedBy AS createdBy,bch.CreatedDate AS createdDate,bch.UpdatedBy AS updatedBy,bch.UpdatedDate AS updatedDate, \r\n"
			+ "bch.Module AS module,m.ModuleName AS moduleName,bch.SubModule AS subModule,sm.Name AS subModuleName,bch.TransitionID AS transitionID, \r\n"
			+ "CONCAT(b.`name`,' : ',c.`name`) AS Transition,bch.StatusFrom AS statusFrom,s.name AS statusFromName,bch.StatusTo AS statusTo,\r\n"
			+ "st.name AS statusToName,bch.OrganizationId AS organizationId, mo.Name AS organizationName\r\n"
			+ "FROM bpf_config_header bch\r\n" + "LEFT JOIN module m ON m.ModuleId = bch.Module\r\n"
			+ "LEFT JOIN master_organization mo ON mo.OrganizationId = bch.OrganizationId\r\n"
			+ "LEFT JOIN sub_module sm ON sm.ID = bch.SubModule\r\n"
			+ "LEFT JOIN status s ON s.StatusId = bch.StatusFrom\r\n"
			+ "LEFT JOIN status st ON st.StatusId = bch.statusTo\r\n"
			+ "LEFT JOIN status b ON b.StatusId = bch.StatusFrom\r\n"
			+ "LEFT JOIN status c ON c.StatusId = bch.StatusTo\r\n" + "WHERE bch.OrganizationId=?1", nativeQuery = true)
	List<Map<String, String>> findAllList(Long organizationId);

	Page<BpfConfigHeader> findByOrganizationId(Long organizationId, Pageable pageable);
	
	
	@Query(value = "SELECT lth.letter_Name AS letterTemplateName FROM bpf_letter_template_header  lth WHERE lth.letter_Id=?1", nativeQuery = true)
	Optional<String> getLetterTemplateName(Long Id);
	
//	@Query("select count(*) from BpfConfigHeader h where h.organizationId=?1")
//	Long countByOrganizationId(Long organizationId);
//
//	@Query("select count(*) from BpfConfigHeader h")
//	Long countAll();

}

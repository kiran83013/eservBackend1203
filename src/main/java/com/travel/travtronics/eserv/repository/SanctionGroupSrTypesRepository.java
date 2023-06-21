package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.SanctionGroupSrTypes;

public interface SanctionGroupSrTypesRepository extends JpaRepository<SanctionGroupSrTypes, Long>{

	@Query(value = "SELECT \r\n" + 
			"sgst.id,\r\n" + 
			"sgst.sanction_group AS sanctionGroup,\r\n" + 
			"sg.name AS sanctionGroupName,\r\n" + 
			"sgst.sr_type AS srType,\r\n" + 
			"sth.Name AS srTypeName,\r\n" + 
			"sgst.is_online AS isOnline,\r\n" + 
			"sgst.is_offline AS isOffline,\r\n" + 
			"sgst.status,\r\n" + 
			"sgst.created_by AS createdBy,\r\n" + 
			"sgst.updated_by AS updatedBy,\r\n" + 
			"sgst.created_date AS createdDate,\r\n" + 
			"sgst.updated_date AS updatedDate\r\n" + 
			"FROM sanction_group_sr_types AS sgst\r\n" + 
			"LEFT JOIN service_types_header AS sth ON sth.ID = sgst.sr_type\r\n" + 
			"LEFT JOIN sanction_group AS sg ON sg.id = sgst.sanction_group\r\n" + 
			"WHERE\r\n" + 
			"sgst.sanction_group = ?1\r\n" + 
			"AND\r\n" + 
			"sgst.status = \"Active\"", nativeQuery = true)
	List<Map<String, String>> findBySanctionGroupList(Long sanctionGroup);

}

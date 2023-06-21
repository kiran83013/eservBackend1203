package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.BpfServiceRegister;

public interface BpfServiceRegisterRepository extends JpaRepository<BpfServiceRegister, Long>{
	
	@Query(value = "SELECT bsr.ID AS id,bsr.ServiceName AS serviceName,bsr.ServiceUrl AS serviceUrl,bsr.StartDate AS startDate,bsr.EndDate AS endDate,\r\n" + 
			"bsr.CreatedBy AS createdBy,bsr.CreatedDate AS createdDate,bsr.UpdatedBy AS updatedBy,bsr.UpdatedDate AS updatedDate,bsr.Description AS description,bsr.Status AS status,\r\n" + 
			"bsr.ModuleId AS moduleId,m.ModuleName AS moduleName, bsr.OrganizationId AS organizationId,mo.Name AS organizationName, bsr.IsQuartz AS isQuartz,\r\n" + 
			"bsr.Output AS output, bsr.Template AS template FROM bpf_service_register bsr\r\n" + 
			"LEFT JOIN module m ON m.ModuleId= bsr.ModuleId\r\n" + 
			"LEFT JOIN  master_organization mo ON mo.OrganizationId = bsr.OrganizationId\r\n" + 
			"WHERE bsr.OrganizationId=?1",nativeQuery = true)
	List<Map<String, String>> findByList(Long organizationId);

	Page<BpfServiceRegister> findByOrganizationId(Long organizationId, Pageable pageable);

}

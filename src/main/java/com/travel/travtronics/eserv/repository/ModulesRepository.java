package com.travel.travtronics.eserv.repository;

import java.util.List;
import java.util.Map;
//import java.util.List;
//import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.eserv.model.Modules;


public interface ModulesRepository extends JpaRepository<Modules, Long>{

	Optional<Modules> findBymoduleId(Long moduleId);

	List<Modules> findAllByModuleId(Long moduleId);

	Page<Modules> findByModuleId(Long moduleId, Pageable pageable);

//	Optional<Modules> findByModuleName(String name);
//
//	Optional<Modules> findBycreatedBy(String createdBy);
//	
//	@Query(value = "select *from Modules where name LIKE %?%",nativeQuery = true)
//	List<Map<String, Object>> findBynames(String name);
	
	@Query(value = "SELECT m.ModuleId AS moduleId,m.CreatedBy AS createdBy,m.CreatedDate AS createdDate,m.Description AS description,m.ModuleName AS moduleName,m.Status AS status,m.UpDatedBy AS upDatedBy,m.UpDatedDate AS upDatedDate FROM module m",nativeQuery = true)
	List<Map<Object, Object>> findListAll();

}

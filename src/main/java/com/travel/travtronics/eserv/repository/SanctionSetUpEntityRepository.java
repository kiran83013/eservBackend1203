package com.travel.travtronics.eserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.travtronics.eserv.model.SanctionSetUpEntity;

public interface SanctionSetUpEntityRepository extends CrudRepository<SanctionSetUpEntity, Long> {

	@Query(value = "SELECT\r\n" + "v.id AS id,\r\n" + "v.view_name,\r\n" + "(SELECT \r\n"
			+ "GROUP_CONCAT(`COLUMN_NAME` ORDER BY `ORDINAL_POSITION` ASC)\r\n" + " FROM \r\n"
			+ "`information_schema`.`COLUMNS`\r\n" + "WHERE `TABLE_NAME`=v.view_name) AS view_names_content\r\n"
			+ "FROM\r\n" + "e_services.sanction_views v\r\n" + "WHERE\r\n" + "v.id=?1 AND v.status='Active'\r\n"
			+ "", nativeQuery = true)
	List<SanctionSetUpEntity> findBySetUpId(Long id);

}

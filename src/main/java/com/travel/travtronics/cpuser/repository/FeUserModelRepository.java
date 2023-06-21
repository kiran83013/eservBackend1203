package com.travel.travtronics.cpuser.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.travel.travtronics.cpuser.model.FeUserModel;

public interface FeUserModelRepository extends PagingAndSortingRepository<FeUserModel, Integer> {

	@Query(value = "SELECT contact.ID contactId,\n" +
			"\tCONCAT(contact.FirstName,' ',contact.LastName) contactName,\n" +
			"\tcontact.PrimaryEmail contactEmail,contact.PrimaryPhoneNumber contactPhoneNO,\n" +
			"\tcontact.CustomerId customerId,customer.BusinessName customerName,\n" +
			"\tcpuser.email userEmail,cpuser.id userId,\n" +
			"\tcpuser.phone_primary userPhoneNo,cpuser.username userName,\n" +
			"\tcpuserrole.name portalRole,\n" +
			"\tchannel.id channelId,\n" +
			"\tchannel.name channelName\n" +
			"FROM e_services.customer_contact contact\n" +
			"LEFT JOIN e_services.customer_info customer ON customer.CUSTOMERID=contact.CustomerId\n" +
			"LEFT JOIN a3m_account cpuser ON cpuser.biz_id=contact.CustomerId AND cpuser.contact_id=contact.ID\n" +
			"LEFT JOIN a3m_rel_account_role cpuserrolemap ON cpuser.id=cpuserrolemap.account_id \n" +
			"LEFT JOIN a3m_acl_role cpuserrole ON cpuserrolemap.role_id=cpuserrole.id\n" +
			"JOIN e_services.master_channel channel\n" +
			"\tWHERE\n" +
			"\t\tcontact.ID=?1 AND contact.CustomerId=?2 AND channel.id=?3", nativeQuery = true)
	List<Map<String, Object>> findByContactIdAndCustomerId(Integer contactId, Integer customerId, Integer channelId);

	@Query(value = "SELECT contact.ID contactId,\n" +
			"\tCONCAT(contact.FirstName,' ',contact.LastName) contactName,\n" +
			"\tcontact.PrimaryEmail contactEmail,contact.PrimaryPhoneNumber contactPhoneNO\n" +
			"FROM e_services.customer_contact contact\n" +
			"WHERE contact.CustomerId=?1 AND contact.RoleId = \"1\"", nativeQuery = true)
	List<Map<String, Object>> findByCustomerIdForPrimaryContact(Integer customerId);
}

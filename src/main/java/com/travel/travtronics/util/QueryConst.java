package com.travel.travtronics.util;

public class QueryConst {

	public static final String GET_EMP_LIST = "SELECT ep.ID AS id, \r\n" + "ep.Prefix AS prefix,\r\n"
			+ "ep.FirstName AS firstName,\r\n" + "ep.MiddleName AS middleName,\r\n" + "ep.LastName AS lastName,\r\n"
			+ "ep.OrganizationId AS organizationId, \r\n" + "ep.BusinessUnitId AS businessUnitId,\r\n"
			+ "ep.DepartmentId AS departmentId,\r\n" + "ep.DesignationId AS designationId,\r\n"
			+ "ep.DesignationName AS designationName,\r\n" + "ep.PrimaryEmail AS primaryEmail,\r\n"
			+ "ep.PrimaryPhoneNumber AS PrimaryPhoneNumber,\r\n" + "ep.SecondaryEmail  AS secondaryEmail,\r\n"
			+ "ep.SecondaryPhoneNumber AS secondaryPhoneNumber,\r\n" + "ep.TelephoneNumber AS telephoneNumber,\r\n"
			+ "ep.Nationality AS nationality, \r\n" + "ep.DateOfBirth AS dob,\r\n" + "ep.Passport AS passport,\r\n"
			+ "ep.IssuedCountry AS issuedCountry,\r\n" + "ep.RemarksAndNotes AS remarksAndNotes,\r\n"
			+ "ep.StartDate AS startDate,\r\n" + "ep.EndDate AS endDate,\r\n" + "ep.ImageUrl AS dpImageUrl,\r\n"
			+ "ep.CreatedBy AS createdBy,\r\n" + "ep.CreatedDate AS createdDate,\r\n" + "ep.UpdatedBy AS updatedBy,\r\n"
			+ "ep.UpdatedDate AS updatedDate,\r\n" + "ep.PrimaryCcp AS primaryCcp,\r\n"
			+ "ep.SecondaryCcp AS secondaryCcp,\r\n" + "ep.Status AS status,\r\n"
			+ "mt.Name AS prefixName, mc.Name AS issuedCountryName ,mn.Name AS nationalityName,mo.Name AS organizationName,\r\n"
			+ "md.Name AS departmentName,bu.Name AS businessUnitName\r\n" + "FROM emp_profile ep \r\n"
			+ "LEFT JOIN master_prefix  mt ON ep.Prefix=mt.ID\r\n"
			+ "LEFT JOIN country_master mc ON mc.ID = ep.IssuedCountry\r\n"
			+ "LEFT JOIN country_master mn ON mn.ID = ep.Nationality\r\n"
			+ "LEFT JOIN master_organization  mo ON mo.OrganizationId=ep.OrganizationId\r\n"
			+ "LEFT JOIN master_departments md ON md.DepartmentId=ep.DepartmentId\r\n"
			+ "LEFT JOIN business_unit bu ON bu.BusinessUnitId=ep.BusinessUnitId AND ep.organizationId =?1";

	public final static String GET_ALL_SLOT_SHIFT_ASSIGNMENTS = "SELECT\r\n" + "slot_shift_map.*,\r\n"
			+ "be_user_det.fullname AS user_name,sth.name slot_template_name,sm.shift_name shift_name,mo.Name AS organization_name\r\n"
			+ "FROM \r\n" + "e_services.user_shift_slot_assignments  slot_shift_map\r\n"
			+ "JOIN e_services_be_user.a3m_account be_user  ON be_user.id=slot_shift_map.user_id \r\n"
			+ "JOIN e_services_be_user.a3m_account_details  be_user_det ON be_user_det.user_id=be_user.id\r\n"
			+ "JOIN e_services.shifts_manager sm ON sm.shift_id=slot_shift_map.shift_id \r\n"
			+ "JOIN e_services.slots_template_header sth ON sth.id=slot_shift_map.slot_template_id\r\n"
			+ "JOIN e_services.master_organization mo ON mo.OrganizationId = slot_shift_map.org_id\r\n"
			+ "WHERE slot_shift_map.org_id=:orgId";

	public final static String GET_SHIFT_USER_MAPPING_LIST = "SELECT usa.id,usa.user_id userId,CONCAT(ui.first_name,' ',ui.last_name) userName,usa.shift_id shiftId,sm.shift_name shiftName,\r\n"
			+ "usa.org_id orgId,usa.start_date startDate,usa.end_date endDate,mo.Name AS organizationName,\r\n"
			+ "usa.created_by createdBy,usa.created_date createdDate,usa.updated_date updatedDate,usa.updated_by updatedBy,\r\n"
			+ "usa.status FROM user_shift_assignments usa\r\n"
			+ "LEFT JOIN e_services.user_info ui ON ui.user_id=usa.user_id\r\n"
			+ "LEFT JOIN master_organization mo ON mo.OrganizationId = usa.org_id\r\n"
			+ "LEFT JOIN shifts_manager sm ON sm.shift_id=usa.shift_id WHERE usa.org_id=?1 ";

	public final static String GET_SLOT_INFO_BY_SHIFT_ID = "SELECT sth.* FROM slots_template_header  sth\r\n"
			+ "INNER JOIN user_shift_slot_assignments ussa ON \r\n" + "ussa.slot_template_id=sth.id\r\n"
			+ "WHERE ussa.shift_id=?1\r\n" + "GROUP BY sth.id";

	public final static String GET_BOOKED_APPOINTMENT_LIST = "SELECT booking.booking_id bookingId,\r\n"
			+ "booking.shift_id shiftId,shift.shift_name shiftName,booking.slot_id slotId, \r\n"
			+ "slot.name slotName,booking.slot_line_id slotLineId,slotline.name slotLineName, \r\n"
			+ "slotline.start_time slotStartTime,slotline.end_time slotEndTime,\r\n"
			+ "booking.user_id userId,CONCAT(ui.first_name,' ',ui.last_name)userName,\r\n"
			+ "booking.booking_date bookingDate,booking.org_id orgId,booking.status, \r\n"
			+ "booking.created_by createdBy,booking.created_date createdDate, mo.Name AS organizationName,\r\n"
			+ "booking.updated_by updatedBy,booking.updated_date updatedDate FROM \r\n"
			+ "e_services.user_appointment_booking booking\r\n"
			+ "INNER JOIN e_services.shifts_manager shift ON shift.shift_id=booking.shift_id\r\n"
			+ "INNER JOIN e_services.slots_template_header slot ON slot.id=booking.slot_id\r\n"
			+ "INNER JOIN e_services.master_organization mo ON mo.OrganizationId = booking.org_id\r\n"
			+ "INNER JOIN e_services.slots_template_lines slotline ON slotline.id=booking.slot_line_id\r\n"
			+ "INNER JOIN e_services.user_info ui ON ui.user_id=booking.user_id WHERE booking.org_id =?1";

	public final static String BOOKED_APPOINTMENT_VALIDATION = "select case when count(ap)>0  then true else false end "
			+ "from AppointMentBookingModel ap where ap.shiftId=?1 AND  ap.slotId=?2 AND ap.slotLineId=?3 AND ap.userId=?4";

	public final static String GET_ALL_UNIT_SLOT_SHIFT_ASSIGNMENTS = "SELECT uss.id,\r\n" + "uss.shift_id shiftId,\r\n"
			+ "sm.shift_name shiftName,  uss.slot_template_id slotTemplateId,\r\n"
			+ "sth.name slotTemplateName,  uss.start_date startDate, uss.end_date endDate,\r\n"
			+ "uss.created_date createdDate,  uss.updated_date updatedDate,uss.org_id orgId,mo.Name AS organizationName,\r\n"
			+ "uss.created_by createdBy,  uss.updated_by updatedBy,uss.status,\r\n"
			+ "uss.unit_id AS unitId,ui.Unit_Name AS unitName\r\n"
			+ "FROM e_services.unit_shift_slot_assignments uss \r\n"
			+ "LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = uss.org_id\r\n"
			+ "LEFT JOIN e_services.shifts_manager sm ON sm.shift_id=uss.shift_id\r\n"
			+ "LEFT JOIN e_services.slots_template_header sth ON sth.id=uss.slot_template_id \r\n"
			+ "LEFT JOIN e_services.unit_information ui ON ui.ID = uss.unit_id WHERE uss.org_id=?1";

	public static final String GET_SHIFT_UNIT_MAPPING_LIST = "SELECT usa.id,usa.shift_id shiftId,sm.shift_name shiftName,\r\n"
			+ "usa.org_id orgId,usa.start_date startDate,usa.end_date endDate,mo.Name AS organizationName,\r\n"
			+ "usa.created_by createdBy,usa.created_date createdDate,usa.updated_date updatedDate,usa.updated_by updatedBy,\r\n"
			+ "usa.status AS status,\r\n"
			+ "usa.unit_id AS unitId,ui.Unit_Name AS unitName FROM unit_shift_assignments usa\r\n"
			+ "LEFT JOIN master_organization mo ON mo.OrganizationId = usa.org_id\r\n"
			+ "LEFT JOIN shifts_manager sm ON sm.shift_id=usa.shift_id\r\n"
			+ "LEFT JOIN e_services.unit_information ui ON ui.ID = usa.unit_id WHERE usa.org_id=?1 ";

	public static final String GET_CONTACT_SEARCH_INFO = "SELECT \r\n"
			+ "contact.*,customer.BusinessName AS customer\r\n" + "FROM e_services.customer_contact contact\r\n"
			+ "JOIN e_services.customer_info customer ON customer.CUSTOMERID=contact.CustomerId";
	
	public static final String GET_SLA_HEADER_LIST_QRY = "SELECT slah.id, slah.org_id, mo.Name AS org_name, slah.business_unit, bu.Name AS business_unit_name, slah.cost_center, \r\n"
			+ "cc.Name AS cost_center_name, slah.location, l.Name AS location_name, slah.business, ci.BusinessName AS business_name, \r\n"
			+ "slah.serivce_type, sth.Name AS serivce_type_name, slah.shift_id, sm.shift_name, slah.sla_time, slah.note, slah.start_date,\r\n"
			+ "slah.end_date, slah.record_status, slah.created_by, slah.created_date, slah.updated_by, slah.updated_date\r\n"
			+ "FROM sla_header AS slah\r\n"
			+ "LEFT JOIN shifts_manager AS sm ON sm.shift_id = slah.shift_id\r\n"
			+ "LEFT JOIN master_organization AS mo ON mo.OrganizationId = slah.org_id\r\n"
			+ "LEFT JOIN business_unit AS bu ON bu.BusinessUnitId = slah.business_unit\r\n"
			+ "LEFT JOIN cost_center AS cc ON cc.CostCenterId = slah.cost_center\r\n"
			+ "LEFT JOIN location AS l ON l.LocationId = slah.location\r\n"
			+ "LEFT JOIN service_types_header AS sth ON sth.ID = slah.serivce_type\r\n"
			+ "LEFT JOIN customer_info AS ci ON ci.CUSTOMERID = slah.business\r\n"
			+ "WHERE 1 = 1\r\n"
			+ "AND slah.record_status = 'Active'";
	
	public static final String HOLIDAY_CAL_DATE_CHECK_JOIN_QRY = "SELECT hth.* FROM holiday_template_lines htl \r\n"
			+ "JOIN holiday_template_header hth ON hth.id = header_id WHERE 1 = 1 \r\n"
			+ "AND hth.id = ?1 AND htl.holiday_date = ?2 AND hth.status = 'Active' \r\n"
			+ "AND (is_deleted IS NULL OR is_deleted != 1)";
	
	
	

}

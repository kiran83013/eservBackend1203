package com.travel.travtronics.response;

public class UserSearchResponse {

	private Long userId;

	private String costCenter;

	private String costCenterName;

	private String userCreatedDate;

	private String department;

	private String departmentName;

	private String email;

	private String phoneNumber;

	private String firstName;

	private String lastName;

	private String userName;

	private String location;

	private String locationName;

	private String supervisor;

	private String supervisorName;

	private String shiftFrom;

	private String shiftTo;

	private Long shiftId;

	private String shiftName;

//	private Long teamId;
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	private String teamName;
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String timeZone;
	private String timeZoneName;

	private Long slotTemplateId;

	private String slotTemplateName;

	private Long userShiftAssignmentId;

	private Long userShiftSlotAssignmentId;
	
	
	

	

	

	public UserSearchResponse(Long userId, String costCenter, String costCenterName, String userCreatedDate,
			String department, String departmentName, String email, String phoneNumber, String firstName,
			String lastName, String userName, String location, String locationName, String supervisor,
			String supervisorName, String shiftFrom, String shiftTo, Long shiftId, String shiftName, String timeZone,
			String timeZoneName, Long slotTemplateId, String slotTemplateName, Long userShiftAssignmentId,
			Long userShiftSlotAssignmentId) {
		super();
		this.userId = userId;
		this.costCenter = costCenter;
		this.costCenterName = costCenterName;
		this.userCreatedDate = userCreatedDate;
		this.department = department;
		this.departmentName = departmentName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.location = location;
		this.locationName = locationName;
		this.supervisor = supervisor;
		this.supervisorName = supervisorName;
		this.shiftFrom = shiftFrom;
		this.shiftTo = shiftTo;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.timeZone = timeZone;
		this.timeZoneName = timeZoneName;
		this.slotTemplateId = slotTemplateId;
		this.slotTemplateName = slotTemplateName;
		this.userShiftAssignmentId = userShiftAssignmentId;
		this.userShiftSlotAssignmentId = userShiftSlotAssignmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(String userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getShiftFrom() {
		return shiftFrom;
	}

	public void setShiftFrom(String shiftFrom) {
		this.shiftFrom = shiftFrom;
	}

	public String getShiftTo() {
		return shiftTo;
	}

	public void setShiftTo(String shiftTo) {
		this.shiftTo = shiftTo;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public Long getSlotTemplateId() {
		return slotTemplateId;
	}

	public void setSlotTemplateId(Long slotTemplateId) {
		this.slotTemplateId = slotTemplateId;
	}

	public String getSlotTemplateName() {
		return slotTemplateName;
	}

	public void setSlotTemplateName(String slotTemplateName) {
		this.slotTemplateName = slotTemplateName;
	}

	public Long getUserShiftAssignmentId() {
		return userShiftAssignmentId;
	}

	public void setUserShiftAssignmentId(Long userShiftAssignmentId) {
		this.userShiftAssignmentId = userShiftAssignmentId;
	}

	public Long getUserShiftSlotAssignmentId() {
		return userShiftSlotAssignmentId;
	}

	public void setUserShiftSlotAssignmentId(Long userShiftSlotAssignmentId) {
		this.userShiftSlotAssignmentId = userShiftSlotAssignmentId;
	}

	

}

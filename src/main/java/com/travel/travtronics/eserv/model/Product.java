package com.travel.travtronics.eserv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travel.travtronics.enums.Status;


@Entity
@Table(name = "customer_product")
public class Product extends WhoColumnsModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "CustomerId")
	private Long customerId;
	
	@Column(name = "OrganizationId")
	private Long organizationId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Business")
	private Long business;

	@Column(name = "Brand")
	private Long brand;

	@Column(name = "ShortName")
	private String shortName;

	@Column(name = "Code")
	private String code;

	@Column(name = "Category")
	private Long category;

	@Column(name = "Type")
	private Long type;

	@Column(name = "UOM")
	private Long uom;

	@Column(name = "Summary")
	private String summary;

	@Column(name = "Description", columnDefinition = "LONGTEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private Status status;

	@Column(name = "Tags")
	private Long tags;

	@Column(name = "ApprovedBy")
	private Long approvedBy;

	@Column(name = "ApprovedDate")
	private Date approvedDate;

	@Column(name = "CancelledBy")
	private Long cancelledBy;

	@Column(name = "CancelledDate")
	private Date cancelledDate;

	@Column(name = "CancelledReason")
	private String cancelledReason;

	@Column(name = "Attr1")
	private Long attr1;

	@Column(name = "Attr2")
	private Long attr2;

	@Column(name = "Attr3")
	private Long attr3;

	@Column(name = "Attr4")
	private Long attr4;

	@Column(name = "Attr5")
	private Long attr5;

	@Column(name = "Attr6")
	private String attr6;

	@Column(name = "Attr7")
	private String attr7;

	@Column(name = "Attr8")
	private String attr8;

	@Column(name = "Attr9")
	private String attr9;

	@Column(name = "Attr10")
	private String attr10;

	@Column(name = "Attr11", columnDefinition = "LONGTEXT")
	private String attr11;

	@Column(name = "Attr12", columnDefinition = "LONGTEXT")
	private String attr12;

	@Column(name = "Attr13")
	private Date attr13;

	@Column(name = "Attr14")
	private Date attr14;

	@Column(name = "attr15")
	private Date attr15;

//	@Column(name = "CreatedBy")
//	private Long createdBy;
//
//	@Column(name = "UpdatedBy")
//	private Long updatedBy;
//
//	@Column(name = "CreatedDate")
//	private Date createdDate;
//
//	@Column(name = "UpdatedDate")
//	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBusiness() {
		return business;
	}

	public void setBusiness(Long business) {
		this.business = business;
	}

	public Long getBrand() {
		return brand;
	}

	public void setBrand(Long brand) {
		this.brand = brand;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getUom() {
		return uom;
	}

	public void setUom(Long uom) {
		this.uom = uom;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getTags() {
		return tags;
	}

	public void setTags(Long tags) {
		this.tags = tags;
	}

	public Long getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Long approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Long getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(Long cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public String getCancelledReason() {
		return cancelledReason;
	}

	public void setCancelledReason(String cancelledReason) {
		this.cancelledReason = cancelledReason;
	}

	public Long getAttr1() {
		return attr1;
	}

	public void setAttr1(Long attr1) {
		this.attr1 = attr1;
	}

	public Long getAttr2() {
		return attr2;
	}

	public void setAttr2(Long attr2) {
		this.attr2 = attr2;
	}

	public Long getAttr3() {
		return attr3;
	}

	public void setAttr3(Long attr3) {
		this.attr3 = attr3;
	}

	public Long getAttr4() {
		return attr4;
	}

	public void setAttr4(Long attr4) {
		this.attr4 = attr4;
	}

	public Long getAttr5() {
		return attr5;
	}

	public void setAttr5(Long attr5) {
		this.attr5 = attr5;
	}

	public String getAttr6() {
		return attr6;
	}

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	public String getAttr7() {
		return attr7;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	public String getAttr8() {
		return attr8;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	public String getAttr9() {
		return attr9;
	}

	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}

	public String getAttr10() {
		return attr10;
	}

	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}

	public String getAttr11() {
		return attr11;
	}

	public void setAttr11(String attr11) {
		this.attr11 = attr11;
	}

	public String getAttr12() {
		return attr12;
	}

	public void setAttr12(String attr12) {
		this.attr12 = attr12;
	}

	public Date getAttr13() {
		return attr13;
	}

	public void setAttr13(Date attr13) {
		this.attr13 = attr13;
	}

	public Date getAttr14() {
		return attr14;
	}

	public void setAttr14(Date attr14) {
		this.attr14 = attr14;
	}

	public Date getAttr15() {
		return attr15;
	}

	public void setAttr15(Date attr15) {
		this.attr15 = attr15;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	

//	public Long getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(Long createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Long getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(Long updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	
	

}

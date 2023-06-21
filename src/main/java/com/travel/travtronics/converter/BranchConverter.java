package com.travel.travtronics.converter;

import com.travel.travtronics.eserv.model.Branch;
import com.travel.travtronics.response.BranchResponse;

public class BranchConverter {

	public static final BranchResponse convertBranchModelToResponse(Branch model) {
		BranchResponse response = new BranchResponse();
		response.setBank(model.getBank());
		response.setId(model.getId());
		response.setBranch(model.getBranch());
		response.setBranchAddress(model.getBranchAddress());
		response.setShortName(model.getShortName());
		response.setCode(model.getCode());
		response.setCountry(model.getCountry());
		response.setCurrency(model.getCurrency());
		response.setBrType(model.getBrType());
		response.setBrCtgy(model.getBrCtgy());
		response.setCode1(model.getCode1());
		response.setCode2(model.getCode2());
		response.setCode3(model.getCode3());
		response.setCode4(model.getCode4());
		response.setCode5(model.getCode5());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setStatus(model.getStatus());
		response.setOrganizationId(model.getOrganizationId());
		return response;
	}
}

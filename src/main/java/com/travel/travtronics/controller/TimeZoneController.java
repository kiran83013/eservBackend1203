package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.BankService;

@RestController
public class TimeZoneController {
	
	@Autowired
	BankService bankService;
	
	@GetMapping("/all")
	public APIResponse getAllTimeZoneInfo() {
		return bankService.getAllTimeZoneInfo();
	}

	
}

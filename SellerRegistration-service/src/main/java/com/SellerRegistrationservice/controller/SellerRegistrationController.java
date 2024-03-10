package com.SellerRegistrationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SellerRegistrationservice.dto.SellerRegistrationDTO;
import com.SellerRegistrationservice.service.SellerRegistrationService;

@RestController
@RequestMapping("/seller-registrations")
public class SellerRegistrationController {

	private final SellerRegistrationService sellerRegistrationService;

	@Autowired
	public SellerRegistrationController(SellerRegistrationService sellerRegistrationService) {
		this.sellerRegistrationService = sellerRegistrationService;
	}

	@PostMapping("/postdetails")
	public ResponseEntity<SellerRegistrationDTO> createSellerRegistration(
			@RequestBody SellerRegistrationDTO sellerRegistrationDTO) {
		SellerRegistrationDTO createdSellerRegistrationDTO = sellerRegistrationService
				.createSellerRegistration(sellerRegistrationDTO);
		return new ResponseEntity<>(createdSellerRegistrationDTO, HttpStatus.CREATED);
	}

	@GetMapping("/getdetails")
	public ResponseEntity<List<SellerRegistrationDTO>> getAllSellerRegistrations() {
		List<SellerRegistrationDTO> sellerRegistrationsDTO = sellerRegistrationService.getAllSellerRegistrations();
		return new ResponseEntity<>(sellerRegistrationsDTO, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String emailID, @RequestParam String password) {
		if (emailID.isEmpty() || password.isEmpty()) {
			return new ResponseEntity<>("EmailID or Password can't be empty", HttpStatus.BAD_REQUEST);
		}

		boolean isValidLogin = sellerRegistrationService.validateLogin(emailID, password);
		if (isValidLogin) {
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
		}
	}
}

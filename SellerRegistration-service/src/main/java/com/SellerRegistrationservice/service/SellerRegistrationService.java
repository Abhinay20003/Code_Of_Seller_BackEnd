package com.SellerRegistrationservice.service;

import java.util.List;

import com.SellerRegistrationservice.dto.SellerRegistrationDTO;

public interface SellerRegistrationService {
	SellerRegistrationDTO createSellerRegistration(SellerRegistrationDTO sellerRegistrationDTO);

	List<SellerRegistrationDTO> getAllSellerRegistrations();

	boolean validateLogin(String emailID, String password);
}
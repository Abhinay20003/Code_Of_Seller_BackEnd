
package com.SellerRegistrationservice.service;

import com.SellerRegistrationservice.dto.SellerRegistrationDTO;
import com.SellerRegistrationservice.model.SellerRegistration;
import com.SellerRegistrationservice.repository.SellerRegistrationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerRegistrationServiceImpl implements SellerRegistrationService {
    private final SellerRegistrationRepo sellerRegistrationRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public SellerRegistrationServiceImpl(SellerRegistrationRepo sellerRegistrationRepo, ModelMapper modelMapper) {
        this.sellerRegistrationRepo = sellerRegistrationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public SellerRegistrationDTO createSellerRegistration(SellerRegistrationDTO sellerRegistrationDTO) {
        SellerRegistration createdSellerRegistration = sellerRegistrationRepo
                .save(modelMapper.map(sellerRegistrationDTO, SellerRegistration.class));
        return modelMapper.map(createdSellerRegistration, SellerRegistrationDTO.class);
    }

    @Override
    public List<SellerRegistrationDTO> getAllSellerRegistrations() {
        List<SellerRegistration> sellerRegistrations = sellerRegistrationRepo.findAll();
        return sellerRegistrations.stream()
                .map(sellerRegistration -> modelMapper.map(sellerRegistration, SellerRegistrationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean validateLogin(String emailID, String password) {
        SellerRegistration seller = sellerRegistrationRepo.findByEmailID(emailID);
        return seller != null && seller.getPassword().equals(password);
    }
}
//package com.SellerRegistrationservice.service;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.SellerRegistrationservice.dto.SellerRegistrationDTO;
//import com.SellerRegistrationservice.model.SellerRegistration;
//import com.SellerRegistrationservice.repository.SellerRegistrationRepo;
//
//@Service
//public class SellerRegistrationServiceImpl implements SellerRegistrationService {
//	private final SellerRegistrationRepo sellerRegistrationRepo;
//
//	@Autowired
//	public SellerRegistrationServiceImpl(SellerRegistrationRepo sellerRegistrationRepo) {
//		this.sellerRegistrationRepo = sellerRegistrationRepo;
//	}
//
//	@Override
//	public SellerRegistrationDTO createSellerRegistration(SellerRegistrationDTO sellerRegistrationDTO) {
//		SellerRegistration createdSellerRegistration = sellerRegistrationRepo
//				.save(convertToEntity(sellerRegistrationDTO));
//		return convertToDTO(createdSellerRegistration);
//	}
//
//	@Override
//	public List<SellerRegistrationDTO> getAllSellerRegistrations() {
//		List<SellerRegistration> sellerRegistrations = sellerRegistrationRepo.findAll();
//		return convertToDTOList(sellerRegistrations);
//	}
//
//	@Override
//	public boolean validateLogin(String emailID, String password) {
//		SellerRegistration seller = sellerRegistrationRepo.findByEmailID(emailID);
//		return seller != null && seller.getPassword().equals(password);
//	}
//
	// Utility methods to convert between DTO and Entity
//	private SellerRegistration convertToEntity(SellerRegistrationDTO sellerRegistrationDTO) {
//		SellerRegistration sellerRegistration = new SellerRegistration();
//		sellerRegistration.setName(sellerRegistrationDTO.getName());
//		sellerRegistration.setEmailID(sellerRegistrationDTO.getEmailID());
//		sellerRegistration.setCompanyName(sellerRegistrationDTO.getCompanyName());
//		sellerRegistration.setGstNumber(sellerRegistrationDTO.getGstNumber());
//		sellerRegistration.setCompanyAddress(sellerRegistrationDTO.getCompanyAddress());
//		sellerRegistration.setPhoneNumber(sellerRegistrationDTO.getPhoneNumber());
//		sellerRegistration.setPassword(sellerRegistrationDTO.getPassword());
//		return sellerRegistration;
//	}
//
//	private SellerRegistrationDTO convertToDTO(SellerRegistration sellerRegistration) {
//		SellerRegistrationDTO sellerRegistrationDTO = new SellerRegistrationDTO();
//		sellerRegistrationDTO.setName(sellerRegistration.getName());
//		sellerRegistrationDTO.setEmailID(sellerRegistration.getEmailID());
//		sellerRegistrationDTO.setCompanyName(sellerRegistration.getCompanyName());
//		sellerRegistrationDTO.setGstNumber(sellerRegistration.getGstNumber());
//		sellerRegistrationDTO.setCompanyAddress(sellerRegistration.getCompanyAddress());
//		sellerRegistrationDTO.setPhoneNumber(sellerRegistration.getPhoneNumber());
//		sellerRegistrationDTO.setPassword(sellerRegistration.getPassword());
//		return sellerRegistrationDTO;
//	}
//
//	private List<SellerRegistrationDTO> convertToDTOList(List<SellerRegistration> sellerRegistrations) {
//		List<SellerRegistrationDTO> sellerRegistrationDTOs = new ArrayList<>();
//		for (SellerRegistration sellerRegistration : sellerRegistrations) {
//			sellerRegistrationDTOs.add(convertToDTO(sellerRegistration));
//		}
//		return sellerRegistrationDTOs;
//	}
//}
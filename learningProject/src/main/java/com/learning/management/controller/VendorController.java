package com.learning.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.management.exception.BusinessException;
import com.learning.management.model.ResponseInfo;
import com.learning.management.model.Status;
import com.learning.management.model.VendorVO;
import com.learning.management.service.VendorService;
@Component
@RestController
@RequestMapping(value = "/vendor")
public class VendorController {
	@Autowired(required=true)
	VendorService vendorService;


	@PostMapping(value = "/addNewVendor")
	public ResponseEntity<ResponseInfo> addNewVendor(@RequestBody VendorVO vendor) throws BusinessException {

       vendorService.add(vendor);
//	
		return new ResponseEntity<ResponseInfo>(
				new ResponseInfo(new Status(HttpStatus.OK, "Successfully added vendor : " +vendor.getVendorId() ), vendor, null),
				HttpStatus.OK);

	}

	@PostMapping(value = "/editVendor")
	public ResponseEntity<ResponseInfo> editVendor(@RequestBody VendorVO vendor) throws BusinessException {

		vendorService.update(vendor);
//	
		return new ResponseEntity<ResponseInfo>(new ResponseInfo(
				new Status(HttpStatus.OK, "Successfully updated User with userId: " + vendor.getVendorId()), vendor, null),
				HttpStatus.OK);

	}

//	@GetMapping(value = "/getUserInfo/{userId}")
//	public UserVO getUser(@PathVariable String userId) throws BusinessException {
//		return userService.findById(userId);
//
//	}

	@GetMapping(value = "/getVendorInfo/{vendorId}")
	public ResponseEntity<ResponseInfo> getVendor(@PathVariable String vendorId) throws BusinessException {
		VendorVO vendor = vendorService.findById(vendorId);
		return new ResponseEntity<ResponseInfo>(new ResponseInfo(
				new Status(HttpStatus.OK, "Successfully fetched User with userId: " + vendor.getVendorId()), vendor, null),
				HttpStatus.OK);

	}

//	@PostMapping(value = "/getAllUsers")
//	public List<UserVO> getAllUsers(@RequestBody UserVO user) throws BusinessException {
//		return userService.findAll(user);
//
//	}

	@PostMapping(value = "/getAllVendor")
	public ResponseEntity<ResponseInfo> getAllVendor(@RequestBody VendorVO vendor) throws BusinessException {
//	
		return new ResponseEntity<ResponseInfo>(
				new ResponseInfo(new Status(HttpStatus.OK, "Successfully fetched Users"), vendorService.findAll(vendor),
						null),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteVendor/{vendorId}")
	public Boolean deleteVendor(@PathVariable String vendorId) throws BusinessException {
		return vendorService.delete(vendorId);
	}
}

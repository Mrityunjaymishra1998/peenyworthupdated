package com.learnig.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.controller.VendorController;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.VendorVO;
import com.learning.management.service.VendorServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes=LearningProjectApplication.class)

public class VendorsController {
	@Autowired
	 VendorController vendorController;
	
	@MockBean
	VendorServiceImpl vendorServiceImpl;
	
	@Test
	public void addNewVendorTest() throws BusinessException {
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		Mockito.when(vendorServiceImpl.add(vendor)).thenReturn(vendor.getVendorId());
		assertEquals("Successfully added vendor : " +vendor.getVendorId(), vendorController.addNewVendor(vendor).getBody().getStatus().getMessage());
      
	}
	
	@Test
	public void addNewUserExceptionTest() throws BusinessException {
		VendorVO vendor=new VendorVO();
		Mockito.when(vendorServiceImpl.add(vendor)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"unable to add vendor","vendor body is empty"));
//		Assertions.assertThrows(RuntimeException.class, ()->usersController.addNewUser(user));
//		assertEquals("Error", usersController.addNewUser(user).getBody());
		Assertions.assertThrows(BusinessException.class, ()->vendorController.addNewVendor(vendor));
	}
	
	@Test
	public void editVendorTest() throws BusinessException {
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		Mockito.when(vendorServiceImpl.update(vendor)).thenReturn(vendor);
		assertEquals("Successfully updated User with userId: " + vendor.getVendorId(),vendorController.editVendor(vendor).getBody().getStatus().getMessage());
		
	}
	
	@Test
	public void editVendorTestException() throws BusinessException {
		VendorVO vendor=new VendorVO();
		Mockito.when(vendorServiceImpl.update(vendor)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"vendor update failed","vendor body is empty"));		
		Assertions.assertThrows(BusinessException.class, ()->vendorController.editVendor(vendor));
			
	}
	
	@Test
	public void getVendorTest() throws Exception
	{
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		Mockito.when(vendorServiceImpl.findById("1")).thenReturn(vendor);
		assertEquals(ResponseEntity.class, vendorController.getVendor("1").getClass());
				
	}

	@Test
	public void getAllVendorTest() throws Exception {
		ArrayList<VendorVO>vendorList=new ArrayList<VendorVO>();
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
		
		
		vendorList.add(vendor);
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Resgular");
		vendor.setVendorDesc("BookSeller");
	
		
		Mockito.when(vendorServiceImpl.findAll(null)).thenReturn(vendorList);
		vendorController.getAllVendor(null);
	}
	
	@Test
	public void deleteVendorTest() throws Exception {
		Mockito.when(vendorServiceImpl.delete(Mockito.anyString())).thenReturn(true);
		assertEquals(true, vendorController.deleteVendor(Mockito.anyString()));
	}
	
	
}

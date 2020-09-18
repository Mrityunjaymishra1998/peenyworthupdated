package com.learning.management.model;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;

import org.junit.runner.RunWith;




@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)

public class VendorsTest {
	@Test
	public void setterAndGetterCheck() {
		VendorVO vendor=new VendorVO();
		vendor.setVendorId("1");
		vendor.setVendorName("Tom");
		vendor.setVendorState("Active");
		vendor.setVendorType("Regular");
		vendor.setVendorDesc("BookSeller");
		
		
		assertEquals("1", vendor.getVendorId());
	    assertEquals("Tom", vendor.getVendorName());
	    assertEquals("Active", vendor.getVendorState());
	    assertEquals("Regular", vendor.getVendorType());
	   assertEquals("BookSeller", vendor.getVendorDesc());
	}

	@Test
	public void constructorCheck() {
		VendorVO vendor=new VendorVO("1","vendorName","vendorType","vendorDesc","vendorState");
		assertEquals("1", vendor.getVendorId());
	    assertEquals("vendorName", vendor.getVendorName());
	    assertEquals("vendorState", vendor.getVendorState());
	    assertEquals("vendorType", vendor.getVendorType());
	   assertEquals("vendorDesc", vendor.getVendorDesc());
		
	}
	
	@Test
	public void toStringCheck() {
		VendorVO vendor=new VendorVO("1","vendorName","vendorState","vendorType","vendorDesc");
		assertEquals(String.class, vendor.toString().getClass());
	}
}




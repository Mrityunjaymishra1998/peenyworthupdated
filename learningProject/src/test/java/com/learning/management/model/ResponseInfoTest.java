package com.learning.management.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class ResponseInfoTest {
	
	@Test
	public void setterAndGetterCheck() {
		Status status = new Status(HttpStatus.OK, "successfull");
		VendorVO vendor=new VendorVO("1","vendorName","vendorState","vendorType","vendorDesc");
		ApiError error = new ApiError("error occured");
		ResponseInfo response = new ResponseInfo();

		response.setStatus(status);
		response.setData(vendor);
		response.setApiError(error);

		assertEquals(status, response.getStatus());
		assertEquals(vendor, response.getData());
		assertEquals(error, response.getApiError());

	}
	
	@Test
	public void constructorCheck() {
		
		Status status = new Status(HttpStatus.OK, "successfull");
		VendorVO vendor=new VendorVO("1","vendorName","vendorState","vendorType","vendorDesc");
		ApiError error = new ApiError("error occured");
		ResponseInfo response = new ResponseInfo(status,vendor,error);
		
		assertEquals(HttpStatus.OK, response.getStatus().getStatus());
		//assertEquals("1", response.getData());
		assertEquals("error occured", response.getApiError().getDebugMessage());

}
	
	@Test
	public void setterAndGetterCheckNews() {
		Status status = new Status(HttpStatus.OK, "successfull");
		NewsVO news=new NewsVO("1","Technical","Image","newsLinks","newsDuration","Yes","Summary");
		ApiError error = new ApiError("error occured");
		ResponseInfo response = new ResponseInfo();

		response.setStatus(status);
		response.setData(news);
		response.setApiError(error);

		assertEquals(status, response.getStatus());
		assertEquals(news, response.getData());
		assertEquals(error, response.getApiError());

	}
	
	@Test
	public void constructorCheckNews() {
		
		Status status = new Status(HttpStatus.OK, "successfull");
		NewsVO news=new NewsVO("1","Technical","Image","newsLinks","newsDuration","Yes","Summary");
		ApiError error = new ApiError("error occured");
		ResponseInfo response = new ResponseInfo(status,news,error);
		
		assertEquals(HttpStatus.OK, response.getStatus().getStatus());
		//assertEquals("1", response.getData());
		assertEquals("error occured", response.getApiError().getDebugMessage());

}
}

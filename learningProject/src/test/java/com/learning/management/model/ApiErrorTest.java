package com.learning.management.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.exception.BusinessException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class ApiErrorTest {
	
	@Test
	public void setterAndGetterCheck() {
		ApiError error=new ApiError();
		error.setTimeStamp(LocalDateTime.parse("2020-09-03T16:27:01.524"));
		error.setDebugMessage("Setting debugMessage");
     	//error.set("Setting details");
		
		assertEquals("2020-09-03T16:27:01.524", error.getTimeStamp().toString());
		assertEquals("Setting debugMessage", error.getDebugMessage());
//		assertEquals("Setting details", error.getDetails());
	}

	@Test
	public void constructorCheck() {
		ApiError error =new ApiError("debugMessage");
		assertEquals(error.getDebugMessage(),"debugMessage");
		//assertEquals(error.getDetails(),"/details");
		
		error=new ApiError(new BusinessException(HttpStatus.NOT_ACCEPTABLE, "exceptionDebugMessage","exceptionMessage"));
		assertEquals("exceptionDebugMessage",error.getDebugMessage());
	//	assertEquals("/details",error.getDetails());
		
	}
	
	@Test
	public void toStringCheck() {
		ApiError error=new ApiError("debugMessage");
		assertEquals(String.class, error.toString().getClass());
	}
}

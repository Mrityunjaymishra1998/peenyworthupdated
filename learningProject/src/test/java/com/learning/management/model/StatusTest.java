package com.learning.management.model;

import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class StatusTest {
	@Test
	public void setterAndGetterCheck() {
		Status status = new Status();

		status.setStatus(HttpStatus.OK);
		status.setMessage("successfull");

		assertEquals(HttpStatus.OK, status.getStatus());
		assertEquals("successfull", status.getMessage());

	}

	@Test
	public void constructorCheck() {

		Status status = new Status(HttpStatus.OK, "successfull");
		
		assertEquals(HttpStatus.OK, status.getStatus());
		assertEquals("successfull", status.getMessage());
		
	}

}

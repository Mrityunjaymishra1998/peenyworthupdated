package com.learning.management.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class TokenVOTest {
	
	@Test
	public void setterAndGetterCheck() {
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		assertEquals("1", token.getUserId());
	    assertEquals("platform", token.getPlatform());
	    assertEquals("validity", token.getValidity());
	    assertEquals("Token", token.getToken());
	   
	}

	@Test
	public void constructorCheck() {
		TokenVO token=new TokenVO("1","platform","validity","Token");
		
		assertEquals("1", token.getUserId());
	    assertEquals("platform", token.getPlatform());
	    assertEquals("validity", token.getValidity());
	    assertEquals("Token", token.getToken());
	}
	
	@Test
	public void toStringCheck() {
		TokenVO token=new TokenVO("1","platform","validity","Token");
		assertEquals(String.class, token.toString().getClass());
	}


}

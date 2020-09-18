package com.learnig.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.controller.TokenController;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.NewsVO;
import com.learning.management.model.TokenVO;
import com.learning.management.service.TokenServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class TokenControllerTest {

	@Autowired
	 TokenController tokenController;
	
	@MockBean
	TokenServiceImpl tokenServiceImpl;
	

	@Test
	public void addNewNewsTest() throws BusinessException {
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
	
	
		Mockito.when(tokenServiceImpl.add(token)).thenReturn(token.getUserId());
		assertEquals("Successfully added news : " +token.getUserId(), tokenController.addNewToken(token).getBody().getStatus().getMessage());
    
	}
	
	@Test
	public void addNewNewsExceptionTest() throws BusinessException {
		
    	TokenVO token=new TokenVO();
		Mockito.when(tokenServiceImpl.add(token)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"unable to add token","token body is empty"));
//		Assertions.assertThrows(RuntimeException.class, ()->usersController.addNewUser(user));
//		assertEquals("Error", usersController.addNewUser(user).getBody());
		Assertions.assertThrows(BusinessException.class, ()->tokenController.addNewToken(token));

	
	}
	
	@Test
	public void editNewsTest() throws BusinessException {
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		
		Mockito.when(tokenServiceImpl.update(token)).thenReturn(token);
		assertEquals("Successfully updated News with newsId: " + token.getUserId(),tokenController.editToken(token).getBody().getStatus().getMessage());
		
	}
	
	@Test
	public void editNewsTestException() throws BusinessException {
	
		TokenVO token=new TokenVO();
//	
		
		Mockito.when(tokenServiceImpl.update(token)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"token update failed","news body is empty"));

		
		
		Assertions.assertThrows(BusinessException.class, ()->tokenController.editToken(token));
		
		
	}
	
	
	@Test
	public void getNewsTest() throws Exception
	{
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		Mockito.when(tokenServiceImpl.findById("1")).thenReturn(token);
		assertEquals(ResponseEntity.class, tokenController.getToken("1").getClass());
				
	
	}

	@Test
	public void getAllNewsTest() throws Exception {
		ArrayList<TokenVO>tokenList=new ArrayList<TokenVO>();
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		token.setUserId("2");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		tokenList.add(token);
		
		Mockito.when(tokenServiceImpl.findAll(null)).thenReturn(tokenList);
		tokenController.getAllToken(null);
	}
	
	@Test
	public void deleteNewsTest() throws Exception {
		Mockito.when(tokenServiceImpl.delete(Mockito.anyString())).thenReturn(true);
		assertEquals(true, tokenController.deleteToken(Mockito.anyString()));
	}
	

}

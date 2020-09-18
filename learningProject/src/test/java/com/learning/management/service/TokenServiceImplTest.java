package com.learning.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.doa.TokenRepository;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.TokenVO;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)

public class TokenServiceImplTest {

	@Autowired
	TokenService tokenService;
	@MockBean
	TokenRepository tokenRepo;
	
	@Test
	public void addTest() throws Exception {
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		Mockito.when(tokenRepo.save(token)).thenReturn(token);
		tokenService.add(token);
		assertEquals(token.getUserId(),tokenService.add(token));
	}
	
	@Test
	public void getTest() throws Exception{
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		Mockito.when(tokenRepo.findById("1")).thenReturn(Optional.of(token));
		assertEquals(token,tokenService.findById("1"));
		assertEquals(token.getUserId(), tokenService.findById("1").getUserId());
		
	}
	@Test
	public void getExceptionTest() throws Exception {
		
		Mockito.when(tokenRepo.findById("1")).thenReturn(null);
		
		Assertions.assertThrows( Exception.class, ()->tokenService.findById("1"));
	
	}
	
	
	@Test
	public void updateTest() throws Exception{
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
        
		Mockito.when(tokenRepo.save(token)).thenReturn(token);
		TokenVO expectedToken=tokenService.update(token);
		assertEquals(token,expectedToken );
		
		
		
	}
	
	@Test
	public void deleteTest() throws Exception{
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		Mockito.when(tokenRepo.findById("1")).thenReturn(Optional.of(token));
		assertEquals(null, tokenService.delete("1"));
		
	}
	@Test
	public void delete_Exception_Test() throws Exception{
		Mockito.when(tokenRepo.findById("1")).thenReturn(null);
		Assertions.assertThrows(Exception.class, () -> tokenService.delete("1"));

		Optional<TokenVO> emptytoken = Optional.empty();
		Mockito.when(tokenRepo.findById("1")).thenReturn(emptytoken);
		Assertions.assertThrows(BusinessException.class, () -> tokenService.delete("1"));
		
	}

	
	@Test
	public void addAllTest() throws Exception{
		ArrayList<TokenVO>tokenList=new ArrayList<TokenVO>();
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		tokenList.add(token);
		
	   tokenService.addAll(tokenList);

	}
	@Test
	public void findAllTest() throws Exception {
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
		
		
		Example<TokenVO> newsVOExample = Example.of(token);
		Mockito.when(tokenRepo.findAll(newsVOExample)).thenReturn(tokenList);

		assertEquals(tokenList, tokenService.findAll(token));

}
	
	@Test
	public void addExceptionTest() {
		
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
	//	Mockito.when(newsRepo.save(null)).thenReturn(new NullPointerException());
	//	Assertions.assertThrows(BusinessException.class, ()->newsService.add(news));
		
		Mockito.when(tokenRepo.save(token)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> tokenService.add(token));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
		
		
	
		

		
	}
	@Test
	public void updateExceptionTest() {
		
		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
		
		
		Mockito.when(tokenRepo.save(token)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> tokenService.update(token));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
		
		
	}
	@Test
	public void findAllExceptionTest() {
		

		TokenVO token=new TokenVO();
		token.setUserId("1");
		token.setPlatform("platform");
		token.setValidity("validity");
		token.setToken("Token");
		
        
		Example<TokenVO> tokenVOExample = Example.of(token);
		Mockito.when(tokenRepo.findAll(tokenVOExample)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> tokenService.findAll(token));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
	}
}

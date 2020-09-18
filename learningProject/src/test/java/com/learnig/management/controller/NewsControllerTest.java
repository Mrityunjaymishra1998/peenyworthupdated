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
import com.learning.management.controller.NewsController;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.NewsVO;
import com.learning.management.service.NewsServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ContextConfiguration(classes=LearningProjectApplication.class)

public class NewsControllerTest {
	@Autowired
	 NewsController newsController;
	
	@MockBean
	NewsServiceImpl newsServiceImpl;
	

	@Test
	public void addNewNewsTest() throws BusinessException {
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
	
		
	
		Mockito.when(newsServiceImpl.add(news)).thenReturn(news.getNewsId());
		assertEquals("Successfully added news : " +news.getNewsId(), newsController.addNewNews(news).getBody().getStatus().getMessage());
     
	}
	
	@Test
	public void addNewNewsExceptionTest() throws BusinessException {
		NewsVO news=new NewsVO();
//		
		Mockito.when(newsServiceImpl.add(news)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"unable to add news","news body is empty"));
//		Assertions.assertThrows(RuntimeException.class, ()->usersController.addNewUser(user));
//		assertEquals("Error", usersController.addNewUser(user).getBody());
		Assertions.assertThrows(BusinessException.class, ()->newsController.addNewNews(news));

	
	}
	
	@Test
	public void editNewsTest() throws BusinessException {
		
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		
		Mockito.when(newsServiceImpl.update(news)).thenReturn(news);
		assertEquals("Successfully updated News with newsId: " + news.getNewsId(),newsController.editNews(news).getBody().getStatus().getMessage());
		
	}
	
	@Test
	public void editNewsTestException() throws BusinessException {
		NewsVO news=new NewsVO();
	
//	
		
		Mockito.when(newsServiceImpl.update(news)).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST,"news update failed","news body is empty"));

		
		
		Assertions.assertThrows(BusinessException.class, ()->newsController.editNews(news));
		
		
	}
	
	
	@Test
	public void getNewsTest() throws Exception
	{
		
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		Mockito.when(newsServiceImpl.findById("1")).thenReturn(news);
		assertEquals(ResponseEntity.class, newsController.getNews("1").getClass());
				
	
	}

	@Test
	public void getAllNewsTest() throws Exception {
		ArrayList<NewsVO>newsList=new ArrayList<NewsVO>();
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
        news.setNewsId("2");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		newsList.add(news);
		
		Mockito.when(newsServiceImpl.findAll(null)).thenReturn(newsList);
		newsController.getAllNews(null);
	}
	
	@Test
	public void deleteNewsTest() throws Exception {
		Mockito.when(newsServiceImpl.delete(Mockito.anyString())).thenReturn(true);
		assertEquals(true, newsController.deleteNews(Mockito.anyString()));
	}
	

}

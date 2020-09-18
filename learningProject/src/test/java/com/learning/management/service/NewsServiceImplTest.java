package com.learning.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;
import com.learning.management.doa.NewsRepository;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.NewsVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)

public class NewsServiceImplTest {

	@Autowired
	NewsService newsService;
	@MockBean
	NewsRepository newsRepo;
	
	@Test
	public void addTest() throws Exception {
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");	 
		Mockito.when(newsRepo.save(news)).thenReturn(news);
		newsService.add(news);
		assertEquals(news.getNewsId(),newsService.add(news));
	}
	
	@Test
	public void getTest() throws Exception{
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		Mockito.when(newsRepo.findById("1")).thenReturn(Optional.of(news));
		assertEquals(news,newsService.findById("1"));
		assertEquals(news.getNewsId(), newsService.findById("1").getNewsId());
		
	}
	@Test
	public void getExceptionTest() throws Exception {
		
		Mockito.when(newsRepo.findById("1")).thenReturn(null);
		
		Assertions.assertThrows( Exception.class, ()->newsService.findById("1"));
	
	}
	
	
	@Test
	public void updateTest() throws Exception{
		
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
        
		Mockito.when(newsRepo.save(news)).thenReturn(news);
		NewsVO expectedNews=newsService.update(news);
		assertEquals(news,expectedNews );
		
		
		
	}
	
	@Test
	public void deleteTest() throws Exception{
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		Mockito.when(newsRepo.findById("1")).thenReturn(Optional.of(news));
		assertEquals(null, newsService.delete("1"));
		
	}
	@Test
	public void delete_Exception_Test() throws Exception{
		Mockito.when(newsRepo.findById("1")).thenReturn(null);
		Assertions.assertThrows(Exception.class, () -> newsService.delete("1"));

		Optional<NewsVO> emptynews = Optional.empty();
		Mockito.when(newsRepo.findById("1")).thenReturn(emptynews);
		Assertions.assertThrows(BusinessException.class, () -> newsService.delete("1"));
		
	}

	
	@Test
	public void addAllTest() throws Exception{
		ArrayList<NewsVO>newsList=new ArrayList<NewsVO>();
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		newsList.add(news);
		
	   newsService.addAll(newsList);

	}
	@Test
	public void findAllTest() throws Exception {
		ArrayList<NewsVO>newsList=new ArrayList<NewsVO>();
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		newsList.add(news);

		
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");

		newsList.add(news);
		
		
		Example<NewsVO> newsVOExample = Example.of(news);
		Mockito.when(newsRepo.findAll(newsVOExample)).thenReturn(newsList);

		assertEquals(newsList, newsService.findAll(news));

}
	
	@Test
	public void addExceptionTest() {
		
		
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
	//	Mockito.when(newsRepo.save(null)).thenReturn(new NullPointerException());
	//	Assertions.assertThrows(BusinessException.class, ()->newsService.add(news));
		
		Mockito.when(newsRepo.save(news)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> newsService.add(news));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
		
		
	
		

		
	}
	@Test
	public void updateExceptionTest() {
		
	
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
		
		
		
		Mockito.when(newsRepo.save(news)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> newsService.update(news));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
		
		
	}
	@Test
	public void findAllExceptionTest() {
		

		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");
        
		Example<NewsVO> newsVOExample = Example.of(news);
		Mockito.when(newsRepo.findAll(newsVOExample)).thenThrow(NullPointerException.class);
		BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> newsService.findAll(news));
		assertEquals(NullPointerException.class, ex.getCause().getClass());
		
	}
}

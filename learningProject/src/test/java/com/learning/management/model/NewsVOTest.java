package com.learning.management.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.management.LearningProjectApplication;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LearningProjectApplication.class)
public class NewsVOTest {

	@Test
	public void setterAndGetterCheck() {
		NewsVO news=new NewsVO();
		news.setNewsId("1");
		news.setNewsDesc("newsDesc");
		news.setNewsDuration("newsDuration");
		news.setNewsImage("newsImage");
		news.setNewsState("newsState");
		news.setNewsSummary("newsSummary");
        news.setNewsLinks("newsLinks");		
		
		assertEquals("1", news.getNewsId());
	    assertEquals("newsDesc", news.getNewsDesc());
	    assertEquals("newsImage", news.getNewsImage());
	    assertEquals("newsDuration", news.getNewsDuration());
	   assertEquals("newsSummary", news.getNewsSummary());
	   assertEquals("newsLinks", news.getNewsLinks());
	   assertEquals("newsState", news.getNewsState());
	   
	}

	@Test
	public void constructorCheck() {
		NewsVO news=new NewsVO("1","newsImage","newsLinks","newsState","newsDesc","newsSummary","newsDuration");
		assertEquals("1", news.getNewsId());
	    assertEquals("newsDesc", news.getNewsDesc());
	    assertEquals("newsImage", news.getNewsImage());
	    assertEquals("newsState", news.getNewsState());
	   assertEquals("newsSummary", news.getNewsSummary());
	   assertEquals("newsLinks",news.getNewsLinks());
	   assertEquals("newsSummary", news.getNewsSummary());
		
	}
	
	@Test
	public void toStringCheck() {
		NewsVO news=new NewsVO("1","newsDuration","newsDesc","newsImage","newsState","newsSummary","newsLinks");
		assertEquals(String.class, news.toString().getClass());
	}

}

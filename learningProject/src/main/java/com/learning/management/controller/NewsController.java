package com.learning.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.management.exception.BusinessException;
import com.learning.management.model.ResponseInfo;
import com.learning.management.model.Status;
import com.learning.management.model.NewsVO;
import com.learning.management.service.NewsService;

@Component
@RestController
@RequestMapping(value = "/news")

public class NewsController {
	@Autowired(required=true)
	NewsService newsService;

	

	@PostMapping(value = "/addNewNews")
	public ResponseEntity<ResponseInfo> addNewNews(@RequestBody NewsVO news) throws BusinessException {

     newsService.add(news);
//	
		return new ResponseEntity<ResponseInfo>(
				new ResponseInfo(new Status(HttpStatus.OK, "Successfully added news : " + news.getNewsId() ),  news, null),
				HttpStatus.OK);

	}

	@PostMapping(value = "/editNews")
	public ResponseEntity<ResponseInfo> editNews(@RequestBody NewsVO news) throws BusinessException {

		newsService.update(news);
//	
		return new ResponseEntity<ResponseInfo>(new ResponseInfo(
				new Status(HttpStatus.OK, "Successfully updated News with newsId: " + news.getNewsId()),news, null),
				HttpStatus.OK);

	}

//	@GetMapping(value = "/getUserInfo/{userId}")
//	public UserVO getUser(@PathVariable String userId) throws BusinessException {
//		return userService.findById(userId);
//
//	}

	@GetMapping(value = "/getNewsInfo/{newsId}")
	public ResponseEntity<ResponseInfo> getNews(@PathVariable String newsId) throws BusinessException {
		NewsVO news =newsService.findById(newsId);
		return new ResponseEntity<ResponseInfo>(new ResponseInfo(
				new Status(HttpStatus.OK, "Successfully fetched News with newsId: " + news.getNewsId()), news, null),
				HttpStatus.OK);

	}

//	@PostMapping(value = "/getAllUsers")
//	public List<UserVO> getAllUsers(@RequestBody UserVO user) throws BusinessException {
//		return userService.findAll(user);
//
//	}

	@PostMapping(value = "/getAllNews")
	public ResponseEntity<ResponseInfo> getAllNews(@RequestBody  NewsVO news) throws BusinessException {

		return new ResponseEntity<ResponseInfo>(
				new ResponseInfo(new Status(HttpStatus.OK, "Successfully fetched News"), newsService.findAll(news),
						null),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteNews/{newsId}")
	public Boolean deleteNews(@PathVariable String newsId) throws BusinessException {
		return newsService.delete(newsId);
	}
}



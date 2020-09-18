package com.learning.management.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.management.exception.BusinessException;
import com.learning.management.doa.NewsRepository;
import com.learning.management.model.NewsVO;


@Transactional
@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsRepository newsRepos;

	@Override
	public NewsVO findById(String newsId) throws BusinessException {

		try {
			Optional<NewsVO> user = newsRepos.findById(newsId);

			if (user.isPresent()) {
				return user.get();
			} else {
				throw new BusinessException(HttpStatus.NOT_FOUND, "news not found",
						"news with news id:" + newsId + " is not found");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {

			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}

	}
	

	@Override
	public NewsVO update(NewsVO news) throws BusinessException {
		try {
			if ((news.getNewsId()==null) && (news.getNewsDesc() == null) && (news.getNewsImage() == null)
					&& (news.getNewsDuration() == null) &&(news.getNewsLinks()== null) && (news.getNewsState()==null)) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "news update failed", "news body is empty");
			}
			return newsRepos.save(news);
		}  catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Boolean delete(String newsId) throws BusinessException  {
		try {
			Optional<NewsVO> news = newsRepos.findById(newsId);
			if (!news.isPresent()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "delete failed",
						" with news id:" + newsId + " is not found");
			}
			newsRepos.delete(news.get());
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
		return null;
		
		

	}

	@Override
	public String add(NewsVO news) throws BusinessException {
		try {
			if ((news.getNewsId()==null) && (news.getNewsDesc() == null) && (news.getNewsImage() == null)
					&& (news.getNewsDuration() == null) &&(news.getNewsLinks()== null) && (news.getNewsState()==null)){

			throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add news", "news body is empty");

		}
			news = newsRepos.save(news);
			return news.getNewsId();
		}catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<NewsVO> addAll(List<NewsVO> newsList) throws BusinessException{

		try {
			if (newsList.isEmpty()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add news", "userList  is empty");	
			}
			return newsRepos.saveAll(newsList);
//			return true;
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}


	}

	@Override
	public List<NewsVO> findAll(NewsVO news) throws BusinessException {
		try {
			Example<NewsVO> newsVOExample = Example.of(news);
			return newsRepos.findAll(newsVOExample);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

}

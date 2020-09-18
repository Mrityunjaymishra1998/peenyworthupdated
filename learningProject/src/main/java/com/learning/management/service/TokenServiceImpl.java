package com.learning.management.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.management.doa.TokenRepository;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.TokenVO;
import com.learning.management.model.ValueObject;

@Transactional
@Service
public class TokenServiceImpl implements TokenService {

	
	@Autowired
   TokenRepository tokenRepos;

	@Override
	public TokenVO findById(String tokenId) throws BusinessException {

		try {
			Optional<TokenVO> token = tokenRepos.findById(tokenId);

			if (token.isPresent()) {
				return token.get();
			} else {
				throw new BusinessException(HttpStatus.NOT_FOUND, "token not found",
						"news with news id:" + tokenId + " is not found");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {

			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}

	}
	

	@Override
	public TokenVO update(TokenVO token) throws BusinessException {
		try {
			if ((token.getUserId()==null) && (token.getToken() == null) && (token.getPlatform() == null)
					&& (token.getValidity() == null)) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "token update failed", "token body is empty");
			}
			return tokenRepos.save(token);
		}  catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Boolean delete(String tokenId) throws BusinessException  {
		try {
			Optional<TokenVO> token = tokenRepos.findById(tokenId);
			if (!token.isPresent()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "delete failed",
						" with news id:" + tokenId + " is not found");
			}
			tokenRepos.delete(token.get());
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
		return null;
		
		

	}

	@Override
	public String add(TokenVO token) throws BusinessException {
		try {
			if ((token.getUserId()==null) && (token.getToken() == null) && (token.getPlatform() == null)
					&& (token.getValidity() == null)){

			throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add token", "token body is empty");

		}
			token = tokenRepos.save(token);
			return token.getUserId();
		}catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<TokenVO> addAll(List<TokenVO> tokenList) throws BusinessException{

		try {
			if (tokenList.isEmpty()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add token", "tokenList  is empty");	
			}
			return tokenRepos.saveAll(tokenList);
//			return true;
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}


	}

	@Override
	public List<TokenVO> findAll(TokenVO token) throws BusinessException {
		try {
			Example<TokenVO> tokenVOExample = Example.of(token);
			return tokenRepos.findAll(tokenVOExample);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}






	
}

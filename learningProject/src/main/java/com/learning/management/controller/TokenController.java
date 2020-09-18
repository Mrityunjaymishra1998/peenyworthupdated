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
import com.learning.management.model.TokenVO;
import com.learning.management.model.ResponseInfo;
import com.learning.management.model.Status;
import com.learning.management.service.TokenService;

@Component
@RestController
@RequestMapping(value = "/token")
public class TokenController {



		@Autowired(required=true)
		TokenService tokenService;

		

		@PostMapping(value = "/addNewToken")
		public ResponseEntity<ResponseInfo> addNewToken(@RequestBody TokenVO token) throws BusinessException {

	     tokenService.add(token);
	//	
			return new ResponseEntity<ResponseInfo>(
					new ResponseInfo(new Status(HttpStatus.OK, "Successfully added token : " + token.getUserId() ),  token, null),
					HttpStatus.OK);

		}

		@PostMapping(value = "/editToken")
		public ResponseEntity<ResponseInfo> editToken(@RequestBody TokenVO token) throws BusinessException {

			tokenService.update(token);
	//	
			return new ResponseEntity<ResponseInfo>(new ResponseInfo(
					new Status(HttpStatus.OK, "Successfully updated Token with tokenId: " + token.getUserId()),token, null),
					HttpStatus.OK);

		}

//		@GetMapping(value = "/getUserInfo/{userId}")
//		public UserVO getUser(@PathVariable String userId) throws BusinessException {
//			return userService.findById(userId);
	//
//		}

		@GetMapping(value = "/getTokenInfo/{tokenId}")
		public ResponseEntity<ResponseInfo> getToken(@PathVariable String tokenId) throws BusinessException {
			TokenVO token =tokenService.findById(tokenId);
			return new ResponseEntity<ResponseInfo>(new ResponseInfo(
					new Status(HttpStatus.OK, "Successfully fetched News with tokenId: " + token.getUserId()),token, null),
					HttpStatus.OK);

		}

//		@PostMapping(value = "/getAllUsers")
//		public List<UserVO> getAllUsers(@RequestBody UserVO user) throws BusinessException {
//			return userService.findAll(user);
	//
//		}

		@PostMapping(value = "/getAllToken")
		public ResponseEntity<ResponseInfo> getAllToken(@RequestBody TokenVO token) throws BusinessException {

			return new ResponseEntity<ResponseInfo>(
					new ResponseInfo(new Status(HttpStatus.OK, "Successfully fetched token"), tokenService.findAll(token),
							null),
					HttpStatus.OK);
		}

		@DeleteMapping(value = "/deleteToken/{tokenId}")
		public Boolean deleteToken(@PathVariable String tokenId) throws BusinessException {
			return tokenService.delete(tokenId);
		}
	}




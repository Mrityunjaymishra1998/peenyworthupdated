package com.learning.management.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class ResponseInfo {

	
//	private HttpStatus status;
	private Status status;
//	private String message;
	private Object data;
	private ApiError apiError;
}

package com.learning.management.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ApiError {
	
//	private HttpStatus status;
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss",shape=JsonFormat.Shape.STRING )
	private LocalDateTime timeStamp;
//	private String message;
	private String debugMessage;
//	private String details;

	public ApiError() {
		super();
		this.timeStamp=LocalDateTime.now();
	}

//	public ApiError(HttpStatus status) {
//		this();
//		this.status = status;
//	}

//	public ApiError(HttpStatus status,Throwable ex) {
//		this();
//		this.message="Unknown Execption Occured :"+ex.getMessage();
//		this.debugMessage=ex.getLocalizedMessage();
//		this.status = status;
//	}
//
//	public ApiError(HttpStatus status, String message,Throwable ex) {
//		this();
//		this.status = status;
//		this.message = message;
//		this.debugMessage=ex.getLocalizedMessage();
//	}
//	
//	public ApiError(HttpStatus status, Throwable ex,String details) {
//		this();
//		this.status = status;
//		this.message = ex.getMessage();
//		this.debugMessage=ex.getLocalizedMessage();
//		this.details=details;
//	}


	public ApiError(Throwable ex) {
		this();
		this.debugMessage=ex.getLocalizedMessage();
//		this.details=details;
	}
	
	public ApiError(String debugMessage) {
		this();
		this.debugMessage=debugMessage;
//		this.details=details;
	}

	@Override
	public String toString() {
		return "ApiError [timeStamp=" + timeStamp + ", debugMessage=" + debugMessage + "]";
	}

	
	
}



	
	


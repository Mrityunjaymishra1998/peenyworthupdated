package com.learning.management.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessException extends Exception{

	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus status;
	private String debugMessage;
	private Throwable throwable;

	public BusinessException() {
		super();
	}

	public BusinessException(HttpStatus status, String message, String debugMessage, Throwable th) {
//		super();
		super(th);
		this.message = message;
		this.status = status;
		this.debugMessage = debugMessage;
		this.throwable=th;
	
	}

	public BusinessException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public BusinessException(HttpStatus status, String message, String debugMessage) {
		super();
		this.message = message;
		this.status = status;
		this.debugMessage = debugMessage;
	}

	@Override
	public String toString() {
		return "BusinessException [message=" + message + ", status=" + status + ", debugMessage=" + debugMessage + "]";
	}
}

package com.learning.management.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.management.model.ApiError;
import com.learning.management.model.ResponseInfo;
import com.learning.management.model.Status;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class BusinessRestExceptionalHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(ex);
		return buildResponseEntity(
				new ResponseInfo(new Status(HttpStatus.BAD_REQUEST, "Malformed Json request"), null, apiError));
	}

	public ResponseEntity<Object> buildResponseEntity(ResponseInfo responseInfo) {

		return new ResponseEntity<Object>(responseInfo, responseInfo.getStatus().getStatus());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessExceptions(BusinessException ex) {
//		ApiError apiError = new ApiError(ex.getStatus(), ex.getDebugMessage(), ex, request.getDescription(false));
		ApiError apiError = new ApiError(ex.getDebugMessage());
		return buildResponseEntity(new ResponseInfo(new Status(ex.getStatus(), ex.getMessage()), null, apiError));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		ApiError apiError = new ApiError(ex);
		return buildResponseEntity(
				new ResponseInfo(new Status(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), null, apiError));
	}


}

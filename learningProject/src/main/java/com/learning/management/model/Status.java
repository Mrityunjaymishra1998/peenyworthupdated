package com.learning.management.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

	HttpStatus status;
	String message;

	@Override
	public String toString() {
		return "Status [status=" + status + ", message=" + message + "]";
	}
}


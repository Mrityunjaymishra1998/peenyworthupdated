package com.learning.management.model;


import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class ValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createdBy;
	private String updatedBy;
	private String createdOnDateTime;
	private String updatedOnDateTime;

}

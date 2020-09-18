package com.learning.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
@Entity
@Table(name="token")
public class TokenVO extends ValueObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public String userId;
	@Column
	public String platform;
	@Column
	public String token;
	@Column
	public String validity;

}

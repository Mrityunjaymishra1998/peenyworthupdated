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

@Table(name="News")
public class NewsVO extends ValueObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public String newsId;
	@Column()
	public String newsImage;
	@Column
	public String newsLinks;
	@Column
	public String newsState;
	@Column
	public String newsDesc;
	@Column
	public String newsSummary;
	@Column
	public String newsDuration;
	
	

}

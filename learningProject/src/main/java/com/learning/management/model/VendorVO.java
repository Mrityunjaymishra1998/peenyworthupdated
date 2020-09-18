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
@Table(name="vendor")

public class VendorVO extends ValueObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String vendorId;
	@Column
	private String vendorName;
	@Column
	private  String vendorType;
	@Column
	private String vendorDesc;
    @Column
	private String vendorState;
    
    

}

package com.learning.management.service;

import java.util.List;

import com.learning.management.exception.BusinessException;
import com.learning.management.model.ValueObject;




public interface BusinessService<T extends ValueObject> {
	public String add(T object) throws BusinessException;

	public T update(T object) throws BusinessException;

	public T findById(String id) throws BusinessException;

	public Boolean delete(String id) throws BusinessException;

	public List<T> addAll(List<T> usersList) throws BusinessException;

	public List<T> findAll(T object) throws BusinessException;


}

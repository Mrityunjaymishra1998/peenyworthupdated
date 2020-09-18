package com.learning.management.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learning.management.doa.VendorRepository;
import com.learning.management.exception.BusinessException;
import com.learning.management.model.VendorVO;
@Transactional
@Service
public class VendorServiceImpl  implements VendorService{
	@Autowired
	VendorRepository vendorRepos;

	@Override
	public VendorVO findById(String vendorId) throws BusinessException {

		try {
			Optional<VendorVO> user = vendorRepos.findById(vendorId);

			if (user.isPresent()) {
				return user.get();
			} else {
				throw new BusinessException(HttpStatus.NOT_FOUND, "user not found",
						"user with user id:" + vendorId + " is not found");
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {

			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}

	}
	

	@Override
	public VendorVO update(VendorVO vendor) throws BusinessException {
		try {
			if ((vendor.getVendorId() == null) && (vendor.getVendorDesc() == null) && (vendor.getVendorType() == null)
					&& (vendor.getVendorState() == null) &&(vendor.getVendorDesc()== null)) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "user update failed", "user body is empty");
			}
			return vendorRepos.save(vendor);
		}  catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Boolean delete(String vendorId) throws BusinessException  {
		try {
			Optional<VendorVO> vendor = vendorRepos.findById(vendorId);
			if (!vendor.isPresent()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "delete failed",
						" with vendor id:" + vendorId + " is not found");
			}
			vendorRepos.delete(vendor.get());
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
		return null;
		
		

	}

	@Override
	public String add(VendorVO vendor) throws BusinessException {
		try {
			if ((vendor.getVendorId() == null) && (vendor.getVendorDesc() == null) && (vendor.getVendorType() == null)
					&& (vendor.getVendorState() == null) &&(vendor.getVendorDesc()== null)) {

			throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add user", "user body is empty");

		}
			vendor = vendorRepos.save(vendor);
			return vendor.getVendorId();
		}catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<VendorVO> addAll(List<VendorVO> vendorList) throws BusinessException{

		try {
			if (vendorList.isEmpty()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "unable to add users", "userList  is empty");	
			}
			return vendorRepos.saveAll(vendorList);
//			return true;
		} catch (BusinessException e) {
			throw new BusinessException(e.getStatus(), e.getMessage(), e.getDebugMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}


	}

	@Override
	public List<VendorVO> findAll(VendorVO vendor) throws BusinessException {
		try {
			Example<VendorVO> vendorVOExample = Example.of(vendor);
			return vendorRepos.findAll(vendorVOExample);
		} catch (Exception e) {
			throw new BusinessException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e.getLocalizedMessage(), e);
		}
	}

}



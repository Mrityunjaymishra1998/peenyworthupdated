package com.learning.management.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;

import com.learning.management.model.VendorVO;

@Service
public interface VendorRepository extends JpaRepository<VendorVO, String>, QueryByExampleExecutor<VendorVO> {

}

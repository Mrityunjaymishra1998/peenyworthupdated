package com.learning.management.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;
import com.learning.management.model.TokenVO;

@Service
public  interface TokenRepository extends JpaRepository<TokenVO, String>, QueryByExampleExecutor<TokenVO>  {

}

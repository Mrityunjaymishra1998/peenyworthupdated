package com.learning.management.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;

import com.learning.management.model.NewsVO;

@Service
public interface NewsRepository extends JpaRepository<NewsVO, String>, QueryByExampleExecutor<NewsVO>{
    
}

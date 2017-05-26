package com.nlu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.DoKho;
import com.nlu.repository.DoKhoRepository;
@Service
@Transactional
public class DoKhoServiceImpl  implements DoKhoService{
  @Autowired
   DoKhoRepository doKhoRepository;
	@Override
	public Page<DoKho> list(Pageable page) {
		// TODO Auto-generated method stub
		return doKhoRepository.list(page);
	}

}

package com.nlu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.Khoa;
import com.nlu.repository.KhoaRepository;

@Service
@Transactional
public class KhoaServiceImpl implements KhoaService{
  @Autowired
    KhoaRepository khoaRepository ;
	@Override
	public Page<Khoa> list(String search , Pageable pageRequest) {
		// TODO Auto-generated method stub
		return khoaRepository.list( search , pageRequest);
	}
	

}

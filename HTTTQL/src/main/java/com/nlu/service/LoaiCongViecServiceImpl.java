package com.nlu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nlu.dao.entity.LoaiCongViec;
import com.nlu.repository.LoaiCongViecRepository;

@Service

public class LoaiCongViecServiceImpl implements LoaiCongViecService {
  @Autowired
   LoaiCongViecRepository loaiCongViecRepository ;
	@Override
	public Page<LoaiCongViec> list(String search, Pageable page) {
		// TODO Auto-generated method stub
		return loaiCongViecRepository.list(search, page);
	}
	
	@Override
	public List<LoaiCongViec> getAll() {
		return loaiCongViecRepository.findAll();
	}

}

package com.nlu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.LoaiCongViec;

public interface LoaiCongViecService {
	public Page<LoaiCongViec> list(String name  , Pageable page);
	/**
	 * Lấy tất cả loại công việc
	 * @return
	 */
	public List<LoaiCongViec>getAll();
	
}

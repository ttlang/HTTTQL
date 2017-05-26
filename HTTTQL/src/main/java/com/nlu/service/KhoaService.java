package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.Khoa;

public interface KhoaService {
	public Page<Khoa> list( String sreach, Pageable pageRequest);

}

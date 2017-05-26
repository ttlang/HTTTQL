package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.DoKho;

public interface DoKhoService {
	public Page<DoKho> list(Pageable page);

}

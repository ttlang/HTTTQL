package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.MonHoc;

public interface MonHocService {
	public Page<MonHoc> list( String sreach, Pageable pageRequest);
	/**
	 * Lấy môn học từ mã môn học
	 * @param maMonHoc mã môn học
	 * @return
	 */
	public MonHoc getMonHocByMaMonHoc(int maMonHoc);
	public MonHoc findOndById(Integer mamon);
}

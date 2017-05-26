package com.nlu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.BoMon;

public interface BoMonService {
	public Page<BoMon> list( String sreach, Pageable pageRequest);
	/**
	 * lấy danh sách môn học từ mã bộ môn
	 * @param mabomon mã bộ môn
	 * @return List<MonHoc>result
	 */
	public List<BoMon>getListBoMonByMaGV(int magv);
	
	/**
	 * Lấy bộ môn dựa vào mã bộ môn
	 * @param maBoMon mã bộ môn
	 * @return
	 */
	public BoMon getBoMonbyMaBoMom(int maBoMon);
}

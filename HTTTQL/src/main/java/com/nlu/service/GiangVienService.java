package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.GiangVien;

public interface GiangVienService {
	public Page<GiangVien> list( String sreach, Pageable pageRequest);

	public String loginResMsg(String username, String md5Hash);


	public GiangVien giengVienById(int id) ;
	public boolean save(GiangVien gv) ;
	
}

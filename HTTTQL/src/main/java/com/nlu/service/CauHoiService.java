package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.entity.CauHoi;

public interface CauHoiService {
	public Page<CauHoi> list(String searchnoidung , Pageable page);
	
	public boolean  themCauHoi(CauHoiDao cauhoi); 
	
	 public boolean themDapAn(DapAnDao dapan);
	 public boolean xoa(int id );
	 public CauHoi findOne(int id ) ;

	public boolean save(CauHoi cauhoi);

}

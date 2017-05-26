package com.nlu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.CauTrucDeThi;
import com.nlu.dao.entity.ChuongMuc;
import com.nlu.repository.ChuongMucRepository;

@Service
@Transactional
public class ChuongMucServiceImpl implements ChuongMucService {
	@Autowired
	ChuongMucRepository chuongMucRepository;

	@Override
	public Page<ChuongMuc> list(String tieude, int ch, Pageable page) {
		// TODO Auto-generated method stub
		return chuongMucRepository.list(tieude, ch, page);

	}

}

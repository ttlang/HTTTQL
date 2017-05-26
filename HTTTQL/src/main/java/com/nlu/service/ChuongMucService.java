package com.nlu.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.ChuongMuc;

public interface ChuongMucService {
	public Page<ChuongMuc> list(String tieude, int ch, Pageable page);

}

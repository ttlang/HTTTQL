package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nlu.dao.entity.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {

	@Query("select m from MonHoc m where m.tenmon like :x")
	public Page<MonHoc> list(@Param("x") String sreach , Pageable page);	 
}

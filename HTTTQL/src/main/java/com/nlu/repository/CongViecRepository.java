package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nlu.dao.entity.CongViec;

public interface CongViecRepository extends JpaRepository<CongViec, Integer> {
	@Query(" select c from CongViec c where c.noidungcv like :x ")
	public Page<CongViec> list(@Param("x") String search , Pageable page) ;
}

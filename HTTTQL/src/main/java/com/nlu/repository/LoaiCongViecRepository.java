package com.nlu.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nlu.dao.entity.LoaiCongViec;
public interface LoaiCongViecRepository extends JpaRepository<LoaiCongViec, Integer> {
	@Query("select l  from LoaiCongViec l where l.tenloaicv like :x ")
	public Page<LoaiCongViec> list(@Param("x") String search ,Pageable page);
}

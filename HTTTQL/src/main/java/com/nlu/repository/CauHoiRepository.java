package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.CauHoi;

@Transactional(readOnly = true)
public interface CauHoiRepository extends JpaRepository<CauHoi, Integer> {
	@Query("select c from CauHoi c where c.noidung like :x")
	public Page<CauHoi> list(@Param("x") String searchNoidung, Pageable page);
}

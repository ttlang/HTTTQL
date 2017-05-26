package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nlu.dao.entity.Khoa;


public interface KhoaRepository extends JpaRepository<Khoa,Integer> {
	@Query("select k from Khoa k where k.tenkhoa like :x")
	public Page<Khoa> list(@Param("x") String sreach , Pageable page);	 
}

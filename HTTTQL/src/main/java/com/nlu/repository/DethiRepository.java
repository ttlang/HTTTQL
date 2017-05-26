package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.dao.entity.DeThi;

public interface DethiRepository extends JpaRepository<DeThi, Integer> {
	@Query(" select d from DeThi d ")
	public Page<DeThi> list(Pageable page) ;

}

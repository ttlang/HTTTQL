package com.nlu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.DapAn;
@Transactional(readOnly = true) 
public interface DapAnRepository extends JpaRepository<DapAn, Integer> {

}

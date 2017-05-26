package com.nlu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nlu.dao.entity.DoKho;

public interface DoKhoRepository extends JpaRepository<DoKho, Integer>{
    @Query(" select d from DoKho d")
    public Page<DoKho> list(Pageable page);
}

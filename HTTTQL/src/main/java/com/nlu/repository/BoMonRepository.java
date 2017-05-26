package com.nlu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nlu.dao.entity.BoMon;

public interface BoMonRepository extends JpaRepository<BoMon, Integer> {
	@Query("select b from BoMon b  where b.tenbomon like :x ")
	public Page<BoMon> list(@Param("x") String sreach, Pageable page);

	/**
	 * Lấy danh sách các môn học thuộc mã bộ môn
	 * 
	 * @param maBoMon
	 *            mã bộ môn
	 * @return List<MonHoc>
	 */
	@Query("FROM BoMon b WHERE b.mabomon=:MABOMON ")
	public List<BoMon> getMonHocFromBoMon(@Param("MABOMON") int maBoMon);

	/**
	 * Lấy danh sách bộ môn mà giáo viên quản lýs
	 * 
	 * @param magv
	 *            mã giáo viên
	 * @return
	 */
	@Query("FROM BoMon b WHERE b.magv=:magv")
	public List<BoMon> getListBoMonByMaGV(@Param("magv") int magv);
}

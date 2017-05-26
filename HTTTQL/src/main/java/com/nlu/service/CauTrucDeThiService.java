package com.nlu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.Chuong;
import com.nlu.dao.DeThiDao;
import com.nlu.dao.entity.CauTrucDeThi;
import com.nlu.dao.entity.DapAn;
import com.nlu.dao.entity.DeThi;

public interface CauTrucDeThiService {
	public CauTrucDeThi getCauTrucDeThiByIdMonHoc(int maMon);

	public boolean themCtCtdt(int maCtdt, int maChuong, int soLuong, byte doKho, double tongDiem) throws Exception;

	public CauTrucDeThi getCauTrucDeThiById(int maCtdt);

	public boolean xoaCtCtdt(int maCtdt, int maChuong, int maDoKho);

	public boolean resetCtdt(int maCtdt);

	public boolean submit(int maCtdt) throws SQLException;

	public ResultSet getListCauHoiByIdMaChuong(int maMon, int maChuong, int soLuong, int maDoKho) throws SQLException;

	public List<DapAn> getListDapAnByIdCauHoi(int maCH);

	public ResultSet getListChuongInCtdtById(int maCtdt) throws SQLException;

	public DeThiDao getDeThiByIdMaCtdt(int maCtdt) throws SQLException;

	public void addCauHoiIntoChuong(Chuong chuong) throws SQLException;

	public void addDapAnIntoCauHoi(CauHoiDao cauHoi) throws SQLException;

	public int getSoLuongCauHoiWithDoKhoInChuong(int maChuong, int maDoKho) throws SQLException;

	public boolean nopDeThi(DeThiDao deThi) throws SQLException;

	public DeThi getDeThiByIdMaDeThi(int maDeThi);
}

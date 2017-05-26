package com.nlu.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DeThiDao {
	private List<Chuong> listChuong;
	private int maDe;
	private int maMon;
	private int maCtdt;
	public int getMaCtdt() {
		return maCtdt;
	}



	public void setMaCtdt(int maCtdt) {
		this.maCtdt = maCtdt;
	}

	private Date ngayThem;
	private Date ngayThi;
	private boolean trangThai;

	public int getMaDe() {
		return maDe;
	}

	

	public DeThiDao(int maDe, int maCtdt, int maMon, Date ngayThem, Date ngayThi, boolean trangThai) throws SQLException {
		super();
		this.maCtdt = maCtdt;
		this.maDe = maDe;
		this.maMon = maMon;
		this.ngayThem = ngayThem;
		this.ngayThi = ngayThi;
		this.trangThai = trangThai;
	}

	public DeThiDao(int maCtdt) {
		this.maCtdt = maCtdt;
	}

	public DeThiDao(int maCtdt, int maMon) {
		this.maCtdt = maCtdt;
		this.maMon = maMon;
	}

	public List<Chuong> getListChuong() {
		return listChuong;
	}

	public void setListChuong(List<Chuong> listChuong) {
		this.listChuong = listChuong;
	}

	public void setMaDe(int maDe) {
		this.maDe = maDe;
	}

	public int getMaMon() {
		return maMon;
	}

	public void setMaMon(int maMon) {
		this.maMon = maMon;
	}

	public Date getNgayThem() {
		return ngayThem;
	}

	public void setNgayThem(Date ngayThem) {
		this.ngayThem = ngayThem;
	}

	public Date getNgayThi() {
		return ngayThi;
	}

	public void setNgayThi(Date ngayThi) {
		this.ngayThi = ngayThi;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		String rs = "";
		rs += this.listChuong.size();
		for (int i = 0; i < this.listChuong.size(); i++) {
			rs += "\n" + this.listChuong.get(i);
		}

		return rs;
	}

}

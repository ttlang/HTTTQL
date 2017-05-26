package com.nlu.dao;

import java.sql.SQLException;
import java.util.List;

public class Chuong {
	private int maMon, maDoKho;
	private int maChuong;
	private int machuong;
	private String tieude;

	public double getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(double tongDiem) {
		this.tongDiem = tongDiem;
	}
	public Chuong(int machuong, String tieude) {
		super();
		this.machuong = machuong;
		this.tieude = tieude;
	}
	
	public int getMachuong() {
		return machuong;
	}

	public void setMachuong(int machuong) {
		this.machuong = machuong;
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	private double tongDiem;
	private String tieuDe;
	private String moTa;
	private int soLuong;
	private List<CauHoiDao> listCauHoi;

	public int getMaChuong() {
		return maChuong;
	}

	public void setMaChuong(int maChuong) {
		this.maChuong = maChuong;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public List<CauHoiDao> getListCauHoi() {
		return listCauHoi;
	}

	public void setListCauHoi(List<CauHoiDao> listCauHoi) {
		this.listCauHoi = listCauHoi;
	}

	public Chuong(int maChuong, String tieuDe, String moTa, int maMon, int soLuong, int maDoKho, double tongDiem)
			throws SQLException {
		super();
		this.maChuong = maChuong;
		this.tieuDe = tieuDe;
		this.moTa = moTa;
		this.maMon = maMon;
		this.soLuong = soLuong;
		this.maDoKho = maDoKho;
		this.tongDiem = tongDiem;
	}

	public int getMaMon() {
		return maMon;
	}

	public void setMaMon(int maMon) {
		this.maMon = maMon;
	}

	public int getMaDoKho() {
		return maDoKho;
	}

	public void setMaDoKho(int maDoKho) {
		this.maDoKho = maDoKho;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "Chuong [maChuong=" + maChuong + ", tieuDe=" + tieuDe + ", moTa=" + moTa + ", soLuong=" + soLuong
				+ ", listCauHoi=" + listCauHoi + ", maMon=" + maMon + ", maDoKho=" + maDoKho + "]";
	}

}

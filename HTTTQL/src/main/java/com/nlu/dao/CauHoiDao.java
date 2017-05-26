package com.nlu.dao;

import java.util.ArrayList;
import java.util.List;

public class CauHoiDao {
	int mach;
	String noidung;
	int mamh;
	int madokho;
	int magv;
	List<DapAnDao> list = new ArrayList<>();

	public int getMamh() {
		return mamh;
	}

	public void setMamh(int mamh) {
		this.mamh = mamh;
	}

	public int getMach() {
		return mach;
	}

	public CauHoiDao() {
		super();
	}

	public CauHoiDao(int mach, String noidung, int mamh, int madokho, int magv) {
		super();
		this.mach = mach;
		this.noidung = noidung;
		this.mamh = mamh;
		this.madokho = madokho;
		this.magv = magv;
	}

	public void setMach(int mach) {
		this.mach = mach;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public int getMadokho() {
		return madokho;
	}

	public void setMadokho(int madokho) {
		this.madokho = madokho;
	}

	public int getMagv() {
		return magv;
	}

	public void setMagv(int magv) {
		this.magv = magv;
	}

	public List<DapAnDao> getList() {
		return list;
	}

	public void setList(List<DapAnDao> list) {
		this.list = list;
	}

	public CauHoiDao(int mach, String noidung, int mamh, int madokho, int magv, List<DapAnDao> list) {
		super();
		this.mach = mach;
		this.noidung = noidung;
		this.mamh = mamh;
		this.madokho = madokho;
		this.magv = magv;
		this.list = list;
	}

	@Override
	public String toString() {
		return "CauHoiDao [mach=" + mach + ", noidung=" + noidung + ", mamh=" + mamh + ", madokho=" + madokho
				+ ", magv=" + magv + ", list=" + list + "]";
	}
	

}

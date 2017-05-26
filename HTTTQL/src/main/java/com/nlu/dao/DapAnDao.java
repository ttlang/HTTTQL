package com.nlu.dao;

public class DapAnDao {
	int mach;
	int madn;
	String noidung;
	boolean dapandung;

	public DapAnDao() {
		super();
	}

	public DapAnDao(int mach, int madn, String noidung, boolean dapandung) {
		super();
		this.mach = mach;
		this.madn = madn;
		this.noidung = noidung;
		this.dapandung = dapandung;
	}

	public int getMach() {
		return mach;
	}

	public void setMach(int mach) {
		this.mach = mach;
	}

	public int getMadn() {
		return madn;
	}

	public void setMadn(int madn) {
		this.madn = madn;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public boolean isDapandung() {
		return dapandung;
	}

	public void setDapandung(boolean dapandung) {
		this.dapandung = dapandung;
	}

	@Override
	public String toString() {
		return "DapAnDao [mach=" + mach + ", madn=" + madn + ", noidung=" + noidung + ", dapandung=" + dapandung + "]";
	}
	

}

package com.nlu.dao;

public class GiangVien {
	private Integer magv;
	private String hogv;
	private String tengv;
	public GiangVien(Integer magv, String hogv, String tengv) {
		super();
		this.magv = magv;
		this.hogv = hogv;
		this.tengv = tengv;
	}
	public Integer getMagv() {
		return magv;
	}
	public void setMagv(Integer magv) {
		this.magv = magv;
	}
	public String getHogv() {
		return hogv;
	}
	public void setHogv(String hogv) {
		this.hogv = hogv;
	}
	public String getTengv() {
		return tengv;
	}
	public void setTengv(String tengv) {
		this.tengv = tengv;
	}
	
	
	
}

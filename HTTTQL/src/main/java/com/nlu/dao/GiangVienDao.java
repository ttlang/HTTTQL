package com.nlu.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class GiangVienDao {
	  private Integer magv;
	
	    private String hogv;
	
	    private String tengv;
	 
	    private Date ngaysinh;
	
	    private String email;
	 
	    private String matkhau;
	  
	    private String anhgv;
	   
	    private String diachi;
	  
	    private String dienthoai;
	  
	    
	    private Boolean gioitinh;


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


		public Date getNgaysinh() {
			return ngaysinh;
		}


		public void setNgaysinh(Date ngaysinh) {
			this.ngaysinh = ngaysinh;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getMatkhau() {
			return matkhau;
		}


		public void setMatkhau(String matkhau) {
			this.matkhau = matkhau;
		}


		public String getAnhgv() {
			return anhgv;
		}


		public void setAnhgv(String anhgv) {
			this.anhgv = anhgv;
		}


		public String getDiachi() {
			return diachi;
		}


		public void setDiachi(String diachi) {
			this.diachi = diachi;
		}


		public String getDienthoai() {
			return dienthoai;
		}


		public void setDienthoai(String dienthoai) {
			this.dienthoai = dienthoai;
		}


		public Boolean getGioitinh() {
			return gioitinh;
		}


		public void setGioitinh(Boolean gioitinh) {
			this.gioitinh = gioitinh;
		}
   
 
}

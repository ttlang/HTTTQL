/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "MON_HOC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MonHoc.findAll", query = "SELECT m FROM MonHoc m"),
		@NamedQuery(name = "MonHoc.findByMamon", query = "SELECT m FROM MonHoc m WHERE m.mamon = :mamon"),
		@NamedQuery(name = "MonHoc.findByTenmon", query = "SELECT m FROM MonHoc m WHERE m.tenmon = :tenmon"),
		@NamedQuery(name = "MonHoc.findByMagv", query = "SELECT m FROM MonHoc m WHERE m.magv = :magv") })
public class MonHoc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "MAMON", nullable = false)
	private Integer mamon;
	@Size(max = 50)
	@Column(name = "TENMON", length = 50)
	private String tenmon;
	@Column(name = "MAGV")
	private Integer magv;
	@ManyToMany(mappedBy = "monHocList")
	private List<GiangVien> giangVienList;
	@OneToMany(mappedBy = "mamon")
	private List<ChuongMuc> chuongMucList;
	@JoinColumn(name = "MABOMON", referencedColumnName = "MABOMON")
	@ManyToOne
	private BoMon mabomon;
	@OneToMany(mappedBy = "mamon")
	private List<DeThi> deThiList;
	@OneToOne(mappedBy = "mamon")
	private CauTrucDeThi cauTrucDeThi;
	@OneToMany(mappedBy = "mamon")
	private List<CauHoi> cauHoiList;

	public MonHoc() {
	}

	public CauTrucDeThi getCauTrucDeThi() {
		return cauTrucDeThi;
	}

	public void setCauTrucDeThi(CauTrucDeThi cauTrucDeThi) {
		this.cauTrucDeThi = cauTrucDeThi;
	}

	public MonHoc(Integer mamon) {
		this.mamon = mamon;
	}

	public Integer getMamon() {
		return mamon;
	}

	public void setMamon(Integer mamon) {
		this.mamon = mamon;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

	public Integer getMagv() {
		return magv;
	}

	public void setMagv(Integer magv) {
		this.magv = magv;
	}

	@JsonManagedReference
	public List<GiangVien> getGiangVienList() {
		return giangVienList;
	}

	public void setGiangVienList(List<GiangVien> giangVienList) {
		this.giangVienList = giangVienList;
	}

	@JsonManagedReference
	public List<ChuongMuc> getChuongMucList() {
		return chuongMucList;
	}

	public void setChuongMucList(List<ChuongMuc> chuongMucList) {
		this.chuongMucList = chuongMucList;
	}

	public BoMon getMabomon() {
		return mabomon;
	}

	public void setMabomon(BoMon mabomon) {
		this.mabomon = mabomon;
	}

	@JsonManagedReference
	public List<DeThi> getDeThiList() {
		return deThiList;
	}

	public void setDeThiList(List<DeThi> deThiList) {
		this.deThiList = deThiList;
	}

	@JsonManagedReference
	public List<CauHoi> getCauHoiList() {
		return cauHoiList;
	}

	public void setCauHoiList(List<CauHoi> cauHoiList) {
		this.cauHoiList = cauHoiList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mamon != null ? mamon.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MonHoc)) {
			return false;
		}
		MonHoc other = (MonHoc) object;
		if ((this.mamon == null && other.mamon != null) || (this.mamon != null && !this.mamon.equals(other.mamon))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.nlu.dao.entity.MonHoc[ mamon=" + mamon + " ]";
	}

}

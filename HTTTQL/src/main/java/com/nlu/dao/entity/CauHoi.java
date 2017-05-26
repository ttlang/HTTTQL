package com.nlu.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnoreType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CAU_HOI")
@XmlRootElement
@JsonIgnoreType
@NamedQueries({ @NamedQuery(name = "CauHoi.findAll", query = "SELECT c FROM CauHoi c"),
		@NamedQuery(name = "CauHoi.findByMach", query = "SELECT c FROM CauHoi c WHERE c.mach = :mach"),
		@NamedQuery(name = "CauHoi.findByNoidung", query = "SELECT c FROM CauHoi c WHERE c.noidung = :noidung"),
		@NamedQuery(name = "CauHoi.findByMagv", query = "SELECT c FROM CauHoi c WHERE c.magv = :magv"),
		@NamedQuery(name = "CauHoi.findByTrangthai", query = "SELECT c FROM CauHoi c WHERE c.trangthai = :trangthai") })
public class CauHoi implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MACH", nullable = false)
	private Integer mach;
	@Size(max = 1073741823)
	@Column(name = "NOIDUNG", length = 1073741823)
	private String noidung;
	@Column(name = "MAGV")
	private Integer magv;
	@Column(name = "TRANGTHAI")
	private Boolean trangthai;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cauHoi")
	private List<ChiTietDeThi> chiTietDeThiList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mach")
	private List<DapAn> dapAnList;
	@JoinColumn(name = "MACHUONG", referencedColumnName = "MACHUONG")
	@ManyToOne
	private ChuongMuc machuong;
	@JoinColumn(name = "MADOKHO", referencedColumnName = "MADOKHO")
	@ManyToOne
	@JsonManagedReference
	private DoKho madokho;
	@JoinColumn(name = "MAMON", referencedColumnName = "MAMON")
	@ManyToOne
	private MonHoc mamon;

	public CauHoi() {
	}

	public CauHoi(Integer mach) {
		this.mach = mach;
	}

	public Integer getMach() {
		return mach;
	}

	public void setMach(Integer mach) {
		this.mach = mach;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public Integer getMagv() {
		return magv;
	}

	public void setMagv(Integer magv) {
		this.magv = magv;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}

	@JsonManagedReference
	@JsonIgnore
	@XmlTransient
	public List<ChiTietDeThi> getChiTietDeThiList() {
		return chiTietDeThiList;
	}

	public void setChiTietDeThiList(List<ChiTietDeThi> chiTietDeThiList) {
		this.chiTietDeThiList = chiTietDeThiList;
	}

	@JsonManagedReference
	@JsonIgnore
	@XmlTransient
	public List<DapAn> getDapAnList() {
		return dapAnList;
	}

	public void setDapAnList(List<DapAn> dapAnList) {
		this.dapAnList = dapAnList;
	}

	public ChuongMuc getMachuong() {
		return machuong;
	}

	public void setMachuong(ChuongMuc machuong) {
		this.machuong = machuong;
	}

	public DoKho getMadokho() {
		return madokho;
	}

	public void setMadokho(DoKho madokho) {
		this.madokho = madokho;
	}

	public MonHoc getMamon() {
		return mamon;
	}

	public void setMamon(MonHoc mamon) {
		this.mamon = mamon;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mach != null ? mach.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CauHoi)) {
			return false;
		}
		CauHoi other = (CauHoi) object;
		if ((this.mach == null && other.mach != null) || (this.mach != null && !this.mach.equals(other.mach))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.nlu.dao.entity.CauHoi[ mach=" + mach + " ]";
	}

}

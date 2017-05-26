/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "CAU_TRUC_DE_THI")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CauTrucDeThi.findAll", query = "SELECT c FROM CauTrucDeThi c"),
		@NamedQuery(name = "CauTrucDeThi.findByMactdt", query = "SELECT c FROM CauTrucDeThi c WHERE c.mactdt = :mactdt"),
		@NamedQuery(name = "CauTrucDeThi.findByNgaycapnhat", query = "SELECT c FROM CauTrucDeThi c WHERE c.ngaycapnhat = :ngaycapnhat"),
		@NamedQuery(name = "CauTrucDeThi.findByTrangthai", query = "SELECT c FROM CauTrucDeThi c WHERE c.trangthai = :trangthai"),
		@NamedQuery(name = "CauTrucDeThi.findBySldetoida", query = "SELECT c FROM CauTrucDeThi c WHERE c.sldetoida = :sldetoida") })
public class CauTrucDeThi implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "MACTDT", nullable = false)
	private Integer mactdt;
	@Column(name = "NGAYCAPNHAT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngaycapnhat;
	@Column(name = "TRANGTHAI")
	private Boolean trangthai;
	@Column(name = "SLDETOIDA")
	private Short sldetoida;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cauTrucDeThi")
	private List<ChiTietCtdt> chiTietCtdtList;
	@JoinColumn(name = "MAGV", referencedColumnName = "MAGV")
	@ManyToOne
	private GiangVien magv;
	@JoinColumn(name = "MAMON", referencedColumnName = "MAMON")
	@ManyToOne
	private MonHoc mamon;

	public CauTrucDeThi() {
	}

	public CauTrucDeThi(Integer mactdt) {
		this.mactdt = mactdt;
	}

	public Integer getMactdt() {
		return mactdt;
	}

	public void setMactdt(Integer mactdt) {
		this.mactdt = mactdt;
	}

	public Date getNgaycapnhat() {
		return ngaycapnhat;
	}

	public void setNgaycapnhat(Date ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}

	public Short getSldetoida() {
		return sldetoida;
	}

	public void setSldetoida(Short sldetoida) {
		this.sldetoida = sldetoida;
	}

	@JsonManagedReference
	public List<ChiTietCtdt> getChiTietCtdtList() {
		return chiTietCtdtList;
	}

	public void setChiTietCtdtList(List<ChiTietCtdt> chiTietCtdtList) {
		this.chiTietCtdtList = chiTietCtdtList;
	}

	public GiangVien getMagv() {
		return magv;
	}

	public void setMagv(GiangVien magv) {
		this.magv = magv;
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
		hash += (mactdt != null ? mactdt.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CauTrucDeThi)) {
			return false;
		}
		CauTrucDeThi other = (CauTrucDeThi) object;
		if ((this.mactdt == null && other.mactdt != null)
				|| (this.mactdt != null && !this.mactdt.equals(other.mactdt))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.nlu.dao.entity.CauTrucDeThi[ mactdt=" + mactdt + " ]";
	}


}

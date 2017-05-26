/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;
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
@Table(name = "CHUONG_MUC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChuongMuc.findAll", query = "SELECT c FROM ChuongMuc c")
    , @NamedQuery(name = "ChuongMuc.findByMachuong", query = "SELECT c FROM ChuongMuc c WHERE c.machuong = :machuong")
    , @NamedQuery(name = "ChuongMuc.findByTieude", query = "SELECT c FROM ChuongMuc c WHERE c.tieude = :tieude")
    , @NamedQuery(name = "ChuongMuc.findByMota", query = "SELECT c FROM ChuongMuc c WHERE c.mota = :mota")})
public class ChuongMuc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MACHUONG", nullable = false)
    private Integer machuong;
    @Size(max = 50)
    @Column(name = "TIEUDE", length = 50)
    private String tieude;
    @Size(max = 100)
    @Column(name = "MOTA", length = 100)
    private String mota;
    @JoinColumn(name = "MAMON", referencedColumnName = "MAMON")
    @ManyToOne
    private MonHoc mamon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuongMuc")
    private List<ChiTietCtdt> chiTietCtdtList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chuongMuc")
    private List<CtTaoCauHoi> ctTaoCauHoiList;
    @OneToMany(mappedBy = "machuong")
    private List<CauHoi> cauHoiList;

    public ChuongMuc() {
    }

    public ChuongMuc(Integer machuong) {
        this.machuong = machuong;
    }

    public Integer getMachuong() {
        return machuong;
    }

    public void setMachuong(Integer machuong) {
        this.machuong = machuong;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public MonHoc getMamon() {
        return mamon;
    }

    public void setMamon(MonHoc mamon) {
        this.mamon = mamon;
    }

    @JsonManagedReference
    public List<ChiTietCtdt> getChiTietCtdtList() {
        return chiTietCtdtList;
    }

    public void setChiTietCtdtList(List<ChiTietCtdt> chiTietCtdtList) {
        this.chiTietCtdtList = chiTietCtdtList;
    }

    @JsonManagedReference
    public List<CtTaoCauHoi> getCtTaoCauHoiList() {
        return ctTaoCauHoiList;
    }

    public void setCtTaoCauHoiList(List<CtTaoCauHoi> ctTaoCauHoiList) {
        this.ctTaoCauHoiList = ctTaoCauHoiList;
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
        hash += (machuong != null ? machuong.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChuongMuc)) {
            return false;
        }
        ChuongMuc other = (ChuongMuc) object;
        if ((this.machuong == null && other.machuong != null) || (this.machuong != null && !this.machuong.equals(other.machuong))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.ChuongMuc[ machuong=" + machuong + " ]";
    }
    
}

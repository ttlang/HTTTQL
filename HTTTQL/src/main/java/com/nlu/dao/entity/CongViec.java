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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "CONG_VIEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CongViec.findAll", query = "SELECT c FROM CongViec c")
    , @NamedQuery(name = "CongViec.findByMacv", query = "SELECT c FROM CongViec c WHERE c.macv = :macv")
    , @NamedQuery(name = "CongViec.findByMamon", query = "SELECT c FROM CongViec c WHERE c.mamon = :mamon")
    , @NamedQuery(name = "CongViec.findByTgbabtdau", query = "SELECT c FROM CongViec c WHERE c.tgbabtdau = :tgbabtdau")
    , @NamedQuery(name = "CongViec.findByTgketthuc", query = "SELECT c FROM CongViec c WHERE c.tgketthuc = :tgketthuc")
    , @NamedQuery(name = "CongViec.findByNoidungcv", query = "SELECT c FROM CongViec c WHERE c.noidungcv = :noidungcv")
    , @NamedQuery(name = "CongViec.findByTrangthai", query = "SELECT c FROM CongViec c WHERE c.trangthai = :trangthai")})
public class CongViec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MACV", nullable = false)
    private Integer macv;
    @Column(name = "MAMON")
    private Integer mamon;
    @Column(name = "TGBABTDAU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgbabtdau;
    @Column(name = "TGKETTHUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgketthuc;
    @Size(max = 1073741823)
    @Column(name = "NOIDUNGCV", length = 1073741823)
    private String noidungcv;
    @Column(name = "TRANGTHAI")
    private Boolean trangthai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "congViec")
    private List<CtTaoCauHoi> ctTaoCauHoiList;
    @JoinColumn(name = "MAGV", referencedColumnName = "MAGV")
    @ManyToOne
    private GiangVien magv;
    @JoinColumn(name = "MALOAICV", referencedColumnName = "MALOAICV")
    @ManyToOne
    private LoaiCongViec maloaicv;

    public CongViec() {
    }

    public CongViec(Integer macv) {
        this.macv = macv;
    }

    public Integer getMacv() {
        return macv;
    }

    public void setMacv(Integer macv) {
        this.macv = macv;
    }

    public Integer getMamon() {
        return mamon;
    }

    public void setMamon(Integer mamon) {
        this.mamon = mamon;
    }

    public Date getTgbabtdau() {
        return tgbabtdau;
    }

    public void setTgbabtdau(Date tgbabtdau) {
        this.tgbabtdau = tgbabtdau;
    }

    public Date getTgketthuc() {
        return tgketthuc;
    }

    public void setTgketthuc(Date tgketthuc) {
        this.tgketthuc = tgketthuc;
    }

    public String getNoidungcv() {
        return noidungcv;
    }

    public void setNoidungcv(String noidungcv) {
        this.noidungcv = noidungcv;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    @JsonManagedReference
    public List<CtTaoCauHoi> getCtTaoCauHoiList() {
        return ctTaoCauHoiList;
    }

    public void setCtTaoCauHoiList(List<CtTaoCauHoi> ctTaoCauHoiList) {
        this.ctTaoCauHoiList = ctTaoCauHoiList;
    }

    public GiangVien getMagv() {
        return magv;
    }

    public void setMagv(GiangVien magv) {
        this.magv = magv;
    }

    public LoaiCongViec getMaloaicv() {
        return maloaicv;
    }

    public void setMaloaicv(LoaiCongViec maloaicv) {
        this.maloaicv = maloaicv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (macv != null ? macv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CongViec)) {
            return false;
        }
        CongViec other = (CongViec) object;
        if ((this.macv == null && other.macv != null) || (this.macv != null && !this.macv.equals(other.macv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.CongViec[ macv=" + macv + " ]";
    }
    
}

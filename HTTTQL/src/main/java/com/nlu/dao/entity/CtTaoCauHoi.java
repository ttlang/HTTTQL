/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "CT_TAO_CAU_HOI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CtTaoCauHoi.findAll", query = "SELECT c FROM CtTaoCauHoi c")
    , @NamedQuery(name = "CtTaoCauHoi.findByMacv", query = "SELECT c FROM CtTaoCauHoi c WHERE c.ctTaoCauHoiPK.macv = :macv")
    , @NamedQuery(name = "CtTaoCauHoi.findByMachuong", query = "SELECT c FROM CtTaoCauHoi c WHERE c.ctTaoCauHoiPK.machuong = :machuong")
    , @NamedQuery(name = "CtTaoCauHoi.findBySl", query = "SELECT c FROM CtTaoCauHoi c WHERE c.sl = :sl")})
public class CtTaoCauHoi implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CtTaoCauHoiPK ctTaoCauHoiPK;
    @Column(name = "SL")
    private Integer sl;
    @JoinColumn(name = "MACHUONG", referencedColumnName = "MACHUONG", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ChuongMuc chuongMuc;
    @JoinColumn(name = "MACV", referencedColumnName = "MACV", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CongViec congViec;

    public CtTaoCauHoi() {
    }

    public CtTaoCauHoi(CtTaoCauHoiPK ctTaoCauHoiPK) {
        this.ctTaoCauHoiPK = ctTaoCauHoiPK;
    }

    public CtTaoCauHoi(int macv, int machuong) {
        this.ctTaoCauHoiPK = new CtTaoCauHoiPK(macv, machuong);
    }

    public CtTaoCauHoiPK getCtTaoCauHoiPK() {
        return ctTaoCauHoiPK;
    }

    public void setCtTaoCauHoiPK(CtTaoCauHoiPK ctTaoCauHoiPK) {
        this.ctTaoCauHoiPK = ctTaoCauHoiPK;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public ChuongMuc getChuongMuc() {
        return chuongMuc;
    }

    public void setChuongMuc(ChuongMuc chuongMuc) {
        this.chuongMuc = chuongMuc;
    }

    public CongViec getCongViec() {
        return congViec;
    }

    public void setCongViec(CongViec congViec) {
        this.congViec = congViec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctTaoCauHoiPK != null ? ctTaoCauHoiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtTaoCauHoi)) {
            return false;
        }
        CtTaoCauHoi other = (CtTaoCauHoi) object;
        if ((this.ctTaoCauHoiPK == null && other.ctTaoCauHoiPK != null) || (this.ctTaoCauHoiPK != null && !this.ctTaoCauHoiPK.equals(other.ctTaoCauHoiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.CtTaoCauHoi[ ctTaoCauHoiPK=" + ctTaoCauHoiPK + " ]";
    }
    
}

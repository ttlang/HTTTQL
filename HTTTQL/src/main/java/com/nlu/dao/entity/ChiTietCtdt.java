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
@Table(name = "CHI_TIET_CTDT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiTietCtdt.findAll", query = "SELECT c FROM ChiTietCtdt c")
    , @NamedQuery(name = "ChiTietCtdt.findByMactdt", query = "SELECT c FROM ChiTietCtdt c WHERE c.chiTietCtdtPK.mactdt = :mactdt")
    , @NamedQuery(name = "ChiTietCtdt.findByMachuong", query = "SELECT c FROM ChiTietCtdt c WHERE c.chiTietCtdtPK.machuong = :machuong")
    , @NamedQuery(name = "ChiTietCtdt.findByMadokho", query = "SELECT c FROM ChiTietCtdt c WHERE c.chiTietCtdtPK.madokho = :madokho")
    , @NamedQuery(name = "ChiTietCtdt.findBySlcauhoi", query = "SELECT c FROM ChiTietCtdt c WHERE c.slcauhoi = :slcauhoi")
    , @NamedQuery(name = "ChiTietCtdt.findByTongdiem", query = "SELECT c FROM ChiTietCtdt c WHERE c.tongdiem = :tongdiem")})
public class ChiTietCtdt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChiTietCtdtPK chiTietCtdtPK;
    @Column(name = "SLCAUHOI")
    private Short slcauhoi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TONGDIEM", precision = 53)
    private Double tongdiem;
    @JoinColumn(name = "MACTDT", referencedColumnName = "MACTDT", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauTrucDeThi cauTrucDeThi;
    @JoinColumn(name = "MACHUONG", referencedColumnName = "MACHUONG", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ChuongMuc chuongMuc;

    public ChiTietCtdt() {
    }

    public ChiTietCtdt(ChiTietCtdtPK chiTietCtdtPK) {
        this.chiTietCtdtPK = chiTietCtdtPK;
    }

    public ChiTietCtdt(int mactdt, int machuong, int madokho) {
        this.chiTietCtdtPK = new ChiTietCtdtPK(mactdt, machuong, madokho);
    }

    public ChiTietCtdtPK getChiTietCtdtPK() {
        return chiTietCtdtPK;
    }

    public void setChiTietCtdtPK(ChiTietCtdtPK chiTietCtdtPK) {
        this.chiTietCtdtPK = chiTietCtdtPK;
    }

    public Short getSlcauhoi() {
        return slcauhoi;
    }

    public void setSlcauhoi(Short slcauhoi) {
        this.slcauhoi = slcauhoi;
    }

    public Double getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(Double tongdiem) {
        this.tongdiem = tongdiem;
    }

    public CauTrucDeThi getCauTrucDeThi() {
        return cauTrucDeThi;
    }

    public void setCauTrucDeThi(CauTrucDeThi cauTrucDeThi) {
        this.cauTrucDeThi = cauTrucDeThi;
    }

    public ChuongMuc getChuongMuc() {
        return chuongMuc;
    }

    public void setChuongMuc(ChuongMuc chuongMuc) {
        this.chuongMuc = chuongMuc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chiTietCtdtPK != null ? chiTietCtdtPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietCtdt)) {
            return false;
        }
        ChiTietCtdt other = (ChiTietCtdt) object;
        if ((this.chiTietCtdtPK == null && other.chiTietCtdtPK != null) || (this.chiTietCtdtPK != null && !this.chiTietCtdtPK.equals(other.chiTietCtdtPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.ChiTietCtdt[ chiTietCtdtPK=" + chiTietCtdtPK + " ]";
    }
    
}

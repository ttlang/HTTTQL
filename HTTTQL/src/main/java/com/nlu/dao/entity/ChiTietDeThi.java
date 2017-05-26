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
@Table(name = "CHI_TIET_DE_THI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiTietDeThi.findAll", query = "SELECT c FROM ChiTietDeThi c")
    , @NamedQuery(name = "ChiTietDeThi.findByMadethi", query = "SELECT c FROM ChiTietDeThi c WHERE c.chiTietDeThiPK.madethi = :madethi")
    , @NamedQuery(name = "ChiTietDeThi.findByMach", query = "SELECT c FROM ChiTietDeThi c WHERE c.chiTietDeThiPK.mach = :mach")
    , @NamedQuery(name = "ChiTietDeThi.findByVitridapandung", query = "SELECT c FROM ChiTietDeThi c WHERE c.vitridapandung = :vitridapandung")
    , @NamedQuery(name = "ChiTietDeThi.findByDiem", query = "SELECT c FROM ChiTietDeThi c WHERE c.diem = :diem")})
public class ChiTietDeThi implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChiTietDeThiPK chiTietDeThiPK;
    @Column(name = "VITRIDAPANDUNG")
    private Short vitridapandung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DIEM", precision = 53)
    private Double diem;
    @JoinColumn(name = "MACH", referencedColumnName = "MACH", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CauHoi cauHoi;
    @JoinColumn(name = "MADETHI", referencedColumnName = "MADETHI", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DeThi deThi;

    public ChiTietDeThi() {
    }

    public ChiTietDeThi(ChiTietDeThiPK chiTietDeThiPK) {
        this.chiTietDeThiPK = chiTietDeThiPK;
    }

    public ChiTietDeThi(int madethi, int mach) {
        this.chiTietDeThiPK = new ChiTietDeThiPK(madethi, mach);
    }

    public ChiTietDeThiPK getChiTietDeThiPK() {
        return chiTietDeThiPK;
    }

    public void setChiTietDeThiPK(ChiTietDeThiPK chiTietDeThiPK) {
        this.chiTietDeThiPK = chiTietDeThiPK;
    }

    public Short getVitridapandung() {
        return vitridapandung;
    }

    public void setVitridapandung(Short vitridapandung) {
        this.vitridapandung = vitridapandung;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }

    public DeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(DeThi deThi) {
        this.deThi = deThi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chiTietDeThiPK != null ? chiTietDeThiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietDeThi)) {
            return false;
        }
        ChiTietDeThi other = (ChiTietDeThi) object;
        if ((this.chiTietDeThiPK == null && other.chiTietDeThiPK != null) || (this.chiTietDeThiPK != null && !this.chiTietDeThiPK.equals(other.chiTietDeThiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.ChiTietDeThi[ chiTietDeThiPK=" + chiTietDeThiPK + " ]";
    }
    
}

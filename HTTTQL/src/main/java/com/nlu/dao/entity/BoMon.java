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
@Table(name = "BO_MON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BoMon.findAll", query = "SELECT b FROM BoMon b")
    , @NamedQuery(name = "BoMon.findByMabomon", query = "SELECT b FROM BoMon b WHERE b.mabomon = :mabomon")
    , @NamedQuery(name = "BoMon.findByTenbomon", query = "SELECT b FROM BoMon b WHERE b.tenbomon = :tenbomon")
    , @NamedQuery(name = "BoMon.findByMagv", query = "SELECT b FROM BoMon b WHERE b.magv = :magv")})
public class BoMon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MABOMON", nullable = false)
    private Integer mabomon;
    @Size(max = 50)
    @Column(name = "TENBOMON", length = 50)
    private String tenbomon;
    @Column(name = "MAGV")
    private Integer magv;
    @OneToMany(mappedBy = "mabomon")
    private List<MonHoc> monHocList;
    @OneToMany(mappedBy = "mabomon")
    private List<GiangVien> giangVienList;
    @JoinColumn(name = "MAKHOA", referencedColumnName = "MAKHOA")
    @ManyToOne
    private Khoa makhoa;

    public BoMon() {
    }

    public BoMon(Integer mabomon) {
        this.mabomon = mabomon;
    }

    public Integer getMabomon() {
        return mabomon;
    }

    public void setMabomon(Integer mabomon) {
        this.mabomon = mabomon;
    }

    public String getTenbomon() {
        return tenbomon;
    }

    public void setTenbomon(String tenbomon) {
        this.tenbomon = tenbomon;
    }

    public Integer getMagv() {
        return magv;
    }

    public void setMagv(Integer magv) {
        this.magv = magv;
    }

    @JsonManagedReference
    public List<MonHoc> getMonHocList() {
        return monHocList;
    }

    public void setMonHocList(List<MonHoc> monHocList) {
        this.monHocList = monHocList;
    }

    @JsonManagedReference
    public List<GiangVien> getGiangVienList() {
        return giangVienList;
    }

    public void setGiangVienList(List<GiangVien> giangVienList) {
        this.giangVienList = giangVienList;
    }

    public Khoa getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Khoa makhoa) {
        this.makhoa = makhoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mabomon != null ? mabomon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoMon)) {
            return false;
        }
        BoMon other = (BoMon) object;
        if ((this.mabomon == null && other.mabomon != null) || (this.mabomon != null && !this.mabomon.equals(other.mabomon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.BoMon[ mabomon=" + mabomon + " ]";
    }
    
}

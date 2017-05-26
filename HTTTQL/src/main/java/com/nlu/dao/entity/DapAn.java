/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "DAP_AN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DapAn.findAll", query = "SELECT d FROM DapAn d")
    , @NamedQuery(name = "DapAn.findByMadapan", query = "SELECT d FROM DapAn d WHERE d.madapan = :madapan")
    , @NamedQuery(name = "DapAn.findByNoidung", query = "SELECT d FROM DapAn d WHERE d.noidung = :noidung")
    , @NamedQuery(name = "DapAn.findByDapandung", query = "SELECT d FROM DapAn d WHERE d.dapandung = :dapandung")})
public class DapAn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADAPAN", nullable = false)
    private Integer madapan;
    @Size(max = 1073741823)
    @Column(name = "NOIDUNG", length = 1073741823)
    private String noidung;
    @Column(name = "DAPANDUNG")
    private Boolean dapandung;
    @JoinColumn(name = "MACH", referencedColumnName = "MACH", nullable = false)
    @ManyToOne(optional = false)
    private CauHoi mach;

    public DapAn() {
    }

    public DapAn(Integer madapan) {
        this.madapan = madapan;
    }

    public Integer getMadapan() {
        return madapan;
    }

    public void setMadapan(Integer madapan) {
        this.madapan = madapan;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Boolean getDapandung() {
        return dapandung;
    }

    public void setDapandung(Boolean dapandung) {
        this.dapandung = dapandung;
    }

    public CauHoi getMach() {
        return mach;
    }

    public void setMach(CauHoi mach) {
        this.mach = mach;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (madapan != null ? madapan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DapAn)) {
            return false;
        }
        DapAn other = (DapAn) object;
        if ((this.madapan == null && other.madapan != null) || (this.madapan != null && !this.madapan.equals(other.madapan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.DapAn[ madapan=" + madapan + " ]";
    }
    
}

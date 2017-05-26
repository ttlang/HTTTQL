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
@Table(name = "KHOA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khoa.findAll", query = "SELECT k FROM Khoa k")
    , @NamedQuery(name = "Khoa.findByMakhoa", query = "SELECT k FROM Khoa k WHERE k.makhoa = :makhoa")
    , @NamedQuery(name = "Khoa.findByTenkhoa", query = "SELECT k FROM Khoa k WHERE k.tenkhoa = :tenkhoa")
    , @NamedQuery(name = "Khoa.findByMagv", query = "SELECT k FROM Khoa k WHERE k.magv = :magv")})
public class Khoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAKHOA", nullable = false)
    private Integer makhoa;
    @Size(max = 50)
    @Column(name = "TENKHOA", length = 50)
    private String tenkhoa;
    @Column(name = "MAGV")
    private Integer magv;
    @OneToMany(mappedBy = "makhoa")
    private List<BoMon> boMonList;

    public Khoa() {
    }

    public Khoa(Integer makhoa) {
        this.makhoa = makhoa;
    }

    public Integer getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Integer makhoa) {
        this.makhoa = makhoa;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public Integer getMagv() {
        return magv;
    }

    public void setMagv(Integer magv) {
        this.magv = magv;
    }

    @JsonManagedReference
    public List<BoMon> getBoMonList() {
        return boMonList;
    }

    public void setBoMonList(List<BoMon> boMonList) {
        this.boMonList = boMonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (makhoa != null ? makhoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khoa)) {
            return false;
        }
        Khoa other = (Khoa) object;
        if ((this.makhoa == null && other.makhoa != null) || (this.makhoa != null && !this.makhoa.equals(other.makhoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.Khoa[ makhoa=" + makhoa + " ]";
    }
    
}

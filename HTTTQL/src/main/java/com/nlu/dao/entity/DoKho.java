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
@Table(name = "DO_KHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoKho.findAll", query = "SELECT d FROM DoKho d")
    , @NamedQuery(name = "DoKho.findByMadokho", query = "SELECT d FROM DoKho d WHERE d.madokho = :madokho")
    , @NamedQuery(name = "DoKho.findByTendokho", query = "SELECT d FROM DoKho d WHERE d.tendokho = :tendokho")})
public class DoKho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MADOKHO", nullable = false)
    private Integer madokho;
    @Size(max = 50)
    @Column(name = "TENDOKHO", length = 50)
    private String tendokho;
    @OneToMany(mappedBy = "madokho")
    private List<CauHoi> cauHoiList;

    public DoKho() {
    }

    public DoKho(Integer madokho) {
        this.madokho = madokho;
    }

    public Integer getMadokho() {
        return madokho;
    }

    public void setMadokho(Integer madokho) {
        this.madokho = madokho;
    }

    public String getTendokho() {
        return tendokho;
    }

    public void setTendokho(String tendokho) {
        this.tendokho = tendokho;
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
        hash += (madokho != null ? madokho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoKho)) {
            return false;
        }
        DoKho other = (DoKho) object;
        if ((this.madokho == null && other.madokho != null) || (this.madokho != null && !this.madokho.equals(other.madokho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.DoKho[ madokho=" + madokho + " ]";
    }
    
}

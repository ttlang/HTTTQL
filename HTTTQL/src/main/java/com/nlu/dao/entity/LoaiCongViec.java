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
@Table(name = "LOAI_CONG_VIEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiCongViec.findAll", query = "SELECT l FROM LoaiCongViec l")
    , @NamedQuery(name = "LoaiCongViec.findByMaloaicv", query = "SELECT l FROM LoaiCongViec l WHERE l.maloaicv = :maloaicv")
    , @NamedQuery(name = "LoaiCongViec.findByTenloaicv", query = "SELECT l FROM LoaiCongViec l WHERE l.tenloaicv = :tenloaicv")})
public class LoaiCongViec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MALOAICV", nullable = false)
    private Integer maloaicv;
    @Size(max = 100)
    @Column(name = "TENLOAICV", length = 100)
    private String tenloaicv;
    @OneToMany(mappedBy = "maloaicv")
    private List<CongViec> congViecList;
    public LoaiCongViec() {
    }

    public LoaiCongViec(Integer maloaicv) {
        this.maloaicv = maloaicv;
    }
    public Integer getMaloaicv() {
        return maloaicv;
    }

    public void setMaloaicv(Integer maloaicv) {
        this.maloaicv = maloaicv;
    }

    public String getTenloaicv() {
        return tenloaicv;
    }

    public void setTenloaicv(String tenloaicv) {
        this.tenloaicv = tenloaicv;
    }

    @JsonManagedReference
    public List<CongViec> getCongViecList() {
        return congViecList;
    }

    public void setCongViecList(List<CongViec> congViecList) {
        this.congViecList = congViecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maloaicv != null ? maloaicv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoaiCongViec)) {
            return false;
        }
        LoaiCongViec other = (LoaiCongViec) object;
        if ((this.maloaicv == null && other.maloaicv != null) || (this.maloaicv != null && !this.maloaicv.equals(other.maloaicv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.LoaiCongViec[ maloaicv=" + maloaicv + " ]";
    }
    
}

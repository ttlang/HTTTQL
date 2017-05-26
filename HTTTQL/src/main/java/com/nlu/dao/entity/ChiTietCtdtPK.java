/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Huynh Thanh
 */
@Embeddable
public class ChiTietCtdtPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MACTDT", nullable = false)
    private int mactdt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MACHUONG", nullable = false)
    private int machuong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MADOKHO", nullable = false)
    private int madokho;

    public ChiTietCtdtPK() {
    }

    public ChiTietCtdtPK(int mactdt, int machuong, int madokho) {
        this.mactdt = mactdt;
        this.machuong = machuong;
        this.madokho = madokho;
    }

    public int getMactdt() {
        return mactdt;
    }

    public void setMactdt(int mactdt) {
        this.mactdt = mactdt;
    }

    public int getMachuong() {
        return machuong;
    }

    public void setMachuong(int machuong) {
        this.machuong = machuong;
    }

    public int getMadokho() {
        return madokho;
    }

    public void setMadokho(int madokho) {
        this.madokho = madokho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mactdt;
        hash += (int) machuong;
        hash += (int) madokho;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietCtdtPK)) {
            return false;
        }
        ChiTietCtdtPK other = (ChiTietCtdtPK) object;
        if (this.mactdt != other.mactdt) {
            return false;
        }
        if (this.machuong != other.machuong) {
            return false;
        }
        if (this.madokho != other.madokho) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.ChiTietCtdtPK[ mactdt=" + mactdt + ", machuong=" + machuong + ", madokho=" + madokho + " ]";
    }
    
}

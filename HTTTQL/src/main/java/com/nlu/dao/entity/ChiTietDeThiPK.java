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
public class ChiTietDeThiPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MADETHI", nullable = false)
    private int madethi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MACH", nullable = false)
    private int mach;

    public ChiTietDeThiPK() {
    }

    public ChiTietDeThiPK(int madethi, int mach) {
        this.madethi = madethi;
        this.mach = mach;
    }

    public int getMadethi() {
        return madethi;
    }

    public void setMadethi(int madethi) {
        this.madethi = madethi;
    }

    public int getMach() {
        return mach;
    }

    public void setMach(int mach) {
        this.mach = mach;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) madethi;
        hash += (int) mach;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietDeThiPK)) {
            return false;
        }
        ChiTietDeThiPK other = (ChiTietDeThiPK) object;
        if (this.madethi != other.madethi) {
            return false;
        }
        if (this.mach != other.mach) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.ChiTietDeThiPK[ madethi=" + madethi + ", mach=" + mach + " ]";
    }
    
}

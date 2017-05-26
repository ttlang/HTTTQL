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
public class CtTaoCauHoiPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MACV", nullable = false)
    private int macv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MACHUONG", nullable = false)
    private int machuong;

    public CtTaoCauHoiPK() {
    }

    public CtTaoCauHoiPK(int macv, int machuong) {
        this.macv = macv;
        this.machuong = machuong;
    }

    public int getMacv() {
        return macv;
    }

    public void setMacv(int macv) {
        this.macv = macv;
    }

    public int getMachuong() {
        return machuong;
    }

    public void setMachuong(int machuong) {
        this.machuong = machuong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) macv;
        hash += (int) machuong;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtTaoCauHoiPK)) {
            return false;
        }
        CtTaoCauHoiPK other = (CtTaoCauHoiPK) object;
        if (this.macv != other.macv) {
            return false;
        }
        if (this.machuong != other.machuong) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.CtTaoCauHoiPK[ macv=" + macv + ", machuong=" + machuong + " ]";
    }
    
}

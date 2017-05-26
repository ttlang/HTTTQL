package com.nlu.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "DE_THI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeThi.findAll", query = "SELECT d FROM DeThi d")
    , @NamedQuery(name = "DeThi.findByMadethi", query = "SELECT d FROM DeThi d WHERE d.madethi = :madethi")
    , @NamedQuery(name = "DeThi.findByNgaythem", query = "SELECT d FROM DeThi d WHERE d.ngaythem = :ngaythem")
    , @NamedQuery(name = "DeThi.findByNgaythi", query = "SELECT d FROM DeThi d WHERE d.ngaythi = :ngaythi")
    , @NamedQuery(name = "DeThi.findByThoigianlambai", query = "SELECT d FROM DeThi d WHERE d.thoigianlambai = :thoigianlambai")
    , @NamedQuery(name = "DeThi.findByTrangthai", query = "SELECT d FROM DeThi d WHERE d.trangthai = :trangthai")})
public class DeThi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADETHI", nullable = false)
    private Integer madethi;
    @Column(name = "NGAYTHEM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaythem;
    @Column(name = "NGAYTHI")
    @Temporal(TemporalType.DATE)
    private Date ngaythi;
    @Size(max = 20)
    @Column(name = "THOIGIANLAMBAI", length = 20)
    private String thoigianlambai;
    @Column(name = "TRANGTHAI")
    private Boolean trangthai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deThi")
    private List<ChiTietDeThi> chiTietDeThiList;
    @JoinColumn(name = "MAMON", referencedColumnName = "MAMON")
    @ManyToOne
    private MonHoc mamon;

    public DeThi() {
    }

    public DeThi(Integer madethi) {
        this.madethi = madethi;
    }

    public Integer getMadethi() {
        return madethi;
    }

    public void setMadethi(Integer madethi) {
        this.madethi = madethi;
    }

    public Date getNgaythem() {
        return ngaythem;
    }

    public void setNgaythem(Date ngaythem) {
        this.ngaythem = ngaythem;
    }

    public Date getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(Date ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getThoigianlambai() {
        return thoigianlambai;
    }

    public void setThoigianlambai(String thoigianlambai) {
        this.thoigianlambai = thoigianlambai;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    @JsonManagedReference
    @XmlTransient
    public List<ChiTietDeThi> getChiTietDeThiList() {
        return chiTietDeThiList;
    }

    public void setChiTietDeThiList(List<ChiTietDeThi> chiTietDeThiList) {
        this.chiTietDeThiList = chiTietDeThiList;
    }

    public MonHoc getMamon() {
        return mamon;
    }

    public void setMamon(MonHoc mamon) {
        this.mamon = mamon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (madethi != null ? madethi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeThi)) {
            return false;
        }
        DeThi other = (DeThi) object;
        if ((this.madethi == null && other.madethi != null) || (this.madethi != null && !this.madethi.equals(other.madethi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.DeThi[ madethi=" + madethi + " ]";
    }
    
}

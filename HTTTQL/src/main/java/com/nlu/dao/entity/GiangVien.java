/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlu.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

/**
 *
 * @author Huynh Thanh
 */
@Entity
@Table(name = "GIANG_VIEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GiangVien.findAll", query = "SELECT g FROM GiangVien g")
    , @NamedQuery(name = "GiangVien.findByMagv", query = "SELECT g FROM GiangVien g WHERE g.magv = :magv")
    , @NamedQuery(name = "GiangVien.findByHogv", query = "SELECT g FROM GiangVien g WHERE g.hogv = :hogv")
    , @NamedQuery(name = "GiangVien.findByTengv", query = "SELECT g FROM GiangVien g WHERE g.tengv = :tengv")
    , @NamedQuery(name = "GiangVien.findByNgaysinh", query = "SELECT g FROM GiangVien g WHERE g.ngaysinh = :ngaysinh")
    , @NamedQuery(name = "GiangVien.findByEmail", query = "SELECT g FROM GiangVien g WHERE g.email = :email")
    , @NamedQuery(name = "GiangVien.findByMatkhau", query = "SELECT g FROM GiangVien g WHERE g.matkhau = :matkhau")
    , @NamedQuery(name = "GiangVien.findByAnhgv", query = "SELECT g FROM GiangVien g WHERE g.anhgv = :anhgv")
    , @NamedQuery(name = "GiangVien.findByDiachi", query = "SELECT g FROM GiangVien g WHERE g.diachi = :diachi")
    , @NamedQuery(name = "GiangVien.findByDienthoai", query = "SELECT g FROM GiangVien g WHERE g.dienthoai = :dienthoai")
    , @NamedQuery(name = "GiangVien.findByGioitinh", query = "SELECT g FROM GiangVien g WHERE g.gioitinh = :gioitinh")})
public class GiangVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MAGV")
    private Integer magv;
    @Size(max = 50)
    @Column(name = "HOGV", length = 50)
    private String hogv;
    @Size(max = 50)
    @Column(name = "TENGV", length = 50)
    private String tengv;
    @Column(name = "NGAYSINH")
    @Temporal(TemporalType.DATE)
    private Date ngaysinh;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Size(max = 50)
    @Column(name = "MATKHAU", length = 50)
    private String matkhau;
    @Size(max = 300)
    @Column(name = "ANHGV", length = 300)
    private String anhgv;
    @Size(max = 100)
    @Column(name = "DIACHI", length = 100)
    private String diachi;
    @Size(max = 12)
    @Column(name = "DIENTHOAI", length = 12)
    private String dienthoai;
    @Column(name = "GIOITINH")
    private Boolean gioitinh;
    @JoinTable(name = "QUAN_LY_MH", joinColumns = {
        @JoinColumn(name = "MAGV", referencedColumnName = "MAGV", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "MAMON", referencedColumnName = "MAMON", nullable = false)})
    @ManyToMany
    private List<MonHoc> monHocList;
    @OneToMany(mappedBy = "magv")
    private List<CauTrucDeThi> cauTrucDeThiList;
    @JoinColumn(name = "MABOMON", referencedColumnName = "MABOMON")
    @ManyToOne
    private BoMon mabomon;
    @OneToMany(mappedBy = "magv")
    private List<CongViec> congViecList;

    public GiangVien() {
    }

    public GiangVien(Integer magv) {
        this.magv = magv;
    }

    public Integer getMagv() {
        return magv;
    }

    public void setMagv(Integer magv) {
        this.magv = magv;
    }

    public String getHogv() {
        return hogv;
    }

    public void setHogv(String hogv) {
        this.hogv = hogv;
    }

    public String getTengv() {
        return tengv;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getAnhgv() {
        return anhgv;
    }

    public void setAnhgv(String anhgv) {
        this.anhgv = anhgv;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public Boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    @JsonManagedReference
    public List<MonHoc> getMonHocList() {
        return monHocList;
    }

    public void setMonHocList(List<MonHoc> monHocList) {
        this.monHocList = monHocList;
    }

    @JsonManagedReference
    public List<CauTrucDeThi> getCauTrucDeThiList() {
        return cauTrucDeThiList;
    }

    public void setCauTrucDeThiList(List<CauTrucDeThi> cauTrucDeThiList) {
        this.cauTrucDeThiList = cauTrucDeThiList;
    }

    public BoMon getMabomon() {
        return mabomon;
    }

    public void setMabomon(BoMon mabomon) {
        this.mabomon = mabomon;
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
        hash += (magv != null ? magv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiangVien)) {
            return false;
        }
        GiangVien other = (GiangVien) object;
        if ((this.magv == null && other.magv != null) || (this.magv != null && !this.magv.equals(other.magv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlu.dao.entity.GiangVien[ magv=" + magv + " ]";
    }
    
}

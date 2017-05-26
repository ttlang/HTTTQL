package com.nlu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nlu.dao.entity.CongViec;

public interface CongViecService {
  public Page<CongViec> list(String search , Pageable page) ;
  /**
   * kiểm tra giáo viên có quản lý bộ môn đó hay không
   * @param magv mã giáo viên
   * @param mamabomon mã bộ
   * @return true giáo viên quản lý môn học
   */
  public boolean checkMagvInBoMon(int magv,int mamabomon);
  
  /**
   * thêm công việc [mã công việc là 1]
   * @param maMon mã môn
   * @param maLoaiGV mã giáo viên
   * @param maLoaiCV mã loại công việc
   * @param thoiGianBatDau thời gian bắt đầu
   * @param thoiGianKetThuc thời gian kết thúc
   * @param noiDungCV nội dung công việc
   * @return thông báo kết quả
   */
  
  public String addCongViec(int maMon,int maGV,int maLoaiCV,String thoiGianBatDau,String thoiGianKetThuc,String noiDungCV);
  
  /**
   * thêm công việc (tạo đề thi[mã công việc là 3])
   * @param maMon
   * @param maGV
   * @param maLoaiCV
   * @param thoiGianBatDau
   * @param thoiGianKetThuc
   * @param noiDungCV
   * @param soLuongDeToiDa
   * @return
   */
  public String addCongViec3(int maMon,int maGV,int maLoaiCV,String thoiGianBatDau,String thoiGianKetThuc,String noiDungCV,int soLuongDeToiDa);
  /**
   * thêm công việc [tạo câu hỏi mã công việc là 3]
   * @param maMon
   * @param maGV
   * @param maLoaiCV
   * @param thoiGianBatDau
   * @param thoiGianKetThuc
   * @param noiDungCV
   * @param chuong
   * @param soLuongCauHoiToiDa
   * @return
   */
  public String addCongViec2(int maMon,int maGV,int maLoaiCV,String thoiGianBatDau,String thoiGianKetThuc,String noiDungCV,int chuong,int soLuongCauHoiToiDa);
  
  
  
  
  
  
 }

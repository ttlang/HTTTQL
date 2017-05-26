package com.nlu.service;

import java.sql.SQLException;

import com.nlu.dao.ThongKeCauHoiDao;

public interface ThongKeService {
   public ThongKeCauHoiDao thongkecauhoi(int makhoa  , int mabomon ) throws SQLException;
}

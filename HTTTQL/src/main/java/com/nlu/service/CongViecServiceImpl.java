package com.nlu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.BoMon;
import com.nlu.dao.entity.CongViec;
import com.nlu.repository.CongViecRepository;

@Service
@Transactional
public class CongViecServiceImpl implements CongViecService {
	@Autowired
	CongViecRepository congViecRepository;
	@Autowired
	SessionFactory sf;

	@Override
	public Page<CongViec> list(String search, Pageable page) {
		return congViecRepository.list(search, page);
	}

	@Override
	public boolean checkMagvInBoMon(int magv, int mamabomon) {
		Session session = sf.getCurrentSession();
		Query query = session.createQuery("SELECT b FROM BoMon b WHERE b.magv = :magv").setInteger("magv", magv);
		@SuppressWarnings("unchecked")
		List<BoMon> result = query.list();
		if (result.isEmpty()) {
			return false;
		} else {
			BoMon b = result.get(0);
			if (b.getMabomon() != mamabomon) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public String addCongViec(int maMon, int maGV, int maLoaiCV, String thoiGianBatDau, String thoiGianKetThuc,
			String noiDungCV) {
		SessionImpl impl = (SessionImpl) sf.getCurrentSession();
		Connection connection = impl.connection();
		String message = "";
		try {
			CallableStatement statement = connection.prepareCall("EXEC p_themCongViec_CONG_VIEC ?,?,?,?,?,? ");
			statement.setInt(1, maMon);
			statement.setInt(2, maGV);
			statement.setInt(3, maLoaiCV);
			statement.setString(4, thoiGianBatDau);
			statement.setString(5, thoiGianKetThuc);
			statement.setNString(6, noiDungCV);
			message = (statement.executeUpdate() > 0) ? "thêm thành công" : "thêm thất bại";
			connection.close();

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}
	@Override
	public String addCongViec3(int maMon, int maGV, int maLoaiCV, String thoiGianBatDau, String thoiGianKetThuc,
			String noiDungCV, int soLuongDeToiDa) {
		SessionImpl impl = (SessionImpl) sf.getCurrentSession();
		Connection connection = impl.connection();
		String message = "";
		try {
			CallableStatement statement = connection.prepareCall("EXEC p_themCongViec2_CONG_VIEC ?,?,?,?,?,?,? ");
			statement.setInt(1, maMon);
			statement.setInt(2, maGV);
			statement.setInt(3, maLoaiCV);
			statement.setString(4, thoiGianBatDau);
			statement.setString(5, thoiGianKetThuc);
			statement.setNString(6, noiDungCV);
			statement.setInt(7, soLuongDeToiDa);
			message = (statement.executeUpdate() > 0) ? "thêm thành công" : "thêm thất bại";
			connection.close();
		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public String addCongViec2(int maMon, int maGV, int maLoaiCV, String thoiGianBatDau, String thoiGianKetThuc,
			String noiDungCV, int chuong, int soLuongCauHoiToiDa) {
		SessionImpl impl = (SessionImpl) sf.getCurrentSession();
		Connection connection = impl.connection();
		String message = "";
		try {
			CallableStatement statement = connection.prepareCall("EXEC p_themCongViec_CAUHOI_CONG_VIEC ?,?,?,?,?,?,?,? ");
			statement.setInt(1, maMon);
			statement.setInt(2, maGV);
			statement.setInt(3, maLoaiCV);
			statement.setString(4, thoiGianBatDau);
			statement.setString(5, thoiGianKetThuc);
			statement.setNString(6, noiDungCV);
			statement.setInt(7, chuong);
			statement.setInt(8, soLuongCauHoiToiDa);
			message = (statement.executeUpdate() > 0) ? "thêm thành công" : "thêm thất bại";
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}

		return message;
	}
}

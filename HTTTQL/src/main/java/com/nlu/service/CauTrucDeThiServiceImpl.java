package com.nlu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.Chuong;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.DeThiDao;
import com.nlu.dao.entity.CauTrucDeThi;
import com.nlu.dao.entity.ChiTietDeThi;
import com.nlu.dao.entity.ChiTietDeThiPK;
import com.nlu.dao.entity.DapAn;
import com.nlu.dao.entity.DeThi;
import com.nlu.repository.CauHoiRepository;
import com.nlu.repository.CauTrucDeThiRepository;
import com.nlu.repository.DethiRepository;
import com.nlu.repository.MonHocRepository;

@Service
@Transactional
public class CauTrucDeThiServiceImpl implements CauTrucDeThiService {
	@Autowired
	CauTrucDeThiRepository cauTrucDeThiR;
	@Autowired
	SessionFactory sf;
	@Autowired
	MonHocRepository monHocR;
	@Autowired
	CauHoiRepository cauHoiR;
	@Autowired
	DethiRepository deThiR;

	@Override
	public boolean themCtCtdt(int maCtdt, int maChuong, int soLuong, byte doKho, double tongDiem) throws Exception {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();

		// CallableStatement callableStatement = connection
		// .prepareCall("INSERT INTO (MACTDT, MACHUONG, MADOKHO, SLCAUHOI,
		// TONGDIEM) VALUES (?,?,?,?,?)");
		CallableStatement callableStatement = connection.prepareCall(
				"EXECUTE  PROCEDURE_INSERTORUPDATE_CAUTRUCDETHI @MACTDT=?, @MACHUONG =?, @MADOKHO=?, @SLCAUHOI=?, @TONGDIEM=?");
		callableStatement.setInt(1, maCtdt);
		callableStatement.setInt(2, maChuong);
		callableStatement.setInt(3, doKho);
		callableStatement.setInt(4, soLuong);
		callableStatement.setDouble(5, tongDiem);
		int row = callableStatement.executeUpdate();
		if (row == 0)
			return false;
		return true;

	}

	@Override
	public CauTrucDeThi getCauTrucDeThiByIdMonHoc(int maMon) {
		return cauTrucDeThiR.getCauTrucDeThiByIdMonHoc(maMon);
	}

	@Override
	public CauTrucDeThi getCauTrucDeThiById(int maCtdt) {
		return cauTrucDeThiR.getCauTrucDeThiByIdCtdt(maCtdt);
	}

	@Override
	public boolean xoaCtCtdt(int maCtdt, int maChuong, int maDoKho) {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();

		try {
			// CallableStatement callableStatement = connection
			// .prepareCall("INSERT INTO (MACTDT, MACHUONG, MADOKHO, SLCAUHOI,
			// TONGDIEM) VALUES (?,?,?,?,?)");
			CallableStatement callableStatement = connection
					.prepareCall("Delete from CHI_TIET_CTDT where MACTDT=? AND MACHUONG=? AND MADOKHO=?");
			callableStatement.setInt(1, maCtdt);
			callableStatement.setInt(2, maChuong);
			callableStatement.setInt(3, maDoKho);
			int row = callableStatement.executeUpdate();
			if (row == 0)
				return false;
			return true;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean resetCtdt(int maCtdt) {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();

		try {
			// CallableStatement callableStatement = connection
			// .prepareCall("INSERT INTO (MACTDT, MACHUONG, MADOKHO, SLCAUHOI,
			// TONGDIEM) VALUES (?,?,?,?,?)");
			CallableStatement callableStatement = connection.prepareCall("Delete from CHI_TIET_CTDT where MACTDT=? ");
			callableStatement.setInt(1, maCtdt);
			int row = callableStatement.executeUpdate();
			if (row == 0)
				return false;
			return true;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean submit(int maCtdt) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection
				.prepareCall("UPDATE CAU_TRUC_DE_THI SET TRANGTHAI=1 where MACTDT=? ");
		callableStatement.setInt(1, maCtdt);
		int row = callableStatement.executeUpdate();
		System.out.println("Da up date dupcjw so dong  la  " + row);
		if (row == 0)
			return false;
		return true;

	}

	@Override
	public ResultSet getListCauHoiByIdMaChuong(int maMon, int maChuong, int soLuong, int maDoKho) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection
				.prepareCall("execute addDeThi @MAMON =?,@MACHUONG=?,@SL=?,@MADOKHO=?");
		callableStatement.setInt(1, maMon);
		callableStatement.setInt(2, maChuong);
		callableStatement.setInt(3, soLuong);
		callableStatement.setInt(4, maDoKho);
		ResultSet row = callableStatement.executeQuery();
		return row;
	}

	@Override
	public List<DapAn> getListDapAnByIdCauHoi(int maCH) {
		return cauTrucDeThiR.getListCauHoiByIdCauHoi(maCH);
	}

	@Override
	public ResultSet getListChuongInCtdtById(int maCtdt) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection.prepareCall(
				" select * from CHI_TIET_CTDT  JOIN CHUONG_MUC ON CHUONG_MUC.MACHUONG=CHI_TIET_CTDT.MACHUONG WHERE  CHI_TIET_CTDT.MACTDT=?");
		callableStatement.setInt(1, maCtdt);
		ResultSet row = callableStatement.executeQuery();
		return row;
	}

	@Override
	public DeThiDao getDeThiByIdMaCtdt(int maCtdt) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection.prepareCall(
				" select * from CHI_TIET_CTDT  JOIN CHUONG_MUC ON CHUONG_MUC.MACHUONG=CHI_TIET_CTDT.MACHUONG WHERE  CHI_TIET_CTDT.MACTDT=?");
		callableStatement.setInt(1, maCtdt);
		ResultSet resultSet = callableStatement.executeQuery();
		DeThiDao deThi = new DeThiDao(maCtdt, this.getCauTrucDeThiById(maCtdt).getMamon().getMamon());
		List<Chuong> listChuong = new ArrayList<>();
		while (resultSet.next()) {
			Chuong chuong = new Chuong(resultSet.getInt("MACHUONG"), resultSet.getString("TIEUDE"),
					resultSet.getString("MOTA"), resultSet.getInt("MAMON"), resultSet.getInt("SLCAUHOI"),
					resultSet.getInt("MADOKHO"), resultSet.getDouble("TONGDIEM"));
			addCauHoiIntoChuong(chuong);
			listChuong.add(chuong);
		}
		deThi.setListChuong(listChuong);

		return deThi;
	}

	@Override
	public void addCauHoiIntoChuong(Chuong chuong) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection
				.prepareCall("execute addDeThi @MAMON =?,@MACHUONG=?,@SL=?,@MADOKHO=?");
		callableStatement.setInt(1, chuong.getMaMon());
		callableStatement.setInt(2, chuong.getMaChuong());
		callableStatement.setInt(3, chuong.getSoLuong());
		callableStatement.setInt(4, chuong.getMaDoKho());
		ResultSet row = callableStatement.executeQuery();
		List<CauHoiDao> cauHoiDaos = new ArrayList<>();
		while (row.next()) {
			CauHoiDao cauHoiDao = new CauHoiDao(row.getInt("MACH"), row.getString("NOIDUNG"), row.getInt("MAMON"),
					row.getInt("MADOKHO"), row.getInt("MAGV"));
			addDapAnIntoCauHoi(cauHoiDao);
			cauHoiDaos.add(cauHoiDao);
		}
		chuong.setListCauHoi(cauHoiDaos);

	}

	@Override
	public void addDapAnIntoCauHoi(CauHoiDao cauHoi) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection
				.prepareCall("select  MACH, MADAPAN, NOIDUNG,DAPANDUNG from DAP_AN where MACH=?");
		callableStatement.setInt(1, cauHoi.getMach());
		ResultSet row = callableStatement.executeQuery();
		List<DapAnDao> dapAnDaos = new ArrayList<>();
		while (row.next()) {
			DapAnDao anDao = new DapAnDao(row.getInt("MACH"), row.getInt("MADAPAN"), row.getString("NOIDUNG"),
					row.getInt("DAPANDUNG") == 1 ? true : false);
			dapAnDaos.add(anDao);

		}
		cauHoi.setList(dapAnDaos);

	}

	@Override
	public int getSoLuongCauHoiWithDoKhoInChuong(int maChuong, int maDoKho) throws SQLException {
		SessionImpl session = (SessionImpl) sf.getCurrentSession();
		Connection connection = session.connection();
		CallableStatement callableStatement = connection
				.prepareCall("SELECT  COUNT(MACH) AS SOLUONG  FROM  CAU_HOI  WHERE MACHUONG =? AND MADOKHO =?");
		callableStatement.setInt(1, maChuong);
		callableStatement.setInt(2, maDoKho);
		ResultSet row = callableStatement.executeQuery();
		row.next();
		return row.getInt("SOLUONG");
	}

	@Override
	public boolean nopDeThi(DeThiDao deThi) throws SQLException {
		com.nlu.dao.entity.DeThi deThiSave = new com.nlu.dao.entity.DeThi();

		deThiSave.setMamon(monHocR.findOne(deThi.getMaMon()));

		deThiSave.setNgaythem(new Date(Calendar.getInstance().getTimeInMillis()));
		deThiSave.setTrangthai(false);
		//
		deThiSave = deThiR.save(deThiSave);
		System.out.println(deThiSave);

		List<ChiTietDeThi> chiTietDeThiList = new ArrayList<>();
		for (Chuong chuong : deThi.getListChuong()) {
			for (CauHoiDao cauHoi : chuong.getListCauHoi()) {
				ChiTietDeThi chiTietDeThi = new ChiTietDeThi();

				chiTietDeThi.setCauHoi(cauHoiR.findOne(cauHoi.getMach()));

				ChiTietDeThiPK chiTietDeThiPK = new ChiTietDeThiPK(deThiSave.getMadethi(), cauHoi.getMach());

				chiTietDeThi.setChiTietDeThiPK(chiTietDeThiPK);

				chiTietDeThi.setDiem(chuong.getTongDiem() / chuong.getSoLuong());
				chiTietDeThiList.add(chiTietDeThi);
			}
		}
		deThiSave.setChiTietDeThiList(chiTietDeThiList);
		try {
			deThiR.save(deThiSave);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		// SessionImpl session = (SessionImpl) sf.getCurrentSession();
		// Connection connection = session.connection();
		// CallableStatement callableStatement = connection
		// .prepareCall(" INSERT INTO DE_THI (MAMON,NGAYTHEM,TRANGTHAI) VALUES
		// (?,?,?)");
		// callableStatement.setInt(1, deThi.getMaMon());
		// callableStatement.setDate(2, new
		// java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		// int row = callableStatement.executeUpdate();
		// ResultSet generatedKeys = callableStatement.getGeneratedKeys();
		// int maCtdt = 0;
		// if (generatedKeys.next()) {
		// maCtdt = (generatedKeys.getInt(1));
		// } else {
		// throw new SQLException("Insert thất bại!");
		// }
		// for (Chuong chuong : deThi.getListChuong()) {
		// double diem = chuong.getTongDiem() / chuong.getSoLuong();
		// for (CauHoiDao cauHoi : chuong.getListCauHoi()) {
		// callableStatement = connection
		// .prepareCall("INSERT INTO CHI_TIET_DE_THI (MADETHI,MACH,DIEM) VALUES
		// (?,?,?)");
		// callableStatement.setInt(1, maCtdt);
		// callableStatement.setInt(2, cauHoi.getMach());
		// callableStatement.setDouble(3, diem);
		// int res = callableStatement.executeUpdate();
		// if (res == 0) {
		// System.out.println("Lỗi insert ");
		// break;
		// }
		// }
		// }

	}

	@Override
	public DeThi getDeThiByIdMaDeThi(int maDeThi) {
		return cauTrucDeThiR.getDeThiByIdMaDeThi(maDeThi);
	}

}

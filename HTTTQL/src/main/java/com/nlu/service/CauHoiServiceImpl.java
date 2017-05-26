package com.nlu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.entity.CauHoi;
import com.nlu.repository.CauHoiRepository;

@Service
@Transactional
public class CauHoiServiceImpl implements CauHoiService {
	@Autowired
	CauHoiRepository cauHoiRepository;
	@Autowired
	SessionFactory sf;
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<CauHoi> list(String searchnoidung, Pageable page) {
		// TODO Auto-generated method stub
		return cauHoiRepository.list(searchnoidung, page);
	}

	@Override
	public boolean themCauHoi(CauHoiDao cauhoi) {
		// TODO Auto-generated method stub
		// @noidung ntext ,
		// @machuong int ,
		// @mamon int ,
		// @madokho int ,
		// @magv int ,
		// @out int OUTPUT
		Session session = sf.getCurrentSession();

		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			CallableStatement callableStatement = conn.prepareCall("proc_add_cauhoI ? , ? , ? ,? , ? ,? ");
			callableStatement.setString(1, cauhoi.getNoidung());
			callableStatement.setInt(2, cauhoi.getMach());
			callableStatement.setInt(3, cauhoi.getMamh());
			callableStatement.setInt(4, cauhoi.getMadokho());
			callableStatement.setInt(5, cauhoi.getMagv());
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();
			int idcauhoi = callableStatement.getInt(6);
			System.out.println(idcauhoi);
			if (idcauhoi == 0) {
				conn.rollback();
				return false;
			} else {
				for (DapAnDao temp : cauhoi.getList()) {
					temp.setMach(idcauhoi);
					if (themDapAn(temp) == false) {
						System.out.println(temp.isDapandung());
						System.out.println(temp.getMach());
						System.out.println(temp.getNoidung());
						conn.rollback();
						return false;
					}
				}
			}
			conn.commit();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("lop cauhoiService ");
			return false;
		}

	}

	@Override
	public boolean themDapAn(DapAnDao dapan) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();
		try {
			CallableStatement callableStatement = conn.prepareCall("proc_add_dapan  ? , ?, ?");
			callableStatement.setInt(1, dapan.getMach());
			callableStatement.setString(2, dapan.getNoidung());
			callableStatement.setBoolean(3, dapan.isDapandung());
			ResultSet rs = callableStatement.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(int id) {
		// TODO Auto-generated method stub
		try {
			cauHoiRepository.delete(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public CauHoi findOne(int id) {
		// TODO Auto-generated method stub
		return cauHoiRepository.findOne(id);
	}

	@Override
	public boolean save(CauHoi cauhoi) {
		// TODO Auto-generated method stub
		try {
			cauHoiRepository.save(cauhoi);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

}

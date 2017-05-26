package com.nlu.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import com.nlu.dao.entity.GiangVien;
import com.nlu.repository.GiangVienRepository;
@Transactional
@Service
public class GiangVienServiceImpl  implements GiangVienService{
  @Autowired
  GiangVienRepository giangVienRepository ;
  @Autowired
  SessionFactory sf ;
  @PersistenceContext
  EntityManager em;
	@Override
	public Page<GiangVien> list(String sreach, Pageable pageRequest) {
		// TODO Auto-generated method stub
		return giangVienRepository.list(sreach, pageRequest);
	}
	@Override
	public String loginResMsg(String username, String md5Hash) {
		// TODO Auto-generated method stub
	   Session session = sf.getCurrentSession();
	   SessionImpl sessionImpl = (SessionImpl)(session) ;
	  Connection cnn = sessionImpl.connection();
	  
	  try {
		CallableStatement callableStatement = cnn.prepareCall("proc_login_GIANGVIEN_ResSmg ? , ? ");
		  callableStatement.setString(1, username);
		  callableStatement.setString(2, md5Hash);
		  ResultSet rs= callableStatement.executeQuery() ;
		  rs.next() ;
		  return rs.getString(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Đăng nhập Erro";
	}
	}

	@Override
	public GiangVien giengVienById(int id) {
		// TODO Auto-generated method stub
		return giangVienRepository
				.giangVienById(id);
	}
	@Override
	public boolean save(GiangVien gv) {
		em.getTransaction().begin();
		em.persist(gv);
		em.getTransaction().commit();
		return true;
	}

}

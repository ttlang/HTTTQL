package com.nlu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nlu.dao.entity.MonHoc;
import com.nlu.repository.MonHocRepository;
@Service
@Transactional
public class MonHocServiceImpl implements MonHocService {
  @Autowired
   MonHocRepository monHocRepository ;
  
  @Autowired
	SessionFactory sf;
	@Override
	public Page<MonHoc> list(String sreach, Pageable pageRequest) {
		// TODO Auto-generated method stub
		return monHocRepository.list(sreach, pageRequest);
	}
	
	
	@Override
	public MonHoc getMonHocByMaMonHoc(int maMonHoc) {
		Session session = sf.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<MonHoc> listMonHoc = session.createCriteria(MonHoc.class).add(Restrictions.eq("mamon", maMonHoc)).list();
		return listMonHoc.get(0);
	}


	@Override
	public MonHoc findOndById(Integer mamon) {
		// TODO Auto-generated method stub
		return monHocRepository.findOne(mamon);
	}

}

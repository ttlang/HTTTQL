
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

import com.nlu.dao.entity.BoMon;
import com.nlu.repository.BoMonRepository;

@Service
@Transactional
public class BoMonServiceImpl implements BoMonService {
	@Autowired
	BoMonRepository boMonRespository;
	@Autowired
	SessionFactory sf;

	@Override
	public Page<BoMon> list(String sreach, Pageable pageRequest) {
		return boMonRespository.list(sreach, pageRequest);
	}

	@Override
	public List<BoMon> getListBoMonByMaGV(int magv) {
		return boMonRespository.getListBoMonByMaGV(magv);
	}

	@Override
	public BoMon getBoMonbyMaBoMom(int maBoMon) {
		Session session = sf.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<BoMon> listMonHoc = session.createCriteria(BoMon.class).add(Restrictions.eq("mabomon", maBoMon)).list();
		return listMonHoc.get(0);
	}
}

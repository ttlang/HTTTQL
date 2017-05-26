
package com.nlu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.DeThi;
import com.nlu.repository.DethiRepository;

@Service
@Transactional
public class DeThiServiceImpl implements DeThiService {
   @Autowired
   DethiRepository dethiRepository ;
	@Override
	public Page<DeThi> list( Pageable page) {
		// TODO Auto-generated method stub
		return dethiRepository.list( page);
	}

   
}

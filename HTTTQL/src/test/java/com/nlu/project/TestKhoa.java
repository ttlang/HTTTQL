package com.nlu.project;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.entity.GiangVien;
import com.nlu.repository.GiangVienRepository;
import com.nlu.repository.KhoaRepository;
import com.nlu.service.GiangVienService;
import com.nlu.service.KhoaService;

@RunWith(SpringRunner.class)
@SpringBootTest

@Transactional
public class TestKhoa {
 @Autowired 
  KhoaService khoaService ;
 @Autowired 
    KhoaRepository khoaRepository ;
 @Autowired
 GiangVienService giangVienService;
 @Autowired
 GiangVienRepository giangVienRepository ;
	@Test
	public void testListKhoa(){
		System.out.println(khoaService.list("", new PageRequest(1, 10)).getSize());	
	}
	@Test 
	public void testuser(){
		
    System.out.println(giangVienService.giengVienById(2).getMonHocList().size());
	// System.out.println(giangVienService.list("%%", new PageRequest(1, 3)).getSize());
	}
	@Test
	public void login(){
		System.out.println(giangVienService.loginResMsg("quynhtranlenhu@gmail.com"	, "fd3dc2c8cfc002a42c76eaa31bd7b462"));
	}
	@Test
	@Modifying
	
	public void testSave() {
		
		GiangVien gv = giangVienRepository.getOne(1);
		 
		 gv.setMagv(gv.getMagv());
		 gv.setHogv("Bui 7777");
		 gv.setTengv("Thi Bich tram");
		 gv.setNgaysinh(new Date());
		 gv.setEmail("bichtram@gmail.com");
		 gv.setDiachi("DH NONG LAM");
		 gv.setDiachi("0981773084");
		 gv.setGioitinh(false);
//		     giangVienService.save(gv);

		 giangVienRepository.saveAndFlush(gv);
		
	}
	
	
	
}

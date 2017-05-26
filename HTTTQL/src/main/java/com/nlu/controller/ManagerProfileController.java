package com.nlu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.dao.GiangVienDao;
import com.nlu.dao.entity.GiangVien;
import com.nlu.repository.GiangVienRepository;
import com.nlu.service.GiangVienService;

@Controller
@RequestMapping(value = "/ManagerProfile")
public class ManagerProfileController {
	@Autowired
	GiangVienRepository giangVienRepository;
	@Autowired
	GiangVienService giangVienService;

	@RequestMapping(value = "")
	public String index(HttpServletRequest request) {
		if (ManagerSession.userAdmin(request) != null)
			return "ManagerProfile/ManagerProfile";
		return "login/login";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody GiangVienDao save(@RequestBody GiangVienDao giangVien, HttpServletRequest request) {
		GiangVien session = ManagerSession.userAdmin(request);
		if (session != null) {
			GiangVien gv = giangVienRepository.getOne(session.getMagv());
			gv.setHogv(giangVien.getHogv());
			gv.setTengv(giangVien.getTengv());
			gv.setNgaysinh(giangVien.getNgaysinh());
			gv.setEmail(giangVien.getEmail());
			gv.setDiachi(giangVien.getDiachi());
			gv.setDienthoai(giangVien.getDienthoai());
			gv.setGioitinh(false);
			gv.setAnhgv(giangVien.getAnhgv());
			gv.setNgaysinh(giangVien.getNgaysinh());
			giangVienRepository.saveAndFlush(gv);
			giangVien.setMagv(gv.getMagv());
			return giangVien;
		} else {
			// chua dang nhap
			return new GiangVienDao();
		}
	}
}

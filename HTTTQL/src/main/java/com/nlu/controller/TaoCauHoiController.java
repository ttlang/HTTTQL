package com.nlu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.CommonConstrants;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.entity.CauHoi;
import com.nlu.dao.entity.DapAn;
import com.nlu.dao.entity.GiangVien;
import com.nlu.dao.entity.MonHoc;
import com.nlu.service.CauHoiService;
import com.nlu.service.GiangVienService;
import com.nlu.service.MonHocService;

@Controller
@RequestMapping(value = "/taocauhoi")
public class TaoCauHoiController {

	@Autowired
	GiangVienService giangVienService;
	@Autowired
	CauHoiService cauHoiService;
	@Autowired
	MonHocService monhocService;

	@RequestMapping(value = "")
	public String index(HttpServletRequest request, Model model) {
		GiangVien gv = null;
		if ((gv = ManagerSession.userAdmin(request)) == null) {
			return "login/login";
		} else {
			model.addAttribute(CommonConstrants.MODEL_GV, gv = giangVienService.giengVienById(gv.getMagv()));

			return "taocauhoi/taocauhoi";
		}
	}

	@RequestMapping(value = "/{id}")
	public String index(@PathVariable(name = "id", required = false) int id, HttpServletRequest request, Model model) {
		GiangVien gv = null;
		if ((gv = ManagerSession.userAdmin(request)) == null) {
			return "login/login";
		} else {
			CauHoiDao cauhoi = null;
			if ((cauhoi = ManagerSession.CAU_HOI(request)) == null) {
				cauhoi = new CauHoiDao();
			}
			model.addAttribute(CommonConstrants.MODEL_GV, gv = giangVienService.giengVienById(gv.getMagv()));
			if (gv.getMonHocList().size() != 0 && id >= 0 && id < gv.getMonHocList().size()) {
				model.addAttribute(CommonConstrants.CHUONG, gv.getMonHocList().get(id).getChuongMucList());
				cauhoi.setMamh(gv.getMonHocList().get(id).getMamon());
				ManagerSession.ADD_CAU_HOI(cauhoi, request);
				model.addAttribute("msg", "Chưa tạo chương cho môn học");
				model.addAttribute("id", id);
			}
			return "taocauhoi/_popCreate";
		}
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody CauHoiDao post(@RequestBody CauHoiDao person, HttpServletRequest request) {
		System.out.println(person.getNoidung() + " " + person.getList().size());

		GiangVien gv = null;
		if ((gv = ManagerSession.userAdmin(request)) == null) {
			// thêm không thành công vi chua login
			return new CauHoiDao();
		} else {
			CauHoiDao cauhoi = null;
			if ((cauhoi = ManagerSession.CAU_HOI(request)) == null) {
				// thêm khoogn thành công vi chua có session cau hoi
				return new CauHoiDao();
			} else {
				person.setMagv(gv.getMagv());
				person.setMamh(cauhoi.getMamh());
				if (cauHoiService.themCauHoi(person)) {
					// them thanh cong
					return person;
				} else {
					return new CauHoiDao();
				}
			}
		}
	}

	@RequestMapping(value = "/xoa/{id}", method = RequestMethod.GET)
	public @ResponseBody boolean xoa(@PathVariable("id") int id) {
		CauHoi temp = null;
		if ((temp = cauHoiService.findOne(id)) != null) {
			if (temp.getTrangthai() == false) {
				 cauHoiService.xoa(id);
				return true ;
			}else{
				
				return false ;
			}
		}else{
		return false;
		}
	}

	@RequestMapping(value = "/cauhoi/{id}", method = RequestMethod.GET)
	public String _cauhoi(Model model, @PathVariable("id") int id, HttpServletRequest request) {
		GiangVien gv = null;
		if ((gv = ManagerSession.userAdmin(request)) == null) {
			return "login/login";
		} else {

			gv = giangVienService.giengVienById(gv.getMagv());
			MonHoc monhoc = monhocService.findOndById(gv.getMonHocList().get(id).getMamon());
			if (monhoc != null) {
				model.addAttribute("cauhoi", monhoc);
			}
			return "taocauhoi/_Cauhoi";
		}
	}

	@RequestMapping(value = "/themcauhoi", method = RequestMethod.POST)
	public @ResponseBody boolean themDapAn(@RequestBody DapAnDao dapan) {
		return cauHoiService.themDapAn(dapan);
	}

	@RequestMapping(value = "/chinhsua", method = RequestMethod.POST)
	public @ResponseBody boolean chinhSua(@RequestBody CauHoiDao caumoi) {
		if (caumoi != null) {
			CauHoi cauhoi = cauHoiService.findOne(caumoi.getMach());
			if (caumoi != null) {
				System.out.println(caumoi.getNoidung());
				cauhoi.setNoidung(caumoi.getNoidung());
				cauhoi.setMadokho(cauhoi.getMadokho());
				List<DapAnDao> dsmoi = caumoi.getList();
				;
				List<DapAn> dscu = cauhoi.getDapAnList();
				for (int i = 0; i < dscu.size(); i++) {
					dscu.get(i).setMach(cauhoi);
					dscu.get(i).setDapandung(dsmoi.get(i).isDapandung());
					dscu.get(i).setNoidung(dsmoi.get(i).getNoidung());
				}
				return cauHoiService.save(cauhoi);
			}
		}
		return false;
	}

	@RequestMapping(value = "/findOne/{id}", method = RequestMethod.GET)
	public String findOne(@PathVariable("id") int id , Model model) {
		CauHoi cauhoi = cauHoiService.findOne(id) ;
		 model.addAttribute("cauhoi", cauhoi);
		return "taocauhoi/_chinhsua";
	}
}

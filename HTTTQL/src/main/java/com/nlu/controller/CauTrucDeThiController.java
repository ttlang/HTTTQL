package com.nlu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.Chuong;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.DeThiDao;
import com.nlu.dao.entity.CauTrucDeThi;
import com.nlu.dao.entity.ChiTietCtdt;
import com.nlu.dao.entity.ChiTietDeThi;
import com.nlu.dao.entity.DapAn;
import com.nlu.dao.entity.DeThi;
import com.nlu.dao.entity.GiangVien;
import com.nlu.service.CauTrucDeThiService;

@Controller
public class CauTrucDeThiController {
	@Autowired
	CauTrucDeThiService cauTrucDeThiS;

	@RequestMapping(value = "/taoCauTrucDeThi/{id}", method = RequestMethod.GET)
	public String cauTrucDeThi(@PathVariable("id") int maMonHoc, HttpServletRequest request, Model model) {
		GiangVien session = ManagerSession.userAdmin(request);
		if (session == null)
			return "login/login";
		// **KIỂM TRA GIÁO VIÊN CÓ DẠY MÔN ĐÓ KHÔNG VÀ CÓ ĐƯỢC PHÂN CÔNG KHÔNG

		// ** CHƯA LÀM KHÚC NÀY
		CauTrucDeThi cauTrucDeThi = cauTrucDeThiS.getCauTrucDeThiByIdMonHoc(maMonHoc);
		if (cauTrucDeThi == null || cauTrucDeThi.getMagv() == null
				|| (session.getMagv().intValue() != cauTrucDeThi.getMagv().getMagv().intValue())) {
			return "redirect:/";
		}
		if (cauTrucDeThi.getTrangthai()) {
			return "redirect:/";
		}
		model.addAttribute("ctdt", cauTrucDeThi);
		List<ChiTietCtdt> chiTietCtdts = cauTrucDeThi.getChiTietCtdtList();
		double tongDiem = 0;
		int slCauDe = 0, slCauKho = 0;
		for (ChiTietCtdt ctCtdt : chiTietCtdts) {
			tongDiem += ctCtdt.getTongdiem();
			if (ctCtdt.getChiTietCtdtPK().getMadokho() == 1) {
				slCauKho += ctCtdt.getSlcauhoi();
			} else {
				slCauDe += ctCtdt.getSlcauhoi();
			}
		}
		model.addAttribute("tongDiem", tongDiem);
		model.addAttribute("slCauDe", slCauDe);
		model.addAttribute("slCauKho", slCauKho);
		model.addAttribute("tongCau", slCauDe + slCauKho);
		return "cautrucdethi/cautrucdethi";
	}

	@RequestMapping(value = "/themCauTrucDeThi/{id}", method = RequestMethod.POST)
	public String themCauTrucDeThi(@PathVariable("id") int maMon, WebRequest wr, HttpServletRequest request,
			RedirectAttributes model) {
		GiangVien session = ManagerSession.userAdmin(request);
		if (session == null)
			return "login/login";
		// int maMon = Integer.parseInt(wr.getParameter("ma_mon"));
		int maChuong = Integer.parseInt(wr.getParameter("ma_chuong"));
		int soLuong = Integer.parseInt(wr.getParameter("so_luong"));
		byte doKho = Byte.parseByte(wr.getParameter("do_kho"));
		double tongDiem = Double.parseDouble(wr.getParameter("tong_diem"));
		CauTrucDeThi ctdt = cauTrucDeThiS.getCauTrucDeThiByIdMonHoc(maMon);
		try {
			boolean addIsSucces = cauTrucDeThiS.themCtCtdt(ctdt.getMactdt(), maChuong, soLuong, doKho, tongDiem);
			if (addIsSucces) {
				model.addFlashAttribute("message", "Thêm chương mới vào cấu trúc đề thi thành công!");
			} else {
				model.addFlashAttribute("message", "Thêm chương mới thất bại!");
			}
		} catch (Exception e) {
			model.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/taoCauTrucDeThi/" + maMon;
	}

	@RequestMapping(value = "/xoaCtCtdt/{mactdt}/{machuong}/{madokho}", method = RequestMethod.GET)
	public String xoaCtCtdt(@PathVariable("mactdt") int maCtdt, @PathVariable("machuong") int maChuong,
			@PathVariable("madokho") int maDoKho, RedirectAttributes model, HttpServletRequest request) {
		CauTrucDeThi cauTrucDeThi = cauTrucDeThiS.getCauTrucDeThiById(maCtdt);
		GiangVien session = ManagerSession.userAdmin(request);
		if (session == null || !cauTrucDeThi.getTrangthai() || session.getMagv() != cauTrucDeThi.getMagv().getMagv()) {
			return "redirect:/";
		}
		boolean isSuccess = cauTrucDeThiS.xoaCtCtdt(maCtdt, maChuong, maDoKho);
		if (isSuccess) {
			model.addFlashAttribute("message", "Xóa thành công!");
		} else
			model.addFlashAttribute("message", "Xóa thất bại!");

		return "redirect:/taoCauTrucDeThi/" + cauTrucDeThi.getMamon().getMamon();

	}

	@RequestMapping(value = "/resetCtdt/{mactdt}", method = RequestMethod.POST)
	public String resetCtdt(@PathVariable("mactdt") int maCtdt, HttpServletRequest request, RedirectAttributes model) {
		GiangVien giangVien = ManagerSession.userAdmin(request);
		CauTrucDeThi cauTrucDeThi = cauTrucDeThiS.getCauTrucDeThiById(maCtdt);
		if (giangVien == null || !cauTrucDeThi.getTrangthai()
				|| giangVien.getMagv() != cauTrucDeThi.getMagv().getMagv()) {
			return "redirect:/";
		}
		boolean isSuccess = cauTrucDeThiS.resetCtdt(maCtdt);
		if (isSuccess) {
			model.addFlashAttribute("message", "Xóa tất cả thành công!");
		} else
			model.addFlashAttribute("message", "Xóa tất cả thất bại!");
		return "redirect:/taoCauTrucDeThi/" + cauTrucDeThi.getMamon().getMamon();

	}

	@RequestMapping(value = "/submitCtdt/{mactdt}", method = RequestMethod.POST)
	public String submitCtdt(@PathVariable("mactdt") int maCtdt, HttpServletRequest request, RedirectAttributes model)
			throws SQLException {
		GiangVien giangVien = ManagerSession.userAdmin(request);
		CauTrucDeThi cauTrucDeThi = cauTrucDeThiS.getCauTrucDeThiById(maCtdt);
		if (giangVien == null || cauTrucDeThi.getTrangthai()
				|| giangVien.getMagv() != cauTrucDeThi.getMagv().getMagv()) {
			return "redirect:/";
		}
		boolean isSuccess = cauTrucDeThiS.submit(maCtdt);
		if (isSuccess) {
			model.addFlashAttribute("message", "Bạn đã hoàn tất tạo cấu trúc đề thi!");
		} else
			model.addFlashAttribute("message", "Đã xảy ra lổi trong quá trình thực hiện!");
		return "redirect:/taoCauTrucDeThi/" + cauTrucDeThi.getMamon().getMamon();

	}

	@RequestMapping(value = "/getSoLuong", method = RequestMethod.POST)
	@ResponseBody
	public int getSoLuong(WebRequest wr) throws SQLException {
		int maChuong = Integer.parseInt(wr.getParameter("ma_chuong"));
		int maDoKho = Integer.parseInt(wr.getParameter("ma_do_kho"));
		int soLuong = cauTrucDeThiS.getSoLuongCauHoiWithDoKhoInChuong(maChuong, maDoKho);
		return soLuong;
	}

	@RequestMapping(value = "/lozz/{mactdt}", method = RequestMethod.GET)
	public @ResponseBody DeThiDao lozz(@PathVariable("mactdt") int maCtdt) throws SQLException {
		DeThiDao deThi = cauTrucDeThiS.getDeThiByIdMaCtdt(maCtdt);
		return deThi;

	}

	@RequestMapping(value = "/test/{mactdt}", method = RequestMethod.GET)
	public String testGiaoDien(@PathVariable("mactdt") int maCtdt, Model model, HttpServletRequest request)
			throws SQLException {
		DeThiDao deThi = ManagerSession.DE_THI(request);
		if (deThi == null) {
			deThi = cauTrucDeThiS.getDeThiByIdMaCtdt(maCtdt);
			ManagerSession.ADD_DE_THI(deThi, request);
		}
		model.addAttribute("deThi", deThi);
		return "cautrucdethi/chitietdethi";
	}

	@RequestMapping(value = "/lamLaiDeThi/{mactdt}", method = RequestMethod.POST)
	public String lamLai(@PathVariable("mactdt") int maCtdt, HttpServletRequest request) {
		ManagerSession.ADD_DE_THI(null, request);
		return "redirect:/test/" + maCtdt;
	}

	@RequestMapping(value = "/nopDeThi", method = RequestMethod.POST)
	public String nopDeThi(HttpServletRequest request) throws SQLException {
		DeThiDao deThi = ManagerSession.DE_THI(request);
		if (deThi == null) {
			return "login/login";
		}
		boolean isSuccess = cauTrucDeThiS.nopDeThi(deThi);

		if (isSuccess) {
			ManagerSession.ADD_CAU_HOI(null, request);
			return "login/login";
		} else
			return "redirect:/test" + deThi.getMaCtdt();

	}

	@RequestMapping(value = "/refreshChuong", method = RequestMethod.POST)
	// @ResponseBody
	public String refreshChuong(WebRequest wr, HttpServletRequest request, RedirectAttributes model)
			throws SQLException {
		DeThiDao deThi = ManagerSession.DE_THI(request);
		if (deThi == null) {
			return null;
		}
		int maChuong = Integer.parseInt(wr.getParameter("ma_chuong"));
		int maDoKho = Integer.parseInt(wr.getParameter("ma_do_kho"));
		Chuong chuong = null;
		for (Chuong chuongTmp : deThi.getListChuong()) {
			if (chuongTmp.getMaChuong() == maChuong && chuongTmp.getMaDoKho() == maDoKho) {
				chuong = chuongTmp;
				break;
			}
		}
		if (chuong == null) {
			return null;
		}
		chuong.getListCauHoi().clear();
		cauTrucDeThiS.addCauHoiIntoChuong(chuong);
		model.addFlashAttribute("chuong", chuong);
		return "redirect:/subRefreshChuong";
	}

	@RequestMapping(value = "/subRefreshChuong")
	public String subRefreshChuong() {
		return "cautrucdethi/refreshchuong";
	}

	@RequestMapping(value = "/print/{madethi}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<CauHoiDao>> print(@PathVariable("madethi") int maDeThi) {
		com.nlu.dao.entity.DeThi deThi = cauTrucDeThiS.getDeThiByIdMaDeThi(maDeThi);

		List<CauHoiDao> cauHois = new ArrayList<>();
		for (ChiTietDeThi ctDeThi : deThi.getChiTietDeThiList()) {
			CauHoiDao cauHoiDao = new CauHoiDao(ctDeThi.getCauHoi().getMach().intValue(),
					ctDeThi.getCauHoi().getNoidung(), ctDeThi.getCauHoi().getMamon().getMagv().intValue(),
					ctDeThi.getCauHoi().getMadokho().getMadokho().intValue(), ctDeThi.getCauHoi().getMagv().intValue());
			List<DapAnDao> dapAnDaos = new ArrayList<>();
			for (DapAn dapAn : ctDeThi.getCauHoi().getDapAnList()) {
				DapAnDao anDao = new DapAnDao(dapAn.getMach().getMach().intValue(), dapAn.getMadapan().intValue(),
						dapAn.getNoidung(), dapAn.getDapandung().booleanValue());
				dapAnDaos.add(anDao);
			}
			Collections.shuffle(dapAnDaos);
			cauHoiDao.setList(dapAnDaos);
			cauHois.add(cauHoiDao);
		}
		Collections.shuffle(cauHois);
		// laays list chuong rooif dùng sốc để thay đổi vị trí câu hỏi

		return new ResponseEntity<List<CauHoiDao>>(cauHois, HttpStatus.OK);
	}

	@RequestMapping(value = "/printDemo/{madethi}", method = RequestMethod.GET)
	public String printDemo(@PathVariable("madethi") int maDeThi, Model model) {
		com.nlu.dao.entity.DeThi deThi = cauTrucDeThiS.getDeThiByIdMaDeThi(maDeThi);

		List<CauHoiDao> cauHois = new ArrayList<>();
		for (ChiTietDeThi ctDeThi : deThi.getChiTietDeThiList()) {
			CauHoiDao cauHoiDao = new CauHoiDao(ctDeThi.getCauHoi().getMach().intValue(),
					ctDeThi.getCauHoi().getNoidung(), ctDeThi.getCauHoi().getMamon().getMagv().intValue(),
					ctDeThi.getCauHoi().getMadokho().getMadokho().intValue(), ctDeThi.getCauHoi().getMagv().intValue());
			List<DapAnDao> dapAnDaos = new ArrayList<>();
			for (DapAn dapAn : ctDeThi.getCauHoi().getDapAnList()) {
				DapAnDao anDao = new DapAnDao(dapAn.getMach().getMach().intValue(), dapAn.getMadapan().intValue(),
						dapAn.getNoidung(), dapAn.getDapandung().booleanValue());
				dapAnDaos.add(anDao);
			}
			Collections.shuffle(dapAnDaos);
			cauHoiDao.setList(dapAnDaos);
			cauHois.add(cauHoiDao);
		}
		Collections.shuffle(cauHois);
		model.addAttribute("listCauHoi", cauHois);
		model.addAttribute("tenMonHoc", deThi.getMamon().getTenmon());
		model.addAttribute("maMonHoc", deThi.getMamon().getMamon().intValue());
		// laays list chuong rooif dùng sốc để thay đổi vị trí câu hỏi
		return "dethi/print";
	}

}

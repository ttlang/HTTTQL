package com.nlu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.nlu.dao.Chuong;
import com.nlu.dao.entity.BoMon;
import com.nlu.dao.entity.ChuongMuc;
import com.nlu.dao.entity.CongViec;
import com.nlu.dao.entity.GiangVien;
import com.nlu.dao.entity.MonHoc;
import com.nlu.service.BoMonService;
import com.nlu.service.CongViecService;
import com.nlu.service.LoaiCongViecService;
import com.nlu.service.MonHocService;

@Controller
@RequestMapping(value = "/congviec")
public class CongViecController {
	@Autowired
	CongViecService congViecService;
	@Autowired
	BoMonService boMonService;
	@Autowired
	MonHocService monHocService;
	@Autowired
	LoaiCongViecService loaiCongViecService;

	@GetMapping("")
	public String Index(HttpServletRequest request, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "Search", defaultValue = "") String Search, HttpSession session, Model model) {
		GiangVien gv = ManagerSession.userAdmin(request);
		if (gv == null) {
			return "login";
		} else {
			Page<CongViec> pageProduct = congViecService.list("%" + Search + "%", new PageRequest(p, 2));
			int pagesCount = pageProduct.getTotalPages();

			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("page", pages);
			model.addAttribute("list", pageProduct);
			model.addAttribute("pageIndex", p);
			model.addAttribute("Search", Search);
			return "congviec/congviec";
		}
	}

	@RequestMapping(value = "/phancong")
	public String phanCong(HttpServletRequest request, Model model) {
		GiangVien gv = ManagerSession.userAdmin(request);
		if (gv == null) {
			return "login";
		} else {
			model.addAttribute("listBoMon", boMonService.getListBoMonByMaGV(gv.getMagv()));
			model.addAttribute("listLoaiCongViec", loaiCongViecService.getAll());
		}

		return "congviec/cong_viec";
	}

	/**
	 * ajax lấy danh sách môn học từ mã bộ môn
	 * 
	 * @param maBoMon
	 *            mã bộ môn
	 * @return
	 */
	@PostMapping(value = "/LayMonHoc")
	@ResponseBody
	public List<com.nlu.dao.MonHoc> getMonHoc(@RequestParam("ma_bo_mon") String maBoMon) {
		BoMon boMon = boMonService.getBoMonbyMaBoMom(Integer.parseInt(maBoMon));
		List<MonHoc> listMonHoc = boMon.getMonHocList();
		List<com.nlu.dao.MonHoc> listResult = new ArrayList<>();
		for (MonHoc monHoc : listMonHoc) {
			listResult.add(new com.nlu.dao.MonHoc(monHoc.getMamon(), monHoc.getTenmon()));
		}
		System.out.println(listResult.size());
		return listResult;
	}

	/**
	 * ajax lấy giảng viên phụ trách môn học từ mã môn học nhận vào
	 * 
	 * @param maMonHoc
	 * @return
	 */
	@PostMapping(value = "/GiangVienPhuTrach")
	@ResponseBody
	public List<com.nlu.dao.GiangVien> getListGiangVien(@RequestParam("ma_mon_hoc") String maMonHoc) {
		List<com.nlu.dao.GiangVien> result = new ArrayList<>();
		MonHoc m = monHocService.getMonHocByMaMonHoc(Integer.parseInt(maMonHoc));
		List<GiangVien> listGiangVien = m.getGiangVienList();
		for (GiangVien giangVien : listGiangVien) {
			result.add(new com.nlu.dao.GiangVien(giangVien.getMagv(), giangVien.getHogv(), giangVien.getTengv()));
		}

		return result;
	}

	@RequestMapping(value = "/layChuongCuaMon", method = RequestMethod.POST)
	@ResponseBody
	public List<Chuong> LayChuongCuaMonHoc(@RequestParam("ma_mon_hoc") int maMonHoc) {
		MonHoc m = monHocService.getMonHocByMaMonHoc(maMonHoc);
		List<ChuongMuc> listChuongMuc = m.getChuongMucList();
		List<Chuong> result = new ArrayList<>();
		for (ChuongMuc chuongMuc : listChuongMuc) {
			result.add(new Chuong(chuongMuc.getMachuong(), chuongMuc.getTieude()));
		}
		return result;
	}

	@RequestMapping(value = "/congviecthuong", method = RequestMethod.POST)
	@ResponseBody
	public String taoDeCuong(@RequestParam(value = "ds_giang_vien[]") String[] dsGiangVien, WebRequest wr,
			HttpServletRequest request) throws ParseException {

		String maMonHoc = wr.getParameter("ma_mon_hoc");
		String thoiGianBatDau = wr.getParameter("thoi_gian_bat_dau");
		String thoiGianKetThuc = wr.getParameter("thoi_gian_ket_thuc");
		String noiDungCongViec = wr.getParameter("noi_dung_cong_viec");
		String message = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date thoiGianBatdau2 = null;
		Date thoiGianKetThuc2 = null;
		try {
			thoiGianBatdau2 = formatter.parse(thoiGianBatDau);
			thoiGianKetThuc2 = formatter.parse(thoiGianKetThuc);

		} catch (Exception e) {
		}
		if (thoiGianBatdau2.after(thoiGianKetThuc2)) {
			message = "Thời gian bắt đầu phải trước thời gian kết thúc";
			return message;
		} else {

			for (int i = 0; i < dsGiangVien.length; i++) {
				message = congViecService.addCongViec(Integer.parseInt(maMonHoc), Integer.parseInt(dsGiangVien[i]), 1,
						thoiGianBatDau, thoiGianKetThuc, noiDungCongViec);

			}

			return message;
		}
	}

	@PostMapping(value = "/taodecuong")
	@ResponseBody
	public String taoDeCuong(@RequestParam(value = "ds_giang_vien[]") String[] dsGiangVien, WebRequest wr) {

		String maMonHoc = wr.getParameter("ma_mon_hoc");
		String thoiGianBatDau = wr.getParameter("thoi_gian_bat_dau");
		String thoiGianKetThuc = wr.getParameter("thoi_gian_ket_thuc");
		String noiDungCongViec = wr.getParameter("noi_dung_cong_viec");
		String message = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date thoiGianBatdau2 = null;
		Date thoiGianKetThuc2 = null;
		String soLuongDeToiDa = wr.getParameter("so_luong_de_toi_da");
		try {
			thoiGianBatdau2 = formatter.parse(thoiGianBatDau);
			thoiGianKetThuc2 = formatter.parse(thoiGianKetThuc);

		} catch (Exception e) {
		}
		if (thoiGianBatdau2.after(thoiGianKetThuc2)) {
			message = "Thời gian bắt đầu phải trước thời gian kết thúc";
			return message;
		} else {

			for (int i = 0; i < dsGiangVien.length; i++) {
				message = congViecService.addCongViec3(Integer.parseInt(maMonHoc), Integer.parseInt(dsGiangVien[i]), 3,
						thoiGianBatDau, thoiGianKetThuc, noiDungCongViec, Integer.parseInt(soLuongDeToiDa));

			}

			return message;
		}

	}

	@RequestMapping(value = "/taocauhoi")
	@ResponseBody
	public String taoCauHoi(WebRequest wr, @RequestParam(value = "ds_giang_vien[]") String[] dsGiangVien,
			@RequestParam("chuong_cua_mon[]") String[] dsChuong) {
		String maMonHoc = wr.getParameter("ma_mon_hoc");
		String thoiGianBatDau = wr.getParameter("thoi_gian_bat_dau");
		String thoiGianKetThuc = wr.getParameter("thoi_gian_ket_thuc");
		String noiDungCongViec = wr.getParameter("noi_dung_cong_viec");
		String message = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date thoiGianBatdau2 = null;
		Date thoiGianKetThuc2 = null;
		String soLuongCauHoiToiDa = wr.getParameter("so_luong_cau_hoi");
		try {
			thoiGianBatdau2 = formatter.parse(thoiGianBatDau);
			thoiGianKetThuc2 = formatter.parse(thoiGianKetThuc);

		} catch (Exception e) {
		}
		if (thoiGianBatdau2.after(thoiGianKetThuc2)) {
			message = "Thời gian bắt đầu phải trước thời gian kết thúc";
			return message;
		} else {

			for (int i = 0; i < dsGiangVien.length; i++) {
				for (int j = 0; j < dsChuong.length; j++) {
					message = congViecService.addCongViec2(Integer.parseInt(maMonHoc), Integer.parseInt(dsGiangVien[i]),
							2, thoiGianBatDau, thoiGianKetThuc, noiDungCongViec, Integer.parseInt(dsChuong[i]),
							Integer.parseInt(soLuongCauHoiToiDa));
				}

			}

			return message;
		}
	}

}

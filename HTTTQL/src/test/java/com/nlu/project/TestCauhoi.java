package com.nlu.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.CauHoiDao;
import com.nlu.dao.DapAnDao;
import com.nlu.dao.DeThiDao;
import com.nlu.repository.CauHoiRepository;
import com.nlu.service.CauHoiService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(readOnly = true)
public class TestCauhoi {

	@Autowired
	CauHoiService cauHoiService;
	@Autowired
	CauHoiRepository cauhoiRepository;

	@Test
	public void themcauhoi() {
		DapAnDao d = new DapAnDao();
		d.setDapandung(false);
		d.setNoidung("Phan nu thoai my ng");
		d.setMach(9);

		System.out.println(cauHoiService.themDapAn(d));

	}

	@Test
	public void themdapan() {
		CauHoiDao cau1 = new CauHoiDao();
		cau1.setMagv(2);
		cau1.setMach(1);
		cau1.setNoidung("ai là nhóm trưởng nhóm chúng ta  ?");
		cau1.setMamh(2);
		cau1.setMadokho(1);
		List<DapAnDao> dapan = new ArrayList<>();
		DapAnDao d1 = new DapAnDao();
		d1.setDapandung(true);
		d1.setNoidung("TRINH");
		dapan.add(d1);

		DapAnDao d2 = new DapAnDao();
		d2.setDapandung(false);
		d2.setNoidung("trang");
		dapan.add(d2);

		DapAnDao d3 = new DapAnDao();
		d3.setDapandung(false);
		d3.setNoidung("thắng");
		dapan.add(d3);

		DapAnDao d4 = new DapAnDao();
		d4.setDapandung(false);
		d4.setNoidung("lang");
		dapan.add(d4);
		cau1.setList(dapan);
		System.out.println(cauHoiService.themCauHoi(cau1));

	}

	@Test
	public void testDeThi() {
		try {
			DeThiDao deThi = new DeThiDao(0, 1, 2, null, null, false);
			System.out.println(deThi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

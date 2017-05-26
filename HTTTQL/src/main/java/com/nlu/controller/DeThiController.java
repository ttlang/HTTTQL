package com.nlu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nlu.dao.entity.DeThi;
import com.nlu.service.DeThiService;

@Controller
@RequestMapping(value = "/dethi")
public class DeThiController {
	@Autowired
	DeThiService deThiService;

	@RequestMapping(value = "")
	public String Index(Model model, @RequestParam(name = "page", defaultValue = "0") int p) {
		Page<DeThi> pageProduct = deThiService.list(new PageRequest(p, 2));
		int pagesCount = pageProduct.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;
		model.addAttribute("page", pages);
		model.addAttribute("list", pageProduct);
		model.addAttribute("pageIndex", p);

		return "dethi/dethi";
	}
}

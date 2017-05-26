package com.nlu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nlu.dao.ThongKeCauHoiDao;
import com.nlu.dao.entity.Khoa;
import com.nlu.repository.KhoaRepository;
import com.nlu.service.ThongKeService;
@Controller
@RequestMapping(value="/thongke")
public class ThongKeController {
	 @Autowired 
	 ThongKeService thongkeService ;
	 @Autowired 
	 KhoaRepository khoaRepository ;
	
	 @RequestMapping(value="")
	 public String index( Model model) {
		  model.addAttribute("khoa" ,khoaRepository.findAll());
		  List<String> tenkhoa = new ArrayList<>() ;
		  List<Integer> makhoa  = new ArrayList<>() ;
		  
		  for(Khoa khoa : khoaRepository.findAll() ) {
			 tenkhoa.add(khoa.getTenkhoa()) ;
			 makhoa.add(khoa.getMakhoa()) ;
		  }
		  model.addAttribute("tenkhoa" , tenkhoa) ;
		  model.addAttribute("makhoa" , makhoa ) ;
		 return  "thongke/thongke" ;
	 }

   @RequestMapping(value="/_duLieuThongKe", method= RequestMethod.GET)
   public  @ResponseBody  ThongKeCauHoiDao duLieuThongKe(Model model, @RequestParam( name="makhoa", defaultValue="1") int makhoa  
			  , @RequestParam(name="mabomon" , defaultValue="-1") int mabamon) {
	   ThongKeCauHoiDao thongke;
	try {
		thongke = thongkeService.thongkecauhoi(makhoa, mabamon);
		model.addAttribute("tenmon", thongke.getTenmon()) ;
	    model.addAttribute("caukho", thongke.getCaukho()) ;
	    model.addAttribute("caude", thongke.getCaude());
	   return thongke ;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 return null;
	}
		
   }
 
   @RequestMapping(value="/_select" , method = RequestMethod.GET)
    public String _select(Model model ,@RequestParam( name="makhoa", defaultValue="0") int makhoa ) {
	   for(Khoa khoa : khoaRepository.findAll() ) {
		    if(khoa.getMakhoa() ==makhoa) {
		    	 model.addAttribute("listbomon",khoa.getBoMonList());
		    	 break ;
		    }
	   }
	  
	  return "thongke/_select" ;
    }
}

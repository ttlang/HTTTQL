package com.nlu.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nlu.dao.ThongKeCauHoiDao;
@Service
@Transactional
public class ThongKeServiceImpl implements  ThongKeService {
    @Autowired 
    SessionFactory sf  ;
	@Override
	public ThongKeCauHoiDao thongkecauhoi(int makhoa, int mabomon) throws SQLException {
		// TODO Auto-generated method stub
		 Session session = sf.getCurrentSession() ;
		   SessionImpl sessionImpl = (SessionImpl)session ;
		    Connection conn = sessionImpl.connection() ;
		    CallableStatement callableStatement = conn.prepareCall((mabomon==-1)?"proc_thongkecauhoi_bomon ?  ":"proc_thongkecauhoi_bomon ? , ?");
		     callableStatement.setInt(1, makhoa);
		     if(mabomon!=-1)callableStatement.setInt(2, mabomon);	     
		      ThongKeCauHoiDao thongke = new ThongKeCauHoiDao() ;
		       List<String> tenmon = new ArrayList<>() ;
		       List<Integer> caukho  = new ArrayList<>() ;
		       
		       List<Integer> caude = new ArrayList<>() ;
		        
 		     ResultSet rs = callableStatement.executeQuery() ;
		       while(rs.next()) {
		    	  tenmon.add(rs.getString(3)) ;
		        caukho.add(rs.getInt(4)) ;
		        caude.add(rs.getInt(5));
		       }
		       thongke.setTenmon(tenmon);
		       thongke.setCaukho(caukho);
		       thongke.setCaude(caude);
		return  thongke;
	}

}

package com.nlu.dao;

import java.util.ArrayList;
import java.util.List;

public class ThongKeCauHoiDao {
  List<String> tenmon = new ArrayList<>() ;
  List<Integer> caukho = new ArrayList<>();
  List<Integer> caude  = new ArrayList<>() ;
public List<String> getTenmon() {
	return tenmon;
}
public void setTenmon(List<String> tenmon) {
	this.tenmon = tenmon;
}
public List<Integer> getCaukho() {
	return caukho;
}
public void setCaukho(List<Integer> caukho) {
	this.caukho = caukho;
}
public List<Integer> getCaude() {
	return caude;
}
public void setCaude(List<Integer> caude) {
	this.caude = caude;
}
  
	
}

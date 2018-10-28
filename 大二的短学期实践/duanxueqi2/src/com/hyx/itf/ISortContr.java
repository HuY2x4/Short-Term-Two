package com.hyx.itf;

import java.util.List;

import com.hyx.model.Sort;

public interface ISortContr {
	
	public List<Sort> getAllSort();
	
	public String[] getAllSortName(int methon);
	
   public boolean addSort(String name, String remark);
	
	public boolean updSort(String name,String remark);
	
	public boolean deleteSort(String name);
}

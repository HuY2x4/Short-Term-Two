package com.hyx.itf;

import java.util.List;

import com.hyx.model.Brand;
import com.hyx.model.Sort;

public interface IBrandContr {

	public boolean addBrand(String name, String remark);
	
	public boolean updBrand(String name,String remark);
	
	public boolean deleteBrand(String name);
	
	public Brand getBrand(String name);
	
	public List<Brand> getAllBrand();
	
	public String[] getAllBrandName(int methon);

}

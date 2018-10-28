package com.hyx.model;

public class Brand {

	private String brandName;
	private String brandRemark;
	private String sortName;
	
	
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandRemark() {
		return brandRemark;
	}
	public void setBrandRemark(String brandRemark) {
		this.brandRemark = brandRemark;
	}
	
	public static Brand currentLoginUser=null;

	
}

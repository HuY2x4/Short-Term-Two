package com.hyx.model;

public class Service {
	private int serviceId;
	private String serviceName;
	private String serviceContent;
	private int serviceLevel;
	private int price;
	private String count;
	private float time;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceContent() {
		return serviceContent;
	}
	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}
	public int getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(int serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	
}

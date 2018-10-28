package com.hyx.itf;

import java.util.List;

import com.hyx.model.Material;
import com.hyx.model.Service;
import com.hyx.util.BaseException;

public interface IServiceContr {

	public List<Service> getAllService() throws BaseException;//好像用不到
	
	public List<Service> getPartServiceByMa(int  materialId) throws BaseException;
	
	public boolean addService(Service service,int materialId) throws BaseException;//还要添加连接表里面的内容
	
	public boolean updService(Service service);
	
	public boolean delService(int  id) throws BaseException;
	
	public Service getService(int serviceId);
	
	
}

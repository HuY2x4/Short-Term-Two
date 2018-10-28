package com.hyx.itf;

import java.util.List;

import com.hyx.model.Material;
import com.hyx.model.Service;
import com.hyx.util.BaseException;

public interface IServiceContr {

	public List<Service> getAllService() throws BaseException;//�����ò���
	
	public List<Service> getPartServiceByMa(int  materialId) throws BaseException;
	
	public boolean addService(Service service,int materialId) throws BaseException;//��Ҫ������ӱ����������
	
	public boolean updService(Service service);
	
	public boolean delService(int  id) throws BaseException;
	
	public Service getService(int serviceId);
	
	
}

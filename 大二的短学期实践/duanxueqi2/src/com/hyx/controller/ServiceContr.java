package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IServiceContr;
import com.hyx.model.House;
import com.hyx.model.MSInfo;
import com.hyx.model.Material;
import com.hyx.model.Service;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class ServiceContr implements IServiceContr{

	@Override
	public List<Service> getAllService() throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list =new ArrayList<Service>();;
		try {
			Query query=null;
		    query = session.createQuery("from Service ");
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取服务信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
		
	}

	@Override
	public List<Service> getPartServiceByMa(int  materialId) throws BaseException{
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Object[]> list =new ArrayList<Object[]>();
		try {
			Query query=null;
		    query = session.createQuery("SELECT m.serviceId,m.serviceName,m.serviceContent,m.serviceLevel,m.price,m.count,m.time from Service m,MSInfo ms where m.serviceId=ms.serviceId  and materialId = ?");
		    query.setParameter(0, materialId);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取服务信息失败");
			}
		session.close();  
        sessionFactory.close();  
        List<Service> newlist =new ArrayList<Service>();
		for(int i=0;i<list.size();i++) {
			Service service1=new Service();
			for(int j=0;j<list.get(i).length;j++) {
				if(j==0) {
					service1.setServiceId((int)list.get(i)[j]);
				}
				else if(j==1){
					service1.setServiceName(String.valueOf(list.get(i)[j]));
				}
				else if(j==2){
					service1.setServiceContent(String.valueOf(list.get(i)[j]));
				}
				else if(j==3){
					service1.setServiceLevel((int)list.get(i)[j]);
				}
				else if(j==4){
					service1.setPrice((int)list.get(i)[j]);
				}
				else if(j==5){
					service1.setCount(String.valueOf(list.get(i)[j]));
				}
				else if(j==6){
					service1.setTime((float)list.get(i)[j]);
				}
				
			}
			newlist.add(service1);
		}


		return newlist;
	}

	@Override
	public boolean addService(Service service,int materialId) throws BaseException {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(service);
		tx.commit();
		session.close();
		
		
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session1 =sessionFactory.openSession();
		session1.beginTransaction();
		List<Service> list =new ArrayList<Service>();;
		try {
			Query query=null;
		    query = session1.createQuery("from Service where serviceName = ?");
		    query.setParameter(0, service.getServiceName());
	        list = query.list();
	        int serviceID=list.get(0).getServiceId();
	        MSInfo msinfo=new MSInfo();
	        msinfo.setCount(1);
	        msinfo.setMaterialId(materialId);
	        msinfo.setServiceId(serviceID);
	        session1.save(msinfo);
	        session1.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("添加服务信息失败");
			}
		session1.close();  
        sessionFactory.close();  
		
		
		
		return true;
	}

	@Override
	public Service getService(int serviceId) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Service b = (Service)session.get(Service.class, serviceId);
	
		tx.commit();
		session.close();
		return b;
		
	}

	@Override
	public boolean updService(Service service) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Service s1 = (Service)session.get(Service.class, service.getServiceId());
		s1.setCount(service.getCount());
		s1.setServiceContent(service.getServiceContent());
		s1.setServiceName(service.getServiceName());
		s1.setPrice(service.getPrice());
		s1.setServiceId(service.getServiceId());
		s1.setServiceLevel(service.getServiceLevel());
		s1.setTime(service.getTime());
		
		session.save(s1);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean delService(int id) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Service> list =new ArrayList<Service>();;
		try {
			Query query=null;
		    query = session.createQuery("Delete FROM MSInfo Where serviceId=?0");
		    query.setParameter("0", id);
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("删除服务信息失败");
			}
		session.close();  
        sessionFactory.close();  
		
		
		
		
		Session session1 = null;
		session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		Service s = (Service)session1.get(Service.class, id);
		session1.delete(s);
		tx1.commit();
		session1.close();
		
		return true;
	}
	

}

package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.startUtil;
import com.hyx.itf.IClientContr;
import com.hyx.model.Brand;
import com.hyx.model.Client;
import com.hyx.model.House;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class ClientContr implements IClientContr{

	@Override
	public boolean addClientHouse(String clientName, House house) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		
	
		session.beginTransaction();
		List<House> list =new ArrayList<House>();;
		int num=0;
		try {
			Query query=null;
		    query = session.createQuery("from House  ");
			//query.setParameter(0, house.getHouseAddress());
	        list = query.list();
	        num=list.size();
			Client client=new Client();
			client.setClientName(clientName);
			client.setHouseId(num+1);
			session.save(client);
			session.save(house);
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取房屋信息失败");
			}
		
		
		session.close();  
        sessionFactory.close();  
        startUtil.roomContr.addRoom(house.getRoom(), num+1);
        
		return true;
	}

	@Override
	public Client getClientNameById(int houseId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Client> list =new ArrayList<Client>();;
		try {
			Query query=null;
		    query = session.createQuery("from Client where houseId=?  ");
		
			query.setParameter(0,houseId);
	        list = query.list();
	       
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取房屋信息失败");
			}
		
		
		session.close();  
        sessionFactory.close();
		return list.get(0);  
	}

	@Override
	public List<Object[]> getAllClientAllInfo() throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Object[]> list =new ArrayList<Object[]>();;
		try {
			Query query=null;
		    query = session.createQuery("SELECT c.clientId,c.clientName,c.houseId,h.houseAddress,h.houseTotalArea,h.room,u.userName "
		    		+ "from Client c,House h,User u "
		    		+ "where c.houseId=h.houseID and h.userId=u.userId ");
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取客户信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

}

package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IHouseContr;
import com.hyx.model.House;
import com.hyx.model.User;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class HouseContr implements IHouseContr{

	@Override
	public List<House> getAllHouseByUser() throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<House> list =new ArrayList<House>();;
		try {
			
			
			Query query=null;
			if(User.currentLoginUser.getUserLevel()==1) {
				 query = session.createQuery("from House where userId = ? ");
				query.setParameter(0, User.currentLoginUser.getUserId());
			}
			else {
				 query = session.createQuery("from House  ");
				
			}
			
	         
	        list = query.list();
	         
	        
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取房屋信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public List<House> getHouseById(int houseId) {//如果房屋为空的情况
		// TODO Auto-generated method stub
		
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		House b = (House)session.get(House.class, houseId);
		List<House> list =new ArrayList<House>();;
		list.add(b);
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<House> getHouseByClientName(String clientName) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Object[]> list =new ArrayList<Object[]>();;
		try {
			Query query=null;
		    query = session.createQuery("SELECT h.houseId,h.houseAddress,h.houseTotalArea,h.room,h.userId from House h,Client c  where h.houseId = c.houseId and clientName like ?");
		    query.setParameter(0, "%"+clientName+"%");

	        list = query.list();
	        session.getTransaction().commit();

			}catch(Exception e) {
				throw new BaseException("获取房屋信息失败");
			}
		session.close();  
        sessionFactory.close();  
		
		List<House> newlist =new ArrayList<House>();
		for(int i=0;i<list.size();i++) {
			House house1=new House();
			for(int j=0;j<list.get(i).length;j++) {
				if(j==0) {
					house1.setHouseId((int)list.get(i)[j]);
				}
				else if(j==1){
					house1.setHouseAddress(String.valueOf(list.get(i)[j]));
				}
				else if(j==2){
					house1.setHouseTotalArea((Float)(list.get(i)[j]));
				}
				else if(j==3){
					house1.setRoom((int)list.get(i)[j]);
				}
				else if(j==4){
					house1.setUserId((int)list.get(i)[j]);
				}
			}
			newlist.add(house1);
		}


		return newlist;
	}

}

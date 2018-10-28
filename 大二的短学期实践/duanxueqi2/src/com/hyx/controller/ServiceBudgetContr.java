package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IServiceBudgetContr;
import com.hyx.model.MSBInfo;
import com.hyx.model.MaterialBudget;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class ServiceBudgetContr implements IServiceBudgetContr{

	@Override
	public List<ServiceBudget> getAllSBOfRoom(int roomId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<ServiceBudget> list =new ArrayList<ServiceBudget>();;
		try {
			Query query=null;
		    query = session.createQuery("from ServiceBudget where roomId= ?");
		    query.setParameter(0, roomId);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public List<Object[]> getAllSBOfHouse(int houseId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Object[]> list =new ArrayList<Object[]>();;
		try {
			Query query=null;
			System.out.println("1");
		    query = session.createQuery("SELECT sb.sbId,sb.serviceId,s.serviceName,m.materialId,m.materialName,sb.count,sb.time,sb.totalPrice,r.roomId,r.roomSort,r.roomRemark "
		    		+ "from ServiceBudget sb,Room r,Service s,MSInfo ms,Material m "
		    		+ "where sb.roomId=r.roomId and sb.serviceId=s.serviceId and sb.serviceId=ms.serviceId and ms.materialId=m.materialId and r.houseId= ?0");
		    		
			System.out.println("2");

		    query.setParameter("0", houseId);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public boolean addSBOfRoom(ServiceBudget sb,int mbId) throws BaseException {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(sb);
		tx.commit();
		session.close();
		
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session2 =sessionFactory.openSession();
		session2.beginTransaction();
		List<ServiceBudget> list =new ArrayList<ServiceBudget>();;
		try {
			Query query=null;
		    query = session2.createQuery("from ServiceBudget where serviceId= ?0 and roomId= ?1");
		    query.setParameter("0",sb.getServiceId() );
		    query.setParameter("1",sb.getRoomId());
	        list = query.list();
	        session2.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session2.close();  
        sessionFactory.close(); 
		
		
		
		Session session1 = null;
		session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		MSBInfo msb=new MSBInfo();
		msb.setRoomId(sb.getRoomId());
		msb.setMbId(mbId);
		msb.setSbId(list.get(0).getSbId());
		session1.save(msb);
		tx1.commit();
		session1.close();
		return true;
	}

	@Override
	public boolean updSBOfRoom(ServiceBudget sb) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		ServiceBudget b = (ServiceBudget)session.get(ServiceBudget.class, sb.getSbId());
		b.setCount(sb.getCount());
		b.setRemark(sb.getRemark());
		b.setRoomId(sb.getRoomId());
		b.setSbId(sb.getSbId());
		b.setServiceId(sb.getServiceId());
		b.setTime(sb.getTime());
		b.setTotalPrice(sb.getTotalPrice());
		session.save(b);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteSBOfRoom(int sbId,int roomId) throws BaseException {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		ServiceBudget sb = (ServiceBudget)session.get(ServiceBudget.class, sbId);
		session.delete(sb);
		tx.commit();
		session.close();
		
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session2 =sessionFactory.openSession();
		session2.beginTransaction();
		List<MSBInfo> list =new ArrayList<MSBInfo>();;
		try {
			Query query=null;
		    query = session2.createQuery(" from MSBInfo where sbId= ?0 and roomId= ?1");
		    query.setParameter("0",sbId);
		    query.setParameter("1",roomId);
		    list = query.list();
	        session2.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session2.close();  
        sessionFactory.close(); 
        
        
        Session session3 = null;
		session3 = HibernateUtil.getSession();
		Transaction tx3 = session3.beginTransaction();
		MSBInfo msb = (MSBInfo)session3.get(MSBInfo.class, list.get(0).getMsbId());
		session3.delete(msb);
		tx3.commit();
		session3.close();
		
		return true;
	}

	@Override
	public boolean hasSBOfRoom(int mbId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<MSBInfo> list =new ArrayList<MSBInfo>();;
		try {
			Query query=null;
		    query = session.createQuery("from MSBInfo where mbId= ?");
		    query.setParameter(0, mbId);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session.close();  
        sessionFactory.close();  
        if(list.size()>0) {
        	return true;
        }
		return false;
	}

}

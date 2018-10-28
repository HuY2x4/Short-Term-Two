package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.startUtil;
import com.hyx.itf.IMaterialBudgetContr;
import com.hyx.model.Brand;
import com.hyx.model.House;
import com.hyx.model.MSBInfo;
import com.hyx.model.Material;
import com.hyx.model.MaterialBudget;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class MaterialBudgetContr implements IMaterialBudgetContr{

	@Override
	public List<MaterialBudget> getAllMBOfRoom(int roomId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<MaterialBudget> list =new ArrayList<MaterialBudget>();;
		try {
			Query query=null;
		    query = session.createQuery("from MaterialBudget where roomId= ?");
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
	public List<Object[]> getAllMBOfHouse(int houseId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Object[]> list =new ArrayList<Object[]>();;
		try {
			Query query=null;
		    query = session.createQuery("SELECT mb.mbId,mb.materialId,m.materialName,mb.count,mb.price,mb.totalPrice,r.roomId,r.roomSort,r.roomRemark "
		    		+ "from MaterialBudget mb,Material m,Room r "
		    		+ "where m.materialId=mb.materialId and mb.roomId=r.roomId  and houseId= ?0");
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
	public int addMBOfRoom(MaterialBudget mb) throws BaseException {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(mb);
		tx.commit();
		session.close();
		
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session1 =sessionFactory.openSession();
		session1.beginTransaction();
		List<MaterialBudget> list =new ArrayList<MaterialBudget>();;
		try {
			Query query=null;
		    query = session1.createQuery("from MaterialBudget where materialId= ?0 and roomId= ?1");
		    query.setParameter("0",mb.getMaterialId() );
		    query.setParameter("1",mb.getRoomId());
	        list = query.list();
	        session1.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session1.close();  
        sessionFactory.close();  
        System.out.println("mb:"+list.get(0).getMbId());
		return list.get(0).getMbId();
	}

	@Override
	public boolean updMBOfRoom(MaterialBudget mb) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		MaterialBudget b = (MaterialBudget)session.get(MaterialBudget.class, mb.getMbId());
		b.setCount(mb.getCount());
		b.setMaterialId(mb.getMaterialId());
		b.setPrice(mb.getPrice());
		b.setRoomId(mb.getRoomId());
		b.setTotalPrice(mb.getTotalPrice());
		b.setMbId(mb.getMbId());
		session.save(b);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteMBOfRoom(int mbId) throws BaseException {
		// TODO Auto-generated method stub
		
		List<MSBInfo> list =new ArrayList<MSBInfo>();
		list=startUtil.msbInfoContr.getSBbyMB(mbId);
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				Session session = null;
				session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				ServiceBudget sb = (ServiceBudget)session.get(ServiceBudget.class, list.get(i).getSbId());
				session.delete(sb);
				tx.commit();
				session.close();
			}
		}
		
		
		
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		MaterialBudget mb = (MaterialBudget)session.get(MaterialBudget.class, mbId);
		session.delete(mb);
		tx.commit();
		session.close();
		return true;
		
	}

	@Override
	public int[] getAllBudgetOfHouse(int houseId) throws BaseException {
		// TODO Auto-generated method stub
		int mbPrice=0;
		int sbPrice=0;
		int allPrice=0;
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Integer> listMB =new ArrayList<Integer>();;
		List<Integer> listSB =new ArrayList<Integer>();;
		try {
			Query query=null;
			query = session.createQuery("SELECT m.totalPrice from MaterialBudget m,Room r where m.roomId=r.roomId and r.houseId= ?0");
		    query.setParameter("0", houseId);
		    listMB = query.list();
		    session.getTransaction().commit();
		    session.close();  
	        sessionFactory.close(); 

	        Configuration config1 = new Configuration().configure();
	        SessionFactory sessionFactory1 = config1.buildSessionFactory();
			Session session1 =sessionFactory1.openSession();
			session1.beginTransaction();

		    
		    
		    Query query1=null;

		    query1 = session1.createQuery("SELECT s.totalPrice from ServiceBudget s,Room r where s.roomId=r.roomId and r.houseId= ?0");
		    query1.setParameter("0", houseId);
		    listSB = query1.list();
	        session1.getTransaction().commit();
            session1.close();  
            sessionFactory1.close();  
			
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}

        for(int i=0;i<listMB.size();i++) {
        	mbPrice+=listMB.get(i);
        }

        for(int j = 0;j<listSB.size();j++) {
        	sbPrice+=listSB.get(j);
        }

        allPrice=mbPrice+sbPrice;
        int[] price=new int[3];
        price[0]=mbPrice;
        price[1]=sbPrice;
        price[2]=allPrice;
        
        
		return price;
	}

	@Override
	public int[] getAllBudgetOfRoom(int roomId) throws BaseException {
		// TODO Auto-generated method stub
		int mbPrice=0;
		int sbPrice=0;
		int allPrice=0;
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<MaterialBudget> listMB =new ArrayList<MaterialBudget>();;
		List<ServiceBudget> listSB =new ArrayList<ServiceBudget>();;
		try {
			Query query=null;
			query = session.createQuery("from MaterialBudget where  roomId= ?0");
		    query.setParameter("0", roomId);
		    listMB = query.list();
		    
		    Query query1=null;
		    query1 = session.createQuery("from ServiceBudget where   roomId= ?0");
		    query1.setParameter("0", roomId);
		    listSB = query1.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取预算信息失败");
			}
		session.close();  
        sessionFactory.close();  
        


        for(MaterialBudget mb : listMB) {
        	mbPrice+=mb.getTotalPrice();
        }
        for(ServiceBudget sb : listSB) {
        	sbPrice+=sb.getTotalPrice();
        }
        allPrice=mbPrice+sbPrice;
        int[] price=new int[3];
        price[0]=mbPrice;
        price[1]=sbPrice;
        price[2]=allPrice;
        
        
		return price;
        
	}

}

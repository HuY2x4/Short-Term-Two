package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.ISortContr;
import com.hyx.model.Brand;
import com.hyx.model.Material;
import com.hyx.model.Sort;
import com.hyx.model.User;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class SortContr implements ISortContr{

	@Override
	public List<Sort> getAllSort() {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Sort> list =new ArrayList<Sort>();;
	    Query query = session.createQuery("from Sort ");
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public boolean addSort(String name, String remark) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Sort sort=new Sort();
		sort.setSortName(name);
		sort.setSortRemark(remark);
		session.save(sort);		
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean updSort(String name, String remark) {
		// TODO Auto-generated method stub
		System.out.println("11");
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Sort sort = (Sort)session.get(Sort.class, name);
		sort.setSortRemark(remark);
		session.save(sort);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean deleteSort(String name) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Material> list =new ArrayList<Material>();;
	    Query query = session.createQuery("from Material where sortName=?0 ");
	    query.setParameter("0", name);
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
        if(list.size()>0) {
        	return false;
        }
        
        
		Session session1 = null;
		session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		Sort sort = (Sort)session1.get(Sort.class, name);
		session1.delete(sort);
		tx1.commit();
		session1.close();
		return true;
	}

	@Override
	public String[] getAllSortName(int methon) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Sort> list =new ArrayList<Sort>();;
	    Query query = session.createQuery("from Sort ");
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
        
        if(methon==1) {
        	String[] str=new String[list.size()+1];
        	for(int i=1;i<=list.size();i++) {
        		str[i]=list.get(i-1).getSortName();
        	}
        	str[0]="-全部分类-";
        	return str;
        }
        else {
        	String[] str=new String[list.size()];
        	for(int i=0;i<list.size();i++) {
        		str[i]=list.get(i).getSortName();
        	}
        
        	return str;
        }
	}

	
}

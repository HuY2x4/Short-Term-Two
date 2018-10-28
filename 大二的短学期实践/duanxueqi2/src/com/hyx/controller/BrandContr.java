package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IBrandContr;
import com.hyx.model.Brand;
import com.hyx.model.Material;
import com.hyx.model.Sort;
import com.hyx.model.User;
import com.hyx.util.HibernateUtil;

public class BrandContr implements IBrandContr{

	@Override
	public boolean addBrand(String name,String remark) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Brand brand=new Brand();
		brand.setBrandName(name);
		brand.setBrandRemark(remark);
		
		session.save(brand);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean updBrand(String name,String remark) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Brand brand = (Brand)session.get(Brand.class, name);
		brand.setBrandRemark(remark);
		session.save(brand);
		tx.commit();
		session.close();
		return true;
		
	}

	@Override
	public boolean deleteBrand(String name) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Material> list =new ArrayList<Material>();;
	    Query query = session.createQuery("from Material where brandName=?0 ");
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
		Brand brand = (Brand)session1.get(Brand.class, name);
		session1.delete(brand);
		tx1.commit();
		session1.close();
		return true;
	}

	@Override
	public Brand getBrand(String name) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Brand brand = (Brand)session.get(Brand.class, name);
		tx.commit();
		return brand;
	}

	@Override
	public List<Brand> getAllBrand() {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Brand> list =new ArrayList<Brand>();;
	    Query query = session.createQuery("from Brand ");
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public String[] getAllBrandName(int methon) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<Brand> list =new ArrayList<Brand>();;
	    Query query = session.createQuery("from Brand ");
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close(); 
        if(methon==1) {
        	String[] str=new String[list.size()+1];
        	for(int i=1;i<=list.size();i++) {
        		str[i]=list.get(i-1).getBrandName();
        	}
        	str[0]="-È«²¿Æ·ÅÆ-";
        	return str;
        }
        else {
        	String[] str=new String[list.size()];
        	for(int i=0;i<list.size();i++) {
        		str[i]=list.get(i).getBrandName();
        	}
        
        	return str;
        }
        
	}

	
}

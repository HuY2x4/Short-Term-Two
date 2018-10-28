package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.startUtil;
import com.hyx.itf.IMaterialContr;
import com.hyx.model.Brand;
import com.hyx.model.House;
import com.hyx.model.Material;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class MaterialContr implements IMaterialContr{

	@Override
	public List<Material> getAllMaterial() throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Material> list =new ArrayList<Material>();;
		try {
			Query query=null;
		    query = session.createQuery("from Material ");
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public List<Material> getPartMaterialBySB(String sort, String brand) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Material> list =new ArrayList<Material>();;
		try {
			Query query=null;
		    query = session.createQuery("from Material where sortName= ? and brandName = ?");
		    query.setParameter(0, sort);
		    query.setParameter(1, brand);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}
	
	
	public List<Material> getPartMaterialByS(String sort) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Material> list =new ArrayList<Material>();;
		try {
			Query query=null;
		    query = session.createQuery("from Material where sortName= ? ");
		    query.setParameter(0, sort);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}
	
	
	public List<Material> getPartMaterialByB(String brand) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Material> list =new ArrayList<Material>();;
		try {
			Query query=null;
		    query = session.createQuery("from Material where brandName= ? ");
		    query.setParameter(0, brand);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public boolean addMaterial(Material material) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(material);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public Material getMaterial(int materialId) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Material b = (Material)session.get(Material.class, materialId);
	
		tx.commit();
		session.close();
		return b;
	}

	@Override
	public List<Material> getMaterialByName(String name) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Material> list =new ArrayList<Material>();;
		try {
			Query query=null;
		    query = session.createQuery("from Material where materialName like ? ");
		    query.setParameter(0, "%"+name+"%");
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public boolean delMaterial(int mId) {
		// TODO Auto-generated method stub
		if(startUtil.msInfoContr.hasService(mId)) {
			return false;
		}
		
		Session session1 = null;
		session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		Material material = (Material)session1.get(Material.class, mId);
		session1.delete(material);
		tx1.commit();
		session1.close();
		return true;
	}

	@Override
	public boolean updMaterial(Material material) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Material material1 = (Material)session.get(Material.class, material.getMaterialId());
		material1.setBrandName(material.getBrandName());
		material1.setColor(material.getColor());
		material1.setMaterialName(material.getMaterialName());
		material1.setPrice(material.getPrice());
		material1.setSortName(material.getSortName());
		material1.setSpecification(material.getSpecification());
		material1.setUnit(material.getUnit());
		material1.setVersion(material.getVersion());
		
		
		session.save(material1);
		tx.commit();
		session.close();
		return true;
	}

}

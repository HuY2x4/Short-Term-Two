package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IMSInfoContr;
import com.hyx.model.MSInfo;
import com.hyx.model.Material;
import com.hyx.model.Service;
import com.hyx.util.BaseException;

public class MSInfoContr implements IMSInfoContr{

	@Override
	public MSInfo getMSByServiceId(int id) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<MSInfo> list =new ArrayList<MSInfo>();;
		try {
			Query query=null;
		    query = session.createQuery("from MSInfo where serviceId= ? ");
		    query.setParameter(0, id);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取材料信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list.get(0);
	}

	@Override
	public boolean hasService(int mId) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<MSInfo> list =new ArrayList<MSInfo>();;
	    Query query = session.createQuery("from MSInfo where msId=?0 ");
	    query.setParameter("0", mId);
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
        if(list.size()>0) {
        	return true;
        }
		return false;
	}

}

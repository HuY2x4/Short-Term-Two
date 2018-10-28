package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IMSBInfoContr;
import com.hyx.model.MSBInfo;
import com.hyx.model.MSInfo;
import com.hyx.util.BaseException;

public class MSBInfoContr implements IMSBInfoContr{

	@Override
	public List<MSBInfo> getSBbyMB(int id) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<MSBInfo> list =new ArrayList<MSBInfo>();;
		try {
			Query query=null;
		    query = session.createQuery("from MSBInfo where mbId= ? ");
		    query.setParameter(0, id);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取sb信息失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}
	

}

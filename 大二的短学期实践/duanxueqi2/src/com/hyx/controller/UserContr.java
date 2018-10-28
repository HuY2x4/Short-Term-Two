package com.hyx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IUserContr;
import com.hyx.model.Sort;
import com.hyx.model.User;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class UserContr implements IUserContr{

	@Override
	public User login(String account, String password) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		if(account==null||account.equals("")) {
			throw new BaseException("�û�������Ϊ��");
		}
		
		if(password==null||password.equals("")) {
			throw new BaseException("���벻��Ϊ��");
		}

		 Query query = session.createQuery("from User where userAccount = ? ");
        
         List<User> list = query.list();
		
		try {
			if(!list.get(0).getUserPassword().equals(password)) {
				throw new BaseException("�������");
			}
			
			session.getTransaction().commit();
				}catch(Exception e) {
				throw new BaseException("�˺Ż��������");
			}finally {
                session.close();
                sessionFactory.close();
            }
		
		return list.get(0);
	}

	@Override
	public User register(String userName,String userAccount,String userPassword,String RuserPassword) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		try {
			if(userName==null||userName.equals("")) {
				throw new BaseException("���ֲ���Ϊ��");
			}else if(userAccount==null||userAccount.equals("")) {
				throw new BaseException("�û�������Ϊ��");
			}else if(userPassword==null||userPassword.equals("")) {
				throw new BaseException("���벻��Ϊ��");
			}else if(!userPassword.equals(RuserPassword)) {
				throw new BaseException("���벻��ͬ");
			}else if(userAccount.length()<5||userAccount.length()>12) {
				throw new BaseException("����ĳ���Ӧ��5-12λ");
			}else if(userPassword.length()<5||userPassword.length()>12) {
				throw new BaseException("����ĳ���Ӧ��5-12λ");
			}
		
			User user=new User();
			List<User> list =new ArrayList<User>();;
			Query query = session.createQuery("from User where userAccount = ? ");
	         query.setParameter(0, userAccount);
	          list = query.list();
	         if(list.size()>0){
	        	 throw new BaseException("�û����Ѿ�����");
	         }
	         
	        user.setUserAccount(userAccount);
	        user.setUserLevel(1);
	        user.setUserName(userName);
	        user.setUserPassword(userPassword);
	        session.save(user); 
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("ע��ʧ��");
			}
		session.close();  
        sessionFactory.close();  
		return null;
	}

	@Override
	public void updPassword(String oldPwd,String newPwd,String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null||newPwd==null||oldPwd.equals("")||newPwd.equals("")){
			throw new BaseException("���������벻��Ϊ��");
		}
		if(!oldPwd.equals(User.currentLoginUser.getUserPassword())){
			throw new BaseException("ԭ�������");
		}
		if(!newPwd.equals(newPwd2)){
			throw new BaseException("���������벻һ��");
		}
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		User b = (User)session.get(User.class, User.currentLoginUser.getUserId());
		b.setUserPassword(newPwd);
		session.save(b);
		tx.commit();
		session.close();
	}

	@Override
	public boolean updAllInfByAdmin(String userId,String userPassword,int userLevel) throws BaseException{//Ա��Ȩ��Ϊ3
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		User b = (User)session.get(User.class, User.currentLoginUser.getUserId());
		b.setUserPassword(userPassword);
		b.setUserLevel(userLevel);
		session.save(b);
		tx.commit();
		return true;
	}

	@Override
	public User getUserByself() {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserAccount(User.currentLoginUser.getUserAccount());
		user.setUserId(User.currentLoginUser.getUserId());
		user.setUserLevel(User.currentLoginUser.getUserLevel());
		user.setUserName(User.currentLoginUser.getUserName());
		user.setUserPassword(User.currentLoginUser.getUserPassword());
		return user;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
	
		List<User> list =new ArrayList<User>();;
	    Query query = session.createQuery("from User ");
	    list = query.list();
	
       
	    session.getTransaction().commit();

		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		User b = (User)session.get(User.class, id);
	
		tx.commit();
		return b;
	}

	@Override
	public Boolean updUser(User user) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		User b = (User)session.get(User.class, user.getUserId());
		b.setUserLevel(user.getUserLevel());
		b.setUserPassword(user.getUserPassword());
		session.save(b);
		tx.commit();
		return true;
	}

	
}

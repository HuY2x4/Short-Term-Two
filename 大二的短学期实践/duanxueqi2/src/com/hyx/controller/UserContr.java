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
			throw new BaseException("用户名不能为空");
		}
		
		if(password==null||password.equals("")) {
			throw new BaseException("密码不能为空");
		}

		 Query query = session.createQuery("from User where userAccount = ? ");
        
         List<User> list = query.list();
		
		try {
			if(!list.get(0).getUserPassword().equals(password)) {
				throw new BaseException("密码错误");
			}
			
			session.getTransaction().commit();
				}catch(Exception e) {
				throw new BaseException("账号或密码错误");
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
				throw new BaseException("名字不能为空");
			}else if(userAccount==null||userAccount.equals("")) {
				throw new BaseException("用户名不能为空");
			}else if(userPassword==null||userPassword.equals("")) {
				throw new BaseException("密码不能为空");
			}else if(!userPassword.equals(RuserPassword)) {
				throw new BaseException("密码不相同");
			}else if(userAccount.length()<5||userAccount.length()>12) {
				throw new BaseException("密码的长度应在5-12位");
			}else if(userPassword.length()<5||userPassword.length()>12) {
				throw new BaseException("密码的长度应在5-12位");
			}
		
			User user=new User();
			List<User> list =new ArrayList<User>();;
			Query query = session.createQuery("from User where userAccount = ? ");
	         query.setParameter(0, userAccount);
	          list = query.list();
	         if(list.size()>0){
	        	 throw new BaseException("用户名已经存在");
	         }
	         
	        user.setUserAccount(userAccount);
	        user.setUserLevel(1);
	        user.setUserName(userName);
	        user.setUserPassword(userPassword);
	        session.save(user); 
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("注册失败");
			}
		session.close();  
        sessionFactory.close();  
		return null;
	}

	@Override
	public void updPassword(String oldPwd,String newPwd,String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null||newPwd==null||oldPwd.equals("")||newPwd.equals("")){
			throw new BaseException("两次新密码不能为空");
		}
		if(!oldPwd.equals(User.currentLoginUser.getUserPassword())){
			throw new BaseException("原密码错误");
		}
		if(!newPwd.equals(newPwd2)){
			throw new BaseException("两次新密码不一致");
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
	public boolean updAllInfByAdmin(String userId,String userPassword,int userLevel) throws BaseException{//员工权限为3
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

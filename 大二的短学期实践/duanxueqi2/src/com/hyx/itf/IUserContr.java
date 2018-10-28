package com.hyx.itf;

import java.util.List;

import com.hyx.model.User;
import com.hyx.util.BaseException;

public interface IUserContr {

	public User login(String account,String password) throws BaseException;
	
	public User register(String userName,String userAccount,String userPassword,String RuserPassword) throws BaseException;
	
	public void updPassword(String oldPwd,String newPwd,String newPwd2) throws BaseException;
	
	public boolean updAllInfByAdmin(String account,String userPassword,int userLevel)throws BaseException;
	
	public User getUserByself();
	
	public List<User> getAllUser();
	
	public Boolean updUser(User user);
	
	public User getUserById(int id);
}

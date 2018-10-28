package com.hyx.itf;

import java.util.List;

import com.hyx.model.MaterialBudget;
import com.hyx.model.ServiceBudget;
import com.hyx.util.BaseException;

public interface IServiceBudgetContr {

	public List<ServiceBudget> getAllSBOfRoom(int roomId) throws BaseException;
	
	public List<Object[]> getAllSBOfHouse(int houseId) throws BaseException;
	
	public boolean addSBOfRoom(ServiceBudget sb,int mbId) throws BaseException;
	
	public boolean updSBOfRoom(ServiceBudget sb);
	
	public boolean deleteSBOfRoom(int sbId,int roomId) throws BaseException;
	
	public boolean hasSBOfRoom(int mbId) throws BaseException;
}

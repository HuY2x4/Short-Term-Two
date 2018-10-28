package com.hyx.itf;

import java.util.List;

import com.hyx.model.MaterialBudget;
import com.hyx.util.BaseException;



public interface IMaterialBudgetContr {
	public List<MaterialBudget> getAllMBOfRoom(int roomId) throws BaseException;
	
	public List<Object[]> getAllMBOfHouse(int houseId) throws BaseException;
	
	public int addMBOfRoom(MaterialBudget mb) throws BaseException;
	
	public boolean updMBOfRoom(MaterialBudget mb);
	
	public boolean deleteMBOfRoom(int mbId) throws BaseException;
	
	public int[] getAllBudgetOfHouse(int houseId) throws BaseException;
	
	public int[] getAllBudgetOfRoom(int roomId) throws BaseException;
}

package com.hyx.itf;

import java.util.List;

import com.hyx.model.House;
import com.hyx.util.BaseException;

public interface IHouseContr {
	public List<House> getAllHouseByUser() throws BaseException;
	
	public List<House> getHouseById(int houseId);
	
	public List<House> getHouseByClientName(String clientName) throws BaseException;
	
	
	
	
}

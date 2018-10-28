package com.hyx.itf;

import java.util.List;

import com.hyx.model.Client;
import com.hyx.model.House;
import com.hyx.util.BaseException;

public interface IClientContr {
	public boolean addClientHouse(String clientName,House house) throws BaseException;//���ӺͿͻ�����Ϣ
	
	public Client getClientNameById(int houseId) throws BaseException;
	
	public List<Object[]> getAllClientAllInfo() throws BaseException;
}

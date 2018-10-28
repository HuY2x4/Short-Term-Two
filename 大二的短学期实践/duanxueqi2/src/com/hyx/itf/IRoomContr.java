package com.hyx.itf;

import java.util.List;

import com.hyx.model.Room;
import com.hyx.util.BaseException;

public interface IRoomContr {
	public boolean addRoom(int roomSort,int houseId);
	
	public boolean updRoom(int roomId,Float roomArea,String remark);
	
	public Room getRoom(int roomId);
	
	public List<Room> getRoomByHouseId(int houseId) throws BaseException;

}

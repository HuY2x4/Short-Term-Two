package com.hyx.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hyx.itf.IRoomContr;
import com.hyx.model.Brand;
import com.hyx.model.House;
import com.hyx.model.Room;
import com.hyx.model.User;
import com.hyx.util.BaseException;
import com.hyx.util.HibernateUtil;

public class RoomContr implements IRoomContr{

	@Override
	public boolean addRoom(int roomSort, int houseId) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int num=0;
		for(int i=0;i<4;i++) {
		//1室厅厨卫
			if(i==0) {
				num=(int)(roomSort/1000)%10;
			}
			else if(i==1) {
				num=(int)(roomSort/100)%10;
			}
			else  if(i==2) {
				num=(int)(roomSort/10)%10;
			}
			else  if(i==3) {
				num=(int)roomSort%10;
			}
			
			for(int j=0;j<num;j++) {
				Room room=new Room();
				room.setHouseId(houseId);
				room.setRoomSort(i+1);
				session.save(room);
			}
			
			
		}
		
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean updRoom(int roomId, Float roomArea, String remark) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Room room = (Room)session.get(Room.class, roomId);
		room.setRoomRemark(remark);
		room.setRoomArea(roomArea);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Room> getRoomByHouseId(int houseId) throws BaseException {
		// TODO Auto-generated method stub
		Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		List<Room> list =new ArrayList<Room>();;
		try {
			
			
			Query query=null;
			query = session.createQuery("from Room where houseId = ? ");
			query.setParameter(0, houseId);
	        list = query.list();
	        session.getTransaction().commit();
			}catch(Exception e) {
				throw new BaseException("获取房间列表失败");
			}
		session.close();  
        sessionFactory.close();  
		return list;
	}

	@Override
	public Room getRoom(int roomId) {
		// TODO Auto-generated method stub
		Session session = null;
		session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Room room = (Room)session.get(Room.class, roomId);
		tx.commit();
		session.close();
		return room;
		
	}
	

}

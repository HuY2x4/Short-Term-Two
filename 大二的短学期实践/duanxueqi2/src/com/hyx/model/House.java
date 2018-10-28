package com.hyx.model;

public class House {
	private int houseId;
	private String houseAddress;
	private float houseTotalArea;
	private int room;
	private int userId;
	
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public float getHouseTotalArea() {
		return houseTotalArea;
	}
	public void setHouseTotalArea(float houseTotalArea) {
		this.houseTotalArea = houseTotalArea;
	}
	public static House currentLoginUser=null;
	
}

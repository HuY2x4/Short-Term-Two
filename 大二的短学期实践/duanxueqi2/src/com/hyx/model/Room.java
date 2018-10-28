package com.hyx.model;

public class Room {
	private  int roomId;
	private float roomArea;
	private int houseId;
	private int roomSort;
	private String roomRemark;
	
	
	public String getRoomRemark() {
		return roomRemark;
	}
	public void setRoomRemark(String roomRemark) {
		this.roomRemark = roomRemark;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public float getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(float roomArea) {
		this.roomArea = roomArea;
	}
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public int getRoomSort() {
		return roomSort;
	}
	public void setRoomSort(int roomSort) {
		this.roomSort = roomSort;
	}
	
}

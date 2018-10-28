package com.hyx.itf;

import java.util.List;

import com.hyx.model.Material;
import com.hyx.util.BaseException;

public interface IMaterialContr {

	public List<Material> getAllMaterial() throws BaseException;//和下面的一样的
	
	public List<Material> getPartMaterialBySB(String sort,String brand) throws BaseException;//讨论如果是全部的情况
	
	public List<Material> getPartMaterialByS(String sort) throws BaseException;//讨论如果是全部的情况
	
	public List<Material> getPartMaterialByB(String brand) throws BaseException;
	
	public List<Material> getMaterialByName(String name) throws BaseException;
	
	public boolean addMaterial(Material material);
	
	public boolean updMaterial(Material material);
	
	public boolean delMaterial(int mId);
	
	public Material getMaterial(int materialId);
}

package com.hyx;

import com.hyx.controller.BrandContr;
import com.hyx.controller.ClientContr;
import com.hyx.controller.HouseContr;
import com.hyx.controller.MSBInfoContr;
import com.hyx.controller.MSInfoContr;
import com.hyx.controller.MaterialBudgetContr;
import com.hyx.controller.MaterialContr;
import com.hyx.controller.RoomContr;
import com.hyx.controller.ServiceBudgetContr;
import com.hyx.controller.ServiceContr;
import com.hyx.controller.SortContr;
import com.hyx.controller.UserContr;
import com.hyx.itf.IBrandContr;
import com.hyx.itf.IClientContr;
import com.hyx.itf.IHouseContr;
import com.hyx.itf.IMSBInfoContr;
import com.hyx.itf.IMSInfoContr;
import com.hyx.itf.IMaterialBudgetContr;
import com.hyx.itf.IMaterialContr;
import com.hyx.itf.IRoomContr;
import com.hyx.itf.IServiceBudgetContr;
import com.hyx.itf.IServiceContr;
import com.hyx.itf.ISortContr;
import com.hyx.itf.IUserContr;

public class startUtil {
	public static IUserContr userContr=new UserContr();
	public static IBrandContr brandContr=new BrandContr();
	public static IClientContr clientContr=new ClientContr();
	public static ISortContr sortContr=new SortContr();
	public static IHouseContr houseContr=new HouseContr();
	public static IMaterialBudgetContr materialBudgetContr=new MaterialBudgetContr();
	public static IMaterialContr materialContr=new MaterialContr();
	public static IRoomContr roomContr =new RoomContr();
	public static IServiceBudgetContr serviceBudgetContr =new ServiceBudgetContr();
	public static IServiceContr serviceContr =new ServiceContr();
	public static IMSInfoContr msInfoContr =new MSInfoContr();
	public static IMSBInfoContr msbInfoContr =new MSBInfoContr();
}

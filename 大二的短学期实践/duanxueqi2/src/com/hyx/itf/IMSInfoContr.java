package com.hyx.itf;

import com.hyx.model.MSInfo;
import com.hyx.util.BaseException;

public interface IMSInfoContr {

	public MSInfo getMSByServiceId(int id) throws BaseException;
	
	public boolean hasService(int mId);
}

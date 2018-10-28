package com.hyx.itf;

import java.util.List;

import com.hyx.model.MSBInfo;
import com.hyx.model.MSInfo;
import com.hyx.util.BaseException;

public interface IMSBInfoContr {

	public List<MSBInfo> getSBbyMB(int id) throws BaseException;

}

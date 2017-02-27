package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.frame.service.BaseService;
import cn.emagsoftware.xfb.pojo.App;
import cn.emagsoftware.xfb.pojo.SysConfig;
import cn.emagsoftware.xfb.pojo.TransLog;
import cn.emagsoftware.xfb.dao.AppSysDao;
import cn.emagsoftware.xfb.service.AppSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * .
 * <p>
 * Copyright: Copyright (c) 2014-10-23
 * <p>
 * Company: 北京幻方朗睿技术有限公司
 * <p>
 * Author: 龙欢
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class AppSysServiceImpl extends BaseService implements AppSysService {

	@Autowired
	private AppSysDao appSysDao;

	@Override
	public List<SysConfig> getSysConfig() throws Exception {
		log.debug("AppSysServiceImpl.getSysConfig");
		return appSysDao.getSysConfig();
	}

	@Override
	public App getNewestApp(int type, String channelCode) throws Exception {
		log.debug("AppSysServiceImpl.getNewestApp,parameter[{type,channelCode},{" + type + "," + channelCode + "}]");
		return appSysDao.getNewestApp(type, channelCode);
	}

	@Override
	public String getConfig(String dicCode, String dicDetailKey) throws Exception {
		log.debug("AppSysServiceImpl.getConfig,parameter[{dicCode,dicDetailKey},{" + dicCode + "," + dicDetailKey + "}]");
		return appSysDao.getConfig(dicCode, dicDetailKey);
	}

	@Override
	public void appHeartbeat(String uuid, String imie, long customerID) throws Exception {
		log.debug("AppSysServiceImpl.getConfig,parameter[{uuid,mobile,imie,customerID},{" + uuid + "," + imie + "," + customerID + "}]");
		appSysDao.appHeartbeat(uuid, imie, customerID);
	}

	@Override
	public void insertTransLog(TransLog transLog) throws Exception {
		appSysDao.insertTransLog(transLog);
	}
}
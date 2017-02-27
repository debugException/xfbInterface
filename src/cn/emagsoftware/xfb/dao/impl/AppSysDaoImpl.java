package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.frame.exception.BaseException;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.xfb.pojo.App;
import cn.emagsoftware.xfb.pojo.SysConfig;
import cn.emagsoftware.xfb.pojo.TransLog;
import cn.emagsoftware.xfb.dao.AppSysDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("appSysDao")
public class AppSysDaoImpl extends BaseDao implements AppSysDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysConfig> getSysConfig() throws Exception {
		List<SysConfig> list;
		try {
			list = (List<SysConfig>) this.getSqlMapClientTemplate().queryForList("appSys.getSysConfig");
			return list;
		} catch (Exception e) {
			logger.error("AppSysDaoImpl.getSysConfig", e);
			throw new BaseException(Constant.ERROR_CODE_9995, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
		}
	}

	@Override
	public App getNewestApp(int type, String channelCode) throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", type);
			params.put("channelCode", channelCode);
			return (App) this.getSqlMapClientTemplate().queryForObject("appSys.getNewestApp", params);
		} catch (Exception e) {
			logger.error("AppSysDaoImpl.getNewestApp", e);
			throw new BaseException(Constant.ERROR_CODE_9995, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
		}
	}

	@Override
	public String getConfig(String dicCode, String dicDetailKey) throws Exception {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dicCode", dicCode);
			param.put("dicDetailKey", dicDetailKey);

			return (String) this.getSqlMapClientTemplate().queryForObject("appSys.getDicDetailByCodekey", param);
		} catch (Exception e) {
			logger.error("AppSysDaoImpl.getConfig", e);
			throw new BaseException(Constant.ERROR_CODE_9995, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
		}
	}

	@Override
	public void appHeartbeat(String uuid, String imie, long customerID) throws Exception {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("uuid", uuid);
			param.put("imie", imie);
			param.put("customerID", customerID);
			param.put("currentTime", new Date());
			this.getSqlMapClientTemplate().insert("appSys.insertAppHeartbeat", param);
		} catch (Exception e) {
			logger.error("AppSysDaoImpl.getNewestApp", e);
			throw new BaseException(Constant.ERROR_CODE_9995, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
		}
	}

	@Override
	public void insertTransLog(TransLog transLog) throws Exception {
		this.getSqlMapClientTemplate().insert("appSys.insertTransLog", transLog);
	}

}

package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.App;
import cn.emagsoftware.xfb.pojo.SysConfig;
import cn.emagsoftware.xfb.pojo.TransLog;

import java.util.List;

public interface AppSysDao {

	/**
	 * 返回CLIENT_CONFIG字典类型的所有配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysConfig> getSysConfig() throws Exception;

	/**
	 * 根据type返回本应用的最新详细信息
	 * 
	 * @param type
	 *            1：android 2：ios
	 * @return
	 * @throws Exception
	 */
	public App getNewestApp(int type, String channelCode) throws Exception;

	/**
	 * 根据数据字典的code，key获取参数值
	 * 
	 * @param dicCode
	 * @param dicDetailKey
	 * @return
	 * @throws Exception
	 */
	public String getConfig(String dicCode, String dicDetailKey) throws Exception;

	/**
	 * 应用心跳
	 * 
	 * @param uuid
	 *            心跳会话ID
	 * @param imie
	 *            设备号
	 * @param customerID
	 *            用户ID
	 * @throws Exception
	 */
	public void appHeartbeat(String uuid, String imie, long customerID) throws Exception;

	public void insertTransLog(TransLog transLog) throws Exception;
}

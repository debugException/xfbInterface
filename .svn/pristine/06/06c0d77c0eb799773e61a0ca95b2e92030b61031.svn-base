package cn.tyiti.xfb.service.jpush.impl;   

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.JpushRecord;
import cn.tyiti.xfb.dao.jpush.IjpushInfoDao;
import cn.tyiti.xfb.service.jpush.IjpushInfoService;

/** 
 * 创建时间：2015-12-9 上午10:54:27  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：JpushInfoService.java  
 * 类说明：  
 */
@Service("jpushInfoService")  
public class JpushInfoService implements IjpushInfoService {
	@Autowired
	private IjpushInfoDao jpushInfoDao ;
	@Override
	public int insertJpushInfo(Map<String, Object> params) {
		
		JpushRecord jpushRecord = new JpushRecord();
		
		jpushRecord.setJpushtitle((String)params.get("jpushtitle"));
		jpushRecord.setJpushmessage((String)params.get("jpushmessage"));
		jpushRecord.setJpushtype((String)params.get("jpushtype"));
		jpushRecord.setJpushmsgId((String)params.get("jpushmsgId"));
		//jpushRecord.setJpushtime((String)params.get("jpushtime")); 可以取数据库时间
		jpushRecord.setJpushlinkaddress((String)params.get("jpushlinkaddress"));
		jpushRecord.setJpushdeadline((String)params.get("jpushdeadline"));
		
		jpushInfoDao.insertJpushInfo(jpushRecord);
		return 0;
	}
} 
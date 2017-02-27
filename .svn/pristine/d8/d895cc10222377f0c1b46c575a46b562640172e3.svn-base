package cn.tyiti.xfb.service.blacklist.impl;   

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.blacklist.BlistInfo;
import cn.tyiti.xfb.dao.blacklist.IblistInfoManageDao;
import cn.tyiti.xfb.service.blacklist.IblistInfoService;

/** 
 * 创建时间：2015-11-24 下午9:53:53  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：BlistInfoService.java  
 * 类说明：  
 */
@Service("blistInfoService")  
public class BlistInfoService implements IblistInfoService {
	
	@Autowired
	private IblistInfoManageDao blistInfoManageDao ;
	
	@Override
	public void insertBlistInfo(Map<String, Object> params) throws Exception {
		BlistInfo blistInfo = new BlistInfo();
		
		BeanUtils.populate(blistInfo,params);
		
		blistInfoManageDao.insert(blistInfo);
	}

}
 
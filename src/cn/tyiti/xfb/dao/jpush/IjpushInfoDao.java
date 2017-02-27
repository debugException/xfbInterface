package cn.tyiti.xfb.dao.jpush;   

import java.util.Map;

import cn.tyiti.xfb.bojo.JpushRecord;

/** 
 * 创建时间：2015-12-9 上午10:59:16  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：IjpushInfoDao.java  
 * 类说明：  
 */
public interface IjpushInfoDao {
	/**
	 * <p>Title:insertJpushInfo</p>
	 * <p>Description:添加极光推送消息到数据库</p>
	 * @param params   jpushtitle  '推送标题',jpushmessage  '推送内容',jpushtype  '推送内容',jpushlinkaddress  '推送连接地址',jpushdeadline  '活动截止时间',jpushtime  '推送时间',
	 */
	 int insertJpushInfo(JpushRecord  jpushinfo);
}
 
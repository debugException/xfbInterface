package cn.emagsoftware.xfb.service;

import java.util.List;
import java.util.Map;

import cn.emagsoftware.xfb.pojo.LoanInfo;
import cn.emagsoftware.xfb.pojo.LoanInfoBack;

/**
 * 
* 项目名称：xfbInterface_update     
* 类名称：CreditService     
* 类描述： 91征信查询    
* 创建人：shenzhiqiang    
* 创建时间：2015-11-27 上午9:56:04       
* @version      
*
 */
public interface CreditService {

	//查询个人征信
	/**
	 * 
	 * @param userId userId用户userId
	 * @param userName realName 姓名
	 * @param cardNumber idCode身份证号
	 * @throws Exception
	 */
	void queryCreditInfo(String userId,String userName,String cardNumber) throws Exception; 
	
	//保存查询结果集
	void addLoanInfo(LoanInfo loanInfo) throws Exception;

	//保存查询结果集
	String getUserIdByTrxNo(String trxNo) throws Exception;
	
	//保存查询结果集
	String getCardNumberByTrxNo(String trxNo) throws Exception; 
	
	//修改黑名单表标识位trxNo
	int updateTrxNo(Map<String, String> map) throws Exception;
	
	//收集本地数据库返回给91平台
	public List<LoanInfoBack> queryLoanInfos(Map<String, String> map) throws Exception;

}

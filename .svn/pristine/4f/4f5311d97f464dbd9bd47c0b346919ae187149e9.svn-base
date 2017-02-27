package cn.tyiti.xfb.service;

import java.util.List;

import cn.tyiti.xfb.bojo.ContactInfo;
import cn.tyiti.xfb.bojo.ContactsInfo;

public interface ContactInfoService {
	/**
	 * 增加联系人并修改自身与基本认证上两步的状态为审核中
	 * @param contactInfoList
	 * @throws Exception 
	 */
	void addContactInfo(List<ContactInfo> contactInfoList) throws Exception;
	/**
	 * 根据联系人每个id修改联系人信息
	 * @param contactInfoList
	 */
	void updateContactInfoById(List<ContactInfo> contactInfoList) throws Exception;
	/**
	 * 通过用户id获取联系人信息
	 * @param userId 
	 * @return 
	 */
	List<ContactInfo> getContactInfoListByUserId(Integer userId) throws Exception;
	
	/**
	 * 根据userId查询是否存在此数据
	 * @param userId
	 * @author shenwu
	 * @return
	 * @throws Exception
	 */
	List<ContactsInfo> getContactsInfoByUserId(Integer userId) throws Exception;
	
	/**
	 * 保存通讯录数据
	 * @param contactsInfoList
	 * @author shenwu
	 * @throws Exception
	 */
	void saveContactsInfoList(List<ContactsInfo> contactsInfoList) throws Exception;
	
}

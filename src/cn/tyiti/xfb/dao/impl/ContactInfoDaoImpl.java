package cn.tyiti.xfb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.ContactInfo;
import cn.tyiti.xfb.bojo.ContactsInfo;
import cn.tyiti.xfb.dao.ContactInfoDao;

@SuppressWarnings("deprecation")
@Repository
public class ContactInfoDaoImpl extends BaseDao implements ContactInfoDao  {

	@Override
	public void addContactInfo(List<ContactInfo> contactInfoList) throws Exception {
		this.getSqlMapClientTemplate().insert("contactinfo.insertContactinfo", contactInfoList);
	}

	@Override
	public void updateContactInfoTypeByUserId(Integer userId) throws Exception {
		this.getSqlMapClientTemplate().update("contactinfo.updateContactinfoType", userId);
	}

	@Override
	public void updateContactInfoById(ContactInfo contactInfo) throws Exception {
		this.getSqlMapClientTemplate().update("contactinfo.updateContactInfo", contactInfo);
	}

	@Override
	public List<ContactInfo> getContactInfoListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("contactinfo.getContactInfoList", userId);
	}

	/* （non-Javadoc）
	 * <p>Title: deleteContactInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ContactInfoDao#deleteContactInfo(java.lang.Integer)
	 */
	@Override
	public void deleteContactInfo(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("contactinfo.deleteContactinfo", userId);
	}
	
	/**
	 * 根据userId查询是否存在此数据
	 */
	@Override
	public List<ContactsInfo> getContactsInfoByUserId(Integer userId) {
		return this.getSqlMapClientTemplate().queryForList("contactinfo.getContactsInfoByUserId", userId);
	}
	
	/**
	 * 保存通讯录数据
	 */
	@Override
	public void saveContactsInfoList(List<ContactsInfo> contactsInfoList) throws Exception {
		this.getSqlMapClientTemplate().insert("contactinfo.saveContactsInfoList", contactsInfoList);
	}

}

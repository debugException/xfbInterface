package cn.tyiti.xfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.UserInfo;
import cn.tyiti.xfb.dao.BlackListDao;
import cn.tyiti.xfb.service.BlackListService;
import cn.tyiti.xfb.utils.blacklist.BlackListQuery;
import cn.tyiti.xfb.utils.blacklist.QlBlackInfoBlackListQuery;
import cn.tyiti.xfb.utils.blacklist.SyBadInfoBlackListQuery;

@Service
public class BlackListServiceImpl implements BlackListService{

	@Autowired
	private BlackListDao blackListDao;
	@Autowired
	private SyBadInfoBlackListQuery syBadInfoBlackListQuery;
	@Autowired
	private QlBlackInfoBlackListQuery qlBlackInfoBlackListQuery;
	@Autowired
	private BlackListQuery blackListQuery;
	@Override
	public UserInfo getUserInfoByUserId(Integer userId) throws Exception {
		return blackListDao.getUserInfoByUserId(userId);
	}
	@Override
	public void addData(UserInfo userInfo) throws Exception {
		 blackListDao.addData(userInfo);
	}
	@Override
	public List<UserInfo> getUserInfo()throws Exception{
		
		return blackListDao.getUserInfo();
	}
	@Override
	public void queryAndInsert(List<UserInfo> list) throws Exception {
		if(list.size()>0){
			blackListDao.batchInsert(list);
			for (UserInfo user : list) {
				blackListQuery.blackListQuery(user.getRealName(),user.getIdCode(), user.getUserId(), user.getLoginName());
				syBadInfoBlackListQuery.blackListQuery(user.getRealName(),user.getIdCode(), user.getUserId(), user.getLoginName());
				qlBlackInfoBlackListQuery.blackListQuery(user.getRealName(),user.getIdCode(), user.getUserId(), user.getLoginName());
			}
		}
	}
	
	
}

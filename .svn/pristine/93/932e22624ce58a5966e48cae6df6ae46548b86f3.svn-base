package cn.tyiti.xfb.service.impl;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.WcfCallUtils;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.BankCardInfo;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.dao.BankCardInfoDao;
import cn.tyiti.xfb.service.BankCardInfoService;
import cn.tyiti.xfb.utils.LogUtil;

@Service
public class BankCardInfoServiceImpl implements BankCardInfoService{
	@Autowired
	private BankCardInfoDao bankCardInfoDao;
    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private WcfCallUtils wcfCallUtils;

	@Override
	public Integer saveBankCardInfo(BankCardInfo bankCardInfo) throws Exception{
		int userId = bankCardInfo.getUserId();
		//更新图片状态为审核中
//		ImageInfo imageInfo = new ImageInfo();
//		imageInfo.setUserId(userId);
		/*A1:银行卡
        A2:信用卡*/
		String cardType = bankCardInfo.getType();
		if("A1".equals(cardType)){
			//储蓄卡
//			imageInfo.setType("A4");
		} else if("A2".equals(cardType)){
			//信用卡
//			imageInfo.setType("A5");
		} else {
			String errorMsg = "userId:"+userId+"银行卡类型为空";
			LogUtil.error(errorMsg);
			throw new Exception(errorMsg);
		}
		int result = 0;

		//缓存用户唯一标识
		String userKey = Constant.CACHE_USER_KEY + userId;
		//从缓存中获取用户信息
		SysUser user = (SysUser) memcachedClient.get(userKey);
		//开户行
		String bank = bankCardInfo.getOpeningBank()==null?"":bankCardInfo.getOpeningBank();
		result = bankCardInfoDao.saveBankCardInfo(bankCardInfo);
		wcfCallUtils.callAddBankCard(result ,user.getLoginName(), bankCardInfo.getName(), 
				bankCardInfo.getType(), bank, bankCardInfo.getCardNo());
		//更新用户审核状态为需要
		SysUser sysUser = new SysUser();
		sysUser.setId(userId);
		sysUser.setHasVerify("A1");
		bankCardInfoDao.updateUserInfoByUserId(sysUser);
//		bankCardInfoDao.updateImageVerifyState(imageInfo);
		return result;
	}
}

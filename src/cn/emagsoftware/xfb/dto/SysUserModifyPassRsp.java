package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-21
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 *
 * 密码修改的返回信息配置
 */
public class SysUserModifyPassRsp extends BaseRspBean {
	
	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}

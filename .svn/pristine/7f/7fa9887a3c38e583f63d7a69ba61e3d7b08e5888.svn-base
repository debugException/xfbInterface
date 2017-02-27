package cn.emagsoftware.frame.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.StringUtils;

import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.common.IBaseDao;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @Title: iBatis DAO基类
 */
public class BaseDao extends SqlMapClientDaoSupport implements ApplicationContextAware,IBaseDao {
	private ApplicationContext context;
	
    public ApplicationContext getContext() {
		return context;
	}
    
    /**
     * 选择数据源
     * Method description goes here.
     * @author Black
     * @date 2015-7-29 下午2:39:43
     *
     * @param name mysql:sqlMapClient sqlserver:sqlServerMapClient
     */
    public void choseSqlClient(String name) {
    	//设置默认数据源
    	if(StringUtils.isEmpty(name)){
    		name="sqlMapClient";
    	}
    	SqlMapClient client = (SqlMapClient) getContext().getBean(name);
    	setSqlMapClientTemplate(new SqlMapClientTemplate(client));
    	afterPropertiesSet();
	}

	/**
     * 注入SqlMapClient
     *
     * @param sqlMapClient
     */
    @Autowired
    public void setSqlMapClientFoJrAutowire(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public int updateUserState(Integer userId) throws Exception {
		
		return this.getSqlMapClientTemplate().update("t_sys_user.updateUserState", userId);
	}

	public int updateUserInfoByUserId(SysUser sysUser) throws Exception {
		
		return this.getSqlMapClientTemplate().update("t_sys_user.updateUserInfoByUserId", sysUser);
	}
	
	public int updateImageVerifyState(ImageInfo imageInfo) throws Exception {
		return this.getSqlMapClientTemplate().update("imageinfo.updateImageVerifyState", imageInfo);
	}
}
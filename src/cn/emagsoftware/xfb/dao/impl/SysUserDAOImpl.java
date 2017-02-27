package cn.emagsoftware.xfb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.utils.WcfCallUtils;
import cn.emagsoftware.xfb.dao.SysUserDAO;
import cn.emagsoftware.xfb.pojo.SysUser;

@Repository("sysUserDao")
public class SysUserDAOImpl extends BaseDao implements SysUserDAO {

    public SysUserDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        SysUser _key = new SysUser();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_sys_user.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(SysUser record) {
        getSqlMapClientTemplate().insert("t_sys_user.insert", record);
    }

    public void insertSelective(SysUser record) {
        getSqlMapClientTemplate().insert("t_sys_user.insertSelective", record);
    }

    public SysUser selectByPrimaryKey(Integer id) {
        SysUser _key = new SysUser();
        _key.setId(id);
        SysUser record = (SysUser) getSqlMapClientTemplate().queryForObject("t_sys_user.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(SysUser record) {
        int rows = getSqlMapClientTemplate().update("t_sys_user.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(SysUser record) {
        int rows = getSqlMapClientTemplate().update("t_sys_user.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public int getUserByloginName(SysUser user) {

        return getSqlMapClientTemplate().queryForList("t_sys_user.selectByLoginName",user).size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<SysUser> userLogin(SysUser user) {

        return (List<SysUser>)getSqlMapClientTemplate().queryForList("t_sys_user.selectByLogin", user);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<SysUser> getPorUserByRecomCode(String recomCode) {
        return (List<SysUser>)getSqlMapClientTemplate().queryForList("t_sys_user.getPorUserByRecomCode", recomCode);  //To change body of implemented methods use File | Settings | File Templates.
    }
    
    @Override
    public List<SysUser> getUserByRecomCode(String recomCode) {
        SysUser user = new SysUser();
        user.setMyCode(recomCode);
        return (List<SysUser>)getSqlMapClientTemplate().queryForList("t_sys_user.getUserByRecomCode", user);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getUserByMyCode(SysUser user) {
         return getSqlMapClientTemplate().queryForList("t_sys_user.selectByMyCode", user).size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updatePassWord(SysUser resultUser) throws Exception{
    	//更改为sqlserver数据源
		/*choseSqlClient("sqlServerMapClient");
		getSqlMapClientTemplate().update("t_multi_sqlserver.updatePWByLoginName",resultUser);
		//改回默认mysql数据源
		choseSqlClient("");*/
		//To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_sys_user.updatePassWord",resultUser);
    }

    @Override
    public List<SysUser> getUserByLoinName(String loginName) {
        SysUser _key = new SysUser();
        _key.setLoginName(loginName);
        return getSqlMapClientTemplate().queryForList("t_sys_user.selectByLoginName",_key);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateRealNameByUsrId(SysUser sysUser) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_sys_user.updateRealNameByUsrId",sysUser);
    }

    @Override
    public Integer getRuralBrokerByUserId(SysUser recomUser) {
        return (Integer) getSqlMapClientTemplate().queryForObject("t_sys_user.getRuralBrokerByUserId", recomUser);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer getCountByRecomCode(String myCode) {
        SysUser recomUser = new SysUser();
        recomUser.setRecomCode(myCode);
        return (Integer) getSqlMapClientTemplate().queryForObject("t_sys_user.getCountByRecomCode", recomUser);//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateRecomNum(SysUser recomUser) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_sys_user.updateRecomNum", recomUser);
    }

    @Override
    public Integer getUserByLNCN(SysUser user) {
        return (Integer)getSqlMapClientTemplate().queryForObject("t_sys_user.getCountBylNCN",user);  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public void insertBazaarRegister(Map paramMap) {
		//更改为sqlserver数据源
		choseSqlClient("sqlServerMapClient");
		getSqlMapClientTemplate().queryForObject("t_multi_sqlserver.callsBazaarRegister",paramMap);
		//改回默认mysql数据源
		choseSqlClient("");
	}

    @Override
    public void updateStatusByLoginName(SysUser sysUser) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_sys_user.updateStatusByLoginName", sysUser);
    }

	@Override
	public SysUser getUserByLoginCard(SysUser user) {
		return (SysUser)getSqlMapClientTemplate().queryForObject("t_sys_user.getUserByLoginCard",user);
	}
}

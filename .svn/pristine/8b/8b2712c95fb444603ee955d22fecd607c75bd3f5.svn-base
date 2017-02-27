package cn.emagsoftware.xfb.dao;

import java.util.List;
import java.util.Map;

import cn.emagsoftware.xfb.pojo.SysUser;

public interface SysUserDAO {

    int deleteByPrimaryKey(Integer id);

    void insert(SysUser record);

    void insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    int getUserByloginName(SysUser user);

    List<SysUser> userLogin(SysUser user);

    List<SysUser> getUserByRecomCode(String recomCode);
    int getUserByMyCode(SysUser user);

    void updatePassWord(SysUser resultUser) throws Exception;

    List<SysUser> getUserByLoinName(String loginName);

    void updateRealNameByUsrId(SysUser sysUser);

    Integer getRuralBrokerByUserId(SysUser recomUser);

    Integer getCountByRecomCode(String myCode);

    void updateRecomNum(SysUser recomUser);
    
    Integer getUserByLNCN(SysUser user);
    
    SysUser getUserByLoginCard(SysUser user);
    
    List<SysUser> getPorUserByRecomCode(String myCode);
    
    void insertBazaarRegister(Map paramMap);
//    根据登录名修改用户状态
    void updateStatusByLoginName(SysUser sysUser);
}

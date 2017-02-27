package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.emagsoftware.xfb.pojo.UserAuthinfo;

import java.util.List;


public interface MemberInfoDAO{
    int deleteByPrimaryKey(Integer id);

    void insert(MemberInfo record);

    void insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);

    void updateScore(MemberInfo userInfo);

    MemberInfo getMemerByUserid(Integer userID);

    void updateUseScore(MemberInfo memberInfo);

    void updateImagePathByUserId(MemberInfo memberInfo);

    void updateScoreAndInfo(MemberInfo requestMemberinfo);

    List<UserAuthinfo> getUserAuthinfoList(UserAuthinfo userAuthinfo);

    MemberInfo getMemerByCardId(String cardId);


    void updateUserType(SysUser user);
}
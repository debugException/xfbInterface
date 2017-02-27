package cn.emagsoftware.xfb.service;

import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.emagsoftware.xfb.pojo.UserAuthinfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午7:35
 * To change this template use File | Settings | File Templates.
 */
public interface MemberInfoService {

    public  void updateScore(MemberInfo userInfo)throws Exception;

    public MemberInfo getMemerByUserid(Integer userID)throws Exception;

    public void updateUseScore(MemberInfo memberInfo)throws Exception;

    public void updateImagePathByUserId(MemberInfo memberInfo)throws Exception;

    public void updateScoreAndInfo(MemberInfo requestMemberinfo)throws Exception;

    public void addMemberInfo(MemberInfo memberInfo)throws Exception;
    public MemberInfo getMemerByCardId(String cardId)throws Exception;
    public List<UserAuthinfo> getUserAuthinfoList(UserAuthinfo userAuthinfo);


    public  void updateUserType(SysUser user)throws Exception;
}

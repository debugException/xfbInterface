package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.xfb.dao.MemberInfoDAO;
import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.emagsoftware.xfb.pojo.UserAuthinfo;
import cn.emagsoftware.xfb.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MemberInfoServiceImpl implements MemberInfoService {

    @Autowired
    private MemberInfoDAO memberInfoDAO;

    @Override
    public void updateScore(MemberInfo userInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.

        memberInfoDAO.updateScore(userInfo);
    }

    @Override
    public MemberInfo getMemerByUserid(Integer userID) throws Exception {
        return memberInfoDAO.getMemerByUserid(userID);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUseScore(MemberInfo memberInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        memberInfoDAO.updateUseScore(memberInfo);
    }

    @Override
    public void updateImagePathByUserId(MemberInfo memberInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        memberInfoDAO.updateImagePathByUserId(memberInfo);
    }

    @Override
    public void updateScoreAndInfo(MemberInfo requestMemberinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        memberInfoDAO.updateScoreAndInfo(requestMemberinfo);
    }

    @Override
    public void addMemberInfo(MemberInfo memberInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        memberInfoDAO.insert(memberInfo);
    }

    @Override
    public MemberInfo getMemerByCardId(String cardId) throws Exception {

        return memberInfoDAO. getMemerByCardId(cardId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UserAuthinfo> getUserAuthinfoList(UserAuthinfo userAuthinfo) {
        return memberInfoDAO.getUserAuthinfoList(userAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUserType(SysUser user) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        memberInfoDAO.updateUserType(user);

    }
}
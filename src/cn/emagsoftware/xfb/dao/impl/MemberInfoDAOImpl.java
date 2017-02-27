package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.MemberInfoDAO;
import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.emagsoftware.xfb.pojo.UserAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("memberInfoDAO")
public class MemberInfoDAOImpl extends BaseDao implements MemberInfoDAO {

    public MemberInfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        MemberInfo _key = new MemberInfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_member_baseinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(MemberInfo record) {
        getSqlMapClientTemplate().insert("t_member_baseinfo.insert", record);
    }

    public void insertSelective(MemberInfo record) {
        getSqlMapClientTemplate().insert("t_member_baseinfo.insertSelective", record);
    }

    public MemberInfo selectByPrimaryKey(Integer id) {
        MemberInfo _key = new MemberInfo();
        _key.setId(id);
        MemberInfo record = (MemberInfo) getSqlMapClientTemplate().queryForObject("t_member_baseinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(MemberInfo record) {
        int rows = getSqlMapClientTemplate().update("t_member_baseinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(MemberInfo record) {
        int rows = getSqlMapClientTemplate().update("t_member_baseinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public void updateScore(MemberInfo userInfo) {
        //To change body of implemented methods use File | Settings | File Templates.

        getSqlMapClientTemplate().update("t_member_baseinfo.updateScore", userInfo);
    }

    @Override
    public MemberInfo getMemerByUserid(Integer userID) {
        MemberInfo _key = new MemberInfo();
        _key.setSysUserid(userID);
        List<MemberInfo> list =  getSqlMapClientTemplate().queryForList("t_member_baseinfo.getMemerByUserid",_key);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUseScore(MemberInfo memberInfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_member_baseinfo.updateUseScore", memberInfo);
    }

    @Override
    public void updateImagePathByUserId(MemberInfo memberInfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_member_baseinfo.updateImagePathByUserId", memberInfo);
    }

    @Override
    public void updateScoreAndInfo(MemberInfo requestMemberinfo) {
        //To change body of implemented methods use File | Settings | File Templates.

        getSqlMapClientTemplate().update("t_member_baseinfo.updateScoreAndInfo", requestMemberinfo);
    }

    @Override
    public List<UserAuthinfo> getUserAuthinfoList(UserAuthinfo userAuthinfo) {
        return  getSqlMapClientTemplate().queryForList("t_member_baseinfo.userAuthinfoList", userAuthinfo);
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MemberInfo getMemerByCardId(String cardId) {
        MemberInfo _key = new MemberInfo();
        _key.setIdNumber(cardId);
        List<MemberInfo> list =  getSqlMapClientTemplate().queryForList("t_member_baseinfo.getMemerByCardId",_key);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;   //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUserType(SysUser user) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_member_baseinfo.updateUserType",user);
    }
}
package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.xfb.dao.*;
import cn.emagsoftware.xfb.pojo.*;
import cn.emagsoftware.xfb.service.UserAuthinfoInfoService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserAuthinfoInfoServiceImpl implements UserAuthinfoInfoService {

    @Autowired
    private SinaAuthinfoDAO sinaAuthinfoDAO;

    @Autowired
    private TaobaoAuthinfoDAO taobaoAuthinfoDAO;

    @Autowired
    private QqAuthinfoDAO qqAuthinfoDAO;

    @Autowired
    private MarryAuthinfoDAO marryAuthinfoDAO;
    @Autowired
    private DriveAuthinfoDAO driveAuthinfoDAO;
    @Autowired
    private JobcertAuthinfoDAO jobcertAuthinfoDAO;
    @Autowired
    private XuexingAuthinfoDAO xuexingAuthinfoDAO;
    @Autowired
    private EmailAuthinfoDAO emailAuthinfoDAO;
    @Autowired
    private BluecardAuthinfoDAO bluecardAuthinfoDAO;




    @Override
    public boolean checkSinaAuthinfo(SinaAuthinfo userInfo) throws Exception {
        List<SinaAuthinfo> list = sinaAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sinaAuthinfo(SinaAuthinfo userInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        sinaAuthinfoDAO.insert(userInfo);
    }

    @Override
    public SinaAuthinfo getSinaAuthinfo(SinaAuthinfo userInfo) throws Exception {

        List<SinaAuthinfo> list = sinaAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TaobaoAuthinfo getTaobaoAuthinfo(TaobaoAuthinfo userInfo) throws Exception {

        List<TaobaoAuthinfo> list = taobaoAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QqAuthinfo getQqAuthinfo(QqAuthinfo userInfo) throws Exception {

        List<QqAuthinfo> list = qqAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean checkQqAuthinfo(QqAuthinfo userInfo) throws Exception {
        List<QqAuthinfo> list = qqAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void qqAuthinfo(QqAuthinfo userInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        qqAuthinfoDAO.insert(userInfo);
    }

    @Override
    public boolean checkTaobaoAuthinfo(TaobaoAuthinfo userInfo) throws Exception {
        List<TaobaoAuthinfo> list = taobaoAuthinfoDAO.getListByUserAndCredit(userInfo);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void taobaoAuthinfo(TaobaoAuthinfo userInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        taobaoAuthinfoDAO.insert(userInfo);
    }

    @Override
    public MarryAuthinfo getMarryAuthinfoByUserid(Integer id) throws Exception {
         //To change body of implemented methods use File | Settings | File Templates.
        MarryAuthinfo marryAuthinfo = new MarryAuthinfo();
        marryAuthinfo.setSysUserid(id);
        List<MarryAuthinfo> marryAuthinfoList = marryAuthinfoDAO.getMarryAuthinfByUserid(marryAuthinfo);
        if(marryAuthinfoList!=null&&marryAuthinfoList.size()>0){
            return marryAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void marryAuthinfo(MarryAuthinfo requestMarryAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        marryAuthinfoDAO.marryAuthinfo(requestMarryAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(MarryAuthinfo marryAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        marryAuthinfoDAO.updateImagePathByUserId(marryAuthinfo);
    }

    @Override
    public void addMarryAuthinfo(MarryAuthinfo marryAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        marryAuthinfoDAO.insert(marryAuthinfo);
    }


    @Override
    public DriveAuthinfo getDriveAuthinfoByUserid(Integer id) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        DriveAuthinfo driveAuthinfo = new DriveAuthinfo();
        driveAuthinfo.setSysUserid(id);
        List<DriveAuthinfo> driveAuthinfoList = driveAuthinfoDAO.getDriveAuthinfoByUserid(driveAuthinfo);
        if(driveAuthinfoList!=null&&driveAuthinfoList.size()>0){
            return driveAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void driveAuthinfo(DriveAuthinfo requestDriveAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        driveAuthinfoDAO.driveAuthinfo(requestDriveAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(DriveAuthinfo driveAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        driveAuthinfoDAO.updateImagePathByUserId(driveAuthinfo);
    }

    @Override
    public void addDriveAuthinfo(DriveAuthinfo driveAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        driveAuthinfoDAO.insert(driveAuthinfo);
    }


    @Override
    public JobcertAuthinfo getJobcertAuthinfoByUserid(Integer id) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        JobcertAuthinfo jobcertAuthinfo = new JobcertAuthinfo();
        jobcertAuthinfo.setSysUserid(id);
        List<JobcertAuthinfo> jobcertAuthinfoList = jobcertAuthinfoDAO.getJobcertAuthinfoByUserid(jobcertAuthinfo);
        if(jobcertAuthinfoList!=null&&jobcertAuthinfoList.size()>0){
            return jobcertAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void jobcertAuthinfo(JobcertAuthinfo requestJobcertAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        jobcertAuthinfoDAO.jobcertAuthinfo(requestJobcertAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(JobcertAuthinfo jobcertAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        jobcertAuthinfoDAO.updateImagePathByUserId(jobcertAuthinfo);
    }

    @Override
    public void addJobcertAuthinfo(JobcertAuthinfo jobcertAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        jobcertAuthinfoDAO.insert(jobcertAuthinfo);
    }


    @Override
    public XuexingAuthinfo getXuexingAuthinfoByUserid(Integer id) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        XuexingAuthinfo xuexingAuthinfo = new XuexingAuthinfo();
        xuexingAuthinfo.setSysUserid(id);
        List<XuexingAuthinfo> xuexingAuthinfoList = xuexingAuthinfoDAO.getXuexingAuthinfoByUserid(xuexingAuthinfo);
        if(xuexingAuthinfoList!=null&&xuexingAuthinfoList.size()>0){
            return xuexingAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void xuexingAuthinfo(XuexingAuthinfo requestXuexingAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        xuexingAuthinfoDAO.xuexingAuthinfo(requestXuexingAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(XuexingAuthinfo xuexingAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        xuexingAuthinfoDAO.updateImagePathByUserId(xuexingAuthinfo);
    }

    @Override
    public void addXuexingAuthinfo(XuexingAuthinfo xuexingAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        xuexingAuthinfoDAO.insert(xuexingAuthinfo);
    }


    @Override
    public EmailAuthinfo getEmailAuthinfoByUserid(Integer id) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        EmailAuthinfo  emailAuthinfo = new EmailAuthinfo();
        emailAuthinfo.setSysUserid(id);
        List<EmailAuthinfo>  emailAuthinfoList =  emailAuthinfoDAO.getEmailAuthinfoByUserid( emailAuthinfo);
        if( emailAuthinfoList!=null&& emailAuthinfoList.size()>0){
            return  emailAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public JobcertAuthinfo getJobcertAuthinfoByUseridAndType(Integer userId, Integer authinfoType) throws Exception {
        JobcertAuthinfo jobcertAuthinfo = new JobcertAuthinfo();
        jobcertAuthinfo.setSysUserid(userId);
        jobcertAuthinfo.setCardType(authinfoType+"");
        List<JobcertAuthinfo> jobcertAuthinfoList = jobcertAuthinfoDAO.getJobcertAuthinfoByUseridAndType(jobcertAuthinfo);
        if(jobcertAuthinfoList!=null&&jobcertAuthinfoList.size()>0){
            return jobcertAuthinfoList.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void  emailAuthinfo(EmailAuthinfo requestEmailAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        emailAuthinfoDAO. emailAuthinfo(requestEmailAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(EmailAuthinfo  emailAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        emailAuthinfoDAO.updateImagePathByUserId( emailAuthinfo);
    }

    @Override
    public void addEmailAuthinfo(EmailAuthinfo  emailAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        emailAuthinfoDAO.insert( emailAuthinfo);
    }


    @Override
    public BluecardAuthinfo getBluecardAuthinfoByUserid(Integer id) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        BluecardAuthinfo  bluecardAuthinfo = new BluecardAuthinfo();
        bluecardAuthinfo.setSysUserid(id);
        List<BluecardAuthinfo>  bluecardAuthinfoList =  bluecardAuthinfoDAO.getBluecardAuthinfoByUserid( bluecardAuthinfo);
        if( bluecardAuthinfoList!=null&& bluecardAuthinfoList.size()>0){
            return  bluecardAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void  bluecardAuthinfo(BluecardAuthinfo requestBluecardAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        bluecardAuthinfoDAO.insert(requestBluecardAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(BluecardAuthinfo  bluecardAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        bluecardAuthinfoDAO.updateImagePathByUserId( bluecardAuthinfo);
    }

    @Override
    public void addBluecardAuthinfo(BluecardAuthinfo  bluecardAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        bluecardAuthinfoDAO.insert( bluecardAuthinfo);
    }

    @Override
    public SinaAuthinfo getSinaAuthinfoByUserid(Integer id) throws Exception {
        SinaAuthinfo  sinaAuthinfo = new SinaAuthinfo();
        sinaAuthinfo.setSysUserid(id);
        List<SinaAuthinfo> sinaAuthinfoList =  sinaAuthinfoDAO.getSinaAuthinfoByUserid( sinaAuthinfo);
        if( sinaAuthinfoList!=null&& sinaAuthinfoList.size()>0){
            return  sinaAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public QqAuthinfo getQqAuthinfoByUserid(Integer id) throws Exception {
        QqAuthinfo  qqAuthinfo = new QqAuthinfo();
        qqAuthinfo.setSysUserid(id);
        List<QqAuthinfo>  qqAuthinfoList =  qqAuthinfoDAO.getQqAuthinfoByUserid( qqAuthinfo);
        if( qqAuthinfoList!=null&& qqAuthinfoList.size()>0){
            return  qqAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public TaobaoAuthinfo getTaobaoAuthinfoByUserid(Integer id) throws Exception {
        TaobaoAuthinfo  taobaoAuthinfo = new TaobaoAuthinfo();
        taobaoAuthinfo.setSysUserid(id);
        List<TaobaoAuthinfo>  taobaoAuthinfoList =  taobaoAuthinfoDAO.getTaobaoAuthinfoByUserid( taobaoAuthinfo);
        if( taobaoAuthinfoList!=null&& taobaoAuthinfoList.size()>0){
            return  taobaoAuthinfoList.get(0);
        }
        return null;
    }

    @Override
    public void updateUserEmail(EmailAuthinfo emailAuthinfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        emailAuthinfoDAO.updateUserEmail(emailAuthinfo);
    }

    @Override
    public void updateXuexingAuthinfo(XuexingAuthinfo reuestXuexingAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        xuexingAuthinfoDAO.updateByPrimaryKey(reuestXuexingAuthinfo);
    }

    @Override
    public void updateBluecardAuthinfo(BluecardAuthinfo reuestBluecardAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        bluecardAuthinfoDAO.updateByPrimaryKey(reuestBluecardAuthinfo);
    }

    @Override
    public void updateEmailAuthinfo(EmailAuthinfo reuestEmailAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        emailAuthinfoDAO.updateByPrimaryKey(reuestEmailAuthinfo);
    }
}

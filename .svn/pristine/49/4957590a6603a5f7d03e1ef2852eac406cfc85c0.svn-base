package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.JobcertAuthinfo;

import java.util.List;

public interface JobcertAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(JobcertAuthinfo record);

    void insertSelective(JobcertAuthinfo record);

    JobcertAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobcertAuthinfo record);

    int updateByPrimaryKey(JobcertAuthinfo record);



    List<JobcertAuthinfo> getJobcertAuthinfoByUserid(JobcertAuthinfo jobcertAuthinfo);

    void jobcertAuthinfo(JobcertAuthinfo requestJobcertAuthinfo);

    List<JobcertAuthinfo> getJobcertAuthinfoByUseridAndType(JobcertAuthinfo jobcertAuthinfo);

    void updateImagePathByUserId(JobcertAuthinfo jobcertAuthinfo);



}
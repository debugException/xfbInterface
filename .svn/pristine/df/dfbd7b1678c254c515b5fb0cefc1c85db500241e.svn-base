package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.DriveAuthinfo;

import java.util.List;

public interface DriveAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(DriveAuthinfo record);

    void insertSelective(DriveAuthinfo record);

    DriveAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriveAuthinfo record);

    int updateByPrimaryKey(DriveAuthinfo record);


    List<DriveAuthinfo> getDriveAuthinfoByUserid(DriveAuthinfo driveAuthinfo);

    void driveAuthinfo(DriveAuthinfo requestDriveAuthinfo);

    void updateImagePathByUserId(DriveAuthinfo driveAuthinfo);


}
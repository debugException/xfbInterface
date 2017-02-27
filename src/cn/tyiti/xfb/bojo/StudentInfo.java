/**
 * @(#)StudentInfo.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.bojo;


/**
 * 提升额度-获取职业信息/提升额度-提交职业信息（学生信息）Entity.
 * 
 * @version 1.0 2015-8-17
 * @author Black
 */
public class StudentInfo extends PublicProperties {
	//学校名称
	private String schoolName;
	//专业
	private String major;
	//年级
	private String grade;
	//毕业时间
	private String graduationDate;
	//审核状态
	private String verifyState;
	
	public String getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	
}

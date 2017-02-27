package cn.emagsoftware.xfb.pojo;

import cn.emagsoftware.frame.bean.BaseBean;
import java.util.Date;

public class BluecardAuthinfo extends BaseBean {
    private Integer id;

    private Integer sysUserid;

    private Integer creditId;

    private Float creditScore;

    private String cardEmail;

    private String cardLogin;

    private String cardPassword;

    private String cardName;

    private String cardTelnum;

    private Integer seasameScore;

    private Date submitTime;

    private String imagePath1;

    private String imagePath2;

    private String imagePath3;

    private Integer isVerify;

    private Integer operator;

    private Date operateTime;

    private String description;

    private Integer verifyStatus;

    private String emailPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserid() {
        return sysUserid;
    }

    public void setSysUserid(Integer sysUserid) {
        this.sysUserid = sysUserid;
    }

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    public Float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Float creditScore) {
        this.creditScore = creditScore;
    }

    public String getCardEmail() {
        return cardEmail;
    }

    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail == null ? null : cardEmail.trim();
    }

    public String getCardLogin() {
        return cardLogin;
    }

    public void setCardLogin(String cardLogin) {
        this.cardLogin = cardLogin == null ? null : cardLogin.trim();
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword == null ? null : cardPassword.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getCardTelnum() {
        return cardTelnum;
    }

    public void setCardTelnum(String cardTelnum) {
        this.cardTelnum = cardTelnum == null ? null : cardTelnum.trim();
    }

    public Integer getSeasameScore() {
        return seasameScore;
    }

    public void setSeasameScore(Integer seasameScore) {
        this.seasameScore = seasameScore;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1 == null ? null : imagePath1.trim();
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2 == null ? null : imagePath2.trim();
    }

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3 == null ? null : imagePath3.trim();
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword == null ? null : emailPassword.trim();
    }
}
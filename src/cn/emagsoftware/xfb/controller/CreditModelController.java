package cn.emagsoftware.xfb.controller;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.ConfigCache;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.UserAuthinfoConstants;
import cn.emagsoftware.xfb.dto.*;
import cn.emagsoftware.xfb.pojo.*;
import cn.emagsoftware.xfb.service.CreditModelService;
import cn.emagsoftware.xfb.service.MemberInfoService;
import cn.emagsoftware.xfb.service.SysUserService;
import cn.emagsoftware.xfb.service.UserAuthinfoInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/creditModel")
public class CreditModelController  extends BaseController{


    @Autowired
    private CreditModelService creditModelService;

    @Autowired
    private UserAuthinfoInfoService userAuthinfoInfoService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private SysUserService sysUserService;

    /**memberInfoService
     *  获取用户认证模型
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modelList", method = RequestMethod.POST)
    public String getModelList(Map<String, String> model){
        CreditModelListRsp  response = new CreditModelListRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            if(checkFlag){
                List<CreditModel> userCreditList = creditModelService.getCreditModel();
                response.setCreditModelList(userCreditList);
            }
        }catch (Exception ex){
            log.error("获取用户认模型息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.getModelList.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;
    }


    /**
     * 用户认证处理
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/userAuthinfo", method = RequestMethod.POST)
    public String  userAuthinfo(Map<String, String> model){

        UserAuthinfoRsp response = new UserAuthinfoRsp();
		response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
		try {
			UserAuthinfo userAuthinfo = this.getUserAuthinfo();
			switch (userAuthinfo.getType()) {
			case 1:
				// 身份证认证
				this.userIdCardAuthinfo(response, userAuthinfo);
				break;
			case 2:
				// 邮箱认证
				this.userEmailAuthinfo(response, userAuthinfo);
				break;
			case 3:
				// 工作证认证
				this.userJobAuthinfo(response, userAuthinfo);
				break;
			case 4:
				// 驾照认证
				this.userDriveAuthinfo(response, userAuthinfo);
				break;
			case 5:
				// 结婚证认证
				this.userMarryAuthinfo(response, userAuthinfo);
				break;
			case 6:
				// 信用卡认证
				this.userBluecardAuthinfo(response, userAuthinfo);
				break;
			case 7:
				// 学信网认证
				this.userXuexinAuthinfo(response, userAuthinfo);
				break;
			case 8:
				// 新浪认证
				this.userSinaAuthinfo(response, userAuthinfo);
				break;
			case 9:
				// 淘宝认证
				this.userTaobaoAuthinfo(response, userAuthinfo);
				break;
			case 10:
				// QQ认证
				this.userQqAuthinfo(response, userAuthinfo);
				break;
			default:
				response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);

            }

        }catch (Exception ex){
            log.error("用户认证异常",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);

        }


        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.userAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;
    }

    /**
     *  用户身份证认证处理流程
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userIdCardAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
//          获取登录用户ID
            MemberInfo requestMemberinfo = this.getMemberInfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }

            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());
                MemberInfo memberInfo = memberInfoService.getMemerByUserid(userAuthinfo.getUserId());
                if(memberInfo!=null){
                    if(memberInfo.getCreditId()!=null&&memberInfo.getCreditId().intValue()!=0&&memberInfo.getVerifyStatus().intValue()!=2){
//                        用户目前状态审核中或者审核通过 不再做二次审核
                            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }else{
                        List<CreditModel> cardAuthinfoModelList  = creditModelService.getCreditModelListByType(userAuthinfo.getType()) ;

                        requestMemberinfo.setId(memberInfo.getId());
                        CreditModel creditModel = getCreditModelByAgeCode(cardAuthinfoModelList,UserAuthinfoConstants.getUserAgeCode(memberInfo.getAge()==null?0:memberInfo.getAge()));
    //                  获取认证模型
//                        CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                        if(creditModel==null){
                            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        }else{


    //                      更新用户身份证认证信息和追加身份认证的信用分
                            requestMemberinfo.setSysUserid(userAuthinfo.getUserId());
//                            requestMemberinfo.setCreditSum(creditModel.getCreditScore());
//                            requestMemberinfo.setUseSum(creditModel.getCreditScore());
                            requestMemberinfo.setCreditId(creditModel.getId());
                            requestMemberinfo.setCreditScore(creditModel.getCreditScore());
                            memberInfoService.updateScoreAndInfo(requestMemberinfo);
                        }
                    }
                }else{
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1005);
                }
            }
        }catch (Exception ex){
            log.error("用户身份证认证异常",ex);
            throw ex;
        }
    }




    /**
     *  用户结婚证认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userMarryAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;

            MarryAuthinfo requestMarryAuthinfo = this.getMarryAuthinfo();


            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
            if(checkFlag){
//          根据用户手机号码获取用户详情
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());
                MarryAuthinfo marryAuthinfo = userAuthinfoInfoService.getMarryAuthinfoByUserid(userAuthinfo.getUserId());

                if(marryAuthinfo!=null){
                    if(marryAuthinfo.getCreditId()!=null&&marryAuthinfo.getCreditId().intValue()!=0&&marryAuthinfo.getVerifyStatus().intValue()!=2){
    //                  结婚证认证已经认证
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }else{
                        requestMarryAuthinfo.setId(marryAuthinfo.getId());
    //                  获取认证模型
                        CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                        if(creditModel==null){
                            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        }else{
    //                      更新用户结婚证认证信息和追加信用分
                            MemberInfo memberInfo = memberInfoService.getMemerByUserid(userAuthinfo.getUserId());


                            requestMarryAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            requestMarryAuthinfo.setCreditId(creditModel.getId());
                            requestMarryAuthinfo.setCreditScore(creditModel.getCreditScore());
                            requestMarryAuthinfo.setUserAge(memberInfo.getAge()+"");

                            userAuthinfoInfoService.marryAuthinfo(requestMarryAuthinfo);
//                            MemberInfo memberInfo = new MemberInfo();
//                            memberInfo.setSysUserid(userAuthinfo.getUserId());
//                            memberInfo.setCreditSum(creditModel.getCreditScore());
//                            memberInfoService.updateScore(memberInfo);

                        }
                    }
                }else{
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1005);
                }
            }

        }catch (Exception ex){
            log.error("用户结婚证认证异常",ex);
            throw ex;
        }
    }


    /**
     *  用户工作证认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userJobAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            JobcertAuthinfo reuestJobcertAuthinfo = this.getJobcertAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }

            if(checkFlag){
                //          根据用户手机号码获取用户详情
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());
                int modelType = Integer.parseInt(reuestJobcertAuthinfo.getCardType());
                int authinoTtype = modelType%10;

                JobcertAuthinfo jobcertAuthinfo = userAuthinfoInfoService.getJobcertAuthinfoByUseridAndType(userAuthinfo.getUserId(),authinoTtype);

                if(jobcertAuthinfo!=null){
                    if(jobcertAuthinfo.getCreditId()!=null&&jobcertAuthinfo.getCreditId().intValue()!=0&&jobcertAuthinfo.getVerifyStatus().intValue()!=2){
//                  工作证认证
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }else{
                        reuestJobcertAuthinfo.setId(jobcertAuthinfo.getId());
//                  获取认证模型
                        CreditModel creditModel = creditModelService.getCreditModelByType(modelType) ;
                        if(creditModel==null){
                            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        }else{
//                      更新用户工作证认证信息和追加信用分
                            reuestJobcertAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            reuestJobcertAuthinfo.setCreditId(creditModel.getId());
                            reuestJobcertAuthinfo.setCreditScore(creditModel.getCreditScore());
                            reuestJobcertAuthinfo.setCardType(authinoTtype+"");
                            userAuthinfoInfoService.jobcertAuthinfo(reuestJobcertAuthinfo);
//                            MemberInfo memberInfo = new MemberInfo();
//                            memberInfo.setSysUserid(userAuthinfo.getUserId());
//                            memberInfo.setCreditSum(creditModel.getCreditScore());
//                            memberInfoService.updateScore(memberInfo);
                        }
                    }
                }else{
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1005);
                }
            }



        }catch (Exception ex){
            log.error("用户工作证认证",ex);
            throw ex;
        }
    }



    /**
     *  用户学信网认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userXuexinAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            XuexingAuthinfo reuestXuexingAuthinfo = this.getXuexingAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());
                XuexingAuthinfo xuexingAuthinfo = userAuthinfoInfoService.getXuexingAuthinfoByUserid(userAuthinfo.getUserId());
                CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                if(xuexingAuthinfo==null){
    //               学信网认证

                    if(creditModel!=null){
                        reuestXuexingAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestXuexingAuthinfo.setCreditId(creditModel.getId());
                        reuestXuexingAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.xuexingAuthinfo(reuestXuexingAuthinfo);
//                        MemberInfo memberInfo = new MemberInfo();
//                        memberInfo.setSysUserid(userAuthinfo.getUserId());
//                        memberInfo.setCreditSum(creditModel.getCreditScore());
//                        memberInfoService.updateScore(memberInfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    if(xuexingAuthinfo.getVerifyStatus()!=null&&xuexingAuthinfo.getVerifyStatus().intValue()==2){
//                        学信网审核没有通过 重新进行信息录入
                        if(creditModel!=null){
                            reuestXuexingAuthinfo.setId(xuexingAuthinfo.getId());
                            reuestXuexingAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            reuestXuexingAuthinfo.setCreditId(creditModel.getId());
                            reuestXuexingAuthinfo.setCreditScore(creditModel.getCreditScore());
                            userAuthinfoInfoService.updateXuexingAuthinfo(reuestXuexingAuthinfo);
                        }
                    }else{
                        //              已经被认证无法再次认证
                        checkFlag = false;
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }


                }
            }
        }catch (Exception ex){
            log.error("用户学信网认证异常",ex);
            throw ex;
        }
    }



    /**
     * 用户信用卡认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userBluecardAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            BluecardAuthinfo reuestBluecardAuthinfo = this.getBluecardAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());
                BluecardAuthinfo bluecardAuthinfo = userAuthinfoInfoService.getBluecardAuthinfoByUserid(userAuthinfo.getUserId());
                CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType());
                if(bluecardAuthinfo==null){
                    //               学信网认证
                    if(creditModel!=null){
                        reuestBluecardAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestBluecardAuthinfo.setCreditId(creditModel.getId());
                        reuestBluecardAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.bluecardAuthinfo(reuestBluecardAuthinfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    if(bluecardAuthinfo.getVerifyStatus()!=null&&bluecardAuthinfo.getVerifyStatus().intValue()==2){
                        if(creditModel!=null){
                            reuestBluecardAuthinfo.setId(bluecardAuthinfo.getId());
                            reuestBluecardAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            reuestBluecardAuthinfo.setCreditId(creditModel.getId());
                            reuestBluecardAuthinfo.setCreditScore(creditModel.getCreditScore());
                            userAuthinfoInfoService.updateBluecardAuthinfo(reuestBluecardAuthinfo);
                        }
                    }else{
                        // 已经被认证无法再次认证
                        checkFlag = false;
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }

                }
            }
        }catch (Exception ex){
            log.error("用户信用卡认证",ex);
            throw ex;
        }
    }


    /**
     * 用户新浪认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userSinaAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            SinaAuthinfo reuestSinaAuthinfo = this.getSinaAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());


                SinaAuthinfo sinaAuthinfo = userAuthinfoInfoService.getSinaAuthinfoByUserid(userAuthinfo.getUserId());

                if(sinaAuthinfo==null){
                    //               学信网认证
                    CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                    if(creditModel!=null){
                        reuestSinaAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestSinaAuthinfo.setCreditId(creditModel.getId());
                        reuestSinaAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.sinaAuthinfo(reuestSinaAuthinfo);
                        MemberInfo memberInfo = new MemberInfo();
                        memberInfo.setSysUserid(userAuthinfo.getUserId());
                        memberInfo.setCreditSum(creditModel.getCreditScore());
                        memberInfoService.updateScore(memberInfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    // 已经被认证无法再次认证
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                }
            }
        }catch (Exception ex){
            log.error("用户新浪认证异常",ex);
            throw ex;
        }
    }


    /**
     * 用户淘宝认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userTaobaoAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            TaobaoAuthinfo reuestTaobaoAuthinfo = this.getTaobaoAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());

                TaobaoAuthinfo taobaoAuthinfo = userAuthinfoInfoService.getTaobaoAuthinfoByUserid(userAuthinfo.getUserId());

                if(taobaoAuthinfo==null){
                    //               学信网认证
                    CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                    if(creditModel!=null){
                        reuestTaobaoAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestTaobaoAuthinfo.setCreditId(creditModel.getId());
                        reuestTaobaoAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.taobaoAuthinfo(reuestTaobaoAuthinfo);
                        MemberInfo memberInfo = new MemberInfo();
                        memberInfo.setSysUserid(userAuthinfo.getUserId());
                        memberInfo.setCreditSum(creditModel.getCreditScore());
                        memberInfoService.updateScore(memberInfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    // 已经被认证无法再次认证
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                }
            }
        }catch (Exception ex){
            log.error("用户淘宝认证异常",ex);
            throw ex;
        }
    }



    /**
     * 用户QQ认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userQqAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            QqAuthinfo reuestQqAuthinfo = this.getQqAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());

                QqAuthinfo qqAuthinfo = userAuthinfoInfoService.getQqAuthinfoByUserid(userAuthinfo.getUserId());

                if(qqAuthinfo==null){
                    //               学信网认证
                    CreditModel creditModel = creditModelService.getCreditModelByType(userAuthinfo.getType()) ;
                    if(creditModel!=null){
                        reuestQqAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestQqAuthinfo.setCreditId(creditModel.getId());
                        reuestQqAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.qqAuthinfo(reuestQqAuthinfo);
                        MemberInfo memberInfo = new MemberInfo();
                        memberInfo.setSysUserid(userAuthinfo.getUserId());
                        memberInfo.setCreditSum(creditModel.getCreditScore());
                        memberInfoService.updateScore(memberInfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    // 已经被认证无法再次认证
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                }
            }
        }catch (Exception ex){
            log.error("用户QQ认证异常",ex);
            throw ex;
        }
    }

    /**
     * 用户驾照认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userDriveAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            DriveAuthinfo reuestDriveAuthinfo = this.getDriveAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());

                DriveAuthinfo driveAuthinfo = userAuthinfoInfoService.getDriveAuthinfoByUserid(userAuthinfo.getUserId());

                if(driveAuthinfo!=null){
                    //               驾照认证
                    if(driveAuthinfo.getCreditId()!=null&&driveAuthinfo.getCreditId().intValue()!=0&&driveAuthinfo.getVerifyStatus().intValue()!=2){
//                       已经认证无法再次认证
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }else{
                        MemberInfo memberInfo = memberInfoService.getMemerByUserid(userAuthinfo.getUserId());
                        List<CreditModel> modelList = creditModelService.getCreditModelListByType(userAuthinfo.getType());

                        CreditModel creditModel = getCreditModelByAgeCode(modelList,UserAuthinfoConstants.getDriverAgeCode(memberInfo.getAge()==null?0:memberInfo.getAge()));
                        if(creditModel!=null){
                            reuestDriveAuthinfo.setUserAge(memberInfo.getAge()+"");
                            reuestDriveAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            reuestDriveAuthinfo.setCreditId(creditModel.getId());
                            reuestDriveAuthinfo.setCreditScore(creditModel.getCreditScore());
                            userAuthinfoInfoService.driveAuthinfo(reuestDriveAuthinfo);
//                            MemberInfo memberInfo = new MemberInfo();
//                            memberInfo.setSysUserid(userAuthinfo.getUserId());
//                            memberInfo.setCreditSum(creditModel.getCreditScore());
//                            memberInfoService.updateScore(memberInfo);
                        }else{
                            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                            checkFlag = false;
                        }
                    }
                }else{
                    // 认证图片没有上传
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1005);
                }
            }
        }catch (Exception ex){
            log.error("用户驾照认证异常",ex);
            throw ex;
        }
    }


    /**
     * 用户邮箱认证
     * @param response
     * @param userAuthinfo
     * @throws Exception
     */

    private void userEmailAuthinfo(UserAuthinfoRsp response,UserAuthinfo userAuthinfo )throws Exception{
        try{

            Boolean   checkFlag = true;
            EmailAuthinfo reuestEmailAuthinfo = this.getEmailAuthinfo();

            if(checkFlag&&(userAuthinfo.getUserId()==null||"".equals(userAuthinfo.getUserId()))){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
            }
//          根据用户手机号码获取用户详情
            if(checkFlag){
//                SysUser sysUser =  sysUserService.getUserByLoinName(userAuthinfo.getUserId());

                EmailAuthinfo emailAuthinfo = userAuthinfoInfoService.getEmailAuthinfoByUserid(userAuthinfo.getUserId());

                List<CreditModel> modelList = creditModelService.getCreditModelListByType(userAuthinfo.getType());

                CreditModel creditModel = this.getCreditModelByAgeCode(modelList,UserAuthinfoConstants.getEmailCode(reuestEmailAuthinfo.getCompanyType()==null?0:reuestEmailAuthinfo.getCompanyType()));

                if(emailAuthinfo==null){
                    //               学信网认证

                    if(creditModel!=null){
                        reuestEmailAuthinfo.setSysUserid(userAuthinfo.getUserId());
                        reuestEmailAuthinfo.setCreditId(creditModel.getId());
                        reuestEmailAuthinfo.setCreditScore(creditModel.getCreditScore());
                        userAuthinfoInfoService.emailAuthinfo(reuestEmailAuthinfo);
//                        MemberInfo memberInfo = new MemberInfo();
//                        memberInfo.setSysUserid(userAuthinfo.getUserId());
//                        memberInfo.setCreditSum(creditModel.getCreditScore());
//                        memberInfoService.updateScore(memberInfo);
                    }else{
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
                        checkFlag = false;
                    }
                }else{
                    if(emailAuthinfo.getVerifyStatus()!=null&&emailAuthinfo.getVerifyStatus()==2){
                        if(creditModel!=null){
                            reuestEmailAuthinfo.setId(emailAuthinfo.getId());
                            reuestEmailAuthinfo.setSysUserid(userAuthinfo.getUserId());
                            reuestEmailAuthinfo.setCreditId(creditModel.getId());
                            reuestEmailAuthinfo.setCreditScore(creditModel.getCreditScore());
                            userAuthinfoInfoService.updateEmailAuthinfo(reuestEmailAuthinfo);
                        }
                    }else{
                        // 已经被认证无法再次认证
                        checkFlag = false;
                        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
                    }
                }
            }
        }catch (Exception ex){
            log.error("用户邮箱认证异常",ex);
            throw ex;
        }
    }


    /**获取新浪认证信息
     *
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getSinaAuthinfo", method = RequestMethod.POST)
    public String getSinaAuthinfo(Map<String, String> model){

        SinaAuthinfoRsp  response = new SinaAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            SinaAuthinfo userInfo = this.getSinaAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            if(checkFlag){
                SinaAuthinfo sinaAuthinfo = userAuthinfoInfoService.getSinaAuthinfo(userInfo);
                if(sinaAuthinfo!=null){
                    response.setSinaAuthinfo(sinaAuthinfo);
                }
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.getSinaAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;

    }

    /**
     *
     *  新浪认证
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sinaAuthinfo", method = RequestMethod.POST)
    public String sinaAuthinfo(Map<String, String> model){
        SinaAuthinfoRsp  response = new SinaAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            SinaAuthinfo userInfo = this.getSinaAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            CreditModel creditModel = creditModelService.getCreditModelById(userInfo.getCreditId()) ;

            if(checkFlag&&creditModel==null){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
            }
            if(checkFlag&&userAuthinfoInfoService.checkSinaAuthinfo(userInfo)){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
            }
            if(checkFlag){
                userInfo.setCreditScore(creditModel.getCreditScore());
                userAuthinfoInfoService.sinaAuthinfo(userInfo);
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setSysUserid(userInfo.getSysUserid());
                memberInfo.setCreditSum(creditModel.getCreditScore());
                memberInfoService.updateScore(memberInfo);
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.sinaAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;
    }


    /**
     *  获取淘宝认证信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getTaobaoAuthinfo", method = RequestMethod.POST)
    public String getTaobaoAuthinfo(Map<String, String> model){

        TaobaoAuthinfoRsp response = new TaobaoAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            TaobaoAuthinfo userInfo = this.getTaobaoAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            if(checkFlag){
                TaobaoAuthinfo sinaAuthinfo = userAuthinfoInfoService.getTaobaoAuthinfo(userInfo);
                if(sinaAuthinfo!=null){
                    response.setTaobaoAuthinfo(sinaAuthinfo);
                }
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.getTaobaoAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;

    }

    /**
     *  淘宝认证信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/taobaoAuthinfo", method = RequestMethod.POST)
    public String taobaoAuthinfo(Map<String, String> model){
        SinaAuthinfoRsp  response = new SinaAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            TaobaoAuthinfo userInfo = this.getTaobaoAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            CreditModel creditModel = creditModelService.getCreditModelById(userInfo.getCreditId()) ;

            if(checkFlag&&creditModel==null){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
            }
            if(checkFlag&&userAuthinfoInfoService.checkTaobaoAuthinfo(userInfo)){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
            }
            if(checkFlag){
                userInfo.setCreditScore(creditModel.getCreditScore());
                userAuthinfoInfoService.taobaoAuthinfo(userInfo);
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setSysUserid(userInfo.getSysUserid());
                memberInfo.setCreditSum(creditModel.getCreditScore());
                memberInfoService.updateScore(memberInfo);
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.taobaoAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;
    }



    /**
     *  获取QQ认证信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getQqAuthinfo", method = RequestMethod.POST)
    public String getQqAuthinfo(Map<String, String> model){

        QqAuthinfoRsp response = new QqAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            QqAuthinfo userInfo = this.getQqAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            if(checkFlag){
                QqAuthinfo qqAuthinfo = userAuthinfoInfoService.getQqAuthinfo(userInfo);
                if(qqAuthinfo!=null){
                    response.setQqAuthinfo(qqAuthinfo);
                }
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.getQqAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;

    }

    /**
     *  QQ认证
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/qqAuthinfo", method = RequestMethod.POST)
    public String qqAuthinfo(Map<String, String> model){
        QqAuthinfoRsp  response = new QqAuthinfoRsp();
        response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1000);
        Boolean checkFlag = true;
        try{
            QqAuthinfo userInfo = this.getQqAuthinfo();
            if(ConfigCache.getInterVersion()==1){
                if(checkFlag&&(userInfo.getSysUserid()==null||userInfo.getSysUserid()==0)){
//                  请求的用户ID 为空
                    checkFlag = false;
                    response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1002);
                }
            }
            CreditModel creditModel = creditModelService.getCreditModelById(userInfo.getCreditId()) ;

            if(checkFlag&&creditModel==null){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1004);
            }
            if(checkFlag&&userAuthinfoInfoService.checkQqAuthinfo(userInfo)){
                checkFlag = false;
                response.setResultCode(UserAuthinfoConstants.ERROR_CODE_1001);
            }
            if(checkFlag){
                userInfo.setCreditScore(creditModel.getCreditScore());
                userAuthinfoInfoService.qqAuthinfo(userInfo);
                MemberInfo memberInfo = new MemberInfo();
                memberInfo.setSysUserid(userInfo.getSysUserid());
                memberInfo.setCreditSum(creditModel.getCreditScore());
                memberInfoService.updateScore(memberInfo);
            }
        }catch(Exception ex){
            log.error("获取用户认证信息错误",ex);
            response.setResultCode(UserAuthinfoConstants.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( UserAuthinfoConstants.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("CreditModelController.qqAuthinfo.response=="+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("OrderController.orderList", e);
        }
        return RET_JSP;
    }


    /**
     * 根据REQUEST获取请求信息
     * @return
     */
    public SinaAuthinfo getSinaAuthinfo(){
        SinaAuthinfo orderInfo = new SinaAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("新浪用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("新浪用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }

    /**
     * 根据REQUEST获取请求信息
     * @return
     */
    public QqAuthinfo getQqAuthinfo(){
       QqAuthinfo orderInfo = new QqAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("QQ用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("QQ用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    /**
     * 根据REQUEST获取请求信息
     * @return
     */
    public TaobaoAuthinfo getTaobaoAuthinfo(){
        TaobaoAuthinfo orderInfo = new TaobaoAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("淘宝用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("淘宝用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }



    /**
     * 根据REQUEST获取请求信息
     * @return
     */
    public UserAuthinfo getUserAuthinfo(){
        UserAuthinfo orderInfo = new UserAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }

    public MemberInfo getMemberInfo(){
        MemberInfo orderInfo = new MemberInfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    public MarryAuthinfo getMarryAuthinfo(){
        MarryAuthinfo orderInfo = new MarryAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    public JobcertAuthinfo getJobcertAuthinfo(){
        JobcertAuthinfo orderInfo = new JobcertAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }

    public XuexingAuthinfo getXuexingAuthinfo(){
        XuexingAuthinfo orderInfo = new XuexingAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    public BluecardAuthinfo getBluecardAuthinfo(){
        BluecardAuthinfo orderInfo = new BluecardAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    public DriveAuthinfo getDriveAuthinfo(){
        DriveAuthinfo orderInfo = new DriveAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    public EmailAuthinfo getEmailAuthinfo(){
        EmailAuthinfo orderInfo = new EmailAuthinfo();
        try {
            BeanUtils.populate(orderInfo, request.getParameterMap());
            if(orderInfo.getCompanyType()!=null){
                orderInfo.setCompanyType(orderInfo.getCompanyType()+1);
            }
            log.debug("用户认证请求信息为："+ JSONObject.fromObject(orderInfo).toString());
        } catch (Exception ex) {
            log.error("用户认证请求信息转换错误" + ex.getMessage(), ex);
            orderInfo = null;
        }
        return orderInfo;
    }


    private CreditModel getCreditModelByAgeCode(List<CreditModel> cardAuthinfoModelList,String ageCode){
        for(int i=0;i<cardAuthinfoModelList.size();i++){
            CreditModel creditModel = cardAuthinfoModelList.get(i);
            if(ageCode.equals(creditModel.getCreditCode())){
                return creditModel;
            }
        }

        return null;

    }


}

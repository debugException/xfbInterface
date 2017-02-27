package cn.emagsoftware.xfb.controller;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.frame.exception.BaseException;
import cn.emagsoftware.utils.ConfigCache;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.MD5Util;
import cn.emagsoftware.utils.ValidateUtils;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.CommonConstant;
import cn.emagsoftware.xfb.constants.SysUserConstant;
import cn.emagsoftware.xfb.dto.CustomerRegistRsp;
import cn.emagsoftware.xfb.dto.SMSCodeRep;
import cn.emagsoftware.xfb.dto.SysUserModifyPassRsp;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.emagsoftware.xfb.service.SysUserService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yindongyong
 * Date: 15-4-3
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MemcachedClient memcachedClient;


    /**
     * 用户登录请求根据用户手机号码和用户密码判断用户登录是否成功
     *
     *  @param model
     * @return
     */

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Map<String, String> model) {
        CustomerRegistRsp response = new CustomerRegistRsp();
        try {

            SysUser SysUserRep = this.getUser();

            SysUser resultUser = sysUserService.userLogin(SysUserRep);

            if (resultUser!=null) {
                response.setResultCode(Constant.SUCCESS_CODE);
                response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.SUCCESS_CODE));
                response.setCustomerID(resultUser.getId()+"");
            }else{
                response.setResultCode(CommonConstant.ERROR_CODE_1007);
                response.setResultMessage(CommonConstant.ERROR_MESSAGE
                        .get(CommonConstant.ERROR_CODE_1007));
            }
        } catch (BaseException e) {
            response.setResultCode(e.getErrorCode());
            response.setResultMessage(e.getErrorMsg());
        } catch (Exception e) {
            log.error("CustomerController.login", e);
            response.setResultCode(Constant.ERROR_CODE_9999);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
        try {
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("CustomerController.login", e);
        }
        return RET_JSP;
    }

    /**
     *  用户注册
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public String registration(Map<String, String> model) throws UnsupportedEncodingException {

        CustomerRegistRsp response = new CustomerRegistRsp();
        response.setResultCode(SysUserConstant.ERROR_CODE_1000);
        boolean checkFlag = true;
        try {
            SysUser user = this.getUser();
            //          手机验证码校验
            if (ConfigCache.getInterVersion() == 1) {
//                测试版本不进行短信验证码校验
                String SMScode = memcachedClient.get(user.getLoginName());
                if (checkFlag && (SMScode == null || SMScode.equals(""))) {
                    //                短信验证码超时
                    response.setResultCode(SysUserConstant.ERROR_CODE_1003);
                    checkFlag = false;
                } else if (checkFlag && (!SMScode.equals(user.getSMSCode()))) {
                    //               短信验证码不正确

                    response.setResultCode(SysUserConstant.ERROR_CODE_1004);
                    checkFlag = false;
                }
            }
//           身份证校验
            if (checkFlag && !ValidateUtils.chekIdCard(user.getMyCode(), -1)) {

                response.setResultCode(SysUserConstant.ERROR_CODE_1002);
                checkFlag = false;
            }
//          推荐人身份校验
            if (user.getRecomCode() != null && !"".equals(user.getRecomCode())) {
                if (checkFlag && !sysUserService.checkRecomCode(user)) {
                    response.setResultCode(SysUserConstant.ERROR_CODE_1006);

                    checkFlag = false;
                }
            }
//          手机号码校验
            if (checkFlag && sysUserService.checkLoginName(user)) {
                response.setResultCode(SysUserConstant.ERROR_CODE_1001);
                checkFlag = false;
            }
//           校验成功进行用户注册
            if(checkFlag){
                String myCode = getRecomCode(user.getLoginName(),6);
                sysUserService.addUser(user);
                response.setResultCode(SysUserConstant.ERROR_CODE_1000);
            }
        } catch (Exception ex) {
            log.error("用户注册异常" + ex.getMessage(), ex);
            response.setResultCode(SysUserConstant.ERROR_CODE_2000);
        }

        try {
            response.setResultMessage( SysUserConstant.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("CustomerController.login", e);
        }
        return RET_JSP;
    }

    /**
     * 根据request获取请求的用户参数
     * @return
     */

    public SysUser getUser() {
        SysUser user = new SysUser();
        try {
            BeanUtils.populate(user, request.getParameterMap());
//            if(user.getPassWord()!=null&&!"".equals(user.getPassWord())){
//                user.setPassWord(MD5Util.getMD5String(user.getPassWord()));
//            }
//            if(user.getNewPassWord()!=null&&!"".equals(user.getNewPassWord())){
//                user.setNewPassWord((MD5Util.getMD5String(user.getNewPassWord())));
//            }
            log.debug("请求的用户信息为："+JSONObject.fromObject(user).toString());
        } catch (Exception ex) {
            log.error("用户请求信息转换错误" + ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * 根据用户的手机进行短信马下发
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getSMScheckcode", method = RequestMethod.POST)
    public String  getSMScheckcode(Map<String, String> model) {
        SMSCodeRep response = new SMSCodeRep();
        response.setResultCode(SysUserConstant.ERROR_CODE_1000);
        try {
            SysUser user = this.getUser();
            String SMSCode = SysUserConstant.createRandom(true, 6);
            response.setSMSCode(SMSCode);
            log.info("用户号码为：" + user.getLoginName() + ";生成的短信校验码为：" + SMSCode);
//          请求短信发送程序进行短信发送


//            添加手机号码到缓存中
            memcachedClient.set(user.getLoginName(), SysUserConstant.SMS_VALID_TIME, SMSCode);
        } catch (Exception ex) {
            log.error("获取短信校验码失败" + ex.getMessage(), ex);
            response.setResultCode(SysUserConstant.ERROR_CODE_2000);
        }
        try {
            response.setResultMessage( SysUserConstant.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("CustomerController.login", e);
        }
        return RET_JSP;

    }

    /**
     *  生成推荐码 根据  手机尾号4 年月日8  6位的数字字母混合的随机数
     * @param loginName
     * @param length
     * @return  20位的推荐码
     */

    public  String getRecomCode(String loginName,int length){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return loginName.substring(7)+"-"+sdf.format(cal.getTime())+ "-"+SysUserConstant.createRandom(false,length);
    }

    /**
     * 根据用户ID 和 原密码信息进行密码更改
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public String modifyPassword(Map<String, String> model){
        SysUserModifyPassRsp response = new SysUserModifyPassRsp();
        try {
            SysUser user = this.getUser();
            SysUser resultUser = sysUserService.getUserById(user);

            if(resultUser!=null&&resultUser.getPassWord().equals(user.getPassWord())){
//                密码正确 进行密码修改
                resultUser.setNewPassWord(user.getNewPassWord());
                sysUserService.updatePassWord(resultUser);
                response.setResultCode(SysUserConstant.ERROR_CODE_1000);
            }else{
                response.setResultCode(SysUserConstant.ERROR_CODE_1005);
            }

        } catch (Exception e) {
            log.error("CustomerController.login", e);
            response.setResultCode(SysUserConstant.ERROR_CODE_2000);
        }

        try {
            response.setResultMessage( SysUserConstant.ERROR_MESSAGE.get(response.getResultCode()));
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.debug("");
        } catch (Exception e) {
            log.error("CustomerController.login", e);
        }
        return RET_JSP;
    }



}

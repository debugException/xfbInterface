package cn.emagsoftware.xfb.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单配置
 * 消息码 2001 - 2200
 * 
 */
public class UserAuthinfoConstants {

    /**
     * 认证成功
     */
    public static final String ERROR_CODE_1000 = "0";

    /**
     * 已存在的认证信息
     */
    public static final String ERROR_CODE_1001 = "1001";


    /**
     * 认证用户为空
     */
    public static final String ERROR_CODE_1002 = "1002";
    /**
     * 不存在的认证模型
     */
    public static final String ERROR_CODE_1004 = "1004";

    /**
     * 认证图片没有上传
     */
    public static final String ERROR_CODE_1005 = "1005";
//  其它异常
    public static final String ERROR_CODE_2000 = "2000";

    /** 返回码MAP */
	public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();



	static {
		ERROR_MESSAGE.put(ERROR_CODE_1000, "认证处理成功");
		ERROR_MESSAGE.put(ERROR_CODE_1001, "存在的认证用户");
        ERROR_MESSAGE.put(ERROR_CODE_1002, "认证用户为空");
        ERROR_MESSAGE.put(ERROR_CODE_1004, "认证失败");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "认证异常");
        ERROR_MESSAGE.put(ERROR_CODE_1005, "认证图片没有上传");
	}


    public static  String  getUserAgeCode(int age){
        String ageCode = "";

        if(age>=55){
            ageCode = "ID_CREDIT_MODEL_G";
        }else if(age>=45){
            ageCode = "ID_CREDIT_MODEL_B";
        }else if(age>=30){
            ageCode = "ID_CREDIT_MODEL_A";
        }else if(age>=18){
            ageCode = "ID_CREDIT_MODEL_C";
        }else{
            ageCode = "ID_CREDIT_MODEL_D";
        }
        return ageCode;
    }


    public static  String  getDriverAgeCode(int age){
        String ageCode = "";

        if(age>28){
            ageCode = "DIRVE_CREDIT_MODEL_A_B";
        }else if(age>=18){
            ageCode = "DIRVE_CREDIT_MODEL_A_A";
        }

        return ageCode;
    }


    public static  String  getEmailCode(int type){
        String ageCode = "";
        if(type==1){
            ageCode = "EMAIL_CREDIT_MODEL_A_A";
        }else if(type==2){
            ageCode = "EMAIL_CREDIT_MODEL_A_B";
        }else if(type==3){
            ageCode = "EMAIL_CREDIT_MODEL_A_C";
        }else if(type==4){
            ageCode = "EMAIL_CREDIT_MODEL_A_D";
        }else if(type==5){
            ageCode = "EMAIL_CREDIT_MODEL_A_E";
        }
        return ageCode;
    }

}

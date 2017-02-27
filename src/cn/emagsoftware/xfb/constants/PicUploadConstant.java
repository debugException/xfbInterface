package cn.emagsoftware.xfb.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 账单配置
 * 消息码 2001 - 2200
 * 
 *
 */
public class PicUploadConstant
{
    /** 图片上传成功 */
    public static final String ERROR_CODE_1000 = "0";

    /** 其它异常 */
    public static final String ERROR_CODE_2000 = "2000";
    /** 不存在的认证类型 */
    public static final String ERROR_CODE_1001 = "1001";

    /** 图片格式不正确 */
    public static final String ERROR_CODE_1002 = "1002";


    /** 用户身份证图片上传失败 */
    public static final String ERROR_CODE_1003 = "1003";


    /**  */
    public static final String ERROR_CODE_1004 = "1004";

    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static
    {
        ERROR_MESSAGE.put(ERROR_CODE_1003,"用户身份证图片上传失败");
        ERROR_MESSAGE.put(ERROR_CODE_1002, "图片格式不正确");
        ERROR_MESSAGE.put(ERROR_CODE_1001, "不存在的认证类型");
        ERROR_MESSAGE.put(ERROR_CODE_1000, "操作成功");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "其它异常");
    }

}
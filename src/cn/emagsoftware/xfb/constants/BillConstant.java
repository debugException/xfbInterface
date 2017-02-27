package cn.emagsoftware.xfb.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 账单配置
 * 消息码 2001 - 2200
 * 
 *
 */
public class BillConstant
{
    /** 账单操作成功 */
    public static final String ERROR_CODE_1000 = "0";

    /** 其它异常 */
    public static final String ERROR_CODE_2000 = "2000";
    /** 没有符合条件的账单 */
    public static final String ERROR_CODE_2001 = "2001";

    /** 没有该账单的详情信息 */
    public static final String ERROR_CODE_2002 = "2002";

    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static
    {
        ERROR_MESSAGE.put(ERROR_CODE_2002, "没有该账单的详情信息");
        ERROR_MESSAGE.put(ERROR_CODE_2001, "没有符合条件的账单");
        ERROR_MESSAGE.put(ERROR_CODE_1000, "账单操作成功");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "其它异常");
    }

}
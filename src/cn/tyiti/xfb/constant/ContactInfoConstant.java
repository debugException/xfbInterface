package cn.tyiti.xfb.constant;

import java.util.HashMap;
import java.util.Map;

public class ContactInfoConstant {
	  /** 联系人操作成功 */
    public static final String ERROR_CODE_1000 = "0";

    /** 此用户还没有联系人 */
    public static final String ERROR_CODE_2001 = "2001";

    /** 其它异常 */
    public static final String ERROR_CODE_2000 = "2000";

    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static
    {
        ERROR_MESSAGE.put(ERROR_CODE_1000, "联系人操作成功");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "其它异常");
        ERROR_MESSAGE.put(ERROR_CODE_2001, "用户无联系人");
    }

}

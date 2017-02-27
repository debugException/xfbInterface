package cn.tyiti.xfb.constant;

import java.util.HashMap;
import java.util.Map;

public class BankInfoConstant {
	  /** 银行卡操作成功 */
    public static final String ERROR_CODE_1000 = "0";


    /** 其它异常 */
    public static final String ERROR_CODE_2000 = "2000";

    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static
    {
        ERROR_MESSAGE.put(ERROR_CODE_1000, "银行卡操作成功");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "其它异常");
    }

}

package cn.tyiti.xfb.utils;



import net.sf.json.JSONObject;
import cn.emagsoftware.utils.Constant;

/**
 * 用于发送用户响应
 */
public abstract class ResponseUtil {

    /**
     * 处理完成后，将处理结构进行包装返回
     */
    public static final JSONObject toJsonObject(Object val) {
        JSONObject json = new JSONObject();
        //序列化对象时，进行日期格式化，保证页面正确的日期显示格式 modify by sunlei 2015-05-15
        /*大小写HH用来区分24小时制 HH:24小时制 hh:12小时制
        日期格式化统一在实体中增加注解处理 日期：yyyy-MM-dd 时间：yyyy-MM-dd HH:mm:ss
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        public void setExpiryTime(Date expiryTime){
            this.expiryTime = expiryTime;
        }
        * author：Black
        * date : 2015-06-10
        */
        //json.put(Globals.JSON_DATA, JSON.parse(JSON.toJSONStringWithDateFormat(val, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat)));
        json.put(Constant.RETURN_MESSAGE, val);
        return json;
    }
    
}
package cn.emagsoftware.utils;

import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.Logger;
import com.thoughtworks.xstream.XStream;

/**
 * XStream 工具 接口输入输出参数 XML与BEAN转换 XML的解析支撑类
 */
public final class XStreamUtils {

    @Logger
    private static ILog log;

    /**
     * <默认构造函数>
     */
    private XStreamUtils() {
    }

    /**
     * xml类型转换成Object类型
     *
     * @param xml
     * @param objClass
     * @return 转换后的对象
     */
    public static Object parseXmlToObj(String xml, Class<?> objClass) {
        Object obj = null;
        try {
            XStream xstream = new XStream();
            // 显示声明使用注解
            xstream.processAnnotations(objClass);
            xstream.autodetectAnnotations(true);
            obj = xstream.fromXML(xml);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ERROR:xml解析出错：" + xml + "\n", e);
        }
        return obj;
    }

    /**
     * Object类型转换成xml
     *
     * @param out
     * @return String 输入xml
     */
    public static String parseObjToXml(Object out) {
        String str = null;
        try {
            XStream xstream = new XStream();
            // 显示声明使用注解
            xstream.processAnnotations(out.getClass());
            xstream.autodetectAnnotations(true);
            str = xstream.toXML(out);
            str = Constant.XML_HEAD + str;
        } catch (Exception e) {
            log.error("ERROR:Object类型转换成xml出错。\n", e);
        }
        return str;
    }

}
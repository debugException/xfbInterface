package cn.emagsoftware.utils;

import cn.emagsoftware.xfb.constants.SysUserConstant;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验类
 *
 */
public class ValidateUtils {
    /**
     * 判断视频相关type是否合法，type类型为1,2,3 1 : 游戏，2：专辑, 3 : 视频一级分类
     *
     * @param type
     * @return true：合法；false：非法
     */
    public static boolean checkVideoType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            String regular = "[1-3]";
            return Pattern.matches(regular, type.trim());
        }
        return true;
    }


    /**
     * 判断视频相关type是否合法，type类型为1,2。1：android，2：ios
     *
     * @param type
     * @return true：合法；false：非法
     */
    public static boolean checkType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            String regular = "[1-2]";
            return Pattern.matches(regular, type.trim());
        }
        return true;
    }

    /**
     * 判断ID是否合法，ID为大于0的数字
     *
     * @param originID
     * @return true：合法；false：非法
     */
    public static boolean checkOriginID(String originID) {
        if (StringUtils.isNotEmpty(originID)) {
            String regular = "^[1-9]+[0-9]*$";
            return Pattern.matches(regular, originID.trim());
        }
        return true;
    }

    /**
     * 判断ID是否合法，ID为大于0的数字
     *
     * @param userID
     * @return true：合法；false：非法
     */
    public static boolean checkUserID(String userID) {
        if (StringUtils.isNotEmpty(userID)) {
            String regular = "^[1-9]+[0-9]*$";
            return Pattern.matches(regular, userID.trim());
        }
        return true;
    }

    /**
     * 判断ID数组是否合法，ID为大于0的数字，ID之间为 , 分隔
     *
     * @param ids
     * @return true：合法；false：非法
     */
    public static boolean checkIDs(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            String regular = "^[1-9]+[0-9]*|([1-9]+[0-9]*\\s*\\,\\s*)*[1-9]+[0-9]*$";
            return Pattern.matches(regular, ids.trim());
        }
        return true;
    }

    /**
     * 判断imie是否合法，imie为数字串
     *
     * @param imie
     * @return true：合法；false：非法
     */
    public static boolean checkImie(String imie) {
        if (StringUtils.isNotEmpty(imie)) {
            String regular = "^[0-9]*$";
            return Pattern.matches(regular, imie.trim());
        }
        return true;
    }

    /**
     * 判断登陆关联平台type是否合法，type类型为1-腾讯QQ互联 2-新浪微博开放平台 3-微信开放平台
     *
     * @param type
     * @return true：合法；false：非法
     */
    public static boolean checkOpenType(String type) {
        if (StringUtils.isNotEmpty(type)) {
            String regular = "[1-3]";
            return Pattern.matches(regular, type.trim());
        }
        return true;
    }

    /**
     * 判断手机号是否合法
     *
     * @param mobile
     * @return true:合法 false：不合法
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            String regular = "[1][3-8]+\\d{9}";
            return Pattern.matches(regular, mobile.trim());
        }
        return true;
    }
    /**
     * 身份证号码合法性校验
     * @param id 身份证号码 sex -1不做性别判断 1 男 2 女
     * @return
     */

//        public static boolean chekIdCard(String id,int  sex){
//            return true;
//        }
    public static boolean chekIdCard(String id,int  sex){

        int iW[]={7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        String szVerCode[] = new String[]{"1","0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

//     身份证 位数校验
        if(id==null||"".equals(id)||(id.length()!=18&&id.length()!=15)){
            return false;
        }

        String Ai = "";
        if(id.length()==18){
            Ai = id.substring(0,17);

        }else if(id.length()==15){
            Ai = id.substring(0,6)+"19"+id.substring(6,15);
        }

//     数字校验 前17位全部数字
        if (!isNumeric(Ai)) {
            return false;
        }

//      身份证 年月日校验
//        String strDate = Ai.substring(6, 14);// 年月日 yyymmdd
//        if (!isDate(strDate) ) {
//            return false;
//        }
//         身份证省份编码判断
        if (SysUserConstant.CARD_PROVINCE.get(Ai.substring(0, 2)) == null) {
            return false;
        }

//        性别校验
        if(sex!=-1){
            int sexNum = Integer.parseInt(Ai.substring(15))%2;
            if(sexNum!=sex){
                return false;
            }
        }

        char pszSrc[]=Ai.toCharArray();

        int iS = 0;
        for(int i=0;i<17;i++)
        {
            iS += (int)(pszSrc[i]-'0') * iW[i];
        }
        int iY = iS%11;
        if(id.length()==18){
        	/*
             * 身份证尾号x 不区分大小写
             * author：Black 
             * date:2015-07-22
             */
            return szVerCode[iY].equalsIgnoreCase(id.substring(17));
        }
        return true;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        boolean resultFlag = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try{
            Date date = dateFormat.parse(strDate);
            Calendar cal = Calendar.getInstance();
            int dateDiff = DateUtil.getDateDiff(cal.getTime(),date,Calendar.YEAR);
            if(dateDiff<SysUserConstant.USER_MIN_AGE||dateDiff>SysUserConstant.USER_MAX_AGE){
//                年龄不符
                resultFlag =  false;
            }
        }catch(Exception ex){
//            非法时间格式 无法格式化
            resultFlag =  false;
        }

        return resultFlag;
    }


    public static int getAgeByIdCard(String idCard){
        String birsDate = "";
        if(idCard.length()==18){
            birsDate = idCard.substring(6,14);
        }else if(idCard.length()==15){
            birsDate = "19"+idCard.substring(6,12);
        }

        int birsYear = Integer.parseInt(birsDate.substring(0,4));
        int birsMonth = Integer.parseInt(birsDate.substring(4,6));
        int birsDay= Integer.parseInt(birsDate.substring(6,8));

        Calendar cal = Calendar.getInstance();

        int curryYear = cal.get(Calendar.YEAR);
        int curryMonth = cal.get(Calendar.MONTH)+1;
        int curryDay= cal.get(Calendar.DAY_OF_MONTH);

        return curryMonth-birsMonth>=0&&curryDay-birsDay>=0?curryYear-birsYear:curryYear-birsYear-1;
    }


    public static int getSexByIdCard(String idCard) {


        String Ai = "";
        if(idCard.length()==18){
            Ai = idCard.substring(0,17);

        }else if(idCard.length()==15){
            Ai = idCard.substring(0,6)+"19"+idCard.substring(6,15);
        }
        int sexNum = Integer.parseInt(Ai.substring(15))%2==0?2:1;
        return sexNum;
    }

}

package cn.tyiti.xfb.utils;



/**
 * 
 * unicode转汉字工具类.
 * 
 * @version 1.0 2015-8-26
 * @author Black
 */
public class UnicodeUtil {
	/**
	 * 
	 * unicode转汉字.
	 * @author Black
	 * @date 2015-8-26 上午10:26:42
	 *
	 * @param utfString
	 * @return
	 */
	public static String convert(String utfString){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		
		while((i=utfString.indexOf("\\U", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if(i+5 < utfString.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
			}
		}
		
		sb.append(utfString.substring(pos, utfString.length()));
		return sb.toString();
	}
}

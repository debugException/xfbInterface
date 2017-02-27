package cn.emagsoftware.xfb.pojo;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;


import cn.emagsoftware.common.BASE64Decoder;
import cn.emagsoftware.common.BASE64Encoder;


public class PkgHeader {
	private String version;			//默认01
	private String custNo;			//请求源
	private String encode;			//01.UTF8 02.GBK
	private String trxCode;			//报文编号 默认四位 例:0001
	private String encryptType;	//加密类型 01.不加密 02.RSA
	private String msgType;		//01.JSON 02.XML 03.Protobuf
	private String msgBody;		//报文主体为Base64编码的字节数组
	private String retCode;			//返回代码
	private String retMsg;			//返回消息
	private String sign;				//签名
	
	public PkgHeader()
	{
		this.setVersion("01");			//默认01
		this.setCustNo("");				//请求源
		this.setEncode("01");			//01.UTF8 02.GBK
		this.setTrxCode("");			//报文编号 默认四位 例:0001
		this.setEncryptType("01");	//加密类型 01.不加密 02.RSA
		this.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
		this.setMsgBody("");			//报文主体
		this.setRetCode("");			//返回代码
		this.setRetMsg("");			//返回消息
		this.setSign("");					//签名
	}
	
	public void parseFormBytes(byte[] data,Charset charset)
	{
		parseFromString(new String(data,charset));
	}
	
	public void parseFormBytes(byte[] data,String charsetName)
	{
		parseFormBytes(data,GetEncoding());
	}
	
	public Charset GetEncoding()
	{
		return this.encode.equals( "02") ? Charset.forName("GBK") : Charset.forName("UTF-8");
	}
	
	public void parseFromString(String pkgStr)
	{
		String[] postDataStr = StringUtils.splitPreserveAllTokens(pkgStr,"|");
		
		this.setVersion(postDataStr[0]);			//默认01
		this.setCustNo(postDataStr[1]);				//客户编号
		this.setEncode(postDataStr[2]);			//01.UTF-8 02.GBK
		this.setTrxCode(postDataStr[3]);			//报文编号 默认四位 例:0001
		this.setEncryptType(postDataStr[4]);	//加密类型 01.不加密 02.RSA
		this.setMsgType(postDataStr[5]);			//01.JSON 02.XML 03.Protobuf
		
		String msgBody = "";
		try {
			msgBody = new String(new BASE64Decoder().decodeBuffer(postDataStr[6]),GetEncoding());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.setMsgBody(msgBody);			//报文主体
		this.setRetCode(postDataStr[7]);			//返回代码
		
		String retMsg = "";
		try {
			retMsg = new String(new BASE64Decoder().decodeBuffer(postDataStr[8]),GetEncoding());
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		this.setRetMsg(retMsg);			//返回消息
		this.setSign(postDataStr[9]);					//签名
	}
	
	public String toPkgStr()
	{
		return toPkgStr(GetEncoding());
	}
	
	public String toPkgStr(Charset charset)
	{
		String msgBody = new BASE64Encoder().encode(this.getMsgBody().getBytes(charset));
		String retMsg = new BASE64Encoder().encode(this.getRetMsg().getBytes(charset));
		
		String result = this.getVersion() + "|" +	
				this.getCustNo()	 + "|" +	
				this.getEncode()	 + "|" +	
				this.getTrxCode() + "|" +	
				this.getEncryptType() + "|" +
				this.getMsgType() + "|" +
				msgBody + "|" +
				this.getRetCode() + "|" +
				retMsg + "|" +
				this.getSign();	
		
		return result;
	}
	
	public String toPkgStr(String charsetName)
	{
		String result = toPkgStr(Charset.forName(charsetName));
		return result;
	}
	
	public byte[] toPkgBytes(Charset charset)
	{
		return toPkgStr(charset).getBytes(charset);
	}
	
	public byte[] toPkgBytes(String charsetName)
	{
		byte[] result = toPkgBytes(Charset.forName(charsetName)); ;
		
		return result;
	}
	
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getCustNo() {
		return custNo;
	}
	
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	public String getEncode() {
		return encode;
	}
	
	public void setEncode(String encode) {
		this.encode = encode;
	}
	
	public String getTrxCode() {
		return trxCode;
	}
	
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}
	
	public String getEncryptType() {
		return encryptType;
	}
	
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getMsgBody() {
		return msgBody;
	}
	
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
}


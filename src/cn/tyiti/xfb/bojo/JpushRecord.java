package cn.tyiti.xfb.bojo;   
/** 
 * 创建时间：2015-12-9 上午11:01:16  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：JpushInfo.java  
 * 类说明：  
 */
public class JpushRecord {
	
	
	private String jpushmsgId; // '推送编号'
	private String jpushtitle; // '推送标题'
	private String jpushmessage; // '推送内容'
	private String jpushtype; // '推送内容'
	private String jpushlinkaddress; // '推送连接地址'
	private String jpushdeadline; // '活动截止时间'
	private String jpushtime;// '推送时间'
	
	

	public String getJpushmsgId() {
		return jpushmsgId;
	}

	public void setJpushmsgId(String jpushmsgId) {
		this.jpushmsgId = jpushmsgId;
	}

	public String getJpushtitle() {
		return jpushtitle;
	}

	public void setJpushtitle(String jpushtitle) {
		this.jpushtitle = jpushtitle;
	}

	public String getJpushmessage() {
		return jpushmessage;
	}

	public void setJpushmessage(String jpushmessage) {
		this.jpushmessage = jpushmessage;
	}

	public String getJpushtype() {
		return jpushtype;
	}

	public void setJpushtype(String jpushtype) {
		this.jpushtype = jpushtype;
	}

	public String getJpushlinkaddress() {
		return jpushlinkaddress;
	}

	public void setJpushlinkaddress(String jpushlinkaddress) {
		this.jpushlinkaddress = jpushlinkaddress;
	}

	public String getJpushdeadline() {
		return jpushdeadline;
	}

	public void setJpushdeadline(String jpushdeadline) {
		this.jpushdeadline = jpushdeadline;
	}

	public String getJpushtime() {
		return jpushtime;
	}

	public void setJpushtime(String jpushtime) {
		this.jpushtime = jpushtime;
	}
}
 
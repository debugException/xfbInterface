package cn.emagsoftware.xfb.pojo;

/**
*      
* 项目名称：xfbInterface     
* 类名称：CollectInfo     
* 类描述：     
* 创建人：shenzhiqiang    
* 创建时间：2015-12-1 下午4:38:31       
* @version      
*
 */
public class CollectInfo {
	
	private String real_name; // 用户名,
	private String card_number; // 身份证号,
	private String type; // 会员类型,
	private String status; // 借款状态,
	private String credit_sum; // 授信金额,
	private String verify_state; // 审批状态,
	private String m_verify_state; // 会员审批,
	private String j_verify_state; // 工作审批,
	private String c_verify_state; // 联系人审批,
	private String s_verify_state; // 学生审批,
	private String use_limit; // 借款金额,
	private String create_time; // 合同日期,
	private String stage_plan; // 批贷期数,
	private String order_status; // 还款状态,
	private String overdue_day; // 逾期天数,
	private String  arrearsAmount;// 欠款金额
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCredit_sum() {
		return credit_sum;
	}
	public void setCredit_sum(String credit_sum) {
		this.credit_sum = credit_sum;
	}
	public String getVerify_state() {
		return verify_state;
	}
	public void setVerify_state(String verify_state) {
		this.verify_state = verify_state;
	}
	public String getM_verify_state() {
		return m_verify_state;
	}
	public void setM_verify_state(String m_verify_state) {
		this.m_verify_state = m_verify_state;
	}
	public String getJ_verify_state() {
		return j_verify_state;
	}
	public void setJ_verify_state(String j_verify_state) {
		this.j_verify_state = j_verify_state;
	}
	public String getC_verify_state() {
		return c_verify_state;
	}
	public void setC_verify_state(String c_verify_state) {
		this.c_verify_state = c_verify_state;
	}
	public String getS_verify_state() {
		return s_verify_state;
	}
	public void setS_verify_state(String s_verify_state) {
		this.s_verify_state = s_verify_state;
	}
	public String getUse_limit() {
		return use_limit;
	}
	public void setUse_limit(String use_limit) {
		this.use_limit = use_limit;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStage_plan() {
		return stage_plan;
	}
	public void setStage_plan(String stage_plan) {
		this.stage_plan = stage_plan;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getOverdue_day() {
		return overdue_day;
	}
	public void setOverdue_day(String overdue_day) {
		this.overdue_day = overdue_day;
	}
	public String getArrearsAmount() {
		return arrearsAmount;
	}
	public void setArrearsAmount(String arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}
	
	
}

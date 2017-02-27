package cn.tyiti.xfb.constant;

public class GlobalConstant {
				//session中保存信息
/**验证码 **/		public final static String SESSION_RAND = "session_rand";
/**登陆人账户**/	public final static String SESSION_LOGIN_NAME = "session_loginName";
/**登陆人ID**/	public final static String SESSION_LOGIN_ID = "session_userId";
/**登陆人姓名**/	public final static String SESSION_REAL_NAME = "session_realName";
	  
				//用户状态
/**用户状态：正常**/	public final static String USER_STATE_NORMAL =  "0"; 
/**用户状态：冻结**/	public final static String USER_STATE_FREEZE =  "1";
/**用户状态：拒绝**/	public final static String USER_STATE_REFUSE =  "2";

				//用户类型
/**用户类型：上班族**/	public final static String USER__TYPE_OFFICER = "A1";
/**用户类型：学生**/		public final static String USER__TYPE_STUDENT = "A2";
/**用户类型：企业**/		public final static String USER__TYPE_BUSINESS = "A3";
	
				//审批状态
/**审批状态：草稿**/  	public final static String VERIFY_STATE_DRAFT = "A1";
/**审批状态：驳回**/	  	public final static String VERIFY_STATE_RETURN = "A2";
/**审批状态：审核中**/ 	public final static String VERIFY_STATE_CHECKING = "A3";
/**审批状态：通过**/		public final static String VERIFY_STATE_PAST = "A4";

				//获取序列
/**获取序列参数*/		public final static String GET_MYSQL_SEQ = "t_sys_user";

//
/**获取序列参数*/		public final static String GET_T_BLIST_MESSAGE_SEQ = "t_bList_message";

			//审核日志
/**拒绝*/		public final static String VERIFY_LOG_REFUSE = "A1";
/**通过*/		public final static String VERIFY_LOG_PAST = "A2";
/**认领*/		public final static String VERIFY_LOG_CLAIM = "A3";
/**补件*/		public final static String VERIFY_LOG_BUJIAN = "A4";

			//审核类型
/**A1:身份证信息*/ 	public final static String VERIFY_LOG_TYPE_CARD = "A1";
/**A2:职业信息*/  	public final static String VERIFY_LOG_TYPE_JOB = "A2";
/**A3:联系人信息*/ 	public final static String VERIFY_LOG_TYPE_CONTACT = "A3";
/**A4:芝麻信用*/	public final static String VERIFY_LOG_TYPE_SESAME = "A4";
/**A5:银行卡*/	public final static String VERIFY_LOG_TYPE_BANK = "A5";
/**A6:信用卡*/	public final static String VERIFY_LOG_TYPE_CREDIT = "A6";
/**A7:驾照*/		public final static String VERIFY_LOG_TYPE_DRIVER = "A7";
/**B1:基础信息*/	public final static String VERIFY_LOG_TYPE_BASIC = "B1";
/**C1:全部通过并增加信用额度*/public final static String VERIFY_LOG_TYPE_PAST = "C1";
/**C2:拒绝并禁止用户提交*/public final static String VERIFY_LOG_TYPE_REFUSE = "C2";

			//订单状态
/**1:待确认*/ 	public final static int ORDER_STATE_COMFIRM = 1;
/**2:已收货(分期)*/	public final static int ORDER_STATE_RECEIVE = 2;
/**3:退货中*/ 	public final static int ORDER_STATE_RETURNING = 3;
/**4:已退货*/ 	public final static int ORDER_STATE_RETURN = 4;
/**5:退款中 */ 	public final static int ORDER_STATE_REFUNDING = 5;
/**6:已退款*/ 	public final static int ORDER_STATE_REFUND = 6;
/**9:已还清*/ 	public final static int ORDER_STATE_PAY_OFF = 9;
/**10:待交货*/ 	public final static int ORDER_STATE_DELIVERYING = 10;
/**11:已交货(代收货)*/public final static int ORDER_STATE_DELIVERY = 11;
/**3:待结算 */ 	public final static int ORDER_STATE_CLEMETE = 3;
/**2:待打款 */ 	public final static int ORDER_STATE_CLEARING = 2;
/**1:已打款 */ 	public final static int ORDER_STATE_PLAY_MONEY = 1;
/**14:纠纷中 */ 	public final static int ORDER_STATE_DISPUTES = 14;

			//订单分期操作
/**0:正常分期 */ 	public final static int AUTO_TYPE_APP = 0;
/**1:批处理分期 */ 	public final static int AUTO_TYPE_BAT = 1;
/**2:客服处理完 */ 	public final static int AUTO_TYPE_PRO = 2;

//订单审核操作
/**1:通过*/   public final static String ORDER_VERIFY_PASS ="1";
/**2:拒绝*/   public final static String ORDER_VERIFY_REJECT = "2";


			//用户来源
/**0:APP*/      public final static String USER_SOURCE_APP="0";
/**1:微商城 */    public final static String USER_SOURCE_WEIXIN="1";

		/**
		 * 	根据订单状态获取
		 * 	@return orderStateName
		 * 	@param int orderState 订单状态
		 * */
		public static String getOrderStateName(int orderstate){
			if(GlobalConstant.ORDER_STATE_RECEIVE == orderstate){
				return "已分期";
			}else if(GlobalConstant.ORDER_STATE_RETURN == orderstate){
				return "已退货";
			}else if(GlobalConstant.ORDER_STATE_RETURNING == orderstate){
				return "退货中";
			}else if(GlobalConstant.ORDER_STATE_DELIVERY == orderstate){
				return "待确认收货";
			}else if(GlobalConstant.ORDER_STATE_PAY_OFF == orderstate){
				return "已还清";	
			}else if(GlobalConstant.ORDER_STATE_REFUNDING == orderstate){
				return "退款中";	
			}else if(GlobalConstant.ORDER_STATE_REFUND == orderstate){
				return "已退款";	
			}else if(GlobalConstant.ORDER_STATE_DISPUTES == orderstate){
				return "纠纷中";	
			}else if(GlobalConstant.ORDER_STATE_DELIVERYING == orderstate){
				return "待交货";
			}else{
				return ((Integer)orderstate).toString();
			}
		}
}

package com.hhwy.purchaseweb.contants;

/**
 * <b>类 名 称：</b>BusinessContants<br/>
 * <b>类 描 述：</b>业务相关静态类<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2016年11月11日 下午5:37:12<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class BusinessContants {
	
	/**
	 * 是否有无标志为
	 * 是（有）：1
	 */
	public final static String YESFLAG = "1";
	
	/**
	 * 是否有无标志为
	 * 否（无）：0
	 */
	
	public final static String NOFLAG = "0";
	
	/**
	 * 客户档案实体
	 */
	public final static String CUST_ENTITY = "s_c_customer_info";
	
	/**
	 * 发电企业档案实体
	 */
	public final static String ELEC_ENTE_ENTITY = "s_c_elec_ente_info";
	
	/**
	 * 合同版本标志
	 * 01新建
	 */
	public static final String CONTRACT_VERSION_ADD ="01";
	
	/**
	 * 合同版本标志
	 * 02生效
	 */
	public static final String CONTRACT_VERSION_EFFECT ="02";
	
	/**
	 * 合同版本标志
	 * 03失效
	 */
	public static final String CONTRACT_VERSION_EXPIRE ="03";
	
	/**
	 * 电费参数版本类别
	 * 04 输配电价
	 */
	public static String PAR_VER_TYPE_TRANS_DIST = "04";
	
	/**
     * 电费参数版本类别
     * 06 价差标准版本号
     */
	public static String PAR_VER_TYPE_PRICE_MARGIN = "06";
	
	/**
	 * 电费参数版本类别
	 * 07 考核规则常量
	 */
	public static final String EVALUAT_RULE ="07";
	
	/**
	 * 电费参数版本类别
	 * 08 分成方式常量 
	 */
	public static final String DIVID_WAY_STAND ="08";
	
	/**
	 * 合同代理方式 
	 * 01 全量代理
	 */
	public static final String PROXY_MODE_ALL = "01";
	
	/**
	 * 合同代理方式 
	 * 02 计量点代理
	 */
	public static final String PROXY_MODE_MP = "02";
	
	/**
     * 输配电价计费参数信息 发布标志
     *  1 已发布
     */
	public static final String REALEASE_FLAG_FINISH = "1";
	
	/**
     * 输配电价计费参数信息 发布标志
     *  0 未发布
     */
	public static final String REALEASE_FLAG_NO = "0";
	
	/**
     * 变动标志
     * 01 新增
     */
	public static final String IDU_FLAG_ADD = "01";
	
	/**
     * 变动标志
     * 02 修改 
     */
	public static final String IDU_FLAG_EDIT = "02";
	
	/**
     * 变动标志
     * 03 删除
     */
	public static final String IDU_FLAG_DELETE = "03";
	
	/**
     * 审批状态
     * 01 草稿
     */
	public static final String APPROVAL_STATE_DRAFT = "01";
	
	/**
     * 审批状态
     * 02 流转中
     */
	public static final String APPROVAL_STATE_CIRCULATION = "02";
	
	/**
     * 审批状态
     * 03 归档
     */
	public static final String APPROVAL_STATE_INFILE = "03";
	
	/**
     * 上报状态
     * 01 已上报; 02 未上报
     */
	public static final String REPORT_STATE_FINISH = "01";
	
	/**
     * 上报状态
     * 02 未上报
     */
	public static final String REPORT_STATE_NO = "02";
	
	/**
     * 补充协议 协议状态
     * 01 正常; 02 作废; 03 超期; 04 未签
     */
	public static final String AGREEMENT_STATE_NORMAL = "01";
	
	/**
     * 补充协议 协议状态
     * 04 未签
     */
	public static final String AGREEMENT_STATE_NOSIGN = "04";
	
	/**
     * 电价码的序列号首位为9
     * 
     */
	public static final String PRCCODE_FIRST_PLACE = "9";
	
	/**
	 * 一月
	 */
	public static final String JAN = "jan";
	
	/**
	 * 二月
	 */
	public static final String FEB = "feb";
	
	/**
	 * 三月
	 */
	public static final String MAR = "mar";
	
	/**
	 * 四月
	 */
	public static final String APR = "apr";
	
	/**
	 * 五月
	 */
	public static final String MAY = "may";
	
	/**
	 * 六月
	 */
	public static final String JUN = "jun";
	
	/**
	 * 七月
	 */
	public static final String JUL = "jul";
	
	/**
	 * 八月
	 */
	public static final String AUG = "aug";
	
	/**
	 * 九月
	 */
	public static final String SEP = "sep";
	
	/**
	 * 十月
	 */
	public static final String OCT = "oct";
	
	/**
	 * 十一月
	 */
	public static final String NOV = "nov";
	
	/**
	 * 十二月
	 */
	public static final String DECE = "dece";
	
	/**
	 * 分成方式：01 保底
	 * 
	 */
	public static final String DIVIDEWAYCODE01 = "01";
	
	/**
	 * 分成方式：03 分成
	 * 
	 */
	public static final String DIVIDEWAYCODE03 = "03";
	
	/**
	 * 分成方式：04 保底或分成
	 * 
	 */
	public static final String DIVIDEWAYCODE04 = "04";
	
	/**
	 * 分成方式：05 保底加分成
	 * 
	 */
	public static final String DIVIDEWAYCODE05 = "05";
	
	/**
	 * 分成方式：06 代理服务费
	 * 
	 */
	public static final String DIVIDEWAYCODE06 = "06";
	//分成方式：07 联动保底加分成
	public static final String DIVIDEWAYCODE07 = "07";
	
	/**
	 * 收益模式选择
	 * 01 甲方收益最大
	 */
	public static final String PROFITMODEA = "01";
	
	/**
	 * 收益模式选择
	 * 02 乙方收益最大 
	 */
	public static final String PROFITMODEB = "02";
	
	/**
	 * 惩罚类型
	 * 0 不惩罚
	 */
	public static final String PUNISHTYPE0 = "0";
	
	/**
	 * 惩罚类型
	 * 1 惩罚
	 */
	public static final String PUNISHTYPE1 = "1";
	
	/**
	 * 惩罚附加项
	 * 1 售方不罚用户不罚
	 */
	public static final String PUNISHFLAG1 = "1";
	
	/**
	 * 惩罚电价基准类型
	 * 01 目录电价
	 */
	public static final String PUNISHPRCBASE01 = "01";
	
	/**
	 * 惩罚电价基准类型
	 * 02 
	 */
	public static final String PUNISHPRCBASE02 = "02";
	
	/**
	 * 惩罚电价基准类型
	 * 03 长协电价
	 */
	public static final String PUNISHPRCBASE03 = "03";
	
	/**
	 * 结算类型
	 * 01 价差
	 */
	public static final String SETTLEMENTMODECODE01 = "01";
	
	/**
	 * 结算类型
	 * 02 价格
	 */
	public static final String SETTLEMENTMODECODE02 = "02";
	
	/**
	 * 赔偿类型
	 * 0 不赔偿
	 */
	public static final String COMPENSATEFLAG0 = "0";
	
	/**
	 * 赔偿类型
	 * 1赔偿
	 */
	public static final String COMPENSATEFLAG1 = "1";
	
	/**
	 * 电价类型
	 * 01 人工录入*
	 * 02合同加权
	 * 03目录电价
	 * 04竞价电价
	 */
	public static final String PRCTYPE01 = "01";
	
	/**
	 * 电价类型
	 * 01 人工录入
	 * 02合同加权*
	 * 03目录电价
	 * 04竞价电价
	 */
	public static final String PRCTYPE02 = "02";
	
	/**
	 * 电价类型
	 * 01 人工录入
	 * 02合同加权
	 * 03目录电价*
	 * 04竞价电价
	 */
	public static final String PRCTYPE03 = "03";
	
	/**
	 * 电价类型
	 * 01 人工录入
	 * 02合同加权
	 * 03目录电价
	 * 04竞价电价*
	 */
	public static final String PRCTYPE04 = "04";
	
	/**
	 * 核对状态
	 * 01未核对
	 * 02 已核对
	 */
	public static final String UNCHECKSTATUS = "01";
	
	/**
	 * 核对状态
	 * 01未核对
	 * 02 已核对
	 */
	public static final String CHECKSTATUS = "02";
	
	/**
     * 月度竞价交易流程 00 计划指定
     */
    public static final String SELL_MONTHBIDSTATUS00 = "00"; 
    
	/** 
	 * 月度竞价交易流程 01 电量审核
	 */
	public static final String SELL_MONTHBIDSTATUS01 = "01"; 

	/**
	 * 月度竞价交易流程 02 交易申报
	 */
	public static final String SELL_MONTHBIDSTATUS02 = "02"; 

	/**
	 * 月度竞价交易流程 03 成交确认
	 */
	public static final String SELL_MONTHBIDSTATUS03 = "03"; 

	/**
	 * 月度竞价交易流程 04 已完成
	 */
	public static final String SELL_MONTHBIDSTATUS04 = "04"; 

	/**
	 * 月度竞价交易流程 05已录入
	 */
//	public static final String SELL_MONTHBIDSTATUS05 = "05"; 

	/**
	 * 月度竞价交易流程 06已结算
	 */
//	public static final String SELL_MONTHBIDSTATUS06 = "06"; 

	/**
	 * 月度竞价交易流程 07已开票
	 */
//	public static final String SELL_MONTHBIDSTATUS07 = "07"; 
	
	/**
	 * 消息分类 审核提醒消息
	 */
	public static final String MESSAGETYPE01 = "ph_m_agre_pq_examine";
	
	/**
	 * 消息分类 上报提醒信息
	 */
	public static final String MESSAGETYPE02 = "s_m_report_pq";
	
	/**
	 * 消息分类 公告消息
	 */
	public static final String MESSAGETYPE03 = "s_w_message";
	
	/**
	 * 短信发送成功与失败标志01、失败；02、成功
	 */
	
	public static final String SEND_SMS_FAIL = "01";
	public static final String SEND_SMS_SUCCESS = "02";
	
	/**
	 * 交割电量分配原则：长协优先
	 */
	public static final String PRIORITYFLAG01 = "01";
	
	/**
	 * 交割电量分配原则：竞价优先
	 */
	public static final String PRIORITYFLAG02 = "02";
	
	/**
	 * 交割电量分配原则：等比例
	 */
	public static final String PRIORITYFLAG03 = "03";
	
	/**
	 * 规则失效
	 * 01失效
	 */
	public static final String RULE_EFFECT01 ="01";
	
	/**
	 * 规则生效
	 * 02生效
	 */
	public static final String RULE_EFFECT02 ="02";
	
	/**
	 * 收益优先级
	 * 01 甲方收益最大
	 */
	public static final String PROFIT_FIRST_CODE01 = "01";
	
	/**
	 * 收益优先级
	 * 02 乙方收益最大
	 */
	public static final String PROFIT_FIRST_CODE02 = "02";
	
}

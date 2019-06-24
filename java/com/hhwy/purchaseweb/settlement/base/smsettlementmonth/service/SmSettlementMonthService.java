package com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmDealInfo;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthVo;

 /**
 * <b>类 名 称：</b>SmSettlementMonthService<br/>
 * <b>类 描 述：</b><br/>		月度收益结算service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年5月26日 下午4:54:03<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SmSettlementMonthService{
	
	/**
	 * 分页获取对象SmSettlementMonth
	 * @param SmSettlementMonthVo
	 * @return QueryResult
	 */
	public QueryResult<SmSettlementMonthDetail> getSmSettlementMonthByPage(SmSettlementMonthVo smSettlementMonthVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmSettlementMonth列表
	 * @param SmSettlementMonthVo
	 * @return List
	 */
	public List<SmSettlementMonthDetail> getSmSettlementMonthListByParams(SmSettlementMonthVo smSettlementMonthVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmSettlementMonth数量
	 * @param SmSettlementMonthVo
	 * @return Integer
	 */
	public Integer getSmSettlementMonthCountByParams(SmSettlementMonthVo smSettlementMonthVo);
	
	
	/**
	 * 根据ID获取对象SmSettlementMonth
	 * @param ID
	 * @return SmSettlementMonth
	 */
	public SmSettlementMonth getSmSettlementMonthById(String id);
	
	/**
	 * @Title: getSmSettlementMonthByYm
	 * @Description: 根据ym年月获取结算主表信息
	 * @param ym	yyyyMM格式
	 * @return 
	 * SmSettlementMonth
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月23日 下午2:09:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月23日 下午2:09:34
	 * @since  1.0.0
	 */
	public SmSettlementMonth getSmSettlementMonthByYm(String ym);
	
	/**
	 * @Title: getSmSettlementMonthByYm
	 * @Description: 根据ym年月获取结算主表id
	 * @param ym	yyyyMM格式
	 * @return 
	 * SmSettlementMonth
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午9:53:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午9:53:31
	 * @since  1.0.0
	 */
	public String getSmSettlementMonthIdByYm(String ym);
	
	/**
	 * @Title: getSmSettlementMonthInfoByIdOrYm
	 * @Description: 通过结算id或者年月获取结算综合数据（包括用户数、委托电量、实际用电量、总购电量等）
	 * @param settleId
	 * @param ym
	 * @return 
	 * SmSettlementMonthDetail
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月27日 上午9:59:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月27日 上午9:59:31
	 * @since  1.0.0
	 */
	public SmSettlementMonthDetail getSmSettlementMonthInfoByIdOrYm(String settleId, String ym);
	
	/**
	 * 添加对象SmSettlementMonth
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmSettlementMonth(SmSettlementMonth smSettlementMonth);
	
	/**
	 * 添加对象SmSettlementMonth列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmSettlementMonthList(List<SmSettlementMonth> smSettlementMonthList);
	
	/**
	 * 更新对象SmSettlementMonth
	 * @param 实体对象
	 * @return SmSettlementMonth
	 */
	public void updateSmSettlementMonth(SmSettlementMonth smSettlementMonth);
	
	/**
	 * @Title: validateDdeleteSettle
	 * @Description: 先验证是否含有已归档的结算方案，如果没有则删除一个月度结算的所有相关信息(江苏)
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月26日 下午5:49:05
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年5月26日 下午5:49:05
	 * @since  1.0.0
	 */
	public void validateDeleteSettle(String settleId) throws Exception;
	
	/**
	 * @Title: deleteSettlementInfo
	 * @Description: 直接根据结算id删除其下所有方案中的用户收益信息、售电公司收益信息、批发市场购电支出明细 信息、用户结算偏差费用信息、月度结算方案详情信息、所有方案、结算主表信息、
	 * 				发票登记信息（福建省可直接删，江苏省已归档方案走不到此步）
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月26日 下午5:49:05
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年5月26日 下午5:49:05
	 * @since  1.0.0
	 */
	public void deleteSettlementInfo(String settleId);

	/**
	 * @Title: calculateDeliveryPrc
	 * @Description: 计算单个用户的 结算电价（山西辽宁 的成交信息录入页面用到）,以后需要提到山西辽宁的结算中
	 * @param List<PhmDealInfo> ,其中consId, ym, dealPrc三个属性有用，结果存在 deliveryPrc属性中
	 * 				tradeMode  交易方式(01：集中竞价，02：双边协商，03：挂牌)
	 * @return  void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年7月2日 下午4:30:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年7月2日 下午4:30:07
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void calculateDeliveryPrc(List<PhmDealInfo> dealInfoList, String tradeMode) throws Exception;
}
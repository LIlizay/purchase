package com.hhwy.purchaseweb.arithmetic.balance.service;

import java.math.BigDecimal;

import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.arithmetic.balance.domain.ConsDealProfitResult;
import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.CompensateRule;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationObject;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationResult;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationRule;
import com.hhwy.purchaseweb.arithmetic.divide.domain.DivideObject;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;

public interface BalanceService{

	/**
	 * 
	 * @Title: getCompDealProfit
	 * @Description: 售电公司交易利润计算 
	 * @param bidPq   竞价交易电量
	 * @param bidPrc  竞价交易电价	(平均电价)
	 * @param lcPq    长协交易电量	（写0）
	 * @param lcPrc   长协交易电价	（null）
	 * @param year   计算年份（用于获取年长协合同电量及金额）
	 * @param amtScale   数据精度	CommonService.getScaleAmt
	 * @return
	 * @throws Exception 
	 * BigDecimal  交易利润
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年8月6日 下午5:18:05
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年8月6日 下午5:18:05
	 * @since  1.0.0
	 */
	public BigDecimal getCompDealProfit(SmRuleConfigure ruleObj,BigDecimal deliveryPq,BigDecimal bidPq,BigDecimal bidPrc,
			BigDecimal lcPq,BigDecimal lcPrc,String year,AmtScale amtScale) throws Exception;	
	/**
	 * @Title: getSmCalcConfigureDetail<br/>
	 * @Description: 查询规则配置<br/>
	 * @return <br/>
	 * SmCalcConfigureDetail<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月23日 上午10:38:04
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月23日 上午10:38:04
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public SmCalcConfigureDetail getSmCalcConfigureDetail() throws Exception;
	
	/**
	 * 
	 * @Title: getCompDeviationProfit
	 * @Description: 售电公司交易偏差考核计算
	 * @param ruleObj 省考核规则
	 * @param deviationObj 计算参数
	 * @param amtScale 数据精度		CommonService.getScaleAmt
	 * @return
	 * @throws Exception 
	 * DeviationResult 考核结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年8月6日 下午9:09:21
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年8月6日 下午9:09:21
	 * @since  1.0.0
	 */
	public DeviationResult getCompDeviationProfit(SmRuleConfigure ruleObj,
			DeviationObject deviationObj,AmtScale amtScale) throws Exception;	
	
	/**
	 * 
	 * @Title: getConsDealProfit
	 * @Description: 用户交易利润计算
	 * @param rule 用户考核规则			（售电公司考核用户）
	 * @param divideObj 用户利润计算参数      	(分成方式)
	 * @param amtScale 数据精度
	 * 			compensateRule				（赔偿,用户考核售电公司）
	 * @return
	 * @throws Exception 
	 * ConsDealProfitResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年8月6日 下午9:15:57
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年8月6日 下午9:15:57
	 * @since  1.0.0
	 */
	public ConsDealProfitResult getConsDealProfit(DeviationRule rule,
			DivideObject divideObj,AmtScale amtScale,CompensateRule compensateRule) throws Exception;

}

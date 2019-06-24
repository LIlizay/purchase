package com.hhwy.purchaseweb.arithmetic.divide.service;

import java.math.BigDecimal;
import java.util.Map;

import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.divide.domain.DivideObject;

public interface DivideService {
	
	/**
	 * 
	 * @Title: getDivide
	 * @Description: 用户利润
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午8:23:10
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午8:23:10
	 * @since  1.0.0
	 */
	public BigDecimal getDivide(DivideObject divideObj,BigDecimal upperProp,
			AmtScale amtScale,String dealPriority) throws Exception;
	
	/**
	 * 
	 * @Title: getLcMarginAndBidDivideProfit
	 * @Description: 用户利润（分成方式为长协保底加竞价分成）
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午7:33:40
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午7:33:40
	 * @since  1.0.0
	 */
	public BigDecimal getLcMarginAndBidDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception;
	
	/**
	 * 
	 * @Title: getMarginAndDivideProfit
	 * @Description: 用户利润（分成方式为保底加分成）
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午5:59:05
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午5:59:05
	 * @since  1.0.0
	 */
	public BigDecimal getMarginAndDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception;
	
	/**
	 * 
	 * @Title: getMarginProfit
	 * @Description: 用户利润（分成方式为保底）
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午3:52:50
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午3:52:50
	 * @since  1.0.0
	 */
	public BigDecimal getMarginProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getDivideProfit
	 * @Description: 用户利润（分成方式为分成）
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午5:06:20
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午5:06:20
	 * @since  1.0.0
	 */
	public BigDecimal getDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception;
	
	/**
	 * 
	 * @Title: getConsDealProfit
	 * @Description: 用户交易利润计算
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午4:41:51
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午4:41:51
	 * @since  1.0.0
	 */
	public Map<String,BigDecimal> getConsDealProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception;
			
	/**
	 * 
	 * @Title: getLcPriorityProfit
	 * @Description: 长协优先时代理单一用户交易利润
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @return
	 * @throws Exception 
	 * Map<String,BigDecimal> 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午4:12:01
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午4:12:01
	 * @since  1.0.0
	 */
	public Map<String,BigDecimal> getLcPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getBidPriorityProfit
	 * @Description: 竞价优先时代理单一用户交易利润
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @return
	 * @throws Exception 
	 * Map<String,BigDecimal> 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月31日 下午4:03:05
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月31日 下午4:03:05
	 * @since  1.0.0
	 */
	public Map<String,BigDecimal> getBidPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getNoPriorityProfit
	 * @Description: 不分先后顺序时代理单一用户交易利润
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月30日 下午7:02:35
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月30日 下午7:02:35
	 * @since  1.0.0
	 */
	public BigDecimal getNoPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception;

}

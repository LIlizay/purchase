package com.hhwy.purchaseweb.arithmetic.deviation.service;

import java.math.BigDecimal;

import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.CompensateRule;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationObject;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationResult;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationRule;

/**
 * 
 * <b>类 名 称：</b>DeviationService<br/>
 * <b>类 描 述：</b>价差模式偏差考核接口<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月28日 下午4:47:42<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface DeviationService {

	/**
	 * 
	 * @Title: getDeviation
	 * @Description: 计算偏差考核
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午7:55:05
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午7:55:05
	 * @since  1.0.0
	 */
	public DeviationResult getDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getUpperDeviation
	 * @Description:正偏差考核计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午8:06:39
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午8:06:39
	 * @since  1.0.0
	 */
	public DeviationResult getUpperDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getLowerDeviation
	 * @Description: 负偏差考核计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午8:07:20
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午8:07:20
	 * @since  1.0.0
	 */
	public DeviationResult getLowerDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getLowerCheckDeviation
	 * @Description: 不拆分考核时负偏差计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午8:56:27
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午8:56:27
	 * @since  1.0.0
	 */
	public DeviationResult getLowerCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getLowerCheckDeviation
	 * @Description: 拆分考核时长协负偏差计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午8:56:27
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午8:56:27
	 * @since  1.0.0
	 */
	public DeviationResult getLowerLcCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getLowerCheckDeviation
	 * @Description: 拆分考核时竞价负偏差计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月27日 下午8:56:27
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月27日 下午8:56:27
	 * @since  1.0.0
	 */
	public DeviationResult getLowerBidCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getLowerPropCheckDeviation
	 * @Description: 按比例计算负偏差
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * DeviationResult 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年7月28日 上午9:21:14
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年7月28日 上午9:21:14
	 * @since  1.0.0
	 */
	public DeviationResult getLowerPropCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;
	
	/**
	 * 
	 * @Title: getCompensateDeviation
	 * @Description: 售电公司赔偿金额计算
	 * @param rule   计算规则
	 * @param deviationObj  计算参数
	 * @param amtScale 计算精度
	 * @return
	 * @throws Exception 
	 * BigDecimal 计算结果
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年8月15日 下午8:52:31
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年8月15日 下午8:52:31
	 * @since  1.0.0
	 */
	public BigDecimal getCompensateDeviation(CompensateRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception;

}

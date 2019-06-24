package com.hhwy.purchaseweb.settlement.jiangsu.service;

import java.math.BigDecimal;

import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeDetailServiceJs<br/>
 * <b>类 描 述：</b><br/>		江苏的结算方案详情的service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月27日 下午9:55:30<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SettlementServiceJs{
	
	/**
	 * @Title: calculateDeliveryPrc
	 * @Description: 计算单个用户的 结算电价	及  服务费用
	 * @param detail
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月27日 下午10:06:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午10:22:12
	 * @since  1.0.0
	 */
	public BigDecimal calculateDeliveryPrc(SmPurchaseSchemeDetailDetail detail) throws Exception ;
	
	/**
	 * @Title: caculateProfit
	 * @Description: 计算用户结算数据和售电公司结算数据
	 * @param smPurchaseSchemeVoJs
	 * @return
	 * @throws Exception 
	 * SettlementVo
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月28日 下午3:09:21
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月28日 下午3:09:21
	 * @since  1.0.0
	 */
	public SmPurchaseSchemeVoJs caculateProfit(SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) throws Exception;
}
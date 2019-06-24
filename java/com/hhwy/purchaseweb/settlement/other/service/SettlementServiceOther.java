package com.hhwy.purchaseweb.settlement.other.service;

import java.util.List;

import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.other.domain.RetailDetailOther;
import com.hhwy.purchaseweb.settlement.other.domain.SmPurchaseSchemeVoOther;

/**
 * <b>类 名 称：</b>SettlementServiceOther<br/>
 * <b>类 描 述：</b><br/>		江苏、福建以外其他省的结算service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:39:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SettlementServiceOther{
	
	/**
	 * @Title: getRetailDetailByYm
	 * @Description: 根据年月获取当月“零售市场售电收入明细”列表(若当月已有结算数据，则取结算数据；若当月没有结算数据，则组装初始化的结算数据)
	 * @param ym
	 * @return
	 * @throws Exception 
	 * List<RetailDetailOther>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:35:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:35:18
	 * @since  1.0.0
	 */
	public List<RetailDetailOther> getRetailDetailByYm(String ym) throws Exception;
	
	/**
	  * @Title: saveOrUpdateSmPurchaseSchemeVoOther
	  * @Description: 其他省月度收益结算方案新增或修改
	  * @param smPurchaseSchemeVoOther
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年9月6日 下午2:12:52
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年9月6日 下午2:12:52
	  * @since  1.0.0
	 */
	public void saveOrUpdateSmPurchaseSchemeVoOther(SmPurchaseSchemeVoOther smPurchaseSchemeVoOther) throws Exception;

	/**
	  * @Title: getSmSettlementMonthInfoByIdOrYm
	  * @Description: 通过结算id或者年月获取结算方案列表页面上面的的form表单中的数据
	  * @param settleId
	  * @param ym
	  * @return SmSettlementMonthDetail
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年9月10日 下午1:52:16
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年9月10日 下午1:52:16
	  * @since  1.0.0
	 */
	public SmSettlementMonthDetail getSmSettlementMonthInfoByIdOrYm(String settleId, String ym);
	
}
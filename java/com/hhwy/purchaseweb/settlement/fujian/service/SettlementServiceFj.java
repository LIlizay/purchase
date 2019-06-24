package com.hhwy.purchaseweb.settlement.fujian.service;

import java.util.List;

import com.hhwy.purchaseweb.settlement.fujian.domain.FormDataAndCostListFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.RetailDetailFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.SmPurchaseSchemeVoFj;

/**
 * <b>类 名 称：</b>SettlementServiceFj<br/>
 * <b>类 描 述：</b><br/>		福建的结算service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:39:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SettlementServiceFj{
	
	/**
	 * @Title: getFormDataAndCostListFjByYm
	 * @Description: 通过年月（新增编辑页面，即add页面）获取结算方案列表页面上面的 form表单中的数据 和 “批发交易结算明细”列表数据
	 * 						当月无论有没有结算数据，都组装初始化的结算数据
	 * 				福建结算  新增或编辑 页面会调用此方法
	 * @param ym
	 * @return
	 * @throws Exception 
	 * FormDataAndCostListFj
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午3:03:56
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午3:03:56
	 * @since  1.0.0
	 */
	public FormDataAndCostListFj getFormDataAndCostListFjByYm(String ym) throws Exception ;
	/**
	 * @Title: getFormDataAndCostListFjBySettleId
	 * @Description: 通过结算id(detail页面)获取结算方案列表页面上面的 form表单中的数据 和 “批发交易结算明细”列表数据
	 * 						当月已有结算数据，取结算数据
	 * 				福建结算  详情页面会调用此方法
	 * @param settleId
	 * @return
	 * @throws Exception 
	 * FormDataAndCostListFj
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午3:04:47
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午3:04:47
	 * @since  1.0.0
	 */
	public FormDataAndCostListFj getFormDataAndCostListFjBySettleId(String settleId) throws Exception ;
	
	/**
	 * @Title: getRetailDetailByYm
	 * @Description: 根据年月获取当月“零售市场售电收入明细”列表(若当月已有结算数据，则结合结算数据获取初始化数据；若当月没有结算数据，则组装初始化的结算数据)
	 * 				，是新增或编辑页面调用
	 * @param ym
	 * @return
	 * @throws Exception 
	 * List<RetailDetailFj>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:35:18
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:35:18
	 * @since  1.0.0
	 */
	public List<RetailDetailFj> getRetailDetailByYm(String ym) throws Exception;
	
	/**
	 * @Title: getRetailDetailBySettledId
	 * @Description: 根据结算id获取当月“零售市场售电收入明细”列表，是详情页面调用
	 * @param settleId
	 * @return
	 * @throws Exception 
	 * List<RetailDetailFj>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午1:45:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午1:45:40
	 * @since  1.0.0
	 */
	public List<RetailDetailFj> getRetailDetailBySettledId(String settleId) throws Exception;
	
	/**
	 * @Title: saveOrUpdateSmPurchaseSchemeVoFj
	 * @Description: 福建省月度收益结算的新增或修改
	 * @param smPurchaseSchemeVoFj 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:06:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:06:20
	 * @since  1.0.0
	 */
	public void saveOrUpdateSmPurchaseSchemeVoFj(SmPurchaseSchemeVoFj smPurchaseSchemeVoFj) throws Exception;
	
	/**
	 * @Title: deleteSettlementInfoFj
	 * @Description: 福建省结算删除，先验证是否存在发票登记信息，如果有则不允许删除
	 * @param settleId
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午1:33:01
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午1:33:01
	 * @since  1.0.0
	 */
	public void deleteSettlementInfoFj(String settleId) throws Exception;
	
}
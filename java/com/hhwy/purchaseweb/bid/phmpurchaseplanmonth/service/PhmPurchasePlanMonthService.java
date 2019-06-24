package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;

/**
 * IPhmPurchasePlanMonthService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmPurchasePlanMonthService{

	/**
	 * 分页获取对象PhmPurchasePlanMonth
	 * @param PhmPurchasePlanMonthVo
	 * @return QueryResult
	 */
	public QueryResult<PhmPurchasePlanMonthDetail> getPhmPurchasePlanMonthByPage(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmPurchasePlanMonth列表
	 * @param PhmPurchasePlanMonthVo
	 * @return List
	 */
	public List<PhmPurchasePlanMonthDetail> getPhmPurchasePlanMonthListByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmPurchasePlanMonth
	 * @param ID
	 * @return PhmPurchasePlanMonth
	 */
	public PhmPurchasePlanMonth getPhmPurchasePlanMonthById(String id);

	/**
	  * @Title: getPhmPurchasePlanMonthByYm
	  * @Description: 根据年月判断购电计划是否存在
	  * @param ym
	  * @return boolean
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年3月9日 下午3:19:37
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年3月9日 下午3:19:37
	  * @since  1.0.0
	 */
	public boolean getPhmPurchasePlanMonthByYm(String ym);
	

	/**
	 * 添加对象PhmPurchasePlanMonth
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPurchasePlanMonth(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception;
	
	/**
	  * @Title: deletePhmPurchasePlanMonth
	  * @Description: 删除对象PhmPurchasePlanMonth
	  * @param phmPurchasePlanMonthVo
	  * @return boolean
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月27日 下午3:37:35
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月27日 下午3:37:35
	  * @since  1.0.0
	 */
	public boolean deletePhmPurchasePlanMonth(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo);
	
	/**
	 * 提交
	 * submit(描述这个方法的作用)<br/>
	 * @param planId 
	 * void
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public void submit(String planId) throws Exception;
	
	/**
	  * @Title: recall
	  * @Description: 月度购电相关流程回退
	  * @param id
	  * @param planStatus
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年12月6日 下午1:50:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年12月6日 下午1:50:50
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void recall(String id, String planStatus) throws Exception;

	/**
	  * @Title: updatePlanStatusByPlanId
	  * @Description: 根据计划id更新计划状态
	  * @param planId
	  * @param sellMonthbidstatus02
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午3:12:41
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午3:12:41
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void updatePlanStatusByPlanId(String planId,String sellMonthbidstatus02) throws Exception;

	/**
	  * @Title: getPqCountByPlanId
	  * @Description: 根据计划id获取本次委托电量、已成交电量、已申报电量
	  * @param id
	  * @return Map<String,Object>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月26日 下午4:36:01
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月26日 下午4:36:01
	  * @since  1.0.0
	 */
	public Map<String, Object> getPqCountByPlanId(String id);
	
	/**
	 * @Title: getPhmPurchasePlanMonthListByYm
	 * @Description: 根据年月获取（本月成交电量、本月代理总电量、长协电量、竞价电量）等数据
	 * @param ym
	 * @return PhmPurchasePlanMonthDetail
	 * @throws Exception 
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年10月26日 下午6:41:52
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年10月26日 下午6:41:52
	 * @since  1.0.0
	 */
	public PhmPurchasePlanMonthDetail getPhmPurchasePlanMonthListByYm(String ym) throws Exception;
	
}
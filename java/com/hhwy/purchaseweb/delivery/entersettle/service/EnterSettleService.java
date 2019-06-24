package com.hhwy.purchaseweb.delivery.entersettle.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleDetail;
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleVo;
import com.hhwy.purchaseweb.delivery.entersettle.domain.SettleDetailDetail;

public interface EnterSettleService {

	/**
	  * @Title: getEnterSettleByPage
	  * @Description: 分页获取录入结算信息
	  * @param enterSettleVo
	  * @return QueryResult<EnterSettleDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 上午10:38:52
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 上午10:38:52
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<EnterSettleDetail> getEnterSettleByPage(EnterSettleVo enterSettleVo) throws Exception;

	/**
	  * @Title: getSettleDetailDetail
	  * @Description: 根据结算id获取用户结算详情
	  * @param id
	  * @return List<SettleDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 下午4:37:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 下午4:37:56
	  * @since  1.0.0
	 */
	public List<SettleDetailDetail> getSettleDetailDetail(EnterSettleVo enterSettleVo);

	/**
	  * @Title: checkRepeatByMonth
	  * @Description: 购电年月判重
	  * @param ym
	  * @return boolean
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月15日 下午6:30:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月15日 下午6:30:45
	  * @since  1.0.0
	 */
	public boolean checkRepeatByMonth(String ym);

	/**
	  * @Title: getEnterSettleDetailByYm
	  * @Description: 根据年月获取合同用户信息
	  * @param ym
	  * @return List<SettleDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 上午9:34:48
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 上午9:34:48
	  * @since  1.0.0
	 */
	public List<SettleDetailDetail> getEnterSettleDetailByYm(String ym);

	/**
	  * @Title: getPurchasePqByYm
	  * @Description: 根据年月获取该月总购电量（当月购电合同和竞价电量之和）
	  * @param ym
	  * @return Map<String,Object>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 上午10:49:48
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 上午10:49:48
	  * @since  1.0.0
	 */
	public Map<String, Object> getPurchasePqByYm(String ym);

	/**
	  * @Title: saveOrUpdateSettle
	  * @Description: 新增或更新结算数据
	  * @param enterSettleVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月16日 下午5:06:51
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月16日 下午5:06:51
	  * @since  1.0.0
	 */
	public void saveOrUpdateSettle(EnterSettleVo enterSettleVo);

	/**
	  * @Title: deleteSettle
	  * @Description: 根据ids删除结算数据
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月17日 下午1:55:51
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月17日 下午1:55:51
	  * @since  1.0.0
	 */
	public void deleteSettle(String[] ids);


}

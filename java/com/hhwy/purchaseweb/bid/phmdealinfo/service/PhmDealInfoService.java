package com.hhwy.purchaseweb.bid.phmdealinfo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmTransactionReportDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.MonthDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoVo;



/**
 * IPhmDealInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmDealInfoService{

	/**
	  * @Title: deletePhmDealInfoByPlanId
	  * @Description: 根据planId删除成交信息
	  * @param planId
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:40:11
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:40:11
	  * @since  1.0.0
	 */
	public void deletePhmDealInfoByPlanId(String planId);

	/**
	  * @Title: getPhmDealInfoByPlanId
	  * @Description: 根据计划id获取其他省竞价、挂牌交易成交信息录入
	  * @param planId
	  * @return
	  * List<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午4:43:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午4:43:31
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<PhmDealInfoDetail> getPhmDealInfoByPlanId(String planId) throws Exception;

	/**
	  * @Title: getYearDealInfoByPlanId
	  * @Description: （年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return List<MonthDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午8:32:07
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午8:32:07
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<MonthDealInfoDetail> getYearDealInfoByPlanId(String planId) throws Exception;

	/**
	  * @Title: getMonthDealInfoByYm
	  * @Description: 根据计划年月获取该年所有已成交年交易详情信息
	  * @param ym
	  * @return List<MonthDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月1日 下午1:18:44
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月1日 下午1:18:44
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<MonthDealInfoDetail> getMonthDealInfoByYm(String ym) throws Exception;

	/**
	  * @Title: savePhmDealInfo
	  * @Description: 添加成交信息
	  * @param phmDealInfoVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 上午10:38:42
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 上午10:38:42
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void savePhmDealInfo(PhmDealInfoVo phmDealInfoVo) throws Exception;

	/**
	  * @Title: getDealListByReportId
	  * @Description: 根据申报id获取该申报段成交信息详情
	  * @param detailVo
	  * @return List<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 下午1:29:37
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 下午1:29:37
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<PhmDealInfoDetail> getDealListByReportId(PhmDealInfoVo detailVo) throws Exception;

	/**
	  * @Title: saveMonthList
	  * @Description: 年度分月计划成交信息保存（年交易）
	  * @param detailVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 下午5:42:41
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 下午5:42:41
	  * @since  1.0.0
	 */
	public void saveMonthList(PhmDealInfoVo detailVo);

	/**
	  * @Title: saveSubMonthList
	  * @Description: 年度分月计划成交信息保存（月交易）
	  * @param detailVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 上午8:31:43
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 上午8:31:43
	  * @since  1.0.0
	 */
	public void saveSubMonthList(PhmDealInfoVo detailVo);

	/**
	  * @Title: submit
	  * @Description: 成交信息提交
	  * @param planId
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 上午9:32:36
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 上午9:32:36
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void submit(String planId) throws Exception;

	/**
	  * @Title: getSbDealInfoByPlanId
	  * @Description: 根据计划id获取其它省双边协商交易成交信息录入
	  * @param planId
	  * @return List<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午2:15:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午2:15:50
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<PhmDealInfoDetail> getSbDealInfoByPlanId(String planId) throws Exception;

	/**
	  * @Title: getSLDealInfoByPlanId
	  * @Description: 根据planId获取山西、辽宁省竞价、挂牌交易成交信息录入
	  * @param detailVo
	  * @return
	  * QueryResult<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月4日 上午6:34:39
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月4日 上午6:34:39
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmDealInfoDetail> getSLDealInfoByPlanId(PhmDealInfoVo detailVo) throws Exception;

	/**
	  * @Title: saveSLDealInfo
	  * @Description: 山西、辽宁省成交信息存储
	  * @param phmDealInfoVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午11:46:16
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午11:46:16
	  * @since  1.0.0
	 */
	public void saveSLDealInfo(PhmDealInfoVo phmDealInfoVo);

	/**
	  * @Title: getSLSBDealInfoByPlanId
	  * @Description: 根据计划id获取山西、辽宁省双边协商交易成交信息
	  * @param detailVo
	  * @return QueryResult<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月4日 上午7:37:05
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月4日 上午7:37:05
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmDealInfoDetail> getSLSBDealInfoByPlanId(PhmDealInfoVo detailVo) throws Exception;

	/**
	  * @Title: getSbYearDealInfoByPlanId
	  * @Description: （双边年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return List<MonthDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月20日 下午7:41:23
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月20日 下午7:41:23
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<MonthDealInfoDetail> getSbYearDealInfoByPlanId(String planId) throws Exception;

	/**
	  * @Title: saveSbYearList
	  * @Description: 双边年交易信息保存
	  * @param phmDealInfoVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月22日 下午1:14:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月22日 下午1:14:45
	  * @since  1.0.0
	 */
	public void saveSbYearList(PhmDealInfoVo phmDealInfoVo);

	/**
	  * @Title: saveSLYearDealInfo
	  * @Description: 山西、辽宁省年度成交信息存储
	  * @param phmDealInfoVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月22日 下午5:56:08
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月22日 下午5:56:08
	  * @since  1.0.0
	 */
	public void saveSLYearDealInfo(PhmDealInfoVo phmDealInfoVo);

	/**
	  * @Title: getSlSbYearDealInfoByPlanId
	  * @Description: 山西、辽宁省（双边年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return List<MonthDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月25日 下午5:43:47
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月25日 下午5:43:47
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public List<MonthDealInfoDetail> getSlSbYearDealInfoByPlanId(String planId) throws Exception;

	/**
	  * @Title: getSlSbDealListByConsIds
	  * @Description: 根据consIds 获取山西、辽宁省双边交易成交信息详情
	  * @param detailVo
	  * @return Map<String,Object>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月2日 下午5:08:34
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月2日 下午5:08:34
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public Map<String, Object> getSlSbDealListByConsIds(PhmDealInfoVo detailVo) throws Exception;

	/**
	  * @Title: savePhmDealInfoByReportDetailList
	  * @Description: 根据用户申报列表保存用户成交信息，为山西、辽宁省竞价/挂牌交易提供服务
	  * @param list
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月11日 上午9:25:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月11日 上午9:25:31
	  * @since  1.0.0
	 */
	public void savePhmDealInfoByReportList(List<PhmTransactionReportDetail> list);

	/**
	  * @Title: caculateDeliveryPrc
	  * @Description: 山西、辽宁省交易，用户结算电价计算
	  * @param map
	  * @return BigDecimal
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月26日 下午3:07:36
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月26日 下午3:07:36
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public BigDecimal caculateDeliveryPrc(Map<String, Object> map) throws Exception;

	/**
	  * @Title: deletePhmDealInfoByReportIds
	  * @Description: 山西、辽宁省竞价/挂牌交易成交信息回退时，根据删除的申报id删除相关成交信息数据
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月26日 下午5:50:05
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月26日 下午5:50:05
	  * @since  1.0.0
	 */
	public void deletePhmDealInfoByReportIds(String[] ids);

	/**
	 * @Title: deletePhmDealInfoExceptReportIds
	  * @Description: 删除phmDealInfo中不存在的reportId
	  * @param ids
	  * @param planId 
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月27日 下午2:34:55
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月27日 下午2:34:55
	  * @since  1.0.0
	 */
	public void deletePhmDealInfoExceptReportIds(List<String> ids, String planId);

	/**
	  * @Title: saveSLPhmDealInfo
	  * @Description: 山西辽宁省用户、电厂多对多关系保存
	  * @param phmDealInfoVo
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月29日 下午1:23:39
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月29日 下午1:23:39
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void saveSLPhmDealInfo(PhmDealInfoVo phmDealInfoVo) throws Exception;
}
package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmTransactionReportDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.AgrePqDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailVo;

 /**
 * <b>类 名 称：</b>PhmTransactionReportDetailService<br/>
 * <b>类 描 述：</b><br/>交易申报明细service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月9日 下午3:25:54<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface PhmTransactionReportDetailService{

	/**
	 * @Title: getPhmTransactionReportDetailListByPlanId
	 * @Description: 根据月度购电计划id获取对象PhmTransactionReportDetail列表
	 * @param planId
	 * @return List<PhmTransactionReportDetailDetail>
	 * @throws Exception 
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月13日 上午10:55:52
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月13日 上午10:55:52
	 * @since  1.0.0
	 */
	public List<PhmTransactionReportDetailDetail> getPhmTransactionReportDetailListByPlanId(String planId) throws Exception;

	/**
	 * @Title: getAgrePqDetailListByPlanId
	 * @Description: 根据计划id查询竞价交易申报中委托电量明细列表
	 * 		借鉴PhmAgrePqExamineServiceImpl中的getExamineDetailListByPlanId方法
	 * @param detailVo
	 * @return
	 * @throws Exception QueryResult<AgrePqDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月13日 下午3:34:33
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月13日 下午3:34:33
	 * @since  1.0.0
	 */
	public QueryResult<AgrePqDetail> getAgrePqDetailListByPlanId(PhmTransactionReportDetailVo detailVo) throws Exception;

	/**
	  * @Title: updatePhmTransactionReportDetail
	  * @Description: 更新对象PhmTransactionReportDetail
	  * @param detailList
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:16:00
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:16:00
	  * @since  1.0.0
	 */
	public void updatePhmTransactionReportDetailList(List<PhmTransactionReportDetail> detailList);

	/**
	  * @Title: checkAndSubmit
	  * @Description: 提交交易申报信息（就是修改月度购电计划的状态）
	  * @param id
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:51:09
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:51:09
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public void checkAndSubmit(String id) throws Exception;


	/**
	  * @Title: getPhmTransactionReportSLByPlanId
	  * @Description:  根据月度购电计划id获取山西、辽宁PhmTransactionReportDetail列表
	  * @param PhmTransactionReportDetailVo
	  * @return QueryResult<PhmTransactionReportDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月28日 下午3:04:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月28日 下午3:04:56
	 * @throws Exception 
	  * @since  1.0.0
	 */
	public QueryResult<PhmTransactionReportDetailDetail> getPhmTransactionReportSLByPlanId(PhmTransactionReportDetailVo detailVo) throws Exception;

	/**
	  * @Title: getConsTransactionReportByIds
	  * @Description: 根据planId、consId获取山西辽宁用户申报电量详情
	  * @param detailVo
	  * @return List<PhmTransactionReportDetailDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月28日 下午8:59:07
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月28日 下午8:59:07
	  * @since  1.0.0
	 */
	public List<PhmTransactionReportDetailDetail> getConsTransactionReportByIds(PhmTransactionReportDetailVo detailVo);

	/**
	  * @Title: saveConsTransactionReportDetail
	  * @Description: 保存用户申报信息
	  * @param detailVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月29日 上午11:10:23
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月29日 上午11:10:23
	  * @since  1.0.0
	 */
	public void saveConsTransactionReportDetail(PhmTransactionReportDetailVo detailVo);
}
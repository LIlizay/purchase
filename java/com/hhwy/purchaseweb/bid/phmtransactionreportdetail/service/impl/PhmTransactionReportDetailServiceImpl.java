package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmTransactionReportDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.service.PhmDealInfoService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.AgrePqDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailVo;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.service.PhmTransactionReportDetailService;
import com.hhwy.purchaseweb.contants.BusinessContants;

 /**
 * <b>类 名 称：</b>PhmTransactionReportDetailServiceImpl<br/>
 * <b>类 描 述：</b><br/>交易申报明细表service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月9日 下午3:25:38<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class PhmTransactionReportDetailServiceImpl implements PhmTransactionReportDetailService {

	public static final Logger log = LoggerFactory.getLogger(PhmTransactionReportDetailServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	
    /**
     * phmPurchasePlanMonthService		月度购电计划service
     */
    @Autowired
    PhmPurchasePlanMonthService phmPurchasePlanMonthService;
	
	/**
	 * phmDealInfoService		竞价成交信息service
	 */
	@Autowired
	private PhmDealInfoService phmDealInfoService;
    
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

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
	@SuppressWarnings("unchecked")
	public QueryResult<AgrePqDetail> getAgrePqDetailListByPlanId(PhmTransactionReportDetailVo detailVo) throws Exception {
		QueryResult<AgrePqDetail> queryResult = new QueryResult<AgrePqDetail>();
		if(detailVo.getPlanId()!=null && !detailVo.getPlanId().equals("")){
			Object result = dao.getOneBySQL("phmAgrePqExamine.sql.getconsCountByPlanId",detailVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<AgrePqDetail> list = (List<AgrePqDetail>) dao.getBySql("phmTransactionReportDetail.sql.getAgrePqDetailByPlanId", detailVo);
			ConvertListUtil.convert(list);
			queryResult.setTotal(total);
			queryResult.setData(list);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}
	
	/**
	 * @Title: getPhmTransactionReportDetailListByPlanId
	 * @Description: 根据月度购电计划id获取对象PhmTransactionReportDetail列表
	 * @param planId
	 * @return List<PhmTransactionReportDetail>
	 * @throws Exception
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月13日 上午10:55:52
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月13日 上午10:55:52
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<PhmTransactionReportDetailDetail> getPhmTransactionReportDetailListByPlanId(String planId) throws Exception{
		if(planId == null || "".equals(planId)){
			return null;
		}
		Parameter.isFilterData.set(true);
		List<PhmTransactionReportDetailDetail> phmTransactionReportDetailList = (List<PhmTransactionReportDetailDetail>)dao.getBySql("phmTransactionReportDetail.sql.getPhmTransactionReportDetailListByPlanId",planId);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmTransactionReportDetailList);
		return phmTransactionReportDetailList;
	}
	
	/**
	 * 更新对象PhmTransactionReportDetail的集合
	 * @param 实体对象
	 */
	@Transactional
	public void updatePhmTransactionReportDetailList(List<PhmTransactionReportDetail> phmTransactionReportDetailList) {
		if(phmTransactionReportDetailList == null || phmTransactionReportDetailList.size() == 0){
			return;
		}
		String planId = phmTransactionReportDetailList.get(0).getPlanId();
		deletePhmTransactionReportDetailByPlanId(planId);
		//流程回退时，删除phmdealInfo中不存在的reportId
		List<String> ids = new ArrayList<String>();
		for(PhmTransactionReportDetail detail : phmTransactionReportDetailList){
			if(detail.getId() != null && !"".equals(detail.getId())){
				ids.add(detail.getId());
			}
		}
		phmDealInfoService.deletePhmDealInfoExceptReportIds(ids,planId);
		savePhmTransactionReportDetailList(phmTransactionReportDetailList);
	}

	/**
	  * @Title: savePhmTransactionReportDetailList
	  * @Description: 电量申报数据存储
	  * @param phmTransactionReportDetailList
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:27:14
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:27:14
	  * @since  1.0.0
	 */
	private void savePhmTransactionReportDetailList(List<PhmTransactionReportDetail> phmTransactionReportDetailList) {
		dao.saveList(phmTransactionReportDetailList);
	}

	/**
	  * @Title: deletePhmTransactionReportDetailByPlanId
	  * @Description: 根据planId删除电量申报相关数据
	  * @param planId
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:19:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:19:50
	  * @since  1.0.0
	 */
	private void deletePhmTransactionReportDetailByPlanId(String planId) {
		dao.deleteBySql("phmTransactionReportDetail.sql.deletePhmTransactionReportDetailByPlanId", planId);
	}
	
	  /**
	  * @Title: checkAndSubmit
	  * @Description: 提交前校验数据
	  * @param planId
	  * @throws Exception void
	  * <b>创 建 人：</b>wangzelu<br/>
	  * <b>创建时间:</b>2017年9月13日 下午9:18:17
	  * <b>修 改 人：</b>wangzelu<br/>
	  * <b>修改时间:</b>2017年9月13日 下午9:18:17
	  * @since  1.0.0
	  */
	 public void checkAndSubmit(String planId) throws Exception{
	     List<PhmTransactionReportDetailDetail> list = (List<PhmTransactionReportDetailDetail>) getPhmTransactionReportDetailListByPlanId(planId);
	     if(list.size()>0){
	     	phmPurchasePlanMonthService.updatePlanStatusByPlanId(planId,BusinessContants.SELL_MONTHBIDSTATUS03);
	     }else{
	     	throw new RuntimeException("请填写数据再提交！");
	     }
	 }

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
	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<PhmTransactionReportDetailDetail> getPhmTransactionReportSLByPlanId(PhmTransactionReportDetailVo detailVo) throws Exception {
		QueryResult<PhmTransactionReportDetailDetail> queryResult = new QueryResult<PhmTransactionReportDetailDetail>();
		if(detailVo.getPlanId()!=null && !detailVo.getPlanId().equals("")){
			Object result = dao.getOneBySQL("phmTransactionReportDetail.sql.getconsReportCountByPlanId",detailVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<PhmTransactionReportDetailDetail> list = (List<PhmTransactionReportDetailDetail>) dao.getBySql("phmTransactionReportDetail.sql.getconsReportDetailByPlanId", detailVo);
			ConvertListUtil.convert(list);
			queryResult.setTotal(total);
			queryResult.setData(list);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}

	/**
	  * @Title: getConsTransactionReportByIds
	  * @Description: 根据planId、consId获取山西辽宁用户申报电量详情
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月28日 下午8:59:07
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月28日 下午8:59:07
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PhmTransactionReportDetailDetail> getConsTransactionReportByIds(PhmTransactionReportDetailVo detailVo) {
		List<PhmTransactionReportDetailDetail> list = (List<PhmTransactionReportDetailDetail>) dao.getBySql("phmTransactionReportDetail.sql.getConsTransactionReportByIds", detailVo.getPhmTransactionReportDetail());
		return list;
	}

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
	@Override
	@Transactional
	public void saveConsTransactionReportDetail(PhmTransactionReportDetailVo detailVo) {
		List<PhmTransactionReportDetail> insertList = detailVo.getInsertList();
		List<PhmTransactionReportDetail> updateList = detailVo.getUpdateList();
		String[] ids = detailVo.getIds();
		//保存新增信息
		if(insertList != null && insertList.size() > 0){
			savePhmTransactionReportDetailList(insertList);
			if("01".equals(detailVo.getTradePeriod())){
				phmDealInfoService.savePhmDealInfoByReportList(insertList);
			}
		}
		//更新存在信息
		if(updateList != null && updateList.size() > 0){
			savePhmTransactionReportDetailList(updateList);
		}
		//删除信息
		if(ids != null && ids.length > 0){
			dao.delete(ids,PhmTransactionReportDetail.class);
			if("01".equals(detailVo.getTradePeriod())){
				phmDealInfoService.deletePhmDealInfoByReportIds(ids);
			}
		}
	}
}
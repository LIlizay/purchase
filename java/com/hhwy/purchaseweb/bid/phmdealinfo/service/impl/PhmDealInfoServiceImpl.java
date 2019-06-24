package com.hhwy.purchaseweb.bid.phmdealinfo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmDealInfo;
import com.hhwy.purchase.domain.PhmPlanYearSubitem;
import com.hhwy.purchase.domain.PhmTransactionReportDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.MonthDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoVo;
import com.hhwy.purchaseweb.bid.phmdealinfo.service.PhmDealInfoService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;

/**
 * PhmDealInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmDealInfoServiceImpl implements PhmDealInfoService {

	public static final Logger log = LoggerFactory.getLogger(PhmDealInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	@Autowired
	PhmPurchasePlanMonthService phmPurchasePlanMonthService;
	
	@Autowired
	SmSettlementMonthService smSettlementMonthService;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	  * @Title: getPhmDealInfoByPlanId
	  * @Description: 根据计划id获取其他省竞价、挂牌交易成交信息录入
	  * @param planId
	  * @return List<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午4:43:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午4:43:31
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PhmDealInfoDetail> getPhmDealInfoByPlanId(String planId) throws Exception {
		List<PhmDealInfoDetail> phmDealInfoDetailList = (List<PhmDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getPhmDealInfoByPlanId", planId);
		ConvertListUtil.convert(phmDealInfoDetailList);
		return phmDealInfoDetailList;
	}
	
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
	@Override
	@SuppressWarnings("unchecked")
	public List<MonthDealInfoDetail> getYearDealInfoByPlanId(String planId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", planId.substring(0,planId.length()-2));
		map.put("attorn", planId.substring(planId.length()-2));
		List<MonthDealInfoDetail> yearDealInfoList = (List<MonthDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getYearDealInfoByPlanId", map);
		ConvertListUtil.convert(yearDealInfoList);
		return yearDealInfoList;
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<MonthDealInfoDetail> getMonthDealInfoByYm(String ym) throws Exception {
		List<MonthDealInfoDetail> monthDealInfoList = (List<MonthDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getMonthDealInfoByYm", ym);
		ConvertListUtil.convert(monthDealInfoList);
		return monthDealInfoList;
	}
	
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
	@Override
	@Transactional
	public void deletePhmDealInfoByPlanId(String planId) {
		dao.deleteBySql("phmDealInfo.sql.deletePhmDealInfoByPlanId", planId);
	}

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
	@Override
	@Transactional
	public void savePhmDealInfo(PhmDealInfoVo phmDealInfoVo) throws Exception {
		List<PhmDealInfo> insertList = phmDealInfoVo.getInsertList();
		List<PhmDealInfo> updateList = phmDealInfoVo.getUpdateList();
		String[] ids = phmDealInfoVo.getIds();
		//标记为true 表示为山西、辽宁双边协商交易，需要计算各交易的结算电价
		if(phmDealInfoVo.isFlag() && insertList != null && insertList.size() > 0){ 
			caculateDeliveryPrcByPhmDealInfo(insertList,"02");
		}
		//保存新增信息
		if(insertList != null && insertList.size() > 0){
			this.saveInsertPhmDealInfoList(insertList);
		}
		//更新存在信息
		if(updateList != null && updateList.size() > 0){
			for(PhmDealInfo dealInfo : updateList){
				dealInfo.setSubitemFlag("0");
			}
			this.savePhmDealInfoList(updateList);
		}
		//删除信息
		if(ids != null && ids.length > 0){
			this.deletePhmDealInfoByIds(ids);
		}
	}

	/**
	  * @Title: deletePhmDealInfoByIds
	  * @Description: 根据ids删除成交的相关信息
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月29日 下午3:29:04
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月29日 下午3:29:04
	  * @since  1.0.0
	 */
	private void deletePhmDealInfoByIds(String[] ids) {
		dao.delete(ids, PhmDealInfo.class);
		dao.delete(ids,PhmPlanYearSubitem.class);
		dao.deleteBySql("phmDealInfo.sql.deleteDealInfoByParentId", ids);
	}

	/**
	  * @Title: saveInsertPhmDealInfoList
	  * @Description: 保存新增数据列
	  * @param insertList
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月29日 下午2:45:00
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月29日 下午2:45:00
	  * @since  1.0.0
	 */
	private void saveInsertPhmDealInfoList(List<PhmDealInfo> insertList) {
		List<PhmPlanYearSubitem> subList = new ArrayList<PhmPlanYearSubitem>();
		for(PhmDealInfo dealInfo : insertList){
			dealInfo.setSubitemFlag("0");
		}
		savePhmDealInfoList(insertList);
		for(PhmDealInfo dealInfo : insertList){
			PhmPlanYearSubitem submit = new PhmPlanYearSubitem();
			submit.setId(dealInfo.getId());
			submit.setPlanId(dealInfo.getPlanId());
			subList.add(submit);
		}
		dao.saveList(subList);
	}

	/**
	  * @Title: 
	  * @Description: 根据List<PhmDealInfo> 计算各个交易的结算电价
	  * @param phmDealInfoList
	  * @param tradeMode 
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月5日 下午4:13:15
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月5日 下午4:13:15
	 * @throws Exception 
	  * @since  1.0.0
	 */
	private void caculateDeliveryPrcByPhmDealInfo(List<PhmDealInfo> phmDealInfoList, String tradeMode) throws Exception {
		smSettlementMonthService.calculateDeliveryPrc(phmDealInfoList, tradeMode);
	}

	/**
	  * @Title: savePhmDealInfoList
	  * @Description: 添加List<PhmDealInfo>
	  * @param phmDealInfoList
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 上午10:42:11
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 上午10:42:11
	  * @since  1.0.0
	 */
	private void savePhmDealInfoList(List<PhmDealInfo> phmDealInfoList) {
		dao.saveList(phmDealInfoList);
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<PhmDealInfoDetail> getDealListByReportId(PhmDealInfoVo detailVo) throws Exception {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(detailVo == null){
			detailVo = new PhmDealInfoVo();
		}
		List<PhmDealInfoDetail> detailList = (List<PhmDealInfoDetail>)dao.getBySql("phmDealInfo.sql.getPhmDealInfoListByParams",detailVo);
		ConvertListUtil.convert(detailList);
		return detailList;
	}

	/**
	  * @Title: saveMonthList
	  * @Description:分月计划成交信息保存(年度交易)
	  * @param detailVo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 下午5:42:41
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 下午5:42:41
	  * @since  1.0.0
	 */
	@Override
	@Transactional
	public void saveMonthList(PhmDealInfoVo detailVo) {
		
		//ph_m_deal_info表信息存储，先删除后保存,根据planId删除subitem为1的数据
		String planId =  detailVo.getId();
		dao.deleteBySql("phmDealInfo.sql.deleteSubPhmDealInfoByPlanId",planId);
		List<PhmDealInfo> dealList = new ArrayList<PhmDealInfo>();
		for(MonthDealInfoDetail detail : detailVo.getMonthList()){
			for(int i = 1 ; i < 13 ; i++){
				PhmDealInfo dealInfo = new PhmDealInfo();
				dealInfo.setPlanId(planId);
				dealInfo.setReportDetailId(detail.getReportId());
				dealInfo.setDealPrc(detail.getDealPrc());
				dealInfo.setDeliveryPrc(detail.getDeliveryPrc());
				dealInfo.setProducerId(detail.getProducerId());
				dealInfo.setGeneratorId(detail.getGeneratorId());
				dealInfo.setAttornType(detail.getAttorn());
				dealInfo.setTraderName(detail.getTraderName());
				dealInfo.setSubitemFlag("1");
				dealInfo.setParentId(detail.getId());
				if(i<10){
					dealInfo.setYm(detailVo.getYm()+"0"+i);
				}else{
					dealInfo.setYm(detailVo.getYm()+i);
				}
				switch(i){
					case 1 : dealInfo.setDealPq(detail.getMon1());		break;
					case 2 : dealInfo.setDealPq(detail.getMon2());		break;
					case 3 : dealInfo.setDealPq(detail.getMon3());		break;
					case 4 : dealInfo.setDealPq(detail.getMon4());		break;
					case 5 : dealInfo.setDealPq(detail.getMon5());		break;
					case 6 : dealInfo.setDealPq(detail.getMon6());		break;
					case 7 : dealInfo.setDealPq(detail.getMon7());		break;
					case 8 : dealInfo.setDealPq(detail.getMon8());		break;
					case 9 : dealInfo.setDealPq(detail.getMon9());		break;
					case 10 : dealInfo.setDealPq(detail.getMon10());	break;
					case 11 : dealInfo.setDealPq(detail.getMon11());	break;
					case 12 : dealInfo.setDealPq(detail.getMon12());	break;
				}
				dealList.add(dealInfo);
			}
		}
		dao.saveList(dealList);
		
		//ph_m_plan_year_subitem表信息存储
		List<PhmPlanYearSubitem> subList = new ArrayList<PhmPlanYearSubitem>();
		for(MonthDealInfoDetail detail : detailVo.getMonthList()){
			PhmPlanYearSubitem subitem = new PhmPlanYearSubitem();
			if(detail.getDealId()!= null && !"".equals(detail.getDealId())){
				subitem.setId(detail.getDealId());
			}else{
				subitem.setId(detail.getId());
			}
			subitem.setPlanId(detail.getPlanId());
			subitem.setDealId(detail.getDealId());
			subitem.setYear(detailVo.getYm());
			subitem.setJan(detail.getMon1());
			subitem.setFeb(detail.getMon2());
			subitem.setMar(detail.getMon3());
			subitem.setApr(detail.getMon4());
			subitem.setMay(detail.getMon5());
			subitem.setJun(detail.getMon6());
			subitem.setJul(detail.getMon7());
			subitem.setAug(detail.getMon8());
			subitem.setSept(detail.getMon9());
			subitem.setOct(detail.getMon10());
			subitem.setNov(detail.getMon11());
			subitem.setDece(detail.getMon12());
			subList.add(subitem);
		}
		dao.saveList(subList);
		
	}

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
	@Override
	public void saveSubMonthList(PhmDealInfoVo detailVo) {
		//ph_m_deal_info表信息存储，根据planId、ym、reportId更新subitemFlag 为1 的dealPq值
		String ym = detailVo.getYm().substring(0,4);
		List<Map<String,Object>> paramsList = new ArrayList<Map<String,Object>>();
		for(MonthDealInfoDetail detail : detailVo.getMonthList()){
			for(int i = 1 ; i < 13 ; i++){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("parentId", detail.getId());
				if(i<10){
					map.put("ym", ym+"0"+i);
				}else{
					map.put("ym", ym+i);
				}
				switch(i){
					case 1 : map.put("dealPq", detail.getMon1());		break;
					case 2 : map.put("dealPq", detail.getMon2());		break;
					case 3 : map.put("dealPq", detail.getMon3());		break;
					case 4 : map.put("dealPq", detail.getMon4());		break;
					case 5 : map.put("dealPq", detail.getMon5());		break;
					case 6 : map.put("dealPq", detail.getMon6());		break;
					case 7 : map.put("dealPq", detail.getMon7());		break;
					case 8 : map.put("dealPq", detail.getMon8());		break;
					case 9 : map.put("dealPq", detail.getMon9());		break;
					case 10 : map.put("dealPq", detail.getMon10());		break;
					case 11 : map.put("dealPq", detail.getMon11());		break;
					case 12 : map.put("dealPq", detail.getMon12());		break;
				}
				paramsList.add(map);
			}
		}
		dao.updateBySql("phmDealInfo.sql.updateSubPhmDealInfo", paramsList);
	}

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
	@Override
	public void submit(String planId) throws Exception {
		Object result = dao.getOneBySQL("phmDealInfo.sql.checkBeforeSubmit",planId);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		if(total!=0){
			throw new RuntimeException("还有【"+total+"】个分段没有录入，请先录入数据再提交！");
		}
		phmPurchasePlanMonthService.updatePlanStatusByPlanId(planId,BusinessContants.SELL_MONTHBIDSTATUS04);
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<PhmDealInfoDetail> getSbDealInfoByPlanId(String planId) throws Exception {
		List<PhmDealInfoDetail> phmDealInfoDetailList = (List<PhmDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getSbDealInfoByPlanId", planId);
		ConvertListUtil.convert(phmDealInfoDetailList);
		return phmDealInfoDetailList;
	}

	/**
	  * @Title: getSLDealInfoByPlanId
	  * @Description: 根据planId获取山西、辽宁省竞价、挂牌交易成交信息录入
	  * @param planId
	  * @return List<PhmDealInfoDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午6:33:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午6:33:31
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<PhmDealInfoDetail> getSLDealInfoByPlanId(PhmDealInfoVo detailVo) throws Exception{
		QueryResult<PhmDealInfoDetail> queryResult = new QueryResult<PhmDealInfoDetail>();
		if(detailVo.getId()!=null && !"".equals(detailVo.getId())){
			Object result = dao.getOneBySQL("phmDealInfo.sql.getSLDealInfoCountByPlanId",detailVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<PhmDealInfoDetail> phmDealInfoDetailList = (List<PhmDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getSLDealInfoByPlanId", detailVo);
			ConvertListUtil.convert(phmDealInfoDetailList);
			queryResult.setTotal(total);
			queryResult.setData(phmDealInfoDetailList);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}

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
	@Override
	@SuppressWarnings("unused")
	public void saveSLDealInfo(PhmDealInfoVo phmDealInfoVo) {
		List<PhmDealInfo> dealList = saveSLData(phmDealInfoVo);
		return;
	}

	/**
	  * @Title: saveSLData
	  * @Description: 保存山西、辽宁数据
	  * @param phmDealInfoVo
	  * @return List<PhmDealInfo>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月22日 下午6:19:29
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月22日 下午6:19:29
	  * @since  1.0.0
	 */
	@Transactional
	private List<PhmDealInfo> saveSLData(PhmDealInfoVo phmDealInfoVo) {
		List<PhmDealInfo> dealList = new ArrayList<PhmDealInfo>();
		List<PhmPlanYearSubitem> subList = new ArrayList<PhmPlanYearSubitem>();
		for(PhmDealInfoDetail detail : phmDealInfoVo.getSlList()){
			PhmDealInfo info = new PhmDealInfo();
			if(detail.getId() != null){
				info.setId(detail.getId());
			}
			info.setYm(phmDealInfoVo.getYm());
			info.setPlanId(phmDealInfoVo.getId());
			info.setReportDetailId(detail.getReportId());
			info.setProducerId(detail.getProducerId());
			info.setDealPq(detail.getDealPq());
			info.setDealPrc(detail.getDealPrc());
			info.setDeliveryPrc(detail.getDeliveryPrc());
			info.setGeneratorId(detail.getGeneratorId());
			info.setTraderName(detail.getTraderName());
			info.setAttornType(detail.getAttornType());
			info.setSubitemFlag("0");
			info.setConsId(detail.getConsId());
			dealList.add(info);
		}
		dao.saveList(dealList);
		for(PhmDealInfo dealInfo : dealList){
			PhmPlanYearSubitem submit = new PhmPlanYearSubitem();
			submit.setId(dealInfo.getId());
			submit.setPlanId(dealInfo.getPlanId());
			subList.add(submit);
		}
		dao.saveList(subList);
		return dealList;
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<PhmDealInfoDetail> getSLSBDealInfoByPlanId(PhmDealInfoVo detailVo) throws Exception {
		QueryResult<PhmDealInfoDetail> queryResult = new QueryResult<PhmDealInfoDetail>();
		if(detailVo.getId()!=null && !"".equals(detailVo.getId())){
			Object result = dao.getOneBySQL("phmDealInfo.sql.getSLSbDealInfoCountByPlanId",detailVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<PhmDealInfoDetail> phmDealInfoDetailList = (List<PhmDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getSLSbDealInfoByPlanId", detailVo);
			ConvertListUtil.convert(phmDealInfoDetailList);
			queryResult.setTotal(total);
			queryResult.setData(phmDealInfoDetailList);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<MonthDealInfoDetail> getSbYearDealInfoByPlanId(String planId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", planId.substring(0,planId.length()-2));
		map.put("attorn", planId.substring(planId.length()-2));
		List<MonthDealInfoDetail> yearDealInfoList = (List<MonthDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getSbYearDealInfoByPlanId", map);
		ConvertListUtil.convert(yearDealInfoList);
		return yearDealInfoList;
	}

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
	@Override
	@Transactional
	public void saveSbYearList(PhmDealInfoVo phmDealInfoVo) {
		List<PhmDealInfo> insertList = phmDealInfoVo.getInsertList();
		String[] ids = phmDealInfoVo.getIds();
		//保存成交信息
		if(insertList != null && insertList.size() > 0){
			List<PhmPlanYearSubitem> subList = new ArrayList<PhmPlanYearSubitem>();
			for(PhmDealInfo dealInfo : insertList){
				dealInfo.setSubitemFlag("0");
			}
			savePhmDealInfoList(insertList);
			for(PhmDealInfo dealInfo : insertList){
				PhmPlanYearSubitem submit = new PhmPlanYearSubitem();
				submit.setId(dealInfo.getId());
				submit.setPlanId(dealInfo.getPlanId());
				subList.add(submit);
			}
			dao.saveList(subList);
		}
		//删除信息
		if(ids != null && ids.length > 0){
			dao.delete(ids, PhmDealInfo.class);
			dao.delete(ids,PhmPlanYearSubitem.class);
			dao.deleteBySql("phmDealInfo.sql.deleteDealInfoByParentId", ids);
		}
		for(int i = 0 ; i < insertList.size(); i++){
			String id = insertList.get(i).getId();
			phmDealInfoVo.getMonthList().get(i).setId(id);
		}
		//保存年交易月份拆分电量
		for(int i = 0 ; i < insertList.size() ; i++){
			phmDealInfoVo.getMonthList().get(i).setDealPrc(insertList.get(i).getDealPrc());
		}
		saveMonthList(phmDealInfoVo);
	}

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
	@Override
	@Transactional
	public void saveSLYearDealInfo(PhmDealInfoVo phmDealInfoVo) {
		List<PhmDealInfo> dealList = saveSLData(phmDealInfoVo);
		if(!phmDealInfoVo.isFlag()){
			for(int i = 0 ; i < dealList.size(); i++){
				String id = dealList.get(i).getId();
				phmDealInfoVo.getMonthList().get(i).setId(id);
			}
		}
		//保存年交易月份拆分电量
		saveMonthList(phmDealInfoVo);
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public List<MonthDealInfoDetail> getSlSbYearDealInfoByPlanId(String planId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", planId.substring(0,planId.length()-2));
		map.put("attorn", planId.substring(planId.length()-2));
		List<MonthDealInfoDetail> yearDealInfoList = (List<MonthDealInfoDetail>) dao.getBySql("phmDealInfo.sql.getSlSbYearDealInfoByPlanId", map);
		ConvertListUtil.convert(yearDealInfoList);
		return yearDealInfoList;
	}

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
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSlSbDealListByConsIds(PhmDealInfoVo detailVo) throws Exception {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(detailVo == null){
			detailVo = new PhmDealInfoVo();
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PhmDealInfoDetail> detailList = (List<PhmDealInfoDetail>)dao.getBySql("phmDealInfo.sql.getPhmDealInfoListByParams",detailVo);
		ConvertListUtil.convert(detailList);
		Map<String,List<PhmDealInfoDetail>> map = new HashMap<String,List<PhmDealInfoDetail>>();
		List<PhmDealInfoDetail> slSbDetailList = new ArrayList<PhmDealInfoDetail>();
		List<String> ids = new ArrayList<String>();
		if(detailList != null && detailList.size() > 0){
			for(PhmDealInfoDetail dealInfo : detailList){
				String key = null;
				if(detailVo.isFlag()){
					key = dealInfo.getDealPrc()+dealInfo.getTraderName()+dealInfo.getAttornType();
				}else{
					key = dealInfo.getDealPrc()+dealInfo.getProducerId()+(dealInfo.getGeneratorId()==null?"":dealInfo.getGeneratorId());
				}
				if(map.containsKey(key)){
					map.get(key).add(dealInfo);
				}else{
					List<PhmDealInfoDetail> list = new ArrayList<PhmDealInfoDetail>();
					list.add(dealInfo);
					map.put(key, list);
				}
			}
			for(List<PhmDealInfoDetail> list : map.values()){ //如果用户数与list的大小相等，说明有共同交易对象，返回前台
				if(list.size() == detailVo.getConsIds().length){
					slSbDetailList.add(list.get(0));
					for(int i = 0 ; i < list.size() ; i++){
						ids.add(list.get(i).getId());
					}
				}
			}
		}
		resultMap.put("list",slSbDetailList);
		resultMap.put("ids", ids);
		return resultMap;
	}

	/**
	  * @Title: savePhmDealInfoByReportDetailList
	  * @Description: 根据用户申报列表保存用户成交信息，为山西、辽宁省竞价/挂牌交易提供服务
	  * 				（山西、辽宁省竞价/挂牌交易成交信息回退时，需要删除相关成交信息数据）
	  * @param list
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月11日 上午9:25:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月11日 上午9:25:31
	  * @since  1.0.0
	 */
	@Override
	public void savePhmDealInfoByReportList(List<PhmTransactionReportDetail> list) {
		List<PhmDealInfo> slList = new ArrayList<PhmDealInfo>();
		List<PhmPlanYearSubitem> subList = new ArrayList<PhmPlanYearSubitem>();
		for(PhmTransactionReportDetail report : list){
			PhmDealInfo deal = new PhmDealInfo();
			deal.setConsId(report.getConsId());
			deal.setReportDetailId(report.getId());
			deal.setPlanId(report.getPlanId());
			deal.setSubitemFlag("0");
			slList.add(deal);
		}
		dao.saveList(slList);
		for(PhmDealInfo deal : slList){
			PhmPlanYearSubitem sub = new PhmPlanYearSubitem();
			sub.setId(deal.getId());
			sub.setDealId(deal.getId());
			sub.setPlanId(deal.getPlanId());
			subList.add(sub);
		}
		dao.saveList(subList);
	}

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
	@Override
	public BigDecimal caculateDeliveryPrc(Map<String,Object> map) throws Exception {
		List<PhmDealInfo> list = new ArrayList<PhmDealInfo>();
		PhmDealInfo deal = new PhmDealInfo();
		deal.setConsId(map.get("consId").toString());
		deal.setYm(map.get("ym").toString());
		BigDecimal dealPrc =  BigDecimal.valueOf(Float.valueOf(map.get("dealPrc").toString()));
		deal.setDealPrc(dealPrc);
		list.add(deal);
		caculateDeliveryPrcByPhmDealInfo(list,map.get("tradeMode").toString());
		return list.get(0).getDeliveryPrc();
	}

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
	@Override
	public void deletePhmDealInfoByReportIds(String[] ids) {
		dao.deleteBySql("phmDealInfo.sql.deletePhmDealInfoByReportIds", ids);
	}

	/**
	 * @Title: deletePhmDealInfoExceptReportIds
	  * @Description: 删除phmDealInfo中不存在的reportId
	  * @param ids
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月27日 下午2:34:55
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月27日 下午2:34:55
	  * @since  1.0.0
	 */
	@Override
	public void deletePhmDealInfoExceptReportIds(List<String> ids, String planId) {
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("planId", planId);
		dao.deleteBySql("phmDealInfo.sql.deletePhmDealInfoExceptReportIds", map);
	}

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
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void saveSLPhmDealInfo(PhmDealInfoVo phmDealInfoVo) throws Exception {
		List<PhmDealInfo> insertList = new ArrayList<PhmDealInfo>();	//新增数据
		List<PhmDealInfo> updateList = new ArrayList<PhmDealInfo>();	//更新数据
		Map<String,String> idMap = new HashMap<String,String>(); 		//筛选需删除的id用
		String[] ids = phmDealInfoVo.getIds();
		if(ids != null && ids.length > 0 && phmDealInfoVo.getInsertList() != null && phmDealInfoVo.getInsertList().size() > 0){
			caculateDeliveryPrcByPhmDealInfo(phmDealInfoVo.getInsertList(),"02");
			// 根据历史数据的id，获取交易对象信息
			List<Map<String,String>> tradeList = (List<Map<String,String>>) dao.getBySql("phmDealInfo.sql.getTradeListByIds", ids);
			Map<String,List<String>> tradeMap = new HashMap<String,List<String>>(); 	//key:交易对象id或交易对象名称		value：数据id列表
			for(Map<String,String> map : tradeList){
				idMap.put(map.get("id"), map.get("id"));
				if(tradeMap.containsKey(map.get("trade"))){
					tradeMap.get(map.get("trade")).add(map.get("id"));
				}else{
					List<String> idList = new ArrayList<String>();
					idList.add(map.get("id"));
					tradeMap.put(map.get("trade"), idList);
				}
			}
			Map<String,String> distinctMap = new HashMap<String,String>();		//前台数据是consId与交易对象匹配好的数据，定义次变量，为了更新的数据防重 ,key:交易对象
			//进行交易对象匹配,tradMap中存在交易对象，视为修改，否则视为新增，过滤好更新数据的id
			for(PhmDealInfo deal : phmDealInfoVo.getInsertList()){
				String trade = deal.getProducerId() == null ? deal.getTraderName() : deal.getProducerId();
				if(tradeMap.containsKey(trade)){
					if(!distinctMap.containsKey(trade)){
						distinctMap.put(trade, trade);
						for(String id : tradeMap.get(trade)){
							PhmDealInfo updateDeal = new PhmDealInfo();
							updateDeal.setId(id);
							updateDeal.setAttornType(deal.getAttornType());
							updateDeal.setDealPrc(deal.getDealPrc());
							updateDeal.setGeneratorId(deal.getGeneratorId());
							updateDeal.setProducerId(deal.getProducerId());
							updateDeal.setTraderName(deal.getTraderName());
							updateList.add(updateDeal);
							idMap.remove(id);
						}
					}
				}else{
					insertList.add(deal);
				}
			}
			//处理要删除的数据id
			if(idMap != null && idMap.size() > 0){
				String[] deleteIds = new String[idMap.size()];
				int i = 0;
				for(String id : idMap.values()){
					deleteIds[i] = id;
					i++;
				}
				this.deletePhmDealInfoByIds(deleteIds);
			}
			//数据保存处理
			if(insertList != null && insertList.size() > 0){
				this.saveInsertPhmDealInfoList(insertList);
			}
			if(updateList != null && updateList.size() > 0){
				dao.updateBySql("phmDealInfo.sql.updatePhmDealInfoByIds", updateList);
			}
		}else{
			savePhmDealInfo(phmDealInfoVo);
		}
	}

}
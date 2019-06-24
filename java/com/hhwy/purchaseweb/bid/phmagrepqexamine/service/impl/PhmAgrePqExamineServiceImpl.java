package com.hhwy.purchaseweb.bid.phmagrepqexamine.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchase.domain.PhmAgrePqExamine;
import com.hhwy.purchase.domain.SwMessage;
import com.hhwy.purchase.domain.SwSms;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineDetail;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineVo;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.YearPlanProxyPqDetail;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.service.PhmAgrePqExamineService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanConsRelaDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmSubMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.sms.SmsUtil;
import com.hhwy.purchaseweb.sms.service.SwSmsService;

/**
 * PhmAgrePqExamineService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmAgrePqExamineServiceImpl implements PhmAgrePqExamineService {
	

	public static final Logger log = LoggerFactory.getLogger(PhmAgrePqExamineServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private PhmPurchasePlanMonthService phmPurchasePlanMonthService;
	
	/**
	 * bigDataService		大数据service
	 */
	@Autowired
	private BigDataService bigDataService;
	
	/**
	 * 短信记录接口swSmsService
	 */
	@Autowired
	SwSmsService swSmsService;

	/**
	  * @Title: getExamineDetailListByPlanId
	  * @Description: 根据planId获取月交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return QueryResult<PhmAgrePqExamineDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月23日 下午9:12:03
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月23日 下午9:12:03
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<PhmAgrePqExamineDetail> getExamineDetailListByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception {
		QueryResult<PhmAgrePqExamineDetail> queryResult = new QueryResult<PhmAgrePqExamineDetail>();
		if(phmAgrePqExamineVo.getPlanId()!=null && !phmAgrePqExamineVo.getPlanId().equals("") && phmAgrePqExamineVo.getYm()!=null && !phmAgrePqExamineVo.getYm().equals("")){
			Object result = dao.getOneBySQL("phmAgrePqExamine.sql.getconsCountByPlanId",phmAgrePqExamineVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<PhmAgrePqExamineDetail> list = (List<PhmAgrePqExamineDetail>) dao.getBySql("phmAgrePqExamine.sql.getExamineDetailByPlanId", phmAgrePqExamineVo);
			queryResult.setTotal(total);
			queryResult.setData(list);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}
	
	/**
	 * @Title: getYearPlanProxyPqDetailByParams
	 * @Description: 根据year和consId获取该用户的所有年交易电量审核分月电量信息，用于提供月购电计划的委托电量审核第二个表格“年度交易委托电量分月信息”的数据
	 * @param year
	 * @param consId
	 * @return
	 * @throws Exception 
	 * List<YearPlanProxyPqDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年3月1日 上午11:34:43
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年3月1日 上午11:34:43
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<YearPlanProxyPqDetail> getYearPlanProxyPqDetailByParams(String year, String consId) throws Exception {
		if(year == null || year.length() != 4 || consId == null || "".equals(consId)) {
			throw new BusinessException("参数异常，查询失败！");
		}
		Map<String, String> params = new HashMap<>();
		params.put("year", year);
		params.put("consId", consId);
		List<YearPlanProxyPqDetail> list = (List<YearPlanProxyPqDetail>) dao.getBySql("phmAgrePqExamine.sql.getYearPlanProxyPqDetailByParams", params);
		ConvertListUtil.convert(list);
		return list;
	}
	

	/**
	  * @Title: saveAgrePqs
	  * @Description: 保存页面填写的委托电量与计划
	  * @param phmAgrePqExamineVo
	  * @return String
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午1:53:45
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午1:53:45
	  * @since  1.0.0
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String saveAgrePqs(PhmAgrePqExamineVo phmAgrePqExamineVo) {
		String planId = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList().get(0).getPlanId();
		String year = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList().get(0).getYm();
		String orgNo = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList().get(0).getOrgNo();
		List<PhmAgrePqExamineDetail> list = new ArrayList<PhmAgrePqExamineDetail>();	//要更新的detailList
		Map<String,PhmAgrePqExamineDetail> agrePqExamineDetailMap = new HashMap<String,PhmAgrePqExamineDetail>();
		//先去数据库根据planId、consId删除已存在的数据
		List<Map<String,Object>> paramsList = new ArrayList<Map<String,Object>>();
		for(PhmAgrePqExamineDetail detail : phmAgrePqExamineVo.getPhmAgrePqExamineDetailList()){
			if(detail.getNewReportPq() == null){
				detail.setNewReportPq(detail.getAgrePq());
			}
			agrePqExamineDetailMap.put(detail.getConsId(), detail);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("planId", planId);
			map.put("consId", detail.getConsId());
			paramsList.add(map);
		}
		list.addAll(phmAgrePqExamineVo.getPhmAgrePqExamineDetailList());
		dao.deleteBySql("phmAgrePqExamine.sql.deleteByPlanIdAndConsId", paramsList);
		//当存储数据为年度交易拆分数据时
		if(phmAgrePqExamineVo.getFlag()){
			for(PhmSubMonthDetail subDetail : phmAgrePqExamineVo.getSubMonthDetail()){
				for(int i = 1 ; i < 13; i++){
					String ym = null;
					PhmAgrePqExamineDetail phmAgrePqExamineDetail = new PhmAgrePqExamineDetail();
					if(i<10){
						ym = year + "0" + i;
					}else{
						ym = year + i;
					}
					phmAgrePqExamineDetail.setId(PlatformTools.getID());
					phmAgrePqExamineDetail.setYm(ym);
					phmAgrePqExamineDetail.setSubitemFlag("1");
					phmAgrePqExamineDetail.setPlanId(planId);
					phmAgrePqExamineDetail.setConsId(subDetail.getConsId());
					phmAgrePqExamineDetail.setOrgNo(orgNo);
					switch(i){
						case 1 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getJan());		break;
						case 2 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getFeb());		break;
						case 3 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getMar());		break;
						case 4 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getApr());		break;
						case 5 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getMay());		break;
						case 6 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getJun());		break;
						case 7 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getJul());		break;
						case 8 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getAug());		break;
						case 9 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getSept());	break;
						case 10 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getOct());	break;
						case 11 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getNov());	break;
						case 12 : phmAgrePqExamineDetail.setNewReportPq(subDetail.getDece());	break;
					}
					list.add(phmAgrePqExamineDetail);
				}
			}
		}
		
		dao.updateBySql("phmAgrePqExamine.sql.saveAgrePqs", list);
		
		//更新前台修改的“年度交易委托电量分月信息”表中信息 --by wangzelu
		this.updateYearPlanProxyPqDetail(phmAgrePqExamineVo.getYearPlanProxyPqDetailList());
		
		//更新大数据表中数据
		bigDataService.saveOrUpdatePlanPqListByExa(phmAgrePqExamineVo.getPhmAgrePqExamineDetailList());
		
		//更新预测电量 by张钊 2018-4-23
		if(phmAgrePqExamineVo.getForecastData() != null &&  phmAgrePqExamineVo.getForecastData().size() > 0){
			dao.updateBySql("phmAgrePqExamine.sql.updateForecastData", phmAgrePqExamineVo);
		}
		return "保存成功";
	}
	
	/**
	 * @Title: saveYearPlanProxyPqDetail
	 * @Description: 更新前台修改的“年度交易委托电量分月信息”表中信息
	 * @param yearPlanProxyPqDetailList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年3月1日 下午3:40:45
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年3月1日 下午3:40:45
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private void updateYearPlanProxyPqDetail(List<YearPlanProxyPqDetail> yearPlanProxyPqDetailList) {
		if(yearPlanProxyPqDetailList == null || yearPlanProxyPqDetailList.size() == 0) {
			return;
		}
		String year = yearPlanProxyPqDetailList.get(0).getYear();
		Map<String, YearPlanProxyPqDetail> consIdPlanIdToDetailMap = new HashMap<>();
		
		Set<String> consIdSet = new HashSet<>();
		for (YearPlanProxyPqDetail detail : yearPlanProxyPqDetailList) {
			consIdSet.add(detail.getConsId());
			String key = detail.getConsId() + detail.getPlanId();
			consIdPlanIdToDetailMap.put(key, detail);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("year", year);
		params.put("consIds", consIdSet);
		List<PhmAgrePqExamine> entityList = (List<PhmAgrePqExamine>)dao.getBySql("phmAgrePqExamine.sql.getYearPlanProxyByYearAndConsIds", params);
		List<PhmAgrePqExamine> updateList = new ArrayList<>();
		for (PhmAgrePqExamine entity : entityList) {
			String key= entity.getConsId() + entity.getPlanId();
			YearPlanProxyPqDetail detail = consIdPlanIdToDetailMap.get(key);
			if(detail != null && checkChanged(detail,entity)) {
				updateList.add(entity);
			}
		}
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		for (PhmAgrePqExamine entity : updateList) {
			entity.setUpdateUser(loginUserId);
			dao.updateBySql("phmAgrePqExamine.sql.updateProxyPqById", entity);
		}
	}
	
	/**
	 * @Title: checkChanged
	 * @Description: 检查此实体对象（委托电量审核对象）中的委托电量是否变更
	 * @param detail
	 * @param entity
	 * @return 
	 * boolean
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年3月1日 下午3:38:04
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年3月1日 下午3:38:04
	 * @since  1.0.0
	 */
	private boolean checkChanged(YearPlanProxyPqDetail detail, PhmAgrePqExamine entity) {
		String ym = entity.getYm();
		int month = 13;	//默认为全年总电量
		if(ym.length() == 6) {
			month = Integer.valueOf(entity.getYm().substring(4));
		}
		BigDecimal detailPq = null;
		switch (month) {
		case 1:
			detailPq = detail.getJan();
			break;
		case 2:
			detailPq = detail.getFeb();
			break;
		case 3:
			detailPq = detail.getMar();
			break;
		case 4:
			detailPq = detail.getApr();
			break;
		case 5:
			detailPq = detail.getMay();
			break;
		case 6:
			detailPq = detail.getJun();
			break;
		case 7:
			detailPq = detail.getJul();
			break;
		case 8:
			detailPq = detail.getAug();
			break;
		case 9:
			detailPq = detail.getSept();
			break;
		case 10:
			detailPq = detail.getOct();
			break;
		case 11:
			detailPq = detail.getNov();
			break;
		case 12:
			detailPq = detail.getDece();
			break;
		default:
			detailPq = detail.getTotal();
			break;
		}
		if(detailPq != entity.getAgrePq()) {
			if(detailPq != null && entity.getAgrePq() != null ) {
				if(!detailPq.equals(entity.getAgrePq())) {
					entity.setAgrePq(detailPq);
					return true;
				}else {
					return false;
				}
			}else {
				entity.setAgrePq(detailPq);
				return true;
			}
		}else {	//detailPq和entity.getAgrePq()都为null，或者指向同一对象
			return false;
		}
	}
	
	/**
	 * 
	 * @Title: getExamineDetailIsNullByPlanId<br/>
	 * @Description: 查询计划下是否有未填写合同电量的用户<br/>
	 * @param planId
	 * @return <br/> String<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月19日 下午7:32:08
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月19日 下午7:32:08
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	public String getExamineDetailIsNullByPlanId(String planId) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		String tradeMode = planId.substring(planId.length()-2);
		planId = planId.substring(0,planId.length()-2);
		map.put("planId", planId);
		List<PhmAgrePqExamineDetail> list = (List<PhmAgrePqExamineDetail>) dao.getBySql("phmAgrePqExamine.sql.getExamineDetailIsNullByPlanId", map);
		if(list != null && list.size() > 0){
			return "有用户未维护委托电量，不可提交！";
		}
		if("02".equals(tradeMode)){
			phmPurchasePlanMonthService.updatePlanStatusByPlanId(planId, BusinessContants.SELL_MONTHBIDSTATUS03);
		}else{
			phmPurchasePlanMonthService.updatePlanStatusByPlanId(planId, BusinessContants.SELL_MONTHBIDSTATUS02);
		}
		return "审核成功！";
	}

	/**
	  * @Title: getForecastResName
	  * @Description: 获取负荷预测权限
	  * @return String
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午3:38:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午3:38:22
	  * @since  1.0.0
	 */
	@Override
	public String getForecastResName() {
		//获取当前账户id by--zhangzhao
		String userId = SystemServiceUtil.getLoginUserInfo().getId();
		//用于前台验证 月度负荷预测按钮权限  
		Object obj = dao.getOneBySQL("phmAgrePqExamine.sql.getUserForecastRes", userId);
		String forecastResName = obj == null ? null : obj.toString();
		return forecastResName;
	}
	
	/**
	 * @Title: saveMessage<br/>
	 * @Description: 保存提示信息<br/>
	 * @param phmAgrePqExamineVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月30日 下午7:29:28
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月30日 下午7:29:28
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void saveMessage(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception{
		//发送短信并记录
		List<PhmAgrePqExamineDetail> phmAgrePqExamineDetailList = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList();
		for(int i=0; i<phmAgrePqExamineDetailList.size(); i++){
			PhmAgrePqExamineDetail phmAgrePqExamineDetail = phmAgrePqExamineDetailList.get(i);
			String phone = phmAgrePqExamineDetail.getContPhone();
			String planId = phmAgrePqExamineDetail.getPlanId();
			String contId = phmAgrePqExamineDetail.getContId();
			//保存信息
			SwSms swSms = new SwSms();
			swSms.setReceivePerson(contId);
			swSms.setSendDate(new Date());
			//获取当前登录人
			String loginName = SystemServiceUtil.getLoginUserName();
			String consId = phmAgrePqExamineDetail.getConsId();
			swSms.setSendPerson(loginName);
			swSms.setReceivePhone(phone);
			swSms.setConsId(consId);
			swSms.setPlanId(planId);
			swSms.setContent(SmsUtil.TEMPLATECODE94320024);
			//保存消息表信息
			SwMessage swMessage = new SwMessage();
			swMessage.setConsId(consId);
			swMessage.setContent(phmAgrePqExamineVo.getRemindInfo());
			swMessage.setTitle(phmAgrePqExamineVo.getTitle());
			swMessage.setMessageStatus("0");
			swMessage.setMessageType(BusinessContants.MESSAGETYPE01);
			swMessage.setPlanId(planId);
			swMessage.setSendDate(new Date());
			swMessage.setSendPerson(loginName);
			swMessage.setReceivePerson(contId);
			//查询当天是否存在消息记录
			Map<String,Object> message = (Map<String, Object>) dao.getOneBySQL("phmAgrePqExamine.sql.getSwMessageListByParams", swMessage);
			if(message != null){//消息记录已存在
				swMessage.setId(message.get("id").toString());
				dao.update(swMessage);
			}else{
				swMessage.setId(PlatformTools.getID());
				dao.save(swMessage);
			}
			//发送记录
			SwSms swSmsResult = swSmsService.getSwSmsByParams(swSms);
			if(swSmsResult != null){//存在记录
				String status = swSmsResult.getStatus();
				if(status != null && "01".equals(status)){//发送失败
					String params = "{\"ym\":\""+phmAgrePqExamineVo.getYm()+"\",\"date\":\""+phmAgrePqExamineVo.getYm()+"20\"}";
					boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94320024,params);
					if(!flag){//发送失败
						swSms.setStatus(BusinessContants.SEND_SMS_FAIL);
						swSms.setId(swSmsResult.getId());
						swSmsService.updateSwSms(swSms);
					}else{//发送成功
						swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
						swSms.setId(swSmsResult.getId());
						swSmsService.updateSwSms(swSms);
					}
				}else{
					swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
					swSms.setId(swSmsResult.getId());
					swSmsService.updateSwSms(swSms);
				}
			}else{//不存在记录
				String params = "{\"ym\":\""+phmAgrePqExamineVo.getYm()+"\",\"date\":\""+phmAgrePqExamineVo.getYm()+"20\"}";
				boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94320024,params);
				if(!flag){//发送失败
					swSms.setStatus(BusinessContants.SEND_SMS_FAIL);
					swSmsService.saveSwSms(swSms);
				}else{//发送成功
					swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
					swSmsService.saveSwSms(swSms);
				}
			}
		}
	}
	
	/**
	 * @Title: remindReportPq
	 * @Description:(重报信息提醒)
	 * @param phmAgrePqExamineVo
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月5日 上午10:56:43
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月5日 上午10:56:43
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void remindReportPq(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception {
		//发送短信并记录
		List<PhmAgrePqExamineDetail> phmAgrePqExamineDetailList = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList();
		for(int i=0; i<phmAgrePqExamineDetailList.size(); i++){
			PhmAgrePqExamineDetail phmAgrePqExamineDetail = phmAgrePqExamineDetailList.get(i);
			String phone = phmAgrePqExamineDetail.getContPhone();
			String planId = phmAgrePqExamineDetail.getPlanId();
			String contId = phmAgrePqExamineDetail.getContId();
			//保存信息
			SwSms swSms = new SwSms();
			swSms.setReceivePerson(contId);
			swSms.setSendDate(new Date());
			//获取当前登录人
			String loginName = SystemServiceUtil.getLoginUserName();
			String consId = phmAgrePqExamineDetail.getConsId();
			swSms.setSendPerson(loginName);
			swSms.setReceivePhone(phone);
			swSms.setConsId(consId);
			swSms.setPlanId(planId);
			swSms.setContent(SmsUtil.TEMPLATECODE94325011);
			//保存消息表信息
			SwMessage swMessage = new SwMessage();
			swMessage.setConsId(consId);
			swMessage.setContent(phmAgrePqExamineVo.getRemindInfo());
			swMessage.setTitle(phmAgrePqExamineVo.getTitle());
			swMessage.setMessageStatus("0");
			swMessage.setMessageType(BusinessContants.MESSAGETYPE01);
			swMessage.setPlanId(planId);
			swMessage.setSendDate(new Date());
			swMessage.setSendPerson(loginName);
			swMessage.setReceivePerson(contId);
			//查询当天是否存在消息记录
			Map<String,Object> message = (Map<String, Object>) dao.getOneBySQL("phmAgrePqExamine.sql.getSwMessageListByParams", swMessage);
			if(message != null){//消息记录已存在
				swMessage.setId(message.get("id").toString());
				dao.update(swMessage);
			}else{
				swMessage.setId(PlatformTools.getID());
				dao.save(swMessage);
			}
			//发送记录
			SwSms swSmsResult = swSmsService.getSwSmsByParams(swSms);
			if(swSmsResult != null){//存在记录
				String status = swSmsResult.getStatus();
				if(status != null && "01".equals(status)){//发送失败
					String params = "{\"ym\":\""+phmAgrePqExamineVo.getYm()+"\",\"date\":\""+phmAgrePqExamineVo.getYm()+"20\"}";
					boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94325011,params);
					if(!flag){//发送失败
						swSms.setStatus(BusinessContants.SEND_SMS_FAIL);
						swSms.setId(swSmsResult.getId());
						swSmsService.updateSwSms(swSms);
					}else{//发送成功
						swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
						swSms.setId(swSmsResult.getId());
						swSmsService.updateSwSms(swSms);
					}
				}else{
					swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
					swSms.setId(swSmsResult.getId());
					swSmsService.updateSwSms(swSms);
				}
			}else{//不存在记录
				String params = "{\"ym\":\""+phmAgrePqExamineVo.getYm()+"\",\"date\":\""+phmAgrePqExamineVo.getYm()+"20\"}";
				boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94325011,params);
				if(!flag){//发送失败
					swSms.setStatus(BusinessContants.SEND_SMS_FAIL);
					swSmsService.saveSwSms(swSms);
				}else{//发送成功
					swSms.setStatus(BusinessContants.SEND_SMS_SUCCESS);
					swSmsService.saveSwSms(swSms);
				}
			}
		}
	}

	/**
	  * @Title: getExamineDetailYearListByPlanId
	  * @Description: 根据planId获取年交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return QueryResult<PhmAgrePqExamineDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午6:10:38
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午6:10:38
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public QueryResult<PhmAgrePqExamineDetail> getExamineDetailYearListByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception {
		QueryResult<PhmAgrePqExamineDetail> queryResult = new QueryResult<PhmAgrePqExamineDetail>();
		if(phmAgrePqExamineVo.getPlanId()!=null && !phmAgrePqExamineVo.getPlanId().equals("") && phmAgrePqExamineVo.getYm()!=null && !phmAgrePqExamineVo.getYm().equals("")){
			Object result = dao.getOneBySQL("phmAgrePqExamine.sql.getconsCountByPlanId",phmAgrePqExamineVo);
			Long total = result == null ? 0 : Long.valueOf(result.toString());
			List<PhmAgrePqExamineDetail> list = (List<PhmAgrePqExamineDetail>) dao.getBySql("phmAgrePqExamine.sql.getExamineDetailYearByPlanId", phmAgrePqExamineVo);
			queryResult.setTotal(total);
			queryResult.setData(list);
		}else{
			throw new Exception("参数异常！");
		}
		return queryResult;
	}

	/**
	  * @Title: getPhmAgreExamineByPlanId
	  * @Description: 根据planId分页获取用户列表，计划制定编辑用
	  * @param phmPlanConsRelaVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月22日 下午3:18:25
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月22日 下午3:18:25
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	public QueryResult<PhmPurchasePlanConsRelaDetail> getPhmAgreExamineByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception {
		if(phmAgrePqExamineVo.getPlanId() == null){
			return null;
		}
		QueryResult<PhmPurchasePlanConsRelaDetail> queryResult = new QueryResult<PhmPurchasePlanConsRelaDetail>();
		long total = getPurchasePlanConsCountByPlanId(phmAgrePqExamineVo);
		List<PhmPurchasePlanConsRelaDetail> consRelaList = getPlanConsListPageByPlanId(phmAgrePqExamineVo);
		queryResult.setTotal(total);
		queryResult.setData(consRelaList);
		return queryResult;
	}

	
	/**
	  * @Title: getPurchasePlanConsCountByPlanId
	  * @Description: getphmPlanConsRelaCountByPlanId
	  * @param phmAgrePqExamineVo
	  * @return long
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月22日 下午3:42:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月22日 下午3:42:22
	  * @since  1.0.0
	 */
	public long getPurchasePlanConsCountByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmAgrePqExamine.sql.getPurchasePlanConsCountByPlanId",phmAgrePqExamineVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	/**
	 * @Title: getPlanConsListPageByPlanId
	 * @Description: 通过planId及pagingFlag等分页属性获取phmAgrePqExamine
	 * @param phmAgrePqExamineVo
	 * @return 
	 * List<PhmPurchasePlanConsRelaDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月16日 下午4:43:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月16日 下午4:43:06
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPurchasePlanConsRelaDetail> getPlanConsListPageByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) throws Exception {
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmAgrePqExamineVo == null){
			phmAgrePqExamineVo = new PhmAgrePqExamineVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPurchasePlanConsRelaDetail> consRelaList = (List<PhmPurchasePlanConsRelaDetail>) dao.getBySql("phmAgrePqExamine.sql.getPlanConsListPageByPlanId", phmAgrePqExamineVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(consRelaList);
		return consRelaList;
	}

	/**
	  * @Title: getSubMonthDetailByPlanId
	  * @Description: 根据计划id获取用户年度分月信息列表
	  * @param phmAgrePqExamineVo
	  * @return List<PhmSubMonthDetail>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月25日 下午3:33:53
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月25日 下午3:33:53
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PhmSubMonthDetail> getSubMonthDetailByPlanId(PhmAgrePqExamineVo phmAgrePqExamineVo) {
		List<PhmSubMonthDetail> subMonthDetail = (List<PhmSubMonthDetail>) dao.getBySql("phmAgrePqExamine.sql.getSubMonthDetailByPlanId", phmAgrePqExamineVo);
		return subMonthDetail;
	}
	
}
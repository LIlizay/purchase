package com.hhwy.purchaseweb.deviationcheck.smconspowerview.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.purchaseweb.archives.scconsumerinfo.domain.ScConsumerInfoVo;
import com.hhwy.purchaseweb.archives.scconsumerinfo.service.ScConsumerInfoService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.AgreConsInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewVo;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.service.SmConsPowerViewService;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.service.SmDeviationInfoService;
import com.hhwy.purchaseweb.purchasesms.SmsUtil;
import com.hhwy.purchaseweb.purchasesms.service.PurchaseSwSmsService;
import com.hhwy.selling.domain.ScContactsInfo;
import com.hhwy.selling.domain.SellingSwMessage;
import com.hhwy.selling.domain.SmConsPowerView;

/**
 * <b>类 名 称：</b>SmConsPowerViewServiceImpl<br/>
 * <b>类 描 述：</b><br/>		用电计划的service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月17日 下午5:28:13<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SmConsPowerViewServiceImpl implements SmConsPowerViewService {

	public static final Logger log = LoggerFactory.getLogger(SmConsPowerViewServiceImpl.class);
	
	private static final String[] columns = {"b.jan", "b.feb", "b.mar", "b.apr", "b.may", "b.jun", "b.jul", "b.aug", "b.sept", "b.oct", "b.nov", "b.dece"};

	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smDeviationInfoService	偏差预警service
	 */
	@Autowired
	private SmDeviationInfoService smDeviationInfoService;
	
	/**
	 * scConsDateService		用户例日维护service
	 */
	@Autowired
	private ScConsDateService scConsDateService;
	
	@Autowired
	private PurchaseSwSmsService purchaseSwSmsService;
	
	/**
	 * scConsumerInfoService		用户service
	 */
	@Autowired
	private ScConsumerInfoService scConsumerInfoService;
	
	/**
	 * @Title: getSmConsPowerViewByPage
	 * @Description: list页面分页获取用户用电计划
	 * @param smConsPowerViewVo
	 * @return
	 * @throws Exception 
	 * QueryResult<SmConsPowerViewDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:15:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:15:48
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<SmConsPowerViewDetail> getSmConsPowerViewByPage(SmConsPowerViewVo smConsPowerViewVo) throws Exception{
		String ym = smConsPowerViewVo.getYm();
		if(ym != null && !"".equals(ym) && ym.indexOf("-") != -1){
			ym = ym.replace("-", "");
		}
		smConsPowerViewVo.setYm(ym);
		QueryResult<SmConsPowerViewDetail> queryResult = new QueryResult<SmConsPowerViewDetail>();
		Parameter.isFilterData.set(true);
		Object obj = dao.getOneBySQL("smConsPowerView.sql.getSmConsPowerViewListCount", smConsPowerViewVo);
		long total = obj == null ? 0 : Integer.valueOf(obj.toString());
		List<SmConsPowerViewDetail> smConsPowerViewDetailList = (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getSmConsPowerViewList", smConsPowerViewVo);
		Parameter.isFilterData.set(false);
		queryResult.setTotal(total);
		queryResult.setData(smConsPowerViewDetailList);
		return queryResult;
	}	
	
	/**
	 * @Title: getAgreConsList
	 * @Description: 根据选择年月查询合同用户列表
	 * @param smConsPowerViewVo
	 * @return
	 * @throws Exception 
	 * QueryResult<AgreConsInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:35:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:35:27
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<AgreConsInfoDetail> getAgreConsList(SmConsPowerViewVo smConsPowerViewVo) throws Exception{
		QueryResult<AgreConsInfoDetail> queryResult = new QueryResult<AgreConsInfoDetail>();
		int month = Integer.parseInt(smConsPowerViewVo.getYm().substring(5)) - 1;
		smConsPowerViewVo.setColumn(columns[month]);
		
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsPowerView.sql.getAgreConsCount",smConsPowerViewVo);
		long total = result == null ? 0 : Long.valueOf(result.toString());
		List<AgreConsInfoDetail> agreConsInfoDetailList = (List<AgreConsInfoDetail>) dao.getBySql("smConsPowerView.sql.getAgreConsList", smConsPowerViewVo);
		Parameter.isFilterData.set(false);
		
		ConvertListUtil.convert(agreConsInfoDetailList);
		queryResult.setTotal(total);
		queryResult.setData(agreConsInfoDetailList);
		return queryResult;
	}
	
	/**
	 * @Title: addSmConsPowerViewByYm
	 * @Description:  电量收集记录新增，即用电计划新增
	 * @param smConsPowerViewVo		用到： ym(yyyyMM格式) 、consIds 、agrePqs 
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:53:31
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:53:31
	 * @since  1.0.0
	 */
	@Transactional
	public void addSmConsPowerViewByYm(SmConsPowerViewVo smConsPowerViewVo) throws Exception{
		String ym = smConsPowerViewVo.getYm();		//(yyyyMM格式)
		String[] consIds = smConsPowerViewVo.getConsIds();
		BigDecimal[] agrePqs = smConsPowerViewVo.getAgrePqs();
		
		Map<String, BigDecimal> consIdToAgrePq = new HashMap<>();
		for(int i = 0; i < consIds.length; i++){
			consIdToAgrePq.put(consIds[i], agrePqs[i]);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		//根据抄表例日分组用户，格式："startDate-endDate-days"  --> List<consId>
		Map<String, List<String>> dateToIdListMap = new HashMap<>(consIds.length);
		for(int i = 0; i < consIds.length; i++){
			String consId = consIds[i];
			
			//Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
			//value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）
			Map<String, Object> dateAndDays = scConsDateService.getDateAndDays(consId , ym);
			
			int days = (int)dateAndDays.get("days");
			String startDate = dateAndDays.get("startDate").toString();
			String endDate = dateAndDays.get("endDate").toString();
	        
			String key = startDate + "-" + endDate + "-" + days;
			if(dateToIdListMap.containsKey(key)) {
				dateToIdListMap.get(key).add(consId);
			}else {
				List<String> consIdList = new ArrayList<>();
				consIdList.add(consId);
				dateToIdListMap.put(key, consIdList);
			}
		}
		
		//全部最终需要新增和更新的用电计划信息
		List<SmConsPowerView> addList = new ArrayList<>();
		List<SmConsPowerView> updateList = new ArrayList<>();
		
		//循环按抄表例日分组的用户，把分组后的用户依次去数据库中获取本月的用电计划实体SmConsPowerView
		//然后把所有数据库中存在的用电计划存入map中，然后组装全量的新增list，若list元素存在于map中，则是更新，若不存在，则是新增
		Calendar cal = Calendar.getInstance();
		for (Entry<String, List<String>> entry : dateToIdListMap.entrySet()) {
			//"startDate-endDate"格式： yyyyMMdd-yyyyMMdd-30
			String key = entry.getKey();
			List<String> consIdList = entry.getValue();
			String startDate = key.substring(0, 8);
			String endDate = key.substring(9,17);
			int days = Integer.valueOf(key.substring(18));
			
			//根据开始结束时间和consId的列表删除偏差预警
			smDeviationInfoService.deleteByConsIdListAndDateRange(consIdList, startDate, endDate);
			
			Map<String, Object> params = new HashMap<>();
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			params.put("consIds", consIdList);
			@SuppressWarnings("unchecked")
			//最终需要更新的用电计划信息
			List<SmConsPowerView> updatePartList = (List<SmConsPowerView>)dao.getBySql("smConsPowerView.sql.getPowerViewByDateAndConsIds",params);
			//consId + ymd   -->  SmConsPowerView 的map
			Map<String, SmConsPowerView> consIdYmdToViewMap = new HashMap<>(days);
			if(updatePartList != null) {
				//加入到最终需要更新的列表中
				updateList.addAll(updatePartList);
				for (SmConsPowerView smConsPowerView : updatePartList) {
					String consIdYmd = smConsPowerView.getConsId() + smConsPowerView.getYmd();
					consIdYmdToViewMap.put(consIdYmd, smConsPowerView);
				}
			}
			//循环遍历consIdList此部分用户的全量用电计划，并得出需要新增的用电计划，及计算需要更新的用电计划中的4个属性（“累计计划电量、日计划电量、ym、isDelete置为0”）
			for (int i = 0; i < consIdList.size(); i ++) {
				String consId = consIdList.get(i);
				//循环一个用户当月每天的数据
				for (int j = 0; j < days; j++) {
					if(j == 0) {
						cal.setTime(format.parse(startDate));
					}else {
						cal.add(Calendar.DAY_OF_MONTH, 1);
					}
					String dateStr = format.format(cal.getTime());
					String consIdYmdKey = consId + dateStr;
					SmConsPowerView smConsPowerView = null;
					//如果数据库中存在此用户当天的数据，则判断此条数据需要更新“累计计划电量、日计划电量、ym、isDelete置为0”
					if(consIdYmdToViewMap.containsKey(consIdYmdKey)) {
						smConsPowerView = consIdYmdToViewMap.get(consIdYmdKey);
					}else {//如果数据库中不存在此用户当天的数据，则新增此条数据并赋值“累计计划电量、日计划电量、ym、isDelete置为0； ymd、consId、”
						smConsPowerView = new SmConsPowerView();
						smConsPowerView.setYmd(dateStr);
						smConsPowerView.setConsId(consId);
						addList.add(smConsPowerView);
					}
					smConsPowerView.setYm(ym);
					smConsPowerView.setIsDelete("0");
					BigDecimal agrePq = consIdToAgrePq.get(consId);
					if(agrePq != null) {
						if(j == days - 1) {	//如果是最后一天，则累计计划电量=合同电量，为消除误差：日计划电量=合同电量-之前所有天的日计划之和
							smConsPowerView.setDayPlanPq(agrePq.subtract(
									agrePq.divide(new BigDecimal(days), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(days - 1))));
							smConsPowerView.setPlanPq(agrePq);
						}else {				//如果不是最后一天，则累计计划电量=日计划电量×天数，日计划电量=合同电量-之前所有天的日计划之和
							smConsPowerView.setDayPlanPq(agrePq.divide(new BigDecimal(days), 2, RoundingMode.HALF_UP));
							smConsPowerView.setPlanPq(smConsPowerView.getDayPlanPq().multiply(new BigDecimal(j+1)));
						}
					}
				}
			}
		}
		//至此完成新增和更新数据的统计，然后分别新增和更新数据
		if(addList.size() != 0) {
			String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
			String loginUserOrgCode = SystemServiceUtil.getLoginUserInfo().getOrgId();	//4a4caba00e884cbe966e1bd4239dc7e7
			Map<String, Object> addParams = new HashMap<>();
			addParams.put("loginUserId", loginUserId);
			addParams.put("orgNo", loginUserOrgCode);
			addParams.put("addList", addList);
			//批量保存
//			dao.saveListBatch(addList);
			dao.saveBySql("smConsPowerView.sql.addPowerViewList", addParams);
		}
		if(updateList.size() != 0){
			//更新多个用户的计划电量字段
			this.updatePlanPq(updateList);
			
			//updateList中为多个用户的用电计划，需要分用户计算偏差预警
			Map<String,List<SmConsPowerView>> consIdToListMap = new HashMap<>();
			for (int i = 0; i < updateList.size(); i++) {
				String consId = updateList.get(i).getConsId();
				if(!consIdToListMap.containsKey(consId)) {
					consIdToListMap.put(consId, new ArrayList<SmConsPowerView>());
				}
				consIdToListMap.get(consId).add(updateList.get(i));
			}
			for (List<SmConsPowerView> viewList : consIdToListMap.values()) {
				smDeviationInfoService.calculateDeviationByPowerViewList(viewList);
			}
		}
	}

	/**
	 * @Title: getPowerViewByCondIdAndYm
	 * @Description: 根据consId和ym（抄表年月）获取用电计划列表,及偏差预警信息
	 * @param params	consId、ym(yyyyMM格式)
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午9:20:53
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午9:20:53
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPowerViewByCondIdAndYm(Map<String, String> params) throws Exception {
		String ym = params.get("ym");
		ym = ym.replace("-", "");
		Map<String, Object> result = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		
		cal.set(Integer.valueOf(ym.substring(0, 4)),Integer.valueOf(ym.substring(4)) - 1, 1);
		//获取当前月的天数
		result.put("date", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		//获取上一个月的天数
		cal.add(Calendar.MONTH, -1);
		result.put("lastDate", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		//根据consId和ym获取用电计划列表
		List<SmConsPowerViewDetail> list = (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getSmConsPowerView", params);
		result.put("list", list);
		
		//获取偏差预警信息
        List<SmDeviationInfoDetail> deviationList= smDeviationInfoService.getDeviationInfoListByConsIdAndYm(params.get("consId"),ym);
        result.put("deviationList", deviationList);
		return result;
	}
	
	/**
	 * @Title: updateSmConsPowerViews
	 * @Description: 更新计划电量，是前台日历页面修改日计划电量（此日之后的所有累计电量会变，所以直接是保存的整月数据）的保存接口
	 * @param smConsPowerViews	整月计划电量信息，有效字段为id、planPq、dayPlanPq
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午10:41:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午10:41:10
	 * @since  1.0.0
	 */
	@Transactional
	public void updatePlanPq(List<SmConsPowerView> smConsPowerViews) throws Exception{
		if(smConsPowerViews == null || smConsPowerViews.size() == 0){
			throw new BusinessException("更新数据为空！");
		}
		for (SmConsPowerView smConsPowerView : smConsPowerViews) {
			dao.updateBySql("smConsPowerView.sql.updatePowerViewPlanData", smConsPowerView);
		}
	}
	
	/**
	 * 
	 * sendMessage(用户上报实际用电量提醒),老版本遗留代码<br/>
	 * @param params
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public String sendMessage(String consId) throws Exception {
		ScConsumerInfoVo scConsumerInfoVo = scConsumerInfoService.getScConsumerInfoById(consId);
		ScContactsInfo scContactsInfo = scConsumerInfoVo.getScContactsInfo();
		String phone = scContactsInfo.getPhone();
		String contId = scContactsInfo.getId();
         //获取当前登录人
         String loginName = SystemServiceUtil.getLoginUserName();
         Calendar calendar = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String ymd = sdf.format(calendar.getTime());
	        //保存消息表信息
         SellingSwMessage swMessage = new SellingSwMessage();
         swMessage.setId(PlatformTools.getID());
         swMessage.setConsId(consId);
         swMessage.setContent("尊敬的用户，请您于"+ymd+" 18:00前上报用电情况，感谢您的配合。");
         swMessage.setTitle("月度电量上报提醒");
         swMessage.setMessageStatus("0");
         swMessage.setMessageType(BusinessContants.MESSAGETYPE02);
         swMessage.setSendDate(new Date());
         swMessage.setSendPerson(loginName);
         swMessage.setReceivePerson(contId);
         
         //查询当天是否存在消息记录
         Map<String,Object> message = (Map<String, Object>) dao.getOneBySQL("smConsPowerView.sql.getSwMessageListByParams", swMessage);
         if(message != null){//消息记录已存在
             swMessage.setId(message.get("id").toString());
             dao.update(swMessage);
         }else{
             swMessage.setId(PlatformTools.getID());
             dao.save(swMessage);
         }
	        
         //保存信息
         Map<String,Object> smsMap = new HashMap<String, Object>();
         smsMap.put("receivePerson",contId);
         smsMap.put("sendDate",new Date());
         smsMap.put("sendPerson", loginName);
         smsMap.put("receivePhone", phone);
         smsMap.put("consId", consId);
         smsMap.put("content", SmsUtil.TEMPLATECODE94190022);
         String id = PlatformTools.getID();
         
         //发送记录
         Map<String,Object> swSmsResult = purchaseSwSmsService.getSwSmsByParams(smsMap);
         if(swSmsResult != null){//存在记录
             String status = swSmsResult.get("status").toString();
             if(status != null && BusinessContants.SEND_SMS_FAIL.equals(status)){//发送失败
                 String para = "{\"time\":\""+ymd+" 18:00\"}";
                 boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94190022,para);
                 if(!flag){//发送失败
                	 smsMap.put("status",BusinessContants.SEND_SMS_FAIL);
                	 smsMap.put("id",swSmsResult.get("id"));
                     purchaseSwSmsService.updateSwSms(smsMap);
                     return "发送失败";
                 }else{//发送成功 
                	 smsMap.put("status",BusinessContants.SEND_SMS_SUCCESS);
                	 smsMap.put("id",swSmsResult.get("id"));
                	 purchaseSwSmsService.updateSwSms(smsMap);
                     return "发送成功";
                 }
             }else{
            	 smsMap.put("status",BusinessContants.SEND_SMS_SUCCESS);
            	 smsMap.put("id",swSmsResult.get("id"));
            	 purchaseSwSmsService.updateSwSms(smsMap);
                 return "发送成功";
             }
         }else{//不存在记录
        	 String para = "{\"time\":\""+ymd+" 18:00\"}";
             boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94190022,para);
             if(!flag){//发送失败
            	 smsMap.put("status",BusinessContants.SEND_SMS_FAIL);
            	 smsMap.put("id", id);
            	 purchaseSwSmsService.saveSwSms(smsMap);
                 return "发送失败";
             }else{//发送成功
            	 smsMap.put("status",BusinessContants.SEND_SMS_SUCCESS);
            	 smsMap.put("id", id);
            	 purchaseSwSmsService.saveSwSms(smsMap);
                 return "发送成功";
             }
         }
	}
	
	/**
	 * @Title: deletePlanDataByParams
	 * @Description: 根据用户id 年月删除数据，只是删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null
	 * 					并删除用户当月的所有预警信息
	 * @param delParams  	格式[{consId : "consId1", ym : "201811" },{consId : "consId2", ym : "201810" }....]
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午11:07:39
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午11:07:39
	 * @since  1.0.0
	 */
	@Transactional
	public void deletePlanDataByParams(List<Map<String, String>> delParams) throws Exception{
		if(delParams == null || delParams.size() == 0){
			throw new Exception("删除数据为空！");
		}
		//依据ym给用户id分组
		Map<String, List<String>> ymConsIdListMap = new HashMap<>();
		for (Map<String, String> map : delParams) {
			if(ymConsIdListMap.containsKey(map.get("ym"))) {
				ymConsIdListMap.get(map.get("ym")).add(map.get("consId"));
			}else {
				List<String> condIdList = new ArrayList<>();
				condIdList.add(map.get("consId"));
				ymConsIdListMap.put(map.get("ym"), condIdList);
			}
		}
		//分别根据ym及用户id分组 删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null,删除偏差预警
		for (Entry<String, List<String>> entry : ymConsIdListMap.entrySet()) {
			this.deletePlanDataByYmAndConsIds(entry.getKey(),entry.getValue());
		}
	}

	/**
	 * @Title: deletePlanDataByYmAndConsIds
	 * @Description: 根据用户id 年月删除数据，只是删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null
	 * 					并删除用户当月的所有预警信息
	 * @param ym	yyyyMM格式
	 * @param consIds
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月26日 下午6:00:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月26日 下午6:00:48
	 * @since  1.0.0
	 */
	@Transactional
	public void deletePlanDataByYmAndConsIds(String ym, List<String> consIds) throws Exception{
		if(ym == null || ym.length() != 6 || consIds == null || consIds.size() ==0) {
			throw new BusinessException("参数错误！");
		}
		Map<String,Object> params = new HashMap<>();
		params.put("ym", ym);
		params.put("consIds", consIds);
		//删除用户当月的所有预警信息
		dao.executeSql("smConsPowerView.sql.deleteWarnInfoByYmAndConsIds", params);
		//删除其累计计划和日计划电量，并把其isDelete置为1,ym设置为null
		dao.executeSql("smConsPowerView.sql.deletePlanDataByYmAndConsIds", params);
	}
	
	/**
	 * @Title: deletePowerViewByConsIds
	 * @Description: 根据用户id数组删除用电计划数据（物理删除），并删除用户当月的所有预警信息,删除用户时用到
	 * @param consIds	数组
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月26日 下午6:00:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月26日 下午6:00:48
	 * @since  1.0.0
	 */
	@Transactional
	public void deletePowerViewByConsIds(String[] consIds){
		//删除用户当月的所有预警信息
		smDeviationInfoService.deleteDeviationInfoByConsIds(consIds);
		//根据用户id数组删除用电计划数据（物理删除）
		dao.executeSql("smConsPowerView.sql.deletePowerViewByConsIds", consIds);
	}
	
	/**
	 * 添加对象SmConsPowerView
	 * @param 实体对象
	 */
	public void saveSmConsPowerViewList(List<SmConsPowerView> smConsPowerViewList) {
		dao.saveList(smConsPowerViewList);
	}
	
	/**
	 * @Title: updateActualPqByMonth
	 * @Description: 	按月更新日用电量和累计用电量两个字段，不需要考虑更新一条数据是否影响之后的数据变化
	 * 					haveActualPqFlag为false. 即powerViewList中只有日用电量(可能为null)，累计用电量需要结合数据库中数据统一计算
	 * 						（从数据库获取powerViewList第一条数据时间的并且是本ym抄表年月的前一个累计电量，计算每日的累计用电量）
	 * 					haveActualPqFlag为true. 即powerViewList中有累计用电量(可能为null)和日用电量(可能为null)
	 * @param consId
	 * @param ym
	 * @param powerViewList 时间增序排列，ymd日期是连续的
	 * @param haveActualPqFlag 		数据类型，true：有累计用电量(可能为null)和日用电量(可能为null)；  false：没有累计用电量(为null)，需要重新计算累计用电量
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void updateActualPqByMonth(String consId, String ym, List<SmConsPowerView> powerViewList,boolean haveActualPqFlag) throws ParseException {
		if(powerViewList.size() == 0) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		if(!haveActualPqFlag) {		//只有日用电量(可能为null)，需要重新计算累计用电量
			String firstDate = powerViewList.get(0).getYmd();
			params.put("ymd", firstDate);
			BigDecimal lastActualPq = (BigDecimal)dao.getOneBySQL("smConsPowerView.sql.getLastActualPqByParams",params);
			
			//本ym抄表年月中没有初始的累计用电量（firstDate为本ym第一天，或者之前几天没有ActualPq）
			if(lastActualPq == null) {
				lastActualPq = BigDecimal.ZERO;
			}
			//赋值累计用电量
			for (SmConsPowerView smConsPowerView : powerViewList) {
				if(smConsPowerView.getDayActualPq() != null) {
					lastActualPq = lastActualPq.add(smConsPowerView.getDayActualPq());
					smConsPowerView.setActualPq(lastActualPq);
				}
			}
		}
		List<String> ymds = new ArrayList<>();
		for (SmConsPowerView smConsPowerView : powerViewList) {
			ymds.add(smConsPowerView.getYmd());
		}
		params.put("ymds", ymds);
		//从数据库中获取现有用电计划
		List<SmConsPowerView> powerViewEntityList = (List<SmConsPowerView>)dao.getBySql("smConsPowerView.sql.getPowerViewListByYmds",params);
		
		//更新数据
		this.updatePowerViewList(powerViewList, powerViewEntityList);
	}
	/**
	 * @Title: updateActualPqByYmds
	 * @Description: 	按日更新日用电量和累计用电量两个字段，需要考虑更新一条数据是否影响之后的数据变化
	 * 					powerViewList中只有累计用电量(可能为null)，日用电量需要结合数据库中数据统一计算
	 * 						（根据ym从数据库获取本抄表年月所有用电计划，计算每日的日用电量）
	 * @param consId
	 * @param ym	yyyyMM格式
	 * @param startDate 本抄表月的开始日期
	 * @param powerViewList 时间增序排列，ymd日期是连续的
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void updateActualPqByYmds(String consId, String ym,String startDate, List<SmConsPowerView> powerViewList) throws ParseException {
		if(powerViewList.size() == 0) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyyMMdd");
		
		Map<String, Object> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		
		//从数据库中获取现有本月的用电计划，无序的
		List<SmConsPowerView> powerViewEntityList = (List<SmConsPowerView>)dao.getBySql("smConsPowerView.sql.getPowerViewListByYm",params);
		//把数据库中数据放入map中，方便查询
		Map<String, SmConsPowerView> entityMap = new HashMap<>();
		if(powerViewEntityList != null && powerViewEntityList.size() != 0) {
			for (SmConsPowerView entity : powerViewEntityList) {
				entityMap.put(entity.getYmd(), entity);
			}
		}
		//最终需要更新的数据，计算了日用电量，并且加入了老数据会影响的数据
		List<SmConsPowerView> powerViewListNew = new ArrayList<>();
		SmConsPowerView nextPowerView = null;
		SmConsPowerView prevPowerView = null;
		for (int i = 0; i < powerViewList.size(); i++) {
			SmConsPowerView powerView = powerViewList.get(i);
			powerViewListNew.add(powerView);
			if(i < powerViewList.size() - 1) {
				nextPowerView = powerViewList.get(i+1);
			}else {
				nextPowerView = null;
			}
			if(i > 0) {
				prevPowerView = powerViewList.get(i-1);
			}else {
				prevPowerView = null;
			}
			String ymd = powerView.getYmd();
			cal.setTime(sdfYmd.parse(ymd));
			cal.add(Calendar.DAY_OF_MONTH, -1);  	//前一天
			String prevYmd = sdfYmd.format(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 2);  	//后一天
			String nextYmd = sdfYmd.format(cal.getTime());
			//计算今天的日用电量
			if(prevPowerView != null && prevYmd.equals(prevPowerView.getYmd())) {	//如果更新数据中含有前一天的数据，则可以计算出今天的日用电量
				if(powerView.getActualPq() != null && prevPowerView.getActualPq() != null) {
					powerView.setDayActualPq(powerView.getActualPq().subtract(prevPowerView.getActualPq()));
				}
			}else if(entityMap.containsKey(prevYmd)){		//如果数据库中含有前一天的数据，则可以计算出今天的日用电量
				prevPowerView = entityMap.get(prevYmd);
				if(powerView.getActualPq() != null && prevPowerView.getActualPq() != null) {
					powerView.setDayActualPq(powerView.getActualPq().subtract(prevPowerView.getActualPq()));
				}
			}else if(prevPowerView == null && startDate.equals(ymd)){	//i为0，并且ymd为本月第一天，prevYmd是上个月例日
				powerView.setDayActualPq(powerView.getActualPq());
			}
			//计算后一天的日用电量
			if(nextPowerView != null && nextYmd.equals(nextPowerView.getYmd())) {	//如果更新数据中含有后一天的数据，则下次循环可以计算到
				continue;
			}else if(entityMap.containsKey(nextYmd)){		//如果数据库中含有后一天的数据，则可以计算出更新的后一天的日用电量
				nextPowerView = entityMap.get(nextYmd);
				if(powerView.getActualPq() != null && nextPowerView.getActualPq() != null) {
					nextPowerView.setDayActualPq(nextPowerView.getActualPq().subtract(powerView.getActualPq()));
				}else {
					nextPowerView.setDayActualPq(null);
				}
				powerViewListNew.add(nextPowerView);
			}
		}
		//更新数据
		this.updatePowerViewList(powerViewListNew, powerViewEntityList);
	}
	
	/**
	 * @Title: rebuildPowerViewByMonth
	 * @Description: 	按月重建本月用电计划，并重新计算偏差预警
	 * 						若本月数据是删除状态（isdelete是1）或没有本月数据，则先根据ym删除本月数据及偏差预警，然后清除计划电量两字段值，
	 * 								变更powerViewList中的isDelete为1，保存存在实际用电量值的powerView，无需重新计算偏差预警
	 * 						若本月数据不是删除状态（isdelete是0），则先根据ym删除本月数据及偏差预警，保存powerViewList，并重新计算偏差预警
	 * 					注，为防止特殊情况的垃圾数据，在根据ym删除本月数据的基础上也会根据powerViewList的最大最小时间区间删除数据
	 * @param consId
	 * @param ym
	 * @param powerViewList 时间增序排列，ymd日期是连续的，并且是整月的数据，默认是未删除状态，以数据库状态为准
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午5:32:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午5:32:49
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@Transactional
	public void rebuildPowerViewByMonth(String consId, String ym, List<SmConsPowerView> powerViewList) throws ParseException {
		if(powerViewList.size() == 0) {
			return;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		//从数据库中获取现有用电计划的状态（是否是删除状态）
		Object obj = dao.getOneBySQL("smConsPowerView.sql.getPowerViewStatusByYm",params);
		int count = obj == null ? 0 : Integer.valueOf(obj.toString());
		
		//根据ym及时间区间删除用电计划
		String startDate = powerViewList.get(0).getYmd();
		String endDate = powerViewList.get(powerViewList.size() - 1).getYmd();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		dao.deleteBySql("smConsPowerView.sql.deletePowerViewByYmAndDateRange", params);
		
		//根据开始结束时间删除偏差预警
		smDeviationInfoService.deleteByConsIdAndDateRange(consId, startDate, endDate);
		for (SmConsPowerView view : powerViewList) {
			if(view.getId() == null || "".equals(view.getId())) {
				view.setId(PlatformTools.getID());
			}
		}
		if(count > 0) {		//现有的用电计划不是删除状态
//			dao.saveListBatch(powerViewList);
			String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
			String loginUserOrgCode = SystemServiceUtil.getLoginUserInfo().getOrgId();	//4a4caba00e884cbe966e1bd4239dc7e7
			Map<String, Object> addParams = new HashMap<>();
			addParams.put("loginUserId", loginUserId);
			addParams.put("orgNo", loginUserOrgCode);
			addParams.put("addList", powerViewList);
			//批量保存
			dao.saveBySql("smConsPowerView.sql.addPowerViewList", addParams);
			//重新计算偏差预警
			smDeviationInfoService.calculateDeviationByPowerViewList(powerViewList);
		}else {				//现有的用电计划是删除状态
			List<SmConsPowerView> powerViewListNew = new ArrayList<>();
			for (SmConsPowerView view : powerViewList) {
				if(view.getActualPq() != null || view.getDayActualPq() != null) {
					view.setIsDelete("1");
					powerViewListNew.add(view);
				}
			}
			//保存新的用电计划
			if(powerViewListNew.size() != 0) {
				String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
				String loginUserOrgCode = SystemServiceUtil.getLoginUserInfo().getOrgId();	//4a4caba00e884cbe966e1bd4239dc7e7
				Map<String, Object> addParams = new HashMap<>();
				addParams.put("loginUserId", loginUserId);
				addParams.put("orgNo", loginUserOrgCode);
				addParams.put("addList", powerViewListNew);
				//批量保存
				dao.saveBySql("smConsPowerView.sql.addPowerViewList", addParams);
//				dao.saveListBatch(powerViewListNew);
			}
		}
	}
	
	/**
	 * @Title: getSmConsPowerViewDetailList<br/>
	 * @Description: 根据用户id ym、usuallyDateFlag（是否按抄表例日展示） 查询用户一个月的用电情况数据，监控平台的用户电量页面用到
	 * 		售电根据usuallyDateFlag来判断是根据smConsPowerView中的ymd字段还是ym字段去匹配传入的ym参数获取用户当月全部用电数据，
	 * 		如果是自然月获取的电量数据，则累加日用电量来更新累计用电量，累加日计划电量来更新累计计划电量。
	 * 		最终计算累计偏差率和日偏差率，返回结果
	 * @param params	consId,ym（yyyyMM格式）,usuallyDateFlag (是否安装抄表年月查询)
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月9日 下午6:31:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年01月08日 下午1:05:10
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsPowerViewDetail> getSmConsPowerViewDetailList(Map<String, String> params) throws Exception{
		String usuallyDateFlag = params.get("usuallyDateFlag"); 		//是否按照抄表例日获取用户数据，值为0或1，字符串
		List<SmConsPowerViewDetail> list = null;
		if(!"0".equals(usuallyDateFlag)) {		//按照抄表例日获取用户数据
			list =  (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getSmConsPowerView", params);
		}else{									//按照自然年月获取用户数据
			list =  (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getSmConsPowerViewByNatureMonth", params);
		}
		
		//如果是按照自然年月获取用户数据，则累计计划电量和累计用电量是按照自然年月的每天电量累加得来
		if("0".equals(usuallyDateFlag)) {
			//存最后一次存在的累计电量 
			BigDecimal lastActualPq = BigDecimal.ZERO;
			for(int i=0; i<list.size(); i++){
				SmConsPowerViewDetail smConsPowerViewDetail = list.get(i);
				
				if(i == 0) {//第一天的累计电量（包括计划和实际）  =  第一天的电量
					smConsPowerViewDetail.setPlanPq(smConsPowerViewDetail.getDayPlanPq());
					smConsPowerViewDetail.setActualPq(smConsPowerViewDetail.getDayActualPq());
					//当日有电量 
					if(smConsPowerViewDetail.getDayActualPq() != null){
						//存最后一次存在的累计电量 
						lastActualPq = lastActualPq.add(smConsPowerViewDetail.getActualPq());
					}
				}else {		//随后的累计电量（包括计划和实际）  =  当天的电量 + 前一天的累计电量
					BigDecimal dayPlanPq = smConsPowerViewDetail.getDayPlanPq();
					smConsPowerViewDetail.setPlanPq(dayPlanPq == null ? 
							list.get(i - 1).getPlanPq() : 
							dayPlanPq.add(list.get(i - 1).getPlanPq() == null ? BigDecimal.ZERO : list.get(i - 1).getPlanPq()));
					BigDecimal dayActualPq = smConsPowerViewDetail.getDayActualPq();
					
					//当日有值（有日 才有累计）  累计 + 日
					if(dayActualPq != null){
						//最后存在的累计  累计 + 日
						lastActualPq = lastActualPq.add(dayActualPq);
						//当日累计 + 最后一次存在的累计
						smConsPowerViewDetail.setActualPq(lastActualPq);
					}else{
						smConsPowerViewDetail.setActualPq(null);
					}
				}
			}
		}
		//计算累计偏差率，日偏差率
		for(int i=0; i<list.size(); i++){
			SmConsPowerViewDetail smConsPowerViewDetail = list.get(i);
			if(smConsPowerViewDetail.getActualPq() != null && smConsPowerViewDetail.getPlanPq() != null && smConsPowerViewDetail.getPlanPq().doubleValue() != 0){
				BigDecimal deviationRate = (smConsPowerViewDetail.getActualPq().subtract(smConsPowerViewDetail.getPlanPq())).multiply(new BigDecimal(100)).divide(smConsPowerViewDetail.getPlanPq(), 2, RoundingMode.HALF_UP);
				smConsPowerViewDetail.setDeviationRate(deviationRate);
			}
			if(smConsPowerViewDetail.getDayActualPq() != null && smConsPowerViewDetail.getDayPlanPq() != null && smConsPowerViewDetail.getDayPlanPq().doubleValue() != 0){
				BigDecimal dayDeviationRate = (smConsPowerViewDetail.getDayActualPq().subtract(smConsPowerViewDetail.getDayPlanPq())).multiply(new BigDecimal(100)).divide(smConsPowerViewDetail.getDayPlanPq(), 2, RoundingMode.HALF_UP);
				smConsPowerViewDetail.setDayDeviationRate(dayDeviationRate);
			}
		}
		return list;
	}
	
	
	
	
	
	/**
	 * @Title: getSmConsPowerViewByPage2<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月所有合同用户用电情况<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * QueryResult<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月12日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月12日 下午1:15:00
	 * @since  1.0.0
	 */
	public QueryResult<SmConsPowerViewDetail> getSmConsPowerViewByPage2(SmConsPowerViewVo smConsPowerViewVo) throws Exception{
		QueryResult<SmConsPowerViewDetail> queryResult = new QueryResult<SmConsPowerViewDetail>();
		long total = this.getSmConsPowerViewCountByMonth(smConsPowerViewVo);
		List<SmConsPowerViewDetail> resultList = this.getResultListByParams(smConsPowerViewVo);
		queryResult.setTotal(total);
		queryResult.setData(resultList);
		return queryResult;
	}
	
	/**
	 * @Title: getTotalPqDataByMonth
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户总的计划电量和总用电量
	 * @param smConsPowerViewVo
	 * @return planPqTotal:当月计划电量总和    actualPqTotal；当月用电量总和	forecastPq：预测全月电量（新增 ——by LiXinze）
	 * Map<String,String>
	 * @throws Exception 
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午4:37:20
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年3月23日 下午4:37:20
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getTotalPqDataByMonth(SmConsPowerViewVo smConsPowerViewVo) throws Exception{
		Parameter.isFilterData.set(true);
		Map<String, String> totalData = (Map<String, String>) dao.getOneBySQL("smConsPowerView.sql.getTotalPqDataByMonth", smConsPowerViewVo);
		Parameter.isFilterData.set(false);

		//计算每个用户的预测电量（ym 按照抄表例日来取） 	——by LiXinze
		Map<String, Double> countMap = getConsCountListByParams(smConsPowerViewVo);
		//计算全月用电量预测
		double totalPq = 0;
		if(countMap != null && countMap.size() > 0){
			for(double pq : countMap.values()){
				totalPq += pq;
			}
		}
		if(totalData == null){
			totalData = new HashMap<String,String>();
		}
		totalData.put("forecastPq",String.format("%.3f", totalPq));
		return totalData;
	}
	
	/**
	 * @Title: getPowerViewDetailListForDayByMonth<br/>
	 * @Description: 根据用户ym(抄表年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户每天的总用电情况
	 * 							返回数据为每天一条，每条为所有用户当天的用电加和<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月13日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月13日 下午1:15:00
	 * @since  1.0.0
	 */
	public List<SmConsPowerViewDetail> getPowerViewDetailListForDayByMonth(SmConsPowerViewVo params){
		Parameter.isFilterData.set(true);
		@SuppressWarnings("unchecked")
		//得到的是按日期的所有用户累加的日用电量和日计划电量
		List<SmConsPowerViewDetail> list =  (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getPowerViewDetailListForDayByMonth", params);
		Parameter.isFilterData.set(false);
		
		/*	这种是上面从数据库取数据是按照用户每天一条数据，目的是方便每个用户和采集平台的数据整合，然后分别计算每个用户的累积电量，最后按日期累加每个用户的四个用电数据得到最终结果
		 * 现在上面sql暂时改为直接按日期累加每个用户的日用电量和日计划电量，然后在Java代码中计算累积计划电量和累积用电量
		 * Map<String, List<SmConsPowerViewDetail>> map = new HashMap<>();
		for(int i=0; i<list.size(); i++){
			//计算累计计划电量
			SmConsPowerViewDetail smConsPowerViewDetail = list.get(i);
			//设置实际用电量（累计用电量），设置为抄表示数减去初始值
			if(smConsPowerViewDetail.getActualPq() == null) {
				//从采集平台拿数据
			}
			if(!map.containsKey(smConsPowerViewDetail.getConsId())) {
				map.put(smConsPowerViewDetail.getConsId(), new ArrayList<SmConsPowerViewDetail>());
			}
			map.get(smConsPowerViewDetail.getConsId()).add(smConsPowerViewDetail);
		}
		List<SmConsPowerViewDetail> resultList = new ArrayList<>();
		Map<String, SmConsPowerViewDetail> dayPqMap = new HashMap<>();
		for (List<SmConsPowerViewDetail> detailList : map.values()) {  
			if(detailList != null && detailList.size() >0 ) {
				for (int i = 0; i < detailList.size(); i++) {
					SmConsPowerViewDetail detail = detailList.get(i);
					if(detail.getYmd() == null) {
						continue;
					}
					//因为是按照自然年月获取用户数据，则每个用户的累计计划电量和累计用电量是按照自然年月的每天电量累加得来
					if(i == 0) {//第一天的累计电量（包括计划和实际）  =  第一天的电量
						detail.setPlanPq(detail.getDayPlanPq());
						detail.setActualPq(detail.getDayActualPq());
					}else {		//随后的累计电量（包括计划和实际）  =  当天的电量 + 前一天的累计电量
						BigDecimal dayPlanPq = detail.getDayPlanPq();
						detail.setPlanPq(dayPlanPq == null ? detailList.get(i - 1).getPlanPq() : dayPlanPq.add(detailList.get(i - 1).getPlanPq() == null ? BigDecimal.ZERO : detailList.get(i - 1).getPlanPq()));
						BigDecimal dayActualPq = detail.getDayActualPq();
						detail.setActualPq(dayActualPq == null ? detailList.get(i - 1).getActualPq() : dayActualPq.add(detailList.get(i - 1).getActualPq() == null ? BigDecimal.ZERO : detailList.get(i - 1).getActualPq()));
					}
					//最终的一天一条的返回数据  = 累加所有用户当天的数据
					if(!dayPqMap.containsKey(detail.getYmd())) {	//如果还没有建立当日的电量信息，则新建并加入list和map
						SmConsPowerViewDetail resultDetail = new SmConsPowerViewDetail();
						resultDetail.setYmd(detail.getYmd());
						resultDetail.setActualPq( detail.getActualPq() != null ? detail.getActualPq() : BigDecimal.ZERO);
						resultDetail.setPlanPq(detail.getPlanPq() != null ? detail.getPlanPq() : BigDecimal.ZERO);
						resultDetail.setDayActualPq( detail.getDayActualPq() != null ? detail.getDayActualPq() : null);
						resultDetail.setDayPlanPq(detail.getDayPlanPq() != null ? detail.getDayPlanPq() : null);
						
						resultList.add(resultDetail);
						dayPqMap.put(detail.getYmd(), resultDetail);
					}else{
						SmConsPowerViewDetail resultDetail = dayPqMap.get(detail.getYmd());
						resultDetail.setActualPq( detail.getActualPq() != null ? detail.getActualPq().add(resultDetail.getActualPq() == null ? BigDecimal.ZERO : resultDetail.getActualPq()) : resultDetail.getActualPq());
						resultDetail.setPlanPq( detail.getPlanPq() != null ? detail.getPlanPq().add(resultDetail.getPlanPq() == null ? BigDecimal.ZERO : resultDetail.getPlanPq()) : resultDetail.getPlanPq());
						resultDetail.setDayPlanPq( detail.getDayPlanPq() != null ? detail.getDayPlanPq().add(resultDetail.getDayPlanPq() == null ? BigDecimal.ZERO : resultDetail.getDayPlanPq()) : resultDetail.getDayPlanPq());
						resultDetail.setDayActualPq( detail.getDayActualPq() != null ? detail.getDayActualPq().add(resultDetail.getDayActualPq() == null ? BigDecimal.ZERO : resultDetail.getDayActualPq()) : resultDetail.getDayActualPq());
					}
				}
			}
		}  */
		for (int i = 0; i < list.size(); i++) {
			SmConsPowerViewDetail detail = list.get(i);
			if(detail.getYmd() == null) {
				continue;
			}
			//因为是按照自然年月获取用户数据，则用户的累计计划电量和累计用电量是按照自然年月的每天电量累加得来
			if(i == 0) {//第一天的累计电量（包括计划和实际）  =  第一天的电量
				detail.setPlanPq(detail.getDayPlanPq());
				detail.setActualPq(detail.getDayActualPq());
			}else {		//随后的累计电量（包括计划和实际）  =  当天的电量 + 前一天的累计电量
				BigDecimal dayPlanPq = detail.getDayPlanPq();
				detail.setPlanPq(dayPlanPq == null ? list.get(i - 1).getPlanPq() : dayPlanPq.add(list.get(i - 1).getPlanPq() == null ? BigDecimal.ZERO : list.get(i - 1).getPlanPq()));
				BigDecimal dayActualPq = detail.getDayActualPq();
				detail.setActualPq(dayActualPq == null ? list.get(i - 1).getActualPq() : dayActualPq.add(list.get(i - 1).getActualPq() == null ? BigDecimal.ZERO : list.get(i - 1).getActualPq()));
			}
		}
		return list;
	}
	
	/**
	  * @Title: exportExcel
	  * @Description: 用电量导出
	  * @param voltCode
	  * @param elecTypeCode
	  * @param month
	  * @param request
	  * @param response
	  * void
	  * @throws Exception 
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月19日 上午9:28:44
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月19日 上午9:28:44
	  * @since  1.0.0
	 */
	@Override
	public void exportExcel(String voltCode, String elecTypeCode, String month, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取导出数据
		SmConsPowerViewVo smConsPowerViewVo = new SmConsPowerViewVo();
		smConsPowerViewVo.setElecTypeCode(elecTypeCode);
		smConsPowerViewVo.setVoltCode(voltCode);
		smConsPowerViewVo.setYm(month);
		smConsPowerViewVo.setPagingFlag(false);
		List<SmConsPowerViewDetail> resultList = getResultListByParams(smConsPowerViewVo);
		
		//获取模版文件
		String excelPath=request.getSession().getServletContext().getRealPath("/plugins/cloud-purchase-web/monitoring-platform/common/电量报表.xlsx");//模板路径
		response.reset();//去除空白行
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		//XX年XX月用户用电量分析表
		String newName = URLEncoder.encode(month.substring(0,4) + "年" + month.substring(5) + "月用户用电量分析表"+ ".xlsx","UTF-8"); //设置导出的文件名称
		request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
	   	response.setHeader("Content-Disposition","attachment;filename=\"" + newName + "\"");  
	   	InputStream fio = new FileInputStream(excelPath);//将模板读入流
	   	ServletOutputStream out = response.getOutputStream();
	   	try {  
	       	XSSFWorkbook workbook=new XSSFWorkbook(fio);//创建excel(xlsx格式)
			XSSFSheet sheet=workbook.getSheetAt(0);     //创建工作薄sheet
			// 数据 Excel 所需字段
           String[] str = {"ConsName","ElecTypeName","VoltCodeName","Date","PlanPq","PlanPqToday","ActualPq","DeviationRate","ForecastPq","ForecastRate"};
			int cellCount = 0;//第一列开始		getName（）；
			//用户行合并,根据用户年份合并
			for(int i = 0 ; i < resultList.size() ; i++){
				SmConsPowerViewDetail detail = resultList.get(i);
				if(detail.getMinDate() != null && !"".equals(detail.getMinDate()) && detail.getMaxDate() != null && !"".equals(detail.getMaxDate())){
					detail.setDate(detail.getMinDate() + "--" + detail.getMaxDate());
				}else{
					detail.setDate("");
				}
				XSSFRow row = sheet.createRow(i+1);						//创建行
				XSSFCellStyle borderStyle = workbook.createCellStyle();			//创建单元格样式对象
				//设置边框样式
				borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
				borderStyle.setBorderTop(CellStyle.BORDER_THIN);
				borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
				borderStyle.setBorderRight(CellStyle.BORDER_THIN);
				//设置单元格边框颜色
				borderStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setTopBorderColor(new XSSFColor(java.awt.Color.black));   
	            borderStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.black)); 
	            borderStyle.setRightBorderColor(new XSSFColor(java.awt.Color.black)); 
	            //居中   
	            borderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	            borderStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	            //自动换行
	            borderStyle.setWrapText(true);
	            //创建列
	            for(int j = 0 ; j < str.length ; j++){
	            	Cell borderCell = row.createCell(cellCount++);					//创建列
	            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
	            	
	            	Class<? extends SmConsPowerViewDetail> importVoClass = detail.getClass();	//拼接get方法，取对象里的值
	            	Method method = importVoClass.getMethod("get"+str[j]);
	            	
           		if( method.invoke(detail) != null){
           			String value = method.invoke(detail).toString();
           			borderCell.setCellValue(value);							//列数据
           		}else{
           			borderCell.setCellValue("");							//列数据
           		}
	            }
	            cellCount = 0;
	            if(i == resultList.size() -1){
	            	CellRangeAddress region = new CellRangeAddress(i+1,i+1,0,3);
	            	sheet.addMergedRegion(region);
	            }
			}
			workbook.write(out);
           out.flush();  
       } catch (FileNotFoundException e) {
			System.out.println("用户用电量分析模板没有找到");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("输出流异常");
			e.printStackTrace();
		}finally {
			try {
				fio.close();
				out.close();
			} catch (IOException e) {
				System.out.println("流关闭异常");
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * @Title: getSmConsPowerViewCountByMonth<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月所有合同用户数量<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月12日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月12日 下午1:15:00
	 * @since  1.0.0
	 */
	private Integer getSmConsPowerViewCountByMonth(SmConsPowerViewVo smConsPowerViewVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsPowerView.sql.getSmConsPowerViewCountByMonth",smConsPowerViewVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	  * @Title: getResultListByParams
	  * @Description: 获取用电量结果
	  * @param smConsPowerViewVo
	  * @return List<SmConsPowerViewDetail>
	  * @throws Exception
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月19日 上午10:24:14
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月19日 上午10:24:14
	  * @since  1.0.0
	 */
	private List<SmConsPowerViewDetail> getResultListByParams(SmConsPowerViewVo smConsPowerViewVo) throws Exception {
		List<SmConsPowerViewDetail> smConsPowerViewDetailList = this.getPowerViewDetailListByMonth(smConsPowerViewVo);
		//获取用户全月用电量预测
		Map<String,Double> map = this.getConsCountListByParams(smConsPowerViewVo);
		//用户全月用电量预测、预测偏差率赋值
		for(SmConsPowerViewDetail smConsPowerViewDetail : smConsPowerViewDetailList){
			if(map.containsKey(smConsPowerViewDetail.getConsId())){
				smConsPowerViewDetail.setForecastPq(String.format("%.3f", map.get(smConsPowerViewDetail.getConsId())));		//全月用电量预测
				//预测偏差率 =（预测全月用电量-代理电量）/代理电量*100%
				if(smConsPowerViewDetail.getPlanPq().compareTo(BigDecimal.ZERO) != 0){
					double rate = (map.get(smConsPowerViewDetail.getConsId()) - smConsPowerViewDetail.getPlanPq().doubleValue()) / smConsPowerViewDetail.getPlanPq().doubleValue() * 100;
					smConsPowerViewDetail.setForecastRate(String.format("%.2f", rate));
				}
			}
		}
		//获取合计信息
		SmConsPowerViewDetail totalDetail = getTotalDetailByParams(smConsPowerViewVo,map);
		smConsPowerViewDetailList.add(totalDetail);
		return smConsPowerViewDetailList;
	}
	
	
	
	
	/**
	 * @Title: getPowerViewDetailListByMonth<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户用电情况<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * List<SmConsPowerViewDetail><br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月12日 下午1:15:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月12日 下午1:15:00
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsPowerViewDetail> getPowerViewDetailListByMonth(SmConsPowerViewVo params) throws Exception{
		Parameter.isFilterData.set(true);
		List<SmConsPowerViewDetail> list = (List<SmConsPowerViewDetail>) dao.getBySql("smConsPowerView.sql.getPowerViewDetailListByMonth", params);
		Parameter.isFilterData.set(false);
		try {
			ConvertListUtil.convert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<list.size(); i++){
			SmConsPowerViewDetail smConsPowerViewDetail = list.get(i);
//			if(smConsPowerViewDetail.getActualPq() == null) {
				//从采集平台拿数据
//			}
			if(smConsPowerViewDetail.getPlanPq() != null && smConsPowerViewDetail.getPlanPq().compareTo(BigDecimal.ZERO) ==1 ) {		//计划电量
				if(smConsPowerViewDetail.getActualPq() != null && smConsPowerViewDetail.getActualPq().compareTo(BigDecimal.ZERO) ==1 ) {	//累计用电量
					//完成度
					//smConsPowerViewDetail.setCompleteRate(smConsPowerViewDetail.getActualPq().divide(smConsPowerViewDetail.getPlanPq(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
					if(smConsPowerViewDetail.getPlanPqToday() != null && smConsPowerViewDetail.getPlanPqToday().compareTo(BigDecimal.ZERO) ==1 ) {	//当天的计划电量
						//偏差率 （已用-计划）/计划
						smConsPowerViewDetail.setDeviationRate((smConsPowerViewDetail.getActualPq().subtract(smConsPowerViewDetail.getPlanPqToday())).divide(smConsPowerViewDetail.getPlanPqToday(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
					}
				}
			}
		}
		return list;
	}
	/**
	  * @Title: getConsCountListByParams
	  * @Description: 计算每个用户的预测电量
	  * @param smConsPowerViewVo
	  * @return Map<String, Double>	(key:用户id	value:用户预测电量)
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月16日 下午1:11:23
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月16日 下午1:11:23
	 * @throws Exception 
	  * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Double> getConsCountListByParams(SmConsPowerViewVo smConsPowerViewVo) throws Exception {
		Map<String,Double> forecastMap = new HashMap<String,Double>();
		/*
		 * 查询用户用电信息（ym 按照抄表例日来取）
		 * id：用户id, beginTime：抄表起始时间, endTime：抄表结束时间(yyyy-MM-dd HH:mm:ss), pq：用户实际用电量, start：例日开始时间,end：例日结束时间(yyyy-MM-dd)
		 * lastDate: 上月结算日抄表时间
		 */
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		smConsPowerViewVo.setLoginUserId(loginUserId);
		List<Map<String, Object>> countList = (List<Map<String, Object>>) dao.getBySql("smConsPowerView.sql.getForecastPqByParams", smConsPowerViewVo);
		if(countList != null && countList.size() > 0){
			//计算每个用户的预测电量, （实际用电量/实际用电时间）* 该月总时间	精确到小时
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			for(Map<String, Object> map : countList){
				double pq = Double.parseDouble(map.get("pq").toString());	//实际用电量
				Date begin;										//用电起始时间
				Date end = sdf1.parse(map.get("endTime").toString());		//用电结束时间
				//判断用电起始时间：上月结算日有抄表示数，取上月结算日时间，上月结算日没示数，取本月起始时间
				if(map.get("lastDate") != null && !"".equals(map.get("lastDate"))){	//取上月结算日时间
					begin = sdf1.parse(map.get("lastDate").toString());
				}else{	//取本月起始时间
					begin = sdf1.parse(map.get("beginTime").toString());
				}
				double hour = ( end.getTime() - begin.getTime() ) / (1000 * 60 * 60);	//实际用电时间
				double totalHour = ( sdf2.parse(map.get("end").toString()).getTime() - sdf2.parse(map.get("start").toString()).getTime() ) / (1000 * 60 * 60) + 24;	//该月总时间
				if(hour == 0) {
					continue;
				}
				double forecastPq = pq / hour * totalHour;
				forecastMap.put(map.get("id").toString(), forecastPq);
			}
		}
		return forecastMap;
	}
	/**
	  * @Title: getTotalDetailByParams
	  * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户用电情况合计
	  * @param smConsPowerViewVo
	  * @param map 用户全月用电量预测
	  * @return SmConsPowerViewDetail
	  * @throws Exception 
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月16日 下午5:58:07
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月16日 下午5:58:07
	  * @since  1.0.0
	 */
	private SmConsPowerViewDetail getTotalDetailByParams(SmConsPowerViewVo smConsPowerViewVo, Map<String, Double> map) throws Exception {
		SmConsPowerViewDetail smConsPowerViewDetail = new SmConsPowerViewDetail();
		smConsPowerViewDetail.setConsName("合计");
		smConsPowerViewDetail.setPlanPq(BigDecimal.ZERO);		//代理电量
		smConsPowerViewDetail.setPlanPqToday(BigDecimal.ZERO);	//计划累计用电量
		smConsPowerViewDetail.setActualPq(BigDecimal.ZERO);		//累计用电量
		smConsPowerViewVo.setPagingFlag(false);
		List<SmConsPowerViewDetail> smConsPowerViewDetailList = this.getPowerViewDetailListByMonth(smConsPowerViewVo);
		/*
		 * 计算合计值：
		 * 合计偏差率 =（合计累计用电量-合计计划累计用电量）/合计计划累计用电量 * 100%
		 * 合计预测偏差率=（合计预测全月电量-合计代理电量）/合计代理电量 * 100%
		 */
		if(smConsPowerViewDetailList != null && smConsPowerViewDetailList.size() > 0){
			for(SmConsPowerViewDetail detail : smConsPowerViewDetailList){
				BigDecimal planPq = smConsPowerViewDetail.getPlanPq().add(detail.getPlanPq() != null ? detail.getPlanPq() : BigDecimal.ZERO);
				BigDecimal planPqToday = smConsPowerViewDetail.getPlanPqToday().add(detail.getPlanPqToday() != null ? detail.getPlanPqToday() : BigDecimal.ZERO);
				BigDecimal actualPq = smConsPowerViewDetail.getActualPq().add(detail.getActualPq() != null ? detail.getActualPq() : BigDecimal.ZERO);
				smConsPowerViewDetail.setPlanPq(planPq);
				smConsPowerViewDetail.setPlanPqToday(planPqToday);
				smConsPowerViewDetail.setActualPq(actualPq);
			}
		}
		//计算全月用电量预测
		double totalPq = 0;
		if(map != null && map.size() > 0){
			for(double pq : map.values()){
				totalPq += pq;
			}
		}
		smConsPowerViewDetail.setForecastPq(String.format("%.3f", totalPq));
		if(smConsPowerViewDetail.getPlanPq() != null && smConsPowerViewDetail.getPlanPq().compareTo(BigDecimal.ZERO) ==1 ) {		//计划电量
			//合计预测偏差率
			if(totalPq != 0){
				BigDecimal forecastRate = BigDecimal.valueOf(totalPq).subtract(smConsPowerViewDetail.getPlanPq()).divide(smConsPowerViewDetail.getPlanPq(), 3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				smConsPowerViewDetail.setForecastRate(forecastRate.toString());
			}
			if(smConsPowerViewDetail.getActualPq() != null && smConsPowerViewDetail.getActualPq().compareTo(BigDecimal.ZERO) ==1 ) {	//累计用电量
				if(smConsPowerViewDetail.getPlanPqToday() != null && smConsPowerViewDetail.getPlanPqToday().compareTo(BigDecimal.ZERO) ==1 ) {	//当天的计划电量
					//合计偏差率
					smConsPowerViewDetail.setDeviationRate((smConsPowerViewDetail.getActualPq().subtract(smConsPowerViewDetail.getPlanPqToday())).divide(smConsPowerViewDetail.getPlanPqToday(),3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
			}
		}
		return smConsPowerViewDetail;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @Title: updatePowerViewList
	 * @Description: 根据需要更新的数据和数据库数据对比，然后更新数据，并计算偏差预警
	 * 						powerViewList中可能存在entityList的元素对象，这样的元素是被原始更新数据影响了日用电量，所以也需要更新
	 * @param powerViewList		需要更新的数据
	 * @param entityList 		数据库数据
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月18日 下午7:25:16
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月18日 下午7:25:16
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@Transactional
	private void updatePowerViewList(List<SmConsPowerView> powerViewList, List<SmConsPowerView> entityList) throws ParseException {
		if(powerViewList.size() == 0) {
			return;
		}
		String consId = powerViewList.get(0).getConsId();
		
		List<SmConsPowerView> addList = new ArrayList<>();
		List<SmConsPowerView> updateList = new ArrayList<>();
		List<String> deleteIdList = new ArrayList<>();
		//把数据库中数据放入map中，方便查询
		Map<String, SmConsPowerView> entityMap = new HashMap<>();
		if(entityList != null && entityList.size() != 0) {
			for (SmConsPowerView entity : entityList) {
				entityMap.put(entity.getYmd(), entity);
			}
		}
		//要更新的日期list，用于删除的偏差预警
		List<String> ymdList = new ArrayList<>();
		//分类新增和更新操作
		for (int i = 0; i < powerViewList.size(); i++) {
			SmConsPowerView smConsPowerView = powerViewList.get(i);
			ymdList.add(smConsPowerView.getYmd());
			//如果在数据库中存在此用电计划
			if(entityMap.containsKey(smConsPowerView.getYmd())) {
				SmConsPowerView entity = entityMap.get(smConsPowerView.getYmd());
				//如果重新计算的日用电量和累计用电量和数据库中的相同，则无需更新
				if(entity != smConsPowerView 
						&& (entity.getActualPq() == smConsPowerView.getActualPq() || (entity.getActualPq() != null && entity.getActualPq().equals(smConsPowerView.getActualPq())))
						&&(entity.getDayActualPq() == smConsPowerView.getDayActualPq() || (entity.getDayActualPq() != null && entity.getDayActualPq().equals(smConsPowerView.getDayActualPq())))
						) {
					ymdList.remove(ymdList.size()-1);
					continue;
				}
				entity.setActualPq(smConsPowerView.getActualPq());
				entity.setDayActualPq(smConsPowerView.getDayActualPq());
				//要更新的数据为逻辑删除的数据，而且更新后实际用电量和日用电量为null，则此数据无意义，直接物理删除
				if(entity.getActualPq() == null && entity.getDayActualPq() == null && "1".equals(entity.getIsDelete())) {
					deleteIdList.add(entity.getId());
				}else {
					updateList.add(entity);
				}
			}else {//如果在数据库中不存在此用电计划
				if(smConsPowerView.getActualPq() != null || smConsPowerView.getDayActualPq() != null) {
					addList.add(smConsPowerView);
				}
			}
		}
		if(deleteIdList.size() != 0) {
			dao.deleteBySql("smConsPowerView.sql.deletePowerViewByIdList", deleteIdList);
		}
		if(updateList.size() != 0) {
			dao.saveList(updateList);
		}
		//批量保存
		if(addList.size() != 0) {
			String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
			String loginUserOrgCode = SystemServiceUtil.getLoginUserInfo().getOrgId();	//4a4caba00e884cbe966e1bd4239dc7e7
			Map<String, Object> addParams = new HashMap<>();
			addParams.put("loginUserId", loginUserId);
			addParams.put("orgNo", loginUserOrgCode);
			addParams.put("addList", addList);
			//批量保存
			dao.saveBySql("smConsPowerView.sql.addPowerViewList", addParams);
//			dao.saveListBatch(addList);
		}
		
		//根据consId和ymdList删除偏差考核
		smDeviationInfoService.deleteByConsIdAndYmdList(consId, ymdList);
		
		//计算updateList的偏差预警，
		smDeviationInfoService.calculateDeviationByPowerViewList(updateList);
	}
}
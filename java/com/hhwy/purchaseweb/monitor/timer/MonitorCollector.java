package com.hhwy.purchaseweb.monitor.timer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.core.util.RemoteProcedureCall;
import com.hhwy.framework.container.AppContainer;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.monitorplatform.all.domain.MonitorBusinessDataCollectDetail;
import com.hhwy.monitorplatform.all.interfaces.MonitorInterface;
import com.hhwy.quartz.core.AbsJob;
import com.hhwy.selling.domain.SmConsPowerView;
@Component
public class MonitorCollector extends AbsJob{
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void executeJob(JobExecutionContext arg0) {
		MonitorInterface monitorInterface = null;
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		System.out.println("定时任务启动");
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		if(dao == null) {
			dao = AppContainer.getDao();
		}
		List<Map<String, String>> databaseList = (List<Map<String, String>>) dao.getBySql("timer.sql.getDatabaseNameList", null); 
		if(databaseList == null || databaseList.size() == 0) {
			return;
		}
		//获取昨天的日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date yestoday = new Date(calendar.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String yestodayStr = format.format(yestoday); // "2018-05-11";				//昨天的日期，yyyy-MM-dd格式
		String yestodayStr2 = yestodayStr.replaceAll("-", ""); // "20180511";		//昨天的日期，yyyyMMdd格式
		System.out.println("=======================" + yestodayStr2 + "========================");
		calendar.add(Calendar.DATE, -1);
		Date beforYestoday = new Date(calendar.getTimeInMillis());
		String beforYestodayStr = format.format(beforYestoday).replaceAll("-", "");//前天的日期yyyyMMdd格式
		//所有采集平台的用户id
		for (Map<String, String> databaseMap : databaseList) {
			String databaseName = databaseMap.get("companyDatabase");
			String companyDomain = databaseMap.get("companyDomain");
			System.out.println("-----------------------------------------------------------");
			System.out.println("定时任务执行到：" + databaseName);
			System.out.println("-----------------------------------------------------------");
			Map<String, String> databaseNameMap = new HashMap<>();
			databaseNameMap.put("databaseName", databaseName);
			//获取当库中所有云售电用户和采集用户的关联关系,及云售电用户的orgNo
			List<Map<String, String>> userIdList = (List<Map<String, String>>) dao.getBySql("timer.sql.getConsUserRelaList", databaseNameMap); 
			if(userIdList == null || userIdList.size() == 0) {
				continue;
			}
			//采集用户id为key，云售电用户id为value
			Map<String, String> userConsRela = new HashMap<>();
			//云售电用户id为key,org_no为value
			Map<String, String> consIdOrgNoMap = new HashMap<>();
			for (Map<String, String> map : userIdList) {
				userConsRela.put(map.get("emUserId"),map.get("consId"));
				consIdOrgNoMap.put(map.get("consId"),map.get("orgNo"));
			}
			if(userConsRela.size() == 0) {
				continue;
			}
			try {
				//云售电用户id和昨天的日用电量的map
				Map<String, BigDecimal> consIdDayActualPqmap = new HashMap<>();
				monitorInterface = RemoteProcedureCall.getInstancce().getService(MonitorInterface.class,companyDomain);
				//因暂时business-core要回退以前的版本，所以现在先用以前的接口
				//monitorInterface = RemoteProcedureCall.getInstancce().getService(MonitorInterface.class);
				for (String emUserId : userConsRela.keySet()) {
					Map<String, Map<String, MonitorBusinessDataCollectDetail>> dataMap  = monitorInterface.getElecByUserAndTime(emUserId,yestodayStr,yestodayStr);
					System.out.println("从综合能效分析平台获取的数据："+ dataMap);
					if(dataMap == null || dataMap.size() == 0) {
						continue;
					}
					BigDecimal totalPq = null;
					for(String key : dataMap.keySet()){
						if(dataMap.get(key).containsKey(yestodayStr)){
							MonitorBusinessDataCollectDetail monitorBusinessDataCollectDetail = dataMap.get(key).get(yestodayStr);
							if(monitorBusinessDataCollectDetail != null && monitorBusinessDataCollectDetail.getTotalElectricQuantity() != null){
								if(totalPq == null){
									totalPq = BigDecimal.ZERO;
								}
								totalPq = totalPq.add(new BigDecimal(monitorBusinessDataCollectDetail.getTotalElectricQuantity()));
							}
						}
					}
					if(totalPq != null) {
						consIdDayActualPqmap.put(userConsRela.get(emUserId), totalPq);
					}
				}
				if(consIdDayActualPqmap.size() == 0) {
					continue;
				}
				//根据有返回数据的云售电consId列表 获取用户昨天的电量收集数据
				Map<String, Object> params = new HashMap<>();
				params.put("databaseName", databaseName);
				params.put("ymd", yestodayStr2);		//昨天的日期
				params.put("consIds", consIdDayActualPqmap.keySet());
				//获取昨天的用户电量收集数据
				List<SmConsPowerView> yestodayViewList = (List<SmConsPowerView>) dao.getBySql("timer.sql.getPowerViewDetailListByYmdAndConsIds", params);
				//从数据库中获取的用户昨天的用电情况信息map
				Map<String, SmConsPowerView> consIdViewMap = new HashMap<>();
				for (SmConsPowerView smConsPowerView : yestodayViewList) {
					consIdViewMap.put(smConsPowerView.getConsId(), smConsPowerView);
				}
				
				params.put("ymd", beforYestodayStr);	//前天的日期
				//获取ymd之前的用户累积用电量最大值及对应日期，然后加上昨天的日用电量作为昨天的累计用电量
				List<SmConsPowerView> beforYestodayViewList = (List<SmConsPowerView>) dao.getBySql("timer.sql.getLastPowerViewListByConsIds", params);
				//从数据库中获取的用户之前的累计用电情况map
				Map<String, SmConsPowerView> consIdActualPqMap = new HashMap<>();
				for (SmConsPowerView smConsPowerView : beforYestodayViewList) {
					consIdActualPqMap.put(smConsPowerView.getConsId(), smConsPowerView);
				}
				
				for (String consId : consIdDayActualPqmap.keySet()) {
					SmConsPowerView consPowerViewEntity = consIdViewMap.get(consId);
					//获取此用户的用电年月的开始日期与结束日期和天数,主要是判断前天是不是超标例日
					Map<String, Object> result = this.getDateAndDays(consId, yestodayStr2.substring(0, 6),databaseName);
					String beginDate = format.format((Date)result.get("startDate")).replaceAll("-", "");
					String endDate = format.format((Date)result.get("endDate")).replaceAll("-", "");
					//本用户前天及之前日期的最新的累计用电量
					BigDecimal lastActualPq = BigDecimal.ZERO;
					//如果存在最新的累计用电量，并且时间在本用电年月内
					if(consIdActualPqMap.get(consId) != null && beginDate.compareTo(consIdActualPqMap.get(consId).getYmd()) != 1 && 
							endDate.compareTo( consIdActualPqMap.get(consId).getYmd()) != -1) {
						lastActualPq = consIdActualPqMap.get(consId).getActualPq();
					}
					
					//要保存或者更新的实体类 以及 目标数据库名称
					Map<String, Object> dbNameAndEntity = new HashMap<>();
					dbNameAndEntity.put("databaseName", databaseName);
					if(consPowerViewEntity == null) {
						consPowerViewEntity = new SmConsPowerView();
						consPowerViewEntity.setId(PlatformTools.getID());
						consPowerViewEntity.setConsId(consId);
						consPowerViewEntity.setYmd(yestodayStr2);
						consPowerViewEntity.setOrgNo(consIdOrgNoMap.get(consId));
						consPowerViewEntity.setDayActualPq(consIdDayActualPqmap.get(consId));
						//计算昨天的累计用电量
						//如果昨天是用电年月的第一天，则用户的累积用电量和日用电量一致
						if(yestodayStr2.equals(beginDate) || beforYestodayStr.equals(endDate)) {
							consPowerViewEntity.setActualPq(consIdDayActualPqmap.get(consId));
						}else{
							consPowerViewEntity.setActualPq(lastActualPq.add(consIdDayActualPqmap.get(consId)));
						}
						consPowerViewEntity.setCreateTime(new Timestamp(new Date().getTime()));
						consPowerViewEntity.setUpdateTime(new Timestamp(new Date().getTime()));
						dbNameAndEntity.put("entity", consPowerViewEntity);
						dao.saveBySql("timer.sql.savePowerView", dbNameAndEntity);
					}else {
						//计算昨天的累计用电量,如果表中已存在日用电量和累计用电量，则认为是手工维护的，则不覆盖
						if(consPowerViewEntity.getActualPq() != null && consPowerViewEntity.getDayActualPq() != null) {
//							BigDecimal beforYestodayActualPq = consPowerViewEntity.getActualPq().subtract(consPowerViewEntity.getDayActualPq());
//							consPowerViewEntity.setActualPq(beforYestodayActualPq.add(consIdDayActualPqmap.get(consId)));
							continue;
						}else if(yestodayStr2.equals(beginDate) || beforYestodayStr.equals(endDate)) {
							//如果昨天是用电年月的第一天，则用户的累积用电量和日用电量一致
							consPowerViewEntity.setActualPq(consIdDayActualPqmap.get(consId));
						}else{
							consPowerViewEntity.setActualPq(lastActualPq.add(consIdDayActualPqmap.get(consId)));
						}
						consPowerViewEntity.setDayActualPq(consIdDayActualPqmap.get(consId));
						consPowerViewEntity.setUpdateTime(new Timestamp(new Date().getTime()));
						dbNameAndEntity.put("entity", consPowerViewEntity);
						dao.updateBySql("timer.sql.updatePowerView", dbNameAndEntity);
					}
					System.out.println("更新的云售电用户采集数据："+consPowerViewEntity);
				}
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("定时任务关闭");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
				System.out.println("-----------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Title: getDateAndDays<br/>
	 * @Description: 根据用户id、ym、dbName，返回用电年月的开始日期与结束日期和天数<br/>
	 * @param consId 用户id
	 * @param ym 用电年月
	 * @param dbName 数据库名称
	 * @return Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1),value类型依次为：Date、Date、long<br/>
	 * @throws Exception <br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月15日 下午4:42:53
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月15日 下午4:42:53
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getDateAndDays(String consId, String ym, String dbName) throws Exception{
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("consId", consId);
		param.put("ym", ym);
		param.put("dbName", dbName);
		
		String usuallyDate = "";	//例日
		
		//查询例日数据，查询的例日是用电年月ym 之前日期的最近一次例日
		List<Map<String, String>> list = (List<Map<String, String>>) dao.getBySql("timer.sql.getUsuallyDateByConsId", param);
		if(list != null && list.size() > 0){
			usuallyDate = list.get(0).get("meterReadDate");
		}
		
		//时间计算
		Date beginDate = null;  //开始时间
		
		long days = 0; //天数
		
		Date usuallyDate2 = null;
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
		Date ym1 = format1.parse(ym);
        
		//计算用电月最后一天
        Calendar c1 = Calendar.getInstance();
        c1.setTime(ym1);
        int maxday = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		//判断是否有例日
		if(usuallyDate == null || usuallyDate.equals("")){
			//如果没有例日，从本月01号开始
			beginDate = format.parse(ym+"01");
			
			Calendar c = Calendar.getInstance();  
	        c.setTime(beginDate);  
	        c.add(Calendar.DAY_OF_MONTH, -1); //-1天  
	        
	        usuallyDate2 = c.getTime();
		}else{
			//有例日，取上个月例日加一天为开始日期
			int usuallyDay = Integer.parseInt(usuallyDate.substring(6));
			c1.add(Calendar.MONTH, -1);
			int lmaxday = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
			Date lastMonth = c1.getTime();
			String lastYm = format1.format(lastMonth);
			
			if(lmaxday > usuallyDay){
				lastYm = lastYm + usuallyDate.substring(6);
			}else{
				lastYm = lastYm + lmaxday;
			}
			
			usuallyDate2 = format.parse(lastYm);
			Calendar c = Calendar.getInstance();  
	        c.setTime(usuallyDate2);  
	        c.add(Calendar.DAY_OF_MONTH, 1); //+1天  
	        
	        beginDate = c.getTime();
		}
		
		String endDay = null;
		Date endDate = null;
		List<Map<String, String>> list1 = (List<Map<String, String>>) dao.getBySql("timer.sql.getNowUsuallyDateByConsId", param);
		if(list1 != null && list1.size() > 0){
			endDay = list1.get(0).get("meterReadDate");
			endDate = format.parse(endDay); //结束时间
		}
        
        if(endDate != null){	//当前月是否有例日
        	//当前月有例日
        	days = (endDate.getTime() - beginDate.getTime()) / (1000*3600*24);
        }else{
        	//当前月没有例日
        	if(usuallyDate == null || usuallyDate.equals("")){	//上个月是否有例日，如果有套用上月例日
        		//上个月没有例日
        		endDate = format.parse(ym+maxday);
        		days = maxday;
        	}else{
        		//上个月有例日
        		int usuallyDay = Integer.parseInt(usuallyDate.substring(6));
        		int nowUsuallyDay = 0;
        		if(maxday > usuallyDay){
        			nowUsuallyDay = usuallyDay;
        		}else{
        			nowUsuallyDay = maxday;
        		}
        		endDate = format.parse(ym + nowUsuallyDay);
        		days = (endDate.getTime() - beginDate.getTime()) / (1000*3600*24);
        	}
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("startDate", beginDate);
        result.put("endDate", endDate);
        result.put("days", days+1);
		return result;
	}
}

package com.hhwy.purchaseweb.monitor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.core.util.RemoteProcedureCall;
import com.hhwy.code.page.list.service.IExportService;
import com.hhwy.collectionplatform.all.domain.EmbusinessDataCollectDetail;
import com.hhwy.collectionplatform.all.interfaces.RealDataInterface;
import com.hhwy.collectionplatform.all.util.Paging;
import com.hhwy.collectionplatform.all.util.PlatformResult;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.monitor.domain.Usermanager;
import com.hhwy.purchaseweb.monitor.service.UsermanagerService;

/**
 * UsermanagerService
 * @author wangchaochao
 * @date 2017-08-24
 * @version 1.0
 */
@Component
public class UsermanagerServiceImpl implements UsermanagerService {

	public static final Logger log = LoggerFactory.getLogger(UsermanagerServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	@Autowired
	IExportService iexportService;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	private RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);  
	
	/**
	 * @Title: getEmbusinessDataCollectDetail<br/>
	 * @Description: 查询采集电量<br/>
	 * @param paging
	 * @param deviceIds
	 * @param yearOrMonth
	 * @param dateFlag
	 * @return <br/>
	 * List<EmbusinessDataCollectDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月25日 下午5:12:10
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月25日 下午5:12:10
	 * @since  1.0.0
	 */
	public PlatformResult<EmbusinessDataCollectDetail> getEmbusinessDataCollectDetail(Paging paging, List<String> deviceIds, String yearOrMonth, String dateFlag){
		
		PlatformResult<EmbusinessDataCollectDetail> platformResult = null;
		if(paging != null && deviceIds != null && deviceIds.size() > 0 && yearOrMonth != null && !yearOrMonth.equals("") && dateFlag != null && !dateFlag.equals("")){
			platformResult= realDataInterface.getEnergyInfoByDeviceIds(paging, deviceIds, yearOrMonth,dateFlag);
		}
		return platformResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usermanager> exportPower(Map<String, Object> param)
			throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		map.put("exportName", "电量报表");
		map.put("cols", "dataDate,meterCode,totalElectricQuantity,timeinterval01,timeinterval02,timeinterval03,timeinterval04,timeinterval05");
		map.put("colsDesc", "日期,电表,总电量,尖峰电量,峰电量,平电量,谷电量,低谷电量");
		map.put("camelDesc", "true");
		Parameter.isFilterData.set(true);
		list = (List<Map<String, Object>>) dao.getBySql("com.user.collect.getExportData", param);
		Parameter.isFilterData.set(false);
		iexportService.exportData(map, list);
		return null;
	}

	/**
	 * @Title: getOnePpaConsumerID
	 * @Description: 查询一个ym当月为合同用户的用户
	 * @param ym    yyyy-MM 格式
	 * @return
	 * @throws Exception 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午8:20:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月23日 下午8:20:20
	 * @since  1.0.0
	 */
	@Override
	public String getOnePpaConsumerID(String ym) throws Exception {
		if(ym == null || ym.length() != 7) {
			return null;
		}
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsPowerView.sql.getOnePpaConsumerID", ym);
		Parameter.isFilterData.set(false);
		if(result != null) {
			return result.toString();
		}else {
			return "";
		}
	}
}
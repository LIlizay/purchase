 package com.hhwy.purchaseweb.deviationcheck.scmpcount.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.core.util.RemoteProcedureCall;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.collectionplatform.all.domain.EmbusinessDataCollectDetail;
import com.hhwy.collectionplatform.all.interfaces.RealDataInterface;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountDetail;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountVo;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.service.ScMpCountService;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.service.SmConsPowerViewService;
import com.hhwy.selling.domain.ScMpCount;
import com.hhwy.selling.domain.ScMpInfo;
import com.hhwy.selling.domain.SmConsPowerView;

/**
 * <b>类 名 称：</b>ScMpCountServiceImpl<br/>
 * <b>类 描 述：</b><br/>  表计示数的service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月13日 下午3:32:48<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class ScMpCountServiceImpl implements ScMpCountService {

	public static final Logger log = LoggerFactory.getLogger(ScMpCountServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	/**
	 * scConsDateService		用户例日维护service
	 */
	@Autowired
	private ScConsDateService scConsDateService;
	
	/**
	 * smConsPowerViewService	用电计划service
	 */
	@Autowired
	private SmConsPowerViewService smConsPowerViewService;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Title: getScMpCountByPage
	 * @Description: 分页获取对象ScMpCount，用于list页面展示数据
	 * @param scMpCountVo
	 * @return
	 * @throws Exception 
	 * QueryResult<ScMpCount>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午3:35:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午3:35:49
	 * @since  1.0.0
	 */
	public QueryResult<ScMpCountDetail> getScMpCountByPage(ScMpCountVo scMpCountVo) throws Exception{
		QueryResult<ScMpCountDetail> queryResult = new QueryResult<ScMpCountDetail>();
		long total = getScMpCountCountByParams(scMpCountVo);
		List<ScMpCountDetail> scMpCountList = getScMpCountListByParams(scMpCountVo);
		queryResult.setTotal(total);
		queryResult.setData(scMpCountList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScMpCount数量，用于list页面展示数据
	 * @param ScMpCountVo
	 * @return Integer
	 */
	public Integer getScMpCountCountByParams(ScMpCountVo scMpCountVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scMpCount.sql.getScMpCountCountByParams",scMpCountVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScMpCount列表，用于list页面展示数据
	 * @param ScMpCountVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScMpCountDetail> getScMpCountListByParams(ScMpCountVo scMpCountVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scMpCountVo == null){
			scMpCountVo = new ScMpCountVo();
		}
		Parameter.isFilterData.set(true);
		List<ScMpCountDetail> scMpCountList = (List<ScMpCountDetail>)dao.getBySql("scMpCount.sql.getScMpCountListByParams",scMpCountVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scMpCountList);
		return scMpCountList;
	}
	
	/**
	 * @Title: getScMpCountListById
	 * @Description: 根据 电表id(即计量点id) 分页获取表计示数列表, 在edit和detail的 可编辑/展示 表格处用到
	 * @param scMpCountVo
	 * @return 
	 * QueryResult<ScMpCount>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午5:14:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午5:14:23
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public QueryResult<ScMpCountDetail> getScMpCountListById(ScMpCountVo scMpCountVo) throws Exception{
		QueryResult<ScMpCountDetail> queryResult = new QueryResult<ScMpCountDetail>();
		@SuppressWarnings("unchecked")
		List<ScMpCountDetail> list = (List<ScMpCountDetail>) dao.getBySql("scMpCount.sql.getScMpCountListByMpId", scMpCountVo);
		if(list != null && list.size() != 0) {
			//设置是否超标例日
			//得到涉及的所有月份
			Set<String> ymSet = new HashSet<>();
			SimpleDateFormat sdfYm = new SimpleDateFormat("yyyyMM");
			for (ScMpCountDetail scMpCountDetail : list) {
				Date readDate = scMpCountDetail.getMeterReadDate();
				ymSet.add(sdfYm.format(readDate));
			}
			ScMpInfo mpInfo = dao.findById(scMpCountVo.getId(), ScMpInfo.class);
			//例日信息set
			Set<String> usuallyDateSet = new HashSet<>();
			for (String ym : ymSet) {
				//获取此月份的例日信息 的map
				//包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate、isNatureYm
				// value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）、int(0:存在抄表例日，按抄表年月； 1：不存在抄表例日，按照自然月)
				Map<String,Object> usuallyDateInfo = scConsDateService.getDateAndDays(mpInfo.getConsId(), ym);
				if((int)usuallyDateInfo.get("isNatureYm") == 0) {
					String endDate = usuallyDateInfo.get("endDate").toString();
					usuallyDateSet.add(endDate);
				}
			}
			SimpleDateFormat sdfYmd = new SimpleDateFormat("yyyyMMdd");
			for (ScMpCountDetail scMpCountDetail : list) {
				Date readDate = scMpCountDetail.getMeterReadDate();
				//如果此天是例日
				if(usuallyDateSet.contains(sdfYmd.format(readDate))) {
					scMpCountDetail.setUsuallyDateFlag("1");
				}else {
					scMpCountDetail.setUsuallyDateFlag("0");
				}
			}
		}
		
		Object result = dao.getOneBySQL("scMpCount.sql.getScMpCountListCountByMpId", scMpCountVo.getId());
		long total = result == null ? 0 : Integer.valueOf(result.toString());
		queryResult.setTotal(total);
		queryResult.setData(list);
		return queryResult;
	}
	
	
	/**
	 * @Title: saveChangeCountVo
	 * @Description: 批量增删改ScMpCount， 然后修改影响到的用电计划
	 * @param changeList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午3:59:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午3:59:48
	 * @since  1.0.0
	 */
	@Transactional
	public void saveChangeCountVo(ScMpCountVo changeListVo) throws Exception {
		String consId = changeListVo.getConsId();
		//日期重复性校验，并计算影响到用电计划的日期（升序排列，yyyyMMdd格式）
		//ymdList中不包含级联影响的日期
			//注：ymdList可能含有例日日期，例日示数更改（或例日没有示数，下个月第一个示数修改，
			//导致下个月所有天的累计/日实际用电量修改）级联的下个月所有天的日期没在此ymdList中
		List<String> ymdList = this.checkData(changeListVo);
		
		//计算出影响到的  所有月份（抄表年月，yyyyMM格式） -> 例日信息 的map
		Map<String, Map<String,Object>> ymToUsuallyDateMap =  this.calculateYmInfoMap(consId,ymdList);
		
		//循环所有月
		for (String ym : ymToUsuallyDateMap.keySet()) {
			//包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate、ymdList,
				//value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）、ymdList
			Map<String,Object> usuallyDateInfo = ymToUsuallyDateMap.get(ym);
			//根据参数更新日用电量和累计用电量两个字段
			this.updateActualPqByParams(consId,ym,usuallyDateInfo);
		}
	}
	/**
	 * @Title: rebuildPowerViewByYmList
	 * @Description: 重新构建多个月的用电计划，并计算偏差预警
	 * 					方案：
	 * @param consId
	 * @param ymList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 上午10:34:02
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 上午10:34:02
	 * @since  1.0.0
	 */
	@Transactional
	public void rebuildPowerViewByYmList(String consId, List<String> ymList) throws Exception {
		//循环所有月
		for (String ym : ymList) {
			//获取此月份的例日信息 的map
			//包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
			// value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）
			Map<String,Object> usuallyDateInfo = scConsDateService.getDateAndDays(consId, ym);
			//根据参数重新计算整个月的用电计划（包括所有字段，所以直接删除powerView并重建，得先根据ym查询是否已被删除（isDelete字段），然后判断是否需要重建），然后更新偏差预警
			this.rebuildPowerViewByParams(consId,ym,usuallyDateInfo);
		}
	}
	/**
	 * @Title: deleteScMpCountAndOthersByConsId
	 * @Description: 根据用户id数组删除所有表计示数、删除用电计划、删除偏差预警，删除用户时调用
	 * @param consIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月10日 下午7:04:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月10日 下午7:04:23
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteScMpCountAndOthersByConsId(String[] consIds){
		//删除表计示数
		dao.deleteBySql("scMpCount.sql.deleteScMpCountByConsId", consIds);
		//删除用电计划、删除偏差预警
		smConsPowerViewService.deletePowerViewByConsIds(consIds);
	}
	/**
	 * @Title: getScMpCountCountByMpInfoId
	 * @Description: 根据计量点id获取其下的抄表示数数据行数,在删除计量点时判断是否有表计示数时用
	 * @param mpInfoId
	 * @return 
	 * long
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午6:59:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午6:59:03
	 * @since  1.0.0
	 */
	public long getScMpCountCountByMpInfoId(String mpInfoId) {
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scMpCount.sql.getScMpCountCountByMpInfoId", mpInfoId);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	/**
	 * @Title: rebuildPowerViewByParams
	 * @Description: 重新构建一个抄表月的用电计划
	 * 					（包括所有字段，所以直接删除powerView并重建，但得先根据ym查询是否已被删除（isDelete字段），然后判断是否需要重建），然后更新偏差预警
	 * @param consId
	 * @param ym 	抄表年月，yyyyMM格式
	 * @param usuallyDateInfo		map: 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
	 *								value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）
	 * @return 
	 * Map<String,List<ScMpCount>>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 上午11:00:05
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 上午11:00:05
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional
	private void rebuildPowerViewByParams(String consId,String ym,Map<String,Object> usuallyDateInfo) throws ParseException {
		Calendar calDate = Calendar.getInstance();
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		//要顺带获取到上个月抄表例日的示数，则在ymdList 中添加一个日期，或把startDate提前一天，获取后再恢复ymdList
		String startDate = (String) usuallyDateInfo.get("startDate");
		String endDate = (String) usuallyDateInfo.get("endDate");
		int days = (int) usuallyDateInfo.get("days");
		
		//获取用户绑定的采集设备id
		List<String> deviceIds = (List<String>) dao.getBySql("scMpCount.sql.getDeviceIdByConsId", consId);
		//ymd(yyyyMM格式) -> 日用电量
		Map<String, BigDecimal> ymdToCollectionDayPq = new HashMap<>();
		if(deviceIds != null && deviceIds.size() != 0){
			RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);
			//远程调用采集接口，获取时间段内  多个设备的日电量总和（数据按日期分组加和）
			List<EmbusinessDataCollectDetail> collectDetails = realDataInterface.getEmbusinessDataCollectDetailsByParams(startDate, endDate, deviceIds);
			//有采集数据
			if(collectDetails != null && collectDetails.size() != 0) {
				//把采集数据单位转换为mwh，然后放入map中
				for (EmbusinessDataCollectDetail pqDetail : collectDetails) {
					if(pqDetail.getTotalElectricQuantity() != null){
						BigDecimal mWH =  new BigDecimal(pqDetail.getTotalElectricQuantity()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP);
						ymdToCollectionDayPq.put(formatYmd.format(pqDetail.getDataDate()), mWH);
					}
				}
			}
		}
		
		List<SmConsPowerView> powerViewList = new ArrayList<>(days);
		//若有采集数据：获取全月示数，相邻日期的示数相减得到日用电量覆盖采集的日用电量，按照日用电量累加得到累计用电量
		if(ymdToCollectionDayPq.size() != 0) {
			//获取从示数计算出的本月第一天 至此月 最后一天的日用电量(可能不全),ymd -> 每日电量
			Map<String, BigDecimal> dayPqMap = this.getDayActualPqByParams(consId, startDate, endDate);
			//从数据库中计算的电量覆盖采集的日用电量
			ymdToCollectionDayPq.putAll(dayPqMap);
			
			String date = startDate;
			calDate.setTime(formatYmd.parse(date));
			BigDecimal actualPq = null;
			
			//补全firstDate到endDate的所有powerView用电计划数据
			for (int i = 0; i < days; i++) {
				SmConsPowerView powerView = new SmConsPowerView();
				powerView.setConsId(consId);
				powerView.setYm(ym);
				powerView.setYmd(date);
				powerView.setIsDelete("0");		//默认是未删除状态，以数据库状态为准
				BigDecimal dayActualPq = ymdToCollectionDayPq.get(date);
				if(actualPq == null) {
					actualPq = dayActualPq;
				}else if(dayActualPq != null){
					actualPq = actualPq.add(dayActualPq);
				}
				powerView.setDayActualPq(dayActualPq);
				powerView.setActualPq(actualPq);
				
				powerViewList.add(powerView);
				
				calDate.add(Calendar.DAY_OF_MONTH, 1); //后一天
				date = formatYmd.format(calDate.getTime());
			}
		}else {		//没有采集数据：则按照startDate到endDate日期的示数计算累积用电量（首先获取上个月例日的示数作为起始示数，若不存在例日示数，则取本月最早一条数据作为起始示数）
						//，减去前一天的累计得到当天的日用电量，后一天的累计减去当天的累计得到第二天的日用电量（若后一天属于下个月，则不计算）。
			//获取从示数计算出的累计实际用电量,ymd -> 累计实际用电量
			Map<String, BigDecimal> actualPqMap = this.getActualPqByParams(consId, startDate, endDate, null);
			
			String date = startDate;
			calDate.setTime(formatYmd.parse(date));
			BigDecimal prevActualPq = null;
			
			//补全startDate到endDate的所有powerView用电计划数据,升序排列
			for (int i = 0; i < days; i++) {
				SmConsPowerView powerView = new SmConsPowerView();
				powerView.setConsId(consId);
				powerView.setYm(ym);
				powerView.setYmd(date);
				powerView.setIsDelete("0");		//默认是未删除状态，以数据库状态为准
				BigDecimal actualPq = actualPqMap.get(date);
				if(i == 0) {
					powerView.setDayActualPq(actualPq);
				}else if(prevActualPq != null && actualPq != null) {
					powerView.setDayActualPq(actualPq.subtract(prevActualPq));
				}
				powerView.setActualPq(actualPq);
				prevActualPq = actualPq;
				
				powerViewList.add(powerView);
				
				calDate.add(Calendar.DAY_OF_MONTH, 1); //后一天
				date = formatYmd.format(calDate.getTime());
			}
		}
		
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("ym", ym);
		//获取用户本月的委托电量
		BigDecimal proxyPq = (BigDecimal)dao.getOneBySQL("scMpCount.sql.getProxyPqByYm", params);
		if(proxyPq != null && proxyPq.compareTo(BigDecimal.ZERO) >= 0) {
			BigDecimal dayPlanPq =  proxyPq.divide(new BigDecimal(days), 3, BigDecimal.ROUND_HALF_UP);
			BigDecimal lastDayPlanPq =  proxyPq.subtract(dayPlanPq.multiply(new BigDecimal(days - 1)));
			//赋值计划用电量两个字段
			for (int i = 0; i < days; i++) {
				SmConsPowerView powerView = powerViewList.get(i);
				if(i != days - 1) {
					powerView.setPlanPq(dayPlanPq.multiply(new BigDecimal(i + 1)));
					powerView.setDayPlanPq(dayPlanPq);
				}else {
					powerView.setPlanPq(proxyPq);
					powerView.setDayPlanPq(lastDayPlanPq);
				}
			}
		}
		
		
		//重建本月所有天的用电计划，powerViewList中ymd日期是连续的
		smConsPowerViewService.rebuildPowerViewByMonth(consId, ym, powerViewList);
	}
	/**
	 * @Title: calculateYmInfoMap
	 * @Description: 计算出影响到的  所有月份（抄表年月，yyyyMM格式） -> 例日信息 的map
	 * 			注：影响到的月份 指影响力此抄表年月的一条或多条用电计划
	 * 				例日信息 的map: 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate、ymdList,
	 *						value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）、ymdList
	 *					例日信息map中的ymdList(升序排列)为参数ymdList的子级，是参数ymdList中属于此抄表年月的ymd
	 *							当map中不含ymdList，这样表明是被上个月例日示数修改导致级联修改本月全部数据
	 * @param ymdList：升序排列
	 * @return
	 * @throws Exception 
	 * Map<String,Map<String,Object>>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月14日 下午3:07:01
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月14日 下午3:07:01
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked" })
	private Map<String, Map<String,Object>> calculateYmInfoMap(String consId, List<String> ymdList) throws Exception {
		//首先计算出“可能”会影响到的所有月份（抄表年月）
		Set<String> ymSet = new HashSet<String>();
		SimpleDateFormat formatYm = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		for (String ymd : ymdList) {
			String ym = ymd.substring(0, 6);
			cal.setTime(formatYm.parse(ym));
			cal.add(Calendar.MONTH, 1);	//下个月
			ymSet.add(ym);
			ymSet.add(formatYm.format(cal.getTime()));
		}
		//所有可能会影响到的月的例日信息
		Map<String, Map<String,Object>> ymToUsuallyDateAllMap = new HashMap<String, Map<String,Object>>();
		for (String ym : ymSet) {
			//包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
			// value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）
			Map<String,Object> usuallyDateInfo = scConsDateService.getDateAndDays(consId, ym);
			ymToUsuallyDateAllMap.put(ym, usuallyDateInfo);
		}
		
		//最终会影响到的月份（抄表年月）——> 例日信息  的map
		Map<String, Map<String,Object>> ymToUsuallyDateMap = new HashMap<String, Map<String,Object>>();
		for (String ymd : ymdList) {
			//此ymd所属的抄表年月,默认为本自然年月
			String ym = ymd.substring(0, 6);
			//此ymd所属于的抄表年月的map
			Map<String,Object> usuallyDateInfo = ymToUsuallyDateAllMap.get(ym);
			
			String endDate = usuallyDateInfo.get("endDate").toString();
			if(ymd.compareTo(endDate) > 0) {	//则此ymd是属于下一个抄表年月
				cal.setTime(formatYm.parse(ym));
				cal.add(Calendar.MONTH, 1);	//下个月
				
				//重新设置ym和 usuallyDateInfo
				ym = formatYm.format(cal.getTime());
				usuallyDateInfo = ymToUsuallyDateAllMap.get(ym);
			}else if(ymd.compareTo(endDate) == 0){			//如果相等，则此ymd是例日，虽然属于本月，但是则此ymd数据会影响当月及下月数据
				cal.setTime(formatYm.parse(ym));
				cal.add(Calendar.MONTH, 1);	//下个月
				String nextYm = formatYm.format(cal.getTime());
				ymToUsuallyDateMap.put(nextYm, ymToUsuallyDateAllMap.get(nextYm));
			}
			ymToUsuallyDateMap.put(ym, usuallyDateInfo);
			
			//构建例日信息map中的ymdList
			if(!usuallyDateInfo.containsKey("ymdList")) {
				List<String> subYmdList = new ArrayList<String>();
				usuallyDateInfo.put("ymdList", subYmdList);
			}
			((List<String>)usuallyDateInfo.get("ymdList")).add(ymd);
		}
		return ymToUsuallyDateMap;
	}
	/**
	 * @Title: checkData
	 * @Description: 日期重复性校验，先把增删改操作保存到数据库，然后判断操作后数据库是否存在重复的日期，若有则抛异常回滚
	 * @param scMpCountVo
	 * @return	
	 * @throws Exception 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月14日 上午9:37:12
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月14日 上午9:37:12
	 * @since  1.0.0
	 */
	@SuppressWarnings({ "unchecked"})
	@Transactional
	private List<String> checkData(ScMpCountVo scMpCountVo) throws Exception {
		Set<String> ymdSet = new HashSet<String>();
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		// 对数据进行存储操作
		if(scMpCountVo.getAddRows() != null){
			dao.saveList(scMpCountVo.getAddRows());
		}
		if(scMpCountVo.getUpdateRows() != null){
			dao.saveList(scMpCountVo.getUpdateRows());
		}
		if(scMpCountVo.getDelRows() != null && scMpCountVo.getDelRows().size() > 0){
			List<ScMpCount> delList = scMpCountVo.getDelRows();
			List<String> delIds = new ArrayList<String>();
			for(ScMpCount scMpCount : delList){
				delIds.add(scMpCount.getId());
				ymdSet.add(formatYmd.format(scMpCount.getMeterReadDate()));
			}
			String[] ids = delIds.toArray(new String[delIds.size()]); 
			dao.delete(ids, ScMpCount.class);
		}
		//根据mpId获取重复的抄表日期
		List<String> dateList = (List<String>) dao.getBySql("scMpCount.sql.checkDateRepeat", scMpCountVo.getId());
		if(dateList != null && dateList.size()>0){
			String dateStr = "";
			for(String date : dateList){
				dateStr = dateStr + " " + date;
			}
			throw new BusinessException(dateStr + "的抄表示数已存在，请检查后提交！");
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if(scMpCountVo.getAddRows() != null){
			for(ScMpCount scMpCount : scMpCountVo.getAddRows()){
				ymdSet.add(formatYmd.format(scMpCount.getMeterReadDate()));
			}
		}
		if(scMpCountVo.getUpdateRows() != null){
			for(ScMpCount scMpCount : scMpCountVo.getUpdateRows()){
				ymdSet.add(formatYmd.format(scMpCount.getMeterReadDate()));
			}
		}
		List<String> ymdList = new ArrayList<>(ymdSet);
		Collections.sort(ymdList);
//	    ymdList.sort(new Comparator() {
//			public int compare(Object o1, Object o2) {
//				String str1=(String) o1;
//				String str2=(String) o2;
//		        return str1.compareTo(str2);
//			}
//		});
		return ymdList;
	}
	
	/**
	 * @Title: updateActualPqByParams
	 * @Description:计算出usuallyDateInfo中的ymdList中日期（若ymdList为null，则计算全月）的  累计实际用电量和日用电量,并更新到用电计划表中，然后更新偏差预警
	 * @param consId
	 * @param ym 	抄表年月，yyyyMM格式
	 * @param usuallyDateInfo		map: 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate、ymdList,
	 *								value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）、ymdList
	 *					例日信息map中的ymdList为升序排列，当map中不含ymdList，这样表明是要计算本月startDate->endDate的全部数据
	 * @return 
	 * Map<String,List<ScMpCount>>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 上午11:00:05
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 上午11:00:05
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional
	private void updateActualPqByParams(String consId,String ym,Map<String,Object> usuallyDateInfo) throws ParseException {
		Calendar calDate = Calendar.getInstance();
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		//要顺带获取到上个月抄表例日的示数，则在ymdList 中添加一个日期，或把startDate提前一天，获取后再恢复ymdList
		String startDate = (String) usuallyDateInfo.get("startDate");
		String endDate = (String) usuallyDateInfo.get("endDate");
		int days = (int) usuallyDateInfo.get("days");
		List<String> ymdList = (List<String>)usuallyDateInfo.get("ymdList");
		
		//获取用户绑定的采集设备id
		List<String> deviceIds = (List<String>) dao.getBySql("scMpCount.sql.getDeviceIdByConsId", consId);
		//ymd(yyyyMM格式) -> 日用电量
		Map<String, BigDecimal> ymdToCollectionDayPq = new HashMap<>();
		if(deviceIds != null && deviceIds.size() != 0){
			RealDataInterface realDataInterface= RemoteProcedureCall.getInstancce().getService(RealDataInterface.class);
			//远程调用采集接口，获取时间段内  多个设备的日电量总和（数据按日期分组加和）
			List<EmbusinessDataCollectDetail> collectDetails = realDataInterface.getEmbusinessDataCollectDetailsByParams(startDate, endDate, deviceIds);
			//有采集数据
			if(collectDetails != null && collectDetails.size() != 0) {
				//把采集数据单位转换为mwh，然后放入map中
				for (EmbusinessDataCollectDetail pqDetail : collectDetails) {
					if(pqDetail.getTotalElectricQuantity() != null){
						BigDecimal mWH =  new BigDecimal(pqDetail.getTotalElectricQuantity()).multiply(new BigDecimal(0.001)).setScale(3, BigDecimal.ROUND_HALF_UP);
						ymdToCollectionDayPq.put(formatYmd.format(pqDetail.getDataDate()), mWH);
					}
				}
			}
		}
		
		//若有采集数据：若参数map中不含ymdList，则无需修改（前提是有定时任务去获取采集数据并存入用电计划中）。
			//否则：相邻日期的示数相减得到日用电量覆盖采集的日用电量，按照日用电量累加得到累计用电量，和数据库本月数据比对，以天为单位更新不一致的数据
		if(ymdToCollectionDayPq.size() != 0) {
			if(ymdList != null) {	//ymdList可能为null，但是不可能size为0
				//获取改变示数的第一天 至此月 最后一天的日用电量
				String firstDate = ymdList.get(0);
				
				//获取从示数计算出的每日电量,ymd -> 每日电量
				Map<String, BigDecimal> dayPqMap = this.getDayActualPqByParams(consId, firstDate, endDate);
				//从数据库中计算的电量覆盖采集的日用电量
				ymdToCollectionDayPq.putAll(dayPqMap);
				
				//补全firstDate到endDate的所有powerView用电计划数据
				List<SmConsPowerView> powerViewList = new ArrayList<>(days);
				calDate.setTime(formatYmd.parse(firstDate));
				for (int i = 0; i < days; i++) {
					SmConsPowerView powerView = new SmConsPowerView();
					powerView.setConsId(consId);
					powerView.setYm(ym);
					powerView.setYmd(firstDate);
					powerView.setDayActualPq(ymdToCollectionDayPq.get(firstDate));
					powerView.setIsDelete("1");		//默认（新增时）是删除状态，以数据库状态为准
					
					powerViewList.add(powerView);
					
					calDate.add(Calendar.DAY_OF_MONTH, 1); //后一天
					firstDate = formatYmd.format(calDate.getTime());
					
					if(firstDate.compareTo(endDate) > 0) {
						break;
					}
				}
				//更新改变示数的第一天之后的本月所有天的实际用电量和日用电量，powerViewList中ymd日期是连续的
				smConsPowerViewService.updateActualPqByMonth(consId, ym, powerViewList, false);
			}else {
				return;
			}
		}else {		//没有采集数据：则按照ymdList日期的示数计算累计用电量（首先获取上个月例日的示数作为起始示数，
						//若不存在例日示数，则取本月最早一条数据作为起始示数，若ymdList中含有此最早日期或比此最早日期更小的日期，则需要重新计算全月数据）
						//，减去前一天的累计得到当天的日用电量，后一天的累计减去当天的累计得到第二天的日用电量（若后一天属于下个月，则不计算）。
						//若参数map中不含ymdList，则表明本月数据都需要修改。
			//获取从示数计算出的累计实际用电量,ymd -> 累计实际用电量
			Map<String, BigDecimal> actualPqMap = this.getActualPqByParams(consId, startDate, endDate, ymdList);
			
			//是否需要更新全月的累计电量和日用电量
			boolean changeAllMonthFlag = (ymdList == null || ymdList.size() == 0);
			
			String date = startDate;
			//补全startDate到endDate的所有powerView用电计划数据,升序排列
			List<SmConsPowerView> powerViewList = new ArrayList<>(days);
			calDate.setTime(formatYmd.parse(date));
			
			for (int i = 0; i < 62; i++) {
				if(changeAllMonthFlag || ymdList.contains(date)) {
					SmConsPowerView powerView = new SmConsPowerView();
					powerView.setConsId(consId);
					powerView.setYm(ym);
					powerView.setYmd(date);
					powerView.setActualPq(actualPqMap.get(date));
					powerView.setIsDelete("1");		//默认（新增时）是删除状态，以数据库状态为准
					powerViewList.add(powerView);
				}
				
				calDate.add(Calendar.DAY_OF_MONTH, 1); //后一天
				date = formatYmd.format(calDate.getTime());
				if(date.compareTo(endDate) > 0) {
					break;
				}
			}
			if(changeAllMonthFlag) {
				//计算日用电量
				BigDecimal lastActualPq = null;
				for (int i = 0; i < powerViewList.size() - 1; i++) {
					SmConsPowerView powerView = powerViewList.get(i);
					if(i == 0) {
						lastActualPq = powerView.getActualPq();
						powerView.setDayActualPq(lastActualPq);
					}else {
						BigDecimal actualPq = powerView.getActualPq();
						if(actualPq != null && lastActualPq != null) {
							powerView.setDayActualPq(actualPq.subtract(lastActualPq));
						}
						lastActualPq = actualPq;
					}
				}
				//按月更新，更新本月所有的实际用电量和日用电量，powerViewList中ymd日期是连续的
				smConsPowerViewService.updateActualPqByMonth(consId, ym, powerViewList, true);
			}else {
				//按日更新，更新powerViewList中所有的实际用电量和日用电量，powerViewList中ymd日期可能是不连续的
				smConsPowerViewService.updateActualPqByYmds(consId, ym,startDate, powerViewList);
			}
		}
	}
	
	/**
	 * @Title: getActualPqByParams
	 * @Description: 根据用户id及时间列表ymdList，获取累计用电量；若ymdList为null，则按照startDate到endDate获取累计用电量
	 * 					注：无论一个还是多个电表的示数，都是以usuallyDatePrev（startDate的前一天）的示数为基准值计算累计用电量，若例日没有示数则取这个月第一条示数
	 * 					特例：当共有多个电表，其中一块多多块电表示数不全，例如：例日为每月25号，电表a示数全，电表b 5号到10号不存在示数，则用户7号的累计用电量应该为
	 * 						a表7号的累计用电量加b表5号的累计用电量；若修改b电表5号示数，则用户5-10号累计用电量都得变更
	 * 		设计方案：按照ymdList日期的示数计算累积用电量（首先获取上个月例日的示数作为起始示数，
	 *			若不存在例日，则取本月最早一条数据作为起始示数，若ymdList中含有此最早日期或比此最早日期更小的日期，则需要重新计算全月数据）
	 *			，减去前一天的累计得到当天的日用电量，后一天的累计减去当天的累计得到第二天的日用电量（若后一天属于下个月，则不计算）。
	 *			若参数map中不含ymdList，则表明本月数据都需要修改。
	 *		特例解决思路：1. 判断是否是全月更新，若全月更新，直接进行步骤3
	 *					 2. 先计算出ymdList中日期影响的后续日期， 把后续日期也加入到ymdList中，并正序排序。把所有影响到的后续日期都另存入一个set
	 *					 3. 根据是否需要全月更新，计算全月或者ymdList的分电表的累计用电量
	 *					 4. 循环ymdList，累加多个表中的当日累计电量，若某电表不存在当天累计，则取前一个存在的累计电量；
	 *						若都不存在累计，而且set中不含此ymd，则累计为null；若set中含此ymd，则跳过此ymd，ymdList中删除此ymd
	 * @param consId	用户id
	 * @param startDate	这个月的开始日期，yyyyMM格式
	 * @param endDate	这个月的结束日期，yyyyMM格式
	 * @param ymdList	ymd列表(升序排列)，yyyyMM格式。若为null或者被移除所有节点使得size为0，则代表需要更新startDate到endDate所有用电计划
	 * @return 
	 * Map<String,BigDecimal>	若ymdList为null或者size为0，则map中是全月数据（可能不全），否则map中是ymdList的子集数据
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午4:02:24
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午4:02:24
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Map<String, BigDecimal> getActualPqByParams(String consId, String startDate, String endDate, List<String> ymdList) throws ParseException {
		Calendar calDate = Calendar.getInstance();
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		//是否需要返回全月的累计电量（即更新全月累计用电量和日用电量）,即ymdList为null或者size为0的标识
		boolean changeAllMonthFlag = false;
		String firstChangeYmd = null;
		if(ymdList != null && ymdList.size() != 0) {
			firstChangeYmd = ymdList.get(0);
		}else {
			changeAllMonthFlag = true;
		}
		//获取上个月的抄表例日
		calDate.setTime(formatYmd.parse(startDate));
		calDate.add(Calendar.DAY_OF_MONTH, -1); //获取抄表开始时间的前一天（即上个月的抄表例日）
		String usuallyDatePrev = formatYmd.format(calDate.getTime());
		
		Map<String, Object> params = new HashMap<>();
		params.put("consId", consId);
		params.put("startDate", usuallyDatePrev);
		params.put("endDate", endDate);
		
		//获取startDate到endDate的所有示数，此list是按照计量点id、抄表日期倒序排列,含有多块表的数据
		List<ScMpCountDetail> monthCountList = (List<ScMpCountDetail>) dao.getBySql("scMpCount.sql.getMpCountByParams", params);
		//本月没有有效示数
		if(monthCountList == null || monthCountList.size() <= 1) {
			return new HashMap<>();
		}
		//每一个电表的ymd最小的示数（即上个月例日的示数或者没有例日这个月最早的示数）
		ScMpCountDetail baseDetail = null;
		
		//重新计算changeAllMonthFlag
		if(!changeAllMonthFlag) {
			//倒序遍历，即ymd正序遍历,计算是否需要返回全月的累计电量（即更新全月累计用电量和日用电量）
			for (int i = monthCountList.size() - 1; i >= 0 ; i--) {
				ScMpCountDetail detail = monthCountList.get(i);
				if(baseDetail == null || !baseDetail.getMpId().equals(detail.getMpId())) {
					baseDetail = detail;
					if(firstChangeYmd != null && firstChangeYmd.compareTo(baseDetail.getReadDateStr()) <= 0) {
						changeAllMonthFlag = true;
						ymdList.clear();		//制空，代表本来只需要更新ymdList中的用电计划，但通过判断需要整月更新
						break;
					}
				}
			}
		}
//		1. 判断是否是全月更新，若全月更新，直接进行步骤3
//		2. 先计算出ymdList中日期影响的后续日期， 把后续日期也加入到ymdList中，并正序排序。把所有影响到的后续日期都另存入一个set
		Set<String> ymdSet = new HashSet<>();
		//重新计算ymdList
		if(!changeAllMonthFlag) {
			for (int j = 0; j < ymdList.size(); j++) {
				String ymd = ymdList.get(j);
				//影响的后续日期的结束日期
				String endYmd = null;
				//加入ymdList时是否包含endYmd当天
				boolean containsEndYmd = false;
				//倒序遍历，即ymd正序遍历,计算是否需要返回全月的累计电量（即更新全月累计用电量和日用电量）
				for (int i = monthCountList.size() - 1; i >= 0 ; i--) {
					ScMpCountDetail detail = monthCountList.get(i);
					String readDateStr = detail.getReadDateStr();
					ScMpCountDetail nextDetail = null;
					String nextReadDateStr = null;
					if(i - 1 > -1) {
						nextDetail = monthCountList.get(i - 1);
						nextReadDateStr = nextDetail.getReadDateStr();
					}
					//ymd > 数据库示数的日期，并且不是上个月例日
					if(ymd.compareTo(readDateStr) >= 0 && !usuallyDatePrev.equals(readDateStr)) {
						//循环到另一个电表数据
						if(nextDetail == null || !nextDetail.getMpId().equals(detail.getMpId())) {
							endYmd = endDate;
							containsEndYmd = true;
							break;
						}else if(ymd.compareTo(nextReadDateStr) < 0){		//nextDetail为本电表的下一条示数
							if(endYmd == null || endYmd.compareTo(nextReadDateStr) < 0) {
								endYmd = nextReadDateStr;
							}
						}
					}
				}
				//根据ymd和endYmd把ymd到endYmd之间的日期加入ymdList中
				if(endYmd != null) {
					int count = 0;
					boolean flag = false;
					while (ymd.compareTo(endYmd) <= 0) {
						calDate.setTime(formatYmd.parse(ymd));
						calDate.add(Calendar.DAY_OF_MONTH, 1); //获取后一天
						ymd= formatYmd.format(calDate.getTime());
						if(!ymdList.contains(ymd) && ((ymd.equals(endYmd) && containsEndYmd) || (!ymd.equals(endYmd)))) {
							ymdList.add(ymd);
							ymdSet.add(ymd);
							flag = true;
						}
						if(count++ > 60) {	//防止数据错误的死循环
							break;
						}
					}
					if(flag) {
						Collections.sort(ymdList);	//重新正序排列ymd
					}
				}
			}
		}
		
//		3. 根据是否需要全月更新，计算全月或者ymdList的分电表的累计用电量
		baseDetail = null;
		//mpId -> 此表计算的所有累计电量
		Map<String, TreeMap<String, BigDecimal>> resultPart = new HashMap<>();
		//倒序遍历，即ymd正序遍历,算出ymdList中每块表的累计电量
		for (int i = monthCountList.size() - 1; i >= 0 ; i--) {
			ScMpCountDetail detail = monthCountList.get(i);
			//循环到另一个电表数据，变换baseDetail
			if(baseDetail == null || !baseDetail.getMpId().equals(detail.getMpId())) {
				baseDetail = detail;
				TreeMap<String, BigDecimal> actualPqMp = new TreeMap<>();
				resultPart.put(baseDetail.getMpId(), actualPqMp);
			}else {		//同一个电表，需要计算累计用电量
				String readDateStr = detail.getReadDateStr();
				//无需计算全月，并且此日期不在ymdList计算范围内，则不计算
				if(!changeAllMonthFlag && !ymdList.contains(readDateStr)) {
					continue;
				}
				BigDecimal actualPq = this.calculateDayActualPq(baseDetail.getMeterReadPq(), detail.getMeterReadPq(),
						 Double.valueOf(detail.getMeterRate()), detail.getMeterDigits());
				if(actualPq != null) {
					resultPart.get(baseDetail.getMpId()).put(readDateStr, actualPq);
				}
			}
		}
		
		
//		4. 循环ymdList，累加多个表中的当日累计电量，若某电表不存在当天累计，则取前一个存在的累计电量；
// 				若左右表都不存在此ymd的累计：而且set中不含此ymd，则累计为null(即ymdList含有此ymd，但返回结果中却不含)；
//								若set中含此ymd，则跳过此ymd，ymdList中删除此ymd
		Map<String, BigDecimal> result = new HashMap<>();
		if(!changeAllMonthFlag) {		//如果不是计算全月
			for (int i = 0; i < ymdList.size(); i++) {
				String ymd = ymdList.get(i);
				BigDecimal actualPq = BigDecimal.ZERO;
				//是否所有表都不存在此天的累计电量
				boolean isNullAllFlag = false;
				for (TreeMap<String, BigDecimal> actualPqMp : resultPart.values()) {
					if(actualPqMp.containsKey(ymd)) {
						isNullAllFlag = true;
					}
					String floorKeyYmd = actualPqMp.floorKey(ymd);	//返回小于等于给定键的最大键；如果不存在这样的键，则返回 null。
					if(floorKeyYmd != null) {		//此电表存在当天的累计电量或者之前的电量
						actualPq = actualPq.add(actualPqMp.get(floorKeyYmd));
					}
				}
				if(isNullAllFlag) {
					result.put(ymd, actualPq);
				}else if(ymdSet.contains(ymd)){
					ymdList.remove(i);
					i--;
				}
			}
		}else {		//如果是计算全月
			int count = 0;
			String ymd = startDate;
			while (ymd.compareTo(endDate) <= 0) {
				BigDecimal actualPq = BigDecimal.ZERO;
				//是否所有表都不存在此天的累计电量
				boolean isNullAllFlag = false;
				for (TreeMap<String, BigDecimal> actualPqMp : resultPart.values()) {
					if(actualPqMp.containsKey(ymd)) {
						isNullAllFlag = true;
					}
					String floorKeyYmd = actualPqMp.floorKey(ymd);	//返回小于等于给定键的最大键；如果不存在这样的键，则返回 null。
					if(floorKeyYmd != null) {		//此电表存在当天的累计电量或者之前的电量
						actualPq = actualPq.add(actualPqMp.get(floorKeyYmd));
					}
				}
				if(isNullAllFlag) {	//某几块表存在此天的累计电量
					result.put(ymd, actualPq);
				}
				if(count++ > 60) {	//防止数据错误的死循环
					break;
				}
				calDate.setTime(formatYmd.parse(ymd));
				calDate.add(Calendar.DAY_OF_MONTH, 1); //获取后一天
				ymd= formatYmd.format(calDate.getTime());
			}
		}
		return result;
	}
	/**
	 * @Title: getDayActualPqByParams
	 * @Description: 根据用户及时间区间，获取日用电量
	 * 					注：无论一个还是多个电表，都不补全数据，即当且仅当存在连续两天电表示数时才能算出后一天的日用电量
	 * @param consId
	 * @param startDate	yyyyMM格式
	 * @param endDate	yyyyMM格式
	 * @return 
	 * Map<String,BigDecimal>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午4:02:24
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午4:02:24
	 * @throws ParseException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private Map<String, BigDecimal> getDayActualPqByParams(String consId,String startDate, String endDate) throws ParseException {
		Calendar calDate = Calendar.getInstance();
		SimpleDateFormat formatYmd = new SimpleDateFormat("yyyyMMdd");
		
		calDate.setTime(formatYmd.parse(startDate));
		calDate.add(Calendar.DAY_OF_MONTH, -1); //获取本月的第一天的前一天
		startDate = formatYmd.format(calDate.getTime());
		
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		//获取startDate前一天到endDate的所有示数，此list是按照计量点id、抄表日期倒序排列,含有多块表的数据
		List<ScMpCountDetail> monthCountList = (List<ScMpCountDetail>) dao.getBySql("scMpCount.sql.getMpCountByParams", params);
		
		Map<String, BigDecimal> result = new HashMap<>();
		//没有有效示数
		if(monthCountList == null || monthCountList.size() <= 1) {
			return result;
		}
		for (int i = 0; i < monthCountList.size() - 1; i++) {
			ScMpCountDetail reading = monthCountList.get(i);
			ScMpCountDetail nextReading =  monthCountList.get(i + 1);
			//如果下一条数据和这条数据 属于 同一计量点
			if(reading.getMpId().equals(nextReading.getMpId())) {
				Date meterReadDate = reading.getMeterReadDate();
				calDate.setTime(meterReadDate);
				calDate.add(Calendar.DAY_OF_MONTH, -1);  //前一天
				String datePrev = formatYmd.format(calDate.getTime());   //前一天的日期
				//如果下一条数据是这条数据前一天的示数，则可以计算日用电量
				if(datePrev.equals(nextReading.getReadDateStr())) {		//如果下一个
					String readDateStr = reading.getReadDateStr();
					BigDecimal dayPq = this.calculateDayActualPq(nextReading.getMeterReadPq(), reading.getMeterReadPq(),
							 Double.valueOf(reading.getMeterRate()), reading.getMeterDigits());
					if(dayPq != null) {
						if(result.containsKey(readDateStr)){
							//累加不同电表的同一天的电量
							result.put(readDateStr, result.get(readDateStr).add(dayPq));
						}else{
							result.put(readDateStr, dayPq);
						}
					}
				}
			}
		}
		return result;
	}
	/**
	 * @Title: gcalculateDayActualPq
	 * @Description: 根据两个示数及电能表倍率、电表位数 计算电量
	 * @param meterReadingPrev	上一个示数
	 * @param meterReading		当天示数
	 * @param meterRate			电能表倍率
	 * @param meterDigits		电表位数
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月17日 下午4:34:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月17日 下午4:34:36
	 * @since  1.0.0
	 */
	private BigDecimal calculateDayActualPq(double meterReadingPrev,double meterReading, double meterRate, String meterDigits) {
		double pq = 0;
		if(meterReading >= meterReadingPrev){//电表不反转
			pq = (meterReading - meterReadingPrev)*meterRate;	//计算结果
		}else{//电表反转
			meterDigits = meterDigits.substring(0,meterDigits.indexOf("."));
			String meterMax = "1";
			for(int i = 0 ; i < Integer.parseInt(meterDigits);i++){
				meterMax = meterMax + "0";
			}
			pq = (Double.valueOf(meterMax) - meterReadingPrev + meterReading)*meterRate;
		}
		if (pq >= 0) {
			return BigDecimal.valueOf(pq).divide(new BigDecimal(1000),3, BigDecimal.ROUND_HALF_UP);
		}else {
			return null;
		}
	}
}
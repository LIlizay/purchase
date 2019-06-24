package com.hhwy.purchaseweb.archives.overview.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.archives.overview.domain.OverViewDetail;
import com.hhwy.purchaseweb.archives.overview.service.OverViewService;

/**
 * 
 * <b>类 名 称：</b>OverViewServiceImpl<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2017年12月29日 下午2:34:38<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class OverViewServiceImpl implements OverViewService {
	
	public static final Logger log = LoggerFactory.getLogger(OverViewServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @Title: getOverViewData<br/>
	 * @Description:TODO(总览页面数据)<br/>
	 * @param userId
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年1月31日 下午16：51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年1月31日 下午16：51
	 * @since  1.0.0
	 */
	@Override
	public Map<String,Object> getOverViewData(String userId) throws Exception{

		Map<String,Object> result = new HashMap<>();
		//下月购电计划交易状态
		result.put("getPurchasePqStatus", getPurchasePqStatus());
		//用户权限
		result.put("userResource", getUserResource(userId));
		//用户规模
		result.put("userScale", getUserScale());
		//年累计购电量 ： 双边数据 竞价 挂牌
		result.put("phMPpaAgrePq", getPhMPpaAgrePq());
		//年累计售电量数据
		result.put("sellPower", getSellPower());
		//下月购电计划交易情况
		result.put("purchasePq",getPurchasePq());
		//售电利润
		result.put("phProfit",getPhProfit());
		//交易电量统计
		result.put("dealElecCount", getDealElecCount());
		//用户合同电量排名
		result.put("consAgreRank", getConsAgreRank());
		//电厂合同电量排名
		result.put("elecAgreRank", getElecAgreRank());
		return result;
	}
	

	/**
	 * 
	 * @Title: getUserScale 
	 * @Description: TODO(查询 用户规模：用电类别)
	 * @return 
	 * OverViewDetail
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2017年12月29日 下午2:39:17
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2017年12月29日 下午2:39:17
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<OverViewDetail> getUserScale(){
		Parameter.isFilterData.set(true);
		 List<OverViewDetail> list = (List<OverViewDetail>) dao.getBySql("overView.sql.getUserScale", null);
		Parameter.isFilterData.set(false);
		return list;
	}
	/**
	 * 
	 * @Title: getPhMPpaAgrePq
	 * @Description: TODO(年累计购电量 ： 双边数据  竞价 挂牌)
	 * @return 
	 * List<OverViewDetail>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月2日 下午1:23:01
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月2日 下午1:23:01
	 * @since  1.0.0
	 */
	public OverViewDetail getPhMPpaAgrePq(){
		Parameter.isFilterData.set(true);
		OverViewDetail map = (OverViewDetail) dao.getOneBySQL("overView.sql.getPhMPpaAgrePq", null);
		Parameter.isFilterData.set(false);
		return map;
	}
	
	/**
	 * 
	 * @Title: getSellPower
	 * @Description: TODO(年累计售电量数据)
	 * @return 
	 * List<OverViewDetail>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月2日 下午3:57:06
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月2日 下午3:57:06
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<OverViewDetail> getSellPower(){
		Parameter.isFilterData.set(true);
		List<OverViewDetail> list =  (List<OverViewDetail>) dao.getBySql("overView.sql.getSellPower", null);
		Parameter.isFilterData.set(false);
		return list;
	}
	
	/**
	 * 
	 * @Title: getPurchasePq
	 * @Description: TODO(下月购电计划交易情况)
	 * @return 
	 * List<OverViewDetail>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月2日 下午6:37:48
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月2日 下午6:37:48
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<OverViewDetail> getPurchasePq(){
		Parameter.isFilterData.set(true);
		List<OverViewDetail> list = (List<OverViewDetail>) dao.getBySql("overView.sql.getPurchasePq", null);
		Parameter.isFilterData.set(false);
		return list;
	}
	
	/**
	 * 
	 * @Title: getPurchasePqStatus
	 * @Description: TODO(当前月购电计划交易状态)
	 * @return 
	 * String
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月5日 下午2:00:11
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月5日 下午2:00:11
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPurchasePqStatus(){
		Parameter.isFilterData.set(true);
		List<Map<String, Object>> result = (List<Map<String, Object>>) dao.getBySql("overView.sql.getPurchasePqStatus", null);
		Parameter.isFilterData.set(false);
		Map<String,Object> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH )+2;
		String date = "";
		if(month < 10){
			date = year+"0"+month;
		}else{
			date = year+""+ month;
		}
		String id = result.get(0).get("id") == null ? "" : result.get(0).get("id").toString();
		String ym = result.get(0).get("ym") == null ? date : result.get(0).get("ym").toString();
		String planStatus = result.get(0).get("planStatus") == null ? "00" : result.get(0).get("planStatus").toString();
		String count =  result.get(0).get("consNum") == null ? "" : result.get(0).get("consNum").toString();
		map.put("id", id);
		map.put("ym", ym);
		map.put("planStatus", planStatus);
		map.put("count", count);
		map.put("tradeMode", result.get(0).get("tradeMode") == null ? "" : result.get(0).get("tradeMode"));
		return map;
	}
	/**
	 * 
	 * @Title: getPhProfit
	 * @Description: TODO(售电公司利润 ：  取前12个月的数据)
	 * @return 
	 * List<OverViewDetail>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月4日 上午9:09:29
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月4日 上午9:09:29
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getPhProfit(){
		Map<String,Object> result = new HashMap<>();
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list = (List<Map<String, Object>>) dao.getBySql("overView.sql.getPhProfit", null);
		Parameter.isFilterData.set(false);
		//年月
		List<String> month = new ArrayList<>();
		//利润
		List<String> pq = new ArrayList<>();
		//增长率
		List<Object> group = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			Map<String,Object> map = list.get(i);
			Map<String,Object> lastMap = null;
			if(map.get("year").toString().equals("last")){
				continue;
			}
			String yearMonthPf = map.get("yearMonthPf") == null ? "" : map.get("yearMonthPf").toString();
			String compProfit = map.get("compProfit") == null ? "" : map.get("compProfit").toString();
			month.add(yearMonthPf);
			pq.add(compProfit);
			//算利率 （本月-上月）/上月  折线图的  注意分母可能是null或者0
			if(i == 0){
				group.add("");
			}else{
				lastMap = list.get(i-1);
				BigDecimal lastPorift = lastMap.get("compProfit") == null ? BigDecimal.ZERO : new BigDecimal(lastMap.get("compProfit").toString());
				if(!"".equals(compProfit) && lastPorift.compareTo(BigDecimal.ZERO) != 0){
					BigDecimal nowProfit = new BigDecimal(compProfit);
					BigDecimal profit = null;
					if(lastPorift.compareTo(BigDecimal.ZERO) > 0){
						//（本期值-上月值）/上月值*100%
						profit = nowProfit.subtract(lastPorift).divide(lastPorift, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
					}else{
						//当上月值为负数时，计算公式修改为：（上月值-本期值）/上月值*100%，
						profit = lastPorift.subtract(nowProfit).divide(lastPorift, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
					}
					group.add(profit);
				}else{
					group.add("");
				}
			}
		}
		result.put("yearMonthPf", month);
		result.put("compProfit", pq);
		result.put("groupRate", group);
		return result;
	}
	
	/**
	 * 
	 * @Title: getConsAgreRank
	 * @Description: TODO(用户合同电量排名)
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月4日 下午4:14:46
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月4日 下午4:14:46
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getConsAgreRank(){
		List<String> proxyPq = new ArrayList<String>();
		Map<String,Object> consName = new HashMap<>();
		List<String> shortName = new ArrayList<String>();
		Parameter.isFilterData.set(true);
		List<Map<String, Object>> lsit = (List<Map<String, Object>>) dao.getBySql("overView.sql.getConsAgreRank", null);
		Parameter.isFilterData.set(false);
		for(int i=0;i<lsit.size();i++){
			Map<String,Object> map = lsit.get(i); 
			String money =  map.get("proxyPq") == null ? "" :  map.get("proxyPq").toString();
			String name = map.get("consName") == null ? "" : map.get("consName").toString();
			String sName = map.get("shortName") == null ? "" : map.get("shortName").toString();
			proxyPq.add(money);
			consName.put(i+"."+sName, name);
			shortName.add(sName);
		}
		Map<String ,Object> result = new HashMap<String, Object>();
		result.put("proxyPq", proxyPq);
		result.put("consName", consName);
		result.put("shortName", shortName);
		return result;
	}
	/**
	 * 
	 * @Title: getElecAgreRank
	 * @Description: TODO(电厂合同电量排名)
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月4日 下午5:27:12
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月4日 下午5:27:12
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getElecAgreRank(){
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list = (List<Map<String, Object>>) dao.getBySql("overView.sql.getElecAgreRank", null);
		Parameter.isFilterData.set(false);
		Map<String ,Object> elecName = new HashMap<String, Object>();
		List<String> agrePq = new ArrayList<String>();
		List<String> shortName = new ArrayList<String>();
		for(int i=0 ; i<list.size() ; i++){
			Map<String ,Object> map = list.get(i);
			String num =  map.get("dealPq") == null ? "" : map.get("dealPq").toString();
			String  sName = map.get("shortElecNameName") == null ? "" : map.get("shortElecNameName").toString();
			String name = map.get("elecName") == null ? "" : map.get("elecName").toString();
			agrePq.add(num);
			shortName.add(sName);
			elecName.put(sName, name);
		}
		Map<String , Object> result = new HashMap<String, Object>();
		result.put("agrePq", agrePq);
		result.put("shortName", shortName);
		result.put("elecName", elecName);
		return result;
	}
	
	/**
	 * 
	 * @Title: getDealElecCount
	 * @Description: TODO(交易电量统计)
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月4日 下午7:04:37
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月4日 下午7:04:37
	 * @since  1.0.0
	 */
			
	@SuppressWarnings("unchecked")
	private Map<String ,Object> getDealElecCount() {
		List<String> yms = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date date = new Date();
		for(int i = 1 ; i < 13 ; i++){
			String ym = df.format(date.getTime()) + (i < 10 ? "0"+i : i);
			yms.add(ym);
		}
		String loginUserId = SystemServiceUtil.getLoginUserInfo().getId();
		Map<String , Object> params = new HashMap<>();
		params.put("ym", yms);
		params.put("loginUserId", loginUserId);
		List<Map<String ,Object>> list = (List<Map<String, Object>>) dao.getBySql("overView.sql.getDealElecCount", params);
		List<String> yearMonthPf = new ArrayList<String>();
		List<String> reportPq = new ArrayList<String>();
		List<String> reportPqName = new ArrayList<String>();
		List<String> purchasePq = new ArrayList<String>();
		List<String> purchasePqName = new ArrayList<String>();
		List<String> delivryPq = new ArrayList<String>();
		List<String> delivryPqName = new ArrayList<String>();
		for(int i=0; i<list.size() ;i++){
			Map<String ,Object> map = list.get(i);
			String yearMonthPf1 = map.get("yearMonthPf") == null ? "" : map.get("yearMonthPf").toString();
			String reportPq1 = map.get("reportPq") == null ? "0" : map.get("reportPq").toString();
			String reportPqName1 = map.get("reportPqName").toString();
			String purchasePq1 = map.get("purchasePq") == null ? "0" : map.get("purchasePq").toString();
			String purchasePqName1 = map.get("purchasePqName").toString();
			String delivryPq1 =  map.get("delivryPq") == null ? "0" : map.get("delivryPq").toString();
			String delivryPqName1 = map.get("delivryPqName") == null ? "" : map.get("delivryPqName").toString();
			
			yearMonthPf.add(yearMonthPf1);
			reportPq.add(reportPq1);
			reportPqName.add(reportPqName1);
			purchasePq.add(purchasePq1);
			purchasePqName.add(purchasePqName1);
			delivryPq.add(delivryPq1);
			delivryPqName.add(delivryPqName1);
		}
		Map<String ,Object> result = new HashMap<String, Object>();
		result.put("yearMonthPf", yearMonthPf);
		result.put("reportPq", reportPq);
		result.put("reportPqName", reportPqName);
		result.put("purchasePq", purchasePq);
		result.put("purchasePqName", purchasePqName);
		result.put("delivryPq", delivryPq);
		result.put("delivryPqName", delivryPqName);
		return result;
	}
	
	/**
	 * 
	 * @Title: getUserResource
	 * @Description: TODO(查看用户权限)
	 * @param userId
	 * @return 
	 * List<String>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月23日 上午10:51:02
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月23日 上午10:51:02
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	private List<String> getUserResource(String userId){
		//查看用户使用权限的 不需要加权限过滤
		List<String> resource = (List<String>) dao.getBySql("overView.sql.getUserResource", userId);	
		return resource;
		
	}
	
	/**
	 * 
	 * @Title: getSellMonLcBid
	 * @Description: TODO(年售电量分月明细)
	 * @param userId
	 * @return 
	 * List<String>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年1月31日 下午16：51
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年1月31日 下午16：51
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String ,Object>> getSellMonLcBid( Map<String,String> params) throws Exception {
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list =  (List<Map<String, Object>>) dao.getBySql("overView.sql.getSellMonLcBid", params);
		Parameter.isFilterData.set(false);
		List<Map<String,Object>> result = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i = 1 ; i < 13; i++){
			if(i<10){
				map.put("mon0"+i, 0);
			}else{
				map.put("mon"+i, 0);
			}
		}
		if(list != null && list.size() > 0){
			for(Map<String,Object> pqMap : list){
				String key = "mon" + pqMap.get("ym");
				map.put(key, pqMap.get("pq"));
			}
		}
		result.add(map);
		return result;
		 
	}
	
	/**
	 * 
	 * @Title: getPhMonLcBid
	 * @Description: TODO(年购电量分月明细)
	 * @param userId
	 * @return s
	 * List<String>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年2月1日 下午18：20
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年2月1日 下午18：20
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPhMonLcBid(Map<String, String> params) throws Exception {
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list =  (List<Map<String, Object>>) dao.getBySql("overView.sql.getPhMonLcBid", params);
		Parameter.isFilterData.set(false);
		List<Map<String,Object>> resultList = new ArrayList<>();
		double monthBidPqTotal = 0;	//月度竞价电量合计
		double yearBidPqTotal = 0;	//年度竞价电量合计
		double monthLcPqTotal = 0;	//月度双边电量合计
		double yearLcPqTotal = 0;	//年度双边电量合计
		double monthGpPqTotal = 0;	//月度挂牌电量合计
		double yearGpPqTotal = 0;	//年度挂牌电量合计
		double total = 0;			//总合计
		if(list != null && list.size() > 0){
			Map<String, Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
			for(Map<String ,Object> pqMap : list){
				if(pqMap.get("ym").toString().length() > 4){
					map.put(pqMap.get("ym").toString().substring(4), pqMap);
				}
			}
			for(int i = 1 ; i < 13 ; i++){
				String key = null;
				if(i<10){
					key = "0"+i;
				}else{
					key = ""+i;
				}
				if(map.containsKey(key)){
					map.get(key).put("ym", i+"月");
					resultList.add(map.get(key));
					monthBidPqTotal += Double.valueOf(map.get(key).get("monthBidPq").toString());	
					yearBidPqTotal += Double.valueOf(map.get(key).get("yearBidPq").toString());	
					monthLcPqTotal += Double.valueOf(map.get(key).get("monthLcPq").toString());	
					yearLcPqTotal += Double.valueOf(map.get(key).get("yearLcPq").toString());	
					monthGpPqTotal += Double.valueOf(map.get(key).get("monthGpPq").toString());
					yearGpPqTotal += Double.valueOf(map.get(key).get("yearGpPq").toString());
					total += Double.valueOf(map.get(key).get("totalPq").toString());
				}else{
					Map<String ,Object> monthMap = new HashMap<String ,Object>();
					monthMap.put("ym", i+"月");
					monthMap.put("monthBidPq", 0);
					monthMap.put("yearBidPq", 0);
					monthMap.put("monthLcPq", 0);
					monthMap.put("yearLcPq", 0);
					monthMap.put("monthGpPq", 0);
					monthMap.put("yearGpPq", 0);
					monthMap.put("totalPq", 0);
					resultList.add(monthMap);
				}
			}
			Map<String, Object> totalMap = new HashMap<>();
			totalMap.put("ym", "合计");
			totalMap.put("monthBidPq", monthBidPqTotal);
			totalMap.put("yearBidPq", yearBidPqTotal);
			totalMap.put("monthLcPq", monthLcPqTotal);
			totalMap.put("yearLcPq", yearLcPqTotal);
			totalMap.put("monthGpPq", monthGpPqTotal);
			totalMap.put("yearGpPq", yearGpPqTotal);
			totalMap.put("totalPq", total);
			resultList.add(totalMap);
		}
		return resultList;
	}
	
	/**
	 * 
	 * @Title: getConsScale
	 * @Description: TODO(用户规模明细)
	 * @param 
	 * @return s
	 * List<String>
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2018年2月6日 下午16：00
	 * <b>修 改 人：</b>zhangzhao<br/>
	 * <b>修改时间:</b>2018年2月6日 下午16：00
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getConsScale() throws Exception {
		Map<String, Object> result = new HashMap<>();
		//合同用户 意向用户数量 
		Parameter.isFilterData.set(true);
		List<Map<Object, String>> getSumConsType = (List<Map<Object, String>>) dao.getBySql("overView.sql.getSumConsType", null);
		Parameter.isFilterData.set(false);
		//发电企业 
		Parameter.isFilterData.set(true);
		List<String> getElecCom = (List<String>) dao.getBySql("overView.sql.getElecCom", null);
		Parameter.isFilterData.set(false);
		//用电分类
		Parameter.isFilterData.set(true);
		List<OverViewDetail> getUserScale = (List<OverViewDetail>) dao.getBySql("overView.sql.getUserScale", null);
		Parameter.isFilterData.set(false);
		 
		//用户电压等级
		 List<String>  voltCount = new ArrayList<>(); //数量
		 List<String>  voltName = new ArrayList<>();	//名
		Parameter.isFilterData.set(true);
		List<Map<String, Object>> getVoltageClass = (List<Map<String, Object>>) dao.getBySql("overView.sql.voltageClass", null);
		Parameter.isFilterData.set(false);
		for(int i=0 ;i<getVoltageClass.size() ;i++){
			Map<String, Object> map = getVoltageClass.get(i);
			String voltCount1 = map.get("voltCount") == null ? "0" : map.get("voltCount").toString();
			String voltName1 = map.get("voltName") == null ? "0" : map.get("voltName").toString();
			voltCount.add(voltCount1);
			voltName.add(voltName1);
		}
		result.put("getSumConsType", getSumConsType);
		result.put("getElecCom", getElecCom);
		result.put("getUserScale", getUserScale);
		result.put("voltCount", voltCount);
		result.put("voltName", voltName);
		return result;
	}
	
	/**
	 * 
	 * @Title: getConsBar<br/>
	 * @Description:TODO(用户规明细模柱状图)<br/>
	 * @param year
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @throws Exception <br/>
	 * QueryResult<><br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年2月6日 下午16：00
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年2月6日 下午16：00
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getConsBar(String year) throws Exception {
		Map<String, Object> result = new HashMap<>();
		/*sql语句子查询过多 手动加了权限过滤*/
		String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		Map<String ,Object> param = new HashMap<>();
		param.put("orgNo", orgNo);
		param.put("year", year);
		//用户数
		Parameter.isFilterData.set(true);
		Map<String ,Object> getConsSum = (Map<String, Object>) dao.getOneBySQL("overView.sql.getConsSum", param);
		Parameter.isFilterData.set(false);
		//去年用户数 11 12月的
		int lastYear = Integer.parseInt(year) - 1;
		param.put("lastYear", lastYear);
		Parameter.isFilterData.set(true);
		List<Map<String, Object>> upYearConsSum = (List<Map<String, Object>>) dao.getBySql("overView.sql.upYearConsSum", param);
		Parameter.isFilterData.set(false);
		result.put("getConsSum", getConsSum);
		result.put("upYearConsSum", upYearConsSum);
		return result;
	}
	
	
	/**
	 * @Title: getDeviationInfoByYm
	 * @Description: 获取指点月份的偏差用户数和总用电偏差率
	 * @param ym  yyyy-MM格式
	 * @return
	 * @throws Exception 
	 * Map<String,String>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月14日 下午6:52:11
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月14日 下午6:52:11
	 * @since  1.0.0
	 */
	public  Map<String, String> getDeviationInfoByYm(String ym) throws Exception{
		Map<String, String> resultMap = new HashMap<>();
		//如果ym为空，则去当月的数据
		if(ym == null || ym.equals("")) {
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM");
			ym  = dataFormat.format(new Date());
		}
		//获取偏差用户数
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("overView.sql.getDeviationConsCountByYm",ym);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		resultMap.put("consCount", String.valueOf(total));
		
		//获取指定月份总偏差率
		Parameter.isFilterData.set(true);
		Object deviationRate = dao.getOneBySQL("overView.sql.getDeviationRateByYm",ym);
		Parameter.isFilterData.set(false);
		BigDecimal deviationRateInt = deviationRate == null ? BigDecimal.ZERO : new BigDecimal(deviationRate.toString());
		deviationRateInt = deviationRateInt.setScale(2, BigDecimal.ROUND_HALF_UP);
		resultMap.put("deviationRate", String.valueOf(deviationRateInt));
		return resultMap;
	}
}
package com.hhwy.purchaseweb.crm.scelectricityinfo.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ForecastPqDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.QueryDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ScElectricityInfoVo;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.TreeGridDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.service.ScElectricityInfoService;
import com.hhwy.selling.domain.ScConsElectricity;

/**
 * ScElectricityInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScElectricityInfoServiceImpl implements ScElectricityInfoService {

	public static final Logger log = LoggerFactory.getLogger(ScElectricityInfoServiceImpl.class);

	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @Title: getTreeGridByPage<br/>
	 * @Description: 查询树表格数据<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * @throws Exception <br/>
	 * QueryResult<TreeGridDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 上午11:17:36
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 上午11:17:36
	 * @since  1.0.0
	 */
	public QueryResult<TreeGridDetail> getTreeGridByPage(ScElectricityInfoVo scElectricityInfoVo) throws Exception{
		QueryResult<TreeGridDetail> queryResult = new QueryResult<TreeGridDetail>();
		long total = getConsElecTotal(scElectricityInfoVo);
		List<TreeGridDetail> treeList = getConsElecGrid(scElectricityInfoVo);
//		long total = getTreeCountByParams(scElectricityInfoVo);
		/*long total = getTreeCountByParams(scElectricityInfoVo); //查询根节点数量  暂去树结构
		if(scElectricityInfoVo.getId() == null || scElectricityInfoVo.getId().equals("")){ //判断是否是查询子节点数据还是查询根节点
			treeList = getParentListByParams(scElectricityInfoVo);
			ConvertListUtil.convert(treeList);
		}else{
			treeList = getChildrenListByConsId(scElectricityInfoVo);
		}*/
		queryResult.setTotal(total);
		queryResult.setData(treeList);
		return queryResult;
	}
	
	/**
	 * @Title: getSCConsElectricity<br/>
	 * @Description:TODO(根据用户id和年份‘更新’用户历史用电信息预测电量字段数据)<br/>
	 * @param id
	 * @throws Exception <br/>
	 * QueryResult<TreeGridDetail><br/>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年3月14日 下午3:20:10
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年3月14日 下午3:20:10
	 * @since  1.0.0
	 */
	@Override
	public void getSCConsElectricity(Map<String,String> params) {
		dao.updateBySql("scElectricityInfo.sql.getSCConsElectricity", params); 
	}
	
	
		/**
		 * 
		 * @Title: getConsElecGrid<br/>
		 * @Description:TODO(客户用电信息列表)<br/>
		 * @return
		 * List<TreeGridDetail>
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月13日 下午7:11:48
		 * <b>修 改 人：</b>zhagnzhao<br/>
		 * <b>修改时间:</b>2018年3月13日 下午7:11:48
		 * @since  1.0.0
		 */
	@SuppressWarnings("unchecked")
	public List<TreeGridDetail> getConsElecGrid(ScElectricityInfoVo scElectricityInfoVo) throws Exception{
		Parameter.isFilterData.set(true);
		List<TreeGridDetail> list = (List<TreeGridDetail>) dao.getBySql("scElectricityInfo.sql.getConsElecGrid", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		return list;
		
	}
		/**
		 * 
		 * @Title: getConsElecTotal<br/>
		 * @Description:TODO(列表数据条数)<br/>
		 * @param scElectricityInfoVo
		 * @return
		 * Integer
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月13日 下午7:35:59
		 * <b>修 改 人：</b>zhagnzhao<br/>
		 * <b>修改时间:</b>2018年3月13日 下午7:35:59
		 * @since  1.0.0
		 */
	public Integer getConsElecTotal(ScElectricityInfoVo scElectricityInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scElectricityInfo.sql.getConsElecTotal", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 
	 * @Title: getTreeCountByParams<br/>
	 * @Description: 查询根节点数量<br/> 暂去树结构
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 上午11:07:17
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 上午11:07:17
	 * @since  1.0.0
	 */
	public Integer getTreeCountByParams(ScElectricityInfoVo scElectricityInfoVo){
		Parameter.isFilterData.set(true);
		Object obj = dao.getOneBySQL("scElectricityInfo.sql.getTreeCountByParams", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		int total = obj == null ? 0 : Integer.valueOf(obj.toString());
		return total;
	} 
	
	/**
	 * 
	 * @Title: getParentListByParams<br/>
	 * @Description: 查询根节点列表<br/> 暂去树结构
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * List<TreeGridDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 上午11:07:42
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 上午11:07:42
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<TreeGridDetail> getParentListByParams(ScElectricityInfoVo scElectricityInfoVo){
		Parameter.isFilterData.set(true);
		List<TreeGridDetail> list = (List<TreeGridDetail>) dao.getBySql("scElectricityInfo.sql.getParentListByParams", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		return list;
	}
	
	/**
	 * 
	 * @Title: getChildrenListByConsId<br/>
	 * @Description: 查询子节点列表<br/>  暂去树结构
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * List<TreeGridDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 上午11:11:03
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 上午11:11:03
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<TreeGridDetail> getChildrenListByConsId(ScElectricityInfoVo scElectricityInfoVo){//根据用户id查询
		List<TreeGridDetail> list = (List<TreeGridDetail>) dao.getBySql("scElectricityInfo.sql.getChildrenListByConsId", scElectricityInfoVo);
		return list;
	}
	
	/**
	 * 
	 * @Title: getForecastPqList<br/>
	 * @Description: 查询预测电量数据列表<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * List<ForecastPqDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 下午9:21:49
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 下午9:21:49
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<ForecastPqDetail> getForecastPqList(ScElectricityInfoVo scElectricityInfoVo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("consId", scElectricityInfoVo.getConsId());
		map.put("year", scElectricityInfoVo.getYear());
		List<ForecastPqDetail> forecastPqDetailList = new ArrayList<ForecastPqDetail>();
		for(int a = 0; a<12; a++){
			ForecastPqDetail forecastPqDetail = new ForecastPqDetail();
			forecastPqDetailList.add(forecastPqDetail);
		}
		for(int a = 0; a < 4; a++){
			map.put("num", a);
			List<BigDecimal> historyList = (List<BigDecimal>) dao.getBySql("scElectricityInfo.sql.getHistoryData", map);
			if(historyList != null && !historyList.isEmpty()){
				int i = 0;
				for (BigDecimal bigDecimal : historyList) {
					if(a == 0){
						forecastPqDetailList.get(i).setYearDPq(bigDecimal);
					}
					if(a == 1){						
						forecastPqDetailList.get(i).setYearCPq(bigDecimal);
					}
					if(a == 2){
						forecastPqDetailList.get(i).setYearBPq(bigDecimal);
					}
					if(a == 3){
						forecastPqDetailList.get(i).setYearAPq(bigDecimal);
					}
					i++;
				}
				
			}
		}
		List<ForecastPqDetail> list = (List<ForecastPqDetail>) dao.getBySql("scElectricityInfo.sql.getForecastPqList", scElectricityInfoVo);
		
		if(list != null && !list.isEmpty()){
			for(int b = 0; b<list.size(); b++){
				if(forecastPqDetailList.get(b).getYearAPq() != null ){
					list.get(b).setYearAPq(forecastPqDetailList.get(b).getYearAPq());
				}
				if(forecastPqDetailList.get(b).getYearBPq() != null){					
					list.get(b).setYearBPq(forecastPqDetailList.get(b).getYearBPq());
				}
				if(forecastPqDetailList.get(b).getYearCPq() != null){					
					list.get(b).setYearCPq(forecastPqDetailList.get(b).getYearCPq());		
				}
				if(forecastPqDetailList.get(b).getYearDPq() != null){
					list.get(b).setYearDPq(forecastPqDetailList.get(b).getYearDPq());		
				}
			}
			splitJoint(list);
		}else{
			splitJoint(forecastPqDetailList);
			return forecastPqDetailList;
		}
		
		return list;
	}
	
	public void splitJoint(List<ForecastPqDetail> list){
			BigDecimal yearAPqSum = new BigDecimal(0);
			BigDecimal yearBPqSum = new BigDecimal(0);
			BigDecimal yearCPqSum = new BigDecimal(0);
			BigDecimal yearDPqSum = new BigDecimal(0);
			BigDecimal dataForecastPqSum = new BigDecimal(0);
			BigDecimal personForecastPqSum = new BigDecimal(0);
			int a = 1;
			for (ForecastPqDetail forecastPqDetail : list) {
				if (forecastPqDetail.getYm() != null && !forecastPqDetail.getYm().equals("")) {
					String m = forecastPqDetail.getYm().substring(4);
					m = Integer.toString(Integer.parseInt(m))+"月";
					forecastPqDetail.setMonth(m);
				}else{
					forecastPqDetail.setMonth(a+"月");
					a++;
				}
				yearAPqSum = forecastPqDetail.getYearAPq() == null ? yearAPqSum : yearAPqSum.add(forecastPqDetail.getYearAPq());
				yearBPqSum = forecastPqDetail.getYearBPq() == null ? yearBPqSum : yearBPqSum.add(forecastPqDetail.getYearBPq());
				yearCPqSum = forecastPqDetail.getYearCPq() == null ? yearCPqSum : yearCPqSum.add(forecastPqDetail.getYearCPq());
				yearDPqSum = forecastPqDetail.getYearDPq() == null ? yearDPqSum : yearDPqSum.add(forecastPqDetail.getYearDPq());
				dataForecastPqSum = forecastPqDetail.getDataForecastPq() == null ? dataForecastPqSum : dataForecastPqSum.add(forecastPqDetail.getDataForecastPq());				
				personForecastPqSum = forecastPqDetail.getPersonForecastPq() == null ? personForecastPqSum : personForecastPqSum.add(forecastPqDetail.getPersonForecastPq());
			}
			ForecastPqDetail forecastPqDetail = new ForecastPqDetail();
			forecastPqDetail.setYearAPq(yearAPqSum);
			forecastPqDetail.setYearBPq(yearBPqSum);
			forecastPqDetail.setYearCPq(yearCPqSum);
			forecastPqDetail.setYearDPq(yearDPqSum);
			forecastPqDetail.setDataForecastPq(dataForecastPqSum);
			forecastPqDetail.setPersonForecastPq(personForecastPqSum);
			forecastPqDetail.setMonth("合计");
			list.add(forecastPqDetail);
	}
	
	/**
	 * 
	 * @Title: calculateForecastPq<br/>
	 * @Description: 测算电量<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * @throws Exception <br/>
	 * List<BigDecimal><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月2日 下午8:18:03
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月2日 下午8:18:03
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<BigDecimal> calculateForecastPq(ScElectricityInfoVo scElectricityInfoVo) throws Exception{
		if(scElectricityInfoVo.getConsId() == null || scElectricityInfoVo.getConsId().equals("") || scElectricityInfoVo.getYear() == null || scElectricityInfoVo.getYear().equals("")){
			throw new Exception("用户id或年份为空！");
		}
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		List<Map<String, Object>> pqList = (List<Map<String, Object>>) dao.getBySql("scElectricityInfo.sql.calculateForecastPq", scElectricityInfoVo);
		
		BigDecimal sum = new BigDecimal(0);
		for(int a = 0; a<13; a++){		
			if(a == 12){
				list.add(sum);
			}else{
				BigDecimal forPq = BigDecimal.ZERO;
				if(pqList != null && pqList.size() > 0){
					for (Map<String, Object> map : pqList) {
						if(Integer.parseInt(map.get("ym").toString()) == a-1 && !map.get("dataForecastPq").toString().equals("")){
							forPq = new BigDecimal(map.get("dataForecastPq").toString());
							break;
						}
					}
				}
				sum = sum.add(forPq);
				list.add(forPq);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: saveScConsElectricityList<br/>
	 * @Description: 保存测算电量列表<br/>
	 * @param scElectricityInfoVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月3日 上午10:11:12
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月3日 上午10:11:12
	 * @since  1.0.0
	 */
	public void saveScConsElectricityList(ScElectricityInfoVo scElectricityInfoVo) {
		saveScConsElectricityList(scElectricityInfoVo.getScConsElectricitieList());
	}
	
	/**
	 * 
	 * @Title: saveScConsElectricityList<br/>
	 * @Description: 保存测算电量<br/>
	 * @param scConsElectricityList <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月21日 下午6:23:59
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月21日 下午6:23:59
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public void saveScConsElectricityList(List<ScConsElectricity> scConsElectricityList) {
		if(scConsElectricityList != null && scConsElectricityList.size() > 0){
			//判断该用户此年份下是否存在用电信息，若存在，做更新操作
			Map<String,Object> map = new HashMap<String,Object>();
			String year = scConsElectricityList.get(0).getYm().substring(0,4);
			map.put("year", year);
			map.put("consId", scConsElectricityList.get(0).getConsId());
			List<ScConsElectricity> list = (List<ScConsElectricity>) dao.getBySql("scConsElectricity.sql.getScConsElectricityByConsIdAndYm", map);
			if(list != null && list.size() > 0){
				dao.updateBySql("scConsElectricity.sql.updateScConsElectricityByConsIdAndYm", scConsElectricityList);
			}else{
				for (ScConsElectricity scConsElectricity : scConsElectricityList) {
					scConsElectricity.setId(PlatformTools.getID());
					scConsElectricity.setOrgNo(OrgUtil.getOrgNoByUser());
				}
				dao.saveList(scConsElectricityList);
			}
		}
	}
	
	/**
	 * 
	 * @Title: updateScConsElectricity<br/>
	 * @Description: 更新测算电量列表<br/>
	 * @param scElectricityInfoVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月3日 上午10:11:30
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月3日 上午10:11:30
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateScConsElectricity(ScElectricityInfoVo scElectricityInfoVo) throws Exception {
		
		List<ScConsElectricity> list = scElectricityInfoVo.getScConsElectricitieList();
		if(list != null && list.size()>0){
			for (ScConsElectricity scConsElectricity : list) {
				dao.updateBySql("scElectricityInfo.sql.updateScConsElectricity", scConsElectricity);
			}
		}else{
			throw new Exception("数据为空");
		}
		
	}
	
	public void updateScConsElectricity(ScConsElectricity scConsElectricity) {
		dao.update(scConsElectricity);
	}
	
	/**
	 * 
	 * @Title: getConsInfoByConsId<br/>
	 * @Description: 根据用户id查询用户信息<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * QueryDetail<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月3日 上午10:07:46
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月3日 上午10:07:46
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public QueryDetail getConsInfoByConsId(ScElectricityInfoVo scElectricityInfoVo){
		List<QueryDetail> list = (List<QueryDetail>) dao.getBySql("scElectricityInfo.sql.getConsInfoByConsId", scElectricityInfoVo);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return new QueryDetail();
	}
	
	/**
	 * @Title: forecast<br/>
	 * @Description:TODO(查询当前用户有无测算权限)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月10日 下午5:31:31
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月10日 下午5:31:31
	 * @since  1.0.0
	 */
	@Override
	public String getForecastRes() {
		String userId = SystemServiceUtil.getLoginUserInfo().getId();
		Object oneBySQL = dao.getOneBySQL("scElectricityInfo.sql.getForecastRes", userId);
		String forecastResName = oneBySQL == null  ? null : oneBySQL.toString();
		return forecastResName;
	}
	
	
													/*					月度电量预测							*/
	
	/**
	 * @Title: getListData<br/>
	 * @Description:TODO(月度电量预测，主列表数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * @throws Exception
	 * QueryResult<ForecastPqDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月15日 下午1:49:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月15日 下午1:49:51
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<ForecastPqDetail> getListData(ScElectricityInfoVo scElectricityInfoVo) throws Exception {
		QueryResult<ForecastPqDetail> queryResult = new QueryResult<ForecastPqDetail>();
		Parameter.isFilterData.set(true);
		List<ForecastPqDetail> list = (List<ForecastPqDetail>) dao.getBySql("scElectricityInfo.sql.getListData", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		long total = getListDataCount(scElectricityInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(list);
		return queryResult;
	}
	
	private long getListDataCount(ScElectricityInfoVo scElectricityInfoVo) {
		Object oneBySQL = dao.getOneBySQL("scElectricityInfo.sql.getListDataCount", scElectricityInfoVo);
		return oneBySQL == null ? 0: Long.parseLong(oneBySQL.toString());
	}

	/**
	 * @Title: checkNextMonthData<br/>
	 * @Description:TODO(查询下个月预测数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月14日 下午2:34:06
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月14日 下午2:34:06
	 * @since  1.0.0
	 */
	@Override
	public Boolean checkNextMonthData(ScElectricityInfoVo scElectricityInfoVo) {
		Parameter.isFilterData.set(true);
		Object oneBySQL = dao.getOneBySQL("scElectricityInfo.sql.getDownMonthData", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		Boolean flag = false;
		if(oneBySQL != null && Integer.valueOf(oneBySQL.toString())> 0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title: getMonthPqForetData<br/>
	 * @Description:TODO(年月下，用户的负荷数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月14日 下午3:15:38
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月14日 下午3:15:38
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<ForecastPqDetail> getMonthPqForetData(ScElectricityInfoVo scElectricityInfoVo) throws Exception {
		QueryResult<ForecastPqDetail> queryResult = new QueryResult<ForecastPqDetail>();
		String ym = scElectricityInfoVo.getYm().replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date parse = sdf.parse(ym);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		//上个月ym
		calendar.add(Calendar.MONTH, -1);
		String upMonthYm = sdf.format(calendar.getTime());
		scElectricityInfoVo.setUpMYm(upMonthYm);
		//去年ym
		calendar.setTime(parse);
		calendar.add(Calendar.YEAR, -1);
		String upYearYm = sdf.format(calendar.getTime());
		scElectricityInfoVo.setUpYYm(upYearYm);
		
		Parameter.isFilterData.set(true);
		List<ForecastPqDetail> list = (List<ForecastPqDetail>) dao.getBySql("scElectricityInfo.sql.getMonthPqForetData", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		
		scElectricityInfoVo.setPagingFlag(false);
		Parameter.isFilterData.set(true);
		List<ForecastPqDetail> totalList = (List<ForecastPqDetail>) dao.getBySql("scElectricityInfo.sql.getMonthPqForetData", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		
		//计算 页面所需展示合计的数据
		list.add(this.totalListDetail(totalList));
		
		long total = getMonthPqForetDataCount(scElectricityInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(list);
		return queryResult;
	}
	private long getMonthPqForetDataCount(ScElectricityInfoVo scElectricityInfoVo) {
		Parameter.isFilterData.set(true);
		Object oneBySQL = dao.getOneBySQL("scElectricityInfo.sql.getMonthPqForetDataCount", scElectricityInfoVo);
		Parameter.isFilterData.set(false);
		return oneBySQL == null ? 0: Long.parseLong(oneBySQL.toString());
	}
	
	/*
	 * 计算列表数据合计
	 */
	public ForecastPqDetail totalListDetail(List<ForecastPqDetail> totalList){
		//计算 页面所需展示合计的数据
		ForecastPqDetail detail = null;
		for(ForecastPqDetail row : totalList){
			if(detail == null){
				detail = new ForecastPqDetail();
				detail.setConsName("合计");
			}
			//上月同期
			if(detail.getUpMonPracticalPq() != null){
				if(row.getUpMonPracticalPq() != null){
					detail.setUpMonPracticalPq(detail.getUpMonPracticalPq().add(row.getUpMonPracticalPq()));
				}
			}else{
				detail.setUpMonPracticalPq(row.getUpMonPracticalPq());
			}
			//去年同期
			if(detail.getUpYearPracticalPq() != null){
				if(row.getUpYearPracticalPq() != null){
					detail.setUpYearPracticalPq(detail.getUpYearPracticalPq().add(row.getUpYearPracticalPq()));
				}
			}else{
				detail.setUpYearPracticalPq(row.getUpYearPracticalPq());
			}
			//大数据预测
			if(detail.getDataForecastPq() != null){
				if(row.getDataForecastPq() != null){
					detail.setDataForecastPq(detail.getDataForecastPq().add(row.getDataForecastPq()));
				}
			}else{
				detail.setDataForecastPq(row.getDataForecastPq());
			}
			//人工预测
			if(detail.getPersonForecastPq() != null){
				if(row.getPersonForecastPq() != null){
					detail.setPersonForecastPq(detail.getPersonForecastPq().add(row.getPersonForecastPq()));
				}
			}else {
				detail.setPersonForecastPq(row.getPersonForecastPq());
			}
			//实际电量
			if(detail.getPracticalPq() != null){
				if(row.getPracticalPq() != null){
					detail.setPracticalPq(detail.getPracticalPq().add(row.getPracticalPq()));
				}
			}else{
				detail.setPracticalPq(row.getPracticalPq());
			}
		}
		return detail;
	}
	
	/**
	 * 月度电量预测 删除共鞥你
	 * @param ym: yyyy-mm
	 * by-zhangzhao
	 */
	@SuppressWarnings("unused")
	@Override
	public void deleteMonthPqFore(ScElectricityInfoVo scElectricityInfoVo) {
		int updateBySql = dao.updateBySql("scElectricityInfo.sql.updateMonthPqFore", scElectricityInfoVo);
	}

	/**
	 * @Title: saveMonPqForeList<br/>
	 * @Description:TODO(月度电量预测保存)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月16日 上午10:32:45
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月16日 上午10:32:45
	 * @since  1.0.0
	 */
	@Override
	public void saveMonPqForeList(ScElectricityInfoVo scElectricityInfoVo) {
		dao.saveList(scElectricityInfoVo.getScConsElectricitieList());
	}

	/**
	 * @Title: exportExcel<br/>
	 * @Description:TODO(月度电量预测导出)<br/>
	 * @param ym  yyyy-MM
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月19日 上午9:44:06
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月19日 上午9:44:06
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(String ym, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String replace = ym.replace("-", "");
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("ym", replace);
		hashMap.put("pagingFlag", false);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date parse = sdf.parse(replace);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		//上个月ym
		calendar.add(Calendar.MONTH, -1);
		String upMonthYm = sdf.format(calendar.getTime());
		hashMap.put("upMYm", upMonthYm);
		//去年ym
		calendar.setTime(parse);
		calendar.add(Calendar.YEAR, -1);
		String upYearYm = sdf.format(calendar.getTime());
		hashMap.put("upYYm", upYearYm);
		//导出数据
		Parameter.isFilterData.set(true);
		List<ForecastPqDetail> resultList = (List<ForecastPqDetail>) dao.getBySql("scElectricityInfo.sql.getMonthPqForetData", hashMap);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(resultList);
		//合计数据
		resultList.add(this.totalListDetail(resultList));
		
		//获取模版文件
		String excelPath=request.getSession().getServletContext().getRealPath("/plugins/cloud-purchase-web/crm/month-pq-forecast/月度电量预测导出.xlsx");//模板路径
		response.reset();//去除空白行
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		String newName = URLEncoder.encode("月度电量预测导出" + System.currentTimeMillis() + ".xlsx","UTF-8"); //设置导出的文件名称
		request.setCharacterEncoding("UTF-8");//设置request的编码方式，防止中文乱码
    	response.setHeader("Content-Disposition","attachment;filename=\"" + newName + "\"");  
    	InputStream fio = new FileInputStream(excelPath);//将模板读入流
    	ServletOutputStream out = response.getOutputStream();
    	try {  
        	XSSFWorkbook workbook=new XSSFWorkbook(fio);//创建excel(xlsx格式)
			XSSFSheet sheet=workbook.getSheetAt(0);     //创建工作薄sheet
			// 数据 Excel 所需字段
            String[] str = {"ConsName","VoltCodeName","UpYearPracticalPq","UpMonPracticalPq","DataForecastPq","PersonForecastPq","PracticalPq","Remark"};
            
			int rowCount = 0; //第1行存数据
			int cellCount = 0;//第3列开始
			//获取表头
			List<String> excelHead = this.getExcelHead(replace);
			//创建表头
			for(int i=0; i < 1 ; i++){
				XSSFRow row = sheet.createRow(rowCount);						//创建行
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
	            for(int j = 0 ; j < excelHead.size() ; j++){
	            	Cell borderCell = row.createCell(cellCount++);					//创建列
	            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
            			borderCell.setCellValue(excelHead.get(j));							//列数据
	            }
	            rowCount ++;
	            cellCount = 0;
			}
//			创建表格数据
			for(int i=0; i < resultList.size() ; i++){
				XSSFRow row = sheet.createRow(rowCount);						//创建行
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
	            
	            ForecastPqDetail forecastPqDetail = resultList.get(i);
	            //创建列
	            for(int j = 0 ; j < str.length ; j++){
	            	Cell borderCell = row.createCell(cellCount++);					//创建列
	            	borderCell.setCellStyle(borderStyle);		  					//单元格样式	
	            	Class<? extends ForecastPqDetail> importVoClass = forecastPqDetail.getClass();	//拼接get方法，取对象里的值
	            	Method method = importVoClass.getMethod("get"+str[j]);
	            	
            		if( method.invoke(forecastPqDetail) != null){
            			String value = method.invoke(forecastPqDetail).toString();
            			borderCell.setCellValue(value);							//列数据
            		}else{
            			borderCell.setCellValue("");							//列数据
            		}
	            }
	            rowCount ++;
	            cellCount = 0;
	            //最后合计行 合并列
	            if(i == resultList.size() - 1){
					CellRangeAddress region = new CellRangeAddress(i+1,i+1,0,2);
					sheet.addMergedRegion(region);
				}
			}
			
			workbook.write(out);
            out.flush();  
        } catch (FileNotFoundException e) {
			System.out.println("月度电量预测模板没有找到");
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

	/*
	 * 导出数据的表头
	 */
	private List<String> getExcelHead(String ym) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date parse = sdf.parse(ym);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		//上个月ym
		calendar.add(Calendar.MONTH, -1);
		String upMonthYm = sdf.format(calendar.getTime());
		//去年ym
		calendar.setTime(parse);
		calendar.add(Calendar.YEAR, -1);
		String upYearYm = sdf.format(calendar.getTime());
		
		List<String> list = new ArrayList<>();
		//用户名
		list.add("用户名称");
		//电压等级
		list.add("电压等级");
		//去年同期
		list.add(upYearYm.substring(0,4) + "年" + upYearYm.substring(4, 6) + "月电量");
		//上期
		list.add(upMonthYm.substring(0,4) + "年" + upMonthYm.substring(4, 6) + "月电量");
		//大数据
		list.add(ym.substring(0,4) + "年" + ym.substring(4, 6) + "月大数据预测电量");
		//人工核定
		list.add(ym.substring(0,4) + "年" + ym.substring(4, 6) + "月人工核定电量");
		//实际电量
		list.add(ym.substring(0,4) + "年" + ym.substring(4, 6) + "月实际电量");
		list.add("备注");
		
		return list;
	}
	

}
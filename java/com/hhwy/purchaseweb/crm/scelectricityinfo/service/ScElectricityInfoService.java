package com.hhwy.purchaseweb.crm.scelectricityinfo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ForecastPqDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.QueryDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ScElectricityInfoVo;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.TreeGridDetail;

/**
 * IScElectricityInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScElectricityInfoService {
	
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
	public QueryResult<TreeGridDetail> getTreeGridByPage(ScElectricityInfoVo scElectricityInfoVo) throws Exception;
	
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
	public List<ForecastPqDetail> getForecastPqList(ScElectricityInfoVo scElectricityInfoVo);
	
		/**
		 * @Title: getSCConsElectricity<br/>
		 * @Description:TODO(根据用户id和年份‘删除’用户历史用电信息预测电量字段数据)<br/>
		 * @param id
		 * @return
		 * Integer
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月14日 下午3:20:10
		 * <b>修 改 人：</b>zhagnzhao<br/>
		 * <b>修改时间:</b>2018年3月14日 下午3:20:10
		 * @since  1.0.0
		 */
	public void getSCConsElectricity(Map<String,String> params); 
	
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
	public List<BigDecimal> calculateForecastPq(ScElectricityInfoVo scElectricityInfoVo) throws Exception;
	
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
	public void updateScConsElectricity(ScElectricityInfoVo scElectricityInfoVo) throws Exception;
	
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
	public void saveScConsElectricityList(ScElectricityInfoVo scElectricityInfoVo);
	
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
	public QueryDetail getConsInfoByConsId(ScElectricityInfoVo scElectricityInfoVo);
	
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
	public String getForecastRes();
	
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
	public Boolean checkNextMonthData(ScElectricityInfoVo scElectricityInfoVo);
	
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
	public QueryResult<ForecastPqDetail> getMonthPqForetData(ScElectricityInfoVo scElectricityInfoVo) throws Exception;
	
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
	public QueryResult<ForecastPqDetail> getListData(ScElectricityInfoVo scElectricityInfoVo) throws Exception;

	/**
	 * 月度电量预测 删除共鞥你
	 * @param ym: yyyy-mm
	 * by-zhangzhao
	 */
	public void deleteMonthPqFore(ScElectricityInfoVo scElectricityInfoVo);

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
	public void saveMonPqForeList(ScElectricityInfoVo scElectricityInfoVo);

	/**
	 * @Title: exportExcel<br/>
	 * @Description:TODO(月度电量预测导出)<br/>
	 * @param ym
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月19日 上午9:44:06
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月19日 上午9:44:06
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void exportExcel(String ym, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
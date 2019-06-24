package com.hhwy.purchaseweb.archives.overview.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * <b>类 名 称：</b>OverViewService<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2017年12月29日 下午2:21:13<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface OverViewService {
	
	/**
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
	public Map<String,Object> getOverViewData(String userId) throws Exception;
	
	/**
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
	public List<Map<String, Object>> getSellMonLcBid(Map<String,String> params) throws Exception;
	
	/**
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
	public List<Map<String ,Object>> getPhMonLcBid(Map<String ,String> params)  throws Exception;
	
	/**
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
	public Map<String, Object> getConsScale() throws Exception;
	
		/**
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
	public  Map<String, Object> getConsBar(String year) throws Exception;
	
	/**
	 * @Title: getDeviationInfoByYm
	 * @Description: 获取指点月份的偏差用户数和总用电偏差率
	 * @param ym yyyy-MM格式
	 * @return
	 * @throws Exception 
	 * Map<String,String>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月14日 下午6:52:11
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月14日 下午6:52:11
	 * @since  1.0.0
	 */
	public  Map<String, String> getDeviationInfoByYm(String ym) throws Exception;
}
















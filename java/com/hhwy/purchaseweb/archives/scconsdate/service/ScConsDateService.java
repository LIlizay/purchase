package com.hhwy.purchaseweb.archives.scconsdate.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateDetail;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateVo;
import com.hhwy.selling.domain.ScConsDate;

/**
 * <b>类 名 称：</b>ScConsDateService<br/>
 * <b>类 描 述：</b><br/>			用户例日维护service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月12日 上午11:02:36<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface ScConsDateService{
	
	/**
	 * @Title: getScConsDateByPage
	 * @Description: 根据consId查询分页获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * QueryResult<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:35:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:35:36
	 * @since  1.0.0
	 */
	public QueryResult<ScConsDate> getScConsDateByPage(ScConsDateVo scConsDateVo) throws Exception;

	/**
	 * @Title: getScConsDateListByConsId
	 * @Description: 根据consId查询获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * List<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:34:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:34:36
	 * @since  1.0.0
	 */
	public List<ScConsDate> getScConsDateListByConsId(ScConsDateVo scConsDateVo) throws Exception;
	
	/**
	 * @Title: getScConsDateCountByConsId
	 * @Description: 根据consId查询获取对象ScConsDate数量
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return 
	 * Integer
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:35:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:35:20
	 * @since  1.0.0
	 */
	public Integer getScConsDateCountByConsId(ScConsDateVo scConsDateVo);
	
	/**
	 * @Title: getScConsDateListByConsId
	 * @Description: 根据consId查询获取对象ScConsDate列表
	 * @param scConsDateVo	有效属性为consId和分页属性
	 * @return
	 * @throws Exception 
	 * List<ScConsDate>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 下午4:34:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 下午4:34:36
	 * @since  1.0.0
	 */
	public ScConsDateDetail getLastScConsDateByConsId(String consId) throws Exception;
	
	/**
	 * @Title: saveScConsDateList
	 * @Description: 添加对象ScConsDate集合，不触发更新用电计划
	 * 					仅提供给：新增用户时批量保存例日数据，因为新增用户没有示数，所以不需要修改用电计划
	 * @param scConsDateList
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:23:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:23:10
	 * @since  1.0.0
	 */
	public void saveScConsDateList(List<ScConsDate> scConsDateList);
	
	/**
	 * @Title: saveScConsDate
	 * @Description: 添加对象ScConsDate，并更新用电计划
	 * @param scConsDate
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:44:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:44:25
	 * @since  1.0.0
	 */
	public void saveScConsDate(ScConsDate scConsDate)  throws Exception;
	
	/**
	 * @Title: saveScConsDate
	 * @Description: 更新对象ScConsDate，并更新用电计划
	 * @param scConsDate
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午1:44:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午1:44:25
	 * @since  1.0.0
	 */
	public void updateScConsDate(ScConsDate scConsDate)  throws Exception;

	/**
	 * @Title: deleteScConsDate
	 * @Description:  删除对象ScConsDate，并更新用电计划
	 * @param id 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:30:57
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:30:57
	 * @since  1.0.0
	 */
	public void deleteScConsDate(String id) throws Exception;
	
	/**
	 * @Title: deleteScConsDateByConsIds
	 * @Description: 根据consIds删除用户例日信息(物理删除)
	 * @param consIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月26日 下午2:17:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月26日 下午2:17:34
	 * @since  1.0.0
	 */
	public void deleteScConsDateByConsIds(String[] consIds);
	
	/**
	 * @Title: getDateAndDays<br/>
	 * @Description: 根据用户id、ym(yyyyMM格式)，返回用电年月的开始日期与结束日期和天数<br/>
	 * @param consId 用户id
	 * @param ym 用电年月
	 * @return Map<String,Object> 包含key为：startDate、endDate、days(天数 = 结束-开始+1)、usuallyDate,
	 * 			value类型依次为：String（yyyyMMdd格式）、String（yyyyMMdd格式）、int、string（例：25）<br/>
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 下午1:33:43
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 下午1:33:43
	 * @since  1.0.0
	 */
	public Map<String, Object> getDateAndDays(String consId, String ym) throws Exception;

}
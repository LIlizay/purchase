package com.hhwy.purchaseweb.archives.scindustrialzone.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneDetail;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneVo;
import com.hhwy.selling.domain.ScIndustrialZone;

/**
 * 
 * <b>类 名 称：</b>ScIndustrialZoneService<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月22日 下午1:57:23<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface ScIndustrialZoneService{
	
	/**
	 * 
	 * @Title: getScIndustrialZoneByPage
	 * @Description:(分页获取园区维护信息)
	 * @param scIndustrialZoneVo
	 * @return
	 * @throws Exception 
	 * QueryResult<ScIndustrialZoneDetail>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:57:35
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:57:35
	 * @since  1.0.0
	 */
	public QueryResult<ScIndustrialZoneDetail> getScIndustrialZoneByPage(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception;

	/**
	 * 
	 * @Title: getScIndustrialZoneListByParams
	 * @Description:(根据查询条件获取对象园区维护信息列表)
	 * @param scIndustrialZoneVo
	 * @return
	 * @throws Exception 
	 * List<ScIndustrialZoneDetail>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:58:13
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:58:13
	 * @since  1.0.0
	 */
	public List<ScIndustrialZoneDetail> getScIndustrialZoneListByParams(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception;
	
	/**
	 * 
	 * @Title: getScIndustrialZoneCountByParams
	 * @Description:(根据查询条件获取对象园区维护信息数量)
	 * @param scIndustrialZoneVo
	 * @return 
	 * Integer
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:01:29
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:01:29
	 * @since  1.0.0
	 */
	public Integer getScIndustrialZoneCountByParams(ScIndustrialZoneVo scIndustrialZoneVo);
	
	/**
	 * 
	 * @Title: getScIndustrialZoneOneByParams
	 * @Description:(根据查询条件获取单个园区维护对象)
	 * @param scIndustrialZoneVo
	 * @return
	 * @throws Exception 
	 * ScIndustrialZoneDetail
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:02:32
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:02:32
	 * @since  1.0.0
	 */
	public ScIndustrialZoneDetail getScIndustrialZoneOneByParams(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception;
	
	/**
	 * 
	 * @Title: getScIndustrialZoneById
	 * @Description:(根据ID获取园区维护对象)
	 * @param id
	 * @return 
	 * ScIndustrialZone
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:03:14
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:03:14
	 * @since  1.0.0
	 */
	public ScIndustrialZone getScIndustrialZoneById(String id);
	
	/**
	 * 
	 * @Title: saveScIndustrialZone
	 * @Description:(添加园区维护信息)
	 * @param scIndustrialZone 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:04:11
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:04:11
	 * @since  1.0.0
	 */
	public void saveScIndustrialZone(ScIndustrialZone scIndustrialZone);
	
	/**
	 * 
	 * @Title: saveScIndustrialZoneList
	 * @Description:(添加园区维护列表信息)
	 * @param scIndustrialZoneList 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:04:41
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:04:41
	 * @since  1.0.0
	 */
	public void saveScIndustrialZoneList(List<ScIndustrialZone> scIndustrialZoneList);
	
	/**
	 * 
	 * @Title: updateScIndustrialZone
	 * @Description:(更新园区维护信息)
	 * @param scIndustrialZone 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:05:10
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:05:10
	 * @since  1.0.0
	 */
	public void updateScIndustrialZone(ScIndustrialZone scIndustrialZone);
	
	/**
	 * 
	 * @Title: deleteScIndustrialZone
	 * @Description:(根据id数组删除园区维护信息)
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午2:06:42
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午2:06:42
	 * @since  1.0.0
	 */
	public void deleteScIndustrialZone(String[] ids);

	/**
	  * @Title: checkScIndustrialZone
	  * @Description: 园区重复性校验
	  * @param scIndustrialZone
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月9日 下午2:55:26
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月9日 下午2:55:26
	  * @since  1.0.0
	 */
	public void checkScIndustrialZone(ScIndustrialZone scIndustrialZone);

	/**
	 * @Title: getZoneInfoListByNames
	 * @Description: 根据园区名称获取园区id，     用户信息导入时用到
	 * @param zoneNames
	 * @return 
	 * List<ScIndustrialZoneDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午3:41:15
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午3:41:15
	 * @since  1.0.0
	 */
	public List<ScIndustrialZoneDetail> getZoneInfoListByNames(List<String> zoneNames);
}
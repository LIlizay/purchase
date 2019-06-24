package com.hhwy.purchaseweb.archives.scdevicerelation.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hhwy.collectionplatform.all.domain.DeviceDetail;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationDetail;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationVo;
import com.hhwy.selling.domain.ScDeviceRelation;

/**
 * IScDeviceRelationService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScDeviceRelationService{
	
	/**
	 * 分页获取对象ScDeviceRelation
	 * @param ScDeviceRelationVo
	 * @return QueryResult
	 */
	public QueryResult<ScDeviceRelationDetail> getScDeviceRelationByPage(ScDeviceRelationVo scDeviceRelationVo, String doamin, String platformCode) throws Exception;

	/**
	 * 根据查询条件获取对象ScDeviceRelation列表
	 * @param ScDeviceRelationVo
	 * @return List
	 */
	public List<ScDeviceRelationDetail> getScDeviceRelationListByParams(ScDeviceRelationVo scDeviceRelationVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScDeviceRelation数量
	 * @param ScDeviceRelationVo
	 * @return Integer
	 */
	public Integer getScDeviceRelationCountByParams(ScDeviceRelationVo scDeviceRelationVo);
	
	/**
	 * 根据查询条件获取单个对象ScDeviceRelation
	 * @param ScDeviceRelationVo
	 * @return ScDeviceRelation
	 */
	public ScDeviceRelationDetail getScDeviceRelationOneByParams(ScDeviceRelationVo scDeviceRelationVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScDeviceRelation
	 * @param ID
	 * @return ScDeviceRelation
	 */
	public ScDeviceRelation getScDeviceRelationById(String id);
	
	/**
	 * 添加对象ScDeviceRelation
	 * @param 实体对象
	 * @return null
	 */
	public void saveScDeviceRelation(ScDeviceRelation scDeviceRelation);
	
	/**
	 * @Title: saveScDeviceRelationList<br/>
	 * @Description:TODO(添加用户关系)<br/>
	 * @param scDeviceRelationVo
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午6:24:34
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午6:24:34
	 * @since  1.0.0
	 */
	public void saveScDeviceRelationList(List<ScDeviceRelation> scDeviceRelation);
	
	/**
	 * 更新对象ScDeviceRelation
	 * @param 实体对象
	 * @return ScDeviceRelation
	 */
	public void updateScDeviceRelation(ScDeviceRelation scDeviceRelation);
	
	/**
	 * 删除对象ScDeviceRelation
	 * @param id数据组
	 */
	public void deleteScDeviceRelation(Map<String, List<Map<String, String>>> params);
	
	/**
	 * @Title: getScDeviceRelationAddByPage<br/>
	 * @Description:TODO(设备管理新增页面列表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午3:00:39
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午3:00:39
	 * @since  1.0.0
	 */
	public QueryResult<DeviceDetail> getScDeviRelaAddByPage(ScDeviceRelationVo scDeviceRelationVo,
			String domain, String platformCode);

	/**
	 * @Title: getConsTree<br/>
	 * @Description:TODO(查询报表选择用户树)<br/>
	 * @return
	 * List<ScDeviceRelationDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 上午8:54:03
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 上午8:54:03
	 * @since  1.0.0
	 */
	public List<ScDeviceRelationDetail> getConsTree();

	/**
	 * @Title: getRealTimePower<br/>
	 * @Description:TODO(查询实时电量)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 下午3:29:56
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 下午3:29:56
	 * @throws ParseException 
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public Map<String, Object> getRealTimePower(ScDeviceRelationVo scDeviceRelationVo) throws ParseException, Exception;

	/**
	 * @Title: deleteBymarketConsNo<br/>
	 * @Description:TODO(根据营销户号删除数据，用户档案那里可以直接删除营销户号)<br/>
	 * @param id
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月25日 下午5:01:56
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年11月27日 下午5:01:56
	 * @since  1.0.0
	 */
	public void deleteByMarketConsNo(String id);

	/**
	 * @Title: getdayPqForm<br/>
	 * @Description:TODO(日电量报表数据)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月2日 上午11:29:01
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月2日 上午11:29:01
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public Map<String, Object> getdayPqForm(ScDeviceRelationVo scDeviceRelationVo) throws Exception;
	
	/**
	 * @Title: getMonthPqForm<br/>
	 * @Description:TODO(月电量报表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月3日 下午5:37:52
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月3日 下午5:37:52
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public Map<String, Object> getMonthPqForm(ScDeviceRelationVo scDeviceRelationVo) throws Exception;
	
	/**
	 * @Title: deleteByConsId<br/>
	 * @Description:TODO(根据用户id删除设备信息，删除用户档案时用)<br/>
	 * @param consIds
	 * void
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月12日 上午10:00:32
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月12日 上午10:00:32
	 * @since  1.0.0
	 */
	public void deleteByConsId(String[] consIds);
}
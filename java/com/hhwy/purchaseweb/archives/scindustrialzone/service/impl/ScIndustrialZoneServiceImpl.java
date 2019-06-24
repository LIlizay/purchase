package com.hhwy.purchaseweb.archives.scindustrialzone.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneDetail;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneVo;
import com.hhwy.purchaseweb.archives.scindustrialzone.service.ScIndustrialZoneService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.selling.domain.ScIndustrialZone;

/**
 * 
 * <b>类 名 称：</b>ScIndustrialZoneServiceImpl<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月22日 下午1:55:41<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
@SuppressWarnings("unchecked")
public class ScIndustrialZoneServiceImpl implements ScIndustrialZoneService {

	public static final Logger log = LoggerFactory.getLogger(ScIndustrialZoneServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
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
	public QueryResult<ScIndustrialZoneDetail> getScIndustrialZoneByPage(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception{
		QueryResult<ScIndustrialZoneDetail> queryResult = new QueryResult<ScIndustrialZoneDetail>();
		long total = getScIndustrialZoneCountByParams(scIndustrialZoneVo);
		List<ScIndustrialZoneDetail> scIndustrialZoneDetailList = getScIndustrialZoneListByParams(scIndustrialZoneVo);
		queryResult.setTotal(total);
		queryResult.setData(scIndustrialZoneDetailList);
		return queryResult;
	}	
	
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
	public Integer getScIndustrialZoneCountByParams(ScIndustrialZoneVo scIndustrialZoneVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scIndustrialZone.sql.getScIndustrialZoneCountByParams",scIndustrialZoneVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
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
	public List<ScIndustrialZoneDetail> getScIndustrialZoneListByParams(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scIndustrialZoneVo == null){
			scIndustrialZoneVo = new ScIndustrialZoneVo();
		}
		Parameter.isFilterData.set(true);
		List<ScIndustrialZoneDetail> scIndustrialZoneDetailList = (List<ScIndustrialZoneDetail>)dao.getBySql("scIndustrialZone.sql.getScIndustrialZoneListByParams",scIndustrialZoneVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scIndustrialZoneDetailList);
		return scIndustrialZoneDetailList;
	}

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
	public ScIndustrialZoneDetail getScIndustrialZoneOneByParams(ScIndustrialZoneVo scIndustrialZoneVo) throws Exception{
	    ScIndustrialZoneDetail scIndustrialZoneDetail = null;
		List<ScIndustrialZoneDetail> scIndustrialZoneDetailList = getScIndustrialZoneListByParams(scIndustrialZoneVo);
		if(scIndustrialZoneDetailList != null && scIndustrialZoneDetailList.size() > 0){
		    scIndustrialZoneDetail = scIndustrialZoneDetailList.get(0);
		}
		return scIndustrialZoneDetail;
	}
	
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
	public ScIndustrialZone getScIndustrialZoneById(String id) {
		return dao.findById(id, ScIndustrialZone.class);
	}	
	
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
	public void saveScIndustrialZone(ScIndustrialZone scIndustrialZone) {
		scIndustrialZone.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(scIndustrialZone);
	}
	
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
	public void saveScIndustrialZoneList(List<ScIndustrialZone> scIndustrialZoneList) {
		for (ScIndustrialZone scIndustrialZone : scIndustrialZoneList) {
			scIndustrialZone.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(scIndustrialZoneList);
	}
	
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
	public void updateScIndustrialZone(ScIndustrialZone scIndustrialZone) {
		dao.update(scIndustrialZone);
	}
	
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
	public void deleteScIndustrialZone(String[] ids) {
		dao.delete(ids, ScIndustrialZone.class);
	}

	/**
	 * 园区重复性校验
	 */
	@Override
	public void checkScIndustrialZone(ScIndustrialZone scIndustrialZone) {
		Parameter.isFilterData.set(true);
		List<String> list = (List<String>) dao.getBySql("scIndustrialZone.sql.checlIndustrialZone", scIndustrialZone);
		Parameter.isFilterData.set(false);
		if(list.size()>0){
            throw new RuntimeException("园区名称重复");
        }
	}
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
	public List<ScIndustrialZoneDetail> getZoneInfoListByNames(List<String> zoneNames){
		Parameter.isFilterData.set(true);
		List<ScIndustrialZoneDetail> list = (List<ScIndustrialZoneDetail>) dao.getBySql("scIndustrialZone.sql.getZoneInfoListByNames", zoneNames);
		Parameter.isFilterData.set(false);
		return list;
	}
}
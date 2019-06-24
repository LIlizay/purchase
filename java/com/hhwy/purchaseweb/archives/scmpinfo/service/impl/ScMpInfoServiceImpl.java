package com.hhwy.purchaseweb.archives.scmpinfo.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scdevicerelation.service.ScDeviceRelationService;
import com.hhwy.purchaseweb.archives.scmpinfo.domain.ScMpInfoVo;
import com.hhwy.purchaseweb.archives.scmpinfo.service.ScMpInfoService;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.service.ScMpCountService;
import com.hhwy.selling.domain.ScMpInfo;
;

/**
 * ScMpInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class ScMpInfoServiceImpl implements ScMpInfoService {

	public static final Logger log = LoggerFactory.getLogger(ScMpInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * scMpCountService	抄表示数service
	 */
	@Autowired
	private ScMpCountService scMpCountService;
	
	/**
	 * scDeviceRelationService 设备关系 by-zhangzhao
	 */
	@Autowired
	private ScDeviceRelationService scDeviceRelationService;
	
	/**
	 * 分页获取对象ScMpInfo
	 * @param ID
	 * @return ScMpInfo
	 */
	public QueryResult<ScMpInfo> getScMpInfoByPage(ScMpInfoVo scMpInfoVo) throws Exception{
		QueryResult<ScMpInfo> queryResult = new QueryResult<ScMpInfo>();
		long total = getScMpInfoCountByParams(scMpInfoVo);
		List<ScMpInfo> scMpInfoList = getScMpInfoListByParams(scMpInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(scMpInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象ScMpInfo数量
	 * @param ScMpInfoVo
	 * @return Integer
	 */
	public Integer getScMpInfoCountByParams(ScMpInfoVo scMpInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("scMpInfo.sql.getScMpInfoCountByParams",scMpInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象ScMpInfo列表
	 * @param ScMpInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ScMpInfo> getScMpInfoListByParams(ScMpInfoVo scMpInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(scMpInfoVo == null){
			scMpInfoVo = new ScMpInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<ScMpInfo> scMpInfoList = (List<ScMpInfo>)dao.getBySql("scMpInfo.sql.getScMpInfoListByParams",scMpInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scMpInfoList);
		return scMpInfoList;
	}
	/**
	 * 根据查询条件获取单个对象ScMpInfo
	 * @param ScMpInfoVo
	 * @return ScMpInfo
	 */
	public ScMpInfo getScMpInfoOneByParams(ScMpInfoVo scMpInfoVo) throws Exception{
		ScMpInfo scMpInfo = null;
		List<ScMpInfo> scMpInfoList = getScMpInfoListByParams(scMpInfoVo);
		if(scMpInfoList != null && scMpInfoList.size() > 0){
			scMpInfo = scMpInfoList.get(0);
		}
		return scMpInfo;
	}
	
	/**
	 * 根据ID获取对象ScMpInfo
	 * @param ID
	 * @return ScMpInfo
	 */
	public ScMpInfo getScMpInfoById(String id) {
		return dao.findById(id, ScMpInfo.class);
	}	
	
	/**
	 * 添加对象ScMpInfo
	 * @param 实体对象
	 */
	public void saveScMpInfo(ScMpInfo scMpInfo) {
		scMpInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(scMpInfo);
	}
	
	/**
	 * 添加对象ScMpInfo
	 * @param 实体对象
	 */
	public void saveScMpInfoList(List<ScMpInfo> scMpInfoList) {
		for (ScMpInfo scMpInfo : scMpInfoList) {
			scMpInfo.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(scMpInfoList);
	}
	
	/**
	 * 更新对象ScMpInfo
	 * @param 实体对象
	 */
	public void updateScMpInfo(ScMpInfo scMpInfo) {
		dao.update(scMpInfo);
	}
	
	/**
	 * 
	 * @Title: deleteSCMpInfo
	 * @Description: 判断是否有表记示数，根据计量点id删除计量点信息
	 * void
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2017年12月18日 下午3:10:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年04月20日 下午6:35:20
	 * @since  1.0.0
	 */
	public void validateAndDeleteSCMpInfo(String id){
		//有抄表记录，不允许删除用户下计量点信息
		long count = scMpCountService.getScMpCountCountByMpInfoId(id);
		if(count > 0){
			 throw new RuntimeException("该计量点已维护抄表示数，不允许删除！");
		}
		//by-zhangzhao 6M25 删除用户设备关系 （修改  ——by LiXinze）
		scDeviceRelationService.deleteByMarketConsNo(id);
		dao.delete(new String[] {id}, ScMpInfo.class);
	}
	/**
	 * 删除对象ScMpInfo
	 * @param id数据组
	 */
	public void deleteScMpInfo(String[] ids) {
		dao.delete(ids, ScMpInfo.class);;
	}
	
	/**
	 * 根据用电用户id删除对象ScMpInfo
	 * @param id数据组
	 */
	public void deleteScMpInfoByConsId(String[] consIds) {
		dao.deleteBySql("scMpInfo.sql.deleteScMpInfoByConsId", consIds);
	}

	/**
	 * 批量更新对象ScMpInfo
	 */
	@Override
	public void updateScMpInfoList(List<ScMpInfo> scMpInfoList) {
		for(ScMpInfo scMpInfo:scMpInfoList){
			this.updateScMpInfo(scMpInfo);
		}
	}
	
	/**
	 * @Title: getMpInfoListByMeterNoAndConsId
	 * @Description: 根据电能表编号和consId获取列表，     用户信息导入时验证一个电能表编号在系统中已经维护到另外一个用户下时用到
	 * @param mpQueryParams
	 * @return 
	 * List<ScMpInfo>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午6:00:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午6:00:40
	 * @since  1.0.0
	 */
	public List<ScMpInfo> getMpInfoListByMeterNoAndConsId(List<Map<String, String>> mpQueryParams){
		Parameter.isFilterData.set(true);
		@SuppressWarnings("unchecked")
		List<ScMpInfo> scMpInfoList = (List<ScMpInfo>)dao.getBySql("scMpInfo.sql.getMpInfoListByMeterNoAndConsId",mpQueryParams);
		Parameter.isFilterData.set(false);
		return scMpInfoList;
	}
	
	/**
	 * @Title: getMpInfoListByMeterNos
	 * @Description: 根据电能表编号列表获取计量点列表
	 * @param meterNos
	 * @return 
	 * List<ScMpInfo>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月21日 下午1:22:15
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月21日 下午1:22:15
	 * @since  1.0.0
	 */
	public List<ScMpInfo> getMpInfoListByMeterNos(List<String> meterNos){
		Parameter.isFilterData.set(true);
		@SuppressWarnings("unchecked")
		List<ScMpInfo> scMpInfoList = (List<ScMpInfo>)dao.getBySql("scMpInfo.sql.getMpInfoListByMeterNos",meterNos);
		Parameter.isFilterData.set(false);
		return scMpInfoList;
	}
}
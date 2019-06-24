package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.domain.SmWarningInfoVo;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.service.SmWarningInfoService;
import com.hhwy.selling.domain.SmWarningInfo;


/**
 * SmWarningInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmWarningInfoServiceImpl implements SmWarningInfoService {

	public static final Logger log = LoggerFactory.getLogger(SmWarningInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmWarningInfo
	 * @param ID
	 * @return SmWarningInfo
	 */
	public QueryResult<SmWarningInfo> getSmWarningInfoByPage(SmWarningInfoVo smWarningInfoVo) throws Exception{
		QueryResult<SmWarningInfo> queryResult = new QueryResult<SmWarningInfo>();
		long total = getSmWarningInfoCountByParams(smWarningInfoVo);
		List<SmWarningInfo> smWarningInfoList = getSmWarningInfoListByParams(smWarningInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(smWarningInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmWarningInfo数量
	 * @param SmWarningInfoVo
	 * @return Integer
	 */
	public Integer getSmWarningInfoCountByParams(SmWarningInfoVo smWarningInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smWarningInfo.sql.getSmWarningInfoCountByParams",smWarningInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmWarningInfo列表
	 * @param SmWarningInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmWarningInfo> getSmWarningInfoListByParams(SmWarningInfoVo smWarningInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smWarningInfoVo == null){
			smWarningInfoVo = new SmWarningInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<SmWarningInfo> smWarningInfoList = (List<SmWarningInfo>)dao.getBySql("smWarningInfo.sql.getSmWarningInfoListByParams",smWarningInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smWarningInfoList);
		return smWarningInfoList;
	}
	/**
	 * 根据查询条件获取单个对象SmWarningInfo
	 * @param SmWarningInfoVo
	 * @return SmWarningInfo
	 */
	public SmWarningInfo getSmWarningInfoOneByParams(SmWarningInfoVo smWarningInfoVo) throws Exception{
		SmWarningInfo smWarningInfo = null;
		List<SmWarningInfo> smWarningInfoList = getSmWarningInfoListByParams(smWarningInfoVo);
		if(smWarningInfoList != null && smWarningInfoList.size() > 0){
			smWarningInfo = smWarningInfoList.get(0);
		}
		return smWarningInfo;
	}
	
	/**
	 * 根据ID获取对象SmWarningInfo
	 * @param ID
	 * @return SmWarningInfo
	 */
	public SmWarningInfo getSmWarningInfoById(String id) {
		return dao.findById(id, SmWarningInfo.class);
	}	
	
	/**
	 * 添加对象SmWarningInfo
	 * @param 实体对象
	 */
	public void saveSmWarningInfo(SmWarningInfo smWarningInfo) {
		dao.save(smWarningInfo);
	}
	
	/**
	 * 添加对象SmWarningInfo
	 * @param 实体对象
	 */
	public void saveSmWarningInfoList(List<SmWarningInfo> smWarningInfoList) {
		dao.saveList(smWarningInfoList);
	}
	
	/**
	 * 更新对象SmWarningInfo
	 * @param 实体对象
	 */
	public void updateSmWarningInfo(SmWarningInfo smWarningInfo) {
		dao.update(smWarningInfo);
	}
	
	/**
	 * 删除对象SmWarningInfo
	 * @param id数据组
	 */
	public void deleteSmWarningInfo(String[] ids) {
		dao.delete(ids, SmWarningInfo.class);
	}	
}
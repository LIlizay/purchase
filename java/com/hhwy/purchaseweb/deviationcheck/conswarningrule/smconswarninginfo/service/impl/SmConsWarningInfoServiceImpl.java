package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.domain.SmConsWarningInfoVo;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.service.SmConsWarningInfoService;
import com.hhwy.selling.domain.SmConsWarningInfo;

/**
 * SmConsWarningInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmConsWarningInfoServiceImpl implements SmConsWarningInfoService {

	public static final Logger log = LoggerFactory.getLogger(SmConsWarningInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmConsWarningInfo
	 * @param ID
	 * @return SmConsWarningInfo
	 */
	public QueryResult<SmConsWarningInfo> getSmConsWarningInfoByPage(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception{
		QueryResult<SmConsWarningInfo> queryResult = new QueryResult<SmConsWarningInfo>();
		long total = getSmConsWarningInfoCountByParams(smConsWarningInfoVo);
		List<SmConsWarningInfo> smConsWarningInfoList = getSmConsWarningInfoListByParams(smConsWarningInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(smConsWarningInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmConsWarningInfo数量
	 * @param SmConsWarningInfoVo
	 * @return Integer
	 */
	public Integer getSmConsWarningInfoCountByParams(SmConsWarningInfoVo smConsWarningInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsWarningInfo.sql.getSmConsWarningInfoCountByParams",smConsWarningInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmConsWarningInfo列表
	 * @param SmConsWarningInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsWarningInfo> getSmConsWarningInfoListByParams(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smConsWarningInfoVo == null){
			smConsWarningInfoVo = new SmConsWarningInfoVo();
		}
		List<SmConsWarningInfo> smConsWarningInfoList = (List<SmConsWarningInfo>)dao.getBySql("smConsWarningInfo.sql.getSmConsWarningInfoListByParams",smConsWarningInfoVo);
		ConvertListUtil.convert(smConsWarningInfoList);
		return smConsWarningInfoList;
	}
	/**
	 * 根据查询条件获取单个对象SmConsWarningInfo
	 * @param SmConsWarningInfoVo
	 * @return SmConsWarningInfo
	 */
	public SmConsWarningInfo getSmConsWarningInfoOneByParams(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception{
		SmConsWarningInfo smConsWarningInfo = null;
		List<SmConsWarningInfo> smConsWarningInfoList = getSmConsWarningInfoListByParams(smConsWarningInfoVo);
		if(smConsWarningInfoList != null && smConsWarningInfoList.size() > 0){
			smConsWarningInfo = smConsWarningInfoList.get(0);
		}
		return smConsWarningInfo;
	}
	
	/**
	 * 根据ID获取对象SmConsWarningInfo
	 * @param ID
	 * @return SmConsWarningInfo
	 */
	public SmConsWarningInfo getSmConsWarningInfoById(String id) {
		return dao.findById(id, SmConsWarningInfo.class);
	}	
	
	/**
	 * 添加对象SmConsWarningInfo
	 * @param 实体对象
	 */
	public void saveSmConsWarningInfo(SmConsWarningInfo smConsWarningInfo) {
		dao.save(smConsWarningInfo);
	}
	
	/**
	 * 添加对象SmConsWarningInfo
	 * @param 实体对象
	 */
	public void saveSmConsWarningInfoList(List<SmConsWarningInfo> smConsWarningInfoList) {
		dao.saveList(smConsWarningInfoList);
	}
	
	/**
	 * 更新对象SmConsWarningInfo
	 * @param 实体对象
	 */
	public void updateSmConsWarningInfo(SmConsWarningInfo smConsWarningInfo) {
		dao.update(smConsWarningInfo);
	}
	
	/**
	 * 删除对象SmConsWarningInfo
	 * @param id数据组
	 */
	public void deleteSmConsWarningInfo(String[] ids) {
		dao.delete(ids, SmConsWarningInfo.class);
	}	
}
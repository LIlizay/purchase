package com.hhwy.purchaseweb.contract.smagrepunishcomp.service.impl;

import java.util.HashMap;
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
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.contract.smagrepunishcomp.domain.SmAgrePunishCompVo;
import com.hhwy.purchaseweb.contract.smagrepunishcomp.service.SmAgrePunishCompService;
import com.hhwy.selling.domain.SmAgrePunishComp;

/**
 * SmAgrePunishCompService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmAgrePunishCompServiceImpl implements SmAgrePunishCompService {

	public static final Logger log = LoggerFactory.getLogger(SmAgrePunishCompServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmAgrePunishComp
	 * @param ID
	 * @return SmAgrePunishComp
	 */
	public QueryResult<SmAgrePunishComp> getSmAgrePunishCompByPage(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception{
		QueryResult<SmAgrePunishComp> queryResult = new QueryResult<SmAgrePunishComp>();
		long total = getSmAgrePunishCompCountByParams(smAgrePunishCompVo);
		List<SmAgrePunishComp> smAgrePunishCompList = getSmAgrePunishCompListByParams(smAgrePunishCompVo);
		queryResult.setTotal(total);
		queryResult.setData(smAgrePunishCompList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmAgrePunishComp数量
	 * @param SmAgrePunishCompVo
	 * @return Integer
	 */
	public Integer getSmAgrePunishCompCountByParams(SmAgrePunishCompVo smAgrePunishCompVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smAgrePunishComp.sql.getSmAgrePunishCompCountByParams",smAgrePunishCompVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmAgrePunishComp列表
	 * @param SmAgrePunishCompVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmAgrePunishComp> getSmAgrePunishCompListByParams(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smAgrePunishCompVo == null){
			smAgrePunishCompVo = new SmAgrePunishCompVo();
		}
		Parameter.isFilterData.set(true);
		List<SmAgrePunishComp> smAgrePunishCompList = (List<SmAgrePunishComp>)dao.getBySql("smAgrePunishComp.sql.getSmAgrePunishCompListByParams",smAgrePunishCompVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smAgrePunishCompList);
		return smAgrePunishCompList;
	}
	/**
	 * 根据查询条件获取单个对象SmAgrePunishComp
	 * @param SmAgrePunishCompVo
	 * @return SmAgrePunishComp
	 */
	public SmAgrePunishComp getSmAgrePunishCompOneByParams(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception{
		SmAgrePunishComp smAgrePunishComp = null;
		List<SmAgrePunishComp> smAgrePunishCompList = getSmAgrePunishCompListByParams(smAgrePunishCompVo);
		if(smAgrePunishCompList != null && smAgrePunishCompList.size() > 0){
			smAgrePunishComp = smAgrePunishCompList.get(0);
		}
		return smAgrePunishComp;
	}
	
	/**
	 * 根据ID获取对象SmAgrePunishComp
	 * @param ID
	 * @return SmAgrePunishComp
	 */
	public SmAgrePunishComp getSmAgrePunishCompById(String id) {
		return dao.findById(id, SmAgrePunishComp.class);
	}	
	
	/**
	 * 添加对象SmAgrePunishComp
	 * @param 实体对象
	 */
	public void saveSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp) {
		smAgrePunishComp.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smAgrePunishComp);
	}
	
	/**
	 * 添加对象SmAgrePunishComp
	 * @param 实体对象
	 */
	public void saveSmAgrePunishCompList(List<SmAgrePunishComp> smAgrePunishCompList) {
		for(SmAgrePunishComp smAgrePunishComp : smAgrePunishCompList){
			smAgrePunishComp.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smAgrePunishCompList);
	}
	
	/**
	 * 更新对象SmAgrePunishComp
	 * @param 实体对象
	 */
	public void updateSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp) {
		dao.update(smAgrePunishComp);
	}
	
	/**
	 * 删除对象SmAgrePunishComp
	 * @param id数据组
	 */
	public void deleteSmAgrePunishComp(String[] ids) {
		dao.delete(ids, SmAgrePunishComp.class);
	}

	/**
	 * 根据合同标识删除对象SmAgrePunishComp
	 * @param id数据组
	 */
	@Override
	public void deleteSmAgrePunishCompByAgreId(String[] ids) {
		Map<String,Object> param = new HashMap<>();
		param.put("agreIds", ids);
		dao.deleteBySql("smAgrePunishComp.sql.deleteSmAgrePunishCompByAgreId", param);
	}	
}
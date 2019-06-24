package com.hhwy.purchaseweb.contract.smagrepunishcons.service.impl;

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
import com.hhwy.purchaseweb.contract.smagrepunishcons.domain.SmAgrePunishConsVo;
import com.hhwy.purchaseweb.contract.smagrepunishcons.service.SmAgrePunishConsService;
import com.hhwy.selling.domain.SmAgrePunishCons;

/**
 * SmAgrePunishConsService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmAgrePunishConsServiceImpl implements SmAgrePunishConsService {

	public static final Logger log = LoggerFactory.getLogger(SmAgrePunishConsServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmAgrePunishCons
	 * @param ID
	 * @return SmAgrePunishCons
	 */
	public QueryResult<SmAgrePunishCons> getSmAgrePunishConsByPage(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception{
		QueryResult<SmAgrePunishCons> queryResult = new QueryResult<SmAgrePunishCons>();
		long total = getSmAgrePunishConsCountByParams(smAgrePunishConsVo);
		List<SmAgrePunishCons> smAgrePunishConsList = getSmAgrePunishConsListByParams(smAgrePunishConsVo);
		queryResult.setTotal(total);
		queryResult.setData(smAgrePunishConsList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmAgrePunishCons数量
	 * @param SmAgrePunishConsVo
	 * @return Integer
	 */
	public Integer getSmAgrePunishConsCountByParams(SmAgrePunishConsVo smAgrePunishConsVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smAgrePunishCons.sql.getSmAgrePunishConsCountByParams",smAgrePunishConsVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmAgrePunishCons列表
	 * @param SmAgrePunishConsVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmAgrePunishCons> getSmAgrePunishConsListByParams(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smAgrePunishConsVo == null){
			smAgrePunishConsVo = new SmAgrePunishConsVo();
		}
		Parameter.isFilterData.set(true);
		List<SmAgrePunishCons> smAgrePunishConsList = (List<SmAgrePunishCons>)dao.getBySql("smAgrePunishCons.sql.getSmAgrePunishConsListByParams",smAgrePunishConsVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smAgrePunishConsList);
		return smAgrePunishConsList;
	}
	/**
	 * 根据查询条件获取单个对象SmAgrePunishCons
	 * @param SmAgrePunishConsVo
	 * @return SmAgrePunishCons
	 */
	public SmAgrePunishCons getSmAgrePunishConsOneByParams(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception{
		SmAgrePunishCons smAgrePunishCons = null;
		List<SmAgrePunishCons> smAgrePunishConsList = getSmAgrePunishConsListByParams(smAgrePunishConsVo);
		if(smAgrePunishConsList != null && smAgrePunishConsList.size() > 0){
			smAgrePunishCons = smAgrePunishConsList.get(0);
		}
		return smAgrePunishCons;
	}
	
	/**
	 * 根据ID获取对象SmAgrePunishCons
	 * @param ID
	 * @return SmAgrePunishCons
	 */
	public SmAgrePunishCons getSmAgrePunishConsById(String id) {
		return dao.findById(id, SmAgrePunishCons.class);
	}	
	
	/**
	 * 添加对象SmAgrePunishCons
	 * @param 实体对象
	 */
	public void saveSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons) {
		smAgrePunishCons.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smAgrePunishCons);
	}
	
	/**
	 * 添加对象SmAgrePunishCons
	 * @param 实体对象
	 */
	public void saveSmAgrePunishConsList(List<SmAgrePunishCons> smAgrePunishConsList) {
		for(SmAgrePunishCons smAgrePunishCons : smAgrePunishConsList){
			smAgrePunishCons.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smAgrePunishConsList);
	}
	
	/**
	 * 更新对象SmAgrePunishCons
	 * @param 实体对象
	 */
	public void updateSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons) {
		dao.update(smAgrePunishCons);
	}
	
	/**
	 * 删除对象SmAgrePunishCons
	 * @param id数据组
	 */
	public void deleteSmAgrePunishCons(String[] ids) {
		dao.delete(ids, SmAgrePunishCons.class);
	}

	/**
	 * 根据合同标识删除对象SmAgrePunishCons
	 * @param id数据组
	 */
	@Override
	public void deleteSmAgrePunishConsByAgreId(String[] ids) {
		Map<String,Object> param = new HashMap<>();
		param.put("agreIds", ids);
		dao.deleteBySql("smAgrePunishCons.sql.deleteSmAgrePunishConsByAgreId",param);
	}	
}
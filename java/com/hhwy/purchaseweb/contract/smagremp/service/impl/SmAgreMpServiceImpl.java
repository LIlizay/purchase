package com.hhwy.purchaseweb.contract.smagremp.service.impl;

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
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpInfoDetail;
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpVo;
import com.hhwy.purchaseweb.contract.smagremp.service.SmAgreMpService;
import com.hhwy.selling.domain.SmAgreMp;


/**
 * SmAgreMpService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0   
 */
@Component
public class SmAgreMpServiceImpl implements SmAgreMpService {

	public static final Logger log = LoggerFactory.getLogger(SmAgreMpServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 根据查询条件获取对象ScMpInfo列表
	 * @param ScMpInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmAgreMpInfoDetail> getScMpInfoList(Map<String,String> map) throws Exception{
		Parameter.isFilterData.set(true);
		List<SmAgreMpInfoDetail> scMpInfoList = (List<SmAgreMpInfoDetail>)dao.getBySql("smAgreMp.sql.getScMpInfoList",map);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(scMpInfoList);
		return scMpInfoList;
	}
	
	/**
	 * 分页获取对象SmAgreMp
	 * @param ID
	 * @return SmAgreMp
	 */
	public QueryResult<SmAgreMp> getSmAgreMpByPage(SmAgreMpVo smAgreMpVo) throws Exception{
		QueryResult<SmAgreMp> queryResult = new QueryResult<SmAgreMp>();
		long total = getSmAgreMpCountByParams(smAgreMpVo);
		List<SmAgreMp> smAgreMpList = getSmAgreMpListByParams(smAgreMpVo);
		queryResult.setTotal(total);
		queryResult.setData(smAgreMpList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmAgreMp数量
	 * @param SmAgreMpVo
	 * @return Integer
	 */
	public Integer getSmAgreMpCountByParams(SmAgreMpVo smAgreMpVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smAgreMp.sql.getSmAgreMpCountByParams",smAgreMpVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmAgreMp列表
	 * @param SmAgreMpVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmAgreMp> getSmAgreMpListByParams(SmAgreMpVo smAgreMpVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smAgreMpVo == null){
			smAgreMpVo = new SmAgreMpVo();
		}
		Parameter.isFilterData.set(true);
		List<SmAgreMp> smAgreMpList = (List<SmAgreMp>)dao.getBySql("smAgreMp.sql.getSmAgreMpListByParams",smAgreMpVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smAgreMpList);
		return smAgreMpList;
	}
	/**
	 * 根据查询条件获取单个对象SmAgreMp
	 * @param SmAgreMpVo
	 * @return SmAgreMp
	 */
	public SmAgreMp getSmAgreMpOneByParams(SmAgreMpVo smAgreMpVo) throws Exception{
		SmAgreMp smAgreMp = null;
		List<SmAgreMp> smAgreMpList = getSmAgreMpListByParams(smAgreMpVo);
		if(smAgreMpList != null && smAgreMpList.size() > 0){
			smAgreMp = smAgreMpList.get(0);
		}
		return smAgreMp;
	}
	
	/**
	 * 根据ID获取对象SmAgreMp
	 * @param ID
	 * @return SmAgreMp
	 */
	public SmAgreMp getSmAgreMpById(String id) {
		return dao.findById(id, SmAgreMp.class);
	}	
	
	/**
	 * 添加对象SmAgreMp
	 * @param 实体对象
	 */
	public void saveSmAgreMp(SmAgreMp smAgreMp) {
		smAgreMp.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smAgreMp);
	}
	
	/**
	 * 添加对象SmAgreMp
	 * @param 实体对象
	 */
	public void saveSmAgreMpList(List<SmAgreMp> smAgreMpList) {
		for(SmAgreMp smAgreMp : smAgreMpList){
			smAgreMp.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smAgreMpList);
	}
	
	/**
	 * 更新对象SmAgreMp
	 * @param 实体对象
	 */
	public void updateSmAgreMp(SmAgreMp smAgreMp) {
		dao.update(smAgreMp);
	}
	
	/**
	 * 删除对象SmAgreMp
	 * @param id数据组
	 */
	public void deleteSmAgreMp(String[] ids) {
		dao.delete(ids, SmAgreMp.class);
	}		
	
	/**
	 * 删除对象SmAgreServ
	 * @param id数据组
	 */
	public void deleteSmAgreMpByAgreIds(String[] agreId) {
		dao.deleteBySql("smAgreMp.sql.deleteSmAgreMpByAgreIds",agreId);
	}
	
}
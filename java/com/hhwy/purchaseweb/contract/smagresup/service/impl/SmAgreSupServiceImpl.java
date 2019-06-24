package com.hhwy.purchaseweb.contract.smagresup.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.core.framework.service.SequenceTool;
import com.hhwy.business.core.sqlfilter.CompanyDomainInfoUtil;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.arithmetic.util.OrgUtil;
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupDetail;
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupVo;
import com.hhwy.purchaseweb.contract.smagresup.service.SmAgreSupService;
import com.hhwy.selling.domain.SmAgreSup;

/**
 * SmAgreSupService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmAgreSupServiceImpl implements SmAgreSupService {

	public static final Logger log = LoggerFactory.getLogger(SmAgreSupServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmAgreSup
	 * @param ID
	 * @return SmAgreSup
	 */
	public QueryResult<SmAgreSupDetail> getSmAgreSupByPage(SmAgreSupVo smAgreSupVo) throws Exception{
		QueryResult<SmAgreSupDetail> queryResult = new QueryResult<SmAgreSupDetail>();
		long total = getSmAgreSupCountByParams(smAgreSupVo);
		List<SmAgreSupDetail> smAgreSupList = getSmAgreSupListByParams(smAgreSupVo);
		queryResult.setTotal(total);
		queryResult.setData(smAgreSupList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmAgreSup数量
	 * @param SmAgreSupVo
	 * @return Integer
	 */
	public Integer getSmAgreSupCountByParams(SmAgreSupVo smAgreSupVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smAgreSup.sql.getSmAgreSupCountByParams",smAgreSupVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmAgreSup列表
	 * @param SmAgreSupVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmAgreSupDetail> getSmAgreSupListByParams(SmAgreSupVo smAgreSupVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smAgreSupVo == null){
			smAgreSupVo = new SmAgreSupVo();
		}
		Parameter.isFilterData.set(true);
		List<SmAgreSupDetail> smAgreSupList = (List<SmAgreSupDetail>)dao.getBySql("smAgreSup.sql.getSmAgreSupListByParams",smAgreSupVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smAgreSupList);
		return smAgreSupList;
	}
	/**
	 * 根据查询条件获取单个对象SmAgreSup
	 * @param SmAgreSupVo
	 * @return SmAgreSup
	 */
	public SmAgreSupDetail getSmAgreSupOneByParams(SmAgreSupVo smAgreSupVo) throws Exception{
	    SmAgreSupDetail smAgreSup = null;
		List<SmAgreSupDetail> smAgreSupList = getSmAgreSupListByParams(smAgreSupVo);
		if(smAgreSupList != null && smAgreSupList.size() > 0){
			smAgreSup = smAgreSupList.get(0);
		}
		return smAgreSup;
	}
	
	/**
	 * 根据ID获取对象SmAgreSup
	 * @param ID
	 * @return SmAgreSup
	 */
	public SmAgreSup getSmAgreSupById(String id) {
		return dao.findById(id, SmAgreSup.class);
	}	
	
	/**
	 * 添加对象SmAgreSup
	 * @param 实体对象
	 */
	public SmAgreSup saveSmAgreSup(SmAgreSup smAgreSup) {
	    //设置id
	    String smAgreSupId = PlatformTools.getID();
	    //设置协议编号
		String dbName = CompanyDomainInfoUtil.getInstance().currentDatabase();
	    String agreNo = SequenceTool.getInstatnce().generateSerinalNumber("sellAgreNo",dbName);
	    smAgreSup.setId(smAgreSupId);
	    smAgreSup.setAgreNo(agreNo);
	    smAgreSup.setOrgNo(OrgUtil.getOrgNoByUser());
		dao.save(smAgreSup);
		return smAgreSup;
	}
	
	/**
	 * 添加对象SmAgreSup
	 * @param 实体对象
	 */
	public void saveSmAgreSupList(List<SmAgreSup> smAgreSupList) {
		for(SmAgreSup smAgreSup : smAgreSupList){
			smAgreSup.setOrgNo(OrgUtil.getOrgNoByUser());
		}
		dao.saveList(smAgreSupList);
	}
	
	/**
	 * 更新对象SmAgreSup
	 * @param 实体对象
	 */
	public void updateSmAgreSup(SmAgreSup smAgreSup) {
		dao.update(smAgreSup);
	}
	
	/**
	 * 删除对象SmAgreSup 根据售电合同id
	 * @param id数据组
	 * by-zhangzhao
	 */
	public void deleteSmAgreSup(String[] smppaIds) {
		dao.deleteBySql("smAgreSup.sql.deleteSmAgreSup",smppaIds);
	}
	
	/**
	 * 删除对象SmAgreSup 根据补充协议id
	 * @param id数据组
	 * by-zhangzhao
	 */
	public void deleteBySupId(String[] supIds) {
		dao.deleteBySql("smAgreSup.sql.deleteBySupId",supIds);
	}
	
	
}
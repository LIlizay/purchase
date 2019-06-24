package com.hhwy.purchaseweb.forecast.phfconsproductionplan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfConsProductionPlan;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanDetail;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanVo;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.service.PhfConsProductionPlanService;

/**
 * PhfConsProductionPlanService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfConsProductionPlanServiceImpl implements PhfConsProductionPlanService {

	public static final Logger log = LoggerFactory.getLogger(PhfConsProductionPlanServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * bigDataService		大数据service
	 */
	@Autowired
	private BigDataService bigDataService;
	
	
	/**
	 * 分页获取对象PhfConsProductionPlan
	 * @param ID
	 * @return PhfConsProductionPlan
	 */
	public QueryResult<PhfConsProductionPlanDetail> getPhfConsProductionPlanByPage(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception{
		QueryResult<PhfConsProductionPlanDetail> queryResult = new QueryResult<PhfConsProductionPlanDetail>();
		phfConsProductionPlanVo.setPagingFlag(false);
		long total = getPhfConsProductionPlanCountByParams(phfConsProductionPlanVo);
		List<PhfConsProductionPlanDetail> phfConsProductionPlanList = getPhfConsProductionPlanListByParams(phfConsProductionPlanVo);
		queryResult.setTotal(total);
		queryResult.setData(phfConsProductionPlanList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfConsProductionPlan数量
	 * @param PhfConsProductionPlanVo
	 * @return Integer
	 */
	public Integer getPhfConsProductionPlanCountByParams(PhfConsProductionPlanVo phfConsProductionPlanVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfConsProductionPlan.sql.getPhfConsProductionPlanCountByParams",phfConsProductionPlanVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfConsProductionPlan列表
	 * @param PhfConsProductionPlanVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfConsProductionPlanDetail> getPhfConsProductionPlanListByParams(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfConsProductionPlanVo == null){
			phfConsProductionPlanVo = new PhfConsProductionPlanVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfConsProductionPlanDetail> phfConsProductionPlanList = (List<PhfConsProductionPlanDetail>)dao.getBySql("phfConsProductionPlan.sql.getPhfConsProductionPlanListByParams",phfConsProductionPlanVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfConsProductionPlanList);
		return phfConsProductionPlanList;
	}
	/**
	 * 根据查询条件获取单个对象PhfConsProductionPlan
	 * @param PhfConsProductionPlanVo
	 * @return PhfConsProductionPlan
	 */
	public PhfConsProductionPlanDetail getPhfConsProductionPlanOneByParams(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception{
		PhfConsProductionPlanDetail phfConsProductionPlan = null;
		List<PhfConsProductionPlanDetail> phfConsProductionPlanList = getPhfConsProductionPlanListByParams(phfConsProductionPlanVo);
		if(phfConsProductionPlanList != null && phfConsProductionPlanList.size() > 0){
			phfConsProductionPlan = phfConsProductionPlanList.get(0);
		}
		return phfConsProductionPlan;
	}
	
	/**
	 * 根据ID获取对象PhfConsProductionPlan
	 * @param ID
	 * @return PhfConsProductionPlan
	 */
	public PhfConsProductionPlan getPhfConsProductionPlanById(String id) {
		return dao.findById(id, PhfConsProductionPlan.class);
	}	
	
	/**
	 * 添加对象PhfConsProductionPlan
	 * @param 实体对象
	 */
	public void savePhfConsProductionPlan(PhfConsProductionPlan phfConsProductionPlan) {
		dao.save(phfConsProductionPlan);
		//更新大数据表中数据
		bigDataService.saveOrUpdateProducePlanList(phfConsProductionPlan.getConsId(), phfConsProductionPlan.getYear());
	}
	
	/**
	 * 添加对象PhfConsProductionPlan
	 * @param 实体对象
	 * @throws Exception 
	 */
	public String savePhfConsProductionPlanList(List<PhfConsProductionPlan> phfConsProductionPlanList) throws Exception {
		PhfConsProductionPlan phfConsProductionPlan=phfConsProductionPlanList.get(0);
		PhfConsProductionPlanVo phfConsProductionPlanVo=new PhfConsProductionPlanVo();
		phfConsProductionPlanVo.getPhfConsProductionPlan().setYear(phfConsProductionPlan.getYear());
		phfConsProductionPlanVo.getPhfConsProductionPlan().setConsId(phfConsProductionPlan.getConsId());
		List<PhfConsProductionPlanDetail> list=getPhfConsProductionPlanListByParams(phfConsProductionPlanVo);
		if(list.size()!=0){
			return "1";
		}
		dao.saveList(phfConsProductionPlanList);
		//更新大数据表中数据
		bigDataService.saveOrUpdateProducePlanList(phfConsProductionPlan.getConsId(), phfConsProductionPlan.getYear());
		return null;
	}
	
	/**
	 * 更新对象PhfConsProductionPlan
	 * @param 实体对象
	 */
	public void updatePhfConsProductionPlan(PhfConsProductionPlan phfConsProductionPlan) {
		dao.update(phfConsProductionPlan);
		//更新大数据表中数据
		bigDataService.saveOrUpdateProducePlanList(phfConsProductionPlan.getConsId(), phfConsProductionPlan.getYear());
	}
	
	/**
	 * 删除对象PhfConsProductionPlan
	 * @param id数据组
	 */
	public void deletePhfConsProductionPlan(String[] ids) {
		if(ids == null || ids.length == 0) {
			return;
		}
		PhfConsProductionPlan phfConsProductionPlan = null;
		for (String id : ids) {
			phfConsProductionPlan = dao.findById(id,  PhfConsProductionPlan.class);
			if(phfConsProductionPlan != null && phfConsProductionPlan.getConsId() != null) {
				break;
			}
		}
		dao.delete(ids, PhfConsProductionPlan.class);
		if(phfConsProductionPlan != null && phfConsProductionPlan.getConsId() != null) {
			//更新大数据表中数据
			bigDataService.saveOrUpdateProducePlanList(phfConsProductionPlan.getConsId(), phfConsProductionPlan.getYear());
		}
	}
	
	/**
	 * 删除用户
	 */
	public void deleteConsInfo(PhfConsProductionPlan phfConsProductionPlan){
		dao.deleteBySql("phfConsProductionPlan.sql.deleteConsInfo", phfConsProductionPlan);
		//删除大数据对应数据
		bigDataService.deleteProducePlanByParams(phfConsProductionPlan.getConsId(),phfConsProductionPlan.getYear());
	}

	/**
	 * 获取父节点信息
	 */
	@Override
	public QueryResult<PhfConsProductionPlanDetail> getParentNode(PhfConsProductionPlanVo phfConsProductionPlanVo)throws Exception {
		QueryResult<PhfConsProductionPlanDetail> queryResult = new QueryResult<PhfConsProductionPlanDetail>();
		long total = getParentNodeCount(phfConsProductionPlanVo);
		List<PhfConsProductionPlanDetail> phfConsProductionPlanList = getParentNodeList(phfConsProductionPlanVo);
		for(int i = 0 ; i < phfConsProductionPlanList.size() ; i++){
			phfConsProductionPlanList.get(i).setParentName(phfConsProductionPlanList.get(i).getConsName());
		}
		queryResult.setTotal(total);
		queryResult.setData(phfConsProductionPlanList);
		return queryResult;
	}	
	
	private Integer getParentNodeCount(PhfConsProductionPlanVo phfConsProductionPlanVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfConsProductionPlan.sql.getParentNodeCount",phfConsProductionPlanVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	@SuppressWarnings("unchecked")
	private List<PhfConsProductionPlanDetail> getParentNodeList(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfConsProductionPlanVo == null){
			phfConsProductionPlanVo = new PhfConsProductionPlanVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfConsProductionPlanDetail> phfConsProductionPlanList = (List<PhfConsProductionPlanDetail>)dao.getBySql("phfConsProductionPlan.sql.getParentNodeList",phfConsProductionPlanVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfConsProductionPlanList);
		return phfConsProductionPlanList;
	}

	/**
	 * 更新用户及产品信息
	 */
	@Override
	@Transactional
	public void updatePhfConsProductionPlanList(List<PhfConsProductionPlan> phfConsProductionPlanList) {
		PhfConsProductionPlan phfConsProductionPlan = phfConsProductionPlanList.get(0);
		//删除表中已存在的数据
		deleteConsInfo(phfConsProductionPlan);
		dao.saveList(phfConsProductionPlanList);
		//更新大数据表中数据
		bigDataService.saveOrUpdateProducePlanList(phfConsProductionPlan.getConsId(), phfConsProductionPlan.getYear());
	}
}
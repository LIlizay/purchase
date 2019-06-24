package com.hhwy.purchaseweb.plan.phmbusinessplan.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.constants.PlanConstants;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PhmBusinessConsRelaVo;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.service.PhmBusinessConsRelaService;
import com.hhwy.purchaseweb.plan.phmbusinessplan.service.PhmBusinessPlanService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.pcode.domain.PCodeVo;
import com.hhwy.business.market.util.CodeUtil;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmBusinessConsRela;
import com.hhwy.purchase.domain.PhmBusinessPlan;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PhmBusinessPlanService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmBusinessPlanServiceImpl implements PhmBusinessPlanService {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessPlanServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	PhmBusinessConsRelaService phmBusinessConsRelaService;
	
	/**
	 * 分页获取对象PhmBusinessPlan
	 * @param ID
	 * @return PhmBusinessPlan
	 */
	public QueryResult<PhmBusinessPlan> getPhmBusinessPlanByPage(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception{
		QueryResult<PhmBusinessPlan> queryResult = new QueryResult<PhmBusinessPlan>();
		long total = getPhmBusinessPlanCountByParams(phmBusinessPlanVo);
		List<PhmBusinessPlan> phmBusinessPlanList = getPhmBusinessPlanListByParams(phmBusinessPlanVo);
		queryResult.setTotal(total);
		queryResult.setData(phmBusinessPlanList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlan数量
	 * @param PhmBusinessPlanVo
	 * @return Integer
	 */
	public Integer getPhmBusinessPlanCountByParams(PhmBusinessPlanVo phmBusinessPlanVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmBusinessPlan.sql.getPhmBusinessPlanCountByParams",phmBusinessPlanVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlan列表
	 * @param PhmBusinessPlanVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmBusinessPlan> getPhmBusinessPlanListByParams(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmBusinessPlanVo == null){
			phmBusinessPlanVo = new PhmBusinessPlanVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmBusinessPlan> phmBusinessPlanList = (List<PhmBusinessPlan>)dao.getBySql("phmBusinessPlan.sql.getPhmBusinessPlanListByParams",phmBusinessPlanVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmBusinessPlanList);
		return phmBusinessPlanList;
	}
	
	/**
     * 分页获取经营计划详细
     * getBusinessPlanDetailByPage(描述这个方法的作用)<br/>
     * @param phmBusinessPlanVo
     * @return
     * @throws Exception 
     * QueryResult<PhmBusinessPlanDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<PhmBusinessPlanDetail> getBusinessPlanDetailByPage(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception{
        QueryResult<PhmBusinessPlanDetail> queryResult = new QueryResult<PhmBusinessPlanDetail>();
        long total = getPhmBusinessPlanCountByParams(phmBusinessPlanVo);
        List<PhmBusinessPlanDetail> phmBusinessPlanList = getBusinessPlanDetail(phmBusinessPlanVo);
        queryResult.setTotal(total);
        queryResult.setData(phmBusinessPlanList);
        return queryResult;
    }
    
    /**
     * 根据查询条件获取经营计划详细
     * getBusinessPlanDetail(描述这个方法的作用)<br/>
     * @param phmBusinessPlanVo
     * @return
     * @throws Exception 
     * List<PhmBusinessPlanDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhmBusinessPlanDetail> getBusinessPlanDetail(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(phmBusinessPlanVo == null){
            phmBusinessPlanVo = new PhmBusinessPlanVo();
        }
        Parameter.isFilterData.set(true);
        List<PhmBusinessPlanDetail> list = (List<PhmBusinessPlanDetail>)dao.getBySql("phmBusinessPlan.sql.getBusinessPlanDetailList",phmBusinessPlanVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
	/**
	 * 根据查询条件获取单个对象PhmBusinessPlan
	 * @param PhmBusinessPlanVo
	 * @return PhmBusinessPlan
	 */
	public PhmBusinessPlan getPhmBusinessPlanOneByParams(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception{
		PhmBusinessPlan phmBusinessPlan = null;
		List<PhmBusinessPlan> phmBusinessPlanList = getPhmBusinessPlanListByParams(phmBusinessPlanVo);
		if(phmBusinessPlanList != null && phmBusinessPlanList.size() > 0){
			phmBusinessPlan = phmBusinessPlanList.get(0);
		}
		return phmBusinessPlan;
	}
	
	/**
	 * 根据ID获取对象PhmBusinessPlan
	 * @param ID
	 * @return PhmBusinessPlan
	 */
	public PhmBusinessPlan getPhmBusinessPlanById(String id) {
		return dao.findById(id, PhmBusinessPlan.class);
	}	
	
	/**
     * 获取购电计划
     * getBusinessPlanById(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * PhmBusinessPlanVo
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public PhmBusinessPlanVo getBusinessPlanById(String id) throws Exception{
        PhmBusinessPlanVo phmBusinessPlanVo = new PhmBusinessPlanVo();
        PhmBusinessPlan phmBusinessPlan = getPhmBusinessPlanById(id);
        phmBusinessPlanVo.setPhmBusinessPlan(phmBusinessPlan);
        PhmBusinessConsRelaVo phmBusinessConsRelaVo = new PhmBusinessConsRelaVo();
        phmBusinessConsRelaVo.setPagingFlag(false);
        phmBusinessConsRelaVo.getPhmBusinessConsRela().setPlanId(id);
        List<PhmBusinessConsRela> list =phmBusinessConsRelaService.getPhmBusinessConsRelaListByParams(phmBusinessConsRelaVo);
        String[] consIds = new String[list.size()];
        for(int i =0;i<list.size();i++){
            consIds[i] = list.get(i).getConsId();
        }
        phmBusinessPlanVo.setConsIds(consIds);
        List<ConsInfoDetail> consList= phmBusinessConsRelaService.getConsInfoDetailByIds(consIds);
        phmBusinessPlanVo.setConsList(consList);
        return phmBusinessPlanVo;
    }
    
	/**
	 * 添加对象PhmBusinessPlan
	 * @param 实体对象
	 */
	@Transactional
	public void savePhmBusinessPlan(PhmBusinessPlanVo phmBusinessPlanVo) {
	    check(phmBusinessPlanVo.getPhmBusinessPlan().getYear());
	    PhmBusinessPlan PhmBusinessPlan = phmBusinessPlanVo.getPhmBusinessPlan();
//	    String sequence = (String) dao.getOneBySQL("phmBusinessPlan.sql.getSequence",PhmBusinessPlan.getYear());
//	    if(StringUtils.isNull(sequence)){
//	        sequence = "1";
//	    }
	    PhmBusinessPlan.setPlanName(PhmBusinessPlan.getYear()+"年经营计划");
	    PhmBusinessPlan.setPlanStatus(PlanConstants.PLAN_STATE_NOT_FORMULATED);
		dao.save(PhmBusinessPlan);
		String id = PhmBusinessPlan.getId();
		for(String consId : phmBusinessPlanVo.getConsIds()){
		    PhmBusinessConsRela phmBusinessConsRela = new PhmBusinessConsRela();
		    phmBusinessConsRela.setConsId(consId);
		    phmBusinessConsRela.setPlanId(id);
		    dao.save(phmBusinessConsRela);
		}
	}
	
	private void check(String year){
	    PhmBusinessPlanVo phmBusinessPlanVo = new PhmBusinessPlanVo();
	    phmBusinessPlanVo.getPhmBusinessPlan().setYear(year);
	    int count = getPhmBusinessPlanCountByParams(phmBusinessPlanVo);
	    if(count!=0){
	        throw new RuntimeException(year+"年经营计划已存在，不能新增");
	    }
	}
	
	/**
	 * 添加对象PhmBusinessPlan
	 * @param 实体对象
	 */
	public void savePhmBusinessPlanList(List<PhmBusinessPlan> phmBusinessPlanList) {
		dao.saveList(phmBusinessPlanList);
	}
	
	/**
	 * 更新对象PhmBusinessPlan
	 * @param 实体对象
	 */
	public void updatePhmBusinessPlan(PhmBusinessPlan phmBusinessPlan) {
		dao.update(phmBusinessPlan);
	}
	
	/**
     * 更新经营计划
     * updateBusinessPlan(描述这个方法的作用)<br/>
     * @param phmBusinessPlan 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void updateBusinessPlan(PhmBusinessPlanVo phmBusinessPlanVo){
        dao.update(phmBusinessPlanVo.getPhmBusinessPlan());
        if(phmBusinessPlanVo.getDeleteIds().length>0){
            dao.deleteBySql("phmBusinessPlan.sql.deleteRelaByConsIds", phmBusinessPlanVo.getDeleteIds());
        }
        String id = phmBusinessPlanVo.getPhmBusinessPlan().getId();
        for(String consId : phmBusinessPlanVo.getConsIds()){
            PhmBusinessConsRela phmBusinessConsRela = new PhmBusinessConsRela();
            phmBusinessConsRela.setConsId(consId);
            phmBusinessConsRela.setPlanId(id);
            dao.save(phmBusinessConsRela);
        }
    }
	
	/**
	 * 删除对象PhmBusinessPlan
	 * @param id数据组
	 */
	public void deletePhmBusinessPlan(String[] ids) {
		dao.delete(ids, PhmBusinessPlan.class);
	}
	
	/**
     * 删除对象PhmBusinessPlan
     * @param id数据组
     */
	@Transactional
    public void deleteBusinessPlan(String id) {
        dao.delete(new String[]{id}, PhmBusinessPlan.class);
        dao.deleteBySql("phmBusinessPlan.sql.deleteRela", id);
    }
	
	/**
	 * 提交
	 * submit(描述这个方法的作用)<br/>
	 * @param id
	 * @param state 
	 * void
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public void submit(String id,String state) throws Exception{
	    PhmBusinessPlan phmBusinessPlan = dao.findById(id, PhmBusinessPlan.class);
	    if(phmBusinessPlan==null){
	        throw new RuntimeException("查询计划失败！");
	    }
	    if(phmBusinessPlan.getPlanStatus().equals(state)){
	        switch(state){
	            case PlanConstants.PLAN_STATE_NOT_FORMULATED:
	                phmBusinessPlan.setPlanStatus(PlanConstants.PLAN_STATE_ALREADY_FORMULATED);
	                break;
	            case PlanConstants.PLAN_STATE_ALREADY_FORMULATED:
	                phmBusinessPlan.setPlanStatus(PlanConstants.PLAN_STATE_ALREADY_COLLECT);
	                break;
	            case PlanConstants.PLAN_STATE_ALREADY_COLLECT:
	                phmBusinessPlan.setPlanStatus(PlanConstants.PLAN_STATE_ALREADY_ANALYSIS);
	                break;
	            default:
	                throw new RuntimeException("状态码错误！");
	        }
	        dao.update(phmBusinessPlan);
	    }else{
	        String name = getBusiTypeCodeNameByValue(phmBusinessPlan.getPlanStatus());
	        throw new RuntimeException("当前计划状态为【"+name+"】，不可提交！");
	    }
	}
	
	 /**
     * 根据码值value获取busiTypeCode的name
     * getBusiTypeCodeNameByValue(描述这个方法的作用)<br/>
     * @param value
     * @return
     * @throws Exception 
     * String
     * @exception 
     * @since  1.0.0
     */
    private String getBusiTypeCodeNameByValue(String value) throws Exception{
        PCodeVo pCodeVo = new PCodeVo();
        pCodeVo.getpCode().setCodeType("sell_planState");
        pCodeVo.setCacheType(ConstantsStatus.CACHE_TYPE_PCODE);
        pCodeVo.setDomain(ConstantsStatus.PCODE_DOMAIN_SELLING);
        pCodeVo.setValueIn(value);
        return CodeUtil.queryCodeListByParam(pCodeVo).get(0).getName();
    }
}
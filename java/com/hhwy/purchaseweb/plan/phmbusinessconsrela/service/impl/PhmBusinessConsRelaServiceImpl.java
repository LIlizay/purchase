package com.hhwy.purchaseweb.plan.phmbusinessconsrela.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.service.PhmBusinessConsRelaService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmBusinessConsRela;
import com.hhwy.purchase.domain.PhmBusinessPlan;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PhmBusinessConsRelaVo;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PurchasingPowerDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmBusinessConsRelaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmBusinessConsRelaServiceImpl implements PhmBusinessConsRelaService {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessConsRelaServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmBusinessConsRela
	 * @param ID
	 * @return PhmBusinessConsRela
	 */
	public QueryResult<PhmBusinessConsRela> getPhmBusinessConsRelaByPage(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception{
		QueryResult<PhmBusinessConsRela> queryResult = new QueryResult<PhmBusinessConsRela>();
		long total = getPhmBusinessConsRelaCountByParams(phmBusinessConsRelaVo);
		List<PhmBusinessConsRela> phmBusinessConsRelaList = getPhmBusinessConsRelaListByParams(phmBusinessConsRelaVo);
		queryResult.setTotal(total);
		queryResult.setData(phmBusinessConsRelaList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmBusinessConsRela数量
	 * @param PhmBusinessConsRelaVo
	 * @return Integer
	 */
	public Integer getPhmBusinessConsRelaCountByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmBusinessConsRela.sql.getPhmBusinessConsRelaCountByParams",phmBusinessConsRelaVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmBusinessConsRela列表
	 * @param PhmBusinessConsRelaVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmBusinessConsRela> getPhmBusinessConsRelaListByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmBusinessConsRelaVo == null){
			phmBusinessConsRelaVo = new PhmBusinessConsRelaVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmBusinessConsRela> phmBusinessConsRelaList = (List<PhmBusinessConsRela>)dao.getBySql("phmBusinessConsRela.sql.getPhmBusinessConsRelaListByParams",phmBusinessConsRelaVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmBusinessConsRelaList);
		return phmBusinessConsRelaList;
	}
	/**
	 * 根据查询条件获取单个对象PhmBusinessConsRela
	 * @param PhmBusinessConsRelaVo
	 * @return PhmBusinessConsRela
	 */
	public PhmBusinessConsRela getPhmBusinessConsRelaOneByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception{
		PhmBusinessConsRela phmBusinessConsRela = null;
		List<PhmBusinessConsRela> phmBusinessConsRelaList = getPhmBusinessConsRelaListByParams(phmBusinessConsRelaVo);
		if(phmBusinessConsRelaList != null && phmBusinessConsRelaList.size() > 0){
			phmBusinessConsRela = phmBusinessConsRelaList.get(0);
		}
		return phmBusinessConsRela;
	}
	
	/**
	 * 根据ID获取对象PhmBusinessConsRela
	 * @param ID
	 * @return PhmBusinessConsRela
	 */
	public PhmBusinessConsRela getPhmBusinessConsRelaById(String id) {
		return dao.findById(id, PhmBusinessConsRela.class);
	}	
	
	/**
     * 根据偏id获取用户信息
     * getConsInfoDetailByPlanId(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * PhmBusinessPlanVo
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public PhmBusinessPlanVo getConsInfoDetailByPlanId(String id) throws Exception{
        PhmBusinessPlanVo phmBusinessPlanVo = new PhmBusinessPlanVo();
        PhmBusinessPlan phmBusinessPlan = dao.findById(id, PhmBusinessPlan.class);
        phmBusinessPlanVo.setPhmBusinessPlan(phmBusinessPlan);
        List<ConsInfoDetail> list = (List<ConsInfoDetail>) dao.getBySql("phmBusinessConsRela.sql.getPlanConsDetail", id);
        ConvertListUtil.convert(list);
        phmBusinessPlanVo.setConsList(list);
        return phmBusinessPlanVo;
    }
    
	/**
     * 根据用户id获取用户信息
     * getConsInfoDetailByIds(描述这个方法的作用)<br/>
     * @param ids
     * @return 
     * List<ConsInfoDetail>
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<ConsInfoDetail> getConsInfoDetailByIds(String[] ids) throws Exception{
        if(ids.length==0){
            return new ArrayList<>();        
        }
        List<ConsInfoDetail> list = (List<ConsInfoDetail>) dao.getBySql("phmBusinessConsRela.sql.getConsInfo", ids);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 分页获取用户电量收集
     * getPurchasingPowerDetailByPage(描述这个方法的作用)<br/>
     * @param phmBusinessConsRelaVo
     * @return
     * @throws Exception 
     * QueryResult<PurchasingPowerDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<PurchasingPowerDetail> getPurchasingPowerDetailByPage(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception{
        QueryResult<PurchasingPowerDetail> queryResult = new QueryResult<PurchasingPowerDetail>();
        long total = getPhmBusinessConsRelaCountByParams(phmBusinessConsRelaVo);
        List<PurchasingPowerDetail> list = getPurchasingPowerDetailListByParams(phmBusinessConsRelaVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }

    /**
     * 获取用户电量收集列表
     * getPurchasingPowerDetailListByParams(描述这个方法的作用)<br/>
     * @param phmBusinessConsRelaVo
     * @return
     * @throws Exception 
     * List<PurchasingPowerDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PurchasingPowerDetail> getPurchasingPowerDetailListByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(phmBusinessConsRelaVo == null){
            phmBusinessConsRelaVo = new PhmBusinessConsRelaVo();
        }
        List<PurchasingPowerDetail> list = (List<PurchasingPowerDetail>)dao.getBySql("phmBusinessConsRela.sql.getPurchasingPowerList",phmBusinessConsRelaVo);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 校验数据是否完整
     * check(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void check(String id){
        Object result = dao.getOneBySQL("phmBusinessConsRela.sql.check",id);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        if(total!=0){
            throw new RuntimeException("还有"+total+"个用户电量尚未填写，请填写后再提交");
        }
    }
    
	/**
	 * 添加对象PhmBusinessConsRela
	 * @param 实体对象
	 */
	public void savePhmBusinessConsRela(PhmBusinessConsRela phmBusinessConsRela) {
		dao.save(phmBusinessConsRela);
	}
	
	/**
	 * 添加对象PhmBusinessConsRela
	 * @param 实体对象
	 */
	public void savePhmBusinessConsRelaList(List<PhmBusinessConsRela> phmBusinessConsRelaList) {
		dao.saveList(phmBusinessConsRelaList);
	}
	
	/**
	 * 更新对象PhmBusinessConsRela
	 * @param 实体对象
	 */
	public void updatePhmBusinessConsRela(PhmBusinessConsRela phmBusinessConsRela) {
		dao.update(phmBusinessConsRela);
	}
	
	/**
	 * 删除对象PhmBusinessConsRela
	 * @param id数据组
	 */
	public void deletePhmBusinessConsRela(String[] ids) {
		dao.delete(ids, PhmBusinessConsRela.class);
	}	
}
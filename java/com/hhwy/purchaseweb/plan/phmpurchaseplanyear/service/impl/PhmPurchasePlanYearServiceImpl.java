package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmforecastprc.service.PhmForecastPrcService;
import com.hhwy.purchaseweb.plan.phmplanyearconsrela.service.PhmPlanYearConsRelaService;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.service.PhmPurchasePlanYearService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmPlanYearConsRela;
import com.hhwy.purchase.domain.PhmPurchasePlanYear;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PhmPurchasePlanYearVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PurchasePlanYearDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.SchemeDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PhmPurchasePlanYearService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmPurchasePlanYearServiceImpl implements PhmPurchasePlanYearService {

	public static final Logger log = LoggerFactory.getLogger(PhmPurchasePlanYearServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	@Autowired
	PhmForecastPrcService phmForecastPrcService;
	
	/**
	 * phmPlanYearConsRelaService 年度购电计划和用户的关系表的service
	 */
	@Autowired
	private PhmPlanYearConsRelaService phmPlanYearConsRelaService;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmPurchasePlanYear
	 * @param ID
	 * @return PhmPurchasePlanYear
	 */
	public QueryResult<PhmPurchasePlanYear> getPhmPurchasePlanYearByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
		QueryResult<PhmPurchasePlanYear> queryResult = new QueryResult<PhmPurchasePlanYear>();
		long total = getPhmPurchasePlanYearCountByParams(phmPurchasePlanYearVo);
		List<PhmPurchasePlanYear> phmPurchasePlanYearList = getPhmPurchasePlanYearListByParams(phmPurchasePlanYearVo);
		queryResult.setTotal(total);
		queryResult.setData(phmPurchasePlanYearList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmPurchasePlanYear数量
	 * @param PhmPurchasePlanYearVo
	 * @return Integer
	 */
	public Integer getPhmPurchasePlanYearCountByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPurchasePlanYear.sql.getPhmPurchasePlanYearCountByParams",phmPurchasePlanYearVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmPurchasePlanYear列表
	 * @param PhmPurchasePlanYearVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPurchasePlanYear> getPhmPurchasePlanYearListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPurchasePlanYearVo == null){
			phmPurchasePlanYearVo = new PhmPurchasePlanYearVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPurchasePlanYear> phmPurchasePlanYearList = (List<PhmPurchasePlanYear>)dao.getBySql("phmPurchasePlanYear.sql.getPhmPurchasePlanYearListByParams",phmPurchasePlanYearVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmPurchasePlanYearList);
		return phmPurchasePlanYearList;
	}
	/**
	 * 根据查询条件获取单个对象PhmPurchasePlanYear
	 * @param PhmPurchasePlanYearVo
	 * @return PhmPurchasePlanYear
	 */
	public PhmPurchasePlanYear getPhmPurchasePlanYearOneByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
		PhmPurchasePlanYear phmPurchasePlanYear = null;
		List<PhmPurchasePlanYear> phmPurchasePlanYearList = getPhmPurchasePlanYearListByParams(phmPurchasePlanYearVo);
		if(phmPurchasePlanYearList != null && phmPurchasePlanYearList.size() > 0){
			phmPurchasePlanYear = phmPurchasePlanYearList.get(0);
		}
		return phmPurchasePlanYear;
	}
	
	/**
	 * 根据ID获取对象PhmPurchasePlanYear
	 * @param ID
	 * @return PhmPurchasePlanYear
	 */
	public PhmPurchasePlanYear getPhmPurchasePlanYearById(String id) {
		return dao.findById(id, PhmPurchasePlanYear.class);
	}	
	
	/**
     * 分页获取购电计划
     * getPurchasePlanYearByPage(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * QueryResult<PurchasePlanYearDetail>
     * @exception 
     * @since  1.0.0
     */
	@Override
    public QueryResult<PurchasePlanYearDetail> getPurchasePlanYearByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
        QueryResult<PurchasePlanYearDetail> queryResult = new QueryResult<PurchasePlanYearDetail>();
        long total = getPhmPurchasePlanYearCountByParams(phmPurchasePlanYearVo);
        List<PurchasePlanYearDetail> list = getPurchasePlanYearListByParams(phmPurchasePlanYearVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }

    /**
     * 根据条件获取购电计划列表
     * getPurchasePlanYearListByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * List<PurchasePlanYearDetail>
     * @exception 
     * @since  1.0.0
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<PurchasePlanYearDetail> getPurchasePlanYearListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
	    //当Vo为空时,初始化Vo对象,应用分页参数
        if(phmPurchasePlanYearVo == null){
            phmPurchasePlanYearVo = new PhmPurchasePlanYearVo();
        }
        Parameter.isFilterData.set(true);
        List<PurchasePlanYearDetail> list = (List<PurchasePlanYearDetail>)dao.getBySql("phmPurchasePlanYear.sql.getPurchasePlanYearListByParams",phmPurchasePlanYearVo);
        Parameter.isFilterData.set(false);
        return list;
    }
    
	/**
     * 分页获取用户信息
     * getConsInfoDetailByPage(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * QueryResult<ConsInfoDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<ConsInfoDetail> getConsInfoDetailByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
        QueryResult<ConsInfoDetail> queryResult = new QueryResult<ConsInfoDetail>();
        long total = getConsInfoDetailCountByParams(phmPurchasePlanYearVo);
        List<ConsInfoDetail> phmPurchasePlanYearList = getConsInfoDetailListByParams(phmPurchasePlanYearVo);
        queryResult.setTotal(total);
        queryResult.setData(phmPurchasePlanYearList);
        return queryResult;
    }

    /**
     * 获取用户信息列表
     * getConsInfoDetailListByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * List<ConsInfoDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<ConsInfoDetail> getConsInfoDetailListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception{
        if(phmPurchasePlanYearVo == null){
            phmPurchasePlanYearVo = new PhmPurchasePlanYearVo();
        }
        Parameter.isFilterData.set(true);
        List<ConsInfoDetail> list = (List<ConsInfoDetail>)dao.getBySql("phmPurchasePlanYear.sql.getAgreConsList",phmPurchasePlanYearVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 获取用户信息数量
     * getConsInfoDetailCountByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getConsInfoDetailCountByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("phmPurchasePlanYear.sql.getAgreConsCount",phmPurchasePlanYearVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 校验该年份是否存在计划
     * checkPlan(描述这个方法的作用)<br/>
     * @param year 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void checkPlan(String year){
        PhmPurchasePlanYearVo phmPurchasePlanYearVo = new PhmPurchasePlanYearVo();
        phmPurchasePlanYearVo.getPhmPurchasePlanYear().setYear(year);
        int total = getPhmPurchasePlanYearCountByParams(phmPurchasePlanYearVo);
        if(total>0){
            throw new RuntimeException("已经存在"+year+"年购电计划！");
        }
    }
    
    /**
     * 获取初始化方案信息
     * getInitScheme(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * SchemeVo
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public SchemeDetail getInitScheme(String year) throws Exception{
    	Parameter.isFilterData.set(true);
        SchemeDetail schemeDetail = (SchemeDetail) dao.getOneBySQL("phmPurchasePlanYear.sql.getInitScheme", year);
        Parameter.isFilterData.set(false);
        if(schemeDetail==null){
            schemeDetail = new SchemeDetail();
        }
//        PhmForecastPrcVo phmForecastPrcVo = new PhmForecastPrcVo();
//        phmForecastPrcVo.getPhmForecastPrc().setYear(year);
//        PhmForecastPrc phmForecastPrc = phmForecastPrcService.getPhmForecastPrcOneByParams(phmForecastPrcVo);
//        schemeDetail.setPhmForecastPrc(phmForecastPrc);
        return schemeDetail;
    }
    
	/**
	 * 添加对象PhmPurchasePlanYear
	 * @param 实体对象
	 * @throws Exception 
	 */
    @Transactional
	public void savePhmPurchasePlanYear(PhmPurchasePlanYear phmPurchasePlanYear) throws Exception {
        phmPurchasePlanYear.setPlanName(phmPurchasePlanYear.getYear()+"年购电计划");
		dao.save(phmPurchasePlanYear);
		PhmPurchasePlanYearVo params = new PhmPurchasePlanYearVo();
		params.setStartYear(phmPurchasePlanYear.getYear());
		params.setPagingFlag(false);
		//获取所有本年度的合同用户
		List<ConsInfoDetail> phmPurchasePlanYearList = getConsInfoDetailListByParams(params);
		if(phmPurchasePlanYearList == null || phmPurchasePlanYearList.size() == 0) {
			throw new RuntimeException("本年度没有售电合同！");
		}
		List<PhmPlanYearConsRela> relaList = new ArrayList<>();
		for (ConsInfoDetail consInfoDetail : phmPurchasePlanYearList) {
			PhmPlanYearConsRela rela = new PhmPlanYearConsRela();
			rela.setId(null);
			rela.setConsId(consInfoDetail.getId());
			rela.setPlanId(phmPurchasePlanYear.getId());
			relaList.add(rela);
		}
		phmPlanYearConsRelaService.savePhmPlanYearConsRelaList(relaList);
	}
	
	/**
	 * 添加对象PhmPurchasePlanYear
	 * @param 实体对象
	 */
	public void savePhmPurchasePlanYearList(List<PhmPurchasePlanYear> phmPurchasePlanYearList) {
		dao.saveList(phmPurchasePlanYearList);
	}
	
	/**
	 * @Title: updatePhmPurchasePlanYear
	 * @Description: 更新对象PhmPurchasePlanYear,先删除年度购电计划和用户的关联关系表，然后再添加
	 * 				（这里因时间紧迫暂时没有考虑删除再添加关联关系是否会影响其他功能。。。）
	 * @param phmPurchasePlanYear 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年12月15日 下午2:25:15
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年12月15日 下午2:25:15
	 * @since  1.0.0
	 */
	@Transactional
	public void updatePhmPurchasePlanYear(PhmPurchasePlanYear phmPurchasePlanYear) throws Exception{
		dao.update(phmPurchasePlanYear);
		//删除其下所有计划于用户的关联关系信息
		phmPlanYearConsRelaService.deletePhmPlanYearConsRelaByPlanId(phmPurchasePlanYear.getId());
		
		PhmPurchasePlanYearVo params = new PhmPurchasePlanYearVo();
		params.setStartYear(phmPurchasePlanYear.getYear());
		params.setPagingFlag(false);
		//获取所有本年度的合同用户
		List<ConsInfoDetail> phmPurchasePlanYearList = getConsInfoDetailListByParams(params);
		if(phmPurchasePlanYearList == null || phmPurchasePlanYearList.size() == 0) {
			throw new RuntimeException("本年度没有售电合同！");
		}
		List<PhmPlanYearConsRela> relaList = new ArrayList<>();
		for (ConsInfoDetail consInfoDetail : phmPurchasePlanYearList) {
			PhmPlanYearConsRela rela = new PhmPlanYearConsRela();
			rela.setId(null);
			rela.setConsId(consInfoDetail.getId());
			rela.setPlanId(phmPurchasePlanYear.getId());
			relaList.add(rela);
		}
		phmPlanYearConsRelaService.savePhmPlanYearConsRelaList(relaList);
	}
	
	/**
	 * 删除对象PhmPurchasePlanYear
	 * @param id数据组
	 */
	@Transactional
	public void deletePhmPurchasePlanYear(String[] ids) {
	    if(ids.length>0){
	        dao.delete(ids, PhmPurchasePlanYear.class);
	        //删除长协电量分月
            dao.deleteBySql("phmPurchasePlanYear.sql.deleteLc", ids);
	        //删除关系
	        dao.deleteBySql("phmPurchasePlanYear.sql.deletePlan", ids);
	        //删除方案
	        dao.deleteBySql("phmPurchasePlanYear.sql.deleteScheme", ids);
	    }
	}	
}
package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.service.PhmBusinessPlanSchemeService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.StringUtils;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmBusinessPlanScheme;
import com.hhwy.purchase.domain.PhmForecastPrc;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhfTradingCenterDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeVo;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.SchemeVo;
import com.hhwy.purchaseweb.plan.phmforecastprc.domain.PhmForecastPrcVo;
import com.hhwy.purchaseweb.plan.phmforecastprc.service.PhmForecastPrcService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmBusinessPlanSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmBusinessPlanSchemeServiceImpl implements PhmBusinessPlanSchemeService {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessPlanSchemeServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	PhmForecastPrcService phmForecastPrcService;
	/**
	 * 分页获取对象PhmBusinessPlanScheme
	 * @param ID
	 * @return PhmBusinessPlanScheme
	 */
	public QueryResult<PhmBusinessPlanScheme> getPhmBusinessPlanSchemeByPage(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception{
		QueryResult<PhmBusinessPlanScheme> queryResult = new QueryResult<PhmBusinessPlanScheme>();
		long total = getPhmBusinessPlanSchemeCountByParams(phmBusinessPlanSchemeVo);
		List<PhmBusinessPlanScheme> phmBusinessPlanSchemeList = getPhmBusinessPlanSchemeListByParams(phmBusinessPlanSchemeVo);
		queryResult.setTotal(total);
		queryResult.setData(phmBusinessPlanSchemeList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlanScheme数量
	 * @param PhmBusinessPlanSchemeVo
	 * @return Integer
	 */
	public Integer getPhmBusinessPlanSchemeCountByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmBusinessPlanScheme.sql.getPhmBusinessPlanSchemeCountByParams",phmBusinessPlanSchemeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlanScheme列表
	 * @param PhmBusinessPlanSchemeVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmBusinessPlanScheme> getPhmBusinessPlanSchemeListByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmBusinessPlanSchemeVo == null){
			phmBusinessPlanSchemeVo = new PhmBusinessPlanSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmBusinessPlanScheme> phmBusinessPlanSchemeList = (List<PhmBusinessPlanScheme>)dao.getBySql("phmBusinessPlanScheme.sql.getPhmBusinessPlanSchemeListByParams",phmBusinessPlanSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmBusinessPlanSchemeList);
		return phmBusinessPlanSchemeList;
	}
	/**
	 * 根据查询条件获取单个对象PhmBusinessPlanScheme
	 * @param PhmBusinessPlanSchemeVo
	 * @return PhmBusinessPlanScheme
	 */
	public PhmBusinessPlanScheme getPhmBusinessPlanSchemeOneByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception{
		PhmBusinessPlanScheme phmBusinessPlanScheme = null;
		List<PhmBusinessPlanScheme> phmBusinessPlanSchemeList = getPhmBusinessPlanSchemeListByParams(phmBusinessPlanSchemeVo);
		if(phmBusinessPlanSchemeList != null && phmBusinessPlanSchemeList.size() > 0){
			phmBusinessPlanScheme = phmBusinessPlanSchemeList.get(0);
		}
		return phmBusinessPlanScheme;
	}
	
	/**
     * 根据planId获取树上列表
     * getTreeList(描述这个方法的作用)<br/>
     * @param planId
     * @return 
     * List<PhmBusinessPlanSchemeDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhmBusinessPlanSchemeDetail> getTreeList(String planId){
        return (List<PhmBusinessPlanSchemeDetail>) dao.getBySql("phmBusinessPlanScheme.sql.getTreeList", planId);
    }
    
    /**
     * 根据年份获取成交平均价
     * getPhfTradingCenterDetailByYear(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * List<PhfTradingCenterDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhfTradingCenterDetail> getPhfTradingCenterDetailByYear(String year){
    	Parameter.isFilterData.set(true);
    	List<PhfTradingCenterDetail> list = (List<PhfTradingCenterDetail>) dao.getBySql("phmBusinessPlanScheme.sql.getPhfTradingCenterDetail", year);
    	Parameter.isFilterData.set(false);
        return list;
    }
    
    
	/**
	 * 根据ID获取对象PhmBusinessPlanScheme
	 * @param ID
	 * @return PhmBusinessPlanScheme
	 */
	public PhmBusinessPlanScheme getPhmBusinessPlanSchemeById(String id) {
		return dao.findById(id, PhmBusinessPlanScheme.class);
	}	
	
	/**
     * 获取初始信息
     * getInitScheme(描述这个方法的作用)<br/>
     * @param planId
     * @return 
     * SchemeVo
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public SchemeVo getInitScheme(String planId,String year) throws Exception{
        SchemeVo schemeVo = new SchemeVo();
        PhmBusinessPlanScheme phmBusinessPlanScheme = (PhmBusinessPlanScheme) dao.getOneBySQL("phmBusinessPlanScheme.sql.getInitScheme", planId);
        schemeVo.setPhmBusinessPlanScheme(phmBusinessPlanScheme);
        PhmForecastPrcVo phmForecastPrcVo = new PhmForecastPrcVo();
        phmForecastPrcVo.getPhmForecastPrc().setYear(year);
        PhmForecastPrc phmForecastPrc = phmForecastPrcService.getPhmForecastPrcOneByParams(phmForecastPrcVo);
        schemeVo.setPhmForecastPrc(phmForecastPrc);
        return schemeVo;
    }
    
    /**
     * 计算利润
     * calc(描述这个方法的作用)<br/>
     * @param phmBusinessPlanScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void calc(PhmBusinessPlanScheme phmBusinessPlanScheme){
        BigDecimal oneHundred = new BigDecimal(100);
        //长协总量   预测合同总量*长协占比
        BigDecimal lcPq = phmBusinessPlanScheme.getAgrePq().multiply(phmBusinessPlanScheme.getLcProp()).divide(oneHundred,2, BigDecimal.ROUND_HALF_UP);
        phmBusinessPlanScheme.setLcPq(lcPq);
        //长协总价   长协单价*长协总量
        BigDecimal lcAwt = phmBusinessPlanScheme.getLcPrc().multiply(lcPq);
        //竞价总量 预测合同总量*竞价占比
        BigDecimal bidPq = phmBusinessPlanScheme.getAgrePq().multiply(phmBusinessPlanScheme.getBidProp()).divide(oneHundred,2, BigDecimal.ROUND_HALF_UP);
        phmBusinessPlanScheme.setBidPq(bidPq);
        BigDecimal bidAwt = phmBusinessPlanScheme.getBidPrc().multiply(bidPq);
        //利润 = 合同金额 - 长协 - 竞价
        BigDecimal profit = phmBusinessPlanScheme.getAgreAmt().subtract(lcAwt).subtract(bidAwt).divide(new BigDecimal(10000))
                .setScale(6, BigDecimal.ROUND_HALF_UP);
        phmBusinessPlanScheme.setProfit(profit);
        BigDecimal dvalueProfit = phmBusinessPlanScheme.getProfitNorm().subtract(profit);
        if(dvalueProfit.compareTo(BigDecimal.ZERO)<0){
            dvalueProfit = BigDecimal.ZERO;
        }
        phmBusinessPlanScheme.setDvalueProfit(dvalueProfit);
    }
    
    /**
     * 校验数据的完整
     * check(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void check(String id){
        PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo = new PhmBusinessPlanSchemeVo();
        phmBusinessPlanSchemeVo.getPhmBusinessPlanScheme().setPlanId(id);
        int total = getPhmBusinessPlanSchemeCountByParams(phmBusinessPlanSchemeVo);
        if(total==0){
            throw new RuntimeException("该计划尚未有方案！请先保存方案在提交");
        }
    }
    
	/**
	 * 添加对象PhmBusinessPlanScheme
	 * @param 实体对象
	 */
	public void savePhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme) {
		dao.save(phmBusinessPlanScheme);
	}
	
	/**
     * 保存
     * save(描述这个方法的作用)<br/>
     * @param phmBusinessPlanScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void save(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo){
        PhmBusinessPlanScheme phmBusinessPlanScheme = phmBusinessPlanSchemeVo.getPhmBusinessPlanScheme();
        if(StringUtils.isNull(phmBusinessPlanScheme.getSchemeName())){
            PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo2 = new PhmBusinessPlanSchemeVo();
            phmBusinessPlanSchemeVo2.getPhmBusinessPlanScheme().setPlanId(phmBusinessPlanScheme.getPlanId());
            int total = getPhmBusinessPlanSchemeCountByParams(phmBusinessPlanSchemeVo2);
            StringBuffer name = new StringBuffer(phmBusinessPlanSchemeVo.getYear());
            name.append("年合同总电量分配方案").append(total+1).append("|").append("长协电量").append(phmBusinessPlanScheme.getLcProp()).append("%").append(",")
                .append("长协电价").append(phmBusinessPlanScheme.getLcPrc()).append("元/兆瓦时").append(",").append("竞价电价")
                .append(phmBusinessPlanScheme.getBidPrc()).append("元/兆瓦时");
            phmBusinessPlanScheme.setSchemeName(name.toString());
        }
        dao.save(phmBusinessPlanScheme);
    }
	
	/**
	 * 添加对象PhmBusinessPlanScheme
	 * @param 实体对象
	 */
	public void savePhmBusinessPlanSchemeList(List<PhmBusinessPlanScheme> phmBusinessPlanSchemeList) {
		dao.saveList(phmBusinessPlanSchemeList);
	}
	
	/**
	 * 更新对象PhmBusinessPlanScheme
	 * @param 实体对象
	 */
	public void updatePhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme) {
		dao.update(phmBusinessPlanScheme);
	}
	
	/**
	 * 删除对象PhmBusinessPlanScheme
	 * @param id数据组
	 */
	public void deletePhmBusinessPlanScheme(String[] ids) {
		dao.delete(ids, PhmBusinessPlanScheme.class);
	}	
}
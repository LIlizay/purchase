package com.hhwy.purchaseweb.plan.phmphplanyearscheme.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.service.PhmPhPlanYearSchemeService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.StringUtils;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmPhPlanYearScheme;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.CalcVo;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeDetail;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmPhPlanYearSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmPhPlanYearSchemeServiceImpl implements PhmPhPlanYearSchemeService {

	public static final Logger log = LoggerFactory.getLogger(PhmPhPlanYearSchemeServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmPhPlanYearScheme
	 * @param ID
	 * @return PhmPhPlanYearScheme
	 */
	public QueryResult<PhmPhPlanYearScheme> getPhmPhPlanYearSchemeByPage(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception{
		QueryResult<PhmPhPlanYearScheme> queryResult = new QueryResult<PhmPhPlanYearScheme>();
		long total = getPhmPhPlanYearSchemeCountByParams(phmPhPlanYearSchemeVo);
		List<PhmPhPlanYearScheme> phmPhPlanYearSchemeList = getPhmPhPlanYearSchemeListByParams(phmPhPlanYearSchemeVo);
		queryResult.setTotal(total);
		queryResult.setData(phmPhPlanYearSchemeList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmPhPlanYearScheme数量
	 * @param PhmPhPlanYearSchemeVo
	 * @return Integer
	 */
	public Integer getPhmPhPlanYearSchemeCountByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPhPlanYearScheme.sql.getPhmPhPlanYearSchemeCountByParams",phmPhPlanYearSchemeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmPhPlanYearScheme列表
	 * @param PhmPhPlanYearSchemeVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPhPlanYearScheme> getPhmPhPlanYearSchemeListByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPhPlanYearSchemeVo == null){
			phmPhPlanYearSchemeVo = new PhmPhPlanYearSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPhPlanYearScheme> phmPhPlanYearSchemeList = 
		        (List<PhmPhPlanYearScheme>)dao.getBySql("phmPhPlanYearScheme.sql.getPhmPhPlanYearSchemeListByParams",phmPhPlanYearSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmPhPlanYearSchemeList);
		return phmPhPlanYearSchemeList;
	}
	/**
	 * 根据查询条件获取单个对象PhmPhPlanYearScheme
	 * @param PhmPhPlanYearSchemeVo
	 * @return PhmPhPlanYearScheme
	 */
	public PhmPhPlanYearScheme getPhmPhPlanYearSchemeOneByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception{
		PhmPhPlanYearScheme phmPhPlanYearScheme = null;
		List<PhmPhPlanYearScheme> phmPhPlanYearSchemeList = getPhmPhPlanYearSchemeListByParams(phmPhPlanYearSchemeVo);
		if(phmPhPlanYearSchemeList != null && phmPhPlanYearSchemeList.size() > 0){
			phmPhPlanYearScheme = phmPhPlanYearSchemeList.get(0);
		}
		return phmPhPlanYearScheme;
	}
	
	/**
     * 分页获取方案详细
     * getSchemeDetailByPage(描述这个方法的作用)<br/>
     * @param phmPhPlanYearSchemeVo
     * @return
     * @throws Exception 
     * QueryResult<PhmPhPlanYearSchemeDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<PhmPhPlanYearSchemeDetail> getSchemeDetailByPage(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception{
        QueryResult<PhmPhPlanYearSchemeDetail> queryResult = new QueryResult<PhmPhPlanYearSchemeDetail>();
        long total = getSchemeDetailCount(phmPhPlanYearSchemeVo);
        List<PhmPhPlanYearSchemeDetail> phmPhPlanYearSchemeList = getSchemeDetailList(phmPhPlanYearSchemeVo);
        queryResult.setTotal(total);
        queryResult.setData(phmPhPlanYearSchemeList);
        return queryResult;
    }
    
	/**
     * 获取方案详细
     * getSchemeDetailList(描述这个方法的作用)<br/>
     * @param phmPhPlanYearSchemeVo
     * @return 
     * List<PhmPhPlanYearSchemeDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhmPhPlanYearSchemeDetail> getSchemeDetailList(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo){
    	Parameter.isFilterData.set(true);
    	List<PhmPhPlanYearSchemeDetail> list = (List<PhmPhPlanYearSchemeDetail>) dao.getBySql("phmPhPlanYearScheme.sql.getTreeList", phmPhPlanYearSchemeVo);
    	Parameter.isFilterData.set(false);
        return list;
    }
    
    /**
     * 获取方案详细数量
     * getSchemeDetailCount(描述这个方法的作用)<br/>
     * @param phmPhPlanYearSchemeVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getSchemeDetailCount(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("phmPhPlanYearScheme.sql.getTreeCount",phmPhPlanYearSchemeVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 根据ID获取对象PhmPhPlanYearScheme
     * @param ID
     * @return PhmPhPlanYearScheme
     */
    public PhmPhPlanYearScheme getPhmPhPlanYearSchemeById(String id) {
        return dao.findById(id, PhmPhPlanYearScheme.class);
    }   
    
	/**
	 * 添加对象PhmPhPlanYearScheme
	 * @param 实体对象
	 */
	public void savePhmPhPlanYearScheme(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) {
	    PhmPhPlanYearScheme phmPhPlanYearScheme = phmPhPlanYearSchemeVo.getPhmPhPlanYearScheme();
	    if(StringUtils.isNull(phmPhPlanYearScheme.getSchemeName())){
	        PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo2 = new PhmPhPlanYearSchemeVo();
	        phmPhPlanYearSchemeVo2.getPhmPhPlanYearScheme().setPlanId(phmPhPlanYearScheme.getPlanId());
	        int total = getPhmPhPlanYearSchemeCountByParams(phmPhPlanYearSchemeVo2);
	        StringBuffer name = new StringBuffer(phmPhPlanYearSchemeVo.getYear());
	        name.append("年合同总电量分配方案").append(total+1).append("|").append("长协电量").append(phmPhPlanYearScheme.getLcProp()).append("%").append(",")
	            .append("长协电价").append(phmPhPlanYearScheme.getLcExperiencePrc()).append("元/兆瓦时").append(",").append("竞价电价")
	            .append(phmPhPlanYearScheme.getBidExperiencePrc()).append("元/兆瓦时");
	        phmPhPlanYearScheme.setSchemeName(name.toString());
	    }
		dao.save(phmPhPlanYearScheme);
	}
	
	/**
	 * 添加对象PhmPhPlanYearScheme
	 * @param 实体对象
	 */
	public void savePhmPhPlanYearSchemeList(List<PhmPhPlanYearScheme> phmPhPlanYearSchemeList) {
		dao.saveList(phmPhPlanYearSchemeList);
	}
	
	/**
	 * 更新对象PhmPhPlanYearScheme
	 * @param 实体对象
	 */
	public void updatePhmPhPlanYearScheme(PhmPhPlanYearScheme phmPhPlanYearScheme) {
		dao.update(phmPhPlanYearScheme);
	}
	
	/**
	 * 删除对象PhmPhPlanYearScheme
	 * @param id数据组
	 */
	public void deletePhmPhPlanYearScheme(String[] ids) {
		dao.delete(ids, PhmPhPlanYearScheme.class);
	}	
	
	/**
     * 系统计算
     * sysCalc(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    public CalcVo sysCalc(PhmPhPlanYearScheme phmPhPlanYearScheme){
        CalcVo calcVo = new CalcVo();
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal tenThousand = new BigDecimal(10000);
        BigDecimal profitIndex = phmPhPlanYearScheme.getProfitIndex().multiply(tenThousand);
        BigDecimal agreAmt = phmPhPlanYearScheme.getAgreAmt();
        BigDecimal agrePq = phmPhPlanYearScheme.getAgrePq();
        BigDecimal lcPrc = phmPhPlanYearScheme.getLcPrc();
        BigDecimal bidPrc = phmPhPlanYearScheme.getBidPrc();
        BigDecimal lcAmt = agrePq.multiply(lcPrc);
        BigDecimal bidAmt = agrePq.multiply(bidPrc);
        //全买长协利润
        BigDecimal lcProfit = agreAmt.subtract(lcAmt);
        //全买竞价利润
        BigDecimal bidProfit = agreAmt.subtract(bidAmt);
        BigDecimal maxProfit = null;
        BigDecimal minProfit = null;
        if(lcProfit.compareTo(bidProfit)>0){
            maxProfit = lcProfit;
            minProfit = bidProfit;
            calcVo.setLcPrc(lcPrc);
            calcVo.setLcPq(agrePq);
            calcVo.setLcProp(oneHundred);
            calcVo.setBidPrc(bidPrc);
            calcVo.setBidPq(BigDecimal.ZERO);
            calcVo.setBidProp(BigDecimal.ZERO);
        }else{
            maxProfit = bidProfit;
            minProfit = lcProfit;
            calcVo.setLcPrc(lcPrc);
            calcVo.setLcPq(BigDecimal.ZERO);
            calcVo.setLcProp(BigDecimal.ZERO);
            calcVo.setBidPrc(bidPrc);
            calcVo.setBidPq(agrePq);
            calcVo.setBidProp(oneHundred);
        }
        if(maxProfit.compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("该方案下长协电价和结算电价均大于平均代理单价，请重新制定长协经验电价和竞价经验电价！");
        }
        calcVo.setMaxProfit(maxProfit.divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
        //如果最小利润小于0，则
        if(minProfit.compareTo(BigDecimal.ZERO)<0){
            minProfit = BigDecimal.ZERO;
        }
        calcVo.setMinProfit(minProfit.divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
        if(profitIndex.compareTo(minProfit)<0){
            calcVo.setMinAddProfit(minProfit.subtract(profitIndex).divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
            calcVo.setMaxAddProfit(maxProfit.subtract(profitIndex).divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
        }else if(profitIndex.compareTo(maxProfit)>0){
            calcVo.setMinDifferenceProfit(profitIndex.subtract(maxProfit).divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
            calcVo.setMaxDifferenceProfit(profitIndex.subtract(minProfit).divide(tenThousand,2,BigDecimal.ROUND_HALF_UP));
            //计算差值电量
            phmPhPlanYearScheme.setDifferenceProfit(BigDecimal.ZERO);
            calcBidDifferentPq(phmPhPlanYearScheme);
            calcVo.setLcPq(phmPhPlanYearScheme.getLcPq());
            calcVo.setLcProp(phmPhPlanYearScheme.getLcProp());
            calcVo.setBidPq(phmPhPlanYearScheme.getBidPq());
            calcVo.setBidProp(phmPhPlanYearScheme.getBidProp());
            calcVo.setBidDifferencePq(phmPhPlanYearScheme.getBidDifferencePq());
        }else{
            //如果利润在区间内，计算长协和差值
            //长协电电量=(v-p-e*b)/(a-b)
            BigDecimal lcPq = agreAmt.subtract(profitIndex).subtract(agrePq.multiply(bidPrc)).divide(lcPrc.subtract(bidPrc),2,BigDecimal.ROUND_HALF_UP);
            BigDecimal bidPq = agrePq.subtract(lcPq);
            BigDecimal lcProp = lcPq.divide(agrePq,4,BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
            BigDecimal bidProp = oneHundred.subtract(lcProp);
            calcVo.setLcPq(lcPq);
            calcVo.setBidPq(bidPq);
            calcVo.setLcProp(lcProp);
            calcVo.setBidProp(bidProp);
        }
        return calcVo;
    }
    
	/**
     * 计算
     * calc(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void calc(PhmPhPlanYearScheme phmPhPlanYearScheme){
        //计算差值电量和其他
        if(phmPhPlanYearScheme.getAddProfit()!=null){
            //计算附加利润
            calcPq(phmPhPlanYearScheme);
        }else if(phmPhPlanYearScheme.getDifferenceProfit()!=null){
            //计算差值
            calcBidDifferentPq(phmPhPlanYearScheme);
        }else if(phmPhPlanYearScheme.getLcPq()!=null){
            //计算占比
            calcProfit(phmPhPlanYearScheme);
        }
        calcCostPrc(phmPhPlanYearScheme);
    }
    
    /**
     * 根据附加利润计算长协电量和竞价电量 以及其占比
     * calcPq(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    private void calcPq(PhmPhPlanYearScheme phmPhPlanYearScheme){
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal tenThousand = new BigDecimal(10000);
        BigDecimal agreAmt = phmPhPlanYearScheme.getAgreAmt();
        BigDecimal profitIndex = phmPhPlanYearScheme.getProfitIndex().multiply(tenThousand);
        BigDecimal addProfit = phmPhPlanYearScheme.getAddProfit().multiply(tenThousand);
        BigDecimal agrePq = phmPhPlanYearScheme.getAgrePq();
        BigDecimal bidPrc = phmPhPlanYearScheme.getBidPrc();
        BigDecimal lcPrc = phmPhPlanYearScheme.getLcPrc();
        
        //长协电电量=(v-p-附加利润-e*b)/(a-b)
        BigDecimal lcPq = agreAmt.subtract(profitIndex.add(addProfit)).subtract(agrePq.multiply(bidPrc)).divide(lcPrc.subtract(bidPrc),2,BigDecimal.ROUND_HALF_UP);
        BigDecimal bidPq = agrePq.subtract(lcPq);
        BigDecimal lcProp = lcPq.divide(agrePq,4,BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
        BigDecimal bidProp = oneHundred.subtract(lcProp);
        
        phmPhPlanYearScheme.setBidPq(bidPq);
        phmPhPlanYearScheme.setLcPq(lcPq);
        phmPhPlanYearScheme.setLcProp(lcProp);
        phmPhPlanYearScheme.setBidProp(bidProp);
    }
    
    /**
     * 根据差值电量计算竞价结算差值电量
     * calcPq(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    private void calcBidDifferentPq(PhmPhPlanYearScheme phmPhPlanYearScheme){
        BigDecimal oneHundred = new BigDecimal(100);
        // p = p* 10000
        BigDecimal tenThousand = new BigDecimal(10000);
        BigDecimal profitIndex = phmPhPlanYearScheme.getProfitIndex().multiply(tenThousand);
        BigDecimal differenceProfit = phmPhPlanYearScheme.getDifferenceProfit().multiply(tenThousand);
        BigDecimal agrePq = phmPhPlanYearScheme.getAgrePq();
        BigDecimal avgPrc = phmPhPlanYearScheme.getAvgPrc();
        BigDecimal bidPrc = phmPhPlanYearScheme.getBidPrc();
        BigDecimal lcPrc = phmPhPlanYearScheme.getLcPrc();
        
        // [p-d-e(c-b)]
        BigDecimal a1 = profitIndex.subtract(differenceProfit).subtract(agrePq.multiply(avgPrc.subtract(bidPrc)));
        // (c+b-2a)
        BigDecimal a2 = avgPrc.add(bidPrc).subtract(lcPrc.add(lcPrc));
        // [(c-a)^2+(c-b)^2+(b-a)^2]
        BigDecimal d1 = avgPrc.subtract(lcPrc);
        BigDecimal d2 = avgPrc.subtract(bidPrc);
        BigDecimal d3 = bidPrc.subtract(lcPrc);
        BigDecimal a3 = d1.multiply(d1).add(d2.multiply(d2)).add(d3.multiply(d3));
        //长协电量   x=[p-d-e(c-b)]*(c+b-2a)/[(c-a)^2+(c-b)^2+(b-a)^2]
        BigDecimal lcPq = a1.multiply(a2).divide(a3,2,BigDecimal.ROUND_HALF_UP);
        BigDecimal bidDifferencePq = null;
        if(lcPq.compareTo(BigDecimal.ZERO)<0){
            lcPq = BigDecimal.ZERO;
            //竞价差值电量  y1=[p-d-e(c-b)]/(c-b)
            bidDifferencePq = a1.divide(d2,2,BigDecimal.ROUND_HALF_UP);
        }else if(lcPq.compareTo(agrePq)>0){
            lcPq = agrePq;
            //e*(a-b)/(c-b)
            BigDecimal a4 = agrePq.multiply(lcPrc.subtract(bidPrc)).divide(avgPrc.subtract(bidPrc),2,BigDecimal.ROUND_HALF_UP);
            // 竞价差值电量  y1=e*(a-b)/(c-b)+[p-d-e(c-b)]/(c-b)
            bidDifferencePq = a4.add(a1.divide(d2,2,BigDecimal.ROUND_HALF_UP));
        }else{
            //(2c-a-b)
            BigDecimal a4 = avgPrc.add(avgPrc).subtract(lcPrc).subtract(bidPrc);
            //竞价差值电量 [p-d-e(c-b)]*(2c-a-b)/[(c-a)^2+(c-b)^2+(b-a)^2]   
            bidDifferencePq = a1.multiply(a4).divide(a3,2,BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal bidPq = agrePq.subtract(lcPq);
        BigDecimal sumPq = lcPq.add(bidPq);
        BigDecimal lcProp = lcPq.divide(sumPq,4,BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
        BigDecimal bidProp = oneHundred.subtract(lcProp);
        phmPhPlanYearScheme.setLcPq(lcPq);
        phmPhPlanYearScheme.setBidPq(bidPq);
        phmPhPlanYearScheme.setLcProp(lcProp);
        phmPhPlanYearScheme.setBidProp(bidProp);
        phmPhPlanYearScheme.setBidDifferencePq(bidDifferencePq);
    }
    
    /**
     * 计算利润
     * calcProfit(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    private void calcProfit(PhmPhPlanYearScheme phmPhPlanYearScheme){
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal lcPq = phmPhPlanYearScheme.getLcPq();
        //竞价电量
        BigDecimal bidPq = phmPhPlanYearScheme.getAgrePq().subtract(lcPq);
        //长协电量占比
        BigDecimal lcProp = phmPhPlanYearScheme.getLcPq().divide(phmPhPlanYearScheme.getAgrePq(),4,BigDecimal.ROUND_HALF_UP).multiply(oneHundred);
        //竞价电量占比
        BigDecimal bidProp = oneHundred.subtract(lcProp);
        phmPhPlanYearScheme.setLcProp(lcProp);
        phmPhPlanYearScheme.setBidPq(bidPq);
        phmPhPlanYearScheme.setBidProp(bidProp);
    }
    
    /**
     * 计算购电成本单价
     * calcCostPrc(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    private void calcCostPrc(PhmPhPlanYearScheme phmPhPlanYearScheme){
        //购电成本 单价 = 总电费/总电量
        BigDecimal lcPq = phmPhPlanYearScheme.getLcPq();
        BigDecimal bidPq = phmPhPlanYearScheme.getBidPq();
        BigDecimal lcPrc = phmPhPlanYearScheme.getLcPrc();
        BigDecimal bidPrc = phmPhPlanYearScheme.getBidPrc();
        BigDecimal bidDifferencePq = phmPhPlanYearScheme.getBidDifferencePq();
        BigDecimal amtSum = null;
        BigDecimal pqSum = null;
        if(bidDifferencePq == null){
            amtSum = lcPq.multiply(lcPrc).add(bidPrc.multiply(bidPq));
            pqSum = lcPq.add(bidPq);
        }else{
            BigDecimal bidSum = bidPq.add(bidDifferencePq);
            amtSum = lcPq.multiply(lcPrc).add(bidPrc.multiply(bidSum));
            pqSum = lcPq.add(bidSum);
        }
        BigDecimal costPrc = amtSum.divide(pqSum,2,BigDecimal.ROUND_HALF_UP);
        phmPhPlanYearScheme.setCostPrc(costPrc);
    }
    
    /**
     * 计算差值电量 
     * calcDvaluePq(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
//    private void calcDvaluePq(PhmPhPlanYearScheme phmPhPlanYearScheme){
//        BigDecimal oneHundred = new BigDecimal(100);
//        //长协成本  a*e*k1
//        BigDecimal lcAwt = phmPhPlanYearScheme.getLcPrc().multiply(phmPhPlanYearScheme.getAgrePq()).multiply(phmPhPlanYearScheme.getLcFinalProp());
//        //竞价成本  b*e*K2
//        BigDecimal bidAwt = phmPhPlanYearScheme.getBidPrc().multiply(phmPhPlanYearScheme.getAgrePq()).multiply(phmPhPlanYearScheme.getBidFinalProp());
//        //利润 g=v-(a*e*K1+b*e*K2)
//        BigDecimal profit = phmPhPlanYearScheme.getAgreAmt().subtract(lcAwt.add(bidAwt).divide(oneHundred));
//        if(profit.compareTo(BigDecimal.ZERO)<=0){
//            throw new RuntimeException("该方案没有利润，请重新制定长协电量占比或电价！");
//        }
//        phmPhPlanYearScheme.setLcProp(phmPhPlanYearScheme.getLcFinalProp());
//        phmPhPlanYearScheme.setBidProp(phmPhPlanYearScheme.getBidFinalProp());
//        //计算购电成本单价
//        calcCostPrc(phmPhPlanYearScheme);
//        BigDecimal sumPq = null;
//        BigDecimal profitIndex = phmPhPlanYearScheme.getProfitIndex().multiply(new BigDecimal(10000));
//        //如果利润小于指标 ,计算差值电量
//        if(profitIndex.compareTo(profit)>0){
//            //总电量  指标/(购电成本-平均代理电价)
//            sumPq = profitIndex.divide(phmPhPlanYearScheme.getAvgPrc().subtract(phmPhPlanYearScheme.getCostPrc()),BigDecimal.ROUND_HALF_UP);
//            //差值电量 = 总电量 - 合同电量
//            BigDecimal dvaluePq =sumPq.subtract(phmPhPlanYearScheme.getAgrePq()).setScale(2, BigDecimal.ROUND_HALF_UP);
//            phmPhPlanYearScheme.setDvaluePq(dvaluePq);
//        }else{
//            sumPq = phmPhPlanYearScheme.getAgrePq();
//            phmPhPlanYearScheme.setDvaluePq(BigDecimal.ZERO);
//        }
//        //计算长协和竞价电量
//        BigDecimal lcPq = sumPq.multiply(phmPhPlanYearScheme.getLcProp()).divide(oneHundred,2,BigDecimal.ROUND_HALF_UP);
//        BigDecimal bidPq = sumPq.multiply(phmPhPlanYearScheme.getBidProp().divide(oneHundred,2,BigDecimal.ROUND_HALF_UP));
//        phmPhPlanYearScheme.setLcPq(lcPq);
//        phmPhPlanYearScheme.setBidPq(bidPq);
//    }
}
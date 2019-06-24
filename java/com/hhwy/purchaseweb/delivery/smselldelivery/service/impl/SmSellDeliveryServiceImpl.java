package com.hhwy.purchaseweb.delivery.smselldelivery.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.arithmetic.util.ValueShiftUtil;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.CalcDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryVo;
import com.hhwy.purchaseweb.delivery.smselldelivery.service.SmSellDeliveryService;
import com.hhwy.selling.domain.SmSellDelivery;

/**
 * SmSellDeliveryService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmSellDeliveryServiceImpl implements SmSellDeliveryService {

	public static final Logger log = LoggerFactory.getLogger(SmSellDeliveryServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmSellDelivery
	 * @param ID
	 * @return SmSellDelivery
	 */
	public QueryResult<SmSellDeliveryDetail> getSmSellDeliveryByPage(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
		QueryResult<SmSellDeliveryDetail> queryResult = new QueryResult<SmSellDeliveryDetail>();
		long total = getSmSellDeliveryCountByParams(smSellDeliveryVo);
		List<SmSellDeliveryDetail> smSellDeliveryList = getSmSellDeliveryListByParams(smSellDeliveryVo);
		queryResult.setTotal(total);
		queryResult.setData(smSellDeliveryList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmSellDelivery数量
	 * @param SmSellDeliveryVo
	 * @return Integer
	 */
	public Integer getSmSellDeliveryCountByParams(SmSellDeliveryVo smSellDeliveryVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smSellDelivery.sql.getSmSellDeliveryCountByParams",smSellDeliveryVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmSellDelivery列表
	 * @param SmSellDeliveryVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmSellDeliveryDetail> getSmSellDeliveryListByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smSellDeliveryVo == null){
			smSellDeliveryVo = new SmSellDeliveryVo();
		}
		Parameter.isFilterData.set(true);
		List<SmSellDeliveryDetail> smSellDeliveryList = (List<SmSellDeliveryDetail>)dao.getBySql("smSellDelivery.sql.getSmSellDeliveryListByParams",smSellDeliveryVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smSellDeliveryList);
		return smSellDeliveryList;
	}
	/**
	 * 根据查询条件获取单个对象SmSellDelivery
	 * @param SmSellDeliveryVo
	 * @return SmSellDelivery
	 */
	public SmSellDeliveryDetail getSmSellDeliveryOneByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
		SmSellDeliveryDetail smSellDelivery = null;
		List<SmSellDeliveryDetail> smSellDeliveryList = getSmSellDeliveryListByParams(smSellDeliveryVo);
		if(smSellDeliveryList != null && smSellDeliveryList.size() > 0){
			smSellDelivery = smSellDeliveryList.get(0);
		}
		return smSellDelivery;
	}
	
	/**
     * 分页获取未结算列表
     * getCalcByPage(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return
     * @throws Exception 
     * QueryResult<SmSellDelivery>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<SmSellDeliveryDetail> getCalcByPage(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
        QueryResult<SmSellDeliveryDetail> queryResult = new QueryResult<SmSellDeliveryDetail>();
        long total = getCalcCountByParams(smSellDeliveryVo);
        List<SmSellDeliveryDetail> list = getCalcListPageByParams(smSellDeliveryVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }

    /**
     * 获取未结算列表
     * getCalcListByParams(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return
     * @throws Exception 
     * List<SmSellDelivery>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<SmSellDeliveryDetail> getCalcListByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(smSellDeliveryVo == null){
            smSellDeliveryVo = new SmSellDeliveryVo();
        }
        Parameter.isFilterData.set(true);
        List<SmSellDeliveryDetail> list = (List<SmSellDeliveryDetail>)dao.getBySql("smSellDelivery.sql.getCalcList",smSellDeliveryVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 
     * @Title: getCalcListPageByParams<br/>
     * @Description: 查询页面展示数据<br/>
     * @param smSellDeliveryVo
     * @return
     * @throws Exception <br/>
     * List<SmSellDeliveryDetail><br/>
     * <b>创 建 人：</b>chengpeng<br/>
     * <b>创建时间:</b>2017年9月18日 下午4:04:25
     * <b>修 改 人：</b>chengpeng<br/>
     * <b>修改时间:</b>2017年9月18日 下午4:04:25
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<SmSellDeliveryDetail> getCalcListPageByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(smSellDeliveryVo == null){
            smSellDeliveryVo = new SmSellDeliveryVo();
        }
        Parameter.isFilterData.set(true);
        List<SmSellDeliveryDetail> list = (List<SmSellDeliveryDetail>)dao.getBySql("smSellDelivery.sql.getCalcListPage",smSellDeliveryVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 获取未结算数量
     * getCalcCountByParams(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getCalcCountByParams(SmSellDeliveryVo smSellDeliveryVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("smSellDelivery.sql.getCalcCount",smSellDeliveryVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
	/**
	 * 根据ID获取对象SmSellDelivery
	 * @param ID
	 * @return SmSellDelivery
	 */
	public SmSellDelivery getSmSellDeliveryById(String id) {
		return dao.findById(id, SmSellDelivery.class);
	}	
	
	/**
	 * 添加对象SmSellDelivery
	 * @param 实体对象
	 */
	public void saveSmSellDelivery(SmSellDelivery smSellDelivery) {
		dao.save(smSellDelivery);
	}
	
	/**
     * 计算未结算
     * calc(描述这个方法的作用)<br/> 
     * void
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
	@Transactional(propagation=Propagation.REQUIRED)
	public void calc(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
//	    SmSellDeliveryVo smSellDeliveryVo = new SmSellDeliveryVo();
	    smSellDeliveryVo.setPagingFlag(false);
	    if(smSellDeliveryVo.getYm() == null || smSellDeliveryVo.getYm().equals("")){
	    	throw new Exception("年月为空");
	    }else{
	    	smSellDeliveryVo.getSmSellDelivery().setYm(smSellDeliveryVo.getYm());
	    }
	    if(smSellDeliveryVo.getStatus() != null && !smSellDeliveryVo.getStatus().equals("00")){
	    	Parameter.isFilterData.set(true);
	    	dao.deleteBySql("smSellDelivery.sql.deleteByYm", smSellDeliveryVo);
	    	Parameter.isFilterData.set(false);
	    }
	    List<SmSellDeliveryDetail> list = getCalcListByParams(smSellDeliveryVo);
	    
	    for (SmSellDeliveryDetail smSellDelivery : list) {
	    	String id = PlatformTools.getID();
	    	//计算参数
	    	BigDecimal tdValue = smSellDelivery.getdValue();//价差
	    	BigDecimal proxyPq = smSellDelivery.getProxyPq();//结算电量
	    	BigDecimal agrePrc = smSellDelivery.getAgrePrc();//合同电价
	    	BigDecimal transPrc = smSellDelivery.getTransPrc();//输配电价
	    	BigDecimal agrePq = smSellDelivery.getAgrePq();//合同电量
	    	BigDecimal bidPq = smSellDelivery.getBidPq();//成交电量
	    	
	    	
	    	//计算数据
	    	BigDecimal consCheckAwt = BigDecimal.ZERO;		//用户偏差考核电费
	    	BigDecimal consCheckPq = BigDecimal.ZERO;		//用户偏差考核电量
	    	BigDecimal consCheckPrc = BigDecimal.ZERO;		//用户偏差考核电价
	    	BigDecimal consElecProfit = BigDecimal.ZERO; 	//用户购电利润
	    	BigDecimal compensateAmt = BigDecimal.ZERO; 	//补偿用户电费
	    	BigDecimal consProfit = BigDecimal.ZERO;  		//用户返利
	    	BigDecimal compensatePq = BigDecimal.ZERO;		//赔偿用户电量
	    	BigDecimal compensatePrc = BigDecimal.ZERO;		//赔偿用户电价
	    	BigDecimal bidProxy = BigDecimal.ZERO;			//偏差电量
	    	if(smSellDelivery.getTimeFlag() != null && smSellDelivery.getTimeFlag().equals("01")){	//不分时
		    	//用户购电利润 = 价差 * 结算电量
		    	if(tdValue != null && proxyPq != null){
		    		consElecProfit = tdValue.multiply(proxyPq);
		    	}
	    	}else if(smSellDelivery.getTimeFlag() != null && smSellDelivery.getTimeFlag().equals("02")){ //分时
	    		BigDecimal cataPlainPrc = smSellDelivery.getCataPlainPrc();//目录平时电价
	    		BigDecimal cataPeakPrc = smSellDelivery.getCataPeakPrc();//目录峰时电价
	    		BigDecimal cataValleyPrc = smSellDelivery.getCataValleyPrc(); //目录谷时电价
	    		BigDecimal plainPrc = agrePrc.add(transPrc);//平时电价
	    		BigDecimal peakPrc = BigDecimal.ZERO;//峰时电价
	    		BigDecimal valleyPrc = BigDecimal.ZERO;//谷时电价
	    		BigDecimal peakPq = BigDecimal.ZERO;//峰时电量
	    		BigDecimal plainPq = BigDecimal.ZERO;//平时电量
	    		BigDecimal valleyPq = BigDecimal.ZERO;//谷时电量
	    		BigDecimal totalPq = smSellDelivery.getTotalPq();//总电量
	    		BigDecimal peakPqParam = smSellDelivery.getPeakPq();//峰时电量
	    		peakPqParam = peakPqParam == null ? BigDecimal.ZERO : peakPqParam;
	    		BigDecimal plainPqParam = smSellDelivery.getPlainPq();//平时电量
	    		plainPqParam = plainPqParam == null ? BigDecimal.ZERO : plainPqParam;
	    		BigDecimal valleyPqParam = smSellDelivery.getValleyPq();//谷时电量
	    		valleyPqParam = valleyPqParam == null ? BigDecimal.ZERO : valleyPqParam;
	    		if(totalPq != null && totalPq.compareTo(BigDecimal.ZERO) != 0){//计算峰、谷、平电量
	    			peakPq = proxyPq.multiply(peakPqParam.divide(totalPq,2,BigDecimal.ROUND_HALF_UP));
	    			plainPq = proxyPq.multiply(plainPqParam.divide(totalPq,2,BigDecimal.ROUND_HALF_UP));
	    			valleyPq = proxyPq.multiply(valleyPqParam.divide(totalPq,2,BigDecimal.ROUND_HALF_UP));
	    		}
	    		if(cataPlainPrc.compareTo(BigDecimal.ZERO) != 0){
	    			peakPrc = plainPrc.multiply(cataPeakPrc.divide(plainPrc,2,BigDecimal.ROUND_HALF_UP));
	    			valleyPrc = plainPrc.multiply(cataValleyPrc.divide(plainPrc,2,BigDecimal.ROUND_HALF_UP));
	    		}
	    		consElecProfit = cataPlainPrc.subtract(plainPrc).multiply(plainPq)
	    				.add(cataPeakPrc.subtract(peakPrc).multiply(peakPq))
	    				.add(cataValleyPrc.subtract(valleyPrc).multiply(valleyPq));
	    	}
	    	//赔偿
	    	if("1".equals(smSellDelivery.getCompPunishFlag())){//判断是否赔偿
	    		if(agrePq != null && bidPq != null){
		    		if(agrePq.compareTo(bidPq) > 0){ //合同 > 成交 才有赔偿
	    				// 赔偿电量 = 计算中间-有效电量*阈值
	    				compensatePq = agrePq.subtract(bidPq).subtract(agrePq.multiply(smSellDelivery.getCompLowerThreshold()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP));						
	    				if(compensatePq.compareTo(BigDecimal.ZERO) > 0){ //赔偿电量>0
	    					compensatePrc = smSellDelivery.getCompLowerPrc();
	    					//赔偿金额 = 赔偿电量 * 电价
	    					compensateAmt = compensatePq.multiply(compensatePrc);
	    				}else{
	    					compensatePq = BigDecimal.ZERO;
	    				}
		    		}
		    	}
	    	}
	    	
	    	if(bidPq != null && proxyPq != null){
	    		//偏差电量 = 结算-成交
	    		bidProxy = proxyPq.subtract(bidPq);
	    	}
	    	
	    	if(bidProxy.compareTo(BigDecimal.ZERO) > 0){ //正偏差
	    		if("1".equals(smSellDelivery.getUpperPunishType())){//惩罚
	    			if(smSellDelivery.getConsUpperType().equals("01")){ //人工录入
	    				consCheckPrc = smSellDelivery.getConsUpperPrc();
	    			}else if(smSellDelivery.getConsUpperType().equals("03")){ //电度电价
	    				//正偏差考核电价 = 电度电价 * 正偏差惩罚比例  / 100
	    				consCheckPrc = smSellDelivery.getKwhPlainPrc().multiply(smSellDelivery.getConsUpperProp()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
	    			}
	    			//正偏差考核电量 = （偏差电量 - （成交 * 正偏差阈值 / 100））* 园区折扣比 / 100
	    			consCheckPq = (bidProxy.subtract(bidPq.multiply(smSellDelivery.getConsUpperThreshold()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP))).multiply(smSellDelivery.getIzProp()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);				
	    			if(consCheckPq.compareTo(BigDecimal.ZERO) > 0){//考核
	    				//正偏差考核电费 = 考核电量 * 考核电价
		    			consCheckAwt = consCheckPq.multiply(consCheckPrc);
	    			}else{
	    				consCheckPq = BigDecimal.ZERO;
	    			}
		    	}
	    	}else if(bidProxy.compareTo(BigDecimal.ZERO) < 0){//负偏差
	    		if("1".equals(smSellDelivery.getLowerPunishType())){//惩罚
	    			if(smSellDelivery.getConsLowerType().equals("01")){ //人工录入
	    				consCheckPrc = smSellDelivery.getConsLowerPrc();
	    			}else if(smSellDelivery.getConsLowerType().equals("02")){//输配电价
	    				//负偏差考核电价 = 输配电价 * 负偏差惩罚比例 / 100
	    				consCheckPrc = transPrc.multiply(smSellDelivery.getConsLowerProp()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
	    			}
	    			//正偏差考核电量 = （偏差电量 - （成交 * 负偏差阈值 / 100））* 园区折扣比 / 100
	    			consCheckPq = (bidProxy.abs().subtract(bidPq.multiply(smSellDelivery.getConsLowerThreshold()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP))).multiply(smSellDelivery.getIzProp()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
	    			if(consCheckPq.compareTo(BigDecimal.ZERO) > 0){
	    				//负偏差考核电费 = 考核电量 * 考核电价
		    			consCheckAwt = consCheckPq.multiply(consCheckPrc);
	    			}else{
	    				consCheckPq = BigDecimal.ZERO;
	    			}
	    		}
	    	}
	    	if(consElecProfit != null && compensateAmt != null && consCheckAwt != null){
	    		consProfit = consElecProfit.subtract(consCheckAwt).add(compensateAmt); //用户利润 = 购电利润 - 考核 + 补偿
	    	}
	    	smSellDelivery.setId(id);
	    	smSellDelivery.setConsElecProfit(consElecProfit);
	    	smSellDelivery.setCompensateAmt(compensateAmt);
	    	smSellDelivery.setConsCheckAwt(consCheckAwt);
	    	smSellDelivery.setConsProfit(consProfit);
	    	smSellDelivery.setConsCheckPq(consCheckPq);
	    	smSellDelivery.setConsCheckPrc(consCheckPrc);
	    	smSellDelivery.setCompensatePq(compensatePq);
	    	smSellDelivery.setCompensatePrc(compensatePrc);
	    	smSellDelivery.setStatus("01");
		}
	    List<SmSellDelivery> domainList = getDomainBydetail(list);
	    if(domainList != null && domainList.size() > 0){
	    	saveSmSellDeliveryList(domainList);
	    }
	    
	}
	
	public List<SmSellDelivery> getDomainBydetail(List<SmSellDeliveryDetail> detailList){
		List<SmSellDelivery> domainList = new ArrayList<SmSellDelivery>();
		SmSellDelivery smSellDelivery = null;
		
		for (SmSellDeliveryDetail smSellDeliveryDetail : detailList) {
			smSellDelivery = new SmSellDelivery();
			smSellDelivery = ValueShiftUtil.valueToValue(smSellDeliveryDetail, smSellDelivery);
			domainList.add(smSellDelivery);
		}
		return domainList;
	}
	
	/**
	 * 添加对象SmSellDelivery
	 * @param 实体对象
	 */
	public void saveSmSellDeliveryList(List<SmSellDelivery> smSellDeliveryList) {
		dao.saveList(smSellDeliveryList);
	}
	
	/**
	 * 更新对象SmSellDelivery
	 * @param 实体对象
	 */
	public void updateSmSellDelivery(SmSellDelivery smSellDelivery) {
		dao.update(smSellDelivery);
	}
	
	/**
	 * 删除对象SmSellDelivery
	 * @param id数据组
	 */
	public void deleteSmSellDelivery(String[] ids) {
		dao.delete(ids, SmSellDelivery.class);
	}	
	
	/**
	 * 
	 * @Title: getPlanList<br/>
	 * @Description: 查询计划列表<br/>
	 * @param smSellDeliveryVo
	 * @return
	 * @throws Exception <br/>
	 * QueryResult<CalcDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月14日 下午9:52:04
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月14日 下午9:52:04
	 * @since  1.0.0
	 */
	public QueryResult<CalcDetail> getPlanList(SmSellDeliveryVo smSellDeliveryVo) throws Exception{
		QueryResult<CalcDetail> queryResult = new QueryResult<CalcDetail>();
		if(smSellDeliveryVo == null){
			smSellDeliveryVo = new SmSellDeliveryVo();
		}
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smSellDelivery.sql.getPlanCount",smSellDeliveryVo);
	    long total = result == null ? 0 : Long.valueOf(result.toString());
	    @SuppressWarnings("unchecked")
		List<CalcDetail> calcDetailList = (List<CalcDetail>) dao.getBySql("smSellDelivery.sql.getPlanList",smSellDeliveryVo);
	    Parameter.isFilterData.set(false);
	    if(calcDetailList != null && calcDetailList.size() > 0){	    	
	    	ConvertListUtil.convert(calcDetailList);
	    }
		queryResult.setTotal(total);
		queryResult.setData(calcDetailList);
		return queryResult;
	}
	
	/**
	 * 
	 * @Title: submitByYm<br/>
	 * @Description: 更新提交状态<br/>
	 * @param smSellDeliveryVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月15日 上午10:46:25
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月15日 上午10:46:25
	 * @since  1.0.0
	 */
	public void submitByYm(SmSellDeliveryVo smSellDeliveryVo){
		Parameter.isFilterData.set(true);
		dao.executeSql("smSellDelivery.sql.updateStatusByYm", smSellDeliveryVo);
		Parameter.isFilterData.set(false);
	}
}
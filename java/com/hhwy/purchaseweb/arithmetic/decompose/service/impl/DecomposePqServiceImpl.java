package com.hhwy.purchaseweb.arithmetic.decompose.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.ConsDecompose;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.DecomposeData;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.DecomposeResult;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.Deviation;
import com.hhwy.purchaseweb.arithmetic.decompose.domain.Rule;
import com.hhwy.purchaseweb.arithmetic.decompose.service.DecomposePqService;
import com.hhwy.purchaseweb.arithmetic.decompose.util.DecomposeComparator;

/**
 * 	
 * <b>类 名 称：</b>DecomposePqServiceImpl<br/>
 * <b>类 描 述：</b>山西交割电量分解公共方法<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月5日 下午7:45:15<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DecomposePqServiceImpl implements DecomposePqService {

	public static final Logger log = LoggerFactory.getLogger(DecomposePqServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	/**
	 * 交易类型：01普通
	 */
	private static final String DEALTYPE01 = "01";
	
	/**
	 * 交易类型：02长协
	 */
	private static final String DEALTYPE02 = "02";
	
	/**
	 * 分配原则：01按成交电量多到少
	 */
	private static final String PRINCIPLEFLAG01 = "01";
	
	/**
	 * 分配原则：02按成交电量少到多
	 */
	private static final String PRINCIPLEFLAG02 = "02";
	
	/**
	 * 长协优先
	 */
	private static final String PRIORITYFLAG01 = "01";
	
	/**
	 * 竞价优先
	 */
	private static final String PRIORITYFLAG02 = "02";
	
	/**
	 * 人工录入
	 */
	private static final String PRCTYPE01 = "01";
	
	/**
	 * 合同加权02
	 */
	private static final String PRCTYPE02 = "02";
	
	/**
	 * 目录电价03
	 */
	private static final String PRCTYPE03 = "03";
	
	/**
	 * 竞价电价04
	 */
	private static final String PRCTYPE04 = "04";
	
	/**
	 * 是否标识：1是
	 */
	private static final String YESFLAG = "1";
	
	/**
	 * 电量类型：01 分割电量
	 */
	private static final String PQTYPE01 = "01";
	
	/**
	 * 电量类型：02 正偏差电量
	 */
	private static final String PQTYPE02 = "02";
	
	/**
	 * 电量类型：03正偏差考核电量
	 */
	private static final String PQTYPE03 = "03";
	
	/**
	 * 电量类型：04 负偏差电量
	 */
	private static final String PQTYPE04 = "04";
	
	/**
	 * 电量类型：05 负偏差考核电量
	 */
	private static final String PQTYPE05 = "05";
	
	/**
	 * 营销侧电价类型：02 目录电价
	 */
	private static final String MPRCTYPE02 = "02";
	
	/**
	 * 营销侧电价类型：01其他电价
	 */
	private static final String MPRCTYPE01 = "01";
	
	/**
	 * 用户类型：零售用户
	 */
	private static final String CONS02 = "02";
	
	/**
	 * 用户类型：直购电用户
	 */
	private static final String CONS01 = "01";
	
	/**
	 * 电价时段：03平时
	 */
	private static final String PRCTSCODE = "03";
	
	public static void main(String[] agrs){
		DecomposePqServiceImpl decomposePqServiceImpl = new DecomposePqServiceImpl();
		List<DecomposeData> params = new ArrayList<DecomposeData>();
		DecomposeData dd = new DecomposeData();
		dd.setContractId("1");
		dd.setMarketUserNo("2");
		dd.setCompanyId("");
		dd.setDealType(DEALTYPE01);
		dd.setElePowerId("2");
		dd.setDeliveryPq(200);
		dd.setPq(60);
		dd.setPrc(1d);
		DecomposeData dd1 = new DecomposeData();
		dd1.setContractId("2");
		dd1.setMarketUserNo("2");
		dd1.setCompanyId("");
		dd1.setDealType(DEALTYPE01);
		dd1.setElePowerId("2");
		dd1.setDeliveryPq(200);
		dd1.setPq(60);
		dd1.setPrc(1d);
		DecomposeData dd2 = new DecomposeData();
		dd2.setContractId("2");
		dd2.setMarketUserNo("2");
		dd2.setCompanyId("");
		dd2.setDealType(DEALTYPE01);
		dd2.setElePowerId("2");
		dd2.setDeliveryPq(200);
		dd2.setPq(60);
		dd2.setPrc(1d);
		params.add(dd);
		params.add(dd1);
		params.add(dd2);
		try {
			List<DecomposeResult> list = decomposePqServiceImpl.getDecomposePq(params);
			System.out.println(list.size());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: getRule
	 * @Description: 获取电量分割系统配置项
	 * @return 
	 * Map<String,Object>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月11日 下午10:31:43
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月11日 下午10:31:43
	 * @since  1.0.0
	 */
	private Rule getRule() throws Exception {
		Rule rule = new Rule();
		rule.setUpperPrcType("03");//正偏差结算价类型
		rule.setUpperPrcProp(new BigDecimal(100));//正偏差结算价比例
		rule.setUpperPqProp(new BigDecimal(3));//正偏差阈值
		rule.setUpperCheckPrcType("03");//正偏差考核电价
		rule.setUpperCheckFlag(YESFLAG);
		rule.setUpperCheckPrcProp(new BigDecimal(100));//正偏差考核电价比例
		rule.setLowerCheckFlag(YESFLAG);
		rule.setLowerCheckBidFlag(YESFLAG);
		rule.setLowerBidPrcType("03");//普通交易负偏差结算价类型
		rule.setLowerCheckBidPqProp(new BigDecimal(0));
		rule.setLowerBidPrcProp(new BigDecimal(90));//普通交易负偏差结算价比例
		rule.setLowerCheckBidPqProp(new BigDecimal(3));//普通交易负偏差阈值
		rule.setLowerCheckBidPrcType("03");//普通交易负偏差考核电价类型
		rule.setLowerCheckBidPrcProp(new BigDecimal(100));//普通交易负偏差考核电价比例
		rule.setLowerCheckLcFlag(YESFLAG);
		rule.setLowerLcPrcType("03");//长协交易负偏差结算价类型
		rule.setLowerLcPrcProp(new BigDecimal(90));//长协交易负偏差结算价比例
		rule.setLowerCheckLcPqProp(new BigDecimal(3));//长协交易负偏差阈值
		rule.setLowerCheckLcPrcType("03");//长协交易负偏差考核电价类型
		rule.setLowerCheckLcPrcProp(new BigDecimal(100));//长协交易负偏差考核电价比例
		rule.setBidPriorityFlag("01");//竞价电量优先交割原则
		rule.setLcPriorityFlag("01");//长协电量优先交割原则
		rule.setPriorityFlag("01");//交割分割优先原则

		return rule;
	}
	
	/**
	 * 
	 * @Title: getDecomposePq
	 * @Description: 计算类别为购电成本进行电量分割
	 * voidl
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月5日 下午7:48:00
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月5日 下午7:48:00
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDecomposePq(List<DecomposeData> params) throws Exception {
		
		Rule rule = getRule();
		
		if(rule==null){
			System.out.println("/**************未配置规则信息**************/");
			return null;
		}
		
		List<DecomposeResult> result = new ArrayList<DecomposeResult>();
		
		Map<String,ConsDecompose> marketSortOne = new HashMap<String, ConsDecompose>();//直购电用户集合
		
		Map<String,ConsDecompose> marketSortTwo = new HashMap<String, ConsDecompose>();//零售用户集合
		  
		for(DecomposeData decomposeData : params){
			//循环解析传入参数，进行分割电量准备
			//从结果集中查看是否已存在用户数据,没有新增，有则增量更新
			String consId = decomposeData.getMarketUserNo();
			//用户分组
			ConsDecompose consDecompose = null; 
			if(StringUtils.isEmpty(decomposeData.getCompanyId())){
				//直购电用户
				consDecompose = marketSortOne.get(consId);
				if(consDecompose==null){
					consDecompose = new ConsDecompose();
					marketSortOne.put(consId, consDecompose);
					consDecompose.setBidPq(BigDecimal.ZERO);
					consDecompose.setLcPq(BigDecimal.ZERO);
					consDecompose.setAmt(BigDecimal.ZERO);
					consDecompose.setDecomposeBidList(new ArrayList<DecomposeData>());
					consDecompose.setDecomposeLcList(new ArrayList<DecomposeData>());
				}
			}else{
				//零售用户
				consDecompose = marketSortTwo.get(consId);
				if(consDecompose==null){
					consDecompose = new ConsDecompose();
					marketSortTwo.put(consId, consDecompose);
					consDecompose.setBidPq(BigDecimal.ZERO);
					consDecompose.setLcPq(BigDecimal.ZERO);
					consDecompose.setAmt(BigDecimal.ZERO);
					consDecompose.setDecomposeBidList(new ArrayList<DecomposeData>());
					consDecompose.setDecomposeLcList(new ArrayList<DecomposeData>());
				}
			}
			consDecompose.setConsId(consId);//用户id
			consDecompose.setDeliveryPq(new BigDecimal(decomposeData.getDeliveryPq()));//用户交割电量
			BigDecimal amt = BigDecimal.ZERO;
			if(DEALTYPE01.equals(decomposeData.getDealType())){
				//普通交易 
				BigDecimal bidPq = new BigDecimal(decomposeData.getPq());
				BigDecimal bidPrc = new BigDecimal(decomposeData.getPrc());
				amt = bidPq.multiply(bidPrc);
				consDecompose.setBidPq(consDecompose.getBidPq().add(bidPq)); 
				consDecompose.getDecomposeBidList().add(decomposeData);//增量更新用户对应参数集合
				consDecompose.setBidPrc(bidPrc);//竞价电价
			}else if(DEALTYPE02.equals(decomposeData.getDealType())){
				//长协交易
				BigDecimal lcPq = new BigDecimal(decomposeData.getPq());
				BigDecimal lcPrc = new BigDecimal(decomposeData.getPrc());
				amt = lcPq.multiply(lcPrc);
				consDecompose.setLcPq(consDecompose.getLcPq().add(lcPq)); 
				consDecompose.getDecomposeLcList().add(decomposeData);//增量更新用户对应参数集合
			}
			consDecompose.getAmt().add(amt);
		}
		Iterator<ConsDecompose> iteratorOne = marketSortOne.values().iterator();
		while(iteratorOne.hasNext()){
			//循环分割直购电用户
			List<DecomposeResult> list = getDecomposeResultForOne(iteratorOne.next(),rule);
			result.addAll(list);
		}
		Iterator<ConsDecompose> iteratorTwo = marketSortTwo.values().iterator();
		while(iteratorTwo.hasNext()){
			//循环分割零售用户
			List<DecomposeResult> list = getDecomposeResultForTwo(iteratorTwo.next(),rule);
			result.addAll(list);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getDecomposeResultForOne
	 * @Description: 直购电用户分割算法
	 * @param consDecompose 用户数据
	 * @param rule 分割规则
	 * @return 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月12日 下午10:31:53
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月12日 下午10:31:53
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDecomposeResultForOne(ConsDecompose consDecompose,Rule rule) throws Exception {

		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal bidPq = consDecompose.getBidPq();//用户普通总电量
		BigDecimal lcPq = consDecompose.getLcPq();//用户长协总电量
		BigDecimal totalPq = bidPq.add(lcPq);//用户交易总电量
		consDecompose.setAvgPrc(consDecompose.getAmt().divide(totalPq,6,BigDecimal.ROUND_HALF_UP));//合同加权电价
		BigDecimal deliveryPq = consDecompose.getDeliveryPq();//用户交割电量
		List<DecomposeData> bidList = consDecompose.getDecomposeBidList();//用户普通交易信息集合
		List<DecomposeData> lcList = consDecompose.getDecomposeLcList();//用户长协交易信息集合
		
		//处理分割结果
		List<DecomposeResult> resultList = new ArrayList<DecomposeResult>();
		BigDecimal deliveryBidPq = BigDecimal.ZERO;//普通交割电量
		BigDecimal deliveryLcPq = BigDecimal.ZERO;//长协交割电量
		String priorityFlag = rule.getPriorityFlag();//交割优先原则

		if(totalPq.compareTo(deliveryPq)<0){
			//正偏差
			deliveryBidPq = bidPq;//普通交割
			deliveryLcPq = lcPq;//长协交割
			//分割正偏差电量
			int deviationPq = deliveryPq.subtract(totalPq).intValue();
			
			//根据交易类型赋值计算交易电量集合及总值
			if(PRIORITYFLAG01.equals(priorityFlag)){
				//正偏差分割结果
				if(BigDecimal.ZERO.compareTo(deliveryBidPq)==0){
					deliveryBidPq = deliveryLcPq;
				}
				resultList.addAll(getBidUpperDeviation(consDecompose, rule, deliveryBidPq, deviationPq));

				if(YESFLAG.equals(rule.getUpperCheckFlag())){
					//正偏差考核
					int checkPq = deviationPq - bidPq.multiply(rule.getUpperPqProp())
							.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
					if(checkPq>0){
						resultList.addAll(getBidUpperCheckDeviation(consDecompose, rule, deliveryBidPq, checkPq));
					}
				}
			}else{
				//正偏差分割结果
				if(BigDecimal.ZERO.compareTo(deliveryLcPq)==0){
					deliveryLcPq = deliveryBidPq;
				}
				resultList.addAll(getLcUpperDeviation(consDecompose, rule, deliveryLcPq, deviationPq));

				if(YESFLAG.equals(rule.getUpperCheckFlag())){
					//正偏差考核
					int checkPq = deviationPq - lcPq.multiply(rule.getUpperPqProp())
							.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
					if(checkPq>0){
						resultList.addAll(getLcUpperCheckDeviation(consDecompose, rule, deliveryLcPq, checkPq));
					}
				}
			}
			
		}else if(totalPq.compareTo(deliveryPq)>0){
			if(PRIORITYFLAG01.equals(priorityFlag)){
				//长协优先，计算负偏差电量及偏差考核电量
				if(lcPq.compareTo(deliveryPq)>=0){
					deliveryLcPq = deliveryPq;//长协交割
					//分割长协负偏差电量
					int deviationLcPq = lcPq.subtract(deliveryPq).intValue();//长协偏差
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
					
					if(YESFLAG.equals(rule.getLowerCheckLcFlag())){
						//长协负偏差考核
						int checkPq = deviationLcPq - lcPq.multiply(rule.getLowerCheckLcPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							resultList.addAll(getLcLowerCheckDeviation(consDecompose, rule, deliveryLcPq, checkPq));
						}
					}
					
					//分割普通负偏差电量
					int deviationBidPq = bidPq.intValue();//竞价偏差
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
					
					if(YESFLAG.equals(rule.getLowerCheckBidFlag())){
						//普通负偏差考核
						int checkPq = deviationBidPq - bidPq.multiply(rule.getLowerCheckBidPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							//普通负偏差考核分割结果
							resultList.addAll(getBidLowerCheckDeviation(consDecompose, rule, deliveryBidPq, checkPq));
						}
					}
				}else{
					deliveryBidPq = deliveryPq.subtract(lcPq);//普通交割
					deliveryLcPq = lcPq;//长协交割
					
					//分割普通负偏差电量
					int deviationBidPq = totalPq.subtract(deliveryPq).intValue();
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
					
					if(YESFLAG.equals(rule.getLowerCheckBidFlag())){
						//普通负偏差考核
						int checkPq = deviationBidPq - bidPq.multiply(rule.getLowerCheckBidPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							//普通负偏差考核分割结果
							resultList.addAll(getBidLowerCheckDeviation(consDecompose, rule, deliveryBidPq, checkPq));
						}
					}
				}
			}else if(PRIORITYFLAG02.equals(priorityFlag)){
				//普通优先，计算负偏差电量及偏差考核电量
				if(bidPq.compareTo(deliveryPq)>=0){
					deliveryBidPq = deliveryPq;//普通交割
					//分割普通负偏差电量
					int deviationBidPq = bidPq.subtract(deliveryPq).intValue();//普通偏差
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
					
					if(YESFLAG.equals(rule.getLowerCheckBidFlag())){
						//长协负偏差考核
						int checkPq = deviationBidPq - bidPq.multiply(rule.getLowerCheckBidPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							//长协负偏差考核分割结果
							resultList.addAll(getBidLowerCheckDeviation(consDecompose, rule, deliveryBidPq, checkPq));
						}
					}

					//分割长协负偏差电量
					int deviationLcPq = lcPq.intValue();//竞价偏差
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
					
					if(YESFLAG.equals(rule.getLowerCheckLcFlag())){
						//长协负偏差考核
						int checkPq = deviationLcPq - lcPq.multiply(rule.getLowerCheckLcPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							resultList.addAll(getLcLowerCheckDeviation(consDecompose, rule, deliveryLcPq, checkPq));
						}
					}

				}else{
					deliveryLcPq = deliveryPq.subtract(bidPq);//长协交割
					deliveryBidPq = bidPq;//普通交割
					
					//分割长协负偏差电量
					int deviationLcPq = totalPq.subtract(deliveryPq).intValue();
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
					
					if(YESFLAG.equals(rule.getLowerCheckLcFlag())){
						//长协负偏差考核
						int checkPq = deviationLcPq - lcPq.multiply(rule.getLowerCheckLcPqProp())
								.divide(hundred,BigDecimal.ROUND_HALF_UP).intValue();
						if(checkPq>0){
							resultList.addAll(getLcLowerCheckDeviation(consDecompose, rule, deliveryLcPq, checkPq));
						}
					}
				}
			}
		}

		//分电厂
		//普通交割
		resultList.addAll(getDecomposeResult(bidList,bidPq,rule.getBidPriorityFlag(),PQTYPE01));
		//长协交割
		resultList.addAll(getDecomposeResult(lcList,lcPq,rule.getLcPriorityFlag(),PQTYPE01));
		
		return resultList;
	}
	
	/**
	 * 
	 * @Title: getDecomposeResultForTwo
	 * @Description: 零售用户分割算法
	 * @param consDecompose 用户数据
	 * @param rule 分割规则
	 * @return 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月12日 下午10:31:53
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月12日 下午10:31:53
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDecomposeResultForTwo(ConsDecompose consDecompose,Rule rule) throws Exception {

		BigDecimal bidPq = consDecompose.getBidPq();//用户普通总电量
		BigDecimal lcPq = consDecompose.getLcPq();//用户长协总电量
		BigDecimal totalPq = bidPq.add(lcPq);//用户交易总电量
		consDecompose.setAvgPrc(consDecompose.getAmt().divide(totalPq,6,BigDecimal.ROUND_HALF_UP));//合同加权电价
		BigDecimal deliveryPq = consDecompose.getDeliveryPq();//用户交割电量
		List<DecomposeData> bidList = consDecompose.getDecomposeBidList();//用户普通交易信息集合
		List<DecomposeData> lcList = consDecompose.getDecomposeLcList();//用户长协交易信息集合
		
		//处理分割结果
		List<DecomposeResult> resultList = new ArrayList<DecomposeResult>();
		BigDecimal deliveryBidPq = BigDecimal.ZERO;//普通交割电量
		BigDecimal deliveryLcPq = BigDecimal.ZERO;//长协交割电量
		String priorityFlag = rule.getPriorityFlag();//交割优先原则
		
		if(totalPq.compareTo(deliveryPq)<0){
			//正偏差
			deliveryBidPq = bidPq;//普通交割
			deliveryLcPq = lcPq;//长协交割
			//分割正偏差电量
			int deviationPq = deliveryPq.subtract(totalPq).intValue();
			
			//根据交易类型赋值计算交易电量集合及总值
			if(PRIORITYFLAG01.equals(priorityFlag)){
				//正偏差分割结果
				if(BigDecimal.ZERO.compareTo(deliveryBidPq)==0){
					deliveryBidPq = deliveryLcPq;
				}
				resultList.addAll(getBidUpperDeviation(consDecompose, rule, deliveryBidPq, deviationPq));
			}else{
				//正偏差分割结果
				if(BigDecimal.ZERO.compareTo(deliveryLcPq)==0){
					deliveryLcPq = deliveryBidPq;
				}
				resultList.addAll(getLcUpperDeviation(consDecompose, rule, deliveryLcPq, deviationPq));
			}
		}else if(totalPq.compareTo(deliveryPq)>0){
			if(PRIORITYFLAG01.equals(priorityFlag)){
				//长协优先，计算负偏差电量及偏差考核电量
				if(lcPq.compareTo(deliveryPq)>=0){
					deliveryLcPq = deliveryPq;//长协交割
					//分割长协负偏差电量
					int deviationLcPq = lcPq.subtract(deliveryPq).intValue();//长协偏差
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
					
					//分割普通负偏差电量
					int deviationBidPq = bidPq.intValue();//竞价偏差
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
				}else{
					deliveryBidPq = deliveryPq.subtract(lcPq);//普通交割
					deliveryLcPq = lcPq;//长协交割
					
					//分割普通负偏差电量
					int deviationBidPq = totalPq.subtract(deliveryPq).intValue();
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
				}
			}else if(PRIORITYFLAG02.equals(priorityFlag)){
				//普通优先，计算负偏差电量及偏差考核电量
				if(bidPq.compareTo(deliveryPq)>=0){
					deliveryBidPq = deliveryPq;//普通交割
					//分割普通负偏差电量
					int deviationBidPq = bidPq.subtract(deliveryPq).intValue();//普通偏差
					//普通负偏差分割结果
					resultList.addAll(getBidLowerDeviation(consDecompose, rule, deliveryBidPq, deviationBidPq));
					
					//分割长协负偏差电量
					int deviationLcPq = lcPq.intValue();//竞价偏差
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
				}else{
					deliveryLcPq = deliveryPq.subtract(bidPq);//长协交割
					deliveryBidPq = bidPq;//普通交割
					
					//分割长协负偏差电量
					int deviationLcPq = totalPq.subtract(deliveryPq).intValue();
					//长协负偏差分割结果
					resultList.addAll(getLcLowerDeviation(consDecompose, rule, deliveryLcPq, deviationLcPq));
				}
			}
		}

		//分电厂
		//普通交割
		resultList.addAll(getDecomposeResult(bidList,bidPq,rule.getBidPriorityFlag(),PQTYPE01));
		//长协交割
		resultList.addAll(getDecomposeResult(lcList,lcPq,rule.getLcPriorityFlag(),PQTYPE01));
		
		return resultList;
	}	
	
	/**
	 * 
	 * @Title: getBidUpperDeviation
	 * @Description: 长协优先正偏差电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:18:03
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:18:03
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getBidUpperDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getUpperPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getUpperPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeBidList().size()>0){
			deviation.setList(consDecompose.getDecomposeBidList());
		}else{
			deviation.setList(consDecompose.getDecomposeLcList());
		}
		deviation.setList(consDecompose.getDecomposeBidList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getBidPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE02);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getLcUpperDeviation
	 * @Description: 竞价优先偏差电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:18:36
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:18:36
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getLcUpperDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getUpperPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getUpperPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getUpperPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeLcList().size()>0){
			deviation.setList(consDecompose.getDecomposeLcList());
		}else{
			deviation.setList(consDecompose.getDecomposeBidList());
		}
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getLcPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE02);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getBidUpperCheckDeviation
	 * @Description: 长协优先正偏差考核电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:28:00
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:28:00
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getBidUpperCheckDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getUpperCheckPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getUpperCheckPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeBidList().size()>0){
			deviation.setList(consDecompose.getDecomposeBidList());
		}else{
			deviation.setList(consDecompose.getDecomposeLcList());
		}
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getBidPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE03);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getLcUpperCheckDeviation
	 * @Description: 竞价优先正偏差考核电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:30:06
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:30:06
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getLcUpperCheckDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getUpperCheckPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getUpperCheckPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getUpperCheckPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeLcList().size()>0){
			deviation.setList(consDecompose.getDecomposeLcList());
		}else{
			deviation.setList(consDecompose.getDecomposeBidList());
		}
		deviation.setList(consDecompose.getDecomposeLcList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getLcPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE03);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}	
	
	/**
	 * 
	 * @Title: getBidUpperDeviation
	 * @Description: 长协优先负偏差电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:18:03
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:18:03
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getBidLowerDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getLowerBidPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getLowerBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getLowerBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getLowerBidPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getLowerBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeBidList().size()>0){
			deviation.setList(consDecompose.getDecomposeBidList());
		}else{
			deviation.setList(consDecompose.getDecomposeLcList());
		}
		deviation.setList(consDecompose.getDecomposeBidList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getBidPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE04);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getLcUpperDeviation
	 * @Description: 竞价优先负偏差电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:18:36
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:18:36
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getLcLowerDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getLowerLcPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getLowerLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getLowerLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getLowerLcPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getLowerLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeLcList().size()>0){
			deviation.setList(consDecompose.getDecomposeLcList());
		}else{
			deviation.setList(consDecompose.getDecomposeBidList());
		}
		deviation.setList(consDecompose.getDecomposeLcList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getLcPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE04);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getBidUpperCheckDeviation
	 * @Description: 长协优先负偏差考核电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:28:00
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:28:00
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getBidLowerCheckDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getLowerCheckBidPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getLowerCheckBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getLowerCheckBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getLowerCheckBidPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getLowerCheckBidPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeBidList().size()>0){
			deviation.setList(consDecompose.getDecomposeBidList());
			deviation.setPqProp(rule.getLowerCheckBidPqProp());//普通交易负偏差阈值
		}else{
			deviation.setList(consDecompose.getDecomposeLcList());
			deviation.setPqProp(rule.getLowerCheckLcPqProp());//长协交易负偏差阈值
		}
		deviation.setList(consDecompose.getDecomposeBidList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getBidPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE05);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getLcUpperCheckDeviation
	 * @Description: 竞价优先负偏差考核电量分割
	 * @param consDecompose
	 * @param rule
	 * @param deliveryPq
	 * @param deviationPq
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月21日 下午12:30:06
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月21日 下午12:30:06
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getLcLowerCheckDeviation(ConsDecompose consDecompose,
			Rule rule,BigDecimal deliveryPq,int deviationPq) throws Exception{
		//电价类型01电价值02目录电价
		String prcType = rule.getLowerCheckLcPrcType();
		String typeCode = prcType.equals(PRCTYPE03)?MPRCTYPE02:MPRCTYPE01;
		BigDecimal prcValue = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal(100);
		//电价处理默认为目录电价比例
		if(PRCTYPE02.equals(prcType)){
			BigDecimal prcProp = rule.getLowerCheckLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getAvgPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE04.equals(prcType)){
			BigDecimal prcProp = rule.getLowerCheckLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
			prcValue = consDecompose.getBidPrc().multiply(prcProp).setScale(6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE01.equals(prcType)){
			prcValue = rule.getLowerCheckLcPrc().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
		}else if(PRCTYPE03.equals(prcType)){
			prcValue = rule.getLowerCheckLcPrcProp().divide(hundred,6,BigDecimal.ROUND_HALF_UP);
		}

		Deviation deviation = new Deviation();
		if(consDecompose.getDecomposeLcList().size()>0){
			deviation.setList(consDecompose.getDecomposeLcList());
		}else{
			deviation.setList(consDecompose.getDecomposeBidList());
		}
		deviation.setList(consDecompose.getDecomposeLcList());
		deviation.setTotalPq(deliveryPq);
		deviation.setPrincipleFlag(rule.getLcPriorityFlag());//交易先后顺序
		deviation.setPqTypeCode(PQTYPE05);//电量类型
		deviation.setPq(deviationPq);//偏差电量
		deviation.setPrcType(typeCode);//计算电价类型
		deviation.setPrcValue(prcValue);//计算电价值
		
		//用户分割结果
		return getDeviationDecomposeResult(deviation);
		
	}
	
	/**
	 * 
	 * @Title: getDeviationDecomposeResult
	 * @Description: 获取偏差电量分割结果及考核结果方法
	 * @param deviation 偏差及考核计算参数实体
	 * @return 
	 * DecomposeResult
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月12日 下午11:43:58
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月12日 下午11:43:58
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDeviationDecomposeResult(Deviation deviation) throws Exception {
		List<DecomposeResult> resultList = new ArrayList<DecomposeResult>();
		List<DecomposeData> list = deviation.getList();
		if(list==null||list.isEmpty()){
			return resultList;
		}
		//对集合排序，优先交割靠前电量
		DecomposeComparator dc = new DecomposeComparator();
		if(PRINCIPLEFLAG01.equals(deviation.getPrincipleFlag())){
			//降序
			Collections.sort(list,dc);
			Collections.reverse(list);
		}else if(PRINCIPLEFLAG02.equals(deviation.getPrincipleFlag())){
			//升序
			Collections.sort(list,dc);
		}
		BigDecimal pq = new BigDecimal(deviation.getPq());//参与计算总电量（分配电量）
		BigDecimal totalPq = deviation.getTotalPq();//分配交割电量
		for(DecomposeData data:list){
			DecomposeResult result = new DecomposeResult();
			result.setMarketUserNo(data.getMarketUserNo());//用户标识
			result.setElePowerId(data.getElePowerId());//电厂标识
			result.setContractId(data.getContractId());//合同标识
			result.setContractPrc(data.getContractPrc());//合同电价
			if(StringUtils.isEmpty(data.getCompanyId())){
				result.setMarketSortCode(CONS01);//直购电用户
			}else{
				result.setMarketSortCode(CONS02);//零售用户
			}
			result.setDealType(data.getDealType());//交易类型
			result.setPqTypeCode(deviation.getPqTypeCode());//电量类型
			result.setPrcTsCode(PRCTSCODE);//电价时段
			result.setTypeCode(deviation.getPrcType());//电价类型
			result.setValue(deviation.getPrcValue().doubleValue());//电价值
			
			//根据电量类型分配计算总电量到对应合同
			if(PQTYPE02.equals(deviation.getPqTypeCode())||PQTYPE03.equals(deviation.getPqTypeCode())){
				//正偏差及考核
				if(list.indexOf(data)==(list.size()-1)){
					result.setDivPq(pq.intValue());
				}else{
					//分割比例
					BigDecimal divPq = new BigDecimal(data.getPq()).multiply(pq)
							.divide(deviation.getTotalPq(),2,BigDecimal.ROUND_HALF_UP);
					result.setDivPq(divPq.intValue());
					pq = pq.subtract(divPq);
				}
			}else if(PQTYPE04.equals(deviation.getPqTypeCode())){
				//负偏差
				BigDecimal dealPq = new BigDecimal(data.getPq());
				if(totalPq.compareTo(dealPq)>0){
					totalPq=totalPq.subtract(dealPq);
					continue;
				}else if(totalPq.compareTo(BigDecimal.ZERO)>0){
					BigDecimal divPq = dealPq.subtract(totalPq);
					if(pq.compareTo(divPq)>0){
						pq = pq.subtract(divPq);
						result.setDivPq(divPq.intValue());
					}else if(pq.compareTo(BigDecimal.ZERO)>0){
						result.setDivPq(pq.intValue());
						pq = BigDecimal.ZERO;
					}else{
						continue;
					}
					totalPq=BigDecimal.ZERO;
				}else{
					result.setDivPq(dealPq.intValue());
					if(pq.compareTo(dealPq)>0){
						pq = pq.subtract(dealPq);
						result.setDivPq(dealPq.intValue());
					}else if(pq.compareTo(BigDecimal.ZERO)>0){
						result.setDivPq(pq.intValue());
						pq = BigDecimal.ZERO;
					}else{
						continue;
					}
				}
			}else if(PQTYPE05.equals(deviation.getPqTypeCode())){

				//负偏差考核
				BigDecimal pqProp = deviation.getPqProp().divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP);
				BigDecimal dealPq = new BigDecimal(data.getPq());//合同电量
				BigDecimal checkPq = dealPq.multiply(pqProp);
				if(totalPq.compareTo(dealPq)>0){
					totalPq=totalPq.subtract(dealPq);
					continue;
				}else if(totalPq.compareTo(BigDecimal.ZERO)>0){
					BigDecimal divPq = dealPq.subtract(totalPq);
					if(divPq.compareTo(checkPq)>0){
						//考核
						divPq = divPq.subtract(checkPq);
						if(pq.compareTo(divPq)>0){
							pq = pq.subtract(divPq);
							result.setDivPq(divPq.intValue());
						}else if(pq.compareTo(BigDecimal.ZERO)>0){
							result.setDivPq(pq.intValue());
							pq = BigDecimal.ZERO;
						}else{
							continue;
						}
					}else{
						//未达到考核电量
						continue;
					}
					totalPq=BigDecimal.ZERO;
				}else{
					//交易电量全偏差
					if(dealPq.compareTo(checkPq)>0){
						//考核
						BigDecimal devPq = dealPq.subtract(checkPq);
						if(pq.compareTo(devPq)>0){
							pq = pq.subtract(devPq);
							result.setDivPq(devPq.intValue());
						}else if(pq.compareTo(BigDecimal.ZERO)>0){
							result.setDivPq(pq.intValue());
							pq = BigDecimal.ZERO;
						}else{
							continue;
						}
					}else{
						//未达到考核电量
						continue;
					}
				}
			}
			
			resultList.add(result);
		}
		
		return resultList;
	}
	
	/**
	 * 
	 * @Title: getDecomposeResult
	 * @Description: 用户-电厂匹配分割结果
	 * @param list 用户-电厂成交信息
	 * @param prcType 计算电价类型
	 * @param prcValue 计算电价值
	 * @param pq 参与计算总电量（未区分电厂）
	 * @param principleFlag 电量排序规则
	 * @param pqTypeCode 电量类型
	 * @return
	 * @throws Exception 
	 * List<DecomposeResult>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月18日 下午10:32:21
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月18日 下午7:59:12
	 * @since  1.0.0
	 */
	public List<DecomposeResult> getDecomposeResult(List<DecomposeData> list,
			BigDecimal pq,String principleFlag,String pqTypeCode) throws Exception {
		List<DecomposeResult> resultList = new ArrayList<DecomposeResult>();
		if(list==null||list.isEmpty()){
			return resultList;
		}
		//对集合排序，优先交割靠前电量
		DecomposeComparator dc = new DecomposeComparator();
		if(PRINCIPLEFLAG01.equals(principleFlag)){
			//降序
			Collections.sort(list,dc);
			Collections.reverse(list);
		}else if(PRINCIPLEFLAG02.equals(principleFlag)){
			//升序
			Collections.sort(list,dc);
		}
		
		for(DecomposeData data:list){
			DecomposeResult result = new DecomposeResult();
			result.setMarketUserNo(data.getMarketUserNo());//用户标识
			result.setElePowerId(data.getElePowerId());//电厂标识
			result.setContractId(data.getContractId());//合同标识
			result.setContractPrc(data.getContractPrc());//合同电价
			if(StringUtils.isEmpty(data.getCompanyId())){
				result.setMarketSortCode(CONS01);//直购电用户
			}else{
				result.setMarketSortCode(CONS02);//零售用户
			}
			result.setDealType(data.getDealType());//交易类型
			result.setPqTypeCode(pqTypeCode);//电量类型
			result.setPrcTsCode(PRCTSCODE);//电价时段
			result.setTypeCode(MPRCTYPE01);//电价类型
			result.setValue(data.getPrc());//电价值
			BigDecimal divPq = new BigDecimal(data.getPq());//本条记录交易电量
			if(pq.compareTo(divPq)>0){
				//当前分配电量大于用户本条交易电量
				result.setDivPq(divPq.intValue());
				pq=pq.subtract(divPq);
			}else if(pq.compareTo(BigDecimal.ZERO)>0){
				result.setDivPq(pq.intValue());
				pq=BigDecimal.ZERO;
			}else{
				result.setDivPq(0);
			}
			
			resultList.add(result);
		}
		
		return resultList;
	}
	
}

package com.hhwy.purchaseweb.arithmetic.balance.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hhwy.business.core.groovyscript.GroovyFinalResult;
import com.hhwy.business.core.groovyscript.GroovyUtil;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.mainframe.utils.SystemConfigUtil;
import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.arithmetic.balance.domain.ConsDealProfitResult;
import com.hhwy.purchaseweb.arithmetic.balance.service.BalanceService;
import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.common.service.CommonService;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.CompensateRule;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationObject;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationResult;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationRule;
import com.hhwy.purchaseweb.arithmetic.deviation.service.DeviationService;
//import com.hhwy.purchaseweb.arithmetic.deviation.service.impl.DValueDeviationServiceImpl;
//import com.hhwy.purchaseweb.arithmetic.divide.service.impl.ConsDValueDivideServiceImpl;
import com.hhwy.purchaseweb.arithmetic.divide.domain.DivideObject;
import com.hhwy.purchaseweb.arithmetic.divide.service.DivideService;
import com.hhwy.purchaseweb.arithmetic.util.ArithmentUtil;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.contants.RuleConfigureContants;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureVo;

@Service("DValueBalanceService")
public class DValueBalanceServiceImpl implements BalanceService {
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier("DValueDeviationService")
	private DeviationService deviationService;
	
	@Autowired
	@Qualifier("ConsDValueDivideService")
	private DivideService divideService;
	
	@Autowired @Qualifier("cloudSellingCommonService")
	private CommonService commonService;
	
//	public static void main(String[] args){
//		DValueBalanceServiceImpl balanceService = new DValueBalanceServiceImpl();
//		try {
//			SmRuleConfigure ruleObj = balanceService.getRule();
//			AmtScale amtScale = new AmtScale();
//			amtScale.setPqScale(new BigDecimal(10000));
//			amtScale.setPrcScale(new BigDecimal(0.001));
//			amtScale.setAmtScale(new BigDecimal(10));
//			BigDecimal compDealProfit = balanceService.getCompDealProfit(ruleObj,new BigDecimal(50000),new BigDecimal(50000), 
//					new BigDecimal(1015), BigDecimal.ZERO, BigDecimal.ZERO, "2017", amtScale);
//			DeviationObject deviationObj = new DeviationObject();
//			deviationObj.setBidPq(new BigDecimal(50000));
//			deviationObj.setBidPrc(new BigDecimal(1015));
//			deviationObj.setDeliveryPq(new BigDecimal(50000));
//			deviationObj.setReportPq(new BigDecimal(50000));
//			DeviationResult deviationResult = balanceService.getCompDeviationProfit(ruleObj, deviationObj, amtScale);
//			System.out.println(compDealProfit);
//			System.out.println(deviationResult.getDeviationAmt());
//			System.out.println(compDealProfit.subtract(deviationResult.getDeviationAmt()));
//			List<DivideObject> list = new ArrayList<DivideObject>();
//			DivideObject divideObject1 = new DivideObject();
//			divideObject1.setConsId("1");
//			divideObject1.setDiviCode(BusinessContants.DIVIDEWAYCODE05);
//			divideObject1.setDiscountUpperFlag(BusinessContants.NOFLAG);
//			divideObject1.setDiscountLowerFlag(BusinessContants.NOFLAG);
//			divideObject1.setDealBidPq(new BigDecimal(10000));
//			divideObject1.setPartyABidProp(new BigDecimal(80));
//			divideObject1.setAgrePrc(new BigDecimal(350));
//			divideObject1.setBidPq(new BigDecimal(10000));
//			divideObject1.setBidPrc(new BigDecimal(1015));
//			divideObject1.setDeliveryPq(new BigDecimal(10000));
//			divideObject1.setReportPq(new BigDecimal(10000));
//			list.add(divideObject1);
//			DivideObject divideObject2 = new DivideObject();
//			divideObject2.setConsId("2");
//			divideObject2.setDiviCode(BusinessContants.DIVIDEWAYCODE05);
//			divideObject2.setDiscountUpperFlag(BusinessContants.NOFLAG);
//			divideObject2.setDiscountLowerFlag(BusinessContants.NOFLAG);
//			divideObject2.setDealBidPq(new BigDecimal(10000));
//			divideObject2.setPartyABidProp(new BigDecimal(80));
//			divideObject2.setAgrePrc(new BigDecimal(350));
//			divideObject2.setBidPq(new BigDecimal(10000));
//			divideObject2.setBidPrc(new BigDecimal(1015));
//			divideObject2.setDeliveryPq(new BigDecimal(10500));
//			divideObject2.setReportPq(new BigDecimal(10000));
//			list.add(divideObject2);
//			DivideObject divideObject3 = new DivideObject();
//			divideObject3.setConsId("3");
//			divideObject3.setDiviCode(BusinessContants.DIVIDEWAYCODE05);
//			divideObject3.setDiscountUpperFlag(BusinessContants.NOFLAG);
//			divideObject3.setDiscountLowerFlag(BusinessContants.NOFLAG);
//			divideObject3.setDealBidPq(new BigDecimal(10000));
//			divideObject3.setPartyABidProp(new BigDecimal(80));
//			divideObject3.setAgrePrc(new BigDecimal(350));
//			divideObject3.setBidPq(new BigDecimal(10000));
//			divideObject3.setBidPrc(new BigDecimal(1015));
//			divideObject3.setDeliveryPq(new BigDecimal(9500));
//			divideObject3.setReportPq(new BigDecimal(10000));
//			list.add(divideObject3);
//			DivideObject divideObject4 = new DivideObject();
//			divideObject4.setConsId("4");
//			divideObject4.setDiviCode(BusinessContants.DIVIDEWAYCODE05);
//			divideObject4.setDiscountUpperFlag(BusinessContants.NOFLAG);
//			divideObject4.setDiscountLowerFlag(BusinessContants.NOFLAG);
//			divideObject4.setDealBidPq(new BigDecimal(10000));
//			divideObject4.setPartyABidProp(new BigDecimal(80));
//			divideObject4.setAgrePrc(new BigDecimal(350));
//			divideObject4.setBidPq(new BigDecimal(10000));
//			divideObject4.setBidPrc(new BigDecimal(1015));
//			divideObject4.setDeliveryPq(new BigDecimal(11000));
//			divideObject4.setReportPq(new BigDecimal(10000));
//			list.add(divideObject4);
//			DivideObject divideObject5 = new DivideObject();
//			divideObject5.setConsId("5");
//			divideObject5.setDiviCode(BusinessContants.DIVIDEWAYCODE05);
//			divideObject5.setDiscountUpperFlag(BusinessContants.NOFLAG);
//			divideObject5.setDiscountLowerFlag(BusinessContants.NOFLAG);
//			divideObject5.setDealBidPq(new BigDecimal(10000));
//			divideObject5.setPartyABidProp(new BigDecimal(80));
//			divideObject5.setAgrePrc(new BigDecimal(350));
//			divideObject5.setBidPq(new BigDecimal(10000));
//			divideObject5.setBidPrc(new BigDecimal(1015));
//			divideObject5.setDeliveryPq(new BigDecimal(9000));
//			divideObject5.setReportPq(new BigDecimal(10000));
//			list.add(divideObject5);
//			DeviationRule rule = new DeviationRule();
//			rule.setUpperCheckFlag(BusinessContants.NOFLAG);
//			rule.setLowerCheckFlag(BusinessContants.NOFLAG);
//			rule.setUpperPqProp(new BigDecimal(2));
//			BigDecimal totalCompensate = BigDecimal.ZERO;
//			BigDecimal totalConsProfit = BigDecimal.ZERO;
//			BigDecimal totalConsPunish = BigDecimal.ZERO;
//			CompensateRule compensateRule = new CompensateRule();
//			compensateRule.setCompFlag(BusinessContants.NOFLAG);
//			for(DivideObject divideObject:list){
//				ConsDealProfitResult consDealProfit = balanceService.getConsDealProfit(rule, divideObject, amtScale, compensateRule);
//				BigDecimal consCompensate = consDealProfit.getConsCompensate();
//				BigDecimal consProfit = consDealProfit.getConsProfit();
//				BigDecimal consPunish = consDealProfit.getConsPunish();
//				
//				System.out.println(divideObject.getConsId()+"      :      "+consCompensate);
//				totalCompensate = totalCompensate.add(consCompensate);
//				System.out.println(divideObject.getConsId()+"      :      "+consProfit);
//				totalConsProfit = totalConsProfit.add(consProfit);
//				System.out.println(divideObject.getConsId()+"      :      "+consPunish);
//				totalConsPunish = totalConsPunish.add(consPunish);
//			}
//			
//			BigDecimal compProfit = compDealProfit.subtract(totalConsProfit).subtract(totalCompensate)
//					.add(totalConsPunish).subtract(deviationResult.getDeviationAmt());
//			System.out.println(compProfit);
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		
//	}
	
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
//	private SmRuleConfigure getRule() throws Exception {
//		SmRuleConfigure rule = new SmRuleConfigure();
//		rule.setUpperPrcType("03");//正偏差结算价类型
//		rule.setUpperPrcProp(new BigDecimal(100));//正偏差结算价比例
//		rule.setUpperPqProp(new BigDecimal(2));//正偏差阈值
//		rule.setUpperCheckPrcType("04");//正偏差考核电价
//		rule.setUpperCheckFlag("1");
//		rule.setUpperCheckPrcProp(new BigDecimal(100));//正偏差考核电价比例
//		rule.setLowerCheckPrcType("04");
//		rule.setLowerCheckFlag("1");
//		rule.setLowerPqProp(new BigDecimal(2));
//		rule.setLowerCheckPrcProp(new BigDecimal(200));
//		rule.setBidPriorityFlag("01");//竞价电量优先交割原则
//		rule.setLcPriorityFlag("01");//长协电量优先交割原则
//		rule.setPriorityFlag("01");//交割分割优先原则
//
//		return rule;
//	}
	
	/**
	 * 售电公司交易总收益
	 */
	@Override
	public BigDecimal getCompDealProfit(SmRuleConfigure ruleObj,BigDecimal deliveryPq,BigDecimal bidPq,BigDecimal bidPrc,
			BigDecimal lcPq,BigDecimal lcPrc,String year,AmtScale amtScale) throws Exception{
		
		SmCalcConfigureDetail smCalcConfigureDetail = getSmCalcConfigureDetail();
		
		if(ruleObj == null){
			ruleObj = new SmRuleConfigure();
		}
		
		String json = smCalcConfigureDetail.getCalcParam();
		if(json != null && !json.equals("")){
			if(json.indexOf("upper") != -1 || json.indexOf("Upper") != -1){
				ruleObj.setUpperCheckFlag(BusinessContants.YESFLAG);
				if(json.indexOf("upperThreshold") != -1){
					Map<String, Object> jsonMap = JSON.parseObject(json);
					ruleObj.setUpperPqProp(new BigDecimal(jsonMap.get("upperThreshold").toString()));
				}
			}else{
				ruleObj.setUpperCheckFlag(BusinessContants.NOFLAG);
			}
			
		}else{
			ruleObj.setUpperCheckFlag(BusinessContants.NOFLAG);
		}
		
		String dealPriority = BusinessContants.PRIORITYFLAG02;
		BigDecimal lcProfit = BigDecimal.ZERO;
		BigDecimal bidProfit = BigDecimal.ZERO;
		if(lcPq == null){
			lcPq = BigDecimal.ZERO;
		}
		if(bidPq == null){
			bidPq = BigDecimal.ZERO;
		}
		if(bidPrc == null){
			bidPrc = BigDecimal.ZERO;
		}
		
		BigDecimal dealPq = bidPq.add(lcPq);//总交易电量
		BigDecimal upperProp = BigDecimal.ZERO;//正偏差阈值
		if(BusinessContants.YESFLAG.equals(ruleObj.getUpperCheckFlag())&&ruleObj.getUpperPqProp()!=null){
			upperProp = ruleObj.getUpperPqProp();
		}
		BigDecimal effectivePq = dealPq.multiply(upperProp).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).add(dealPq);//有效电量
		BigDecimal dealBidPq = BigDecimal.ZERO;//竞价计算电量
		BigDecimal dealLcPq = BigDecimal.ZERO;//长协计算电量
		if(deliveryPq.compareTo(effectivePq)==0){
			if(lcPrc == null){
				Map<String,Object> map = commonService.getLcAgrePqAndPrc(year);
				if(map != null && map.get("lcAgrePq") != null){
					BigDecimal lcAgrePq = new BigDecimal(map.get("lcAgrePq").toString());
					BigDecimal lcAgreAmt = map.get("lcAgreAmt") == null?
							BigDecimal.ZERO:new BigDecimal(map.get("lcAgreAmt").toString());
					lcProfit = lcPq.multiply(lcAgreAmt).divide(lcAgrePq,2,BigDecimal.ROUND_HALF_UP);
				}
			}else{
				lcProfit = lcPq.multiply(lcPrc).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			bidProfit = bidPq.multiply(bidPrc);
		}else if(deliveryPq.compareTo(effectivePq)>0){
			if(BusinessContants.PRIORITYFLAG01.equals(dealPriority)){
				dealLcPq = lcPq;
				dealBidPq = effectivePq.subtract(dealLcPq);
			}else if(BusinessContants.PRIORITYFLAG02.equals(dealPriority)){
				dealBidPq = bidPq;
				dealLcPq = effectivePq.subtract(dealBidPq);
			}else if(BusinessContants.PRIORITYFLAG03.equals(dealPriority)){
				dealBidPq = dealBidPq.multiply(effectivePq).divide(dealPq,2,BigDecimal.ROUND_HALF_UP);
				dealLcPq = effectivePq.subtract(dealBidPq);
			}
			if(lcPrc == null){
				Map<String,Object> map = commonService.getLcAgrePqAndPrc(year);
				if(map!= null && map.get("lcAgrePq") != null){
					BigDecimal lcAgrePq = new BigDecimal(map.get("lcAgrePq").toString());
					BigDecimal lcAgreAmt = map.get("lcAgreAmt") == null?
							BigDecimal.ZERO:new BigDecimal(map.get("lcAgreAmt").toString());
					lcProfit = dealLcPq.multiply(lcAgreAmt).divide(lcAgrePq,2,BigDecimal.ROUND_HALF_UP);
				}
			}else{
				lcProfit = dealLcPq.multiply(lcPrc).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			bidProfit = dealBidPq.multiply(bidPrc).setScale(2,BigDecimal.ROUND_HALF_UP);
		}else{
			if(BusinessContants.PRIORITYFLAG01.equals(dealPriority)){
				if(deliveryPq.compareTo(lcPq)>0){
					dealLcPq = lcPq;
					dealBidPq = deliveryPq.subtract(dealLcPq);
				}else{
					dealLcPq = deliveryPq;
				}
			}else if(BusinessContants.PRIORITYFLAG02.equals(dealPriority)){
				if(deliveryPq.compareTo(bidPq)>0){
					dealBidPq = bidPq;
					dealLcPq = deliveryPq.subtract(dealBidPq);
				}else{
					dealBidPq = deliveryPq;
				}
			}else if(BusinessContants.PRIORITYFLAG03.equals(dealPriority)){
				dealBidPq = dealBidPq.multiply(deliveryPq).divide(dealPq,2,BigDecimal.ROUND_HALF_UP);
				dealLcPq = deliveryPq.subtract(dealBidPq);
			}
			if(lcPrc == null){
				Map<String,Object> map = commonService.getLcAgrePqAndPrc(year);
				if(map != null && map.get("lcAgrePq") != null){
					BigDecimal lcAgrePq = new BigDecimal(map.get("lcAgrePq").toString());
					BigDecimal lcAgreAmt = map.get("lcAgreAmt") == null?
							BigDecimal.ZERO:new BigDecimal(map.get("lcAgreAmt").toString());
					lcProfit = dealLcPq.multiply(lcAgreAmt).divide(lcAgrePq,2,BigDecimal.ROUND_HALF_UP);
				}
			}else{
				lcProfit = dealLcPq.multiply(lcPrc).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			bidProfit = dealBidPq.multiply(bidPrc).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		BigDecimal profit = lcProfit.add(bidProfit).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);
		return profit;
	} 
	
	/**
	 * @Title: getSmCalcConfigureDetail<br/>
	 * @Description: 查询规则配置<br/>
	 * @return <br/>
	 * SmCalcConfigureDetail<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月23日 上午10:38:04
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月23日 上午10:38:04
	 * @throws Exception 
	 * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public SmCalcConfigureDetail getSmCalcConfigureDetail() throws Exception{
		
		String province = SystemConfigUtil.getConfig("cloudselling.province");
		
		SmCalcConfigureVo smCalcConfigureVo = new SmCalcConfigureVo();
		smCalcConfigureVo.getSmCalcConfigure().setStatus(RuleConfigureContants.CONTRACT_RULE_EFFECT);
		smCalcConfigureVo.getSmCalcConfigure().setProvince(province);
		
		List<SmCalcConfigureDetail> smCalcConfigureList = (List<SmCalcConfigureDetail>)dao.getBySql("smCalcConfigure.sql.getSmCalcConfigureListByParams", smCalcConfigureVo);
		if(smCalcConfigureList == null || smCalcConfigureList.size() == 0){
			throw new BusinessException("生效规则为空，请配置规则！");
		}
		return smCalcConfigureList.get(0);
	}
	
	/**
	 * 售电公司交易偏差考核计算
	 */
	@Override
	public DeviationResult getCompDeviationProfit(SmRuleConfigure ruleObj,
			DeviationObject deviationObj,AmtScale amtScale) throws Exception{
//		amtScale = new AmtScale();
//		DeviationService deviationServiceImpl = new DValueDeviationServiceImpl();
//		DeviationRule rule = new DeviationRule();
//		ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(ruleObj),rule);
//		return deviationService.getDeviation(rule, deviationObj, amtScale);
//		return deviationServiceImpl.getDeviation(rule, deviationObj, amtScale);
		
		Map<String, Object> params = new HashMap<String, Object>();
		boolean flag = false;
		
		SmCalcConfigureDetail smCalcConfigureDetail = getSmCalcConfigureDetail();
		if(smCalcConfigureDetail != null){
			String json = smCalcConfigureDetail.getCalcParam();
			if(json != null && !json.equals("")){
				Map<String, Object> deviationObjMap = null;
				deviationObjMap = ArithmentUtil.transBeanToMap(deviationObj);
				Map<String, Object> jsonMap = JSON.parseObject(json);
				
				for (String obj : deviationObjMap.keySet()) {
					if(jsonMap.containsKey(obj)){
						if(deviationObjMap.get(obj) != null && !deviationObjMap.get(obj).toString().equals("")){
							jsonMap.put(obj, deviationObjMap.get(obj));
						}
					}
				}
				jsonMap.put("agrePq", (deviationObj.getReportPq().toString()));
		 		jsonMap.put("actualPq", (deviationObj.getDeliveryPq().toString()));
		 		
		 		System.out.println("---------------agrePq:"+ deviationObj.getReportPq().toString());
		 		System.out.println("++++++++++++++++actualPq:"+ deviationObj.getDeliveryPq().toString());
		 		params = jsonMap;
//				for (String obj : jsonMap.keySet()) {
//					params.put(obj, jsonMap.get(obj));
//				}
				flag = true;
			}
		}
		
		DeviationResult deviationResult = null;
		
		if(flag){
		
			//params.put("node1param1"," node1param1Value" );	//第一个节点所需参数
			System.out.println("////////////////params: "+ params.toString());
			GroovyFinalResult groovyResult = GroovyUtil.executeByBusiCode(smCalcConfigureDetail.getCalcCode(), params);		//调用工具得到返回结果
			List<Map<String, Object>> list = groovyResult.getNodeResultList();
			if(list != null && list.size() > 0){
				Map<String, Object> resultMap = list.get(0);
				
				BigDecimal deviationAmt = !resultMap.containsKey("deviationAmt") || resultMap.get("deviationAmt") == null || resultMap.get("deviationAmt").toString().equals("") ? BigDecimal.ZERO : new BigDecimal(resultMap.get("deviationAmt").toString());                              
				BigDecimal deviationPq = !resultMap.containsKey("deviationPq") || resultMap.get("deviationPq") == null || resultMap.get("deviationPq").toString().equals("") ? BigDecimal.ZERO : new BigDecimal(resultMap.get("deviationPq").toString());                              
				//BigDecimal deviationRate = !resultMap.containsKey("deviationRate") || resultMap.get("deviationRate") == null || resultMap.get("deviationRate").toString().equals("") ? BigDecimal.ZERO : new BigDecimal(resultMap.get("deviationRate").toString());                              
				
				deviationResult = new DeviationResult();
				deviationResult.setDeviationAmt(deviationAmt);
				deviationResult.setDeviationPq(deviationPq);
				//deviationResult.setDeviationProp(deviationRate);
			}
		}
		if(deviationResult == null){
			deviationResult = new DeviationResult();
			deviationResult.setDeviationAmt(BigDecimal.ZERO);
			deviationResult.setDeviationPq(BigDecimal.ZERO);
			//deviationResult.setDeviationProp(BigDecimal.ZERO);
		}
		
		return deviationResult;
	}
	
	/**
	 * 计算用户最终利润
	 */
	@Override
	public ConsDealProfitResult getConsDealProfit(DeviationRule rule,
			DivideObject divideObj,AmtScale amtScale,CompensateRule compensateRule) throws Exception {
//		DivideService divideServiceImpl = new ConsDValueDivideServiceImpl();
//		DeviationService deviationServiceImpl = new DValueDeviationServiceImpl();
		ConsDealProfitResult consDealProfitResult = new ConsDealProfitResult();
		BigDecimal upperProp = BigDecimal.ZERO;
		if(BusinessContants.YESFLAG.equals(rule.getUpperCheckFlag())&&rule.getUpperPqProp()!=null){
			upperProp = rule.getUpperPqProp();
		}
//		BigDecimal profit = divideServiceImpl.getDivide(divideObj, upperProp, amtScale, rule.getPriorityFlag());
		BigDecimal profit = divideService.getDivide(divideObj, upperProp, amtScale, rule.getPriorityFlag());
		DeviationObject deviationObj = new DeviationObject();
		ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(divideObj), deviationObj);
//		DeviationResult deviationResult = deviationServiceImpl.getDeviation(rule, deviationObj, amtScale);
		DeviationResult deviationResult = deviationService.getDeviation(rule, deviationObj, amtScale);
		BigDecimal consPunish = deviationResult.getDeviationAmt();
//		BigDecimal compensateAmt = deviationServiceImpl.getCompensateDeviation(compensateRule, deviationObj, amtScale);
		BigDecimal compensateAmt = deviationService.getCompensateDeviation(compensateRule, deviationObj, amtScale);
		consDealProfitResult.setConsDealProfit(profit);
		consDealProfitResult.setConsProfit(profit.subtract(consPunish).add(compensateAmt));
		consDealProfitResult.setConsPunish(consPunish);
		consDealProfitResult.setConsCompensate(compensateAmt);
		consDealProfitResult.setConsDeviationPq(deviationResult.getDeviationPq());
		return consDealProfitResult;
	}
	
}

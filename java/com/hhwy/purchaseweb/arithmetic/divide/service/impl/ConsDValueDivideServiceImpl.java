package com.hhwy.purchaseweb.arithmetic.divide.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.common.domain.TypeForPrc;
import com.hhwy.purchaseweb.arithmetic.common.service.CommonService;
import com.hhwy.purchaseweb.arithmetic.divide.domain.DivideObject;
import com.hhwy.purchaseweb.arithmetic.divide.service.DivideService;
import com.hhwy.purchaseweb.arithmetic.util.ArithmentUtil;
import com.hhwy.purchaseweb.contants.BusinessContants;

/**
 * 
 * <b>类 名 称：</b>DValueDivideServiceImpl<br/>
 * <b>类 描 述：</b>价差模式利润分成算法<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月28日 下午4:46:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Service("ConsDValueDivideService")
public class ConsDValueDivideServiceImpl implements DivideService {
	
	@Autowired @Qualifier("cloudSellingCommonService")
	private CommonService commonService;
	
	/**
	 * 用户分成利润计算
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * 
	 */
	@Override
	public BigDecimal getDivide(DivideObject divideObj,BigDecimal upperProp,
			AmtScale amtScale,String dealPriority) throws Exception {
		BigDecimal profit = BigDecimal.ZERO;//最终利润
		String divideCode = divideObj.getDiviCode();//分成方式
		if(StringUtils.isEmpty(dealPriority)){
			dealPriority = BusinessContants.PRIORITYFLAG01;
		}
		if(upperProp == null){
			upperProp = BigDecimal.ZERO;
		}
		if(BusinessContants.DIVIDEWAYCODE01.equals(divideCode)){
			//保底
			profit = getMarginProfit(divideObj, upperProp, amtScale);
		}else{
			if(BusinessContants.DIVIDEWAYCODE03.equals(divideCode)){
				//分成
				profit = getDivideProfit(divideObj, upperProp, amtScale, dealPriority);
			}else if(BusinessContants.DIVIDEWAYCODE05.equals(divideCode)){
				//保底加分成
				profit = getMarginAndDivideProfit(divideObj, upperProp, amtScale, dealPriority);
			}else if(BusinessContants.DIVIDEWAYCODE04.equals(divideCode)){
				//保底或分成
				BigDecimal marginProfit = getMarginProfit(divideObj, upperProp, amtScale);//保底利润
				BigDecimal divideProfit = getDivideProfit(divideObj, upperProp, amtScale, dealPriority);//分成利润
				if(marginProfit.compareTo(divideProfit)>0){
					profit = marginProfit;
				}else{
					profit = divideProfit;
				}
			}else if(BusinessContants.DIVIDEWAYCODE06.equals(divideCode)){ 
				//长协保底竞价分成
				profit = getLcMarginAndBidDivideProfit(divideObj, upperProp, amtScale, dealPriority);
			}
			if(BusinessContants.YESFLAG.equals(divideObj.getDiscountUpperFlag())
					&&profit.compareTo(divideObj.getDiscountUpperAmt())>0){
				profit = divideObj.getDiscountUpperAmt();//优惠上限
			}else if(BusinessContants.YESFLAG.equals(divideObj.getDiscountLowerFlag())
					&&profit.compareTo(divideObj.getDiscountLowerAmt())<0){
				profit = divideObj.getDiscountLowerAmt();//优惠下限
			}
		}
		
		return profit;
	}
	
	/**
	 * 用户利润（分成方式为长协保底竞价分行层）
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * 
	 */
	@Override
	public BigDecimal getLcMarginAndBidDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception {
		BigDecimal lcPq = divideObj.getDealLcPq();//长协成交电量
		BigDecimal agrePrc = divideObj.getLcPrc();//合同电价
		BigDecimal bidPq = divideObj.getDealBidPq();//竞价成交电量
		BigDecimal bidPrc = divideObj.getBidPrc();//竞价成交电价
		BigDecimal lcMargin = lcPq.multiply(agrePrc);//长协利润
		BigDecimal prop = divideObj.getPartyABidProp();//竞价分成比例
		BigDecimal bidDivide = bidPq.multiply(bidPrc).multiply(prop)
				.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);//竞价分成利润
		return lcMargin.add(bidDivide);
		
	}
	
	/**
	 * 用户利润（分成方式为保底加分成）
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * 
	 */
	@Override
	public BigDecimal getMarginAndDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception {
		BigDecimal marginProfit = getMarginProfit(divideObj, upperProp, amtScale);//保底利润
		Map<String,BigDecimal> map = getConsDealProfit(divideObj, upperProp, amtScale, dealPriority);
		BigDecimal consProfit = map.get("totalAmt");//用户交易利润
		if(marginProfit.compareTo(consProfit)<0){
			BigDecimal divideProfit = consProfit.subtract(marginProfit);//参与分成利润
			BigDecimal prop = divideObj.getPartyABidProp().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);//用户分成比例
			divideProfit = divideProfit.multiply(prop).setScale(2,BigDecimal.ROUND_HALF_UP);//用户分得利润
			return marginProfit.add(divideProfit);//用户利润
		}else{
			return marginProfit;//用户利润
		}
	}
	
	/**
	 * 用户利润（分成方式为保底）
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * 
	 */
	@Override
	public BigDecimal getMarginProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception {
		BigDecimal reportPq = divideObj.getReportPq();//申报电量
		BigDecimal deliveryPq = divideObj.getDeliveryPq();//交割电量
		BigDecimal effectivePq = reportPq.multiply(upperProp)
				.divide(new BigDecimal(100)).add(reportPq);//正偏差最大电量
		BigDecimal agrePrc = divideObj.getAgrePrc();//合同电价
		if(deliveryPq.compareTo(effectivePq)<0){
			return agrePrc.multiply(deliveryPq).multiply(amtScale.getAmtScale());//用户利润
		}else{
			return agrePrc.multiply(effectivePq).multiply(amtScale.getAmtScale());//用户利润
		}
	}
	
	/**
	 * 用户利润（分成方式为分成）
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * 
	 */
	@Override
	public BigDecimal getDivideProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception {
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal divideBidProp = divideObj.getPartyABidProp();//用户竞价分成比例
		BigDecimal divideLcProp = divideObj.getPartyALcProp();//用户长协分成比例
		Map<String,BigDecimal> map = getConsDealProfit(divideObj, upperProp, amtScale, dealPriority);//用户交易利润
		if(divideLcProp != null){
			BigDecimal lcProfit = map.get("lcAmt").multiply(divideLcProp)
					.divide(hundred,2,BigDecimal.ROUND_HALF_UP);//用户长协利润
			BigDecimal bidProfit = map.get("bidAmt").multiply(divideBidProp)
					.divide(hundred,2,BigDecimal.ROUND_HALF_UP);//用户竞价利润
			return lcProfit.add(bidProfit);//用户利润
		}else{
			return map.get("totalAmt").multiply(divideBidProp)
					.divide(hundred,2,BigDecimal.ROUND_HALF_UP);//用户利润
		}
	}
	
	/**
	 * 用户交易利润
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * @param dealPriority 交易优先顺序
	 * 
	 */
	@Override
	public Map<String,BigDecimal> getConsDealProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale,String dealPriority) throws Exception{
		if(BusinessContants.PRIORITYFLAG01.equals(dealPriority)){
			return getLcPriorityProfit(divideObj, upperProp, amtScale);
		}else if(BusinessContants.PRIORITYFLAG02.equals(dealPriority)){
			return getBidPriorityProfit(divideObj, upperProp, amtScale); 
		}else if(BusinessContants.PRIORITYFLAG03.equals(dealPriority)){
			BigDecimal lcPq = divideObj.getLcPq();//长协申报电量
			BigDecimal reportPq = divideObj.getReportPq();//总申报电量
			BigDecimal amt = getNoPriorityProfit(divideObj, upperProp, amtScale);
			BigDecimal lcAmt = amt.multiply(lcPq).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//长协交易利润
			BigDecimal bidAmt = amt.subtract(lcAmt);//竞价交易利润
			
			Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
			map.put("lcAmt", lcAmt);
			map.put("bidAmt", bidAmt);
			map.put("totalAmt", amt);
			
			return map;
		}else{
			Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
			map.put("lcAmt", BigDecimal.ZERO);
			map.put("bidAmt", BigDecimal.ZERO);
			map.put("totalAmt", BigDecimal.ZERO);
			
			return map;
		}
	}
	
	/**
	 * 长协优先时代理单一用户交易利润
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * 
	 */
	@Override
	public Map<String,BigDecimal> getLcPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception{
		BigDecimal lcAmt = BigDecimal.ZERO;//长协利润
		BigDecimal bidAmt = BigDecimal.ZERO;//竞价利润
		BigDecimal dealLcPq = divideObj.getDealLcPq();//长协成交电量
		if(dealLcPq == null){
			dealLcPq = divideObj.getLcPq();
		}
		BigDecimal lcPrc = divideObj.getLcPrc();//长协成交电价
		BigDecimal bidPrc = divideObj.getBidPrc();//长协成交电价
		BigDecimal deliveryPq = divideObj.getDeliveryPq();//交割电量
		BigDecimal reportPq = divideObj.getReportPq();//申报总电量
		BigDecimal effectivePq = reportPq.multiply(upperProp)
				.divide(new BigDecimal(100)).add(reportPq);//正偏差最大电量
		if(dealLcPq!=null&&deliveryPq.compareTo(dealLcPq)<=0){
			if(lcPrc == null&&divideObj.getLcAgreAmt()!=null){
				lcAmt = dealLcPq.multiply(divideObj.getLcAgreAmt()).divide(divideObj.getLcAgrePq(),2,BigDecimal.ROUND_HALF_UP);
			}else if(lcPrc!=null){
				lcAmt = dealLcPq.multiply(lcPrc).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				lcAmt = BigDecimal.ZERO;
			}
		}else{
			BigDecimal deliveryBidPq = BigDecimal.ZERO;//竞价交割电量
			if(dealLcPq!=null){
				if(lcPrc == null&&divideObj.getLcAgreAmt()!=null){
					lcAmt = dealLcPq.multiply(divideObj.getLcAgreAmt()).divide(divideObj.getLcAgrePq(),2,BigDecimal.ROUND_HALF_UP);
				}else if(lcPrc!=null){
					lcAmt = dealLcPq.multiply(lcPrc).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);
				}else{
					lcAmt = BigDecimal.ZERO;
				}
				if(deliveryPq.compareTo(effectivePq)<0&&deliveryPq.compareTo(dealLcPq)>0){
					deliveryBidPq = deliveryPq.subtract(dealLcPq);
				}else if(deliveryPq.compareTo(effectivePq)>=0){
					deliveryBidPq = effectivePq.subtract(dealLcPq);
				}
			}else{
				if(deliveryPq.compareTo(effectivePq)<0){
					deliveryBidPq = deliveryPq;
				}else if(deliveryPq.compareTo(effectivePq)>=0){
					deliveryBidPq = effectivePq;
				}
			}
			//竞价利润
			bidAmt = deliveryBidPq.multiply(bidPrc).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lcAmt", lcAmt);
		map.put("bidAmt", bidAmt);
		map.put("totalAmt", lcAmt.add(bidAmt));
		
		return map;
	}
	
	/**
	 * 竞价优先时代理单一用户交易利润
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * 
	 */
	@Override
	public Map<String,BigDecimal> getBidPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception{
		BigDecimal lcAmt = BigDecimal.ZERO;//长协利润
		BigDecimal bidAmt = BigDecimal.ZERO;//竞价利润
		BigDecimal dealBidPq = divideObj.getDealBidPq();//竞价成交电量
		if(dealBidPq == null){
			dealBidPq = divideObj.getBidPq();
		}
		BigDecimal bidPrc = divideObj.getBidPrc();//竞价成交电价
		BigDecimal lcPrc = divideObj.getLcPrc();//竞价成交电价
		BigDecimal deliveryPq = divideObj.getDeliveryPq();//交割电量
		BigDecimal reportPq = divideObj.getReportPq();//申报总电量
		BigDecimal effectivePq = reportPq.multiply(upperProp)
				.divide(new BigDecimal(100)).add(reportPq);//正偏差最大电量
		if(dealBidPq!=null&&deliveryPq.compareTo(dealBidPq)<=0){
			bidAmt = deliveryPq.multiply(bidPrc).multiply(amtScale.getAmtScale()).multiply(amtScale.getAmtScale())
					.setScale(2,BigDecimal.ROUND_HALF_UP);//交易利润
		}else{
			BigDecimal deliveryLcPq = BigDecimal.ZERO;//长协交割电量
			if(dealBidPq!=null){
				bidAmt = dealBidPq.multiply(bidPrc).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//竞价利润
				if(deliveryPq.compareTo(effectivePq)<0&&deliveryPq.compareTo(dealBidPq)>0){
					deliveryLcPq = deliveryPq.subtract(dealBidPq);
				}else if(deliveryPq.compareTo(effectivePq)>=0){
					deliveryLcPq = effectivePq.subtract(dealBidPq);
				}
			}else{
				if(deliveryPq.compareTo(effectivePq)<0){
					deliveryLcPq = deliveryPq;
				}else if(deliveryPq.compareTo(effectivePq)>=0){
					deliveryLcPq = effectivePq;
				}
			}
			//长协利润
			if(lcPrc == null&& divideObj.getLcAgreAmt()!=null && divideObj.getLcAgreAmt().compareTo(BigDecimal.ZERO)!=0){
				lcAmt = deliveryLcPq.multiply(divideObj.getLcAgreAmt()).divide(divideObj.getLcAgrePq(),2,BigDecimal.ROUND_HALF_UP);
			}else if(lcPrc!=null && divideObj.getLcAgreAmt().compareTo(BigDecimal.ZERO)!=0){
				lcAmt = deliveryLcPq.multiply(lcPrc).multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				lcAmt = BigDecimal.ZERO;
			}
		}
		
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lcAmt", lcAmt);
		map.put("bidAmt", bidAmt);
		map.put("totalAmt", lcAmt.add(bidAmt));
		
		return map;
	}
	
	/**
	 * 不分先后顺序时代理单一用户交易利润
	 * 
	 * @param divideObj 分成计算参数
	 * @param upperProp 正偏差阈值
	 * @param amtScale 数据精度
	 * 
	 */
	@Override
	public BigDecimal getNoPriorityProfit(DivideObject divideObj,
			BigDecimal upperProp,AmtScale amtScale) throws Exception { 
		BigDecimal deliveryPq = divideObj.getDeliveryPq();//交割电量
		BigDecimal reportPq = divideObj.getReportPq();//申报总电量
		BigDecimal effectivePq = reportPq.multiply(upperProp)
				.divide(new BigDecimal(100)).add(reportPq);//正偏差最大电量
		//合同加权平均价
		TypeForPrc typeForPrc = new TypeForPrc();
		ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(divideObj), typeForPrc);
		typeForPrc.setPrcType(BusinessContants.PRCTYPE02);
		BigDecimal avgPrc = commonService.getTypeFotPrc(typeForPrc);
		if(deliveryPq.compareTo(effectivePq)>0){
			return effectivePq.multiply(avgPrc).multiply(amtScale.getAmtScale());
		}else{
			return deliveryPq.multiply(avgPrc).multiply(amtScale.getAmtScale());
		}
	}

}

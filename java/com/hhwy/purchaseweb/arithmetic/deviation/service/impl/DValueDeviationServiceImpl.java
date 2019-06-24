package com.hhwy.purchaseweb.arithmetic.deviation.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.common.domain.TypeForPrc;
import com.hhwy.purchaseweb.arithmetic.common.service.CommonService;
//import com.hhwy.purchaseweb.arithmetic.common.service.impl.CommonServiceImpl;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.CompensateRule;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationObject;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationResult;
import com.hhwy.purchaseweb.arithmetic.deviation.domain.DeviationRule;
import com.hhwy.purchaseweb.arithmetic.deviation.service.DeviationService;
import com.hhwy.purchaseweb.arithmetic.util.ArithmentUtil;
import com.hhwy.purchaseweb.contants.BusinessContants;

/**
 * 
 * <b>类 名 称：</b>DValueDeviationServiceImpl<br/>
 * <b>类 描 述：</b>价差模式偏差考核实现<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月28日 下午4:48:39<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Service("DValueDeviationService")
public class DValueDeviationServiceImpl implements DeviationService {
	
	@Autowired @Qualifier("cloudSellingCommonService")
	private CommonService commonService;

	/**
	 * 计算偏差考核
	 */
	@Override
	public DeviationResult getDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		//对比申报和交割电量，决定考核计算算法
		BigDecimal reportPq = deviationObj.getReportPq();//申报电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		if(reportPq == null || deliveryPq == null){
			//无偏差
			DeviationResult deviationResult = new DeviationResult();
			deviationResult.setDeviationAmt(BigDecimal.ZERO);
			deviationResult.setDeviationPq(BigDecimal.ZERO);
			deviationResult.setDeviationProp(BigDecimal.ZERO);
			return deviationResult;
		}
		int flag = reportPq.compareTo(deliveryPq);
		if(BusinessContants.YESFLAG.equals(rule.getUpperCheckFlag())&&flag<0){
			//正偏差
			return getUpperDeviation(rule, deviationObj, amtScale);
		}else if(BusinessContants.YESFLAG.equals(rule.getLowerCheckFlag())&&flag>0){
			//负偏差
			return getLowerDeviation(rule, deviationObj, amtScale);
		}else{ 
			//无偏差
			DeviationResult deviationResult = new DeviationResult();
			deviationResult.setDeviationAmt(BigDecimal.ZERO);
			deviationResult.setDeviationPq(BigDecimal.ZERO);
			deviationResult.setDeviationProp(BigDecimal.ZERO);
			return deviationResult;
		}
	}

	/**
	 * 正偏差考核计算
	 */
	@Override
	public DeviationResult getUpperDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
//		commonService = new CommonServiceImpl();
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal prop = rule.getUpperPqProp();
		if(prop == null){
			prop = BigDecimal.ONE;
		}else{
			prop = hundred.add(prop).divide(hundred);//正偏差允许电量比例
		}
		BigDecimal reportPq = deviationObj.getReportPq();//申报电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		BigDecimal effectivePq = reportPq.multiply(prop);//正偏差最大允许电量
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		int flag = effectivePq.compareTo(deliveryPq);
		if(flag<0){
			//正偏差
			BigDecimal deviationPq = deliveryPq.subtract(reportPq);//偏差电量
			deviationResult.setDeviationPq(deviationPq);
			BigDecimal deviationProp = BigDecimal.ZERO;
			if(reportPq.compareTo(BigDecimal.ZERO)!=0){
				deviationProp = deviationPq.multiply(hundred).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//偏差率
			}
			deviationResult.setDeviationPq(deviationProp);
			//考核电价
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化
			typeForPrc.setPrcType(rule.getUpperCheckPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getUpperCheckPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getUpperCheckPrc());//赋值人工录入电价
			BigDecimal deviationPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationCheckPq = deliveryPq.subtract(effectivePq);//偏差考核电量
			BigDecimal deviationAmt = deviationCheckPq.multiply(deviationPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
			deviationResult.setDeviationAmt(deviationAmt);
		}
		
		return deviationResult;
		
	}

	/**
	 * 负偏差考核计算
	 */
	@Override
	public DeviationResult getLowerDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
//		commonService = new CommonServiceImpl();
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		BigDecimal reportPq = deviationObj.getReportPq();//申报电量
		BigDecimal deviationPq = reportPq.subtract(deliveryPq);//偏差电量
		BigDecimal deviationProp = reportPq.subtract(deliveryPq);//偏差电量占比
		BigDecimal lcPq = deviationObj.getLcPq();//长协申报电量
		BigDecimal bidPq = deviationObj.getBidPq();//竞价申报电量 
		DeviationResult deviationResult = new DeviationResult();
		deviationResult.setDeviationPq(deviationPq);
		deviationResult.setDeviationProp(deviationProp);
		if(BusinessContants.YESFLAG.equals(rule.getSplitCheckFlag())){
			//拆分负偏差考核
			if(BusinessContants.PRIORITYFLAG01.equals(rule.getPriorityFlag())){
				if(lcPq!=null&&deliveryPq.compareTo(lcPq)<0){
					DeviationResult lcDeviation = getLowerLcCheckDeviation(rule, deviationObj, amtScale);//长协偏差信息
					deviationObj.setDeliveryPq(BigDecimal.ZERO);
					DeviationResult bidDeviation = getLowerBidCheckDeviation(rule, deviationObj, amtScale);//竞价偏差信息
					deviationResult.setDeviationAmt(lcDeviation.getDeviationAmt().add(bidDeviation.getDeviationAmt()));//偏差考核金额
				}else{
					if(bidPq != null){
						deviationResult.setDeviationAmt(getLowerBidCheckDeviation(rule, deviationObj, amtScale).getDeviationAmt());//偏差考核金额
					}
				}
			}else if(BusinessContants.PRIORITYFLAG02.equals(rule.getPriorityFlag())){
				if(bidPq!=null&&deliveryPq.compareTo(bidPq)<0){
					DeviationResult bidDeviation = getLowerBidCheckDeviation(rule, deviationObj, amtScale);//竞价偏差信息
					deviationObj.setDeliveryPq(BigDecimal.ZERO);
					DeviationResult lcDeviation = getLowerLcCheckDeviation(rule, deviationObj, amtScale);//长协偏差信息
					deviationResult.setDeviationAmt(lcDeviation.getDeviationAmt().add(bidDeviation.getDeviationAmt()));//偏差考核金额
				}else{
					if(lcPq != null){
						deviationResult.setDeviationAmt(getLowerLcCheckDeviation(rule, deviationObj, amtScale).getDeviationAmt());//偏差考核金额
					}
				}
			}else if(BusinessContants.PRIORITYFLAG03.equals(rule.getPriorityFlag())){
				deviationResult.setDeviationAmt(getLowerPropCheckDeviation(rule, deviationObj, amtScale).getDeviationAmt());//偏差考核金额
			}
		}else{
			//不拆分负偏差考核
			deviationResult.setDeviationAmt(getLowerCheckDeviation(rule, deviationObj, amtScale).getDeviationAmt());//偏差考核金额
		}
		
		return deviationResult;
		
	}
	
	/**
	 * 不拆分考核时负偏差计算
	 */
	@Override
	public DeviationResult getLowerCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal prop = hundred.subtract(rule.getLowerPqProp()).divide(hundred);//负偏差允许电量比例
		BigDecimal reportPq = deviationObj.getReportPq();//申报电量
		BigDecimal effectivePq = reportPq.multiply(prop);//负偏差最大允许电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		int flag = effectivePq.compareTo(deliveryPq);
		if(flag>0){
			//负偏差
			BigDecimal deviationPq = effectivePq.subtract(deliveryPq);//偏差考核电量
			deviationResult.setDeviationPq(deviationPq);
			BigDecimal deviationProp = BigDecimal.ZERO;
			if(reportPq.compareTo(BigDecimal.ZERO)!=0){
				deviationProp = deviationPq.multiply(hundred).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//偏差率
			}
			deviationResult.setDeviationPq(deviationProp);
			//考核电价
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化
			typeForPrc.setPrcType(rule.getLowerCheckPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getLowerCheckPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getLowerCheckPrc());//赋值人工录入电价
			BigDecimal deviationPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationAmt = deviationPq.multiply(deviationPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
			deviationResult.setDeviationAmt(deviationAmt);
		}
		
		return deviationResult;
		
	}

	/**
	 * 拆分考核时长协负偏差计算
	 */
	@Override
	public DeviationResult getLowerLcCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal lcPq = deviationObj.getLcPq();//申报电量
		BigDecimal prop = hundred.subtract(rule.getLowerCheckLcPqProp()).divide(hundred,2,BigDecimal.ROUND_HALF_UP);//长协负偏差允许电量比例
		BigDecimal effectivePq = lcPq.multiply(prop);//长协负偏差最大允许电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		int flag = effectivePq.compareTo(deliveryPq);
		if(flag>0){
			//长协负偏差
			BigDecimal deviationPq = effectivePq.subtract(deliveryPq);//偏差考核电量
			//考核电价
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化
			typeForPrc.setPrcType(rule.getLowerCheckLcPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getLowerCheckLcPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getLowerCheckLcPrc());//赋值人工录入电价
			BigDecimal deviationPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationAmt = deviationPq.multiply(deviationPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
			deviationResult.setDeviationAmt(deviationAmt);
		}
		
		return deviationResult;
		
	}

	/**
	 * 拆分考核时竞价负偏差计算
	 */
	@Override
	public DeviationResult getLowerBidCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal prop = hundred.subtract(rule.getLowerCheckBidPqProp()).divide(hundred,2,BigDecimal.ROUND_HALF_UP);//竞价负偏差允许电量比例
		BigDecimal bidPq = deviationObj.getBidPq();//申报电量
		BigDecimal effectivePq = bidPq.multiply(prop);//竞价负偏差最大允许电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		int flag = effectivePq.compareTo(deliveryPq);
		if(flag>0){
			//竞价负偏差 
			BigDecimal deviationPq = deliveryPq.subtract(effectivePq);//偏差考核电量
			//考核电价
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化
			typeForPrc.setPrcType(rule.getLowerCheckBidPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getLowerCheckBidPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getLowerCheckBidPrc());//赋值人工录入电价
			BigDecimal deviationPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationAmt = deviationPq.multiply(deviationPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
			deviationResult.setDeviationAmt(deviationAmt);
		}
		
		return deviationResult;
		
	}

	/**
	 * 按比例负偏差计算
	 */
	@Override
	public DeviationResult getLowerPropCheckDeviation(DeviationRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal reportPq = deviationObj.getReportPq();//申报总电量
		BigDecimal deliveryPq = deviationObj.getDeliveryPq();//交割电量
		BigDecimal actualDeviation = reportPq.subtract(deliveryPq);//真实偏差电量
		
		BigDecimal lcPq = deviationObj.getLcPq();//长协申报电量
		if(lcPq==null){
			lcPq = BigDecimal.ZERO;
		}
		BigDecimal lcProp = rule.getLowerCheckLcPqProp().divide(hundred);//长协负偏差允许电量比例
		BigDecimal deviationLcPq = lcPq.multiply(lcProp);//长协负偏差允许偏差电量
		BigDecimal bidPq = deviationObj.getBidPq();//竞价申报电量
		if(bidPq==null){
			bidPq = BigDecimal.ZERO;
		}
		BigDecimal BidProp = rule.getLowerCheckBidPqProp().divide(hundred);//竞价负偏差允许电量比例
		BigDecimal deviationBidPq = bidPq.multiply(BidProp);//竞价负偏差允许偏差电量
		BigDecimal deviationTotalPq = deviationLcPq.add(deviationBidPq);//负偏差允许偏差总电量
		int flag = deviationTotalPq.compareTo(actualDeviation);
		if(flag<0){
			//负偏差 
			BigDecimal deviationPq = actualDeviation.subtract(deviationTotalPq);//偏差考核电量
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化

			BigDecimal lcDeviationPq = BigDecimal.ZERO;
			if(reportPq.compareTo(BigDecimal.ZERO)!=0){
				lcDeviationPq = deviationPq.multiply(lcPq).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//长协偏差考核电量
			}
			//长协考核电价
			typeForPrc.setPrcType(rule.getLowerCheckLcPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getLowerCheckLcPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getLowerCheckLcPrc());//赋值人工录入电价
			BigDecimal deviationLcPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationLcAmt = lcDeviationPq.multiply(deviationLcPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额

			BigDecimal bidDeviationPq = BigDecimal.ZERO;
			if(reportPq.compareTo(BigDecimal.ZERO)!=0){
				bidDeviationPq = deviationPq.multiply(bidPq).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//竞价偏差考核电量
			}
			typeForPrc.setPrcType(rule.getLowerCheckBidPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getLowerCheckBidPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getLowerCheckBidPrc());//赋值人工录入电价
			BigDecimal deviationBidPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			BigDecimal deviationBidAmt = bidDeviationPq.multiply(deviationBidPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
			deviationResult.setDeviationAmt(deviationLcAmt.add(deviationBidAmt));//总考核金额
		}
		
		return deviationResult;
		
	}
	
	/**
	 * 售电公司赔偿金额计算
	 */
	@Override
	public BigDecimal getCompensateDeviation(CompensateRule rule,DeviationObject deviationObj,AmtScale amtScale) throws Exception {
		BigDecimal hundred = new BigDecimal(100);//计算常量
		if(BusinessContants.NOFLAG.equals(rule.getCompFlag())){
			return BigDecimal.ZERO;
		}
		BigDecimal prop = hundred.subtract(rule.getCompPqProp()).divide(hundred);//赔偿阈值
		BigDecimal reportPq = deviationObj.getReportPq();//申报电量
		BigDecimal effectivePq = reportPq.multiply(prop);//赔偿最大允许电量
		BigDecimal dealPq = deviationObj.getLcPq().add(deviationObj.getBidPq());//成交电量
		//计算结果
		DeviationResult deviationResult = new DeviationResult();
		//无偏差
		deviationResult.setDeviationAmt(BigDecimal.ZERO);
		deviationResult.setDeviationPq(BigDecimal.ZERO);
		deviationResult.setDeviationProp(BigDecimal.ZERO);
		int flag = effectivePq.compareTo(dealPq);
		BigDecimal deviationAmt = BigDecimal.ZERO;
		if(flag>0){
			//负偏差
			BigDecimal deviationPq = effectivePq.subtract(dealPq);//偏差考核电量
			deviationResult.setDeviationPq(deviationPq);
			BigDecimal deviationProp = deviationPq.multiply(hundred).divide(reportPq,2,BigDecimal.ROUND_HALF_UP);//偏差考核率
			deviationResult.setDeviationPq(deviationProp);
			//考核电价
			TypeForPrc typeForPrc = new TypeForPrc();
			ArithmentUtil.transMapToBean(ArithmentUtil.transBeanToMap(deviationObj), typeForPrc);//数据转化
			typeForPrc.setPrcType(rule.getCompPrcType());//赋值电价类型
			typeForPrc.setPrcProp(rule.getCompPrcProp());//赋值电价比例
			typeForPrc.setCustomPrc(rule.getCompPrc());//赋值人工录入电价
			BigDecimal deviationPrc = commonService.getTypeFotPrc(typeForPrc);//计算考核电价
			deviationAmt = deviationPq.multiply(deviationPrc)
					.multiply(amtScale.getAmtScale()).setScale(2,BigDecimal.ROUND_HALF_UP);//计算考核金额
		}
		
		return deviationAmt;
		
	}

}

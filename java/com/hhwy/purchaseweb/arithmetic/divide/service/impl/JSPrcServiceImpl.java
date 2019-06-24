package com.hhwy.purchaseweb.arithmetic.divide.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.hhwy.framework.exception.BusinessException;
import com.hhwy.purchaseweb.arithmetic.divide.service.PrcService;
import com.hhwy.purchaseweb.contants.BusinessContants;

@Service("JSPrcService")
public class JSPrcServiceImpl implements PrcService {

	/**
	 * 价差模式电价计算方法，返回用户结算电价
	 * @param settlementModeCode 价差或者价格模式
	 * @param divideCode 分成方式
	 * @param modelPrc 标杆电价
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益价差 
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception
	 */
	@Override
	public BigDecimal getPrc(String settlementModeCode,String divideCode, BigDecimal modelPrc,
			BigDecimal agrePrc, BigDecimal divideProp, BigDecimal profitPrc,
			String profitFirstCode) throws Exception {
		if(settlementModeCode.equals(BusinessContants.SETTLEMENTMODECODE01)){
			return getSpreadPrc(divideCode, modelPrc, agrePrc, divideProp, profitPrc, profitFirstCode);
		}else if(settlementModeCode.equals(BusinessContants.SETTLEMENTMODECODE02)){
			return getPricePrc(divideCode, modelPrc, agrePrc, divideProp, profitPrc, profitFirstCode);
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 价差模式电价计算方法，返回用户结算电价
	 * @param divideCode 分成方式
	 * @param modelPrc 标杆电价
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益价差 
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getSpreadPrc(String divideCode,BigDecimal modelPrc,BigDecimal agrePrc,
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception {
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal balancePrc = BigDecimal.ZERO;//结算电价
		if(divideProp == null){
			divideProp = BigDecimal.ZERO;
		}
		if(BusinessContants.DIVIDEWAYCODE01.equals(divideCode)){
			//保底
			balancePrc = modelPrc.subtract(agrePrc).setScale(4,BigDecimal.ROUND_HALF_UP);
		}else{
			if(BusinessContants.DIVIDEWAYCODE03.equals(divideCode)){
				//分成
				balancePrc = modelPrc.subtract(profitPrc.multiply(divideProp).divide(hundred,4,BigDecimal.ROUND_HALF_UP))
						.setScale(4, BigDecimal.ROUND_HALF_UP);
			}else if(BusinessContants.DIVIDEWAYCODE05.equals(divideCode)){
				//保底加分成
				if(agrePrc.compareTo(profitPrc)<0){
					BigDecimal subPrc = agrePrc.add(profitPrc.subtract(agrePrc).multiply(divideProp).divide(hundred,4,BigDecimal.ROUND_HALF_UP))
							.setScale(4,BigDecimal.ROUND_HALF_UP);//用户收益价差
					balancePrc = modelPrc.subtract(subPrc).setScale(4,BigDecimal.ROUND_HALF_UP);
				}else{
					//保底价差大于等于收益价差，无分成利益
					balancePrc = modelPrc.subtract(agrePrc).setScale(4,BigDecimal.ROUND_HALF_UP);
				}
			}else if(BusinessContants.DIVIDEWAYCODE04.equals(divideCode)){
				//保底或分成
				BigDecimal marginPrc = modelPrc.subtract(agrePrc).setScale(4,BigDecimal.ROUND_HALF_UP);//保底电价
				BigDecimal dividePrc = modelPrc.subtract(profitPrc.multiply(divideProp).divide(hundred,4,BigDecimal.ROUND_HALF_UP))
						.setScale(4, BigDecimal.ROUND_HALF_UP);//分成电价
				if(profitFirstCode.equals(BusinessContants.PROFIT_FIRST_CODE01)){
					//甲方收益最大
					if(marginPrc.compareTo(dividePrc)>0){
						balancePrc = dividePrc;
					}else{
						balancePrc = marginPrc;
					}					
				}else if(profitFirstCode.equals(BusinessContants.PROFIT_FIRST_CODE02)){
					//乙方收益最大
					if(marginPrc.compareTo(dividePrc)>0){
						balancePrc = marginPrc;
					}else{
						balancePrc = dividePrc;
					}					
				}
			}
		}
		
		return balancePrc;
	}

	/**
	 * 价格模式电价计算方法，返回用户结算电价
	 * @param divideCode 分成方式
	 * @param modelPrc 标杆电价
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益电价
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getPricePrc(String divideCode,BigDecimal modelPrc,BigDecimal agrePrc,
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception {
		return this.getPricePrc(divideCode,modelPrc,modelPrc,agrePrc,
				divideProp,profitPrc,profitFirstCode);
	}
	/**
	 * @Title: getPricePrc
	 * @Description: 价格模式电价计算方法，返回用户结算电价
	 * @param divideCode 分成方式
	 * @param modelPrcDefault 默认分成基准值（分成基准值默认为  目录电价-输配电价）
	 * @param modelPrc 标杆电价（用户调整之后的分成基准值）
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益电价 
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception 
	 * BigDecimal
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年9月10日 下午4:08:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年9月10日 下午4:08:48
	 * @since  1.0.0
	 */
	public BigDecimal getPricePrc(String divideCode,BigDecimal modelPrcDefault,BigDecimal modelPrc,BigDecimal agrePrc,
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception {
		BigDecimal hundred = new BigDecimal(100);//计算常量
		BigDecimal balancePrc = BigDecimal.ZERO;//结算电价
		if(divideProp == null){
			divideProp = BigDecimal.ZERO;
		}
		if(BusinessContants.DIVIDEWAYCODE01.equals(divideCode)){			//保底
			if(agrePrc == null) {
				throw new BusinessException("保底电价为空！");
			}
			//保底
			balancePrc = agrePrc.setScale(4,BigDecimal.ROUND_HALF_UP);
		}else{
			if(BusinessContants.DIVIDEWAYCODE03.equals(divideCode)){		//分成
				//分成
				balancePrc = modelPrcDefault.subtract(modelPrc.subtract(profitPrc).multiply(divideProp)
						.divide(hundred)).setScale(4, BigDecimal.ROUND_HALF_UP);
			}else if(BusinessContants.DIVIDEWAYCODE05.equals(divideCode)){		//固定保底加分成
				if(agrePrc == null) {
					throw new BusinessException("保底电价为空！");
				}
				//保底加分成
				if(agrePrc.compareTo(profitPrc)>0){
					balancePrc = agrePrc.subtract(agrePrc.subtract(profitPrc).multiply(divideProp)
							.divide(hundred,4,BigDecimal.ROUND_HALF_UP)).setScale(4,BigDecimal.ROUND_HALF_UP);//用户收益价差
				}else{
					//保底电价 小于等于 收益电价，无分成利益，保底电价即结算电价
					balancePrc = agrePrc.setScale(4,BigDecimal.ROUND_HALF_UP);
				}
			}else if(BusinessContants.DIVIDEWAYCODE07.equals(divideCode)){		//联动保底加分成
				if(agrePrc == null) {
					throw new BusinessException("保底电价为空！");
				}
				//保底加分成
				if(agrePrc.compareTo(profitPrc)>0){
					balancePrc = agrePrc.subtract(agrePrc.subtract(profitPrc).multiply(divideProp)
							.divide(hundred,4,BigDecimal.ROUND_HALF_UP)).setScale(4,BigDecimal.ROUND_HALF_UP);//用户收益价差
				}else{
					//保底电价 小于等于 收益电价，无分成利益，收益电价即结算电价
					balancePrc = profitPrc.setScale(4,BigDecimal.ROUND_HALF_UP);
				}
			}else if(BusinessContants.DIVIDEWAYCODE04.equals(divideCode)){		//保底或分成
				if(agrePrc == null) {
					throw new BusinessException("保底电价为空！");
				}
				//保底或分成
				BigDecimal marginPrc = agrePrc.setScale(4,BigDecimal.ROUND_HALF_UP);//保底电价
				BigDecimal dividePrc = BigDecimal.ZERO;//分成电价
				if(modelPrc.compareTo(profitPrc)>0){
					//标杆电价大于收益电价
					dividePrc =  modelPrcDefault.subtract(modelPrc.subtract(profitPrc).multiply(divideProp)
							.divide(hundred)).setScale(4, BigDecimal.ROUND_HALF_UP);
				}else{
					dividePrc = modelPrc;
				}
				if(profitFirstCode.equals(BusinessContants.PROFIT_FIRST_CODE01)){
					//甲方收益最大
					if(marginPrc.compareTo(dividePrc)>0){
						balancePrc = dividePrc;
					}else{
						balancePrc = marginPrc;
					}					
				}else if(profitFirstCode.equals(BusinessContants.PROFIT_FIRST_CODE02)){
					//乙方收益最大
					if(marginPrc.compareTo(dividePrc)>0){
						balancePrc = marginPrc;
					}else{
						balancePrc = dividePrc;
					}					
				}
			}
			if(modelPrc.compareTo(balancePrc)<=0){
				//标杆电价小于等于收益电价
				balancePrc = modelPrc;
			}
		}
		return balancePrc;
	}
}

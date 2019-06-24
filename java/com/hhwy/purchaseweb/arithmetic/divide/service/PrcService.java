/**
 * 
 */
package com.hhwy.purchaseweb.arithmetic.divide.service;

import java.math.BigDecimal;

/**
 * 
 * <b>类 名 称：</b>PrcService<br/>
 * <b>类 描 述：</b>电价算法<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2018年03月31日 下午4:46:57<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 *
 */
public interface PrcService {

	/**
	 * 电价计算方法，返回用户结算电价
	 * @param settlementModeCode 价差或者价格模式
	 * @param divideCode 分成方式
	 * @param modelPrc 标杆电价
	 * @param agrePrc 保底电价
	 * @param divideProp 分成比例
	 * @param profitPrc 收益价差 
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getPrc(String settlementModeCode,String divideCode,BigDecimal modelPrc,BigDecimal agrePrc,
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception;
	
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
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception ;
	
	/**
	 * 价格模式电价计算方法，返回用户结算电价
	 * @param divideCode 分成方式
	 * @param modelPrc 标杆电价
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益价差 
	 * @param profitFirstCode 收益优先级
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getPricePrc(String divideCode,BigDecimal modelPrc,BigDecimal agrePrc,
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception ;
	/**
	 * @Title: getPricePrc
	 * @Description: 价格模式电价计算方法，返回用户结算电价
	 * @param divideCode 分成方式
	 * @param modelPrcDefault 默认分成基准值（分成基准值默认为  目录电价-输配电价）
	 * @param modelPrc 标杆电价（用户调整之后的分成基准值）
	 * @param agrePrc 保底电价
	 * @param divideProp 用户分成比例（百分数），算法内进行换算
	 * @param profitPrc 收益价差 
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
			BigDecimal divideProp,BigDecimal profitPrc,String profitFirstCode) throws Exception;
}

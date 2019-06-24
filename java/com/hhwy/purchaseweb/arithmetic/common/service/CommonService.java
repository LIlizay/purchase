package com.hhwy.purchaseweb.arithmetic.common.service;

import java.math.BigDecimal;
import java.util.Map;

import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.arithmetic.common.domain.AmtScale;
import com.hhwy.purchaseweb.arithmetic.common.domain.TypeForPrc;

public interface CommonService {
	
	/**
	 * 
	 * @Title: getRule
	 * @Description: 获取当前登陆人所在省份配置规则或指定省份配置规则
	 * @param province
	 * @return 
	 * SmRuleConfigure
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年6月15日 上午9:01:45
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年6月15日 上午9:01:45
	 * @since  1.0.0
	 */
	public SmRuleConfigure getRule(String province,String ruleId) throws Exception;

	/**
	 * 
	 * @Title: getCatPrc
	 * @Description: 获取客户对应目录电价
	 * @param custId 客户id，目录电价根据客户id关联查询
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月3日 下午7:10:29
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月3日 下午7:10:29
	 * @since  1.0.0
	 */
	public BigDecimal getCatPrc(String custId) throws Exception;

	/**
	 * 
	 * @Title: getTypeFotPrc
	 * @Description: 获取对应电价类型的电价
	 * @param 
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月4日 下午1:09:01
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月4日 下午1:09:01
	 * @since  1.0.0
	 */
	public BigDecimal getTypeFotPrc(TypeForPrc typeForPrc) throws Exception;
	
	/**
	 * 
	 * @Title: getLcAgrePqAndPrc
	 * @Description: 获取当前年内长协合同总电量及总金额
	 * @return
	 * @throws Exception 
	 * Map<String,Object>
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月5日 下午9:24:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月23日 下午4:24:20
	 * @since  1.0.0
	 */
	public Map<String,Object> getLcAgrePqAndPrc(String ym) throws Exception;

	/**
	 * 
	 * @Title: getScaleAmt
	 * @Description: 根据客户id获取金额精度
	 * @return 
	 * BigDecimal
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2017年5月4日 下午7:31:06
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2017年5月4日 下午7:31:06
	 * @since  1.0.0
	 */
	public AmtScale getScaleAmt() throws Exception ;
	
}

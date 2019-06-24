package com.hhwy.purchaseweb.settlement.jiangsu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.domain.SmCompanyProfitVo;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.jiangsu.service.SettlementServiceJs;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeDetailControllerJs<br/>
 * <b>类 描 述：</b><br/>		江苏的结算方案详情的controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月27日 下午9:49:44<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/settlementControllerJs")
public class SettlementControllerJs {

	public static final Logger log = LoggerFactory.getLogger(SettlementControllerJs.class);
	
	/**
	 * smPurchaseSchemeDetailService
	 */
	@Autowired
	private SettlementServiceJs settlementServiceJs;
	
	/**
	 * @Title: calculateDeliveryPrc
	 * @Description: 计算单个用户的 结算电价
	 * @param smPurchaseSchemeDetailJs
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月27日 下午10:03:09
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月27日 下午10:03:09
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/calculateDeliveryPrc", method = RequestMethod.POST)
	public Object calculateDeliveryPrc(@RequestBody SmPurchaseSchemeDetailDetail smPurchaseSchemeDetail) {
		ExecutionResult result = new ExecutionResult();
		try {
			settlementServiceJs.calculateDeliveryPrc(smPurchaseSchemeDetail);
			result.setData(smPurchaseSchemeDetail);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("计算结算电价成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error(e.getMessage(),e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg((e.getMessage()==null || "".equals(e.getMessage()))? "结算电价计算失败！" : (e.getMessage() + "结算电价计算失败！") );					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: caculateProfit
	 * @Description: 根据结算页面用户填的分成模式和分配的双边和竞价电量等信息，计算零售实诚售电收入明细和批发购电市场指出明细
	 * 			即计算用户结算数据和售电公司结算数据
	 * @param smCompanyProfitVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月28日 下午3:01:30
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月28日 下午3:01:30
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/caculateProfit", method = RequestMethod.POST)
	public Object caculateProfit(@RequestBody SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPurchaseSchemeVoJs queryResult = settlementServiceJs.caculateProfit(smPurchaseSchemeVoJs);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(queryResult);							//设置数据列表
			result.setMsg("计算收益成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("计算收益失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SmCompanyProfitVo());						//设置返回结果为空
			result.setMsg("计算收益失败！" + (e.getMessage()==null ? "" : e.getMessage()));			//设置返回消息，根据实际情况修改
		}
		return result;
	}
}
package com.hhwy.purchaseweb.settlement.other.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.other.domain.RetailDetailOther;
import com.hhwy.purchaseweb.settlement.other.domain.SmPurchaseSchemeVoOther;
import com.hhwy.purchaseweb.settlement.other.service.SettlementServiceOther;

 /**
 * <b>类 名 称：</b>SettlementControllerOther<br/>
 * <b>类 描 述：</b><br/>		江苏、福建以外其他省的结算方案的controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:23:26<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/settlementControllerOther")
public class SettlementControllerOther {

	public static final Logger log = LoggerFactory.getLogger(SettlementControllerOther.class);
	
	/**
	 * smPurchaseSchemeDetailService
	 */
	@Autowired
	private SettlementServiceOther settlementServiceOther;
	
	/**
	 * @Title: getRetailDetailByYm
	 * @Description: 	根据年月获取当月“零售市场售电收入明细”列表(有可能当月已有结算数据，则取结算数据；若当月没有结算数据，则组装初始化的结算数据)
	 * 				其他省结算  新增或编辑页面的 零售市场售电收入明细 表数据
	 * @param ym
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:31:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:31:17
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getRetailDetailByYm", method = RequestMethod.GET)
	public Object getRetailDetailByYm(String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<RetailDetailOther> list = settlementServiceOther.getRetailDetailByYm(ym);
			result.setRows(list);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);					
			result.setMsg("零售市场售电收入明细信息获取成功！");					
		} catch (Exception e) {
			log.error("零售市场售电收入明细信息获取失败",e);						
			result.setCode(ReturnCode.RES_FAILED);		
			result.setFlag(false);						
			result.setMsg("零售市场售电收入明细信息获取失败！");					
		}
		return result;
	}
	
	
	/**
	 * @Title: saveSmPurchaseSchemeVo
	 * @Description: 其他省的新增或修改计划下的方案,可能还包含结算主表的新增。
	 * @param smPurchaseSchemeVoJs
	 * @return Object
	 * <b>创 建 人：</b>LiXinze<br/>
	 * <b>创建时间:</b>2018年9月6日 下午2:08:36
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年9月6日 下午2:08:36
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveSmPurchaseSchemeVo", method = RequestMethod.POST)
	public Object saveSmPurchaseSchemeVo(@RequestBody SmPurchaseSchemeVoOther smPurchaseSchemeVoOther) {
		ExecutionResult result = new ExecutionResult();
		try {
			settlementServiceOther.saveOrUpdateSmPurchaseSchemeVoOther(smPurchaseSchemeVoOther);
			result.setCode(ReturnCode.RES_SUCCESS);
			result.setData(smPurchaseSchemeVoOther);
			result.setFlag(true);
			result.setMsg("方案保存成功！");
		} catch (Exception e) {
			log.error("方案保存失败",e);
			result.setCode(ReturnCode.RES_FAILED);
			result.setFlag(false);
			result.setMsg("方案保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: getSmSettlementMonthInfoByIdOrYm
	 * @Description: 通过结算id或者年月获取结算方案列表页面上面的的form表单中的数据
	 * @param settleId
	 * @param ym
	 * @return Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月27日 上午10:00:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月27日 上午10:00:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getFormDateBySettleIdOrYm", method = RequestMethod.GET)
	public Object getSmSettlementMonthInfoByIdOrYm(String settleId, String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmSettlementMonthDetail smSettlementMonth = settlementServiceOther.getSmSettlementMonthInfoByIdOrYm(settleId, ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smSettlementMonth);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	
}
package com.hhwy.purchaseweb.settlement.fujian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.purchaseweb.settlement.fujian.domain.FormDataAndCostListFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.RetailDetailFj;
import com.hhwy.purchaseweb.settlement.fujian.domain.SmPurchaseSchemeVoFj;
import com.hhwy.purchaseweb.settlement.fujian.service.SettlementServiceFj;

 /**
 * <b>类 名 称：</b>SettlementControllerFj<br/>
 * <b>类 描 述：</b><br/>		福建结算方案的controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:23:26<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/settlementControllerFj")
public class SettlementControllerFj {

	public static final Logger log = LoggerFactory.getLogger(SettlementControllerFj.class);
	
	/**
	 * smPurchaseSchemeDetailService
	 */
	@Autowired
	private SettlementServiceFj settlementServiceFj;
	
	/**
	 * @Title: getSmSettlementMonthInfoByIdOrYm
	 * @Description: 通过结算id(detail页面)或者年月（新增编辑页面，即add页面）获取结算方案列表页面上面的 form表单中的数据 和 “批发交易结算明细”列表数据
	 * 						(若是结算id则当月已有结算数据，则取结算数据；若是ym当月无论有没有结算数据，则组装初始化的结算数据)
	 * 				福建结算  新增或编辑、详情页面会调用此方法
	 * @param settleId
	 * @param ym
	 * @return Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月27日 上午10:00:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月27日 上午10:00:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getFormAndCostDataByIdOrYm", method = RequestMethod.GET)
	public Object getSmSettlementMonthInfoByIdOrYm(String settleId, String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			FormDataAndCostListFj formDataDetailFj = null;
			if(settleId == null || "".equals(settleId)) {
				formDataDetailFj = settlementServiceFj.getFormDataAndCostListFjByYm(ym);
			}else {
				formDataDetailFj = settlementServiceFj.getFormDataAndCostListFjBySettleId(settleId);
			}
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(formDataDetailFj);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况s修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			}
		}
		return result;
	}
	
	/**
	 * @Title: getRetailDetailByYm
	 * @Description: 	根据年月获取当月“零售市场售电收入明细”列表
	 * 					(若参数是结算id则是详情页面调用，当月已有结算数据，则取结算数据；
	 * 						若参数是ym，则是新增或编辑页面调用，当月若没有结算数据，则组装初始化的结算数据，若有结算数据，则结合结算数据组装初始化数据)
	 * 				福建结算  新增或编辑、详情页面的 零售市场售电收入明细 表数据
	 * @param ym
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月17日 下午5:31:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月17日 下午5:31:17
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getRetailDetailByIdOrYm", method = RequestMethod.GET)
	public Object getRetailDetailByIdOrYm(String settleId, String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<RetailDetailFj> list = null;
			if(settleId == null || "".equals(settleId)) {
				list = settlementServiceFj.getRetailDetailByYm(ym);
			}else {
				list = settlementServiceFj.getRetailDetailBySettledId(settleId);
			}
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
	 * @Description: 福建的 新增或修改 结算数据
	 * @param smPurchaseSchemeVoFj
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:05:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:05:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveSmPurchaseSchemeVo", method = RequestMethod.POST)
	public Object saveSmPurchaseSchemeVo(@RequestBody SmPurchaseSchemeVoFj smPurchaseSchemeVoFj) {
		ExecutionResult result = new ExecutionResult();
		try {
			settlementServiceFj.saveOrUpdateSmPurchaseSchemeVoFj(smPurchaseSchemeVoFj);
			result.setCode(ReturnCode.RES_SUCCESS);
			result.setData(smPurchaseSchemeVoFj);
			result.setFlag(true);
			result.setMsg("方案保存成功！");
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());
			}else{
				result.setMsg("方案保存失败！");
			}
			log.error("方案保存失败",e);
			result.setCode(ReturnCode.RES_FAILED);
			result.setFlag(false);
		}
		return result;
	}
	
	/**
	 * @Title: deleteSmSettlementMonth
	 * @Description: 先验证是否已填写发票信息，如果没有则删除一个月度结算的所有相关信息
	 * @param settleId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月23日 下午1:33:41
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月23日 下午1:33:41
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/{settleId}", method = RequestMethod.DELETE)
	public Object deleteSmSettlementMonth(@PathVariable String settleId) {
		ExecutionResult result = new ExecutionResult();
		try {
			settlementServiceFj.deleteSettlementInfoFj(settleId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setMsg(e.getMessage());				//设置返回消息，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
		}
		return result;
	}	
	
}
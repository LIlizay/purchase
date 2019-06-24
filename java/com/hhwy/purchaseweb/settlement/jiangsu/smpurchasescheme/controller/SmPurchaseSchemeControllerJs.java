package com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.controller;

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
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.service.SmPurchaseSchemeServiceJs;

/**
 * <b>类 名 称：</b>SmPurchaseSchemeControllerJs<br/>
 * <b>类 描 述：</b><br/>		江苏的月度购电结算方案 controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月30日 下午4:16:59<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/smPurchaseSchemeControllerJs")
public class SmPurchaseSchemeControllerJs {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeControllerJs.class);
	
	/**
	 * smPurchaseSchemeServiceJs			江苏结算方案的service
	 */
	@Autowired
	private SmPurchaseSchemeServiceJs smPurchaseSchemeServiceJs;
	
	/**
	 * @Title: getSmPurchaseSchemeVoBySchemeId
	 * @Description: 江苏的根据方案id获取结算结果（包括 方案详情实体类列表、售电公司结算信息、用户结算信息（零售市场售电收入明细）、批发市场购电支出明细）
	 * 				因为前台弹框的参数是“方案实体对象”，所以在这里就不取“方案实体对象”了
	 * @param schemeId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月31日 下午8:31:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月31日 下午8:31:03
	 * @since  1.0.0
	 */
	@RequestMapping(value = "getSmPurchaseSchemeVoBySchemeId/{schemeId}", method = RequestMethod.GET)
	public Object getSmPurchaseSchemeVoBySchemeId(@PathVariable String schemeId) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPurchaseSchemeVoJs smPurchaseSchemeVoJs = smPurchaseSchemeServiceJs.getSmPurchaseSchemeVoBySchemeId(schemeId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smPurchaseSchemeVoJs);			//设置数据实体
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
	
	/**
	 * @Title: saveSmPurchaseSchemeVo
	 * @Description: 江苏的新增或修改计划下的方案,可能还包含结算主表的新增。
	 * 			包含：方案，方案详情列表，售电公司结算信息，用户结算信息列表（零售市场售电收入明细）
	 * 					，批发市场购电支出明细，月度结算用户偏差惩罚费用信息 列表
	 * @param smPurchaseSchemeVoJs
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午5:00:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午5:00:40
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveSmPurchaseSchemeVo", method = RequestMethod.POST)
	public Object saveSmPurchaseSchemeVo(@RequestBody SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeServiceJs.saveOrUpdateSmPurchaseSchemeVoJs(smPurchaseSchemeVoJs);
			result.setCode(ReturnCode.RES_SUCCESS);
			result.setData(smPurchaseSchemeVoJs);
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
	 * @Title: getConsInfoBySchemeId
	 * @Description: 江苏结算中 根据方案id删除 方案、方案详情信息列表、公司收益信息、
	 * 					用户收益信息列表（零售市场售电收入明细），批发市场购电支出明细
	 * @param schemeIds
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午5:00:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午5:00:17
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/deleteSettlementinfo", method = RequestMethod.POST)
	public Object getConsInfoBySchemeId(@RequestBody String[] schemeIds) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeServiceJs.deleteAllSettlementInfo(schemeIds);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);					
			result.setMsg("方案删除成功！");					
		} catch (Exception e) {
			log.error("方案删除失败",e);						
			result.setCode(ReturnCode.RES_FAILED);		
			result.setFlag(false);						
			result.setMsg("方案删除失败！");					
		}
		return result;
	}
}
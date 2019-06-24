package com.hhwy.purchaseweb.settlement.base.smpurchasescheme.controller;

import java.math.BigDecimal;
import java.util.Map;

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
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeView;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeVo;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.SmPurchaseSchemeService;

/**
 * SmPurchaseSchemeController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smPurchaseSchemeController")
public class SmPurchaseSchemeController {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeController.class);
	
	/**
	 * smPurchaseSchemeService	月度结算方案service
	 */
	@Autowired
	private SmPurchaseSchemeService smPurchaseSchemeService;
	
	
	/**
	 * @Title: getSmPurchaseSchemeByPage
	 * @Description: 根据结算id(settleId)获取对象SmPurchaseSchemeView列表
	 * @param smPurchaseSchemeVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午11:31:02
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午11:31:02
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmPurchaseSchemeByPage(@RequestBody(required=false) SmPurchaseSchemeVo smPurchaseSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			if(smPurchaseSchemeVo.getSmPurchaseScheme().getSettleId() == null || 
					"".equals(smPurchaseSchemeVo.getSmPurchaseScheme().getSettleId())) {
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setTotal(0);	//设置数据总条数
				result.setRows(new Object[] {});		//设置数据列表
				result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
			}else {
				QueryResult<SmPurchaseSchemeView> queryResult = smPurchaseSchemeService.getSmPurchaseSchemeByPage(smPurchaseSchemeVo);
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setTotal(queryResult.getTotal());	//设置数据总条数
				result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
				result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
			}
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SmPurchaseScheme
	 * @param ID
	 * @return SmPurchaseScheme
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmPurchaseSchemeById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPurchaseScheme smPurchaseScheme = smPurchaseSchemeService.getSmPurchaseSchemeById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smPurchaseScheme);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	/**
	 * 添加对象SmPurchaseScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmPurchaseScheme(@RequestBody SmPurchaseScheme smPurchaseScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeService.saveSmPurchaseScheme(smPurchaseScheme);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象SmPurchaseScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmPurchaseScheme(@RequestBody SmPurchaseScheme smPurchaseScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeService.updateSmPurchaseScheme(smPurchaseScheme);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象SmPurchaseScheme
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmPurchaseScheme(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeService.deleteSmPurchaseScheme(new String[]{id});
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
	
	/**
	 * @Title: submitSchemeById
	 * @Description:  归档月度购电计划
	 * @param schemeId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午1:24:51
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午1:24:51
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/submitSchemeById/{schemeId}", method = RequestMethod.GET)
	public Object submitSchemeById(@PathVariable String schemeId) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPurchaseSchemeService.submitSchemeById(schemeId);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);	
			result.setMsg("方案归档成功！");					
		} catch (Exception e) {
			log.error("方案归档失败",e);						
			result.setCode(ReturnCode.RES_FAILED);		
			result.setFlag(false);						
			result.setMsg(e.getMessage());					
		}
		return result;
	}
	
	/**
	 * @Title: getLcBidListedAvgPrcByYm
	 * @Description: 根据年月获取 双边、竞价、挂牌三个加权平均价
	 * @param ym
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午4:15:57
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午4:15:57
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getLcBidListedAvgPrcByYm", method = RequestMethod.GET)
	public Object getLcBidListedAvgPrcByYm(String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String, BigDecimal> avgPrc = smPurchaseSchemeService.getLcBidListedAvgPrcByYm(ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(avgPrc);			//设置数据实体
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
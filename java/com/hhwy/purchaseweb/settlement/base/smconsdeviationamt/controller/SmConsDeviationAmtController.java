package com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.controller;

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
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmConsDeviationAmt;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtDetail;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtVo;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.service.SmConsDeviationAmtService;

/**
 * SmConsDeviationAmtController
 * @author hhwy 		用户结算偏差费用信息 的controller
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smConsDeviationAmtController")
public class SmConsDeviationAmtController {

	public static final Logger log = LoggerFactory.getLogger(SmConsDeviationAmtController.class);
	
	/**
	 * smConsDeviationAmtService 用户结算偏差费用信息 service
	 */
	@Autowired
	private SmConsDeviationAmtService smConsDeviationAmtService;
	
	
	/**
	 * 分页获取对象SmConsDeviationAmt
	 * @param ID
	 * @return SmConsDeviationAmt
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmConsDeviationAmtByPage(@RequestBody(required=false) SmConsDeviationAmtVo smConsDeviationAmtVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmConsDeviationAmt> queryResult = smConsDeviationAmtService.getSmConsDeviationAmtByPage(smConsDeviationAmtVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SmConsDeviationAmt
	 * @param ID
	 * @return SmConsDeviationAmt
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmConsDeviationAmtById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmConsDeviationAmt smConsDeviationAmt = smConsDeviationAmtService.getSmConsDeviationAmtById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smConsDeviationAmt);			//设置数据实体
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
	 * 添加对象SmConsDeviationAmt
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmConsDeviationAmt(@RequestBody SmConsDeviationAmt smConsDeviationAmt) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsDeviationAmtService.saveSmConsDeviationAmt(smConsDeviationAmt);
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
	 * 更新对象SmConsDeviationAmt
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmConsDeviationAmt(@RequestBody SmConsDeviationAmt smConsDeviationAmt) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsDeviationAmtService.updateSmConsDeviationAmt(smConsDeviationAmt);
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
	 * 删除对象SmConsDeviationAmt
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmConsDeviationAmt(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsDeviationAmtService.deleteSmConsDeviationAmt(new String[]{id});
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * @Title: getConsDeviAmtByYmOrSchemeId
	 * @Description: 根据计划id或年月获取当月所有合同用户偏差费用计算相关信息
	 * 				用户返回结算方案新增或编辑页面的 用户偏差费用计算表数据
	 * @param ym
	 * @param schemeId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午2:12:32
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午2:12:32
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getConsDeviAmtByYmOrSchemeId", method = RequestMethod.GET)
	public Object getConsDeviAmtByYmOrSchemeId(String ym, String schemeId) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<SmConsDeviationAmtDetail> list = null;
			if(schemeId != null && !"".equals(schemeId.trim())){
				list=smConsDeviationAmtService.getConsDeviAmtBySchemeId(schemeId);
			}else if(ym != null && !"".equals(ym.trim())){
				list=smConsDeviationAmtService.getConsDeviAmtByYm(ym);
			}
			result.setRows(list);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);					
			result.setMsg("用户偏差费用信息获取成功！");
		} catch (Exception e) {
			log.error("用户偏差费用信息获取失败",e);
			result.setCode(ReturnCode.RES_FAILED);		
			result.setFlag(false);
			result.setMsg("用户偏差费用信息获取失败！");					
		}
		return result;
	}
	
	/**
	 * @Title: calculateDeviationCheckAmt
	 * @Description: 计算单个用户的 考核费用
	 * @param smConsDeviationAmtDetail
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午4:55:44
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午4:55:44
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/calculateDeviationCheckAmt", method = RequestMethod.POST)
	public Object calculateDeviationCheckAmt(@RequestBody SmConsDeviationAmtDetail smConsDeviationAmtDetail) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsDeviationAmtService.calculateDeviationCheckAmt(smConsDeviationAmtDetail);
			result.setData(smConsDeviationAmtDetail);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("计算考核费用成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("计算考核费用失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
		}
		return result;
	}
}
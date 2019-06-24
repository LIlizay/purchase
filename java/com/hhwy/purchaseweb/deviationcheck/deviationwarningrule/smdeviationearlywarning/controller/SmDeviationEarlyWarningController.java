package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.controller;

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
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningVo;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.service.SmDeviationEarlyWarningService;
import com.hhwy.selling.domain.SmDeviationEarlyWarning;

/**
 * SmDeviationEarlyWarningController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smDeviationEarlyWarningController")
public class SmDeviationEarlyWarningController {

	public static final Logger log = LoggerFactory.getLogger(SmDeviationEarlyWarningController.class);
	
	/**
	 * smDeviationEarlyWarningService
	 */
	@Autowired
	private SmDeviationEarlyWarningService smDeviationEarlyWarningService;
	
	
	/**
	 * 分页获取对象SmDeviationEarlyWarning
	 * @param ID
	 * @return SmDeviationEarlyWarning
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmDeviationEarlyWarningByPage(@RequestBody(required=false) SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmDeviationEarlyWarningDetail> queryResult = smDeviationEarlyWarningService.getSmDeviationEarlyWarningByPage(smDeviationEarlyWarningVo);
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
	 * 根据ID获取对象SmDeviationEarlyWarning
	 * @param ID
	 * @return SmDeviationEarlyWarning
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmDeviationEarlyWarningById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmDeviationEarlyWarningVo smDeviationEarlyWarningVo = smDeviationEarlyWarningService.getSmDeviationEarlyWarningById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smDeviationEarlyWarningVo);			//设置数据实体
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
	 * 添加对象SmDeviationEarlyWarning
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmDeviationEarlyWarning(@RequestBody SmDeviationEarlyWarning smDeviationEarlyWarning) {
		ExecutionResult result = new ExecutionResult();
		try {
			smDeviationEarlyWarningService.saveSmDeviationEarlyWarning(smDeviationEarlyWarning);
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
	 * 更新对象SmDeviationEarlyWarning
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmDeviationEarlyWarning(@RequestBody SmDeviationEarlyWarning smDeviationEarlyWarning) {
		ExecutionResult result = new ExecutionResult();
		try {
			smDeviationEarlyWarningService.updateSmDeviationEarlyWarning(smDeviationEarlyWarning);
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
	 * 删除对象SmDeviationEarlyWarning
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSmDeviationEarlyWarning(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smDeviationEarlyWarningService.deleteSmDeviationEarlyWarning(ids);
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
	 * 
	 * @Title: saveSmDeviationEarlyWarning<br/>
	 * @Description: 保存预警规则<br/>
	 * @param smDeviationEarlyWarningVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午7:24:25
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午7:24:25
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public Object saveSmDeviationEarlyWarning(@RequestBody  SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			smDeviationEarlyWarningService.saveDeviationRuleList(smDeviationEarlyWarningVo);
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
	 * 
	 * @Title: updateDeviationRuleList<br/>
	 * @Description: 更新预警规则<br/>
	 * @param smDeviationEarlyWarningVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午9:53:12
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午9:53:12
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
	public Object updateDeviationRuleList(@RequestBody  SmDeviationEarlyWarningVo smDeviationEarlyWarningVo){
		ExecutionResult result = new ExecutionResult();
		try {
			smDeviationEarlyWarningService.updateDeviationRuleList(smDeviationEarlyWarningVo);
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
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Object getCountByStatus(){
		try {
			return smDeviationEarlyWarningService.getCountByStatus();
		} catch (Exception e) {
			log.error("查询失败",e);
			return new Integer(-1);
		}
	}
}
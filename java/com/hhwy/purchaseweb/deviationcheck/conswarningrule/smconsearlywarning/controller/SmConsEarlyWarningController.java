package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.controller;

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
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningVo;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.service.SmConsEarlyWarningService;
import com.hhwy.selling.domain.SmConsEarlyWarning;

/**
 * SmConsEarlyWarningController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smConsEarlyWarningController")
public class SmConsEarlyWarningController {

	public static final Logger log = LoggerFactory.getLogger(SmConsEarlyWarningController.class);
	
	/**
	 * smConsEarlyWarningService
	 */
	@Autowired
	private SmConsEarlyWarningService smConsEarlyWarningService;
	
	
	/**
	 * 分页获取对象SmConsEarlyWarning
	 * @param ID
	 * @return SmConsEarlyWarning
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmConsEarlyWarningByPage(@RequestBody(required=false) SmConsEarlyWarningVo smConsEarlyWarningVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmConsEarlyWarningDetail> queryResult = smConsEarlyWarningService.getSmConsEarlyWarningByPage(smConsEarlyWarningVo);
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
	 * 根据ID获取对象SmConsEarlyWarning
	 * @param ID
	 * @return SmConsEarlyWarning
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmConsEarlyWarningById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmConsEarlyWarningVo smConsEarlyWarningVo = smConsEarlyWarningService.getSmConsEarlyWarningById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smConsEarlyWarningVo);			//设置数据实体
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
	 * 添加对象SmConsEarlyWarning
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmConsEarlyWarning(@RequestBody SmConsEarlyWarning smConsEarlyWarning) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsEarlyWarningService.saveSmConsEarlyWarning(smConsEarlyWarning);
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
	 * 更新对象SmConsEarlyWarning
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmConsEarlyWarning(@RequestBody SmConsEarlyWarning smConsEarlyWarning) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsEarlyWarningService.updateSmConsEarlyWarning(smConsEarlyWarning);
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
	 * 删除对象SmConsEarlyWarning
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSmConsEarlyWarning(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsEarlyWarningService.deleteSmConsEarlyWarning(ids);
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
	 * @Title: saveConsRule<br/>
	 * @Description: 保存用户规则<br/>
	 * @param smConsEarlyWarningVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:34:59
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:34:59
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public Object saveConsRule(@RequestBody SmConsEarlyWarningVo smConsEarlyWarningVo){
		ExecutionResult result = new ExecutionResult();
		try {
			smConsEarlyWarningService.saveConsRule(smConsEarlyWarningVo);
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
	 * @Title: updateConsRule<br/>
	 * @Description: 更新用户规则<br/>
	 * @param smConsEarlyWarningVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:36:34
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:36:34
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
	public Object updateConsRule(@RequestBody SmConsEarlyWarningVo smConsEarlyWarningVo){
		ExecutionResult result = new ExecutionResult();
		try {
			smConsEarlyWarningService.updateConsRule(smConsEarlyWarningVo);
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
	 * 
	 * @Title: getConsEarlyWarningByConsId<br/>
	 * @Description: 判断用户是否存在规则<br/>
	 * @param consId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月22日 下午3:23:52
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月22日 下午3:23:52
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getDataByConsId", method = RequestMethod.POST)
	public Object getConsEarlyWarningByConsId(@RequestBody String consId){
		ExecutionResult result = new ExecutionResult();
		try {
			SmConsEarlyWarningVo smConsEarlyWarningVo = smConsEarlyWarningService.getConsEarlyWarningByConsId(consId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smConsEarlyWarningVo);			//设置数据实体
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
	 * @Title: getDefaultRule<br/>
	 * @Description: 查询默认规则<br/>
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月31日 下午4:37:18
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月31日 下午4:37:18
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getDefaultRule", method = RequestMethod.POST)
	public Object getDefaultRule(){
		ExecutionResult result = new ExecutionResult();
		try {
			SmConsEarlyWarningVo smConsEarlyWarningVo = smConsEarlyWarningService.getDefaultRule();
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smConsEarlyWarningVo);			//设置数据实体
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
}
package com.hhwy.purchaseweb.contract.smagretemplate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateDetail;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateVo;
import com.hhwy.purchaseweb.contract.smagretemplate.service.SmAgreTemplateService;
import com.hhwy.selling.domain.SmAgreTemplate;

/**
 * SmAgreTemplateController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smAgreTemplateController")
public class SmAgreTemplateController {

	public static final Logger log = LoggerFactory.getLogger(SmAgreTemplateController.class);
	
	/**
	 * smAgreTemplateService
	 */
	@Autowired
	private SmAgreTemplateService smAgreTemplateService;
	
	
	/**
	 * 分页获取对象SmAgreTemplate
	 * @param ID
	 * @return SmAgreTemplate
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmAgreTemplateByPage(@RequestBody(required=false) SmAgreTemplateVo smAgreTemplateVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmAgreTemplateDetail> queryResult = smAgreTemplateService.getSmAgreTemplateByPage(smAgreTemplateVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
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
	 * 根据ID获取对象SmAgreTemplate
	 * @param ID
	 * @return SmAgreTemplate
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmAgreTemplateById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmAgreTemplate smAgreTemplate = smAgreTemplateService.getSmAgreTemplateById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smAgreTemplate);			//设置数据实体
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
	 * 获取下拉list
	 * getSelect(描述这个方法的作用)<br/>
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getSelect", method = RequestMethod.GET)
    public Object getSelect(@RequestParam String type) {
	    return smAgreTemplateService.getSelect(type);
	}
	
	/**
	 * 添加对象SmAgreTemplate
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmAgreTemplate(@RequestBody SmAgreTemplate smAgreTemplate) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreTemplateService.saveSmAgreTemplate(smAgreTemplate);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
		
	}
	
	/**
	 * 模板生效
	 * effect(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
//	@RequestMapping(value = "/effect/{id}", method = RequestMethod.POST)
//    public Object effect(@PathVariable String id) {
//        ExecutionResult result = new ExecutionResult();
//        try {
//            smAgreTemplateService.effect(id);
//            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
//            result.setFlag(true);                       //设置是否成功标识
//            result.setMsg("生效成功！");                 //设置返回消息，根据实际情况修改
//        } catch (Exception e) {
//            log.error("生效失败",e);                        //记录异常日志，根据实际情况修改
//            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
//            result.setFlag(false);                      //设置是否成功标识
//            result.setMsg("生效失败！");                 //设置返回消息，根据实际情况修改
//        }
//        return result;
//        
//    }
//	
	/**
     * 模板失效
     * invalid(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
//    @RequestMapping(value = "/invalid/{id}", method = RequestMethod.POST)
//    public Object invalid(@PathVariable String id) {
//        ExecutionResult result = new ExecutionResult();
//        try {
//            smAgreTemplateService.invalid(id);
//            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
//            result.setFlag(true);                       //设置是否成功标识
//            result.setMsg("失效成功！");                 //设置返回消息，根据实际情况修改
//        } catch (Exception e) {
//            log.error("失效失败",e);                        //记录异常日志，根据实际情况修改
//            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
//            result.setFlag(false);                      //设置是否成功标识
//            result.setMsg("失效失败！");                 //设置返回消息，根据实际情况修改
//        }
//        return result;
//        
//    }
    
	/**
	 * 更新对象SmAgreTemplate
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmAgreTemplate(@RequestBody SmAgreTemplate smAgreTemplate) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreTemplateService.updateSmAgreTemplate(smAgreTemplate);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 删除对象SmAgreTemplate
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmAgreTemplate(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreTemplateService.deleteSmAgreTemplate(new String[]{id});
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
}
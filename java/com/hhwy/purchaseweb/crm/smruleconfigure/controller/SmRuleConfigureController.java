package com.hhwy.purchaseweb.crm.smruleconfigure.controller;

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
import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.crm.smruleconfigure.domain.SmRuleConfigureVo;
import com.hhwy.purchaseweb.crm.smruleconfigure.service.SmRuleConfigureService;

/**
 * SmRuleConfigureController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smRuleConfigureController")
public class SmRuleConfigureController {

	public static final Logger log = LoggerFactory.getLogger(SmRuleConfigureController.class);
	
	/**
	 * smRuleConfigureService
	 */
	@Autowired
	private SmRuleConfigureService smRuleConfigureService;
	
	
	/**
	 * 分页获取对象SmRuleConfigure
	 * @param ID
	 * @return SmRuleConfigure
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmRuleConfigureByPage(@RequestBody(required=false) SmRuleConfigureVo smRuleConfigureVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmRuleConfigure> queryResult = smRuleConfigureService.getSmRuleConfigureByPage(smRuleConfigureVo);
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
	 * 根据ID获取对象SmRuleConfigure
	 * @param ID
	 * @return SmRuleConfigure
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmRuleConfigureById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmRuleConfigure smRuleConfigure = smRuleConfigureService.getSmRuleConfigureById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smRuleConfigure);			//设置数据实体
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
	 * 添加对象SmRuleConfigure
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmRuleConfigure(@RequestBody SmRuleConfigure smRuleConfigure) {
		ExecutionResult result = new ExecutionResult();
		try {
		    smRuleConfigureService.saveSmRuleConfigure(smRuleConfigure);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smRuleConfigure);
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
	 * 更新对象SmRuleConfigure
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmRuleConfigure(@RequestBody SmRuleConfigure smRuleConfigure) {
		ExecutionResult result = new ExecutionResult();
		try {
			smRuleConfigureService.updateSmRuleConfigure(smRuleConfigure);
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
	 * 删除对象SmRuleConfigure
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmRuleConfigure(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smRuleConfigureService.deleteSmRuleConfigure(new String[]{id});
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
	 * 获取资源树
	 * getTree(描述这个方法的作用)<br/>
	 * @param nodeType
	 * @param provinceId
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
    public Object getTree(@RequestParam String nodeType,@RequestParam String provinceId) {
        try {
            return smRuleConfigureService.getTree(nodeType, provinceId);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            return new Object[]{};
        }
    }  
	
	/**
	 * 规则生效
	 * effect(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/effect/{provinceId}/{id}", method = RequestMethod.POST)
    public Object effect(@PathVariable String provinceId,@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            result = smRuleConfigureService.effect(provinceId,id);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("生效失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("生效失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }   
	
	/**
	 * 失效规则
	 * invalid(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = "/invalid/{id}", method = RequestMethod.POST)
    public Object invalid(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            smRuleConfigureService.invalid(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("失效成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("失效失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("失效失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }   
}
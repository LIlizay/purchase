package com.hhwy.purchaseweb.agreement.phmagretemplate.controller;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmAgreTemplate;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateDetail;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateVo;
import com.hhwy.purchaseweb.agreement.phmagretemplate.service.PhmAgreTemplateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmAgreTemplateController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmAgreTemplateController")
public class PhmAgreTemplateController {

	public static final Logger log = LoggerFactory.getLogger(PhmAgreTemplateController.class);
	
	/**
	 * phmAgreTemplateService
	 */
	@Autowired
	private PhmAgreTemplateService phmAgreTemplateService;
	
	
	/**
	 * 分页获取对象PhmAgreTemplate
	 * @param ID
	 * @return PhmAgreTemplate
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmAgreTemplateByPage(@RequestBody(required=false) PhmAgreTemplateVo phmAgreTemplateVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmAgreTemplateDetail> queryResult = phmAgreTemplateService.getPhmAgreTemplateByPage(phmAgreTemplateVo);
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
	 * 根据ID获取对象PhmAgreTemplate
	 * @param ID
	 * @return PhmAgreTemplate
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmAgreTemplateById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmAgreTemplate phmAgreTemplate = phmAgreTemplateService.getPhmAgreTemplateById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmAgreTemplate);			//设置数据实体
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
	 * 添加对象PhmAgreTemplate
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmAgreTemplate(@RequestBody PhmAgreTemplate phmAgreTemplate) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmAgreTemplateService.savePhmAgreTemplate(phmAgreTemplate);
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
	 * 模板生效
	 * savePhmAgreTemplate(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
			//暂时去掉
/*    @RequestMapping( value = "effect/{id}",method = RequestMethod.POST)
    public Object effect(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmAgreTemplateService.effect(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("生效成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("生效失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("生效失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }*/
    
    /**
     * 模板失效
     * savePhmAgreTemplate(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
			//暂时去掉    
    /*@RequestMapping( value = "invalid/{id}",method = RequestMethod.POST)
    public Object savePhmAgreTemplate(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmAgreTemplateService.invalid(id);
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
        
    }*/
    
	/**
	 * 更新对象PhmAgreTemplate
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmAgreTemplate(@RequestBody PhmAgreTemplate phmAgreTemplate) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmAgreTemplateService.updatePhmAgreTemplate(phmAgreTemplate);
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
	 * 删除对象PhmAgreTemplate
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmAgreTemplate(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmAgreTemplateService.deletePhmAgreTemplate(new String[]{id});
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
}
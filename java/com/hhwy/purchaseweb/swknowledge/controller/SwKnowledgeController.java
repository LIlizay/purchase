package com.hhwy.purchaseweb.swknowledge.controller;

import javax.servlet.http.HttpServletRequest;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledge;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeDetail;
import com.hhwy.purchaseweb.swknowledge.domain.SwKnowledgeVo;
import com.hhwy.purchaseweb.swknowledge.service.SwKnowledgeService;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SwHtmlInfoController
 * @author LiXinze
 * @date 2017-11-8
 * @version 1.0
 */
@RestController
@RequestMapping("/swKnowledgeController")
public class SwKnowledgeController {

	public static final Logger log = LoggerFactory.getLogger(SwKnowledgeController.class);
	
	/**
	 * swKnowledgeService
	 */
	@Autowired
	private SwKnowledgeService swKnowledgeService;
	
	
	/**
	 * 分页获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSwHtmlInfoByPage(@RequestBody(required=false) SwKnowledgeVo snKnowledgeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SwKnowledgeDetail> queryResult = swKnowledgeService.getSwKnowledgeByPage(snKnowledgeVo);
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
			return result;
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSwHtmlInfoById(@PathVariable String id,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwKnowledgeDetail swHtmlInfoDetail = swKnowledgeService.getSwKnowledgeById(id,request);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swHtmlInfoDetail);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 添加对象Knowledge
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveKnowledge(@RequestBody SwKnowledgeVo knowledgeVo,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwKnowledge KnowledgeResult = swKnowledgeService.saveSwKnowledge(knowledgeVo,request);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(KnowledgeResult);	        //设置返回结果数据
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SwKnowledge());	        //设置返回结果数据
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象Knowledge
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateKnowledge(@RequestBody SwKnowledgeVo swknowledgeVo,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			swKnowledgeService.updateSwKnowledge(swknowledgeVo,request);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象Knowledge
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteKnowledge(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			swKnowledgeService.deleteSwKnowledge(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	
}
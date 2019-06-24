package com.hhwy.purchaseweb.ssfeedback.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackDetail;
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackVo;
import com.hhwy.purchaseweb.ssfeedback.service.SsFeedbackService;
import com.hhwy.selling.domain.SsFeedback;

/**
 * SsFeedbackController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/ssFeedbackController")
public class SsFeedbackController {

	public static final Logger log = LoggerFactory.getLogger(SsFeedbackController.class);
	
	/**
	 * ssFeedbackService
	 */
	@Autowired
	private SsFeedbackService ssFeedbackService;
	
	
	/**
	 * 查看用户满意程度码表
	 */
	@RequestMapping(value = "/getConsSatisfaction", method = RequestMethod.GET)
	public Object getConsSatisfaction(){
		ExecutionResult result = new ExecutionResult();
		try {
			List<Map<String, Object>> consSatisfaction = ssFeedbackService.getConsSatisfaction();
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setData(consSatisfaction);
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("查看用户满意程度码表成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查看用户满意程度码表失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setData(null);
			result.setMsg("查看用户满意程度码表失败！"+e.getMessage()); // 设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	
	/**
	 * 分页获取对象SsFeedback
	 * @param ID
	 * @return SsFeedback
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年3月21日 下午5:19:37
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年3月21日 下午5:19:37
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSsFeedbackByPage(@RequestBody(required=false) SsFeedbackVo ssFeedbackVo ,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			String url = request.getServerName();//用于匹配数据库 权限过滤
			QueryResult<SsFeedbackDetail> queryResult = ssFeedbackService.getSsFeedbackByPage(ssFeedbackVo, url);
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
	 * 根据ID获取对象SsFeedback
	 * @param ID
	 * @return SsFeedback
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSsFeedbackById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SsFeedback ssFeedback = ssFeedbackService.getSsFeedbackById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(ssFeedback);					//设置数据实体
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
	 * 添加对象SsFeedback(提交按钮)
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object saveSsFeedback(@RequestBody SsFeedback ssFeedback,HttpServletRequest httpServletRequest) {
		ExecutionResult result = new ExecutionResult();
		try {
			String serverName = httpServletRequest.getServerName();//用于查看时权限过滤
			ssFeedbackService.saveSsFeedback(ssFeedback, serverName);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("提交成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("提交失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("提交失败！"+e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象SsFeedback(反馈回复更新)
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Object updateSsFeedback(@RequestBody SsFeedback ssFeedback) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssFeedbackService.updateSsFeedback(ssFeedback);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(ssFeedback);					//设置数据实体
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
	 * 删除对象SsFeedback
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSsFeedback(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssFeedbackService.deleteSsFeedback(new String[]{id});
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
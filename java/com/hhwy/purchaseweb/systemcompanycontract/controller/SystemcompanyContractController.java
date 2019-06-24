package com.hhwy.purchaseweb.systemcompanycontract.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractDetail;
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractVo;
import com.hhwy.purchaseweb.systemcompanycontract.service.SystemcompanyContractService;
import com.hhwy.selling.domain.SystemcompanyContract;

/**
 * SystemcompanyContractController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/systemcompanyContractController")
public class SystemcompanyContractController {

	public static final Logger log = LoggerFactory.getLogger(SystemcompanyContractController.class);
	
	/**
	 * systemcompanyContractService
	 */
	@Autowired
	private SystemcompanyContractService systemcompanyContractService;
	
	
	/**
	 * @Title: getSystemcompanyContractByPage<br/>
	 * @Description:平台用户管理主列表
	 * @param systemcompanyContractVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月30日 下午5:24:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月30日 下午5:24:51
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSystemcompanyContractByPage(@RequestBody(required=false) SystemcompanyContractVo systemcompanyContractVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SystemcompanyContractDetail> queryResult = systemcompanyContractService.getSystemcompanyContractByPage(systemcompanyContractVo);
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
	 * @Title: saveSystemcompanyContractList<br/>
	 * @Description:平台用户管理编辑页面保存
	 * @param systemcompanyContractList
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月1日 下午5:13:35
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月1日 下午5:13:35
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	public Object saveSystemcompanyContractList(@RequestBody(required=false) SystemcompanyContractVo systemcompanyContractVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyContractService.saveSystemcompanyContractList(systemcompanyContractVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: exportExcel<br/>
	 * @Description:平台用户明细导出，需求暂定导出全部数据
	 * @param systemcompanyContractVo
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月5日 上午11:10:19
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月5日 上午11:10:19
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public Object exportExcel(HttpServletRequest request, HttpServletResponse response){
		ExecutionResult result = new ExecutionResult();
		try{
			systemcompanyContractService.exportExcel(request,response);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("导出成功！");					//设置返回消息，根据实际情况修改
		}catch (Exception e) {
			log.error("导出失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("导出失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SystemcompanyContract
	 * @param ID
	 * @return SystemcompanyContract
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSystemcompanyContractById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SystemcompanyContract systemcompanyContract = systemcompanyContractService.getSystemcompanyContractById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(systemcompanyContract);			//设置数据实体
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
	 * 添加对象SystemcompanyContract
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSystemcompanyContract(@RequestBody SystemcompanyContract systemcompanyContract) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyContractService.saveSystemcompanyContract(systemcompanyContract);
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
	 * 更新对象SystemcompanyContract
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSystemcompanyContract(@RequestBody SystemcompanyContract systemcompanyContract) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyContractService.updateSystemcompanyContract(systemcompanyContract);
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
	 * 删除对象SystemcompanyContract
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSystemcompanyContract(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyContractService.deleteSystemcompanyContract(new String[]{id});
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
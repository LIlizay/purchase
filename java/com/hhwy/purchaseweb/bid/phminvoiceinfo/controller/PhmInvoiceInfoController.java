package com.hhwy.purchaseweb.bid.phminvoiceinfo.controller;

import com.hhwy.purchaseweb.bid.phminvoiceinfo.service.PhmInvoiceInfoService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoDetail;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmInvoiceInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmInvoiceInfoController")
public class PhmInvoiceInfoController {

	public static final Logger log = LoggerFactory.getLogger(PhmInvoiceInfoController.class);
	
	/**
	 * phmInvoiceInfoService
	 */
	@Autowired
	private PhmInvoiceInfoService phmInvoiceInfoService;
	
	
	/**
	 * 分页获取对象PhmInvoiceInfo
	 * @param ID
	 * @return PhmInvoiceInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmInvoiceInfoByPage(@RequestBody(required=false) PhmInvoiceInfoVo phmInvoiceInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmInvoiceInfoDetail> queryResult = phmInvoiceInfoService.getPhmInvoiceInfoByPage(phmInvoiceInfoVo);
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
	 * 根据ID获取对象PhmInvoiceInfo
	 * @param ID
	 * @return PhmInvoiceInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmInvoiceInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmInvoiceInfo phmInvoiceInfo = phmInvoiceInfoService.getPhmInvoiceInfoById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmInvoiceInfo);			//设置数据实体
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
	 * 添加对象PhmInvoiceInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmInvoiceInfo(@RequestBody PhmInvoiceInfo phmInvoiceInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmInvoiceInfo phmInvoiceInfoData = phmInvoiceInfoService.savePhmInvoiceInfo(phmInvoiceInfo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmInvoiceInfoData);         //设置返回数据
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new PhmInvoiceInfo());         //设置返回数据
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhmInvoiceInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmInvoiceInfo(@RequestBody PhmInvoiceInfo phmInvoiceInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmInvoiceInfo phmInvoiceInfoData = phmInvoiceInfoService.updatePhmInvoiceInfo(phmInvoiceInfo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmInvoiceInfoData);         //设置返回数据
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new PhmInvoiceInfo());         //设置返回数据
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象PhmInvoiceInfo
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmInvoiceInfo(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmInvoiceInfoService.deletePhmInvoiceInfo(new String[]{id});
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
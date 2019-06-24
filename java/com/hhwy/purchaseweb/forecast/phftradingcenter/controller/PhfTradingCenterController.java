package com.hhwy.purchaseweb.forecast.phftradingcenter.controller;

import com.hhwy.purchaseweb.forecast.phftradingcenter.service.PhfTradingCenterService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfTradingCenter;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.forecast.phftradingcenter.domain.PhfTradingCenterVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfTradingCenterController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfTradingCenterController")
public class PhfTradingCenterController {

	public static final Logger log = LoggerFactory.getLogger(PhfTradingCenterController.class);
	
	/**
	 * phfTradingCenterService
	 */
	@Autowired
	private PhfTradingCenterService phfTradingCenterService;
	
	
	/**
	 * 分页获取对象PhfTradingCenter
	 * @param ID
	 * @return PhfTradingCenter
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfTradingCenterByPage(@RequestBody(required=false) PhfTradingCenterVo phfTradingCenterVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfTradingCenter> queryResult = phfTradingCenterService.getPhfTradingCenterByPage(phfTradingCenterVo);
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
	 * 根据ID获取对象PhfTradingCenter
	 * @param ID
	 * @return PhfTradingCenter
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfTradingCenterById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfTradingCenter phfTradingCenter = phfTradingCenterService.getPhfTradingCenterById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfTradingCenter);			//设置数据实体
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
	 * 添加对象PhfTradingCenter
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfTradingCenter(@RequestBody PhfTradingCenter phfTradingCenter) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfTradingCenterService.savePhfTradingCenter(phfTradingCenter);
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
	 * 更新对象PhfTradingCenter
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfTradingCenter(@RequestBody PhfTradingCenter phfTradingCenter) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfTradingCenterService.updatePhfTradingCenter(phfTradingCenter);
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
	 * 删除对象PhfTradingCenter
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfTradingCenter(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			String[] ids=id.split(",");
			phfTradingCenterService.deletePhfTradingCenter(ids);
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
	 * 保存预测模块信息
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
	public Object saveForecastInfo(@RequestBody PhfTradingCenterVo phfTradingCenterVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			String info=phfTradingCenterService.saveForecastInfo(phfTradingCenterVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(info);
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 查询预测模块信息
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getForecast/{id}", method = RequestMethod.GET)
	public Object getForecast(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfTradingCenterVo phfTradingCenterVo = phfTradingCenterService.getForecast(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfTradingCenterVo);			//设置数据实体
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
	 * 更新预测模块信息
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/updateForecastInfo",method = RequestMethod.POST)
	public Object updateForecastInfo(@RequestBody PhfTradingCenterVo phfTradingCenterVo) {
		ExecutionResult result = new ExecutionResult();
		try {
		    String info = phfTradingCenterService.updateForecastInfo(phfTradingCenterVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setData(info);
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
}
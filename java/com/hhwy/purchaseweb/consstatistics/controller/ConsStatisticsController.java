package com.hhwy.purchaseweb.consstatistics.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.consstatistics.domain.ConsstatisticsVo;
import com.hhwy.purchaseweb.consstatistics.service.ConsStatisticsService;

@RestController
@RequestMapping("/consStatisticsController")
public class ConsStatisticsController {
	
	public static final Logger log = LoggerFactory.getLogger(ConsStatisticsController.class);
	@Autowired
	private ConsStatisticsService consStatisticsService;
	
	@RequestMapping(value="/getInitData", method = RequestMethod.GET)
	public Object getInitData() {
		ExecutionResult result = new ExecutionResult();
		try {
			Object data = consStatisticsService.getInitData();
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setData(data);
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 分页获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmPpaByPage(@RequestBody(required=false)ConsstatisticsVo params) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<Map<String, Object>> queryResult = consStatisticsService.getConsDetail(params);
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
}

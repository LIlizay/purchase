package com.hhwy.purchaseweb.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.common.service.CommonService;

/**
 * 
 * <b>类 名 称：</b>CommonController<br/>
 * <b>类 描 述：公共查询类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月19日 下午5:39:44<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/sell_commonController")
@Component("sell_CommonController")
public class CommonController {
	
	public static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 
	 * @Title: getAcUser
	 * @Description:(获取人员列表信息)
	 * @param params
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年7月19日 下午5:45:46
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年7月19日 下午5:45:46
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/acuser", method = RequestMethod.POST)
	public Object getAcUser(@RequestBody Map<String,String> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<Map<String,Object>> queryResult = commonService.getAcUser(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getData());		//设置数据列表
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
	 * @Title: getCurrentTime
	 * @Description: 获取当前系统时间	YYYY-MM-dd HH:mm:ss 格式
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年4月28日 上午10:28:05
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年4月28日 上午10:28:05
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getCurrentTime", method = RequestMethod.GET)
	public Object getCurrentTime() {
		ExecutionResult result = new ExecutionResult();
		try {
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			String newDate=sdf.format(date);
			result.setCode(ReturnCode.RES_SUCCESS);		
			result.setFlag(true);						
			result.setData(newDate);		
			result.setMsg("当前时间查询成功！");			
		} catch (Exception e) {
			log.error("当前时间查询失败",e);				
			result.setFlag(false);						
			result.setRows(new Object[]{});						
			result.setTotal(0);						
			result.setMsg("当前时间查询失败！");		
		}
		return result;
	}
}

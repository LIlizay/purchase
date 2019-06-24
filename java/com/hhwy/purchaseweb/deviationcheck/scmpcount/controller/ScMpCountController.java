package com.hhwy.purchaseweb.deviationcheck.scmpcount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountDetail;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.domain.ScMpCountVo;
import com.hhwy.purchaseweb.deviationcheck.scmpcount.service.ScMpCountService;

 /**
 * <b>类 名 称：</b>ScMpCountController<br/>
 * <b>类 描 述：</b><br/> 表计示数的controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月13日 下午3:31:56<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/scMpCountController")
public class ScMpCountController {

	public static final Logger log = LoggerFactory.getLogger(ScMpCountController.class);
	
	/**
	 * scMpCountService		表计示数的service
	 */
	@Autowired
	private ScMpCountService scMpCountService;
	
	/**
	 * @Title: getScMpCountByPage
	 * @Description: 分页获取对象ScMpCount
	 * @param scMpCountVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午3:33:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午3:33:48
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScMpCountByPage(@RequestBody(required=false) ScMpCountVo scMpCountVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScMpCountDetail> queryResult = scMpCountService.getScMpCountByPage(scMpCountVo);
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
	 * @Title: getScMpInfoById
	 * @Description: 根据计量点ID获取对象ScMpCountList
	 * @param scMpCountVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午4:49:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午4:49:36
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getByMpId", method = RequestMethod.POST)
	public Object getScMpInfoById(@RequestBody(required=false) ScMpCountVo scMpCountVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScMpCountDetail> queryResult =  scMpCountService.getScMpCountListById(scMpCountVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
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
	 * 添加对象ScMpCount
	 * @param 实体对象
	 */
	/**
	 * @Title: saveChangeCountVo
	 * @Description:  统一保存前台对示数的增删改列表，并更新用户的用电计划及偏差预警
	 * @param scMpCountVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月13日 下午6:05:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月13日 下午6:05:17
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveChangeCountVo", method = RequestMethod.POST)
	public Object saveChangeCountVo(@RequestBody ScMpCountVo scMpCountVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scMpCountService.saveChangeCountVo(scMpCountVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			}
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
		}
		return result;
		
	}
	
}
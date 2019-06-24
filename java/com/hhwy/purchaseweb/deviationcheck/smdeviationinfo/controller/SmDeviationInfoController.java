package com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.controller;

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
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain.SmDeviationInfoVo;
import com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.service.SmDeviationInfoService;

/**
 * <b>类 名 称：</b>SmDeviationInfoController<br/>
 * <b>类 描 述：</b><br/>		偏差预警的Controller类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午4:21:52<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/smDeviationInfoController")
public class SmDeviationInfoController {

	public static final Logger log = LoggerFactory.getLogger(SmDeviationInfoController.class);
	
	/**
	 * smDeviationInfoService	偏差预警service
	 */
	@Autowired
	private SmDeviationInfoService smDeviationInfoService;
	
	
	/**
	 * @Title: getSmDeviationInfoByPage
	 * @Description: 分页获取偏差预警对象SmDeviationInfo
	 * @param smDeviationInfoVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午11:25:48
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午11:25:48
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmDeviationInfoByPage(@RequestBody(required=false) SmDeviationInfoVo smDeviationInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmDeviationInfoDetail> queryResult = smDeviationInfoService.getSmDeviationInfoByPage(smDeviationInfoVo);			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
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
	 * 根据ID获取对象SmDeviationInfo
	 * @param ID
	 * @return SmDeviationInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmDeviationInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmDeviationInfoDetail smDeviationInfo = smDeviationInfoService.getSmDeviationInfoDetailById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smDeviationInfo);			//设置数据实体
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
	
}
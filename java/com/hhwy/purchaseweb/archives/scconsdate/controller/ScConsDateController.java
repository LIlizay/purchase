package com.hhwy.purchaseweb.archives.scconsdate.controller;

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
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scconsdate.domain.ScConsDateVo;
import com.hhwy.purchaseweb.archives.scconsdate.service.ScConsDateService;
import com.hhwy.selling.domain.ScConsDate;

 /**
 * <b>类 名 称：</b>ScConsDateController<br/>
 * <b>类 描 述：</b><br/> 用户例日维护Controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月12日 上午11:00:50<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/scConsDateController")
public class ScConsDateController {

	public static final Logger log = LoggerFactory.getLogger(ScConsDateController.class);
	
	/**
	 * scConsDateService		用户例日维护service
	 */
	@Autowired
	private ScConsDateService scConsDateService;
	
	/**
	 * @Title: getScConsDateByPage
	 * @Description: 分页获取对象ScConsDate
	 * @param scConsDateVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:05:16
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:05:16
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScConsDateByPage(@RequestBody(required=false) ScConsDateVo scConsDateVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScConsDate> queryResult = scConsDateService.getScConsDateByPage(scConsDateVo);
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
	 * @Title: saveScConsDate
	 * @Description: 添加对象ScConsDate
	 * @param scConsDate
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:06:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:06:25
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveScConsDate(@RequestBody ScConsDate scConsDate) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsDateService.saveScConsDate(scConsDate);
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
	
	/**
	 * @Title: updateScConsDate
	 * @Description: 更新对象ScConsDate
	 * @param scConsDate
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:06:35
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:06:35
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateScConsDate(@RequestBody ScConsDate scConsDate) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsDateService.updateScConsDate(scConsDate);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			}
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
		}
		return result;
	}
	
	/**
	 * @Title: deleteScConsDate
	 * @Description: 删除对象ScConsDate
	 * @param id
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月12日 上午11:06:46
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月12日 上午11:06:46
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteScConsDate(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			scConsDateService.deleteScConsDate(id);
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
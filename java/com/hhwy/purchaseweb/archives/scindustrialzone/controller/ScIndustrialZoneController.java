package com.hhwy.purchaseweb.archives.scindustrialzone.controller;

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
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneDetail;
import com.hhwy.purchaseweb.archives.scindustrialzone.domain.ScIndustrialZoneVo;
import com.hhwy.purchaseweb.archives.scindustrialzone.service.ScIndustrialZoneService;
import com.hhwy.selling.domain.ScIndustrialZone;

/**
 * 
 * <b>类 名 称：</b>ScIndustrialZoneController<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月22日 下午1:47:08<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/scIndustrialZoneController")
public class ScIndustrialZoneController {

	public static final Logger log = LoggerFactory.getLogger(ScIndustrialZoneController.class);
	
	/**
	 * 园区维护scIndustrialZoneService
	 */
	@Autowired
	private ScIndustrialZoneService scIndustrialZoneService;
	
	
	/**
	 * 
	 * @Title: getScIndustrialZoneByPage
	 * @Description:(分页获取园区维护信息)
	 * @param scIndustrialZoneVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:48:17
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:48:17
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScIndustrialZoneByPage(@RequestBody(required=false) ScIndustrialZoneVo scIndustrialZoneVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScIndustrialZoneDetail> queryResult = scIndustrialZoneService.getScIndustrialZoneByPage(scIndustrialZoneVo);
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
	 * 
	 * @Title: getScIndustrialZoneById
	 * @Description:(根据id获取园区维护对象信息)
	 * @param id
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:48:43
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:48:43
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getScIndustrialZoneById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScIndustrialZone scIndustrialZone = scIndustrialZoneService.getScIndustrialZoneById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scIndustrialZone);			//设置数据实体
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
	 * 
	 * @Title: saveScIndustrialZone
	 * @Description:(添加园区维护信息)
	 * @param scIndustrialZone
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:50:11
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:50:11
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveScIndustrialZone(@RequestBody ScIndustrialZone scIndustrialZone) {
		ExecutionResult result = new ExecutionResult();
		try {
			scIndustrialZoneService.checkScIndustrialZone(scIndustrialZone);
			scIndustrialZoneService.saveScIndustrialZone(scIndustrialZone);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！"+e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 
	 * @Title: updateScIndustrialZone
	 * @Description:(更新园区维护信息)
	 * @param scIndustrialZone
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:50:56
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:50:56
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateScIndustrialZone(@RequestBody ScIndustrialZone scIndustrialZone) {
		ExecutionResult result = new ExecutionResult();
		try {
			scIndustrialZoneService.checkScIndustrialZone(scIndustrialZone);
			scIndustrialZoneService.updateScIndustrialZone(scIndustrialZone);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！" +e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: deleteScIndustrialZone
	 * @Description:(根据id数组删除园区维护信息)
	 * @param ids
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年5月22日 下午1:51:35
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年5月22日 下午1:51:35
	 * @since  1.0.0
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteScIndustrialZone(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			scIndustrialZoneService.deleteScIndustrialZone(ids);
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
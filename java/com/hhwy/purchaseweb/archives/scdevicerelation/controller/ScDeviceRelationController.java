package com.hhwy.purchaseweb.archives.scdevicerelation.controller;

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

import com.hhhwy.common.property.EnvPropertyConfigHelper;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.collectionplatform.all.domain.DeviceDetail;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationDetail;
import com.hhwy.purchaseweb.archives.scdevicerelation.domain.ScDeviceRelationVo;
import com.hhwy.purchaseweb.archives.scdevicerelation.service.ScDeviceRelationService;
import com.hhwy.selling.domain.ScDeviceRelation;

/**
 * ScDeviceRelationController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/scDeviceRelationController")
public class ScDeviceRelationController {

	public static final Logger log = LoggerFactory.getLogger(ScDeviceRelationController.class);
	
	/**
	 * scDeviceRelationService
	 */
	@Autowired
	private ScDeviceRelationService scDeviceRelationService;
	
	
	/**
	 * 分页获取对象ScDeviceRelation
	 * @param ID
	 * @return ScDeviceRelation
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScDeviceRelationByPage(@RequestBody(required=false) ScDeviceRelationVo scDeviceRelationVo, HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			String doamin = request.getServerName();//域名
			String platformCode = EnvPropertyConfigHelper.getValue("fweb.frontserver.platform");//平台编码，
			QueryResult<ScDeviceRelationDetail> queryResult = scDeviceRelationService.getScDeviceRelationByPage(scDeviceRelationVo, doamin, platformCode);
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
	 * @Title: getScDeviceRelationAddByPage<br/>
	 * @Description:TODO(设备管理新增页面列表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月20日 下午3:00:39
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月20日 下午3:00:39
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/addPage", method = RequestMethod.POST)
	public Object getScDeviRelaAddByPage(@RequestBody(required=false) ScDeviceRelationVo scDeviceRelationVo,HttpServletRequest httpServletRequest) {
		ExecutionResult result = new ExecutionResult();
		try {
			String domain = httpServletRequest.getServerName();//域名
			String platformCode = EnvPropertyConfigHelper.getValue("fweb.frontserver.platform");//平台编码，
			QueryResult<DeviceDetail> queryResult = scDeviceRelationService.getScDeviRelaAddByPage(scDeviceRelationVo, domain, platformCode);
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
	 * 根据ID获取对象ScDeviceRelation
	 * @param ID
	 * @return ScDeviceRelation
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getScDeviceRelationById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScDeviceRelation scDeviceRelation = scDeviceRelationService.getScDeviceRelationById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scDeviceRelation);			//设置数据实体
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
	 * 添加对象ScDeviceRelation
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveScDeviceRelation(@RequestBody List<ScDeviceRelation> scDeviceRelation) {
		ExecutionResult result = new ExecutionResult();
		try {
			scDeviceRelationService.saveScDeviceRelationList(scDeviceRelation);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象ScDeviceRelation
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateScDeviceRelation(@RequestBody ScDeviceRelation scDeviceRelation) {
		ExecutionResult result = new ExecutionResult();
		try {
			scDeviceRelationService.updateScDeviceRelation(scDeviceRelation);
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
	 * 删除对象ScDeviceRelation
	 * @param id
	 */
	@RequestMapping(value="/deleteTree", method = RequestMethod.DELETE)
	public Object deleteScDeviceRelation(@RequestBody Map<String, List<Map<String, String>>> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			scDeviceRelationService.deleteScDeviceRelation(params);
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
	 * @Title: getConsTree<br/>
	 * @Description:TODO(查询报表选择用户树)<br/>
	 * @return
	 * List<ScDeviceRelationDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 上午8:54:03
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 上午8:54:03
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getConsTree", method = RequestMethod.GET)
	public Object getConsTree(){
		ExecutionResult result = new ExecutionResult();
		try {
			List<ScDeviceRelationDetail> scDeviceRelationDetail = scDeviceRelationService.getConsTree();;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scDeviceRelationDetail);			//设置数据实体
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
	 * @Title: getRealTimePower<br/>
	 * @Description:TODO(查询实时电量)<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年6月22日 下午3:29:56
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年6月22日 下午3:29:56
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getRealTimePower", method = RequestMethod.POST)
	public Object getRealTimePower(@RequestBody ScDeviceRelationVo scDeviceRelationVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			//map<多少分钟数据,List<map<几分钟, 电量>>>
			Map<String, Object> map = scDeviceRelationService.getRealTimePower(scDeviceRelationVo);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(map);			//设置数据实体
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
	 * @Title: getdayPqForm<br/>
	 * @Description:TODO(日电量报表数据)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月2日 上午11:29:01
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月2日 上午11:29:01
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getdayPqForm", method = RequestMethod.POST)
	public Object getdayPqForm(@RequestBody ScDeviceRelationVo scDeviceRelationVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String, Object> map = scDeviceRelationService.getdayPqForm(scDeviceRelationVo);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(map);			//设置数据实体
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
	 * @Title: getMonthPqForm<br/>
	 * @Description:TODO(月电量报表)<br/>
	 * @param scDeviceRelationVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年7月3日 下午5:37:52
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年7月3日 下午5:37:52
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getMonthPqForm", method = RequestMethod.POST)
	public Object getMonthPqForm(@RequestBody ScDeviceRelationVo scDeviceRelationVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String, Object> map = scDeviceRelationService.getMonthPqForm(scDeviceRelationVo);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(map);			//设置数据实体
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
	
	
	
	
	
	
}


















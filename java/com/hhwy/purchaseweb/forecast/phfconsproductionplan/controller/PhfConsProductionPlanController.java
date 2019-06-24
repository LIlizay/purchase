package com.hhwy.purchaseweb.forecast.phfconsproductionplan.controller;

import com.hhwy.purchaseweb.forecast.phfconsproductionplan.service.PhfConsProductionPlanService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfConsProductionPlan;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanDetail;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanVo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfConsProductionPlanController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfConsProductionPlanController")
public class PhfConsProductionPlanController {

	public static final Logger log = LoggerFactory.getLogger(PhfConsProductionPlanController.class);
	
	/**
	 * phfConsProductionPlanService
	 */
	@Autowired
	private PhfConsProductionPlanService phfConsProductionPlanService;
	
	
	/**
	 * 分页获取对象PhfConsProductionPlan
	 * @param ID
	 * @return PhfConsProductionPlan
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfConsProductionPlanByPage(@RequestBody(required=false) PhfConsProductionPlanVo phfConsProductionPlanVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfConsProductionPlanDetail> queryResult = phfConsProductionPlanService.getPhfConsProductionPlanByPage(phfConsProductionPlanVo);
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
	 * 查询子节点信息
	 */
	@RequestMapping( value = "/getPhfConsProductionPlanList", method = RequestMethod.POST)
	public List<PhfConsProductionPlanDetail> getPhfConsProductionPlanList(@RequestBody(required=false) PhfConsProductionPlanVo phfConsProductionPlanVo) {
		phfConsProductionPlanVo.setPagingFlag(false);
		List<PhfConsProductionPlanDetail> list =new ArrayList<PhfConsProductionPlanDetail>();
		try {
			list = phfConsProductionPlanService.getPhfConsProductionPlanListByParams(phfConsProductionPlanVo);
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
		}
		return list;
	}
	
	/**
	 * 根据ID获取对象PhfConsProductionPlan
	 * @param ID
	 * @return PhfConsProductionPlan
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfConsProductionPlanById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfConsProductionPlan phfConsProductionPlan = phfConsProductionPlanService.getPhfConsProductionPlanById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfConsProductionPlan);			//设置数据实体
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
	 * 添加对象PhfConsProductionPlan
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfConsProductionPlan(@RequestBody PhfConsProductionPlan phfConsProductionPlan) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfConsProductionPlanService.savePhfConsProductionPlan(phfConsProductionPlan);
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
	 * 更新对象PhfConsProductionPlan
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfConsProductionPlan(@RequestBody PhfConsProductionPlan phfConsProductionPlan) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfConsProductionPlanService.updatePhfConsProductionPlan(phfConsProductionPlan);
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
	 * 删除对象PhfConsProductionPlan
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfConsProductionPlan(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfConsProductionPlanService.deletePhfConsProductionPlan(new String[]{id});
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
	
	/**
	 * 保存数据列表
	 */
	@RequestMapping( value = "/savePhfConsProductionPlanList", method = RequestMethod.POST)
	public Object savePhfConsProductionPlanList(@RequestBody List<PhfConsProductionPlan> phfConsProductionPlanList) {
		ExecutionResult result = new ExecutionResult();
		try {
			String data=phfConsProductionPlanService.savePhfConsProductionPlanList(phfConsProductionPlanList);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);
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
	 * @Title: getParentNode
	 * @Description: 查询父节点信息
	 * @param phfConsProductionPlanVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>sunqi<br/>
	 * <b>创建时间:</b>2017年8月7日 上午10:41:55
	 * <b>修 改 人：</b>sunqi<br/>
	 * <b>修改时间:</b>2017年8月7日 上午10:41:55
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getParentNode", method = RequestMethod.POST)
	public Object getParentNode(@RequestBody(required=false) PhfConsProductionPlanVo phfConsProductionPlanVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfConsProductionPlanDetail> queryResult = phfConsProductionPlanService.getParentNode(phfConsProductionPlanVo);
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
	 * 删除用户信息
	 */
	@RequestMapping(value = "/deleteConsInfo", method = RequestMethod.DELETE)
	public Object deleteConsInfo(@RequestBody PhfConsProductionPlan phfConsProductionPlan) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfConsProductionPlanService.deleteConsInfo(phfConsProductionPlan);
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
	
	/**
	 * 更新数据列表
	 */
	@RequestMapping( value = "/updatePhfConsProductionPlanList", method = RequestMethod.POST)
	public Object updatePhfConsProductionPlanList(@RequestBody List<PhfConsProductionPlan> phfConsProductionPlanList) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfConsProductionPlanService.updatePhfConsProductionPlanList(phfConsProductionPlanList);
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
}
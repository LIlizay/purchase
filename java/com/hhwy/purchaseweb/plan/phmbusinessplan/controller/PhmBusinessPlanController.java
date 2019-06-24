package com.hhwy.purchaseweb.plan.phmbusinessplan.controller;

import com.hhwy.purchaseweb.plan.phmbusinessplan.service.PhmBusinessPlanService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmBusinessPlan;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmBusinessPlanController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmBusinessPlanController")
public class PhmBusinessPlanController {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessPlanController.class);
	
	/**
	 * phmBusinessPlanService
	 */
	@Autowired
	private PhmBusinessPlanService phmBusinessPlanService;
	
	
	/**
	 * 分页获取对象PhmBusinessPlan
	 * @param ID
	 * @return PhmBusinessPlan
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmBusinessPlanByPage(@RequestBody(required=false) PhmBusinessPlanVo phmBusinessPlanVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmBusinessPlan> queryResult = phmBusinessPlanService.getPhmBusinessPlanByPage(phmBusinessPlanVo);
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
	 * 分页获取计划
	 * getBusinessPlanDetailByPage(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/planPage", method = RequestMethod.POST)
    public Object getBusinessPlanDetailByPage(@RequestBody(required=false) PhmBusinessPlanVo phmBusinessPlanVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PhmBusinessPlanDetail> queryResult = phmBusinessPlanService.getBusinessPlanDetailByPage(phmBusinessPlanVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setTotal(0);                         //设置数据总条数为0
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
	 * 根据ID获取对象PhmBusinessPlan
	 * @param ID
	 * @return PhmBusinessPlan
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmBusinessPlanById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmBusinessPlanVo phmBusinessPlanVo = phmBusinessPlanService.getBusinessPlanById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmBusinessPlanVo);			//设置数据实体
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
	 * 添加对象PhmBusinessPlan
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmBusinessPlan(@RequestBody PhmBusinessPlanVo phmBusinessPlanVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanService.savePhmBusinessPlan(phmBusinessPlanVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！"+e.getMessage());		//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhmBusinessPlan
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmBusinessPlan(@RequestBody PhmBusinessPlanVo phmBusinessPlanVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanService.updateBusinessPlan(phmBusinessPlanVo);
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
	 * 删除对象PhmBusinessPlan
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmBusinessPlan(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanService.deleteBusinessPlan(id);
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
	 * 提交方法
	 * deletePhmBusinessPlan(描述这个方法的作用)<br/>
	 * @param phmBusinessPlan
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Object deletePhmBusinessPlan(@RequestBody PhmBusinessPlan phmBusinessPlan) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessPlanService.submit(phmBusinessPlan.getId(), phmBusinessPlan.getPlanStatus());;
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("提交成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("提交失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("提交失败！"+e.getMessage());                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}
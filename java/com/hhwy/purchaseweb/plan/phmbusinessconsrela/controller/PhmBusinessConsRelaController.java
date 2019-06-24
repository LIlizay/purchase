package com.hhwy.purchaseweb.plan.phmbusinessconsrela.controller;

import java.util.List;

import com.hhwy.purchaseweb.plan.constants.PlanConstants;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.service.PhmBusinessConsRelaService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmBusinessConsRela;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PhmBusinessConsRelaVo;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PurchasingPowerDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;
import com.hhwy.purchaseweb.plan.phmbusinessplan.service.PhmBusinessPlanService;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmBusinessConsRelaController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmBusinessConsRelaController")
public class PhmBusinessConsRelaController {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessConsRelaController.class);
	
	/**
	 * phmBusinessConsRelaService
	 */
	@Autowired
	private PhmBusinessConsRelaService phmBusinessConsRelaService;
	
	@Autowired
	private PhmBusinessPlanService phmBusinessPlanService;
	
	/**
	 * 分页获取对象PhmBusinessConsRela
	 * @param ID
	 * @return PhmBusinessConsRela
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmBusinessConsRelaByPage(@RequestBody(required=false) PhmBusinessConsRelaVo phmBusinessConsRelaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmBusinessConsRela> queryResult = phmBusinessConsRelaService.getPhmBusinessConsRelaByPage(phmBusinessConsRelaVo);
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
	 * 获取用户信息
	 * getPhmBusinessConsRelaByPage(描述这个方法的作用)<br/>
	 * @param ids
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping( value = "/getConsInfo", method = RequestMethod.POST)
    public Object getPhmBusinessConsRelaByPage(@RequestBody String[] ids) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<ConsInfoDetail> list = phmBusinessConsRelaService.getConsInfoDetailByIds(ids);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(list);
            result.setMsg("查询用户列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询用户列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(new Object[]{});                     //设置返回结果为空
            result.setMsg("查询用户列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * 获取用户信息经营电量收集
     * getPhmBusinessConsRelaByPage(描述这个方法的作用)<br/>
     * @param ids
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/getConsDetail/{id}", method = RequestMethod.GET)
    public Object getConsInfoDetailByPlanId(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            PhmBusinessPlanVo phmBusinessPlanVo = phmBusinessConsRelaService.getConsInfoDetailByPlanId(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(phmBusinessPlanVo);
            result.setMsg("查询计划信息成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询计划信息失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(new Object[]{});                     //设置返回结果为空
            result.setMsg("查询计划信息失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * 分页获取收集电量
     * getPurchasingPowerDetailByPage(描述这个方法的作用)<br/>
     * @param phmBusinessConsRelaVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/purchaingPowerPage", method = RequestMethod.POST)
    public Object getPurchasingPowerDetailByPage(@RequestBody(required=false) PhmBusinessConsRelaVo phmBusinessConsRelaVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PurchasingPowerDetail> queryResult = phmBusinessConsRelaService.getPurchasingPowerDetailByPage(phmBusinessConsRelaVo);
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
     * 提交
     * submit(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Object submit(@RequestBody String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessConsRelaService.check(id);
            phmBusinessPlanService.submit(id, PlanConstants.PLAN_STATE_ALREADY_FORMULATED);
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
    
	/**
	 * 根据ID获取对象PhmBusinessConsRela
	 * @param ID
	 * @return PhmBusinessConsRela
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmBusinessConsRelaById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmBusinessConsRela phmBusinessConsRela = phmBusinessConsRelaService.getPhmBusinessConsRelaById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmBusinessConsRela);			//设置数据实体
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
	 * 添加对象PhmBusinessConsRela
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object savePhmBusinessConsRela(@RequestBody PhmBusinessConsRela phmBusinessConsRela) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessConsRelaService.savePhmBusinessConsRela(phmBusinessConsRela);
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
	
	@RequestMapping(value = "/saveList",method = RequestMethod.POST)
    public Object savePhmBusinessConsRelaList(@RequestBody List<PhmBusinessConsRela> phmBusinessConsRelaList) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessConsRelaService.savePhmBusinessConsRelaList(phmBusinessConsRelaList);;
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("保存成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("保存失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("保存失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }
	
	/**
	 * 更新对象PhmBusinessConsRela
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmBusinessConsRela(@RequestBody PhmBusinessConsRela phmBusinessConsRela) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessConsRelaService.updatePhmBusinessConsRela(phmBusinessConsRela);
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
	 * 删除对象PhmBusinessConsRela
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmBusinessConsRela(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessConsRelaService.deletePhmBusinessConsRela(new String[]{id});
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
}
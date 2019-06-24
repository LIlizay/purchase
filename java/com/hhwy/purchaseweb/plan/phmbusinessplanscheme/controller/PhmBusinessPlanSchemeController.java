package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.controller;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.purchaseweb.plan.constants.PlanConstants;
import com.hhwy.purchaseweb.plan.phmbusinessplan.service.PhmBusinessPlanService;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.service.PhmBusinessPlanSchemeService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmBusinessPlanScheme;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhfTradingCenterDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeVo;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.SchemeVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmBusinessPlanSchemeController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmBusinessPlanSchemeController")
public class PhmBusinessPlanSchemeController {

	public static final Logger log = LoggerFactory.getLogger(PhmBusinessPlanSchemeController.class);
	
	/**
	 * phmBusinessPlanSchemeService
	 */
	@Autowired
	private PhmBusinessPlanSchemeService phmBusinessPlanSchemeService;
	
	@Autowired
	private PhmBusinessPlanService phmBusinessPlanService;
	
	/**
	 * 分页获取对象PhmBusinessPlanScheme
	 * @param ID
	 * @return PhmBusinessPlanScheme
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmBusinessPlanSchemeByPage(@RequestBody(required=false) PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmBusinessPlanScheme> queryResult = phmBusinessPlanSchemeService.getPhmBusinessPlanSchemeByPage(phmBusinessPlanSchemeVo);
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
	 * 根据planId获取树上列表
	 * getTreeList(描述这个方法的作用)<br/>
	 * @param planId
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
    public Object getTreeList(@RequestBody PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) {
        List<PhmBusinessPlanSchemeDetail> list= null;
        try {
            list = phmBusinessPlanSchemeService.getTreeList(phmBusinessPlanSchemeVo.getPhmBusinessPlanScheme().getPlanId());
        } catch (Exception e) {
            list = new ArrayList<>();
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
        }
        return list;
    }
	
    /**
     * 根据年份获取成交平均价
     * getPhfTradingCenterDetailByYear(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/getPhfTradingCenterDetail", method = RequestMethod.GET)
    public Object getPhfTradingCenterDetailByYear(@RequestParam String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhfTradingCenterDetail> list = phmBusinessPlanSchemeService.getPhfTradingCenterDetailByYear(year);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(list.size());    //设置数据总条数
            result.setRows(list);      //设置数据列表
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
    @RequestMapping( value = "/submit", method = RequestMethod.POST)
    public Object submit(@RequestBody String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessPlanSchemeService.check(id);
            phmBusinessPlanService.submit(id, PlanConstants.PLAN_STATE_ALREADY_COLLECT);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("提交成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("提交失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("提交失败！"+e.getMessage());         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
	/**
	 * 根据ID获取对象PhmBusinessPlanScheme
	 * @param ID
	 * @return PhmBusinessPlanScheme
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmBusinessPlanSchemeById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmBusinessPlanScheme phmBusinessPlanScheme = phmBusinessPlanSchemeService.getPhmBusinessPlanSchemeById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmBusinessPlanScheme);			//设置数据实体
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
	 * 获取初始方案信息
	 * getPhmBusinessPlanSchemeById(描述这个方法的作用)<br/>
	 * @param planId
	 * @param year
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = "/getInitScheme", method = RequestMethod.GET)
    public Object getPhmBusinessPlanSchemeById(@RequestParam String planId,@RequestParam String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            SchemeVo schemeVo =  phmBusinessPlanSchemeService.getInitScheme(planId, year);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(schemeVo);          //设置数据实体
            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);                       //设置返回结果为空
            result.setMsg("查询失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }   
    
    
    /**
     * 计算
     * getPhmBusinessPlanSchemeById(描述这个方法的作用)<br/>
     * @param phmBusinessPlanScheme
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/calc", method = RequestMethod.POST)
    public Object getPhmBusinessPlanSchemeById(@RequestBody PhmBusinessPlanScheme phmBusinessPlanScheme) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessPlanSchemeService.calc(phmBusinessPlanScheme);;
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(phmBusinessPlanScheme);          //设置数据实体
            result.setMsg("计算成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("计算失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);                       //设置返回结果为空
            result.setMsg("计算失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    } 
    
	/**
	 * 添加对象PhmBusinessPlanScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmBusinessPlanScheme(@RequestBody PhmBusinessPlanScheme phmBusinessPlanScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanSchemeService.savePhmBusinessPlanScheme(phmBusinessPlanScheme);
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
	
	/**
	 * 保存
	 * savePhmBusinessPlanScheme(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanSchemeVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object savePhmBusinessPlanScheme(@RequestBody PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmBusinessPlanSchemeService.save(phmBusinessPlanSchemeVo);
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
	 * 更新对象PhmBusinessPlanScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmBusinessPlanScheme(@RequestBody PhmBusinessPlanScheme phmBusinessPlanScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanSchemeService.updatePhmBusinessPlanScheme(phmBusinessPlanScheme);
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
	 * 删除对象PhmBusinessPlanScheme
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmBusinessPlanScheme(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmBusinessPlanSchemeService.deletePhmBusinessPlanScheme(new String[]{id});
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
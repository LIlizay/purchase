package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.controller;

import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.service.PhmPurchasePlanYearService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmPurchasePlanYear;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PhmPurchasePlanYearVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PurchasePlanYearDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.SchemeDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmPurchasePlanYearController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmPurchasePlanYearController")
public class PhmPurchasePlanYearController {

	public static final Logger log = LoggerFactory.getLogger(PhmPurchasePlanYearController.class);
	
	/**
	 * phmPurchasePlanYearService
	 */
	@Autowired
	private PhmPurchasePlanYearService phmPurchasePlanYearService;
	
	
	/**
	 * 分页获取对象PhmPurchasePlanYear
	 * @param ID
	 * @return PhmPurchasePlanYear
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmPurchasePlanYearByPage(@RequestBody(required=false) PhmPurchasePlanYearVo phmPurchasePlanYearVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmPurchasePlanYear> queryResult = phmPurchasePlanYearService.getPhmPurchasePlanYearByPage(phmPurchasePlanYearVo);
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
	 * 分页获取购电计划
	 * getPurchasePlanYearByPage(描述这个方法的作用)<br/>
	 * @param phmPurchasePlanYearVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/planPage", method = RequestMethod.POST)
    public Object getPurchasePlanYearByPage(@RequestBody(required=false) PhmPurchasePlanYearVo phmPurchasePlanYearVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PurchasePlanYearDetail> queryResult = phmPurchasePlanYearService.getPurchasePlanYearByPage(phmPurchasePlanYearVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setTotal(0);                         //设置数据总条数为0
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
        }
        return result;
    }
	
	/**
	 * 分页获取用户信息
	 * getConsInfoDetailByPage(描述这个方法的作用)<br/>
	 * @param phmPurchasePlanYearVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/consPage", method = RequestMethod.POST)
    public Object getConsInfoDetailByPage(@RequestBody(required=false) PhmPurchasePlanYearVo phmPurchasePlanYearVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<ConsInfoDetail> queryResult = phmPurchasePlanYearService.getConsInfoDetailByPage(phmPurchasePlanYearVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setTotal(0);                         //设置数据总条数为0
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
        }
        return result;
    }
	/**
	 * 根据ID获取对象PhmPurchasePlanYear
	 * @param ID
	 * @return PhmPurchasePlanYear
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmPurchasePlanYearById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmPurchasePlanYear phmPurchasePlanYear = phmPurchasePlanYearService.getPhmPurchasePlanYearById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmPurchasePlanYear);			//设置数据实体
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
	 * 获取方案初始信息
	 * getInitScheme(描述这个方法的作用)<br/>
	 * @param year
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
   @RequestMapping(value = "getInitScheme/{year}", method = RequestMethod.GET)
    public Object getInitScheme(@PathVariable String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            SchemeDetail schemeDetail = phmPurchasePlanYearService.getInitScheme(year);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(schemeDetail);            //设置数据实体
            result.setMsg("查询初始化数据成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);                       //设置返回结果为空
            result.setMsg("查询初始化数据失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
   
	/**
	 * 添加对象PhmPurchasePlanYear
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmPurchasePlanYear(@RequestBody PhmPurchasePlanYear phmPurchasePlanYear) {
		ExecutionResult result = new ExecutionResult();
		try {
		    phmPurchasePlanYearService.checkPlan(phmPurchasePlanYear.getYear());
			phmPurchasePlanYearService.savePhmPurchasePlanYear(phmPurchasePlanYear);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！"+e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhmPurchasePlanYear
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmPurchasePlanYear(@RequestBody PhmPurchasePlanYear phmPurchasePlanYear) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPurchasePlanYearService.updatePhmPurchasePlanYear(phmPurchasePlanYear);
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
	 * 删除对象PhmPurchasePlanYear
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmPurchasePlanYear(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPurchasePlanYearService.deletePhmPurchasePlanYear(new String[]{id});
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
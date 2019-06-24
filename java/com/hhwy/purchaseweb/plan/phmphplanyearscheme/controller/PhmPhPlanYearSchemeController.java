package com.hhwy.purchaseweb.plan.phmphplanyearscheme.controller;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.purchaseweb.plan.phmphplanyearscheme.service.PhmPhPlanYearSchemeService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmPhPlanYearScheme;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.CalcVo;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeDetail;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmPhPlanYearSchemeController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmPhPlanYearSchemeController")
public class PhmPhPlanYearSchemeController {

	public static final Logger log = LoggerFactory.getLogger(PhmPhPlanYearSchemeController.class);
	
	/**
	 * phmPhPlanYearSchemeService
	 */
	@Autowired
	private PhmPhPlanYearSchemeService phmPhPlanYearSchemeService;
	
	
	/**
	 * 分页获取对象PhmPhPlanYearScheme
	 * @param ID
	 * @return PhmPhPlanYearScheme
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmPhPlanYearSchemeByPage(@RequestBody(required=false) PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmPhPlanYearScheme> queryResult = phmPhPlanYearSchemeService.getPhmPhPlanYearSchemeByPage(phmPhPlanYearSchemeVo);
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
	 * 分页获取方案详细
	 * getSchemeDetailByPage(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearSchemeVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/detailPage", method = RequestMethod.POST)
    public Object getSchemeDetailByPage(@RequestBody(required=false) PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PhmPhPlanYearSchemeDetail> queryResult = phmPhPlanYearSchemeService.getSchemeDetailByPage(phmPhPlanYearSchemeVo);
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
	 * 获取树表格下拉
	 * getTreeList(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearSchemeVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getTreeList", method = RequestMethod.POST)
    public Object getTreeList(@RequestBody(required=false) PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) {
        List<PhmPhPlanYearSchemeDetail> list = null;
        try{
            phmPhPlanYearSchemeVo.setPagingFlag(false);
            list = phmPhPlanYearSchemeService.getSchemeDetailList(phmPhPlanYearSchemeVo);
        }catch (Exception e) {
            log.error("查询列表失败",e); 
            list = new ArrayList<>();
        }
        return list;
    }
	
	/**
	 * 根据ID获取对象PhmPhPlanYearScheme
	 * @param ID
	 * @return PhmPhPlanYearScheme
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmPhPlanYearSchemeById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmPhPlanYearScheme phmPhPlanYearScheme = phmPhPlanYearSchemeService.getPhmPhPlanYearSchemeById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmPhPlanYearScheme);			//设置数据实体
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
	 * 添加对象PhmPhPlanYearScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmPhPlanYearScheme(@RequestBody PhmPhPlanYearSchemeVo phmPhPlanYearScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPhPlanYearSchemeService.savePhmPhPlanYearScheme(phmPhPlanYearScheme);
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
	 * 系统计算
	 * sysCalc(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearScheme
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/sysCalc", method = RequestMethod.POST)
    public Object sysCalc(@RequestBody PhmPhPlanYearScheme phmPhPlanYearScheme){
        ExecutionResult result = new ExecutionResult();
        try {
            CalcVo calcVo = phmPhPlanYearSchemeService.sysCalc(phmPhPlanYearScheme);
            result.setData(calcVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("计算成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("计算失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("计算失败！"+e.getMessage());       //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
     * 计算
     * calc(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
	@RequestMapping(value = "/calc", method = RequestMethod.POST)
    public Object calc(@RequestBody PhmPhPlanYearScheme phmPhPlanYearScheme){
        ExecutionResult result = new ExecutionResult();
        try {
            phmPhPlanYearSchemeService.calc(phmPhPlanYearScheme);
            result.setData(phmPhPlanYearScheme);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("计算成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("计算失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("计算失败！"+e.getMessage());       //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
	 * 更新对象PhmPhPlanYearScheme
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmPhPlanYearScheme(@RequestBody PhmPhPlanYearScheme phmPhPlanYearScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPhPlanYearSchemeService.updatePhmPhPlanYearScheme(phmPhPlanYearScheme);
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
	 * 删除对象PhmPhPlanYearScheme
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmPhPlanYearScheme(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPhPlanYearSchemeService.deletePhmPhPlanYearScheme(new String[]{id});
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
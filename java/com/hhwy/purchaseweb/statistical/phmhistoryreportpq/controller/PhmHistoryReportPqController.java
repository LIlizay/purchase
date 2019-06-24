package com.hhwy.purchaseweb.statistical.phmhistoryreportpq.controller;

import java.util.List;

import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.service.PhmHistoryReportPqService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmHistoryReportPq;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmHistoryReportPqController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmHistoryReportPqController")
public class PhmHistoryReportPqController {

	public static final Logger log = LoggerFactory.getLogger(PhmHistoryReportPqController.class);
	
	/**
	 * phmHistoryReportPqService
	 */
	@Autowired
	private PhmHistoryReportPqService phmHistoryReportPqService;
	
	
	/**
	 * 分页获取对象PhmHistoryReportPq
	 * @param ID
	 * @return PhmHistoryReportPq
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmHistoryReportPqByPage(@RequestBody(required=false) PhmHistoryReportPqVo phmHistoryReportPqVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmHistoryReportPq> queryResult = phmHistoryReportPqService.getPhmHistoryReportPqByPage(phmHistoryReportPqVo);
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
	 * 根据ID获取对象PhmHistoryReportPq
	 * @param ID
	 * @return PhmHistoryReportPq
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmHistoryReportPqById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmHistoryReportPq phmHistoryReportPq = phmHistoryReportPqService.getPhmHistoryReportPqById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmHistoryReportPq);			//设置数据实体
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
	 * 添加对象PhmHistoryReportPq
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmHistoryReportPq(@RequestBody PhmHistoryReportPq phmHistoryReportPq) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPqService.savePhmHistoryReportPq(phmHistoryReportPq);
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
	 * 更新对象PhmHistoryReportPq
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmHistoryReportPq(@RequestBody PhmHistoryReportPq phmHistoryReportPq) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPqService.updatePhmHistoryReportPq(phmHistoryReportPq);
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
	 * 删除对象PhmHistoryReportPq
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmHistoryReportPq(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPqService.deletePhmHistoryReportPq(new String[]{id});
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
     * 根据年份获取历史报量信息
     * getPqHistoryPage(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/pqHistoryPage", method = RequestMethod.GET)
    public Object getPqHistoryPage(@RequestParam String ym) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhmHistoryReportPqDetail> list = phmHistoryReportPqService.getPqHistoryPage(ym);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setRows(list);;
            result.setMsg("查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setMsg("查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * 根据年份获取历史电量
     * getPqHistoryByYear(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/getPqHistoryByYear", method = RequestMethod.GET)
    public Object getPqHistoryByYear(@RequestParam String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhmHistoryReportPq> list = phmHistoryReportPqService.getPqHistoryByYear(year);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setRows(list);;
            result.setMsg("查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setMsg("查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}
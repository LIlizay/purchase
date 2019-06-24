package com.hhwy.purchaseweb.statistical.phmhistoryreportprc.controller;

import java.util.List;
import java.util.Map;

import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.service.PhmHistoryReportPrcService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmHistoryReportPrc;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmHistoryReportPrcController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmHistoryReportPrcController")
public class PhmHistoryReportPrcController {

	public static final Logger log = LoggerFactory.getLogger(PhmHistoryReportPrcController.class);
	
	/**
	 * phmHistoryReportPrcService
	 */
	@Autowired
	private PhmHistoryReportPrcService phmHistoryReportPrcService;
	
	
	/**
	 * 分页获取对象PhmHistoryReportPrc
	 * @param ID
	 * @return PhmHistoryReportPrc
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmHistoryReportPrcByPage(@RequestBody(required=false) PhmHistoryReportPrcVo phmHistoryReportPrcVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmHistoryReportPrc> queryResult = phmHistoryReportPrcService.getPhmHistoryReportPrcByPage(phmHistoryReportPrcVo);
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
	 * 根据ID获取对象PhmHistoryReportPrc
	 * @param ID
	 * @return PhmHistoryReportPrc
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmHistoryReportPrcById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmHistoryReportPrc phmHistoryReportPrc = phmHistoryReportPrcService.getPhmHistoryReportPrcById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmHistoryReportPrc);			//设置数据实体
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
	 * 添加对象PhmHistoryReportPrc
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmHistoryReportPrc(@RequestBody PhmHistoryReportPrc phmHistoryReportPrc) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPrcService.savePhmHistoryReportPrc(phmHistoryReportPrc);
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
	 * 更新对象PhmHistoryReportPrc
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmHistoryReportPrc(@RequestBody PhmHistoryReportPrc phmHistoryReportPrc) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPrcService.updatePhmHistoryReportPrc(phmHistoryReportPrc);
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
	 * 删除对象PhmHistoryReportPrc
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmHistoryReportPrc(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmHistoryReportPrcService.deletePhmHistoryReportPrc(new String[]{id});
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
     * 根据年份获取历史报价信息
     * getPriceHistoryPage(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/priceHistoryPage", method = RequestMethod.GET)
    public Object getPriceHistoryPage(@RequestParam Map<String,Object> map) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhmHistoryReportPrcDetail> list = phmHistoryReportPrcService.getPriceHistoryPage(map);
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
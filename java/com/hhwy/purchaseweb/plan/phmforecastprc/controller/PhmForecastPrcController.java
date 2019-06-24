package com.hhwy.purchaseweb.plan.phmforecastprc.controller;

import com.hhwy.purchaseweb.plan.phmforecastprc.service.PhmForecastPrcService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import com.hhwy.purchase.domain.PhmForecastPrc;
import com.hhwy.purchaseweb.plan.phmforecastprc.domain.PhmForecastPrcVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmForecastPrcController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmForecastPrcController")
public class PhmForecastPrcController {

	public static final Logger log = LoggerFactory.getLogger(PhmForecastPrcController.class);
	
	/**
	 * phmForecastPrcService
	 */
	@Autowired
	private PhmForecastPrcService phmForecastPrcService;
	
	
	/**
	 * 分页获取对象PhmForecastPrc
	 * @param ID
	 * @return PhmForecastPrc
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmForecastPrcByPage(@RequestBody(required=false) PhmForecastPrcVo phmForecastPrcVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmForecastPrc> queryResult = phmForecastPrcService.getPhmForecastPrcByPage(phmForecastPrcVo);
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
	 * 根据年份获取预测电价
	 * getPhmForecastPrc(描述这个方法的作用)<br/>
	 * @param phmForecastPrcVo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getPhmForecastPrc/{year}", method = RequestMethod.GET)
    public Object getPhmForecastPrc(@PathVariable String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            PhmForecastPrcVo phmForecastPrcVo = new PhmForecastPrcVo();
            phmForecastPrcVo.getPhmForecastPrc().setYear(year);
            PhmForecastPrc phmForecastPrc = phmForecastPrcService.getPhmForecastPrcOneByParams(phmForecastPrcVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(phmForecastPrc);
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
            result.setData(null);
            return result;
        }
        return result;
    }
	
	/**
	 * 根据ID获取对象PhmForecastPrc
	 * @param ID
	 * @return PhmForecastPrc
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmForecastPrcById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmForecastPrc phmForecastPrc = phmForecastPrcService.getPhmForecastPrcById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmForecastPrc);			//设置数据实体
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
	 * 添加对象PhmForecastPrc
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmForecastPrc(@RequestBody PhmForecastPrc phmForecastPrc) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmForecastPrcService.savePhmForecastPrc(phmForecastPrc);
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
	 * 更新对象PhmForecastPrc
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmForecastPrc(@RequestBody PhmForecastPrc phmForecastPrc) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmForecastPrcService.updatePhmForecastPrc(phmForecastPrc);
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
	 * 删除对象PhmForecastPrc
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmForecastPrc(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmForecastPrcService.deletePhmForecastPrc(new String[]{id});
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
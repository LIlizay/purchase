package com.hhwy.purchaseweb.forecast.phflcdealinfo.controller;

import java.util.List;

import com.hhwy.purchaseweb.forecast.phflcdealinfo.service.PhfLcDealInfoService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhfLcDealInfo;
import com.hhwy.purchaseweb.forecast.phflcdealinfo.domain.PhfLcDealInfoVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfLcDealInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfLcDealInfoController")
public class PhfLcDealInfoController {

	public static final Logger log = LoggerFactory.getLogger(PhfLcDealInfoController.class);
	
	/**
	 * phfLcDealInfoService
	 */
	@Autowired
	private PhfLcDealInfoService phfLcDealInfoService;
	
	
	/**
	 * 分页获取对象PhfLcDealInfo
	 * @param ID
	 * @return PhfLcDealInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfLcDealInfoByPage(@RequestBody(required=false) PhfLcDealInfoVo phfLcDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfLcDealInfo> queryResult = phfLcDealInfoService.getPhfLcDealInfoByPage(phfLcDealInfoVo);
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
	 * 根据ID获取对象PhfLcDealInfo
	 * @param ID
	 * @return PhfLcDealInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfLcDealInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfLcDealInfo phfLcDealInfo = phfLcDealInfoService.getPhfLcDealInfoById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfLcDealInfo);			//设置数据实体
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
	 * 添加对象PhfLcDealInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfLcDealInfo(@RequestBody PhfLcDealInfo phfLcDealInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfLcDealInfoService.savePhfLcDealInfo(phfLcDealInfo);
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
	 * 更新对象PhfLcDealInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfLcDealInfo(@RequestBody PhfLcDealInfo phfLcDealInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfLcDealInfoService.updatePhfLcDealInfo(phfLcDealInfo);
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
	 * 删除对象PhfLcDealInfo
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfLcDealInfo(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfLcDealInfoService.deletePhfLcDealInfo(new String[]{id});
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
     * 分页获取对象PhfLcDealInfo
     * @param ID
     * @return PhfLcDealInfo
     */
    @RequestMapping( value = "/getPhfLcDealInfoByYear/{year}", method = RequestMethod.GET)
    public Object getPhfLcDealInfoByPage(@PathVariable String year) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<PhfLcDealInfo> list = phfLcDealInfoService.getPhfLcDealInfoByYear(year);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setRows(list);      //设置数据列表
            result.setMsg("查询近3年长协电价成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("查询近3年长协电价失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});                     //设置返回结果为空
            result.setMsg("查询近3年长协电价失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}
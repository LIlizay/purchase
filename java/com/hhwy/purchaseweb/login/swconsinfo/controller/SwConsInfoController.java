package com.hhwy.purchaseweb.login.swconsinfo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaDetail;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaVo;
import com.hhwy.purchaseweb.login.swconsinfo.domain.SwConsInfoVo;
import com.hhwy.purchaseweb.login.swconsinfo.service.SwConsInfoService;
import com.hhwy.selling.domain.SwConsInfo;

/**
 * SwConsInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/swConsInfoController")
public class SwConsInfoController {

	public static final Logger log = LoggerFactory.getLogger(SwConsInfoController.class);
	
	/**
	 * swConsInfoService
	 */
	@Autowired
	private SwConsInfoService swConsInfoService;
	
	
	/**
	 * 分页获取对象SwConsInfo
	 * @param ID
	 * @return SwConsInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSwConsInfoByPage(@RequestBody(required=false) SwConsInfoVo swConsInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SwConsInfo> queryResult = swConsInfoService.getSwConsInfoByPage(swConsInfoVo);
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
     * 分页获取对象SwConsInfo
     * @param ID
     * @return SwConsInfo
     */
    @RequestMapping( value = "/relaPage", method = RequestMethod.POST)
    public Object getRelaDetailByPage(@RequestBody(required=false) RelaVo relaVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<RelaDetail> queryResult = swConsInfoService.getRelaDetailByPage(relaVo);
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
	 * 根据ID获取对象SwConsInfo
	 * @param ID
	 * @return SwConsInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSwConsInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwConsInfo swConsInfo = swConsInfoService.getSwConsInfoById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swConsInfo);			//设置数据实体
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
	 * 添加对象SwConsInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSwConsInfo(@RequestBody SwConsInfo swConsInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsInfoService.saveSwConsInfo(swConsInfo);
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
	 * 绑定账号
	 * binding(描述这个方法的作用)<br/>
	 * @param swConsInfo
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "binding", method = RequestMethod.POST)
    public Object binding(@RequestBody SwConsInfo swConsInfo) {
        ExecutionResult result = new ExecutionResult();
        try {
            swConsInfoService.binding(swConsInfo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("绑定账号成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("绑定账号失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("绑定账号失败！"+e.getMessage());                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }
	
	/**
	 * 更新对象SwConsInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSwConsInfo(@RequestBody SwConsInfo swConsInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsInfoService.updateSwConsInfo(swConsInfo);
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
	 * 删除对象SwConsInfo
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSwConsInfo(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsInfoService.deleteSwConsInfo(new String[]{id});
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("解除绑定成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("解除绑定失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("解除绑定失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * remindMessage(提醒用户信息)<br/>
	 * @param relaDetailList
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/remindMessage", method = RequestMethod.POST)
    public Object remindMessage(@RequestBody List<RelaDetail> relaDetailList) {
        ExecutionResult result = new ExecutionResult();
        try {
        	swConsInfoService.remindMessage(relaDetailList);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("提醒成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("提醒失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("提醒失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}
package com.hhwy.purchaseweb.agreement.phmppa.controller;

import java.util.List;
import java.util.Map;

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
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaDetail;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaVo;
import com.hhwy.purchaseweb.agreement.phmppa.service.PhmPpaService;

/**
 * PhmPpaController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmPpaController")
public class PhmPpaController {

	public static final Logger log = LoggerFactory.getLogger(PhmPpaController.class);
	
	/**
	 * phmPpaService
	 */
	@Autowired
	private PhmPpaService phmPpaService;
	
	
	/**
	 * 分页获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmPpaByPage(@RequestBody(required=false) PhmPpaVo phmPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmPpaDetail> queryResult = phmPpaService.getPhmPpaByPage(phmPpaVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 根据ID获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmPpaById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmPpaDetail phmPpaDetail = phmPpaService.getPhmPpaById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmPpaDetail);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
     * 根据ID获取对象PhmPpa
     * @param ID
     * @return PhmPpa
     */
    @RequestMapping(value = "/getImplementation", method = RequestMethod.GET)
    public Object getImplementation(String id) {
        ExecutionResult result = new ExecutionResult();
        try {
        	List<PhmGeneratorMonthPqDetail> list = phmPpaService.getImplementation(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(4);							//设置数据总条数
            result.setRows(list);                       //设置数据实体
            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(new Object[]{});                       //设置返回结果为空
            result.setMsg("查询失败！");                 //设置返回消息，根据实际情况修改
        }
        return result;
    }   
    
	/**
	 * 添加对象PhmPpa
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmPpa(@RequestBody PhmPpaVo phmPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			//判断该电厂在该有效期内是否存在  暂去
//			Integer total = phmPpaService.getElecCountByParams(phmPpaVo);
//			if(total > 0){
//				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
//				result.setFlag(true);						//设置是否成功标识
//				result.setMsg("在该有效期内已存在该电厂合同！");					//设置返回消息，根据实际情况修改
//				return result;
//			}
			PhmPpaVo resultData = phmPpaService.savePhmPpa(phmPpaVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultData); 				//设置返回数据
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null); 				//设置返回数据
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhmPpa
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmPpa(@RequestBody PhmPpaVo phmPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPpaService.updatePhmPpa(phmPpaVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象PhmPpa
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deletePhmPpa(@RequestBody Map<String, Object> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPpaService.deletePhmPpa(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！" + e.getMessage());					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	/**
	 * 
	 * @Title: getPhcElecInfoList
	 * @Description:(查询电厂信息)
	 * @return 
	 * List<PhmPpaDetail>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:13:37
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:13:37
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getPhcElecInfoList", method = RequestMethod.GET)
	public List<PhmPpaDetail> getPhcElecInfoList() {
		try {
			List<PhmPpaDetail> queryResult = phmPpaService.getPhcElecInfoList();
			if(queryResult != null && !queryResult.isEmpty()){
				return queryResult;
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("查询失败",e);				
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getYearList
	 * @Description:(获取查询参数年)
	 * @return 
	 * List<Map<String,String>>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月8日 上午9:16:48
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月8日 上午9:16:48
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getYearList", method = RequestMethod.GET)
	public List<Map<String,String>> getYearList() {
		try {
			List<Map<String,String>> queryResult = phmPpaService.getYearList();
			if(queryResult != null && !queryResult.isEmpty()){
				return queryResult;
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error("查询失败",e);				
			return null;
		}
	}
}
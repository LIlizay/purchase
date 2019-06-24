package com.hhwy.purchaseweb.crm.smcalcconfigure.controller;

import java.util.List;
import java.util.Map;

import com.hhwy.purchase.domain.SmCalcConfigure;
import com.hhwy.purchaseweb.crm.smcalcconfigure.service.SmCalcConfigureService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SmCalcConfigureController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smCalcConfigureController")
public class SmCalcConfigureController {

	public static final Logger log = LoggerFactory.getLogger(SmCalcConfigureController.class);
	
	/**
	 * smCalcConfigureService
	 */
	@Autowired
	private SmCalcConfigureService smCalcConfigureService;
	
	
	/**
	 * 分页获取对象SmCalcConfigure
	 * @param ID
	 * @return SmCalcConfigure
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmCalcConfigureByPage(@RequestBody(required=false) SmCalcConfigureVo smCalcConfigureVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmCalcConfigureDetail> queryResult = smCalcConfigureService.getSmCalcConfigureByPage(smCalcConfigureVo);
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
	 * 根据ID获取对象SmCalcConfigure
	 * @param ID
	 * @return SmCalcConfigure
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmCalcConfigureById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmCalcConfigure smCalcConfigure = smCalcConfigureService.getSmCalcConfigureById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smCalcConfigure);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
	
	/**
	 * 添加对象SmCalcConfigure
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmCalcConfigure(@RequestBody SmCalcConfigure smCalcConfigure) {
		ExecutionResult result = new ExecutionResult();
		try {
			smCalcConfigureService.saveSmCalcConfigure(smCalcConfigure);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smCalcConfigure);
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
		
	}
	
	/**
	 * 更新对象SmCalcConfigure
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmCalcConfigure(@RequestBody SmCalcConfigure smCalcConfigure) {
		ExecutionResult result = new ExecutionResult();
		try {
			smCalcConfigureService.updateSmCalcConfigure(smCalcConfigure);
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
	 * 删除对象SmCalcConfigure
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSmCalcConfigure(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smCalcConfigureService.deleteSmCalcConfigure(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}	
	
	/**
	 * @Title: getRuleValueList<br/>
	 * @Description: 查询规则下拉框数据<br/>
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午3:48:35
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午3:48:35
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getRuleValue", method = RequestMethod.GET)
	public Object getRuleValueList(){
		try {
			List<Map<String, Object>> list = smCalcConfigureService.getRuleValueList();
			return list;
		} catch (Exception e) {
			log.error("查询失败",e);
			return new String[]{};
		}
	}
	
	/**
	 * @Title: getSellDbList<br/>
	 * @Description: 查询售电公司库名<br/>
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:10:23
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:10:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getSellDb", method = RequestMethod.GET)
	public Object getSellDbList(){
		try {
			List<Map<String, Object>> list = smCalcConfigureService.getSellDbList();
			return list;
		} catch (Exception e) {
			log.error("查询失败",e);
			return new String[]{};
		}
	}
	
	/**
	 * @Title: getRuleParamsList<br/>
	 * @Description: 查询规则参数列表<br/>
	 * @param ruleId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:29:00
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:29:00
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getRuleParams", method = RequestMethod.POST)
	public Object getRuleParamsList(@RequestBody String ruleCode){
		ExecutionResult result = new ExecutionResult();
		try {
			List<Map<String, Object>> list = smCalcConfigureService.getRuleParamsList(ruleCode);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(list);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new String[]{});				//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: getTree<br/>
	 * @Description: 查询树<br/>
	 * @param nodeType
	 * @param provinceId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午10:56:05
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午10:56:05
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
    public Object getTree(@RequestParam String nodeType,@RequestParam String provinceId) {
        try {
            return smCalcConfigureService.getTree(nodeType, provinceId);
        } catch (Exception e) {
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            return new Object[]{};
        }
    }  
	
	/**
	 * @Title: effect<br/>
	 * @Description: 规则生效<br/>
	 * @param provinceId
	 * @param id
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:07:24
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:07:24
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/effect/{provinceId}/{id}", method = RequestMethod.POST)
    public Object effect(@PathVariable String provinceId,@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            result = smCalcConfigureService.effect(provinceId,id);
        } catch (Exception e) {
            log.error("生效失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("生效失败！");                 //设置返回消息，根据实际情况修改
        }
        return result;
    }
	
	/**
	 * @Title: invalid<br/>
	 * @Description: 规则失效<br/>
	 * @param id
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:08:05
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:08:05
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/invalid/{id}", method = RequestMethod.POST)
    public Object invalid(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
        	smCalcConfigureService.invalid(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("失效成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("失效失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("失效失败！");                 //设置返回消息，根据实际情况修改
        }
        return result;
    }
}
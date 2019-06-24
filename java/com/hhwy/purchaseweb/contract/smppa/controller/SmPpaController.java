package com.hhwy.purchaseweb.contract.smppa.controller;

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
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaDetail;
import com.hhwy.purchaseweb.contract.smppa.domain.SmPpaVo;
import com.hhwy.purchaseweb.contract.smppa.service.SmPpaService;
import com.hhwy.selling.domain.SmPpa;

/**
 * SmPpaController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smPpaController")
public class SmPpaController {

	public static final Logger log = LoggerFactory.getLogger(SmPpaController.class);
	
	/**
	 * smPpaService
	 */
	@Autowired
	private SmPpaService smPpaService;
	
	/**
	 * 分页获取对象SmPpa
	 * @param ID
	 * @return SmPpa
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmPpaByPage(@RequestBody(required=false) SmPpaVo smPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmPpaDetail> queryResult = smPpaService.getSmPpaByPage(smPpaVo);
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
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SmPpa
	 * @param ID
	 * @return SmPpa
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmPpaById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPpaVo smPpaVo = smPpaService.getSmPpaById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smPpaVo);			//设置数据实体
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
	  * @Title: getDeleteRelatedById
	  * @Description: 根据合同id获取月度购电管理、实际用电量录入、月度收益结算的关联关系，为数据删除做提醒
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年4月14日 下午1:53:02
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年4月14日 下午1:53:02
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/deleteRelated/{id}", method = RequestMethod.GET)
	public Object getDeleteRelatedById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			String msg = smPpaService.getDeleteRelatedById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			//result.setData(smPpaVo);			//设置数据实体
			result.setMsg(msg);					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("验证失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("验证失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: getImplementation
	  * @Description: 根据ID获取对象SmPpa
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月17日 下午1:47:46
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月17日 下午1:47:46
	  * @since  1.0.0
	 */
	 @RequestMapping(value = "/getImplementation", method = RequestMethod.GET)
	    public Object getImplementation(String id) {
	        ExecutionResult result = new ExecutionResult();
	        try {
	        	List<SmPpaDetail> list = smPpaService.getImplementation(id);
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
	 * 添加对象SmPpa
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmPpa(@RequestBody SmPpaVo smPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPpaVo param = new SmPpaVo();
	        param.getSmPpa().setConsId(smPpaVo.getSmPpa().getConsId());
	        param.getSmPpa().setEffectiveDate(smPpaVo.getSmPpa().getEffectiveDate());
	        param.getSmPpa().setExpiryDate(smPpaVo.getSmPpa().getExpiryDate());
	        //param.getSmPpa().setAgreStatus("01");
	        SmPpaDetail smPpaDetail = smPpaService.getSmPpaOneByParams(param);
	        if(smPpaDetail != null){//该用户已有正常合同
	            result.setCode(ReturnCode.RES_FAILED);       //设置返回结果编码：成功
	            result.setFlag(false);                       //设置是否成功标识
	            result.setMsg("该用户已存在正常合同信息！");                 //设置返回消息，根据实际情况修改
	            return result;
	        }
			SmPpaVo resultSmPpaVo = smPpaService.saveSmPpa(smPpaVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultSmPpaVo);              //设置返回结果
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SmPpaVo());              //设置返回结果
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象SmPpa
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmPpa(@RequestBody SmPpaVo smPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPpaVo smp = new SmPpaVo();
			smp.getSmPpa().setConsId(smPpaVo.getSmPpa().getConsId());
			smp.getSmPpa().setId(smPpaVo.getSmPpa().getId());
			smp.getSmPpa().setEffectiveDate(smPpaVo.getSmPpa().getEffectiveDate());
			smp.getSmPpa().setExpiryDate(smPpaVo.getSmPpa().getExpiryDate());
			SmPpaDetail smPpaDetail = smPpaService.getSmPpaOneByParams(smp);
			if(smPpaDetail != null){//该用户已有正常合同
	            result.setCode(ReturnCode.RES_FAILED);       //设置返回结果编码：成功
	            result.setFlag(false);                       //设置是否成功标识
	            result.setMsg("该用户已存在正常合同信息！");                 //设置返回消息，根据实际情况修改
	            return result;
	        }
			
			smPpaService.updateSmPpa(smPpaVo);
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
	 * 提交SmPpa
	 * @param id
	 */
	@RequestMapping(value="/approval",method = RequestMethod.GET)
	public Object approval(String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmPpaVo smPpaVo = smPpaService.getSmPpaById(id);
			if(smPpaVo == null || smPpaVo.getSmPpa() == null){
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setMsg("请添加记录！");					//设置返回消息，根据实际情况修改
				return result;
			}
	        SmPpaVo param = new SmPpaVo();
	        param.getSmPpa().setConsId(smPpaVo.getSmPpa().getConsId());
	        param.getSmPpa().setAgreStatus("01");
	        SmPpaDetail smPpaDetail = smPpaService.getSmPpaOneByParams2(param);
	        if(smPpaDetail != null){//该用户已有正常合同
	            result.setCode(ReturnCode.RES_SUCCESS);       //设置返回结果编码：成功
	            result.setFlag(true);                       //设置是否成功标识
	            result.setMsg("该用户已存在正常合同信息！");                 //设置返回消息，根据实际情况修改
	            return result;
	        }
	        smPpaService.approvalSmPpa(smPpaVo.getSmPpa());//提交
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("提交成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("提交失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("提交失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象SmPpa
	 * @param id
	 */
	@RequestMapping( method = RequestMethod.DELETE)
	public Object deleteSmPpa(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPpaService.deleteSmPpa(ids);
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
	  * @Title: deleteSmPpaById
	  * @Description: 根据合同ID 删除非草稿状态合同
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年4月14日 下午3:56:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年4月14日 下午3:56:31
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
	public Object deleteSmPpaById(@RequestBody String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPpaService.deleteSmPpaById(id);
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
	 * 
		 * 
		 * @Title: forecast<br/>
		 * @Description:负荷预测<br/>
		 * @param smPpa
		 * @return
		 * Object
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月12日 下午4:57:00
		 * <b>修 改 人：</b>chengpeng<br/>
		 * <b>修改时间:</b>2018年3月12日 下午4:57:00
		 * @since  1.0.0
	 */
	@RequestMapping(value = "/forecast", method = RequestMethod.POST)
	public Object forecast(@RequestBody SmPpa smPpa ){
	        ExecutionResult result = new ExecutionResult();
			try {
				Map<String, Object> data = (Map<String, Object>) smPpaService.getForecast(smPpa);
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setData(data);			//设置数据实体
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
	 * @Title: forecast<br/>
	 * @Description:查询当前用户有无负荷预测权限<br/>
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年4月10日 下午5:31:31
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年4月10日 下午5:31:31
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/forecastRes", method = RequestMethod.GET)
	public Object forecast(){
        ExecutionResult result = new ExecutionResult();
		try {
			String data = smPpaService.getForecastRes();
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);			//设置数据实体
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
	
}
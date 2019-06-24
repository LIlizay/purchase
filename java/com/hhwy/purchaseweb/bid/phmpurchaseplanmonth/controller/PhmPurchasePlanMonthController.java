package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.controller;

import java.util.List;
import java.util.Map;

import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmPurchasePlanMonthController		月度购电计划制定 controller
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmPurchasePlanMonthController")
public class PhmPurchasePlanMonthController {

	public static final Logger log = LoggerFactory.getLogger(PhmPurchasePlanMonthController.class);
	
	/**
	 * phmPurchasePlanMonthService
	 */
	@Autowired
	private PhmPurchasePlanMonthService phmPurchasePlanMonthService;

	
	/**
	 * 分页获取对象PhmPurchasePlanMonth
	 * @param ID
	 * @return PhmPurchasePlanMonth
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmPurchasePlanMonthByPage(@RequestBody(required=false) PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmPurchasePlanMonthDetail> queryResult = phmPurchasePlanMonthService.getPhmPurchasePlanMonthByPage(phmPurchasePlanMonthVo);
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
	  * @Title: getFormData
	  * @Description: 根据计划id获取表单数据信息
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年1月22日 下午4:05:08
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年1月22日 下午4:05:08
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getFormData/{id}",method = RequestMethod.GET)
	public Object getFormData(@PathVariable String id){
		ExecutionResult result = new ExecutionResult();
		try {
			PhmPurchasePlanMonthVo phmVo = new PhmPurchasePlanMonthVo();
			phmVo.getPhmPurchasePlanMonth().setId(id);
			List<PhmPurchasePlanMonthDetail> list = phmPurchasePlanMonthService.getPhmPurchasePlanMonthListByParams(phmVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(list.get(0));			//设置数据实体
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
	 * 根据ID获取对象PhmPurchasePlanMonth
	 * @param ID
	 * @return PhmPurchasePlanMonth
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmPurchasePlanMonthById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmPurchasePlanMonth phmPurchasePlanMonth = phmPurchasePlanMonthService.getPhmPurchasePlanMonthById(id);
			Map<String,Object> map = phmPurchasePlanMonthService.getPqCountByPlanId(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setExtMap(map);
			result.setData(phmPurchasePlanMonth);		//设置数据实体
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
	  * @Title: getPlanValidateByYm
	  * @Description: 根据年月判断购电计划是否存在
	  * ===================================================已废弃=====================================================================
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年3月9日 下午3:15:26
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年3月9日 下午3:15:26
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getPlanValidateByYm/{ym}", method = RequestMethod.GET)
	public Object getPlanValidateByYm(@PathVariable String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			boolean flag = phmPurchasePlanMonthService.getPhmPurchasePlanMonthByYm(ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(flag);			//设置数据实体
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
	 * 添加对象PhmPurchasePlanMonth
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmPurchasePlanMonth(@RequestBody PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmPurchasePlanMonthService.savePhmPurchasePlanMonth(phmPurchasePlanMonthVo);
			result.setData(phmPurchasePlanMonthVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
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
	 * 删除对象PhmPurchasePlanMonth
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deletePhmPurchasePlanMonth(@RequestBody PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			boolean flag = phmPurchasePlanMonthService.deletePhmPurchasePlanMonth(phmPurchasePlanMonthVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(flag);
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
     * 根据ID获取对象PhmPurchasePlanMonth
     * @param ID
     * @return PhmPurchasePlanMonth
     */
    @RequestMapping(value = "/submit/{id}", method = RequestMethod.POST)
    public Object submit(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            phmPurchasePlanMonthService.submit(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("提交成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("提交失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("提交失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
        }
        return result;
    }
    
    /**
      * @Title: recall
      * @Description: 月度购电相关流程回退
      * @param phmPurchasePlanMonthVo
      * @return  Object
      * <b>创 建 人：</b>LiXinze<br/>
      * <b>创建时间:</b>2017年12月6日 下午1:49:59
      * <b>修 改 人：</b>LiXinze<br/>
      * <b>修改时间:</b>2017年12月6日 下午1:49:59
      * @since  1.0.0
     */
    @RequestMapping(value = "/recall" , method = RequestMethod.PUT)
    public Object recall(@RequestBody PhmPurchasePlanMonth phmPurchasePlanMonth){
    	ExecutionResult result = new ExecutionResult();
    	try{
    		String id= phmPurchasePlanMonth.getId();
    		String planStatus = phmPurchasePlanMonth.getPlanStatus();
    		phmPurchasePlanMonthService.recall(id,planStatus);
    		result.setCode(ReturnCode.RES_SUCCESS);     	//设置返回结果编码：成功
            result.setFlag(true);                       	//设置是否成功标识
            result.setMsg("退回成功！");                 		//设置返回消息，根据实际情况修改
    	}catch(Exception e){
    		 log.error("退回失败",e);                        //记录异常日志，根据实际情况修改
             result.setCode(ReturnCode.RES_FAILED);      	//设置返回结果编码：失败
             result.setFlag(false);                      	//设置是否成功标识
             result.setMsg("退回失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
             return result;
    	}
    	return result;
    }
    
}
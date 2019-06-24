package com.hhwy.purchaseweb.delivery.smselldelivery.controller;

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
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.CalcDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryVo;
import com.hhwy.purchaseweb.delivery.smselldelivery.service.SmSellDeliveryService;
import com.hhwy.selling.domain.SmSellDelivery;

/**
 * SmSellDeliveryController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smSellDeliveryController")
public class SmSellDeliveryController {

	public static final Logger log = LoggerFactory.getLogger(SmSellDeliveryController.class);
	
	/**
	 * smSellDeliveryService
	 */
	@Autowired
	private SmSellDeliveryService smSellDeliveryService;
	
	
	/**
	 * 分页获取对象SmSellDelivery
	 * @param ID
	 * @return SmSellDelivery
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmSellDeliveryByPage(@RequestBody(required=false) SmSellDeliveryVo smSellDeliveryVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmSellDeliveryDetail> queryResult = smSellDeliveryService.getSmSellDeliveryByPage(smSellDeliveryVo);
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
     * 分页获取对象未计算对象
     * @param ID
     * @return SmSellDelivery
     */
    @RequestMapping( value = "/calcPage", method = RequestMethod.POST)
    public Object getCalcByPage(@RequestBody(required=false) SmSellDeliveryVo smSellDeliveryVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<SmSellDeliveryDetail> queryResult = smSellDeliveryService.getCalcByPage(smSellDeliveryVo);
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
	 * 根据ID获取对象SmSellDelivery
	 * @param ID
	 * @return SmSellDelivery
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmSellDeliveryById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmSellDelivery smSellDelivery = smSellDeliveryService.getSmSellDeliveryById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smSellDelivery);			//设置数据实体
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
	 * 添加对象SmSellDelivery
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmSellDelivery(@RequestBody SmSellDelivery smSellDelivery) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSellDeliveryService.saveSmSellDelivery(smSellDelivery);
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
	 * 计算未结算
	 * saveSmSellDelivery(描述这个方法的作用)<br/>
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
    @RequestMapping(value = "/calc", method = RequestMethod.POST)
    public Object saveSmSellDelivery(@RequestBody(required=false) SmSellDeliveryVo smSellDeliveryVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            smSellDeliveryService.calc(smSellDeliveryVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("计算成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("计算失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("计算失败！");                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }
    
	/**
	 * 更新对象SmSellDelivery
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmSellDelivery(@RequestBody SmSellDelivery smSellDelivery) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSellDeliveryService.updateSmSellDelivery(smSellDelivery);
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
	 * 删除对象SmSellDelivery
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmSellDelivery(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSellDeliveryService.deleteSmSellDelivery(new String[]{id});
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
	 * 
	 * @Title: getPlanList<br/>
	 * @Description: 查询计划列表<br/>
	 * @param smSellDeliveryVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月14日 下午10:01:02
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月14日 下午10:01:02
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getPlanList", method = RequestMethod.POST)
	public Object getPlanList(@RequestBody(required=false) SmSellDeliveryVo smSellDeliveryVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<CalcDetail> queryResult = smSellDeliveryService.getPlanList(smSellDeliveryVo);
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
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Object submitByYm(@RequestBody(required=false) SmSellDeliveryVo smSellDeliveryVo){
		ExecutionResult result = new ExecutionResult();
		try {
			smSellDeliveryService.submitByYm(smSellDeliveryVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("提交成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("提交失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("提交失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
}
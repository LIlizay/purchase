package com.hhwy.purchaseweb.delivery.purchasesettle.controller;

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
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchasesettleInfoDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.SettleDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.service.PurchaseSettleService;

@RestController
@RequestMapping("/purchaseSettleController")
public class PurchaseSettleController {
	
public static final Logger log = LoggerFactory.getLogger(PurchaseSettleController.class);
    
    /**
     * phfTradingCenterService
     */
    @Autowired
    private PurchaseSettleService purchaseSettleService;
    
    /**
     * 分页查询电费结算
     * getPurchaseSettleByPage(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return
     * @throws Exception 
     * QueryResult<PurchaseSettleDetail>
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/page", method = RequestMethod.POST)
    public Object getPurchaseSettleByPage(@RequestBody(required=false) PurchaseSettleVo purchaseSettleVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<PurchaseSettleDetail> queryResult = purchaseSettleService.getPurchaseSettleByPage(purchaseSettleVo);
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
     * 分页查询电费详细
     * getSettleDetailByPage(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/SettleDetail/page", method = RequestMethod.POST)
    public Object getSettleDetailByPage(@RequestBody(required=false) PurchaseSettleVo purchaseSettleVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<SettleDetail> queryResult = purchaseSettleService.getSettleDetailByPage(purchaseSettleVo);
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
     * 获取合计
     * getSettleDetailByPage(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/getSum/{id}", method = RequestMethod.GET)
    public Object getSettleDetailByPage(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            PurchaseSettleDetail purchaseSettleDetail = purchaseSettleService.getSum(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(purchaseSettleDetail);   
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("分页查询列表失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("分页查询列表失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * @Title: getPhmPurchasePlanMonthByPage
     * @Description: 获取已经归档的计划
     * @param phmPurchasePlanMonthVo
     * @return 
     * Object
     * <b>创 建 人：</b>sunqi<br/>
     * <b>创建时间:</b>2017年9月21日 下午3:05:45
     * <b>修 改 人：</b>sunqi<br/>
     * <b>修改时间:</b>2017年9月21日 下午3:05:45
     * @since  1.0.0
     */
    @RequestMapping( value = "/getPurchasesettleInfo", method = RequestMethod.POST)
	public Object getPurchasesettleInfo(@RequestBody(required=false) PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PurchasesettleInfoDetail> queryResult = purchaseSettleService.getPurchasesettleInfo(phmPurchasePlanMonthVo);
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
}

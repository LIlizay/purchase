package com.hhwy.purchaseweb.bid.transactionlreport.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.PhmTransactionlReportVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlPriceHistoryDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportVo;
import com.hhwy.purchaseweb.bid.transactionlreport.service.TransactionlReportService;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;


@RestController
@RequestMapping("/transactionlReportController")
public class TransactionlReportController {
    
    public static final Logger log = LoggerFactory.getLogger(TransactionlReportController.class);
    
    @Autowired
    TransactionlReportService transactionlReportService;
    
    @Autowired
    BigDataService bigDataService;
    /**
     * 分页查询申报电量
     * getMeAddPlPrcByPage(描述这个方法的作用)<br/>
     * @param scAddPlPrcVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/page", method = RequestMethod.POST)
    public Object getMeAddPlPrcByPage(@RequestBody TransactionlReportVo transactionlReportVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<TransactionlReportDetail> queryResult = transactionlReportService.getTransactionlReportByPage(transactionlReportVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
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
     * getMeAddPlPrcByPage(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
   /* --by wangzelu  @RequestMapping( value = "/getSum/{id}", method = RequestMethod.GET)
    public Object getMeAddPlPrcByPage(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            Map<String, Object> map = transactionlReportService.getSum(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(map);
            result.setMsg("查询合计成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("查询合计失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("查询合计失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }*/
    
    /**
     * 分页获取用户历史报价信息
     * getConsHistoryByPage(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/consHistoryPage", method = RequestMethod.POST)
    public Object getConsHistoryByPage(@RequestBody TransactionlReportVo transactionlReportVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<TransactionlReportDetail> queryResult = transactionlReportService.getConsHistoryByPage(transactionlReportVo);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setTotal(queryResult.getTotal());    //设置数据总条数
            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
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
     * 保存交易申报
     * getMeAddPlPrcByPage(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/save", method = RequestMethod.POST)
    public Object getMeAddPlPrcByPage(@RequestBody PhmTransactionlReportVo phmTransactionlReportVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            transactionlReportService.savePhmTransactionlReport(phmTransactionlReportVo);
            result.setData(phmTransactionlReportVo.getTransactionlReportList());
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("保存成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("保存失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("保存失败！");         //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
    
    /**
     * @Title: getTransactionlPriceHistoryInfo
     * @Description: 获取历史申报信息
     * @param phmPurchasePlanMonthVo
     * @return 
     * Object
     * <b>创 建 人：</b>sunqi<br/>
     * <b>创建时间:</b>2017年9月18日 下午8:25:05
     * <b>修 改 人：</b>sunqi<br/>
     * <b>修改时间:</b>2017年9月18日 下午8:25:05
     * @since  1.0.0
     */
    @RequestMapping( value = "/getTransactionlPriceHistoryInfo", method = RequestMethod.POST)
    public Object getTransactionlPriceHistoryInfo(@RequestBody PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<TransactionlPriceHistoryDetail> list=transactionlReportService.getTransactionlPriceHistoryInfo(phmPurchasePlanMonthVo);
            result.setData(list);
            result.setCode(ReturnCode.RES_SUCCESS);  
            result.setFlag(true);                      
            result.setMsg("获取历史申报信息成功！");         
        } catch (Exception e) {
            log.error("获取历史申报信息失败",e);                
            result.setCode(ReturnCode.RES_FAILED);    
            result.setFlag(false);                      
            result.setMsg("获取历史申报信息失败！");         
            return result;
        }
        return result;
    }
    
    /*@RequestMapping( value = "/submit/{id}", method = RequestMethod.POST)
    public Object submit(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            transactionlReportService.check(id);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("提交成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("保存失败",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("提交失败！"+e.getMessage());    //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }*/
    
    /**
     * 价格预测
     * getPriceForecast(描述这个方法的作用)<br/>
     * @return 
     * Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping( value = "/priceForecast", method = RequestMethod.POST)
    public Object getPriceForecast(@RequestBody(required =true) Map<String, String> map) {
        ExecutionResult result = new ExecutionResult();
        try {
            List<Map<String, Object>> list = bigDataService.getPriceForecast(map.get("ym"),map.get("consId"));
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setData(list);
            result.setMsg("价格预测成功！");         //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("价格预测失败！",e);                //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setData(null);
            result.setMsg("价格预测失败！");    //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
}

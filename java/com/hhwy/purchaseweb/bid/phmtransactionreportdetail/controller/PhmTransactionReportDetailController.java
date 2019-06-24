package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.controller;

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
import com.hhwy.purchase.domain.PhmTransactionReportDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.AgrePqDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailDetail;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain.PhmTransactionReportDetailVo;
import com.hhwy.purchaseweb.bid.phmtransactionreportdetail.service.PhmTransactionReportDetailService;

 /**
 * <b>类 名 称：</b>PhmTransactionReportDetailController<br/>
 * <b>类 描 述：</b><br/> 	交易申报明细
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月9日 下午3:23:46<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/phmTransactionReportDetailController")
public class PhmTransactionReportDetailController {

	public static final Logger log = LoggerFactory.getLogger(PhmTransactionReportDetailController.class);
	
	/**
	 * phmTransactionReportDetailService 交易申报明细的service
	 */
	@Autowired
	private PhmTransactionReportDetailService phmTransactionReportDetailService;
	
	/**
	 * 分页获取对象PhmTransactionReportDetail
	 * @param ID
	 * @return PhmTransactionReportDetail
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getAgrePqDetailListByPlanId(@RequestBody PhmTransactionReportDetailVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<AgrePqDetail> queryResult = phmTransactionReportDetailService.getAgrePqDetailListByPlanId(detailVo);
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
	 * @Title: getPhmTransactionReportDetailByPlanId
	 * @Description: 根据月度购电计划id获取对象PhmTransactionReportDetail列表
	 * @param planId
	 * @return Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月13日 下午3:37:47
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月13日 下午3:37:47
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/list/{planId}", method = RequestMethod.GET)
	public Object getPhmTransactionReportDetailByPlanId(@PathVariable String planId) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmTransactionReportDetailDetail> detailList = phmTransactionReportDetailService.getPhmTransactionReportDetailListByPlanId(planId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(detailList == null ? 0 : detailList.size());	//设置数据总条数
			result.setRows(detailList);		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: getPhmTransactionReportSLByPlanId
	  * @Description:  根据月度购电计划id分页获取山西、辽宁PhmTransactionReportDetail列表
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月28日 下午3:04:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月28日 下午3:04:56
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/conslist", method = RequestMethod.POST)
	public Object getPhmTransactionReportSLByPlanId(@RequestBody PhmTransactionReportDetailVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmTransactionReportDetailDetail> queryResult = phmTransactionReportDetailService.getPhmTransactionReportSLByPlanId(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: getConsTransactionReportByIds
	  * @Description: 根据planId、consId获取山西辽宁用户申报电量详情
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月28日 下午8:59:07
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月28日 下午8:59:07
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/slconslist", method = RequestMethod.POST)
	public Object getConsTransactionReportByIds(@RequestBody PhmTransactionReportDetailVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmTransactionReportDetailDetail> detailList = phmTransactionReportDetailService.getConsTransactionReportByIds(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(detailList == null ? 0 : detailList.size());	//设置数据总条数
			result.setRows(detailList);		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: updatePhmTransactionReportDetail
	  * @Description: 更新对象PhmTransactionReportDetail
	  * @param detailList
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月27日 下午7:15:26
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月27日 下午7:15:26
	  * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmTransactionReportDetail(@RequestBody List<PhmTransactionReportDetail> detailList) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmTransactionReportDetailService.updatePhmTransactionReportDetailList(detailList);
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
	  * @Title: saveConsTransactionReportDetail
	  * @Description: 保存用户申报信息
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月29日 上午11:09:48
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月29日 上午11:09:48
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/consreport", method = RequestMethod.POST)
	public Object saveConsTransactionReportDetail(@RequestBody PhmTransactionReportDetailVo detailVo){
		ExecutionResult result = new ExecutionResult();
		try {
			phmTransactionReportDetailService.saveConsTransactionReportDetail(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * @Title: checkAndSubmit
	 * @Description: 提交交易申报信息（就是修改月度购电计划的状态）
	 * @param id
	 * @return Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月13日 下午9:22:37
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月13日 下午9:22:37
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/submit/{id}", method = RequestMethod.POST)
   public Object checkAndSubmit(@PathVariable String id) {
       ExecutionResult result = new ExecutionResult();
       try {
    	   phmTransactionReportDetailService.checkAndSubmit(id);
           result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
           result.setFlag(true);                       //设置是否成功标识
           result.setMsg("提交成功！");         //设置返回消息，根据实际情况修改
       } catch (Exception e) {
           log.error("保存失败",e);                //记录异常日志，根据实际情况修改
           result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
           result.setFlag(false);                      //设置是否成功标识
           result.setMsg("提交失败！"+e.getMessage());    //设置返回消息，根据实际情况修改
       }
       return result;
   }
	
	
}
package com.hhwy.purchaseweb.bid.phmdealinfo.controller;

import java.math.BigDecimal;
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
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.MonthDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoDetail;
import com.hhwy.purchaseweb.bid.phmdealinfo.domain.PhmDealInfoVo;
import com.hhwy.purchaseweb.bid.phmdealinfo.service.PhmDealInfoService;

/**
 * PhmDealInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmDealInfoController")
public class PhmDealInfoController {

	public static final Logger log = LoggerFactory.getLogger(PhmDealInfoController.class);
	
	/**
	 * phmDealInfoService
	 */
	@Autowired
	private PhmDealInfoService phmDealInfoService;
	
	/**
	  * @Title: getPhmDealInfoByPlanId
	  * @Description: 根据计划id获取其他省竞价、挂牌交易成交信息录入
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午4:41:00
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午4:41:00
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getList/{planId}", method = RequestMethod.GET)
	public Object getPhmDealInfoByPlanId(@PathVariable String planId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmDealInfoDetail> detailList = phmDealInfoService.getPhmDealInfoByPlanId(planId);
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
	  * @Title: getSbDealInfoByPlanId
	  * @Description: 根据计划id获取其它省双边协商交易成交信息录入
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午2:14:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午2:14:22
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getListSb/{planId}", method = RequestMethod.GET)
	public Object getSbDealInfoByPlanId(@PathVariable String planId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmDealInfoDetail> detailList = phmDealInfoService.getSbDealInfoByPlanId(planId);
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
	  * @Title: getSLDealInfoByPlanId
	  * @Description: 根据planId获取山西、辽宁省竞价、挂牌交易成交信息录入
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午6:31:56
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午6:31:56
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getSLList", method = RequestMethod.POST)
	public Object getSLDealInfoByPlanId(@RequestBody PhmDealInfoVo detailVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmDealInfoDetail> queryResult = phmDealInfoService.getSLDealInfoByPlanId(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
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
	  * @Title: getSLSBDealInfoByPlanId
	  * @Description: 根据计划id获取山西、辽宁省双边协商交易成交信息
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月4日 上午7:35:26
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月4日 上午7:35:26
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getSLListSb", method = RequestMethod.POST)
	public Object getSLSBDealInfoByPlanId(@RequestBody PhmDealInfoVo detailVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmDealInfoDetail> queryResult = phmDealInfoService.getSLSBDealInfoByPlanId(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
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
	  * @Title: getYearDealInfoByPlanId
	  * @Description: （年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午8:29:43
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午8:29:43
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getYearDealInfo/{planId}", method = RequestMethod.GET)
	public Object getYearDealInfoByPlanId(@PathVariable String planId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<MonthDealInfoDetail> detailList = phmDealInfoService.getYearDealInfoByPlanId(planId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(detailList == null? null :detailList);		//设置数据列表
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
	  * @Title: getSbYearDealInfoByPlanId
	  * @Description: （双边年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月30日 下午8:29:43
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月30日 下午8:29:43
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getSbYearDealInfo/{planId}", method = RequestMethod.GET)
	public Object getSbYearDealInfoByPlanId(@PathVariable String planId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<MonthDealInfoDetail> detailList = phmDealInfoService.getSbYearDealInfoByPlanId(planId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(detailList == null? null :detailList);		//设置数据列表
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
	  * @Title: getSlSbYearDealInfoByPlanId
	  * @Description: 山西、辽宁省（双边年交易）根据计划id获取本次交易情况
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月25日 下午5:42:49
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月25日 下午5:42:49
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getSlSbYearDealInfo/{planId}", method = RequestMethod.GET)
	public Object getSlSbYearDealInfoByPlanId(@PathVariable String planId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<MonthDealInfoDetail> detailList = phmDealInfoService.getSlSbYearDealInfoByPlanId(planId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(detailList == null? null :detailList);		//设置数据列表
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
	  * @Title: getDealListByReportId
	  * @Description: 根据申报id获取该申报段成交信息详情
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 下午1:06:46
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 下午1:06:46
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/dealList", method = RequestMethod.POST)
	public Object getDealListByReportId(@RequestBody PhmDealInfoVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmDealInfoDetail> detailList = phmDealInfoService.getDealListByReportId(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(detailList == null? null :detailList);		//设置数据列表
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
	  * @Title: 根据consIds 获取山西、辽宁省双边交易成交信息详情
	  * @Description: getSlSbDealListByConsIds
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月2日 下午5:03:52
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月2日 下午5:03:52
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/slSbDealList", method = RequestMethod.POST)
	public Object getSlSbDealListByConsIds(@RequestBody PhmDealInfoVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String,Object> resultMap = phmDealInfoService.getSlSbDealListByConsIds(detailVo);
			//List<PhmDealInfoDetail> detailList = phmDealInfoService.getDealListByReportId(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(resultMap.get("list") == null? null :resultMap.get("list"));		//设置数据列表
			result.setData(resultMap.get("ids"));
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
	  * @Title: saveMonthList
	  * @Description: 年度分月计划成交信息保存（年交易）
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 下午5:38:16
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 下午5:38:16
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/saveMonthList", method = RequestMethod.POST)
	public Object saveMonthList(@RequestBody PhmDealInfoVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveMonthList(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: saveSubMonthList
	  * @Description: 年度分月计划成交信息保存（月交易）
	  * @param detailVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 上午8:30:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 上午8:30:31
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/saveSubMonthList", method = RequestMethod.POST)
	public Object saveSubMonthList(@RequestBody PhmDealInfoVo detailVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveSubMonthList(detailVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setMsg("保存列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: getMonthDealInfo
	  * @Description: 根据计划年月获取该年所有已成交年交易详情信息
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月1日 下午1:16:11
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月1日 下午1:16:11
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/getMonthDealInfo/{ym}", method = RequestMethod.GET)
	public Object getMonthDealInfoByYm(@PathVariable String ym){
		ExecutionResult result = new ExecutionResult();
		try {
			List<MonthDealInfoDetail> detailList = phmDealInfoService.getMonthDealInfoByYm(ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(detailList == null? null :detailList);		//设置数据列表
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
	  * @Title: savePhmDealInfo
	  * @Description: 添加成交信息
	  * @param phmDealInfoVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月2日 上午10:36:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月2日 上午10:36:50
	  * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmDealInfo(@RequestBody PhmDealInfoVo phmDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.savePhmDealInfo(phmDealInfoVo);
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
	  * @Title: saveSLPhmDealInfo
	  * @Description: 山西辽宁省用户、电厂多对多关系保存
	  * @param phmDealInfoVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年8月29日 下午1:21:58
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年8月29日 下午1:21:58
	  * @since  1.0.0
	 */
	@RequestMapping(value="/SLTradeSave", method = RequestMethod.POST)
	public Object saveSLPhmDealInfo(@RequestBody PhmDealInfoVo phmDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveSLPhmDealInfo(phmDealInfoVo);
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
	  * @Title: calDeliveryPrc
	  * @Description: 山西、辽宁省交易，用户结算电价计算
	  * @param map
	  * @return  Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年7月26日 下午2:47:39
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年7月26日 下午2:47:39
	  * @since  1.0.0
	 */
	@RequestMapping(value="/calDeliveryPrc", method = RequestMethod.POST)
	public Object calDeliveryPrc(@RequestBody Map<String,Object> map) {
		ExecutionResult result = new ExecutionResult();
		try {
			BigDecimal deliveryPrc = phmDealInfoService.caculateDeliveryPrc(map);
			result.setData(deliveryPrc);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("结算电价计算失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("结算电价计算失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	  * @Title: saveSbYearList
	  * @Description: 双边年交易信息保存
	  * @param phmDealInfoVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月22日 下午1:13:54
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月22日 下午1:13:54
	  * @since  1.0.0
	 */
	@RequestMapping(value="/saveSbYearList", method = RequestMethod.POST)
	public Object saveSbYearList(@RequestBody PhmDealInfoVo phmDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveSbYearList(phmDealInfoVo);
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
	  * @Title: saveSLDealInfo
	  * @Description: 山西、辽宁省成交信息存储
	  * @param phmDealInfoVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 下午11:42:12
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 下午11:42:12
	  * @since  1.0.0
	 */
	@RequestMapping(value="/SLSave", method = RequestMethod.POST)
	public Object saveSLDealInfo(@RequestBody PhmDealInfoVo phmDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveSLDealInfo(phmDealInfoVo);
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
	  * @Title: saveSLYearDealInfo
	  * @Description:山西、辽宁省年度成交信息存储
	  * @param phmDealInfoVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月22日 下午5:54:55
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月22日 下午5:54:55
	  * @since  1.0.0
	 */
	@RequestMapping(value="/SLYearSave", method = RequestMethod.POST)
	public Object saveSLYearDealInfo(@RequestBody PhmDealInfoVo phmDealInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmDealInfoService.saveSLYearDealInfo(phmDealInfoVo);
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
	  * @Title: submit
	  * @Description: 提交成交信息
	  * @param planId
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月3日 上午9:31:57
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月3日 上午9:31:57
	  * @since  1.0.0
	 */
	@RequestMapping( value = "/submit/{planId}", method = RequestMethod.POST)
	public Object submit(@PathVariable String planId) {
      ExecutionResult result = new ExecutionResult();
      try {
          phmDealInfoService.submit(planId);
          result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
          result.setFlag(true);                       //设置是否成功标识
          result.setMsg("提交成功！");         //设置返回消息，根据实际情况修改
      } catch (Exception e) {
          log.error("提交失败",e);                //记录异常日志，根据实际情况修改
          result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
          result.setFlag(false);                      //设置是否成功标识
          result.setRows(new Object[]{});                     //设置返回结果为空
          result.setTotal(0);                         //设置数据总条数为0
          result.setMsg("提交失败！"+e.getMessage());         //设置返回消息，根据实际情况修改
          return result;
      }
      return result;
  }
}
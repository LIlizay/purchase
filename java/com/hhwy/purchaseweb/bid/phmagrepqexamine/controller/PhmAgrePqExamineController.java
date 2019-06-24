package com.hhwy.purchaseweb.bid.phmagrepqexamine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineDetail;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineVo;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.YearPlanProxyPqDetail;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.service.PhmAgrePqExamineService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanConsRelaDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmSubMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.contants.BusinessContants;

/**
 * PhmAgrePqExamineController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmAgrePqExamineController")
public class PhmAgrePqExamineController {

	public static final Logger log = LoggerFactory.getLogger(PhmAgrePqExamineController.class);
	
	/**
	 * phmAgrePqExamineService
	 */
	@Autowired
	private PhmAgrePqExamineService phmAgrePqExamineService;
	
	@Autowired
	private PhmPurchasePlanMonthService phmPurchasePlanMonthService;
	
	/**
	  * @Title: getPhmAgreExamineByPlanId
	  * @Description: 根据planId分页获取用户列表，计划制定编辑用
	  * @param phmPlanConsRelaVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月22日 下午3:18:25
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月22日 下午3:18:25
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getPhmAgreExamineByPlanId", method = RequestMethod.POST)
	public Object getPhmAgreExamineByPlanId(@RequestBody(required=false) PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try{
			QueryResult<PhmPurchasePlanConsRelaDetail> queryResult = phmAgrePqExamineService.getPhmAgreExamineByPlanId(phmAgrePqExamineVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		}catch(Exception e){
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
	  * @Title: getExamineDetailListByPlanId
	  * @Description: 根据planId获取月交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午6:07:24
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午6:07:24
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public Object getExamineDetailListByPlanId(@RequestBody(required=true) PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmAgrePqExamineDetail> queryResult = phmAgrePqExamineService.getExamineDetailListByPlanId(phmAgrePqExamineVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");	
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
	 * @Title: getYearPlanProxyPqDetailByParams
	 * @Description: 根据year和consId获取该用户的所有年交易电量审核分月电量信息，用于提供月购电计划的委托电量审核第二个表格“年度交易委托电量分月信息”的数据
	 * @param year
	 * @param consId
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2019年3月1日 下午1:20:29
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2019年3月1日 下午1:20:29
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getYearBidDetailList", method = RequestMethod.GET)
	public Object getYearPlanProxyPqDetailByParams(@RequestParam String year, @RequestParam String consId){
		ExecutionResult result = new ExecutionResult();
		try {
			List<YearPlanProxyPqDetail> detailList = phmAgrePqExamineService.getYearPlanProxyPqDetailByParams(year, consId);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(detailList);		//设置数据列表
			result.setMsg("查询列表成功！");	
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());			//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
			}
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
		}
		return result;
	}
	/**
	  * @Title: getExamineDetailYearListByPlanId
	  * @Description: 根据planId获取年交易电量审核用户
	  * @param phmAgrePqExamineVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午6:08:22
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午6:08:22
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getYearList", method = RequestMethod.POST)
	public Object getExamineDetailYearListByPlanId(@RequestBody(required=true) PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmAgrePqExamineDetail> queryResult = phmAgrePqExamineService.getExamineDetailYearListByPlanId(phmAgrePqExamineVo);
			List<PhmSubMonthDetail> subMonthDetail = phmAgrePqExamineService.getSubMonthDetailByPlanId(phmAgrePqExamineVo);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("subMonth", subMonthDetail);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setExtMap(map);
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");	
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
	  * @Title: saveAgrePqs
	  * @Description: 保存页面填写的委托电量
	  * @param phmAgrePqExamineVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午1:47:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午1:47:31
	  * @since  1.0.0
	 */
	@RequestMapping(value="/saveAgrePqs", method=RequestMethod.POST)
	public Object saveAgrePqs(@RequestBody PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhmAgrePqExamineDetail> details = phmAgrePqExamineVo.getPhmAgrePqExamineDetailList();
			if(details == null || details.size() == 0) {
				log.error("参数为空，保存失败");				//记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("保存失败！");			//设置返回消息，根据实际情况修改
				return result;
			}
			//先判断这个计划是否是电量审核状态，如果不是，则保存失败
			String planId = details.get(0).getPlanId();
			PhmPurchasePlanMonth planMonth = phmPurchasePlanMonthService.getPhmPurchasePlanMonthById(planId);
			//如果此时月度计划状态不是“已制定”状态
			if(!BusinessContants.SELL_MONTHBIDSTATUS01.equals(planMonth.getPlanStatus())) {
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("计划已提交，不允许重复保存！");	//设置返回消息，根据实际情况修改
				return result;
			}
			String msg =phmAgrePqExamineService.saveAgrePqs(phmAgrePqExamineVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg(msg);
		} catch (Exception e) {
			log.error("保存失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getExamineDetailIsNullByPlanId<br/>
	 * @Description: 查询计划是否有未填写合同电量的用户<br/>
	 * @param planId
	 * @return <br/> Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年7月19日 下午7:43:32
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年7月19日 下午7:43:32
	 * @since  1.0.0
	 */
	@RequestMapping(value="/getNull", method=RequestMethod.GET)
	public Object getExamineDetailIsNullByPlanId(@RequestParam String planId){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			String msg = phmAgrePqExamineService.getExamineDetailIsNullByPlanId(planId);
			map.put("msg", msg);
			return map;
		} catch (Exception e) {
			log.error("查询失败",e);	
			map.put("msg", "审核失败，请刷新重试！");
			return map;
		}
	}
	
	/**
	  * @Title: getForeCastFlag
	  * @Description: 获取负荷预测权限标识
	  * @param phmAgrePqExamineVo
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月24日 下午3:34:17
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月24日 下午3:34:17
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/getForeCastFlag", method = RequestMethod.GET)
	public Object getForeCastFlag(){
		try {
			String forecastResName = phmAgrePqExamineService.getForecastResName();
			return forecastResName;
		} catch (Exception e) {
			log.error("===========获取负荷预测权限失败=============",e);	
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: remindReportPq
	 * @Description:(重报信息提醒)
	 * @param phmAgrePqExamineVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月5日 上午10:35:40
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月5日 上午10:35:40
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/remindReportPq", method = RequestMethod.POST)
	public Object remindReportPq(@RequestBody(required =true) PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try {
			phmAgrePqExamineService.remindReportPq(phmAgrePqExamineVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("提醒成功！");	
		} catch (Exception e) {
			log.error("提醒失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("提醒失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * @Title: saveMessage<br/>
	 * @Description: 保存提示信息<br/>
	 * @param phmAgrePqExamineVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月30日 下午7:40:58
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月30日 下午7:40:58
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveMessage", method = RequestMethod.POST)
	public Object saveMessage(@RequestBody(required =true) PhmAgrePqExamineVo phmAgrePqExamineVo){
		ExecutionResult result = new ExecutionResult();
		try {
			phmAgrePqExamineService.saveMessage(phmAgrePqExamineVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("提醒成功！");	
		} catch (Exception e) {
			log.error("提醒失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("提醒失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
}
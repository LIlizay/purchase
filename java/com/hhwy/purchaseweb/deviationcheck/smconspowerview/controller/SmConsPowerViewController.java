package com.hhwy.purchaseweb.deviationcheck.smconspowerview.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hhwy.purchaseweb.archives.overview.service.OverViewService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.AgreConsInfoDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewDetail;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain.SmConsPowerViewVo;
import com.hhwy.purchaseweb.deviationcheck.smconspowerview.service.SmConsPowerViewService;
import com.hhwy.selling.domain.SmConsPowerView;

/**
 * <b>类 名 称：</b>SmConsPowerViewController<br/>
 * <b>类 描 述：</b><br/>		用电计划的controller类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月17日 下午5:29:25<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/smConsPowerViewController")
public class SmConsPowerViewController {

	public static final Logger log = LoggerFactory.getLogger(SmConsPowerViewController.class);
	
	/**
	 * smConsPowerViewService
	 */
	@Autowired
	private SmConsPowerViewService smConsPowerViewService;
	
	/**
	 * overViewService	总览页面的service
	 */
	@Autowired
	private OverViewService overViewService;
	
	@Autowired
	private PhmPurchasePlanMonthService phmPurchasePlanMonthService;
	
	/**
	 * 分页获取对象SmConsPowerView
	 * @param ID
	 * @return SmConsPowerView
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmConsPowerViewByPage(@RequestBody(required=false) SmConsPowerViewVo smConsPowerViewVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmConsPowerViewDetail> queryResult = smConsPowerViewService.getSmConsPowerViewByPage(smConsPowerViewVo);
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
	 * @Title: getAgreConsListByPage
	 * @Description: 查询合同用户列表分页
	 * @param smConsPowerViewVo		只有ym及分页参数有用
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:27:38
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:27:38
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getConsList", method = RequestMethod.POST)
	public Object getAgreConsListByPage(@RequestBody(required=true) SmConsPowerViewVo smConsPowerViewVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<AgreConsInfoDetail> queryResult = smConsPowerViewService.getAgreConsList(smConsPowerViewVo);
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
	 * @Title: addSmConsPowerViewByYm
	 * @Description: 电量收集记录新增
	 * @param smConsPowerViewVo		用到： ym(yyyyMM格式) 、consIds 、agrePqs 
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月19日 下午7:51:46
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月19日 下午7:51:46
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	public Object addSmConsPowerViewByYm(@RequestBody(required=true) SmConsPowerViewVo smConsPowerViewVo){
		ExecutionResult result = new ExecutionResult();
		try {
			smConsPowerViewService.addSmConsPowerViewByYm(smConsPowerViewVo);
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
	 * @Title: getSmConsPowerView
	 * @Description: 根据consId和ym（抄表年月）获取用电计划列表，及偏差预警信息等信息
	 * @param params
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午9:25:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午9:25:27
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getPowerViewByCondIdAndYm", method = RequestMethod.POST)
	public Object getPowerViewByCondIdAndYm(@RequestBody(required=false) Map<String,String> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String, Object> data = smConsPowerViewService.getPowerViewByCondIdAndYm(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);							//设置数据
			result.setMsg("查询成功！");						//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(null);						//设置返回结果为空
			result.setMsg("查询失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}

	/**
	 * @Title: updatePlanPq
	 * @Description: 更新计划电量，是前台日历页面修改日计划电量（此日之后的所有累计电量会变，所以直接是保存的整月数据）的保存接口
	 * @param smConsPowerViews
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午10:36:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午10:36:34
	 * @since  1.0.0
	 */
	@RequestMapping( value="/updatePlanPq",method = RequestMethod.POST)
	public Object updatePlanPq(@RequestBody List<SmConsPowerView> smConsPowerViews) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsPowerViewService.updatePlanPq(smConsPowerViews);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			if(e instanceof BusinessException) {
				result.setMsg(e.getMessage());					//设置返回消息，根据实际情况修改
			}else {
				result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			}
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
		}
		return result;
	}
	
	/**
	 * 
	 * sendMessage<br/>		发送“月度电量上报提醒”，老版本遗留代码
	 * @param params
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/sendMessage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sendMessage(@RequestBody(required=false) String consId) {
		try {
			return smConsPowerViewService.sendMessage(consId);
		} catch (Exception e) {
			log.error("查询失败",e);				//记录异常日志，根据实际情况修改
			return "发送失败";
		}
	}
	
	/**
	 * @Title: deletePowerViewPlanPq
	 * @Description: 根据用户id 年月删除数据<br/>
	 * @param delParams		格式[{consId : "consId1", ym : "201811" },{consId : "consId2", ym : "201810" }....]
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年12月20日 上午11:06:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年12月20日 上午11:06:20
	 * @since  1.0.0
	 */
	@RequestMapping( value="/del",method = RequestMethod.POST)
	public Object deletePowerViewPlanPq(@RequestBody List<Map<String, String>> delParams) {
		ExecutionResult result = new ExecutionResult();
		try {
			smConsPowerViewService.deletePlanDataByParams(delParams);
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
	 * @Title: delSmConsPowerViews<br/>
	 * @Description: 根据用户id和年月（抄表年月或自然年月）查询用户用电收集列表信息，监控平台的用户电量页面用到
	 * @param consId,ym（yyyyMM格式）,usuallyDateFlag (是否安装抄表年月查询)
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月10日 上午9:45:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月10日 上午9:45:34
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getByUser", method = RequestMethod.POST)
	public Object getSmConsPowerViewByUserIdAndYm(@RequestBody(required=false) Map<String, String> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<SmConsPowerViewDetail> queryResult = smConsPowerViewService.getSmConsPowerViewDetailList(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(queryResult);		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new Object[]{});						//设置返回结果为空
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: getPowerViewDetailListByMonth<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户用电情况,监控平台中的综合电量页面用到<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月10日 上午9:45:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月10日 上午9:45:34
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getByMonth", method = RequestMethod.POST)
	public Object getPowerViewDetailListByMonth(@RequestBody(required=false) SmConsPowerViewVo params) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmConsPowerViewDetail> queryResult = smConsPowerViewService.getSmConsPowerViewByPage2(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setTotal(0);
			result.setRows(new Object[]{});		//设置数据列表
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: getTotalPqDataByMonth
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月所有合同用户 总的计划电量、总用电量、双边电量、竞价电量
	 * @param params
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月23日 下午5:48:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月23日 下午5:48:03
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getTotalPqDataByParams", method = RequestMethod.POST)
	public Object getTotalPqDataByParams(@RequestBody(required=false) SmConsPowerViewVo params) {
		ExecutionResult result = new ExecutionResult();
		try {
			String ym = params.getYm().replace("-", "");
			//获取总的偏差率
			Map<String, String> deviationRateMap = overViewService.getDeviationInfoByYm(params.getYm());
			PhmPurchasePlanMonthDetail queryResult = phmPurchasePlanMonthService.getPhmPurchasePlanMonthListByYm(ym);
			Map<String, String> totalData = smConsPowerViewService.getTotalPqDataByMonth(params);
			//totalData.put("ppaPq", queryResult.getPpaPq() == null ? "0" : queryResult.getPpaPq().toString());
			//totalData.put("dealPq", queryResult.getDealPq() == null ? "0" : queryResult.getDealPq().toString());
			totalData.put("purchasePq", queryResult.getReportPq() == null ? "0"  : queryResult.getReportPq().toString());
			totalData.put("deviationRateTotal", queryResult == null ? null : deviationRateMap.get("deviationRate"));
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(totalData);				//设置数据列表
			result.setMsg("查询成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	/**
	 * @Title: getPowerViewDetailListByMonth<br/>
	 * @Description: 根据用户ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级  查询当月合同用户每天的总用电情况,
	 * 							返回数据为每天一条，每条为所有用户当天的用电加和<br/>
	 * @param SmConsPowerViewVo params 中 ym(年月：yyyy-MM)、elecTypeCode用电类别、voltCode电压等级
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月13日 上午9:45:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月13日 上午9:45:34
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getDayPqByMonth", method = RequestMethod.POST)
	public Object getPowerViewDetailListForDayByMonth(@RequestBody(required=false) SmConsPowerViewVo params) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<SmConsPowerViewDetail> queryResult = smConsPowerViewService.getPowerViewDetailListForDayByMonth(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(queryResult);		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);		//设置数据列表
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: exportExcel
	  * @Description: 用电量导出
	  * @param voltCode
	  * @param elecTypeCode
	  * @param month
	  * @param request
	  * @param response
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年11月19日 上午9:25:51
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年11月19日 上午9:25:51
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public Object exportExcel(@RequestParam String voltCode,@RequestParam String elecTypeCode, @RequestParam String month,HttpServletRequest request,HttpServletResponse response){
		ExecutionResult result = new ExecutionResult();
		try{
			smConsPowerViewService.exportExcel(voltCode, elecTypeCode, month, request,response);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("导出成功！");					//设置返回消息，根据实际情况修改
		}catch (Exception e) {
			log.error("导出失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("导出失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
}
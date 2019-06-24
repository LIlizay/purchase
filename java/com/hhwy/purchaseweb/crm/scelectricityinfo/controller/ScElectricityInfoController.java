package com.hhwy.purchaseweb.crm.scelectricityinfo.controller;

import java.math.BigDecimal;
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
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ForecastPqDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.QueryDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.ScElectricityInfoVo;
import com.hhwy.purchaseweb.crm.scelectricityinfo.domain.TreeGridDetail;
import com.hhwy.purchaseweb.crm.scelectricityinfo.service.ScElectricityInfoService;

/**
 * 
 * <b>类 名 称：</b>ScElectricityInfoController<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月18日 上午8:51:09<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/scElectricityInfoController")
public class ScElectricityInfoController {

	public static final Logger log = LoggerFactory.getLogger(ScElectricityInfoController.class);
	
	/**
	 * 客户历史用电信息的scElectricityInfoService
	 */
	@Autowired
	private ScElectricityInfoService scElectricityInfoService;
	
	/**
	 * 
	 * @Title: getTreeGridByPage<br/>
	 * @Description: 查询树表格数据<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月1日 上午11:29:37
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月1日 上午11:29:37
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getTree", method = RequestMethod.POST)
	public Object getTreeGridByPage(@RequestBody(required=false) ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<TreeGridDetail> queryResult = scElectricityInfoService.getTreeGridByPage(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("树表格查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getForecastPqList<br/>
	 * @Description: 查询预测电量列表<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月2日 下午8:21:32
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月2日 下午8:21:32
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public Object getForecastPqList(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			List<ForecastPqDetail> list = scElectricityInfoService.getForecastPqList(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(list);		//设置数据列表
			result.setMsg("查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setMsg("查询列表失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 根据用户id和年份‘删除’用户历史用电信息预测电量字段数据
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Object deleteScConsumerInfo(@RequestBody Map<String,String> params) {
		ExecutionResult result = new ExecutionResult();
		try {
			scElectricityInfoService.getSCConsElectricity(params);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
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
	 * @Title: calculateForecastPq<br/>
	 * @Description: 测算电量<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月2日 下午8:21:51
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月2日 下午8:21:51
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public Object calculateForecastPq(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			List<BigDecimal> list = scElectricityInfoService.calculateForecastPq(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(list);		//设置数据列表
			result.setMsg("测算成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("测算失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setMsg("测算失败！");			//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public Object saveScConsElectricityList(@RequestBody(required=false) ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scElectricityInfoService.saveScConsElectricityList(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(scElectricityInfoVo.getScConsElectricitieList());
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
	public Object updateScConsElectricity(@RequestBody(required=false) ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scElectricityInfoService.updateScConsElectricity(scElectricityInfoVo);
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
	 * 
	 * @Title: getConsInfoByConsId<br/>
	 * @Description: 根据用户id查询用户数据<br/>
	 * @param scElectricityInfoVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月3日 上午10:15:34
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月3日 上午10:15:34
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getConsInfo", method = RequestMethod.POST)
	public Object getConsInfoByConsId(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryDetail queryDetail = scElectricityInfoService.getConsInfoByConsId(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(queryDetail);
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new QueryDetail());
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * @Title: forecast<br/>
	 * @Description:TODO(查询当前用户有无测算权限)<br/>
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
			String data = scElectricityInfoService.getForecastRes();
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
	
	
														/*           月度电量负荷预测            */
	/**
	 * @Title: getListData<br/>
	 * @Description:TODO(月度电量预测，主列表数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * @throws Exception
	 * QueryResult<ForecastPqDetail>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月15日 下午1:49:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月15日 下午1:49:51
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getListData", method = RequestMethod.POST)
	public Object getListData(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
        ExecutionResult result = new ExecutionResult();
        try {
			QueryResult<ForecastPqDetail> queryResult = scElectricityInfoService.getListData(scElectricityInfoVo);
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
	 * 月度电量预测 删除共鞥你
	 * @param ym: yyyy-mm
	 * by-zhangzhao
	 */
	@RequestMapping(value = "updateMonthPqFore", method = RequestMethod.POST)
	public Object updateMonthPqFore(@RequestBody ScElectricityInfoVo scElectricityInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			scElectricityInfoService.deleteMonthPqFore(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
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
	 * @Title: checkNextMonthData<br/>
	 * @Description:TODO(查询下个月预测数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月14日 下午2:34:06
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月14日 下午2:34:06
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/checkNextMonthData", method = RequestMethod.POST)
	public Object checkNextMonthData(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
        ExecutionResult result = new ExecutionResult();
        try {
			Boolean queryResult = scElectricityInfoService.checkNextMonthData(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(queryResult);
			result.setMsg("查询成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);
			result.setMsg("查询失败！");			//设置返回消息，根据实际情况修改
		}
        return result;
	}
	
	/**
	 * @Title: getMonthPqForetData<br/>
	 * @Description:TODO(年月下，用户的负荷数据)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月14日 下午3:15:38
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月14日 下午3:15:38
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getMonthPqForetData", method = RequestMethod.POST)
	public Object getMonthPqForetData(@RequestBody ScElectricityInfoVo scElectricityInfoVo){
        ExecutionResult result = new ExecutionResult();
        try {
			QueryResult<ForecastPqDetail> queryResult = scElectricityInfoService.getMonthPqForetData(scElectricityInfoVo);
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
	 * @Title: saveMonPqForeList<br/>
	 * @Description:TODO(月度电量预测保存)<br/>
	 * @param scElectricityInfoVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月16日 上午10:32:45
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月16日 上午10:32:45
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveMonPqForeList", method = RequestMethod.POST)
	public Object saveMonPqForeList(@RequestBody(required=false) ScElectricityInfoVo scElectricityInfoVo){
		ExecutionResult result = new ExecutionResult();
		try {
			scElectricityInfoService.saveMonPqForeList(scElectricityInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(scElectricityInfoVo.getScConsElectricitieList());
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
	 * @Title: exportExcel<br/>
	 * @Description:TODO(月度电量预测导出)<br/>
	 * @param ym
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月19日 上午9:44:06
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月19日 上午9:44:06
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public Object exportExcel(@RequestParam String ym, HttpServletRequest request, HttpServletResponse response){
		ExecutionResult result = new ExecutionResult();
		try{
			scElectricityInfoService.exportExcel(ym, request,response);
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
	
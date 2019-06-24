package com.hhwy.purchaseweb.archives.overview.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hhwy.purchaseweb.archives.overview.service.OverViewService;
import com.hhwy.purchaseweb.archives.powerplant.controller.PowerPlantController;

/**
 * 
 * <b>类 名 称：</b>OverViewController<br/>
 * <b>类 描 述：</b>总览页面<br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2017年12月29日 下午1:42:13<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/overViewController")
public class OverViewController {
	
	public static final Logger log = LoggerFactory.getLogger(PowerPlantController.class);
	@Autowired
	private OverViewService overViewService;
	//总览页面数据
	@RequestMapping(value = "/getOverViewData/{userId}",method = RequestMethod.GET)
	public Object getOverViewData(@PathVariable String userId) throws Exception{
		
		 ExecutionResult result = new ExecutionResult();
         try {
        	 Map<String,Object> data = overViewService.getOverViewData(userId);
             result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
             result.setData(data);
             result.setFlag(true);                       //设置是否成功标识
             result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
         } catch (Exception e) {
             log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
             result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
             result.setFlag(false);                      //设置是否成功标识
             result.setData(null);
             result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
         }
         return result;
	}
	//年售电量分月明细
	@RequestMapping(value = "/getSellMonLcBid", method = RequestMethod.POST)
	public Object getSellMonLcBid(@RequestBody(required=false) Map<String,String> params) throws Exception{
		 
		ExecutionResult result = new ExecutionResult();
         try {
        	 List<Map<String, Object>> data = overViewService.getSellMonLcBid(params);
             result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
             result.setData(data);
             result.setFlag(true);                       //设置是否成功标识
             result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
         } catch (Exception e) {
             log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
             result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
             result.setFlag(false);                      //设置是否成功标识
             result.setRows(new Object[]{});
             result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
         }
         return result;
		
	}
	
	
	//年购电量分月分析
	@RequestMapping(value = "getPhMonLcBid", method = RequestMethod.POST)
	public Object getPhMonLcBid(@RequestBody(required=false) Map<String,String> params) throws Exception{
		ExecutionResult result = new ExecutionResult();
        try {
        	List<Map<String,Object>> data = overViewService.getPhMonLcBid(params);
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setRows(data);
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setRows(new Object[]{});
            result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
        }
        return result;
	}
	
	//用户规模明细
		@RequestMapping(value = "getConsScale", method = RequestMethod.GET)
		public Object getConsScale() throws Exception{
			ExecutionResult result = new ExecutionResult();
	        try {
	        	Map<String, Object> data = overViewService.getConsScale();
	            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
	            result.setData(data);
	            result.setFlag(true);                       //设置是否成功标识
	            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
	        } catch (Exception e) {
	            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
	            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
	            result.setFlag(false);                      //设置是否成功标识
	            result.setData(null);
	            result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
	        }
	        return result;
		}
		
		//用户规模柱状图
		@RequestMapping(value = "getConsBar/{year}" ,method = RequestMethod.GET)
		public Object getconsBar(@PathVariable String year){
			ExecutionResult result = new ExecutionResult();
			try {
				Map<String, Object> data = overViewService.getConsBar(year);
	            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
	            result.setData(data);
	            result.setFlag(true);                       //设置是否成功标识
	            result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
			} catch (Exception e) {
	            log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
	            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
	            result.setFlag(false);                      //设置是否成功标识
	            result.setData(null);
	            result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
			}
			 return result;
			
		}
		
		/**
		 * @Title: getDeviationInfo
		 * @Description: 获取当月的偏差用户数和总用电偏差率
		 * @return 
		 * Object
		 * <b>创 建 人：</b>wangzelu<br/>
		 * <b>创建时间:</b>2018年3月14日 下午6:41:25
		 * <b>修 改 人：</b>wangzelu<br/>
		 * <b>修改时间:</b>2018年3月14日 下午6:41:25
		 * @since  1.0.0
		 */
		@RequestMapping(value = "getDeviationInfo" ,method = RequestMethod.GET)
		public Object getDeviationInfo(){
			ExecutionResult result = new ExecutionResult();
			try {
				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM");
				String ym  = dataFormat.format(new Date());
				Map<String, String> data = overViewService.getDeviationInfoByYm(ym);
				result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
				result.setData(data);
				result.setFlag(true);                       //设置是否成功标识
				result.setMsg("查询成功！");                 //设置返回消息，根据实际情况修改
			} catch (Exception e) {
				log.error("查询失败",e);                        //记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
				result.setFlag(false);                      //设置是否成功标识
				result.setData(null);
				result.setMsg("查询失败！"+e.getMessage());     //设置返回消息，根据实际情况修改
			}
			return result;
		}
}

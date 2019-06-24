package com.hhwy.purchaseweb.delivery.entersettle.controller;

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
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleDetail;
import com.hhwy.purchaseweb.delivery.entersettle.domain.EnterSettleVo;
import com.hhwy.purchaseweb.delivery.entersettle.domain.SettleDetailDetail;
import com.hhwy.purchaseweb.delivery.entersettle.service.EnterSettleService;

@RestController
@RequestMapping("/enterSettleController")
public class EnterSettleController {
	
	public static final Logger log = LoggerFactory.getLogger(EnterSettleController.class);
	
	 @Autowired
	 private EnterSettleService enterSettleService;
	 
	 /**
	   * @Title: getEnterSettleByPage
	   * @Description: 分页获取结算列表
	   * @param enterSettleVo
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月16日 下午5:00:14
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月16日 下午5:00:14
	   * @since  1.0.0
	  */
	 @RequestMapping( value = "/page", method = RequestMethod.POST)
	 public Object getEnterSettleByPage(@RequestBody(required=false) EnterSettleVo enterSettleVo){
		 ExecutionResult result = new ExecutionResult();
	        try {
	            QueryResult<EnterSettleDetail> queryResult = enterSettleService.getEnterSettleByPage(enterSettleVo);
	            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
	            result.setFlag(true);                       //设置是否成功标识
	            result.setTotal(queryResult.getTotal());    //设置数据总条数
	            result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());      //设置数据列表
	            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
	        } catch (Exception e) {
	            // TODO: handle exception
	            log.error("分页查询列表失败",e);               //记录异常日志，根据实际情况修改
	            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
	            result.setFlag(false);                      //设置是否成功标识
	            result.setRows(new Object[]{});             //设置返回结果为空
	            result.setTotal(0);                         //设置数据总条数为0
	            result.setMsg("分页查询列表失败！");         	//设置返回消息，根据实际情况修改
	            return result;
	        }
	        return result;
	 }
	 
	 /**
	   * @Title: getConsDetailByYm
	   * @Description: 根据年月获取合同用户信息
	   * @param enterSettleVo
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月16日 下午5:00:47
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月16日 下午5:00:47
	   * @since  1.0.0
	  */
	 @RequestMapping( value = "/getConsDetailByYm", method = RequestMethod.POST)
	 public Object getConsDetailByYm(@RequestBody(required=false) EnterSettleVo enterSettleVo){
		 ExecutionResult result = new ExecutionResult();
	        try {
	        	String ym = enterSettleVo.getPhmEnterSettle().getYm();
	            List<SettleDetailDetail> detailList = enterSettleService.getEnterSettleDetailByYm(ym);
	            Map<String,Object> map = enterSettleService.getPurchasePqByYm(ym);
	            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
	            result.setFlag(true);                       //设置是否成功标识
	            result.setData(detailList);
	            result.setExtMap(map);
	            result.setMsg("分页查询列表成功！");         //设置返回消息，根据实际情况修改
	        } catch (Exception e) {
	            // TODO: handle exception
	            log.error("分页查询列表失败",e);               //记录异常日志，根据实际情况修改
	            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
	            result.setFlag(false);                      //设置是否成功标识
	            result.setRows(new Object[]{});             //设置返回结果为空
	            result.setTotal(0);                         //设置数据总条数为0
	            result.setMsg("分页查询列表失败！");         	//设置返回消息，根据实际情况修改
	            return result;
	        }
	        return result;
	 }
	 
	 /**
	   * @Title: getSettleDetail
	   * @Description: 根据结算id获取合同用户结算数据
	   * @param id
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月16日 下午5:01:19
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月16日 下午5:01:19
	   * @since  1.0.0
	  */
	 @RequestMapping(value = "/getSettleDetailById", method = RequestMethod.POST)
	 public Object getSettleDetailById(@RequestBody(required=false) EnterSettleVo enterSettleVo){
		 ExecutionResult result = new ExecutionResult();
			try{
				String ym = enterSettleVo.getPhmEnterSettle().getYm();
				List<SettleDetailDetail> detailList = enterSettleService.getSettleDetailDetail(enterSettleVo);
				Map<String,Object> map = enterSettleService.getPurchasePqByYm(ym);
				result.setExtMap(map);
				result.setData(detailList); // 设置数据实体
				result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
				result.setFlag(true); // 设置是否成功标识
			}catch (Exception e){
				log.error("查询失败", e); // 记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
				result.setFlag(false); // 设置是否成功标识
				result.setData(null); // 设置返回结果为空
				result.setMsg("查询失败！"); // 设置返回消息，根据实际情况修改
			}
			return result;
	 }
	 
	 /**
	   * @Title: saveOrUpdateSettle
	   * @Description: 新增或编辑结算数据
	   * @param enterSettleVo
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月17日 下午2:01:13
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月17日 下午2:01:13
	   * @since  1.0.0
	  */
	 @RequestMapping(method = RequestMethod.POST)
	 public Object saveOrUpdateSettle(@RequestBody EnterSettleVo enterSettleVo) {
		 ExecutionResult result = new ExecutionResult();
		 	try{
		 		enterSettleService.saveOrUpdateSettle(enterSettleVo);
				result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
				result.setFlag(true); // 设置是否成功标识
			}catch (Exception e){
				log.error("查询失败", e); // 记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
				result.setFlag(false); // 设置是否成功标识
				result.setData(null); // 设置返回结果为空
				result.setMsg("查询失败！"); // 设置返回消息，根据实际情况修改
			}
		 return result;
	 }

	 /**
	   * @Title: getCheckRepeatByMonth
	   * @Description: 根据月份查重
	   * @param enterSettleVo
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月16日 下午5:02:22
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月16日 下午5:02:22
	   * @since  1.0.0
	  */
	 @RequestMapping( value = "/checkRepeatByMonth", method = RequestMethod.POST)
	 public Object getCheckRepeatByMonth(@RequestBody(required=false) EnterSettleVo enterSettleVo){
		 ExecutionResult result = new ExecutionResult();
			try{
				String ym = enterSettleVo.getPhmEnterSettle().getYm();
				boolean flag = enterSettleService.checkRepeatByMonth(ym);
				result.setData(flag); // 设置数据实体
				result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
				result.setFlag(true); // 设置是否成功标识
			}catch (Exception e){
				log.error("查询失败", e); // 记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
				result.setFlag(false); // 设置是否成功标识
				result.setData(null); // 设置返回结果为空
				result.setMsg("查询失败！"); // 设置返回消息，根据实际情况修改
			}
			return result;
	 }
	 
	 /**
	   * @Title: deleteSettle
	   * @Description: 根据ids删除结算相关数据
	   * @param ids
	   * @return Object
	   * <b>创 建 人：</b>LiXinze<br/>
	   * <b>创建时间:</b>2018年5月17日 下午1:59:46
	   * <b>修 改 人：</b>LiXinze<br/>
	   * <b>修改时间:</b>2018年5月17日 下午1:59:46
	   * @since  1.0.0
	  */
	 @RequestMapping(method = RequestMethod.DELETE)
	 public Object deleteSettle(@RequestBody String[] ids){
		 ExecutionResult result = new ExecutionResult();
		 try {
			enterSettleService.deleteSettle(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	 }
}

package com.hhwy.purchaseweb.settlement.base.smsettlementmonth.controller;

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
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthDetail;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain.SmSettlementMonthVo;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;

 /**
 * <b>类 名 称：</b>SmSettlementMonthController<br/>
 * <b>类 描 述：</b><br/>			月度收益结算
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年5月26日 下午4:55:26<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/smSettlementMonthController")
public class SmSettlementMonthController {

	public static final Logger log = LoggerFactory.getLogger(SmSettlementMonthController.class);
	
	/**
	 * smSettlementMonthService 结算主表service
	 */
	@Autowired
	private SmSettlementMonthService smSettlementMonthService;
	
	
	/**
	 * 分页获取对象SmSettlementMonth
	 * @param ID
	 * @return SmSettlementMonth
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmSettlementMonthByPage(@RequestBody(required=false) SmSettlementMonthVo smSettlementMonthVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmSettlementMonthDetail> queryResult = smSettlementMonthService.getSmSettlementMonthByPage(smSettlementMonthVo);
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
	 * @Title: getSmSettlementMonthInfoByIdOrYm
	 * @Description: 通过结算id或者年月获取结算方案列表页面上面的的form表单中的数据
	 * @param settleId
	 * @param ym
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月27日 上午10:00:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月27日 上午10:00:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getFormDateBySettleIdOrYm", method = RequestMethod.GET)
	public Object getSmSettlementMonthInfoByIdOrYm(String settleId, String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmSettlementMonthDetail smSettlementMonth = smSettlementMonthService.getSmSettlementMonthInfoByIdOrYm(settleId, ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smSettlementMonth);			//设置数据实体
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
	
	/**
	 * 添加对象SmSettlementMonth
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmSettlementMonth(@RequestBody SmSettlementMonth smSettlementMonth) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSettlementMonthService.saveSmSettlementMonth(smSettlementMonth);
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
	 * 更新对象SmSettlementMonth
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmSettlementMonth(@RequestBody SmSettlementMonth smSettlementMonth) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSettlementMonthService.updateSmSettlementMonth(smSettlementMonth);
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
	 * @Title: deleteSmSettlementMonth
	 * @Description: 先验证是否含有已归档的结算方案，如果没有则删除一个月度结算的所有相关信息(江苏)
	 * @param id
	 * @return  Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月26日 下午5:47:42
	 * <b>修 改 人：</b>LiXinze<br/>
	 * <b>修改时间:</b>2018年5月26日 下午5:47:42
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmSettlementMonth(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smSettlementMonthService.validateDeleteSettle(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setMsg(e.getMessage());				//设置返回消息，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
		}
		return result;
	}	
}
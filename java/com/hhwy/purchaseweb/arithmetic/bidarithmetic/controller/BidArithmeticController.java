package com.hhwy.purchaseweb.arithmetic.bidarithmetic.controller;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidResultDetail;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain.BidSearchVo;
import com.hhwy.purchaseweb.arithmetic.bidarithmetic.service.BidArithmeticService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 /**
 * <b>类 名 称：</b>BidArithmeticController<br/>
 * <b>类 描 述：</b><br/>中标算法测试请求Controller
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年04月08日 下午7:58:30<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/bidArithmeticController")
public class BidArithmeticController {

	public static final Logger log = LoggerFactory.getLogger(BidArithmeticController.class);
	
	/**
	 * scCatPrcService
	 */
	@Autowired
	private BidArithmeticService bidArithmeticService;
	
	/**
	 * 分页获取对象ScCatPrc
	 * @param ID
	 * @return ScCatPrc
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScCatPrcByPage(@RequestBody(required=false) BidSearchVo bidSearchVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<BidResultDetail> queryResult = bidArithmeticService.getBidListInfo(bidSearchVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal() == null? 0:queryResult.getTotal());	//设置数据总条数
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
	
}
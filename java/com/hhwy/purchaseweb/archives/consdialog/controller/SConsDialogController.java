package com.hhwy.purchaseweb.archives.consdialog.controller;

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
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogDetail;
import com.hhwy.purchaseweb.archives.consdialog.domain.ConsDialogVo;
import com.hhwy.purchaseweb.archives.consdialog.service.ConsDialogService;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;

/**
 * ConsDialogController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/sConsDialogController")
public class SConsDialogController {

	public static final Logger log = LoggerFactory.getLogger(SConsDialogController.class);
	
	/**
	 * consDialogService
	 */
	@Autowired
	private ConsDialogService consDialogService;
	
	/**
     * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
     * @param scOrgInfoVo       查询条件
     * @return QueryResult<ScOrgInfoDetail>
     */
    @RequestMapping( value = "/orgPage", method = RequestMethod.POST)
    public Object getScOrgInfoByPage(@RequestBody(required=false) ConsDialogVo consDialogVo) {
        ExecutionResult result = new ExecutionResult();
        try {
            QueryResult<ScOrgInfoDetail> queryResult = consDialogService.getScOrgInfoByPage(consDialogVo);
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
        }
        return result;
    }
	
	/**
	 * 公共组件回显方法
	 * @param ID
	 * @return ScConsumerInfo
	 */
	@RequestMapping(value = "/getConsInfoById/{id}", method = RequestMethod.GET)
	public ConsDialogDetail getConsInfoById(@PathVariable String id) {
		try {
			ConsDialogDetail ConsDialogDetail = consDialogService.getConsInfo(id);;
			return ConsDialogDetail;			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			return null;
		}
	}

	/**
	 * 
	 * @Title: getConsList
	 * @Description: 用户选择列表
	 * @return 
	 * Object
	 * <b>创 建 人：</b>xucong<br/>
	 * <b>创建时间:</b>2016年12月16日 下午1:25:02
	 * <b>修 改 人：</b>xucong<br/>
	 * <b>修改时间:</b>2016年12月16日 下午1:25:02
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getConsList", method = RequestMethod.POST)
	public Object getConsList(@RequestBody(required=false) ConsDialogVo consDialogVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ConsDialogDetail> queryResult = consDialogService.getConsList(consDialogVo);
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
	 * @Title: getConsListForMonitor
	 * @Description: 监控平台->用户电量 页面的选择用户弹出框
	 * @param consDialogVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月23日 下午9:19:49
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月23日 下午9:19:49
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getConsListForMonitor", method = RequestMethod.POST)
	public Object getConsListForMonitor(@RequestBody(required=false) ConsDialogVo consDialogVo){
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ConsDialogDetail> queryResult = consDialogService.getConsListForMonitor(consDialogVo);
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
}
package com.hhwy.purchaseweb.crm.ssagrescheme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SsAgreScheme;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SmPpaVo;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeDetail;
import com.hhwy.purchaseweb.crm.ssagrescheme.domain.SsAgreSchemeVo;
import com.hhwy.purchaseweb.crm.ssagrescheme.service.SsAgreSchemeService;

/**
 * 合同辅助设计SsAgreSchemeController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/ssAgreSchemeController")
public class SsAgreSchemeController {

	public static final Logger log = LoggerFactory.getLogger(SsAgreSchemeController.class);
	
	/**
	 * 合同辅助设计ssAgreSchemeService
	 */
	@Autowired
	private SsAgreSchemeService ssAgreSchemeService;
	
	
	/**
	 * @Title: getSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:30:38
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:30:38
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSsAgreSchemeByPage(@RequestBody(required=false) SsAgreSchemeVo ssAgreSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SsAgreSchemeDetail> queryResult = ssAgreSchemeService.getSsAgreSchemeByPage(ssAgreSchemeVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
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
	 * @Title: getTreeSsAgreSchemeByPage<br/>
	 * @Description: 分页获取合同辅助设计对象（树表格）<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:30:19
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:30:19
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/treepage", method = RequestMethod.POST)
	public Object getTreeSsAgreSchemeByPage(@RequestBody(required=false) SsAgreSchemeVo ssAgreSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SsAgreSchemeDetail> queryResult = ssAgreSchemeService.getTreeSsAgreSchemeByPage(ssAgreSchemeVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
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
	 * @Title: getSsAgreSchemeById<br/>
	 * @Description: 根据ID获取合同辅助设计对象<br/>
	 * @param id
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:31:07
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:31:07
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSsAgreSchemeById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SsAgreScheme ssAgreScheme = ssAgreSchemeService.getSsAgreSchemeById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(ssAgreScheme);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	/**
	 * @Title: saveSsAgreScheme<br/>
	 * @Description: 添加合同辅助设计对象<br/>
	 * @param ssAgreScheme
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:31:19
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:31:19
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSsAgreScheme(@RequestBody SsAgreScheme ssAgreScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.saveSsAgreScheme(ssAgreScheme);
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
	 * @Title: updateSsAgreScheme<br/>
	 * @Description: 更新合同辅助设计对象<br/>
	 * @param ssAgreScheme
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:31:31
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:31:31
	 * @since  1.0.0
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSsAgreScheme(@RequestBody SsAgreScheme ssAgreScheme) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.updateSsAgreScheme(ssAgreScheme);
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
	 * @Title: deleteSsAgreScheme<br/>
	 * @Description: 删除合同辅助设计对象<br/>
	 * @param ids
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:31:46
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:31:46
	 * @since  1.0.0
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSsAgreScheme(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.deleteSsAgreScheme(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	  * @Title: calculate
	  * @Description: 计算方案
	  * @param ssAgreSchemeDetail
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月25日 下午2:31:31
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月25日 下午2:31:31
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/calculate", method = RequestMethod.POST)
	public Object calculate(@RequestBody(required=true) SsAgreSchemeDetail ssAgreSchemeDetail) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.calculate(ssAgreSchemeDetail);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(ssAgreSchemeDetail);			//设置数据实体
			result.setMsg("计算成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("计算失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(ssAgreSchemeDetail);			
			result.setMsg(e.getMessage());				//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * @Title: getSsAgreSchemeListByConsName<br/>
	 * @Description: 根据客户Id查询所有方案<br/>
	 * @param consId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:29:25
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:29:25
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/getSsAgreSchemeListByConsId", method = RequestMethod.GET)
	public Object getSsAgreSchemeListByConsName(@RequestParam(required=true, value="consId") String consId){
		ExecutionResult result = new ExecutionResult();
		SsAgreSchemeVo ssAgreSchemeVo = new SsAgreSchemeVo();
		SsAgreScheme ssAgreScheme = new SsAgreScheme();
		try {
			ssAgreScheme.setConsId(consId);
			ssAgreSchemeVo.setSsAgreScheme(ssAgreScheme);
			List<SsAgreSchemeDetail> ssAgreSchemeList = ssAgreSchemeService.getSsAgreSchemeListByConsName(ssAgreSchemeVo);
			result.setCode("999");		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setRows(ssAgreSchemeList);		//设置数据列表
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
	 * @Title: commitSsAgreScheme<br/>
	 * @Description: 提交对方案的操作<br/>
	 * @param ssAgreSchemeVo
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年5月23日 下午2:29:10
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年5月23日 下午2:29:10
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/commit", method = RequestMethod.POST)
	public Object commitSsAgreScheme(@RequestBody SsAgreSchemeVo ssAgreSchemeVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.commitSsAgreScheme(ssAgreSchemeVo);
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
	 * 将合同辅助设计转换为实际合同
	 * @param 实体对象
	 */
	@RequestMapping( value = "/changeToContract",method = RequestMethod.POST)
	public Object changeToContract(@RequestBody SmPpaVo smPpaVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			if( "".equals(smPpaVo.getSmPpa().getConsId()) || null == smPpaVo.getSmPpa().getConsId()){
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setData(new SmPpaVo());              //设置返回结果
				result.setMsg("用户名称参数为空!转换失败");
			}
			SmPpaVo resultSmPpaVo = ssAgreSchemeService.saveSmPpa(smPpaVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultSmPpaVo);              //设置返回结果
			result.setMsg("转换成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("转换失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SmPpaVo());              //设置返回结果
			result.setMsg("转换失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	/**
	 * 
	 * @Title: outExcel
	 * @Description: TODO(页面导出功能)
	 * @param ids
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhangzhao<br/>
	 * <b>创建时间:</b>2017年12月5日 上午9:11:13
	 * <b>修 改 人：</b>sunqi<br/>
	 * <b>修改时间:</b>2017年12月5日 上午9:11:13
	 * @since  1.0.0
	 */
	
	@RequestMapping(value = "/outExcel", method = RequestMethod.GET)
	public Object outExcel(@RequestParam String ids,HttpServletRequest request,HttpServletResponse response){
		ExecutionResult result = new ExecutionResult();
		try {
			ssAgreSchemeService.outExcel(ids, request, response);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("导出成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("导出失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("导出失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	
	
	
	
	
	
	
}
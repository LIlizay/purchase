package com.hhwy.purchaseweb.delivery.smfundprcinfo.controller;

import java.util.List;

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
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoVo;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.service.SmFundPrcInfoService;
import com.hhwy.selling.domain.SmFundPrcInfo;

/**
 * SmFundPrcInfoController
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smFundPrcInfoController")
public class SmFundPrcInfoController {

	public static final Logger log = LoggerFactory
			.getLogger(SmFundPrcInfoController.class);

	/**
	 * smFundPrcInfoService
	 */
	@Autowired
	private SmFundPrcInfoService smFundPrcInfoService;

	/**
	 * 分页获取对象SmFundPrcInfo
	 * 
	 * @param ID
	 * @return SmFundPrcInfo
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public Object getSmFundPrcInfoByPage(
			@RequestBody(required = false) SmFundPrcInfoVo smFundPrcInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmFundPrcInfoDetail> queryResult = smFundPrcInfoService
					.getSmFundPrcInfoByPage(smFundPrcInfoVo);
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setTotal(queryResult.getTotal()); // 设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult
					.getData() : queryResult.getRows()); // 设置数据列表
			result.setMsg("分页查询列表成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("分页查询列表失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setRows(new Object[] {}); // 设置返回结果为空
			result.setTotal(0); // 设置数据总条数为0
			result.setMsg("分页查询列表失败！"); // 设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}

	/**
	 * 根据ID获取对象SmFundPrcInfo
	 * 
	 * @param ID
	 * @return SmFundPrcInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmFundPrcInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmFundPrcInfo smFundPrcInfo = smFundPrcInfoService
					.getSmFundPrcInfoById(id);
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setData(smFundPrcInfo); // 设置数据实体
			result.setMsg("查询成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setData(null); // 设置返回结果为空
			result.setMsg("查询失败！"); // 设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}

	/**
	 * 添加对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object saveSmFundPrcInfo(@RequestBody SmFundPrcInfo smFundPrcInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			smFundPrcInfoService.saveSmFundPrcInfo(smFundPrcInfo);
			result.setData(smFundPrcInfo);
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("保存成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("保存失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;

	}

	/**
	 * @Title: saveSmFundPrcInfoList
	 * @Description: 保存政府性基金及附加列表
	 * @param smFundPrcInfo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年2月27日 下午2:57:09
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年2月27日 下午2:57:09
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public Object saveSmFundPrcInfoList(@RequestBody List<SmFundPrcInfo> smFundPrcInfos) {
		ExecutionResult result = new ExecutionResult();
		try {
			smFundPrcInfoService.saveSmFundPrcInfoList(smFundPrcInfos);
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("保存成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("保存失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	 * 更新对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Object updateSmFundPrcInfo(@RequestBody SmFundPrcInfo smFundPrcInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			smFundPrcInfoService.updateSmFundPrcInfo(smFundPrcInfo);
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("更新成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("更新失败！"); // 设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}

	/**
	 * 删除对象SmFundPrcInfo
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmFundPrcInfo(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smFundPrcInfoService.deleteSmFundPrcInfo(new String[] { id });
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("删除成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("删除失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}

	@RequestMapping(value = "/deleteByPrcId/{prcId}", method = RequestMethod.DELETE)
	public Object deleteSmFundPrcInfoByPrcId(@PathVariable String prcId) {
		ExecutionResult result = new ExecutionResult();
		try {
			smFundPrcInfoService.deleteSmFundPrcInfoByPrcIds(new String[] { prcId });
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setMsg("删除成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("删除失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}
}
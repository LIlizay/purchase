package com.hhwy.purchaseweb.delivery.smprcinfo.controller;

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
import com.hhwy.purchase.domain.SmPrcInfo;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smprcinfo.domain.SmPrcInfoVo;
import com.hhwy.purchaseweb.delivery.smprcinfo.service.SmPrcInfoService;

/**
 * SmPrcInfoController
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smPrcInfoController")
public class SmPrcInfoController {

	public static final Logger log = LoggerFactory.getLogger(SmPrcInfoController.class);

	/**
	 * smPrcInfoService
	 */
	@Autowired
	private SmPrcInfoService smPrcInfoService;

	/**
	 * @Title: getPrcInfoVoGroup
	 * @Description: 根据省份code获取其下所有电价列表
	 * @param smPrcInfoVo
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月21日 下午7:13:33
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月21日 下午7:13:33
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getPrcInfoVoGroup", method = RequestMethod.POST)
	public Object getPrcInfoVoGroup(@RequestBody(required = false) SmPrcInfoVo smPrcInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			if (smPrcInfoVo.getProvinceId() == null || "".equals((smPrcInfoVo.getProvinceId()))) {
				result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
				result.setFlag(false); // 设置是否成功标识
				result.setData(new Object[] {}); // 设置返回结果为空
				result.setTotal(0); // 设置数据总条数为0
				result.setMsg("参数错误,查询列表失败！"); // 设置返回消息，根据实际情况修改
			} else {
				List<SmPrcInfoVo> queryResult = smPrcInfoService.getSmPrcInfoGroupByProvinceId(smPrcInfoVo);
				result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
				result.setFlag(true); // 设置是否成功标识
				result.setData(queryResult); // 设置数据列表
				result.setMsg("查询多个列表成功！"); // 设置返回消息，根据实际情况修改
			}
		} catch (Exception e) {
			log.error("查询多个列表失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setData(new Object[] {}); // 设置返回结果为空
			result.setTotal(0); // 设置数据总条数为0
			result.setMsg("查询多个列表失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}
	
	/**
	  * @Title: getSmPrcInfoByConsId
	  * @Description: 根据用户id获取电价信息
	  * @param id
	  * @return Object
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年2月8日 下午5:30:02
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年2月8日 下午5:30:02
	  * @since  1.0.0
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmPrcInfoByConsId(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try{
			SmPrcInfo prc = smPrcInfoService.getSmPrcInfoByConsId(id);
			if(prc != null){
				result.setMsg("查询成功！"); // 设置返回消息，根据实际情况修改
			}else{
				result.setMsg("没有维护电价信息，请联系系统管理员！"); // 设置返回消息，根据实际情况修改
			}
			result.setData(prc); // 设置数据实体
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
	 * @Title: getInitSmPrcInfoList
	 * @Description: 获取初始的电价列表（主要获取初始的 用电类别、电压等级）
	 * @return 
	 * Object
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年11月21日 下午7:41:23
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年11月21日 下午7:41:23
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/getInitPrcInfoList", method = RequestMethod.GET)
	public Object getInitSmPrcInfoList() {
		ExecutionResult result = new ExecutionResult();
		try {
			List<SmPrcInfoDetail> smPrcInfoDetails = smPrcInfoService.getInitSmPrcInfoList();
			result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
			result.setFlag(true); // 设置是否成功标识
			result.setData(smPrcInfoDetails); // 设置数据实体
			result.setMsg("查询成功！"); // 设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setData(null); // 设置返回结果为空
			result.setMsg("查询失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}

	/**
	 * 添加对象SmPrcInfo
	 * 
	 * @param 实体对象
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object saveOrUpdateSmPrcInfo(@RequestBody SmPrcInfoVo smPrcInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			String msg = smPrcInfoService.saveOrUpdateSmPrcInfoVo(smPrcInfoVo);
			if(msg == null || "".equals(msg)) {
				result.setCode(ReturnCode.RES_SUCCESS); // 设置返回结果编码：成功
				result.setFlag(true); // 设置是否成功标识
				result.setMsg("保存成功"); // 设置返回消息，根据实际情况修改
			}else {
				result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：成功
				result.setFlag(false); // 设置是否成功标识
				result.setMsg(msg); // 设置返回消息，根据实际情况修改
			}
		} catch (Exception e) {
			log.error("保存失败", e); // 记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED); // 设置返回结果编码：失败
			result.setFlag(false); // 设置是否成功标识
			result.setMsg("保存失败！"); // 设置返回消息，根据实际情况修改
		}
		return result;
	}

	/**
	 * 更新对象SmPrcInfo
	 * 
	 * @param 实体对象
	 */
	/*@RequestMapping(method = RequestMethod.PUT)
	public Object updateSmPrcInfo(@RequestBody SmPrcInfo smPrcInfo) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPrcInfoService.updateSmPrcInfo(smPrcInfo);
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
	}*/

	/**
	 * 删除对象SmPrcInfo
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object deleteSmPrcInfo(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smPrcInfoService.deleteSmPrcInfo(ids);
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
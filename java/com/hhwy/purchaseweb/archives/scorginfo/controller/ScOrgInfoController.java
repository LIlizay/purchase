package com.hhwy.purchaseweb.archives.scorginfo.controller;

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
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoVo;
import com.hhwy.purchaseweb.archives.scorginfo.service.ScOrgInfoService;
import com.hhwy.selling.domain.ScOrgInfo;

 /**
 * <b>类 名 称：</b>ScOrgInfoController<br/>
 * <b>类 描 述：</b><br/>		供电公司信息的controller
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2016年11月22日 下午4:00:03<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@RestController
@RequestMapping("/scOrgInfoController")
public class ScOrgInfoController {

	public static final Logger log = LoggerFactory.getLogger(ScOrgInfoController.class);
	
	/**
	 * scOrgInfoService 供电公司的service
	 */
	@Autowired
	private ScOrgInfoService scOrgInfoService;
	
	
	/**
	 * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param scOrgInfoVo		查询条件
	 * @return QueryResult<ScOrgInfoDetail>
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getScOrgInfoByPage(@RequestBody(required=false) ScOrgInfoVo scOrgInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<ScOrgInfoDetail> queryResult = scOrgInfoService.getScOrgInfoByPage(scOrgInfoVo);
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
	 * 根据ID获取对象ScOrgInfo(供电公司信息)
	 * @param ID
	 * @return ScOrgInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getScOrgInfoById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			ScOrgInfo scOrgInfo = scOrgInfoService.getScOrgInfoById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(scOrgInfo);			//设置数据实体
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
	 * 添加对象ScOrgInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveScOrgInfo(@RequestBody ScOrgInfo scOrgInfo){
		ExecutionResult result = new ExecutionResult();
		try {
			scOrgInfoService.checkScOrgInfo(scOrgInfo);
			scOrgInfoService.saveScOrgInfo(scOrgInfo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败!"+e.getMessage());					//设置返回消息，根据实际情况修改
		}
		return result;
		
	}
	
	/**
	 * 更新对象ScOrgInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateScOrgInfo(@RequestBody ScOrgInfo scOrgInfo) {
		ExecutionResult result = new ExecutionResult();
		if(scOrgInfo == null || scOrgInfo.getId() ==  null || "".equals(scOrgInfo.getId().trim())){
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("参数无效，更新失败！");					//设置返回消息，根据实际情况修改
		}else{
			try {
				scOrgInfoService.checkScOrgInfo(scOrgInfo);
				scOrgInfoService.updateScOrgInfo(scOrgInfo);
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
			} catch (Exception e) {
				log.error("更新失败",e);						//记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			}
		}
		return result;
	}
	
	/**
	 * 删除对象ScOrgInfo
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteScOrgInfo(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		if(id ==  null || "".equals(id.trim())){
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("参数无效，删除失败！");					//设置返回消息，根据实际情况修改
		}else{
			try {
				scOrgInfoService.deleteScOrgInfo(new String[]{id});
				result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
				result.setFlag(true);						//设置是否成功标识
				result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
			} catch (Exception e) {
				log.error("删除失败",e);						//记录异常日志，根据实际情况修改
				result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
				result.setFlag(false);						//设置是否成功标识
				result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			}
		}
		return result;
	}	
	
	/**
	 * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param scOrgInfoVo		查询条件
	 * @return QueryResult<ScOrgInfoDetail>
	 */
	@RequestMapping( value = "/getSelectOrgList", method = RequestMethod.GET)
	public List<ScOrgInfo> getSelectOrgList() {
		try {
			return scOrgInfoService.getSelectOrgList();
		} catch (Exception e) {
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			return null;
		}
	}
	
}
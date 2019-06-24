package com.hhwy.purchaseweb.swhtmlinfo.controller;

import javax.servlet.http.HttpServletRequest;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfo;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoDetail;
import com.hhwy.purchaseweb.swhtmlinfo.domain.SwHtmlInfoVo;
import com.hhwy.purchaseweb.swhtmlinfo.service.SwHtmlInfoService;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SwHtmlInfoController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/swHtmlInfoController")
public class SwHtmlInfoController {

	public static final Logger log = LoggerFactory.getLogger(SwHtmlInfoController.class);
	
	/**
	 * swHtmlInfoService
	 */
	@Autowired
	private SwHtmlInfoService swHtmlInfoService;
	
	
	/**
	 * 分页获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSwHtmlInfoByPage(@RequestBody(required=false) SwHtmlInfoVo swHtmlInfoVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			if(swHtmlInfoVo.getSwHtmlInfo().getReleaseTime() != null && !"".equals(swHtmlInfoVo.getSwHtmlInfo().getReleaseTime())){
				String time = swHtmlInfoVo.getSwHtmlInfo().getReleaseTime().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				swHtmlInfoVo.setReleaseTime(sdf.format(sdf.parse(time)));
			}
			QueryResult<SwHtmlInfoDetail> queryResult = swHtmlInfoService.getSwHtmlInfoByPage(swHtmlInfoVo);
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
			return result;
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SwHtmlInfo
	 * @param ID
	 * @return SwHtmlInfo
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSwHtmlInfoById(@PathVariable String id,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwHtmlInfoDetail swHtmlInfoDetail = swHtmlInfoService.getSwHtmlInfoById(id,request);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swHtmlInfoDetail);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 添加对象SwHtmlInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSwHtmlInfo(@RequestBody SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwHtmlInfo swHtmlInfoResult = swHtmlInfoService.saveSwHtmlInfo(swHtmlInfoVo,request);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swHtmlInfoResult);	        //设置返回结果数据
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SwHtmlInfo());	        //设置返回结果数据
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象SwHtmlInfo
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSwHtmlInfo(@RequestBody SwHtmlInfoVo swHtmlInfoVo,HttpServletRequest request) {
		ExecutionResult result = new ExecutionResult();
		try {
			swHtmlInfoService.updateSwHtmlInfo(swHtmlInfoVo,request);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象SwHtmlInfo
	 * @param id
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Object deleteSwHtmlInfo(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			swHtmlInfoService.deleteSwHtmlInfo(ids);
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
	
	/**
	 * 
	 * @Title: releaseseSwHtmlInfo
	 * @Description:(发布网站信息维护列表信息)
	 * @param ids
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月8日 下午1:48:04
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月8日 下午1:48:04
	 * @since  1.0.0
	 */
	@RequestMapping(value="/releaseseSwHtmlInfo",method = RequestMethod.POST)
	public Object releaseseSwHtmlInfo(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			swHtmlInfoService.releaseseSwHtmlInfo(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("发布成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("发布失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("发布失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: cancelReleaseseSwHtmlInfo
	 * @Description:(取消发布网站信息维护列表信息)
	 * @param ids
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月8日 下午1:57:14
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月8日 下午1:57:14
	 * @since  1.0.0
	 */
	@RequestMapping(value="/cancelReleaseseSwHtmlInfo",method = RequestMethod.POST)
	public Object cancelReleaseseSwHtmlInfo(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			swHtmlInfoService.cancelReleaseseSwHtmlInfo(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("取消发布成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("取消发布失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("取消发布失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: createHot
	 * @Description:(设置热点)
	 * @param ids
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月14日 下午7:47:47
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月14日 下午7:47:47
	 * @since  1.0.0
	 */
	@RequestMapping(value="/createHot",method = RequestMethod.POST)
	public Object createHot(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			swHtmlInfoService.createHot(ids);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("设置热点成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("设置热点失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("设置热点失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	//--------------------------门户网站调用-------------------------------
	
	
	
	
	/**
	 * 获取网站首页标题信息 getWebsiteTitle
	 */
	@RequestMapping(value = "/home/getWebsiteTitle", method = RequestMethod.GET)
	public Object getWebsiteTitle() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsiteTitle();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页公司资讯
	 */
	@RequestMapping(value = "/home/getWebsiteCompanyInfo", method = RequestMethod.GET)
	public Object getWebsiteCompanyInfo() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsiteCompanyInfo();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页主营业务
	 */
	@RequestMapping(value = "/home/getWebsiteMainBusiness", method = RequestMethod.GET)
	public Object getWebsiteMainBusiness() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsiteMainBusiness();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页总经理寄语
	 */
	@RequestMapping(value = "/home/getWebsiteManagerWishs", method = RequestMethod.GET)
	public Object getWebsiteManagerWishs() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			Map<String, Object> resultMap = swHtmlInfoService.getWebsiteManagerWishs();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultMap);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页电力交易
	 */
	@RequestMapping(value = "/home/getWebsitePowerDeal", method = RequestMethod.GET)
	public Object getWebsitePowerDeal() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<List<Map<String, Object>>> resultList = swHtmlInfoService.getWebsitePowerDeal();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页电力服务
	 */
	@RequestMapping(value = "/home/getWebsitePowerService", method = RequestMethod.GET)
	public Object getWebsitePowerService() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsitePowerService();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页专题专栏
	 */
	@RequestMapping(value = "/home/getWebsiteSpecial", method = RequestMethod.GET)
	public Object getWebsiteSpecial() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<List<Map<String, Object>>> resultList = swHtmlInfoService.getWebsiteSpecial();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页政策法规
	 */
	@RequestMapping(value = "/home/getWebsitePolicy", method = RequestMethod.GET)
	public Object getWebsitePolicy() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsitePolicy();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页友情链接
	 */
	@RequestMapping(value = "/home/getWebsiteFriendshipLink", method = RequestMethod.GET)
	public Object getWebsiteFriendshipLink() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsiteFriendshipLink();
			
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 获取网站首页栏目头列表
	 */
	@RequestMapping(value = "/home/getWebsiteColumnHeader", method = RequestMethod.GET)
	public Object getWebsiteColumnHeader() {
		ExecutionResult result = new ExecutionResult();
		try {
			
			//根据一级栏目，查询首页头信息
			List<Map<String, Object>> resultList = swHtmlInfoService.getWebsiteColumnHeader();
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(resultList);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 
	 * @Title: gethtmlMessage
	 * @Description:(获取文章信息)
	 * @param id
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月20日 上午11:28:15
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月20日 上午11:28:15
	 * @since  1.0.0
	 */
	@RequestMapping( value = "/home/gethtmlMessage", method = RequestMethod.GET)
	public Object gethtmlMessage(String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String,Object> data = swHtmlInfoService.gethtmlMessage(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);						//设置返回结果集
			result.setMsg("查询成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new Object[]{});						//设置返回结果集
			result.setMsg("查询失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getWebsiteColumnContent
	 * @Description:(根据id获取菜单栏目下的信息)
	 * @param id
	 * @param type
	 * @return 
	 * Object
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年6月20日 下午9:15:35
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年6月20日 下午9:15:35
	 * @since  1.0.0
	 */
	@RequestMapping(value = "/home/getWebsiteColumnContent", method = RequestMethod.GET)
	public Object getWebsiteColumnContent(String id,String type) {
		ExecutionResult result = new ExecutionResult();
		try {
			Map<String,Object> data = swHtmlInfoService.getWebsiteColumnContent(id,type);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(data);					//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
}
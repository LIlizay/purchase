package com.hhwy.purchaseweb.login.swconsregister.controller;

import java.util.HashMap;
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

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterDetail;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterVo;
import com.hhwy.purchaseweb.login.swconsregister.service.SwConsRegisterService;
import com.hhwy.purchaseweb.login.swconsregister.util.QiChaCha;
import com.hhwy.selling.domain.SwConsRegister;

/**
 * SwConsRegisterController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/swConsRegisterController")
public class SwConsRegisterController {

	public static final Logger log = LoggerFactory.getLogger(SwConsRegisterController.class);
	
	/**
	 * swConsRegisterService
	 */
	@Autowired
	private SwConsRegisterService swConsRegisterService;
	
	
	/**
	 * 分页获取对象SwConsRegister
	 * @param ID
	 * @return SwConsRegister
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSwConsRegisterByPage(@RequestBody(required=false) SwConsRegisterVo swConsRegisterVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SwConsRegisterDetail> queryResult = swConsRegisterService.getSwConsRegisterByPage(swConsRegisterVo);
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
	 * 根据ID获取对象SwConsRegister
	 * @param ID
	 * @return SwConsRegister
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSwConsRegisterById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwConsRegister swConsRegister = swConsRegisterService.getSwConsRegisterById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swConsRegister);			//设置数据实体
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
	 * 审核通过
	 * approvalExamine(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "approvalExamine/{id}", method = RequestMethod.POST)
    public Object approvalExamine(@PathVariable String id) {
        ExecutionResult result = new ExecutionResult();
        try {
            swConsRegisterService.approvalExamine(id);;
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("审核通过成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("审核通过失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("审核通过失败！"+e.getMessage());                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
        
    }
	
	/**
	 * 审核不通过
	 * unApprovalExamine(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(value = "unApprovalExamine", method = RequestMethod.POST)
    public Object unApprovalExamine(@RequestBody List<SwConsRegisterDetail> swConsRegisterDetailList) {
        ExecutionResult result = new ExecutionResult();
        try {
            swConsRegisterService.unApprovalExamine(swConsRegisterDetailList);;
            result.setCode(ReturnCode.RES_SUCCESS);     //设置返回结果编码：成功
            result.setFlag(true);                       //设置是否成功标识
            result.setMsg("审核不通过成功！");                 //设置返回消息，根据实际情况修改
        } catch (Exception e) {
            // TODO: handle exception
            log.error("审核不通过失败",e);                        //记录异常日志，根据实际情况修改
            result.setCode(ReturnCode.RES_FAILED);      //设置返回结果编码：失败
            result.setFlag(false);                      //设置是否成功标识
            result.setMsg("审核不通过失败！"+e.getMessage());                 //设置返回消息，根据实际情况修改
            return result;
        }
        return result;
    }
	
	/**
	 * 添加对象SwConsRegister
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSwConsRegister(@RequestBody SwConsRegister swConsRegister) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsRegisterService.saveSwConsRegister(swConsRegister);
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
	 * 更新对象SwConsRegister
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSwConsRegister(@RequestBody SwConsRegister swConsRegister) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsRegisterService.updateSwConsRegister(swConsRegister);
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
	 * 删除对象SwConsRegister
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSwConsRegister(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			swConsRegisterService.deleteSwConsRegister(new String[]{id});
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
	 * 查询企业信息
	 * @param enteName
	 * @return
	 */
	@RequestMapping(value = "/getEnterpriseInfo", method = RequestMethod.POST)
	public Map<String,String> getEnterpriseInfo(@RequestBody String registerNo){
		Map<String,String> result = null;
		try {
			String json = QiChaCha.sendPost("http://www.qichacha.com/gongsi_getList","key="+registerNo.substring(0,(registerNo.length()-1)));
			JSONArray array = JSONArray.parseArray(json);
			if(array!=null&&!array.isEmpty()){
				result = new HashMap<String, String>();
				JSONObject item =(JSONObject)array.get(0);
				String keyNo = item.get("KeyNo").toString();
				String name = item.get("Name").toString();
				String url = "http://www.qichacha.com/company_getinfos?unique="+keyNo+"&companyname="+name+"&tab=base";
				String info = QiChaCha.sendGet(url,"");
				String[] str = info.split("</section>");
				for(String sectionFlag:str){
					if(sectionFlag.indexOf("base_info")>0&&sectionFlag.indexOf("Cominfo")>0){
						result.put("html", sectionFlag+ "</section>");
						break;
					}
				}
				//获取工商注册快照信息
				String snapshortUrl = "http://www.qichacha.com/company_snapshoot?keyNo="+keyNo+"&companyName="+name;
				String snapshortHtml = QiChaCha.sendGet(snapshortUrl,"");
				if(!StringUtils.isEmpty(snapshortHtml)){
					String urlStr = "http://qccdata.qichacha.com/ECIScreen/4a0b71ae9b4a2049055508b892d2c154/";
					int subStart = snapshortHtml.indexOf(urlStr);
					if(subStart>0){
						snapshortHtml = snapshortHtml.substring(subStart);
						snapshortUrl = snapshortHtml.substring(0,snapshortHtml.indexOf("\""));
						result.put("snapshortUrl", snapshortUrl);
					}
				}
			}
			
			
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result;
		}
	}
	
}
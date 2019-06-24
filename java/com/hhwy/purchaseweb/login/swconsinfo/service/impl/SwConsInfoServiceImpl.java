package com.hhwy.purchaseweb.login.swconsinfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaDetail;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaVo;
import com.hhwy.purchaseweb.login.swconsinfo.domain.SwConsInfoVo;
import com.hhwy.purchaseweb.login.swconsinfo.service.SwConsInfoService;
import com.hhwy.purchaseweb.purchasesms.SmsUtil;
import com.hhwy.purchaseweb.purchasesms.service.PurchaseSwSmsService;
import com.hhwy.selling.domain.SwConsInfo;
import com.hhwy.sso.client.filter.SessionUtil;

/**
 * SwConsInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SwConsInfoServiceImpl implements SwConsInfoService {

	public static final Logger log = LoggerFactory.getLogger(SwConsInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 短息记录PurchaseSwSmsService
	 */
	@Autowired
	private PurchaseSwSmsService purchaseSwSmsService;
	
	/**
	 * 分页获取对象SwConsInfo
	 * @param ID
	 * @return SwConsInfo
	 */
	public QueryResult<SwConsInfo> getSwConsInfoByPage(SwConsInfoVo swConsInfoVo) throws Exception{
		QueryResult<SwConsInfo> queryResult = new QueryResult<SwConsInfo>();
		long total = getSwConsInfoCountByParams(swConsInfoVo);
		List<SwConsInfo> swConsInfoList = getSwConsInfoListByParams(swConsInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(swConsInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SwConsInfo数量
	 * @param SwConsInfoVo
	 * @return Integer
	 */
	public Integer getSwConsInfoCountByParams(SwConsInfoVo swConsInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("swConsInfo.sql.getSwConsInfoCountByParams",swConsInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
     * 根据查询条件获取对象SwConsInfo列表
     * @param SwConsInfoVo
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<SwConsInfo> getSwConsInfoListByParams(SwConsInfoVo swConsInfoVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(swConsInfoVo == null){
            swConsInfoVo = new SwConsInfoVo();
        }
        Parameter.isFilterData.set(true);
        List<SwConsInfo> swConsInfoList = (List<SwConsInfo>)dao.getBySql("swConsInfo.sql.getSwConsInfoListByParams",swConsInfoVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(swConsInfoList);
        return swConsInfoList;
    }
    
	/**
     * 分页获取对象SwConsInfo
     * @param SwConsInfoVo
     * @return QueryResult
     */
    public QueryResult<RelaDetail> getRelaDetailByPage(RelaVo relaVo) throws Exception{
        QueryResult<RelaDetail> queryResult = new QueryResult<RelaDetail>();
        long total = getRelaDetailCountByParams(relaVo);
        List<RelaDetail> swConsInfoList = getRelaDetailListByParams(relaVo);
        queryResult.setTotal(total);
        queryResult.setData(swConsInfoList);
        return queryResult;
    }

    /**
     * 根据查询条件获取对象SwConsInfo列表
     * @param SwConsInfoVo
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<RelaDetail> getRelaDetailListByParams(RelaVo relaVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(relaVo == null){
            relaVo = new RelaVo();
        }
       /* String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		relaVo.setOrgNo(orgNo);*/
        String userId = SystemServiceUtil.getLoginUserInfo().getId();
        relaVo.setUserId(userId);
        List<RelaDetail> list = (List<RelaDetail>)dao.getBySql("swConsInfo.sql.getRelaList",relaVo);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 根据查询条件获取对象SwConsInfo数量
     * @param SwConsInfoVo
     * @return Integer
     */
    public Integer getRelaDetailCountByParams(RelaVo relaVo){
    	/*String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		relaVo.setOrgNo(orgNo);*/
	    String userId = SystemServiceUtil.getLoginUserInfo().getId();
        relaVo.setUserId(userId);
        Object result = dao.getOneBySQL("swConsInfo.sql.getRelaCount",relaVo);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
	/**
	 * 根据查询条件获取单个对象SwConsInfo
	 * @param SwConsInfoVo
	 * @return SwConsInfo
	 */
	public SwConsInfo getSwConsInfoOneByParams(SwConsInfoVo swConsInfoVo) throws Exception{
		SwConsInfo swConsInfo = null;
		List<SwConsInfo> swConsInfoList = getSwConsInfoListByParams(swConsInfoVo);
		if(swConsInfoList != null && swConsInfoList.size() > 0){
			swConsInfo = swConsInfoList.get(0);
		}
		return swConsInfo;
	}
	
	/**
	 * 根据ID获取对象SwConsInfo
	 * @param ID
	 * @return SwConsInfo
	 */
	public SwConsInfo getSwConsInfoById(String id) {
		return dao.findById(id, SwConsInfo.class);
	}	
	
	/**
     * 绑定账号
     * binding(描述这个方法的作用)<br/>
     * @param swConsInfo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void binding(SwConsInfo swConsInfo){
        SwConsInfoVo swConsInfoVo = new SwConsInfoVo();
        swConsInfoVo.getSwConsInfo().setLoginName(swConsInfo.getLoginName());
        int count = getSwConsInfoCountByParams(swConsInfoVo);
        if(count!=0){
            throw new RuntimeException("该账号已经被绑定，请先解除绑定后再删除！");
        }
        swConsInfoVo.getSwConsInfo().setLoginName(null);
        swConsInfoVo.getSwConsInfo().setConsId(swConsInfo.getConsId());
        count = getSwConsInfoCountByParams(swConsInfoVo);
        if(count!=0){
            throw new RuntimeException("该档案已经被绑定，请选择其他档案！");
        }
        saveSwConsInfo(swConsInfo);
    }
    
    
	/**
	 * 添加对象SwConsInfo
	 * @param 实体对象
	 */
	public void saveSwConsInfo(SwConsInfo swConsInfo) {
		dao.save(swConsInfo);
	}
	
	/**
	 * 添加对象SwConsInfo
	 * @param 实体对象
	 */
	public void saveSwConsInfoList(List<SwConsInfo> swConsInfoList) {
		dao.saveList(swConsInfoList);
	}
	
	/**
	 * 更新对象SwConsInfo
	 * @param 实体对象
	 */
	public void updateSwConsInfo(SwConsInfo swConsInfo) {
		dao.update(swConsInfo);
	}
	
	/**
	 * 删除对象SwConsInfo
	 * @param id数据组
	 */
	public void deleteSwConsInfo(String[] ids) {
		dao.delete(ids, SwConsInfo.class);
	}

	/**
	 * 
	 * remindMessage(提醒用户信息)<br/>
	 * @param relaDetailList
	 * @return
	 * @throws Exception 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	@Transactional
	public void remindMessage(List<RelaDetail> relaDetailList)
			throws Exception {
		for(RelaDetail relaDetail : relaDetailList){
			Map<String,Object> params = new HashMap<>();
			String phone = relaDetail.getPhone();
			String consId = relaDetail.getConsId();
			String contId = relaDetail.getContId();
			params.put("receivePhone", phone);
			params.put("consId", consId);
			params.put("receivePerson", contId);
			params.put("sendDate", new Date());
			params.put("content",SmsUtil.TEMPLATECODE94110020);
			//获取当前登录人
			String loginName = SystemServiceUtil.getLoginUserName();
			params.put("sendPerson", loginName);
			params.put("createUser", SessionUtil.getLoginUserId());
			params.put("updateUser", SessionUtil.getLoginUserId());
			Map<String,Object> swSms = purchaseSwSmsService.getSwSmsByParams(params);
			if(swSms != null){//存在记录
				String status = swSms.get("status") == null ? "" : swSms.get("status").toString();
				if(status != null && BusinessContants.SEND_SMS_FAIL.equals(status)){//发送失败
					String para = "{\"loginName\":\""+relaDetail.getLoginName()+"\"}";
					boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94110020,para);
					if(!flag){//发送失败
						params.put("status",BusinessContants.SEND_SMS_FAIL);
						params.put("id",swSms.get("id"));
						purchaseSwSmsService.updateSwSms(params);
					}else{//发送成功
						params.put("status",BusinessContants.SEND_SMS_SUCCESS);
						params.put("id",swSms.get("id"));
						purchaseSwSmsService.updateSwSms(params);
					}
				}else{
					params.put("status",BusinessContants.SEND_SMS_SUCCESS);
					params.put("id",swSms.get("id"));
					purchaseSwSmsService.updateSwSms(params);
				}
			}else{//不存在记录
				String para = "{\"loginName\":\""+relaDetail.getLoginName()+"\"}";
				boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94110020,para);
				String id = PlatformTools.getID();
				if(!flag){//发送失败
					params.put("status",BusinessContants.SEND_SMS_FAIL);
					params.put("id",id);
					purchaseSwSmsService.saveSwSms(params);
				}else{//发送成功
					params.put("status",BusinessContants.SEND_SMS_SUCCESS);
					params.put("id",id);
					purchaseSwSmsService.saveSwSms(params);
				}
			}
		}
	}	
}
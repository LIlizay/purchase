package com.hhwy.purchaseweb.login.swconsregister.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.core.framework.service.SequenceTool;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaDetail;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaVo;
import com.hhwy.purchaseweb.login.swconsregister.constant.RegisterContants;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterDetail;
import com.hhwy.purchaseweb.login.swconsregister.domain.SwConsRegisterVo;
import com.hhwy.purchaseweb.login.swconsregister.service.SwConsRegisterService;
import com.hhwy.purchaseweb.purchasesms.SmsUtil;
import com.hhwy.purchaseweb.purchasesms.service.PurchaseSwSmsService;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.ScContactsInfo;
import com.hhwy.selling.domain.SwConsRegister;
import com.hhwy.sso.client.filter.SessionUtil;

/**
 * SwConsRegisterService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SwConsRegisterServiceImpl implements SwConsRegisterService {

	public static final Logger log = LoggerFactory.getLogger(SwConsRegisterServiceImpl.class);
	
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
	 * 分页获取对象SwConsRegister
	 * @param ID
	 * @return SwConsRegister
	 */
	public QueryResult<SwConsRegisterDetail> getSwConsRegisterByPage(SwConsRegisterVo swConsRegisterVo) throws Exception{
		QueryResult<SwConsRegisterDetail> queryResult = new QueryResult<SwConsRegisterDetail>();
		long total = getSwConsRegisterCountByParams(swConsRegisterVo);
		List<SwConsRegisterDetail> swConsRegisterList = getSwConsRegisterListByParams(swConsRegisterVo);
		queryResult.setTotal(total);
		queryResult.setData(swConsRegisterList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SwConsRegister数量
	 * @param SwConsRegisterVo
	 * @return Integer
	 */
	public Integer getSwConsRegisterCountByParams(SwConsRegisterVo swConsRegisterVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("swConsRegister.sql.getSwConsRegisterCountByParams",swConsRegisterVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SwConsRegister列表
	 * @param SwConsRegisterVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SwConsRegisterDetail> getSwConsRegisterListByParams(SwConsRegisterVo swConsRegisterVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(swConsRegisterVo == null){
			swConsRegisterVo = new SwConsRegisterVo();
		}
		Parameter.isFilterData.set(true);
		List<SwConsRegisterDetail> swConsRegisterList = (List<SwConsRegisterDetail>)dao.getBySql("swConsRegister.sql.getSwConsRegisterListByParams",swConsRegisterVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(swConsRegisterList);
		return swConsRegisterList;
	}
	
	 /**
     * 分页获取对象RelaDetail
     * @param RelaVo
     * @return QueryResult
     */
    public QueryResult<RelaDetail> getRelaDetailByPage(RelaVo relaVo) throws Exception{
        QueryResult<RelaDetail> queryResult = new QueryResult<RelaDetail>();
        long total = getRelaDetailCountByParams(relaVo);
        List<RelaDetail> list = getRelaDetailListByParams(relaVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }

    /**
     * 根据查询条件获取对象RelaDetail列表
     * @param RelaVo
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<RelaDetail> getRelaDetailListByParams(RelaVo relaVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(relaVo == null){
            relaVo = new RelaVo();
        }
        String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		relaVo.setOrgNo(orgNo);
        List<RelaDetail> list = (List<RelaDetail>)dao.getBySql("swConsRegister.sql.getRelaList",relaVo);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 根据查询条件获取对象RelaDetail数量
     * @param RelaVo
     * @return Integer
     */
    public Integer getRelaDetailCountByParams(RelaVo relaVo){
    	String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		relaVo.setOrgNo(orgNo);
        Object result = dao.getOneBySQL("swConsRegister.sql.getRelaCount",relaVo);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
	/**
	 * 根据查询条件获取单个对象SwConsRegister
	 * @param SwConsRegisterVo
	 * @return SwConsRegister
	 */
	public SwConsRegisterDetail getSwConsRegisterOneByParams(SwConsRegisterVo swConsRegisterVo) throws Exception{
	    SwConsRegisterDetail swConsRegister = null;
		List<SwConsRegisterDetail> swConsRegisterList = getSwConsRegisterListByParams(swConsRegisterVo);
		if(swConsRegisterList != null && swConsRegisterList.size() > 0){
			swConsRegister = swConsRegisterList.get(0);
		}
		return swConsRegister;
	}
	
	/**
	 * 根据ID获取对象SwConsRegister
	 * @param ID
	 * @return SwConsRegister
	 */
	public SwConsRegister getSwConsRegisterById(String id) {
		return dao.findById(id, SwConsRegister.class);
	}	
	
	/**
     * 审核通过
     * approvalExamine(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
	@Transactional
    public void approvalExamine(String id){
	    SwConsRegister swConsRegister = changeExamineStatus(id,true);
	    if(RegisterContants.REGISTER_CONS_TYPE_AGRECONS.equals(swConsRegister.getConsType())||
	            RegisterContants.REGISTER_CONS_TYPE_PHCONS.equals(swConsRegister.getConsType())){
	    	Parameter.isFilterData.set(true);
	        Object result = dao.getOneBySQL("swConsRegister.sql.checkCons", swConsRegister);
	        Parameter.isFilterData.set(false);
	        int total = result == null ? 0 : Integer.valueOf(result.toString());
	        if(total!=0){
	            return;
	        }
	        String consId = PlatformTools.getID();
	        ScConsumerInfo scConsumerInfo = new ScConsumerInfo();
	        scConsumerInfo.setId(consId);
	        scConsumerInfo.setConsName(swConsRegister.getConsName());
	        //设置意向用户
	        scConsumerInfo.setConsType(RegisterContants.CONS_TYPE_YIXIANG);
	        String consNo = SequenceTool.getInstatnce().generateSerinalNumber("sellConsNo");
	        scConsumerInfo.setConsNo(consNo);
	        scConsumerInfo.setVoltCode(swConsRegister.getVoltCode());
	        scConsumerInfo.setElecTypeCode(swConsRegister.getElecTypeCode());
	        scConsumerInfo.setRegistrationNo(swConsRegister.getRegisterNo());
	        scConsumerInfo.setRegisteredAddress(swConsRegister.getAddr());
	        dao.save(scConsumerInfo);
	        ScContactsInfo scContactsInfo = new ScContactsInfo();
	        scContactsInfo.setConsId(consId);
	        scContactsInfo.setContName(swConsRegister.getContName());
	        scContactsInfo.setPhone(swConsRegister.getPhone());
	        dao.save(scContactsInfo);
	    }
        
    }
    
    /**
     * 审核不通过
     * unApprovalExamine(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
	@Transactional
    public void unApprovalExamine(List<SwConsRegisterDetail> swConsRegisterDetailList) throws Exception{
        for(SwConsRegisterDetail swConsRegisterDetail : swConsRegisterDetailList){
        	Map<String,Object> params = new HashMap<>();
			String phone = swConsRegisterDetail.getPhone();
			params.put("receivePhone", phone);
			params.put("sendDate", new Date());
			params.put("content",SmsUtil.TEMPLATECODE94245024);
			//获取当前登录人
			String loginName = SystemServiceUtil.getLoginUserName();
			params.put("sendPerson", loginName);
			params.put("createUser", SessionUtil.getLoginUserId());
			params.put("updateUser", SessionUtil.getLoginUserId());
			boolean flag = SmsUtil.sendMessage(phone,SmsUtil.TEMPLATECODE94245024,null);
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
			changeExamineStatus(swConsRegisterDetail.getId(),false);
        }
    }
    
    /**
     * 改变审核状态
     * changeExamineStatus(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    private SwConsRegister changeExamineStatus(String id,boolean flag){
        //校验数据
        SwConsRegister swConsRegister = getSwConsRegisterById(id);
        if(RegisterContants.EXAMINE_STATUS_AUDITED.equals(swConsRegister.getExamineStatus())){
            throw new RuntimeException("该用 户已经被审核！");
        }
        String statue = null;
        if(flag){
            statue = RegisterContants.EXAMINE_STATUS_AUDITED;
        }else{
            statue = RegisterContants.EXAMINE_STATUS_REJECT;
        }
        //修改状态
        swConsRegister.setExamineStatus(statue);
        dao.update(swConsRegister);
        return swConsRegister;
        
    }
    
    /**
	 * 添加对象SwConsRegister
	 * @param 实体对象
	 */
	public void saveSwConsRegister(SwConsRegister swConsRegister) {
		dao.save(swConsRegister);
	}
	
	/**
	 * 添加对象SwConsRegister
	 * @param 实体对象
	 */
	public void saveSwConsRegisterList(List<SwConsRegister> swConsRegisterList) {
		dao.saveList(swConsRegisterList);
	}
	
	/**
	 * 更新对象SwConsRegister
	 * @param 实体对象
	 */
	public void updateSwConsRegister(SwConsRegister swConsRegister) {
		dao.update(swConsRegister);
	}
	
	/**
	 * 删除对象SwConsRegister
	 * @param id数据组
	 */
	public void deleteSwConsRegister(String[] ids) {
		dao.delete(ids, SwConsRegister.class);
	}	
}
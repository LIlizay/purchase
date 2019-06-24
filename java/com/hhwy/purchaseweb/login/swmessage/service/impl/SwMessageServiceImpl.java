package com.hhwy.purchaseweb.login.swmessage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.domain.UserInfo;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.login.swmessage.domain.SellingSwMessageDetail;
import com.hhwy.purchaseweb.login.swmessage.domain.SwMessageVo;
import com.hhwy.purchaseweb.login.swmessage.service.SwMessageService;
import com.hhwy.selling.domain.SellingSwMessage;

/**
 * SwMessageService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SwMessageServiceImpl implements SwMessageService {

	public static final Logger log = LoggerFactory.getLogger(SwMessageServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SwMessage
	 * @param ID
	 * @return SwMessage
	 */
	public QueryResult<SellingSwMessageDetail> getSwMessageByPage(SwMessageVo swMessageVo) throws Exception{
		QueryResult<SellingSwMessageDetail> queryResult = new QueryResult<SellingSwMessageDetail>();
		long total = getSwMessageCountByParams(swMessageVo);
		List<SellingSwMessageDetail> swMessageList = getSwMessageListByParams(swMessageVo);
		queryResult.setTotal(total);
		queryResult.setData(swMessageList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SwMessage数量
	 * @param SwMessageVo
	 * @return Integer
	 */
	public Integer getSwMessageCountByParams(SwMessageVo swMessageVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("sellingSwMessage.sql.getSellingSwMessageCountByParams",swMessageVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SwMessage列表
	 * @param SwMessageVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SellingSwMessageDetail> getSwMessageListByParams(SwMessageVo swMessageVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(swMessageVo == null){
			swMessageVo = new SwMessageVo();
		}
		Parameter.isFilterData.set(true);
		List<SellingSwMessageDetail> swMessageList = (List<SellingSwMessageDetail>)dao.getBySql("sellingSwMessage.sql.getSellingSwMessageListByParams",swMessageVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(swMessageList);
		return swMessageList;
	}
	/**
	 * 根据查询条件获取单个对象SwMessage
	 * @param SwMessageVo
	 * @return SwMessage
	 */
	public SellingSwMessageDetail getSwMessageOneByParams(SwMessageVo swMessageVo) throws Exception{
		SellingSwMessageDetail sellingSwMessageDetail = null;
		List<SellingSwMessageDetail> swMessageList = getSwMessageListByParams(swMessageVo);
		if(swMessageList != null && swMessageList.size() > 0){
			sellingSwMessageDetail = swMessageList.get(0);
		}
		return sellingSwMessageDetail;
	}
	
	/**
	 * 根据ID获取对象SwMessage
	 * @param ID
	 * @return SwMessage
	 */
	public SellingSwMessage getSwMessageById(String id) {
		return dao.findById(id, SellingSwMessage.class);
	}	
	
	/**
	 * 添加对象SwMessage
	 * @param 实体对象
	 */
	public void saveSwMessage(SellingSwMessage sellingSwMessage) {
		UserInfo userInfo = SystemServiceUtil.getLoginUserInfo();
		sellingSwMessage.setSendPerson(userInfo.getLoginName());
		sellingSwMessage.setOrgNo(userInfo.getOrgId());
		sellingSwMessage.setMessageType(BusinessContants.MESSAGETYPE03);
		dao.save(sellingSwMessage);
	}
	
	/**
	 * 添加对象SwMessage
	 * @param 实体对象
	 */
	public void saveSwMessageList(List<SellingSwMessage> sellingSwMessageList) {
		dao.saveList(sellingSwMessageList);
	}
	
	/**
	 * 更新对象SwMessage
	 * @param 实体对象
	 */
	public void updateSwMessage(SellingSwMessage sellingSwMessage) {
		dao.update(sellingSwMessage);
	}
	
	/**
	 * 删除对象SwMessage
	 * @param id数据组
	 */
	public void deleteSwMessage(String[] ids) {
		dao.delete(ids, SellingSwMessage.class);
	}	
}
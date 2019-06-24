package com.hhwy.purchaseweb.login.swmessage.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.login.swmessage.domain.SellingSwMessageDetail;
import com.hhwy.purchaseweb.login.swmessage.domain.SwMessageVo;
import com.hhwy.selling.domain.SellingSwMessage;

/**
 * ISwMessageService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwMessageService{
	
	/**
	 * 分页获取对象SwMessage
	 * @param SwMessageVo
	 * @return QueryResult
	 */
	public QueryResult<SellingSwMessageDetail> getSwMessageByPage(SwMessageVo swMessageVo) throws Exception;

	/**
	 * 根据查询条件获取对象SellingSwMessage列表
	 * @param SwMessageVo
	 * @return List
	 */
	public List<SellingSwMessageDetail> getSwMessageListByParams(SwMessageVo swMessageVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SwMessage数量
	 * @param SwMessageVo
	 * @return Integer
	 */
	public Integer getSwMessageCountByParams(SwMessageVo swMessageVo);
	
	/**
	 * 根据查询条件获取单个对象SwMessage
	 * @param SwMessageVo
	 * @return SwMessage
	 */
	public SellingSwMessageDetail getSwMessageOneByParams(SwMessageVo swMessageVo) throws Exception;
	
	/**
	 * 根据ID获取对象SwMessage
	 * @param ID
	 * @return SwMessage
	 */
	public SellingSwMessage getSwMessageById(String id);
	
	/**
	 * 添加对象SwMessage
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwMessage(SellingSwMessage sellingSwMessage);
	
	/**
	 * 添加对象SwMessage列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwMessageList(List<SellingSwMessage> sellingSwMessageList);
	
	/**
	 * 更新对象SwMessage
	 * @param 实体对象
	 * @return SwMessage
	 */
	public void updateSwMessage(SellingSwMessage sellingSwMessage);
	
	/**
	 * 删除对象SwMessage
	 * @param id数据组
	 */
	public void deleteSwMessage(String[] ids);

}
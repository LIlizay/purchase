package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain.SmConsEarlyWarningVo;
import com.hhwy.selling.domain.SmConsEarlyWarning;


/**
 * ISmConsEarlyWarningService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmConsEarlyWarningService{
	
	/**
	 * 分页获取对象SmConsEarlyWarning
	 * @param SmConsEarlyWarningVo
	 * @return QueryResult
	 */
	public QueryResult<SmConsEarlyWarningDetail> getSmConsEarlyWarningByPage(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmConsEarlyWarning列表
	 * @param SmConsEarlyWarningVo
	 * @return List
	 */
	public List<SmConsEarlyWarningDetail> getSmConsEarlyWarningListByParams(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmConsEarlyWarning数量
	 * @param SmConsEarlyWarningVo
	 * @return Integer
	 */
	public Integer getSmConsEarlyWarningCountByParams(SmConsEarlyWarningVo smConsEarlyWarningVo);
	
	/**
	 * 根据查询条件获取单个对象SmConsEarlyWarning
	 * @param SmConsEarlyWarningVo
	 * @return SmConsEarlyWarning
	 */
	public SmConsEarlyWarningDetail getSmConsEarlyWarningOneByParams(SmConsEarlyWarningVo smConsEarlyWarningVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmConsEarlyWarning
	 * @param ID
	 * @return SmConsEarlyWarning
	 * @throws Exception 
	 */
	public SmConsEarlyWarningVo getSmConsEarlyWarningById(String id) throws Exception;
	
	/**
	 * 添加对象SmConsEarlyWarning
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsEarlyWarning(SmConsEarlyWarning smConsEarlyWarning);
	
	/**
	 * 添加对象SmConsEarlyWarning列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsEarlyWarningList(List<SmConsEarlyWarning> smConsEarlyWarningList);
	
	/**
	 * 更新对象SmConsEarlyWarning
	 * @param 实体对象
	 * @return SmConsEarlyWarning
	 */
	public void updateSmConsEarlyWarning(SmConsEarlyWarning smConsEarlyWarning);
	
	/**
	 * 删除对象SmConsEarlyWarning
	 * @param id数据组
	 */
	public void deleteSmConsEarlyWarning(String[] ids);
	
	/**
	 * 
	 * @Title: saveConsRule<br/>
	 * @Description: 保存用户规则<br/>
	 * @param smConsEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:16:31
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:16:31
	 * @since  1.0.0
	 */
	public void saveConsRule(SmConsEarlyWarningVo smConsEarlyWarningVo);
	
	/**
	 * 
	 * @Title: updateConsRule<br/>
	 * @Description: 更新用户规则<br/>
	 * @param smConsEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:21:43
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:21:43
	 * @since  1.0.0
	 */
	public void updateConsRule(SmConsEarlyWarningVo smConsEarlyWarningVo);
	
	/**
	 * 
	 * @Title: getConsEarlyWarningByConsId<br/>
	 * @Description: 判断用户是否已经存在规则<br/>
	 * @param consId
	 * @return
	 * @throws Exception <br/>
	 * SmConsEarlyWarningVo<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月22日 下午3:16:13
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月22日 下午3:16:13
	 * @since  1.0.0
	 */
	public SmConsEarlyWarningVo getConsEarlyWarningByConsId(String consId) throws Exception;
	
	/**
	 * @Title: getDefaultRule<br/>
	 * @Description: 查询默认规则<br/>
	 * @return
	 * @throws Exception <br/>
	 * SmConsEarlyWarningVo<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月31日 下午4:33:03
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月31日 下午4:33:03
	 * @since  1.0.0
	 */
	public SmConsEarlyWarningVo getDefaultRule() throws Exception;

}
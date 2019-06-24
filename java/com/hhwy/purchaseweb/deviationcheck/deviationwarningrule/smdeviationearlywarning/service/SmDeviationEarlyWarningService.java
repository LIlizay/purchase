package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningDetail;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain.SmDeviationEarlyWarningVo;
import com.hhwy.selling.domain.SmDeviationEarlyWarning;

/**
 * ISmDeviationEarlyWarningService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmDeviationEarlyWarningService{
	
	/**
	 * 分页获取对象SmDeviationEarlyWarning
	 * @param SmDeviationEarlyWarningVo
	 * @return QueryResult
	 */
	public QueryResult<SmDeviationEarlyWarningDetail> getSmDeviationEarlyWarningByPage(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmDeviationEarlyWarning列表
	 * @param SmDeviationEarlyWarningVo
	 * @return List
	 */
	public List<SmDeviationEarlyWarningDetail> getSmDeviationEarlyWarningListByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmDeviationEarlyWarning数量
	 * @param SmDeviationEarlyWarningVo
	 * @return Integer
	 */
	public Integer getSmDeviationEarlyWarningCountByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo);
	
	/**
	 * 根据查询条件获取单个对象SmDeviationEarlyWarning
	 * @param SmDeviationEarlyWarningVo
	 * @return SmDeviationEarlyWarning
	 */
	public SmDeviationEarlyWarningDetail getSmDeviationEarlyWarningOneByParams(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmDeviationEarlyWarning
	 * @param ID
	 * @return SmDeviationEarlyWarning
	 * @throws Exception 
	 */
	public SmDeviationEarlyWarningVo getSmDeviationEarlyWarningById(String id) throws Exception;
	
	/**
	 * 添加对象SmDeviationEarlyWarning
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmDeviationEarlyWarning(SmDeviationEarlyWarning smDeviationEarlyWarning);
	
	/**
	 * 添加对象SmDeviationEarlyWarning列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmDeviationEarlyWarningList(List<SmDeviationEarlyWarning> smDeviationEarlyWarningList);
	
	/**
	 * 更新对象SmDeviationEarlyWarning
	 * @param 实体对象
	 * @return SmDeviationEarlyWarning
	 */
	public void updateSmDeviationEarlyWarning(SmDeviationEarlyWarning smDeviationEarlyWarning);
	
	/**
	 * 删除对象SmDeviationEarlyWarning
	 * @param id数据组
	 */
	public void deleteSmDeviationEarlyWarning(String[] ids);
	
	/**
	 * 
	 * @Title: saveDeviationRuleList<br/>
	 * @Description: 保存预警规则<br/>
	 * @param smDeviationEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午6:04:16
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午6:04:16
	 * @since  1.0.0
	 */
	public void saveDeviationRuleList(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo);
	
	/**
	 * @Title: updateDeviationRuleList<br/>
	 * @Description: 更新预警规则<br/>
	 * @param smDeviationEarlyWarningVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午9:40:44
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午9:40:44
	 * @since  1.0.0
	 */
	public void updateDeviationRuleList(SmDeviationEarlyWarningVo smDeviationEarlyWarningVo);
	
	/**
	 * 
	 * @Title: getCountByStatus<br/>
	 * @Description: 查询规则状态<br/>
	 * @return <br/>
	 * Integer<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年8月5日 下午7:22:29
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年8月5日 下午7:22:29
	 * @since  1.0.0
	 */
	public Integer getCountByStatus();

}
package com.hhwy.purchaseweb.ssfeedback.service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackDetail;
import com.hhwy.purchaseweb.ssfeedback.domain.SsFeedbackVo;
import com.hhwy.selling.domain.SsFeedback;

/**
 * SsFeedbackService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SsFeedbackService{
	
	
	/**
		 * 
		 * @Title: getConsSatisfaction<br/>
		 * @Description:TODO(查用户满意程度码表)<br/>
		 * @return
		 * @throws Exception
		 * List<Map<String,Object>>
		 * @throws Exception <br/>
		 * QueryResult<TreeGridDetail><br/>
		 * <b>创 建 人：</b>zhagnzhao<br/>
		 * <b>创建时间:</b>2018年3月12日 下午2:13:45
		 * <b>修 改 人：</b>chengpeng<br/>
		 * <b>修改时间:</b>2018年3月12日 下午2:13:45
		 * @since  1.0.0
	 */
	public List<Map<String, Object>> getConsSatisfaction() throws Exception;
	
	/**
	 * 添加对象SsFeedback(提交按钮)
	 * @param 实体对象
	 * @return null
	 * @throws MalformedURLException 
	 */
	public void saveSsFeedback(SsFeedback ssFeedback, String url) throws MalformedURLException;
	
	/**
	 * 分页获取对象SsFeedback
	 * @param url 
	 * @param SsFeedbackVo
	 * @return QueryResult 日期格式 YYYY-mm-dd
	 */
	public QueryResult<SsFeedbackDetail> getSsFeedbackByPage(SsFeedbackVo ssFeedbackVo, String url) throws Exception;

	/**
	 * 根据查询条件获取对象SsFeedback列表
	 * @param SsFeedbackVo
	 * @return List
	 */
	public List<SsFeedbackDetail> getSsFeedbackListByParams(SsFeedbackVo ssFeedbackVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SsFeedback数量
	 * @param SsFeedbackVo
	 * @return Integer
	 */
	public Integer getSsFeedbackCountByParams(SsFeedbackVo ssFeedbackVo);
	
	/**
	 * 根据查询条件获取单个对象SsFeedback
	 * @param SsFeedbackVo
	 * @return SsFeedback
	 */
	public SsFeedbackDetail getSsFeedbackOneByParams(SsFeedbackVo ssFeedbackVo) throws Exception;
	
	/**
	 * 根据ID获取对象SsFeedback
	 * @param ID
	 * @return SsFeedback
	 * @throws Exception 
	 */
	public SsFeedback getSsFeedbackById(String id) throws Exception;
	
	
	
	/**
	 * 添加对象SsFeedback列表 （反馈回复确认按钮）
	 * @param 实体对象
	 * @return null
	 */
	public void saveSsFeedbackList(List<SsFeedback> ssFeedbackList);
	
	/**
	 * 更新对象SsFeedback
	 * @param 实体对象
	 * @return SsFeedback
	 */
	public void updateSsFeedback(SsFeedback ssFeedback);
	
	/**
	 * 删除对象SsFeedback
	 * @param id数据组
	 */
	public void deleteSsFeedback(String[] ids);

}
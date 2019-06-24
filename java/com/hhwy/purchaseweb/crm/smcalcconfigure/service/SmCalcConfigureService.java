package com.hhwy.purchaseweb.crm.smcalcconfigure.service;

import java.util.List;
import java.util.Map;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmCalcConfigure;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureDetail;
import com.hhwy.purchaseweb.crm.smcalcconfigure.domain.SmCalcConfigureVo;

/**
 * ISmCalcConfigureService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmCalcConfigureService{
	
	/**
	 * 分页获取对象SmCalcConfigure
	 * @param SmCalcConfigureVo
	 * @return QueryResult
	 */
	public QueryResult<SmCalcConfigureDetail> getSmCalcConfigureByPage(SmCalcConfigureVo smCalcConfigureVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmCalcConfigure列表
	 * @param SmCalcConfigureVo
	 * @return List
	 */
	public List<SmCalcConfigureDetail> getSmCalcConfigureListByParams(SmCalcConfigureVo smCalcConfigureVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmCalcConfigure数量
	 * @param SmCalcConfigureVo
	 * @return Integer
	 */
	public Integer getSmCalcConfigureCountByParams(SmCalcConfigureVo smCalcConfigureVo);
	
	/**
	 * 根据查询条件获取单个对象SmCalcConfigure
	 * @param SmCalcConfigureVo
	 * @return SmCalcConfigure
	 */
	public SmCalcConfigureDetail getSmCalcConfigureOneByParams(SmCalcConfigureVo smCalcConfigureVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmCalcConfigure
	 * @param ID
	 * @return SmCalcConfigure
	 */
	public SmCalcConfigure getSmCalcConfigureById(String id);
	
	/**
	 * 添加对象SmCalcConfigure
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmCalcConfigure(SmCalcConfigure smCalcConfigure);
	
	/**
	 * 添加对象SmCalcConfigure列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmCalcConfigureList(List<SmCalcConfigure> smCalcConfigureList);
	
	/**
	 * 更新对象SmCalcConfigure
	 * @param 实体对象
	 * @return SmCalcConfigure
	 */
	public void updateSmCalcConfigure(SmCalcConfigure smCalcConfigure);
	
	/**
	 * 删除对象SmCalcConfigure
	 * @param id数据组
	 */
	public void deleteSmCalcConfigure(String[] ids);
	
	/**
	 * @Title: getRuleValueList<br/>
	 * @Description: 查询规则下拉框数据 <br/>
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午3:43:37
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午3:43:37
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getRuleValueList();
	
	/**
	 * @Title: getSellDbList<br/>
	 * @Description: 查询售电公司库名<br/>
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:08:52
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:08:52
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getSellDbList();
	
	/**
	 * @Title: getRuleParamsList<br/>
	 * @Description: 查询规则参数列表<br/>
	 * @param ruleId
	 * @return <br/>
	 * List<Map<String,Object>><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月16日 下午4:23:16
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月16日 下午4:23:16
	 * @since  1.0.0
	 */
	public List<Map<String, Object>> getRuleParamsList(String ruleCode);
	
	/**
	 * @Title: getTree<br/>
	 * @Description: 查询树<br/>
	 * @param nodeType
	 * @param provinceId
	 * @return <br/>
	 * Object<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午10:49:20
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午10:49:20
	 * @since  1.0.0
	 */
	public Object getTree(String nodeType, String provinceId);
	
	/**
	 * @Title: effect<br/>
	 * @Description: 设置生效状态<br/>
	 * @param provinceId
	 * @param id
	 * @return
	 * @throws Exception <br/>
	 * ExecutionResult<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:01:02
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:01:02
	 * @since  1.0.0
	 */
	public ExecutionResult effect(String provinceId, String id) throws Exception;
	
	/**
	 * @Title: invalid<br/>
	 * @Description: 规则失效<br/>
	 * @param id
	 * @throws Exception <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2018年1月17日 上午11:04:47
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2018年1月17日 上午11:04:47
	 * @since  1.0.0
	 */
	public void invalid(String id) throws Exception;

}
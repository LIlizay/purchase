package com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmConsDeviationAmt;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtDetail;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain.SmConsDeviationAmtVo;

/**
 * ISmConsDeviationAmtService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmConsDeviationAmtService{
	
	/**
	 * 分页获取对象SmConsDeviationAmt
	 * @param SmConsDeviationAmtVo
	 * @return QueryResult
	 */
	public QueryResult<SmConsDeviationAmt> getSmConsDeviationAmtByPage(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmConsDeviationAmt列表
	 * @param SmConsDeviationAmtVo
	 * @return List
	 */
	public List<SmConsDeviationAmt> getSmConsDeviationAmtListByParams(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmConsDeviationAmt数量
	 * @param SmConsDeviationAmtVo
	 * @return Integer
	 */
	public Integer getSmConsDeviationAmtCountByParams(SmConsDeviationAmtVo smConsDeviationAmtVo);
	
	/**
	 * 根据查询条件获取单个对象SmConsDeviationAmt
	 * @param SmConsDeviationAmtVo
	 * @return SmConsDeviationAmt
	 */
	public SmConsDeviationAmt getSmConsDeviationAmtOneByParams(SmConsDeviationAmtVo smConsDeviationAmtVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmConsDeviationAmt
	 * @param ID
	 * @return SmConsDeviationAmt
	 */
	public SmConsDeviationAmt getSmConsDeviationAmtById(String id);
	
	/**
	 * 添加对象SmConsDeviationAmt
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsDeviationAmt(SmConsDeviationAmt smConsDeviationAmt);
	
	/**
	 * 添加对象SmConsDeviationAmt列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsDeviationAmtList(List<SmConsDeviationAmt> smConsDeviationAmtList);
	
	/**
	 * 更新对象SmConsDeviationAmt
	 * @param 实体对象
	 * @return SmConsDeviationAmt
	 */
	public void updateSmConsDeviationAmt(SmConsDeviationAmt smConsDeviationAmt);
	
	/**
	 * 删除对象SmConsDeviationAmt
	 * @param id数据组
	 */
	public void deleteSmConsDeviationAmt(String[] ids);

	/**
	 * @Title: deleteConsDevAmtBySchemeIds
	 * @Description: 根据方案id删除月度结算用户偏差惩罚费用信息表信息
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午10:21:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午10:21:26
	 * @since  1.0.0
	 */
	public void deleteConsDevAmtBySchemeIds(String[] schemeIds) ;
	
	/**
	 * @Title: getConsDeviAmtBySchemeId
	 * @Description: 根据方案id获取偏差费用计算相关信息
	 * @param schemeId
	 * @return 
	 * List<SmConsDeviationAmtDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午3:04:12
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午3:04:12
	 * @since  1.0.0
	 */
	public List<SmConsDeviationAmtDetail> getConsDeviAmtBySchemeId(String schemeId)  throws Exception;
	
	/**
	 * @Title: getConsDeviAmtByYm
	 * @Description: 根据ym获取当月的所有合同用户偏差费用计算相关信息
	 * @param ym	yyyyMM格式或者yyyy-MM格式
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午3:04:27
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午3:04:27
	 * @since  1.0.0
	 */
	public List<SmConsDeviationAmtDetail> getConsDeviAmtByYm(String ym) throws Exception;
	
	/**
	 * @Title: calculateDeviationCheckAmt
	 * @Description: 计算单个用户的 考核费用
	 * @param detail
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月29日 下午4:59:06
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月29日 下午4:59:06
	 * @since  1.0.0
	 */
	public void calculateDeviationCheckAmt(SmConsDeviationAmtDetail detail) throws Exception;
}
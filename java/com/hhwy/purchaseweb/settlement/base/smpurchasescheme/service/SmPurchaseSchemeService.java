package com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeView;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeVo;

/**
 * ISmPurchaseSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmPurchaseSchemeService{
	
	/**
	 * @Title: getSmPurchaseSchemeByPage
	 * @Description: 根据查询条件获取对象SmPurchaseSchemeView列表,主要比SmPurchaseScheme类多了‘售电公司偏差考核’属性，用于前台页面方案列表展示
	 * @param smPurchaseSchemeVo
	 * @return
	 * @throws Exception 
	 * List<SmPurchaseSchemeView>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午11:58:14
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午11:58:14
	 * @since  1.0.0
	 */
	public QueryResult<SmPurchaseSchemeView> getSmPurchaseSchemeByPage(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmPurchaseScheme列表
	 * @param SmPurchaseSchemeVo
	 * @return List
	 */
	public List<SmPurchaseScheme> getSmPurchaseSchemeListByParams(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmPurchaseScheme数量
	 * @param SmPurchaseSchemeVo
	 * @return Integer
	 */
	public Integer getSmPurchaseSchemeCountByParams(SmPurchaseSchemeVo smPurchaseSchemeVo);
	
	/**
	 * 根据查询条件获取单个对象SmPurchaseScheme
	 * @param SmPurchaseSchemeVo
	 * @return SmPurchaseScheme
	 */
	public SmPurchaseScheme getSmPurchaseSchemeOneByParams(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmPurchaseScheme
	 * @param ID
	 * @return SmPurchaseScheme
	 */
	public SmPurchaseScheme getSmPurchaseSchemeById(String id);
	
	/**
	 * 添加对象SmPurchaseScheme
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme);
	
	/**
	 * 添加对象SmPurchaseScheme列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmPurchaseSchemeList(List<SmPurchaseScheme> smPurchaseSchemeList);
	
	/**
	 * 更新对象SmPurchaseScheme
	 * @param 实体对象
	 * @return SmPurchaseScheme
	 */
	public void updateSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme);
	
	/**
	 * 删除对象SmPurchaseScheme
	 * @param id数据组
	 */
	public void deleteSmPurchaseScheme(String[] ids);
	
	
	/**
	 * @Title: submitSchemeById
	 * @Description: 归档月度购电计划的方案
	 * @param schemeId
	 * @return 
	 * String
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午1:33:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午1:33:07
	 * @since  1.0.0
	 */
	public void submitSchemeById(String schemeId)  throws Exception;
	
	/**
	 * @Title: deleteSchemeInfo
	 * @Description: 根据方案id删除 方案、方案详情信息列表、公司收益信息、用户收益信息列表
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午2:56:56
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午2:56:56
	 * @since  1.0.0
	 */
	public void deleteSchemeInfo(String[] schemeIds);
	
	/**
	 * @Title: getLcBidListedAvgPrcByYm
	 * @Description: 根据年月获取 双边、竞价、挂牌三个加权平均价
	 * @param ym
	 * @return
	 * @throws Exception 
	 * Map<String,BigDecimal>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午4:17:46
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午4:17:46
	 * @since  1.0.0
	 */
	public Map<String, BigDecimal> getLcBidListedAvgPrcByYm(String ym);
}
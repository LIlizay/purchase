package com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.service.SmCompanyProfitService;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeView;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain.SmPurchaseSchemeVo;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.SmPurchaseSchemeService;

/**
 * SmPurchaseSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmPurchaseSchemeServiceImpl implements SmPurchaseSchemeService {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smCompanyProfitService 月度收益结算中的售电公司收益信息service
	 */
	@Autowired
	private SmCompanyProfitService smCompanyProfitService;
	
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
	public QueryResult<SmPurchaseSchemeView> getSmPurchaseSchemeByPage(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception{
		QueryResult<SmPurchaseSchemeView> queryResult = new QueryResult<SmPurchaseSchemeView>();
		long total = getSmPurchaseSchemeCountByParams(smPurchaseSchemeVo);
		List<SmPurchaseSchemeView> smPurchaseSchemeList = getSchemeViewListByParams(smPurchaseSchemeVo);
		queryResult.setTotal(total);
		queryResult.setData(smPurchaseSchemeList);
		return queryResult;
	}	
	
	/**
	 * @Title: getSchemeViewListByParams
	 * @Description: 根据查询条件获取对象SmPurchaseSchemeView列表,主要比SmPurchaseScheme类多了‘售电公司偏差考核’属性，用于前台页面方案列表展示
	 * @param smPurchaseSchemeVo
	 * @return
	 * @throws Exception 
	 * List<SmPurchaseSchemeView>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月30日 下午11:50:55
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午11:50:55
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SmPurchaseSchemeView> getSchemeViewListByParams(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smPurchaseSchemeVo == null){
			smPurchaseSchemeVo = new SmPurchaseSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<SmPurchaseSchemeView> smPurchaseSchemeList = (List<SmPurchaseSchemeView>)dao.getBySql("smPurchaseScheme.sql.getSchemeViewListByParams",smPurchaseSchemeVo);
		Parameter.isFilterData.set(false);
		//ConvertListUtil.convert(smPurchaseSchemeList);
		return smPurchaseSchemeList;
	}
	
	/**
	 * 根据查询条件获取对象SmPurchaseScheme数量
	 * @param SmPurchaseSchemeVo
	 * @return Integer
	 */
	public Integer getSmPurchaseSchemeCountByParams(SmPurchaseSchemeVo smPurchaseSchemeVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smPurchaseScheme.sql.getSmPurchaseSchemeCountByParams",smPurchaseSchemeVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmPurchaseScheme列表
	 * @param SmPurchaseSchemeVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmPurchaseScheme> getSmPurchaseSchemeListByParams(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smPurchaseSchemeVo == null){
			smPurchaseSchemeVo = new SmPurchaseSchemeVo();
		}
		Parameter.isFilterData.set(true);
		List<SmPurchaseScheme> smPurchaseSchemeList = (List<SmPurchaseScheme>)dao.getBySql("smPurchaseScheme.sql.getSmPurchaseSchemeListByParams",smPurchaseSchemeVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smPurchaseSchemeList);
		return smPurchaseSchemeList;
	}
	/**
	 * 根据查询条件获取单个对象SmPurchaseScheme
	 * @param SmPurchaseSchemeVo
	 * @return SmPurchaseScheme
	 */
	public SmPurchaseScheme getSmPurchaseSchemeOneByParams(SmPurchaseSchemeVo smPurchaseSchemeVo) throws Exception{
		SmPurchaseScheme smPurchaseScheme = null;
		List<SmPurchaseScheme> smPurchaseSchemeList = getSmPurchaseSchemeListByParams(smPurchaseSchemeVo);
		if(smPurchaseSchemeList != null && smPurchaseSchemeList.size() > 0){
			smPurchaseScheme = smPurchaseSchemeList.get(0);
		}
		return smPurchaseScheme;
	}
	
	/**
	 * 根据ID获取对象SmPurchaseScheme
	 * @param ID
	 * @return SmPurchaseScheme
	 */
	public SmPurchaseScheme getSmPurchaseSchemeById(String id) {
		return dao.findById(id, SmPurchaseScheme.class);
	}	
	
	/**
	 * 添加对象SmPurchaseScheme
	 * @param 实体对象
	 */
	public void saveSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme) {
		dao.save(smPurchaseScheme);
	}
	
	/**
	 * 添加对象SmPurchaseScheme
	 * @param 实体对象
	 */
	public void saveSmPurchaseSchemeList(List<SmPurchaseScheme> smPurchaseSchemeList) {
		dao.saveList(smPurchaseSchemeList);
	}
	
	/**
	 * 更新对象SmPurchaseScheme
	 * @param 实体对象
	 */
	public void updateSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme) {
		dao.update(smPurchaseScheme);
	}
	
	/**
	 * 删除对象SmPurchaseScheme
	 * @param id数据组
	 */
	public void deleteSmPurchaseScheme(String[] ids) {
		dao.delete(ids, SmPurchaseScheme.class);
	}
	
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
	@Override
	@Transactional
	public void submitSchemeById(String schemeId)  throws Exception{
		//验证本结算是否已有归档的方案
		Object count = dao.getOneBySQL("smPurchaseScheme.sql.validateSubmitCanOrNot", schemeId);
		int total = count == null ? 0 : Integer.valueOf(count.toString());
		if(total > 0) {
			throw new BusinessException("此结算已存在归档的方案！");
		}
		//验证本本方案是否已计算收益
		count = dao.getOneBySQL("smPurchaseScheme.sql.validateCalculateCanOrNot", schemeId);
		total = count == null ? 0 : Integer.valueOf(count.toString());
		if(total == 0) {
			throw new BusinessException("此方案还未计算结算收益，不能归档！");
		}
		//由jpa模式改为mybatis
		SmPurchaseScheme smPurchaseScheme=dao.findById(schemeId, SmPurchaseScheme.class);
//		smPurchaseScheme.setSchemeStatus(1);
//		dao.update(smPurchaseScheme);
		dao.updateBySql("smPurchaseScheme.sql.submitSchemeById", schemeId);
		
		//生成发票登记信息，删除方案时记得删除相关的发票登记信息
		PhmInvoiceInfo phmInvoice = new PhmInvoiceInfo();
		phmInvoice.setSettleId(smPurchaseScheme.getSettleId());
		dao.save(phmInvoice);
	}
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
	@Override
	@Transactional
	public void deleteSchemeInfo(String[] schemeIds) {
		//这里需要先判断此方案是否已归档，归档的方案不能被删除（前台已先判断了）
		//。。。
		dao.delete(schemeIds, SmPurchaseScheme.class);
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("schemeIds", schemeIds);
		dao.deleteBySql("smPurchaseSchemeDetail.sql.deleteConsInfo", map);
		//删除公司结算数据信息及用户收益列表信息
		smCompanyProfitService.deleteComAndConsProfitBySchemeIds(schemeIds);
	}
	
	/**
	 * @Title: getLcBidListedAvgPrcByYm
	 * @Description: 根据年月获取 双边、竞价、挂牌三个加权平均价
	 * @param ym	yyyyMM或yyyy-MM格式
	 * @return	bidPrcAvg:	 	竞价加权平均价
	 *			lcPrcAvg： 		双边加权平均价
	 *			listedPrcAvg：	挂牌加权平均价
	 * @throws Exception 
	 * Map<String,BigDecimal>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午4:17:46
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午4:17:46
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String, BigDecimal> getLcBidListedAvgPrcByYm(String ym){
		if(ym == null || "".equals(ym)){
			return null;
		}
		ym = ym.replaceAll("-", "");
		Parameter.isFilterData.set(true);
		Map<String, BigDecimal> result = (Map<String, BigDecimal>)dao.getOneBySQL("smPurchaseScheme.sql.getLcBidListedAvgPrcByYm",ym);
		Parameter.isFilterData.set(false);
		return result;
	}
}
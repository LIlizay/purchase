package com.hhwy.purchaseweb.settlement.base.smcompanyprofit.service.impl;

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
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.domain.SmCompanyProfitVo;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.service.SmCompanyProfitService;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service.SmConsumerProfitService;

/**
 * SmCompanyProfitService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmCompanyProfitServiceImpl implements SmCompanyProfitService {

	public static final Logger log = LoggerFactory.getLogger(SmCompanyProfitServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 用户受益 smConsumerProfitService
	 */
	@Autowired
	private SmConsumerProfitService smConsumerProfitService;
	
	/**
	 * 分页获取对象SmCompanyProfit
	 * @param ID
	 * @return SmCompanyProfit
	 */
	public QueryResult<SmCompanyProfit> getSmCompanyProfitByPage(SmCompanyProfitVo smCompanyProfitVo) throws Exception{
		QueryResult<SmCompanyProfit> queryResult = new QueryResult<SmCompanyProfit>();
		long total = getSmCompanyProfitCountByParams(smCompanyProfitVo);
		List<SmCompanyProfit> smCompanyProfitList = getSmCompanyProfitListByParams(smCompanyProfitVo);
		queryResult.setTotal(total);
		queryResult.setData(smCompanyProfitList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SmCompanyProfit数量
	 * @param SmCompanyProfitVo
	 * @return Integer
	 */
	public Integer getSmCompanyProfitCountByParams(SmCompanyProfitVo smCompanyProfitVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smCompanyProfit.sql.getSmCompanyProfitCountByParams",smCompanyProfitVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmCompanyProfit列表
	 * @param SmCompanyProfitVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmCompanyProfit> getSmCompanyProfitListByParams(SmCompanyProfitVo smCompanyProfitVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smCompanyProfitVo == null){
			smCompanyProfitVo = new SmCompanyProfitVo();
		}
		Parameter.isFilterData.set(true);
		List<SmCompanyProfit> smCompanyProfitList = (List<SmCompanyProfit>)dao.getBySql("smCompanyProfit.sql.getSmCompanyProfitListByParams",smCompanyProfitVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(smCompanyProfitList);
		return smCompanyProfitList;
	}
	/**
	 * 根据查询条件获取单个对象SmCompanyProfit
	 * @param SmCompanyProfitVo
	 * @return SmCompanyProfit
	 */
	public SmCompanyProfit getSmCompanyProfitOneByParams(SmCompanyProfitVo smCompanyProfitVo) throws Exception{
		SmCompanyProfit smCompanyProfit = null;
		List<SmCompanyProfit> smCompanyProfitList = getSmCompanyProfitListByParams(smCompanyProfitVo);
		if(smCompanyProfitList != null && smCompanyProfitList.size() > 0){
			smCompanyProfit = smCompanyProfitList.get(0);
		}
		return smCompanyProfit;
	}
	
	/**
	 * 根据ID获取对象SmCompanyProfit
	 * @param ID
	 * @return SmCompanyProfit
	 */
	public SmCompanyProfit getSmCompanyProfitById(String id) {
		return dao.findById(id, SmCompanyProfit.class);
	}	
	
	/**
	 * 添加对象SmCompanyProfit
	 * @param 实体对象
	 */
	public void saveSmCompanyProfit(SmCompanyProfit smCompanyProfit) {
		dao.save(smCompanyProfit);
	}
	
	/**
	 * 添加对象SmCompanyProfit
	 * @param 实体对象
	 */
	public void saveSmCompanyProfitList(List<SmCompanyProfit> smCompanyProfitList) {
		dao.saveList(smCompanyProfitList);
	}
	
	/**
	 * 更新对象SmCompanyProfit
	 * @param 实体对象
	 */
	public void updateSmCompanyProfit(SmCompanyProfit smCompanyProfit) {
		dao.update(smCompanyProfit);
	}
	
	/**
	 * 删除对象SmCompanyProfit
	 * @param id数据组
	 */
	public void deleteSmCompanyProfit(String[] ids) {
		dao.delete(ids, SmCompanyProfit.class);
	}
	
	/**
	 * @Title: getSmCompanyProfitBySchemeId
	 * @Description: 根据方案id获取售电公司结算信息
	 * @param schemeId
	 * @return
	 * @throws Exception SmCompanyProfitVo
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月27日 上午1:14:59
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月27日 上午1:14:59
	 * @since  1.0.0
	 */
	public SmCompanyProfit getSmCompanyProfitBySchemeId(String schemeId) throws Exception{
		if(schemeId == null || "".equals(schemeId)){
			return null;
		}
		SmCompanyProfitVo smCompanyProfitVo = new SmCompanyProfitVo();
		SmCompanyProfit companyProfit = new SmCompanyProfit();
		companyProfit.setSchemeId(schemeId);
		smCompanyProfitVo.setSmCompanyProfit(companyProfit);
		SmCompanyProfit smCompanyProfit = getSmCompanyProfitOneByParams(smCompanyProfitVo);
		
//		smCompanyProfitVo.setSmCompanyProfit(smCompanyProfit);
//		//获取用户受益列表信息 
//		SmConsumerProfitVo smConsumerProfitVo = new SmConsumerProfitVo();
//		SmConsumerProfit consumerProfit = new SmConsumerProfit();
//		consumerProfit.setCompProId(smCompanyProfit.getId());
//		smConsumerProfitVo.setSmConsumerProfit(consumerProfit);
//		List<SmConsumerProfitDetail>  smConsumerProfitDetailList = smConsumerProfitService.getSmConsumerProfitListByParams(smConsumerProfitVo);
		
//		smCompanyProfitVo.setSmConsumerProfitDetailList(smConsumerProfitDetailList);
		return smCompanyProfit;
	}
	/**
	 * @Title: deleteSmCompanyProfitBySchemeIds
	 * @Description: 根据方案id数组删除公司结算数据信息及用户收益列表信息
	 * @param schemeIds void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月28日 上午9:16:22
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月28日 上午9:16:22
	 * @since  1.0.0
	 */
	@Transactional
	public void deleteComAndConsProfitBySchemeIds(String[] schemeIds){
		smConsumerProfitService.deleteSmConsumerProfitBySchemeIds(schemeIds);
		Map<String,Object> param = new HashMap<>();
		param.put("schemeIds", schemeIds);
		dao.deleteBySql("smCompanyProfit.sql.deleteSmComponyProfitBySchemeId", param);
	}
}
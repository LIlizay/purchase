package com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitVo;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service.SmConsumerProfitService;

/**
 * SmConsumerProfitService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SmConsumerProfitServiceImpl implements SmConsumerProfitService {

	public static final Logger log = LoggerFactory.getLogger(SmConsumerProfitServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SmConsumerProfit
	 * @param ID
	 * @return SmConsumerProfit
	 */
	public QueryResult<SmConsumerProfitDetail> getSmConsumerProfitByPage(SmConsumerProfitVo smConsumerProfitVo) throws Exception{
		QueryResult<SmConsumerProfitDetail> queryResult = new QueryResult<SmConsumerProfitDetail>();
		long total = getSmConsumerProfitCountByParams(smConsumerProfitVo);
		List<SmConsumerProfitDetail> smConsumerProfitList = getSmConsumerProfitListByParams(smConsumerProfitVo);
		queryResult.setTotal(total);
		queryResult.setData(smConsumerProfitList);
		return queryResult;
	}	

	
	/**
	 * 根据查询条件获取对象SmConsumerProfit数量
	 * @param SmConsumerProfitVo
	 * @return Integer
	 */
	public Integer getSmConsumerProfitCountByParams(SmConsumerProfitVo smConsumerProfitVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("smConsumerProfit.sql.getSmConsumerProfitCountByParams",smConsumerProfitVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SmConsumerProfit列表
	 * @param SmConsumerProfitVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SmConsumerProfitDetail> getSmConsumerProfitListByParams(SmConsumerProfitVo smConsumerProfitVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(smConsumerProfitVo == null){
			smConsumerProfitVo = new SmConsumerProfitVo();
		}
		Parameter.isFilterData.set(true);
		List<SmConsumerProfitDetail> smConsumerProfitList = (List<SmConsumerProfitDetail>)dao.getBySql("smConsumerProfit.sql.getSmConsumerProfitListByParams",smConsumerProfitVo);
		Parameter.isFilterData.set(false);
		//ConvertListUtil.convert(smConsumerProfitList);
		return smConsumerProfitList;
	}
	/**
	 * 根据查询条件获取单个对象SmConsumerProfit
	 * @param SmConsumerProfitVo
	 * @return SmConsumerProfit
	 */
	public SmConsumerProfitDetail getSmConsumerProfitOneByParams(SmConsumerProfitVo smConsumerProfitVo) throws Exception{
		SmConsumerProfitDetail smConsumerProfit = null;
		List<SmConsumerProfitDetail> smConsumerProfitList = getSmConsumerProfitListByParams(smConsumerProfitVo);
		if(smConsumerProfitList != null && smConsumerProfitList.size() > 0){
			smConsumerProfit = smConsumerProfitList.get(0);
		}
		return smConsumerProfit;
	}
	
	/**
	 * 根据ID获取对象SmConsumerProfit
	 * @param ID
	 * @return SmConsumerProfit
	 */
	public SmConsumerProfit getSmConsumerProfitById(String id) {
		return dao.findById(id, SmConsumerProfit.class);
	}	
	
	/**
	 * 添加对象SmConsumerProfit
	 * @param 实体对象
	 */
	public void saveSmConsumerProfit(SmConsumerProfit smConsumerProfit) {
		dao.save(smConsumerProfit);
	}
	
	/**
	 * 添加对象SmConsumerProfit
	 * @param 实体对象
	 */
	public void saveSmConsumerProfitList(List<SmConsumerProfit> smConsumerProfitList) {
		dao.saveList(smConsumerProfitList);
	}
	
	/**
	 * 更新对象SmConsumerProfit
	 * @param 实体对象
	 */
	public void updateSmConsumerProfit(SmConsumerProfit smConsumerProfit) {
		dao.update(smConsumerProfit);
	}
	
	/**
	 * 删除对象SmConsumerProfit
	 * @param id数据组
	 */
	public void deleteSmConsumerProfit(String[] ids) {
		dao.delete(ids, SmConsumerProfit.class);
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
	public List<SmConsumerProfitDetail> getSmConsumerProfitListBySchemeId(String schemeId) throws Exception{
		if(schemeId == null || "".equals(schemeId)){
			return null;
		}
		//获取用户受益列表信息 
		SmConsumerProfitVo smConsumerProfitVo = new SmConsumerProfitVo();
		SmConsumerProfit consumerProfit = new SmConsumerProfit();
		consumerProfit.setSchemeId(schemeId);
		smConsumerProfitVo.setSmConsumerProfit(consumerProfit);
		List<SmConsumerProfitDetail>  smConsumerProfitList = getSmConsumerProfitListByParams(smConsumerProfitVo);
		return smConsumerProfitList;
	}
	/**
	 * @Title: deleteSmConsumerProfitBySchemeIds
	 * @Description: 根据方案id列表删除用户结算信息
	 * @param schemeIds void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月28日 上午10:46:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月28日 上午10:46:26
	 * @since  1.0.0
	 */
	public void deleteSmConsumerProfitBySchemeIds(String[] schemeIds){
		Map<String,Object> param = new HashMap<>();
		param.put("schemeIds", schemeIds);
		dao.deleteBySql("smConsumerProfit.sql.deleteSmConsumerProfitBySchemeIds", param);
	}
}
package com.hhwy.purchaseweb.agreement.phmagredeviation.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.agreement.phmagredeviation.service.PhmAgreDeviationService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmAgreDeviation;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationDetail;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmAgreDeviationService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmAgreDeviationServiceImpl implements PhmAgreDeviationService {

	public static final Logger log = LoggerFactory.getLogger(PhmAgreDeviationServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmAgreDeviation
	 * @param ID
	 * @return PhmAgreDeviation
	 */
	public QueryResult<PhmAgreDeviationDetail> getPhmAgreDeviationByPage(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception{
		QueryResult<PhmAgreDeviationDetail> queryResult = new QueryResult<PhmAgreDeviationDetail>();
		long total = getPhmAgreDeviationCountByParams(phmAgreDeviationVo);
		List<PhmAgreDeviationDetail> phmAgreDeviationDetailList = getPhmAgreDeviationListByParams(phmAgreDeviationVo);
		queryResult.setTotal(total);
		queryResult.setData(phmAgreDeviationDetailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmAgreDeviation数量
	 * @param PhmAgreDeviationVo
	 * @return Integer
	 */
	public Integer getPhmAgreDeviationCountByParams(PhmAgreDeviationVo phmAgreDeviationVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmAgreDeviation.sql.getPhmAgreDeviationCountByParams",phmAgreDeviationVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmAgreDeviation列表
	 * @param PhmAgreDeviationVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmAgreDeviationDetail> getPhmAgreDeviationListByParams(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmAgreDeviationVo == null){
			phmAgreDeviationVo = new PhmAgreDeviationVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmAgreDeviationDetail> phmAgreDeviationDetailList = (List<PhmAgreDeviationDetail>)dao.getBySql("phmAgreDeviation.sql.getPhmAgreDeviationListByParams",phmAgreDeviationVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmAgreDeviationDetailList);
		return phmAgreDeviationDetailList;
	}
	/**
	 * 根据查询条件获取单个对象PhmAgreDeviation
	 * @param PhmAgreDeviationVo
	 * @return PhmAgreDeviation
	 */
	public PhmAgreDeviationDetail getPhmAgreDeviationOneByParams(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception{
		PhmAgreDeviationDetail phmAgreDeviationDetail = null;
		List<PhmAgreDeviationDetail> phmAgreDeviationDetailList = getPhmAgreDeviationListByParams(phmAgreDeviationVo);
		if(phmAgreDeviationDetailList != null && phmAgreDeviationDetailList.size() > 0){
			phmAgreDeviationDetail = phmAgreDeviationDetailList.get(0);
		}
		return phmAgreDeviationDetail;
	}
	
	/**
	 * 根据ID获取对象PhmAgreDeviation
	 * @param ID
	 * @return PhmAgreDeviation
	 */
	public PhmAgreDeviation getPhmAgreDeviationById(String id) {
		return dao.findById(id, PhmAgreDeviation.class);
	}	
	
	/**
	 * 添加对象PhmAgreDeviation
	 * @param 实体对象
	 */
	public void savePhmAgreDeviation(PhmAgreDeviation phmAgreDeviation) {
		dao.save(phmAgreDeviation);
	}
	
	/**
	 * 添加对象PhmAgreDeviation
	 * @param 实体对象
	 */
	public void savePhmAgreDeviationList(List<PhmAgreDeviation> phmAgreDeviationList) {
		dao.saveList(phmAgreDeviationList);
	}
	
	/**
	 * 更新对象PhmAgreDeviation
	 * @param 实体对象
	 */
	public void updatePhmAgreDeviation(PhmAgreDeviation phmAgreDeviation) {
		dao.update(phmAgreDeviation);
	}
	
	/**
	 * 删除对象PhmAgreDeviation
	 * @param id数据组
	 */
	public void deletePhmAgreDeviation(String[] ids) {
		dao.delete(ids, PhmAgreDeviation.class);
	}	
}
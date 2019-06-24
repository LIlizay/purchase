package com.hhwy.purchaseweb.plan.phmplanyearconsrela.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmplanyearconsrela.service.PhmPlanYearConsRelaService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmPlanYearConsRela;
import com.hhwy.purchaseweb.plan.phmplanyearconsrela.domain.PhmPlanYearConsRelaVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmPlanYearConsRelaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmPlanYearConsRelaServiceImpl implements PhmPlanYearConsRelaService {

	public static final Logger log = LoggerFactory.getLogger(PhmPlanYearConsRelaServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmPlanYearConsRela
	 * @param ID
	 * @return PhmPlanYearConsRela
	 */
	public QueryResult<PhmPlanYearConsRela> getPhmPlanYearConsRelaByPage(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception{
		QueryResult<PhmPlanYearConsRela> queryResult = new QueryResult<PhmPlanYearConsRela>();
		long total = getPhmPlanYearConsRelaCountByParams(phmPlanYearConsRelaVo);
		List<PhmPlanYearConsRela> phmPlanYearConsRelaList = getPhmPlanYearConsRelaListByParams(phmPlanYearConsRelaVo);
		queryResult.setTotal(total);
		queryResult.setData(phmPlanYearConsRelaList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmPlanYearConsRela数量
	 * @param PhmPlanYearConsRelaVo
	 * @return Integer
	 */
	public Integer getPhmPlanYearConsRelaCountByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPlanYearConsRela.sql.getPhmPlanYearConsRelaCountByParams",phmPlanYearConsRelaVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmPlanYearConsRela列表
	 * @param PhmPlanYearConsRelaVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPlanYearConsRela> getPhmPlanYearConsRelaListByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPlanYearConsRelaVo == null){
			phmPlanYearConsRelaVo = new PhmPlanYearConsRelaVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPlanYearConsRela> phmPlanYearConsRelaList = (List<PhmPlanYearConsRela>)dao.getBySql("phmPlanYearConsRela.sql.getPhmPlanYearConsRelaListByParams",phmPlanYearConsRelaVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmPlanYearConsRelaList);
		return phmPlanYearConsRelaList;
	}
	/**
	 * 根据查询条件获取单个对象PhmPlanYearConsRela
	 * @param PhmPlanYearConsRelaVo
	 * @return PhmPlanYearConsRela
	 */
	public PhmPlanYearConsRela getPhmPlanYearConsRelaOneByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception{
		PhmPlanYearConsRela phmPlanYearConsRela = null;
		List<PhmPlanYearConsRela> phmPlanYearConsRelaList = getPhmPlanYearConsRelaListByParams(phmPlanYearConsRelaVo);
		if(phmPlanYearConsRelaList != null && phmPlanYearConsRelaList.size() > 0){
			phmPlanYearConsRela = phmPlanYearConsRelaList.get(0);
		}
		return phmPlanYearConsRela;
	}
	
	/**
	 * 根据ID获取对象PhmPlanYearConsRela
	 * @param ID
	 * @return PhmPlanYearConsRela
	 */
	public PhmPlanYearConsRela getPhmPlanYearConsRelaById(String id) {
		return dao.findById(id, PhmPlanYearConsRela.class);
	}	
	
	/**
	 * 添加对象PhmPlanYearConsRela
	 * @param 实体对象
	 */
	public void savePhmPlanYearConsRela(PhmPlanYearConsRela phmPlanYearConsRela) {
		dao.save(phmPlanYearConsRela);
	}
	
	/**
	 * 添加对象PhmPlanYearConsRela
	 * @param 实体对象
	 */
	public void savePhmPlanYearConsRelaList(List<PhmPlanYearConsRela> phmPlanYearConsRelaList) {
		dao.saveList(phmPlanYearConsRelaList);
	}
	
	/**
	 * 更新对象PhmPlanYearConsRela
	 * @param 实体对象
	 */
	public void updatePhmPlanYearConsRela(PhmPlanYearConsRela phmPlanYearConsRela) {
		dao.update(phmPlanYearConsRela);
	}
	
	/**
	 * 删除对象PhmPlanYearConsRela
	 * @param id数据组
	 */
	public void deletePhmPlanYearConsRela(String[] ids) {
		dao.delete(ids, PhmPlanYearConsRela.class);
	}	
	
	/**
	 * @Title: deletePhmPlanYearConsRelaByPlanId
	 * @Description: 根据年度购电计划id删除对象PhmPlanYearConsRela
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年12月15日 下午2:28:37
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年12月15日 下午2:28:37
	 * @since  1.0.0
	 */
	public void deletePhmPlanYearConsRelaByPlanId(String planId) {
		dao.deleteBySql("phmPlanYearConsRela.sql.deleteRelaByPlanId", planId);
	}
	
}
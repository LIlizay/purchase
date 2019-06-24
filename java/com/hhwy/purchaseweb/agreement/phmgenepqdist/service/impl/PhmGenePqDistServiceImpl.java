package com.hhwy.purchaseweb.agreement.phmgenepqdist.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.agreement.phmgenepqdist.service.PhmGenePqDistService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.purchaseweb.agreement.phmgenepqdist.domain.PhmGenePqDistVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmGenePqDistService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmGenePqDistServiceImpl implements PhmGenePqDistService {

	public static final Logger log = LoggerFactory.getLogger(PhmGenePqDistServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmGenePqDist
	 * @param ID
	 * @return PhmGenePqDist
	 */
	public QueryResult<PhmGenePqDist> getPhmGenePqDistByPage(PhmGenePqDistVo phmGenePqDistVo) throws Exception{
		QueryResult<PhmGenePqDist> queryResult = new QueryResult<PhmGenePqDist>();
		long total = getPhmGenePqDistCountByParams(phmGenePqDistVo);
		List<PhmGenePqDist> phmGenePqDistList = getPhmGenePqDistListByParams(phmGenePqDistVo);
		queryResult.setTotal(total);
		queryResult.setData(phmGenePqDistList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmGenePqDist数量
	 * @param PhmGenePqDistVo
	 * @return Integer
	 */
	public Integer getPhmGenePqDistCountByParams(PhmGenePqDistVo phmGenePqDistVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmGenePqDist.sql.getPhmGenePqDistCountByParams",phmGenePqDistVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmGenePqDist列表
	 * @param PhmGenePqDistVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmGenePqDist> getPhmGenePqDistListByParams(PhmGenePqDistVo phmGenePqDistVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmGenePqDistVo == null){
			phmGenePqDistVo = new PhmGenePqDistVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmGenePqDist> phmGenePqDistList = (List<PhmGenePqDist>)dao.getBySql("phmGenePqDist.sql.getPhmGenePqDistListByParams",phmGenePqDistVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmGenePqDistList);
		return phmGenePqDistList;
	}
	/**
	 * 根据查询条件获取单个对象PhmGenePqDist
	 * @param PhmGenePqDistVo
	 * @return PhmGenePqDist
	 */
	public PhmGenePqDist getPhmGenePqDistOneByParams(PhmGenePqDistVo phmGenePqDistVo) throws Exception{
		PhmGenePqDist phmGenePqDist = null;
		List<PhmGenePqDist> phmGenePqDistList = getPhmGenePqDistListByParams(phmGenePqDistVo);
		if(phmGenePqDistList != null && phmGenePqDistList.size() > 0){
			phmGenePqDist = phmGenePqDistList.get(0);
		}
		return phmGenePqDist;
	}
	
	/**
	 * 根据ID获取对象PhmGenePqDist
	 * @param ID
	 * @return PhmGenePqDist
	 */
	public PhmGenePqDist getPhmGenePqDistById(String id) {
		return dao.findById(id, PhmGenePqDist.class);
	}	
	
	/**
	 * 添加对象PhmGenePqDist
	 * @param 实体对象
	 */
	public void savePhmGenePqDist(PhmGenePqDist phmGenePqDist) {
		dao.save(phmGenePqDist);
	}
	
	/**
	 * 添加对象PhmGenePqDist
	 * @param 实体对象
	 */
	public void savePhmGenePqDistList(List<PhmGenePqDist> phmGenePqDistList) {
		dao.saveList(phmGenePqDistList);
	}
	
	/**
	 * 更新对象PhmGenePqDist
	 * @param 实体对象
	 */
	public void updatePhmGenePqDist(PhmGenePqDist phmGenePqDist) {
		dao.update(phmGenePqDist);
	}
	
	/**
	 * 删除对象PhmGenePqDist
	 * @param id数据组
	 */
	public void deletePhmGenePqDist(String[] ids) {
		dao.delete(ids, PhmGenePqDist.class);
	}	
}
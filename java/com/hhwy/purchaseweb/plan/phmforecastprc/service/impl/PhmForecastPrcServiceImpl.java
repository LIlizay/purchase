package com.hhwy.purchaseweb.plan.phmforecastprc.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmforecastprc.service.PhmForecastPrcService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmForecastPrc;
import com.hhwy.purchaseweb.plan.phmforecastprc.domain.PhmForecastPrcVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmForecastPrcService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmForecastPrcServiceImpl implements PhmForecastPrcService {

	public static final Logger log = LoggerFactory.getLogger(PhmForecastPrcServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmForecastPrc
	 * @param ID
	 * @return PhmForecastPrc
	 */
	public QueryResult<PhmForecastPrc> getPhmForecastPrcByPage(PhmForecastPrcVo phmForecastPrcVo) throws Exception{
		QueryResult<PhmForecastPrc> queryResult = new QueryResult<PhmForecastPrc>();
		long total = getPhmForecastPrcCountByParams(phmForecastPrcVo);
		List<PhmForecastPrc> phmForecastPrcList = getPhmForecastPrcListByParams(phmForecastPrcVo);
		queryResult.setTotal(total);
		queryResult.setData(phmForecastPrcList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmForecastPrc数量
	 * @param PhmForecastPrcVo
	 * @return Integer
	 */
	public Integer getPhmForecastPrcCountByParams(PhmForecastPrcVo phmForecastPrcVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmForecastPrc.sql.getPhmForecastPrcCountByParams",phmForecastPrcVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmForecastPrc列表
	 * @param PhmForecastPrcVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmForecastPrc> getPhmForecastPrcListByParams(PhmForecastPrcVo phmForecastPrcVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmForecastPrcVo == null){
			phmForecastPrcVo = new PhmForecastPrcVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmForecastPrc> phmForecastPrcList = (List<PhmForecastPrc>)dao.getBySql("phmForecastPrc.sql.getPhmForecastPrcListByParams",phmForecastPrcVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmForecastPrcList);
		return phmForecastPrcList;
	}
	/**
	 * 根据查询条件获取单个对象PhmForecastPrc
	 * @param PhmForecastPrcVo
	 * @return PhmForecastPrc
	 */
	public PhmForecastPrc getPhmForecastPrcOneByParams(PhmForecastPrcVo phmForecastPrcVo) throws Exception{
		PhmForecastPrc phmForecastPrc = null;
		List<PhmForecastPrc> phmForecastPrcList = getPhmForecastPrcListByParams(phmForecastPrcVo);
		if(phmForecastPrcList != null && phmForecastPrcList.size() > 0){
			phmForecastPrc = phmForecastPrcList.get(0);
		}
		return phmForecastPrc;
	}
	
	/**
	 * 根据ID获取对象PhmForecastPrc
	 * @param ID
	 * @return PhmForecastPrc
	 */
	public PhmForecastPrc getPhmForecastPrcById(String id) {
		return dao.findById(id, PhmForecastPrc.class);
	}	
	
	/**
	 * 添加对象PhmForecastPrc
	 * @param 实体对象
	 */
	public void savePhmForecastPrc(PhmForecastPrc phmForecastPrc) {
		dao.save(phmForecastPrc);
	}
	
	/**
	 * 添加对象PhmForecastPrc
	 * @param 实体对象
	 */
	public void savePhmForecastPrcList(List<PhmForecastPrc> phmForecastPrcList) {
		dao.saveList(phmForecastPrcList);
	}
	
	/**
	 * 更新对象PhmForecastPrc
	 * @param 实体对象
	 */
	public void updatePhmForecastPrc(PhmForecastPrc phmForecastPrc) {
		dao.update(phmForecastPrc);
	}
	
	/**
	 * 删除对象PhmForecastPrc
	 * @param id数据组
	 */
	public void deletePhmForecastPrc(String[] ids) {
		dao.delete(ids, PhmForecastPrc.class);
	}	
}
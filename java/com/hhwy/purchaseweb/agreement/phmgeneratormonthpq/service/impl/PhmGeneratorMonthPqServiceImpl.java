package com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.service.PhmGeneratorMonthPqService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmGeneratorMonthPqService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmGeneratorMonthPqServiceImpl implements PhmGeneratorMonthPqService {

	public static final Logger log = LoggerFactory.getLogger(PhmGeneratorMonthPqServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmGeneratorMonthPq
	 * @param ID
	 * @return PhmGeneratorMonthPq
	 */
	public QueryResult<PhmGeneratorMonthPqDetail> getPhmGeneratorMonthPqByPage(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception{
		QueryResult<PhmGeneratorMonthPqDetail> queryResult = new QueryResult<PhmGeneratorMonthPqDetail>();
		long total = getPhmGeneratorMonthPqCountByParams(phmGeneratorMonthPqVo);
		List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = getPhmGeneratorMonthPqListByParams(phmGeneratorMonthPqVo);
		queryResult.setTotal(total);
		queryResult.setData(phmGeneratorMonthPqDetailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmGeneratorMonthPq数量
	 * @param PhmGeneratorMonthPqVo
	 * @return Integer
	 */
	public Integer getPhmGeneratorMonthPqCountByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmGeneratorMonthPq.sql.getPhmGeneratorMonthPqCountByParams",phmGeneratorMonthPqVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmGeneratorMonthPq列表
	 * @param PhmGeneratorMonthPqVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmGeneratorMonthPqDetail> getPhmGeneratorMonthPqListByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmGeneratorMonthPqVo == null){
			phmGeneratorMonthPqVo = new PhmGeneratorMonthPqVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = (List<PhmGeneratorMonthPqDetail>)dao.getBySql("phmGeneratorMonthPq.sql.getPhmGeneratorMonthPqListByParams",phmGeneratorMonthPqVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmGeneratorMonthPqDetailList);
		return phmGeneratorMonthPqDetailList;
	}
	/**
	 * 根据查询条件获取单个对象PhmGeneratorMonthPq
	 * @param PhmGeneratorMonthPqVo
	 * @return PhmGeneratorMonthPq
	 */
	public PhmGeneratorMonthPqDetail getPhmGeneratorMonthPqOneByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception{
		PhmGeneratorMonthPqDetail phmGeneratorMonthPqDetail = null;
		List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = getPhmGeneratorMonthPqListByParams(phmGeneratorMonthPqVo);
		if(phmGeneratorMonthPqDetailList != null && phmGeneratorMonthPqDetailList.size() > 0){
			phmGeneratorMonthPqDetail = phmGeneratorMonthPqDetailList.get(0);
		}
		return phmGeneratorMonthPqDetail;
	}
	
	/**
	 * 根据ID获取对象PhmGeneratorMonthPq
	 * @param ID
	 * @return PhmGeneratorMonthPq
	 */
	public PhmGeneratorMonthPq getPhmGeneratorMonthPqById(String id) {
		return dao.findById(id, PhmGeneratorMonthPq.class);
	}	
	
	/**
	 * 添加对象PhmGeneratorMonthPq
	 * @param 实体对象
	 */
	public void savePhmGeneratorMonthPq(PhmGeneratorMonthPq phmGeneratorMonthPq) {
		dao.save(phmGeneratorMonthPq);
	}
	
	/**
	 * 添加对象PhmGeneratorMonthPq
	 * @param 实体对象
	 */
	public void savePhmGeneratorMonthPqList(List<PhmGeneratorMonthPq> phmGeneratorMonthPqList) {
		dao.saveList(phmGeneratorMonthPqList);
	}
	
	/**
	 * 更新对象PhmGeneratorMonthPq
	 * @param 实体对象
	 */
	public void updatePhmGeneratorMonthPq(PhmGeneratorMonthPq phmGeneratorMonthPq) {
		dao.update(phmGeneratorMonthPq);
	}
	
	/**
	 * 删除对象PhmGeneratorMonthPq
	 * @param id数据组
	 */
	public void deletePhmGeneratorMonthPq(String[] ids) {
		dao.delete(ids, PhmGeneratorMonthPq.class);
	}
}
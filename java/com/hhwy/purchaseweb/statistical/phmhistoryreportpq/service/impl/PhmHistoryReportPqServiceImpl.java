package com.hhwy.purchaseweb.statistical.phmhistoryreportpq.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.service.PhmHistoryReportPqService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmHistoryReportPq;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmHistoryReportPqService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmHistoryReportPqServiceImpl implements PhmHistoryReportPqService {

	public static final Logger log = LoggerFactory.getLogger(PhmHistoryReportPqServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmHistoryReportPq
	 * @param ID
	 * @return PhmHistoryReportPq
	 */
	public QueryResult<PhmHistoryReportPq> getPhmHistoryReportPqByPage(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception{
		QueryResult<PhmHistoryReportPq> queryResult = new QueryResult<PhmHistoryReportPq>();
		long total = getPhmHistoryReportPqCountByParams(phmHistoryReportPqVo);
		List<PhmHistoryReportPq> phmHistoryReportPqList = getPhmHistoryReportPqListByParams(phmHistoryReportPqVo);
		queryResult.setTotal(total);
		queryResult.setData(phmHistoryReportPqList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPq数量
	 * @param PhmHistoryReportPqVo
	 * @return Integer
	 */
	public Integer getPhmHistoryReportPqCountByParams(PhmHistoryReportPqVo phmHistoryReportPqVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmHistoryReportPq.sql.getPhmHistoryReportPqCountByParams",phmHistoryReportPqVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPq列表
	 * @param PhmHistoryReportPqVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmHistoryReportPq> getPhmHistoryReportPqListByParams(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmHistoryReportPqVo == null){
			phmHistoryReportPqVo = new PhmHistoryReportPqVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmHistoryReportPq> phmHistoryReportPqList = (List<PhmHistoryReportPq>)dao.getBySql("phmHistoryReportPq.sql.getPhmHistoryReportPqListByParams",phmHistoryReportPqVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmHistoryReportPqList);
		return phmHistoryReportPqList;
	}
	/**
	 * 根据查询条件获取单个对象PhmHistoryReportPq
	 * @param PhmHistoryReportPqVo
	 * @return PhmHistoryReportPq
	 */
	public PhmHistoryReportPq getPhmHistoryReportPqOneByParams(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception{
		PhmHistoryReportPq phmHistoryReportPq = null;
		List<PhmHistoryReportPq> phmHistoryReportPqList = getPhmHistoryReportPqListByParams(phmHistoryReportPqVo);
		if(phmHistoryReportPqList != null && phmHistoryReportPqList.size() > 0){
			phmHistoryReportPq = phmHistoryReportPqList.get(0);
		}
		return phmHistoryReportPq;
	}
	
	/**
	 * 根据ID获取对象PhmHistoryReportPq
	 * @param ID
	 * @return PhmHistoryReportPq
	 */
	public PhmHistoryReportPq getPhmHistoryReportPqById(String id) {
		return dao.findById(id, PhmHistoryReportPq.class);
	}	
	
	/**
     * 根据年份获取历史电量
     * getPqHistoryByYear(描述这个方法的作用)<br/>
     * @param year
     * @return
     * @throws Exception 
     * List<PhmHistoryReportPq>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhmHistoryReportPq> getPqHistoryByYear(String year) throws Exception{
    	Parameter.isFilterData.set(true);
    	List<PhmHistoryReportPq> list = (List<PhmHistoryReportPq>) dao.getBySql("phmHistoryReportPq.sql.getPqHistoryByYear", year);
    	Parameter.isFilterData.set(false);
        return list;
    }
    
	/**
	 * 添加对象PhmHistoryReportPq
	 * @param 实体对象
	 */
	public void savePhmHistoryReportPq(PhmHistoryReportPq phmHistoryReportPq) {
		dao.save(phmHistoryReportPq);
	}
	
	/**
	 * 添加对象PhmHistoryReportPq
	 * @param 实体对象
	 */
	public void savePhmHistoryReportPqList(List<PhmHistoryReportPq> phmHistoryReportPqList) {
		dao.saveList(phmHistoryReportPqList);
	}
	
	/**
	 * 更新对象PhmHistoryReportPq
	 * @param 实体对象
	 */
	public void updatePhmHistoryReportPq(PhmHistoryReportPq phmHistoryReportPq) {
		dao.update(phmHistoryReportPq);
	}
	
	/**
	 * 删除对象PhmHistoryReportPq
	 * @param id数据组
	 */
	public void deletePhmHistoryReportPq(String[] ids) {
		dao.delete(ids, PhmHistoryReportPq.class);
	}	
	
	 /**
     * 根据年份获取历史报量信息
     * getPqHistoryPage(描述这个方法的作用)<br/>
     * @param ym
     * @return 
     * PhmHistoryReportPqDetail
     * @exception 
     * @since  1.0.0
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PhmHistoryReportPqDetail> getPqHistoryPage(String ym){
    	Parameter.isFilterData.set(true);
        List<PhmHistoryReportPqDetail> list = (List<PhmHistoryReportPqDetail>) dao.getBySql("phmHistoryReportPq.sql.getPqHistory",ym);
        Parameter.isFilterData.set(false);
        return list;
    }
}
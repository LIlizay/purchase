package com.hhwy.purchaseweb.forecast.phfpurcreport.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phfpurcreport.service.PhfPurcReportService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfPurcReport;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchaseweb.forecast.phfpurcreport.domain.PhfPurcReportVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhfPurcReportService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfPurcReportServiceImpl implements PhfPurcReportService {

	public static final Logger log = LoggerFactory.getLogger(PhfPurcReportServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhfPurcReport
	 * @param ID
	 * @return PhfPurcReport
	 */
	public QueryResult<PhfPurcReport> getPhfPurcReportByPage(PhfPurcReportVo phfPurcReportVo) throws Exception{
		QueryResult<PhfPurcReport> queryResult = new QueryResult<PhfPurcReport>();
		long total = getPhfPurcReportCountByParams(phfPurcReportVo);
		List<PhfPurcReport> phfPurcReportList = getPhfPurcReportListByParams(phfPurcReportVo);
		queryResult.setTotal(total);
		queryResult.setData(phfPurcReportList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfPurcReport数量
	 * @param PhfPurcReportVo
	 * @return Integer
	 */
	public Integer getPhfPurcReportCountByParams(PhfPurcReportVo phfPurcReportVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfPurcReport.sql.getPhfPurcReportCountByParams",phfPurcReportVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfPurcReport列表
	 * @param PhfPurcReportVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfPurcReport> getPhfPurcReportListByParams(PhfPurcReportVo phfPurcReportVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfPurcReportVo == null){
			phfPurcReportVo = new PhfPurcReportVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfPurcReport> phfPurcReportList = (List<PhfPurcReport>)dao.getBySql("phfPurcReport.sql.getPhfPurcReportListByParams",phfPurcReportVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfPurcReportList);
		return phfPurcReportList;
	}
	/**
	 * 根据查询条件获取单个对象PhfPurcReport
	 * @param PhfPurcReportVo
	 * @return PhfPurcReport
	 */
	public PhfPurcReport getPhfPurcReportOneByParams(PhfPurcReportVo phfPurcReportVo) throws Exception{
		PhfPurcReport phfPurcReport = null;
		List<PhfPurcReport> phfPurcReportList = getPhfPurcReportListByParams(phfPurcReportVo);
		if(phfPurcReportList != null && phfPurcReportList.size() > 0){
			phfPurcReport = phfPurcReportList.get(0);
		}
		return phfPurcReport;
	}
	
	/**
	 * 根据ID获取对象PhfPurcReport
	 * @param ID
	 * @return PhfPurcReport
	 */
	public PhfPurcReport getPhfPurcReportById(String id) {
		return dao.findById(id, PhfPurcReport.class);
	}	
	
	/**
	 * 添加对象PhfPurcReport
	 * @param 实体对象
	 */
	public void savePhfPurcReport(PhfPurcReport phfPurcReport) {
		dao.save(phfPurcReport);
	}
	
	/**
	 * 添加对象PhfPurcReport
	 * @param 实体对象
	 */
	public void savePhfPurcReportList(List<PhfPurcReport> phfPurcReportList) {
		dao.saveList(phfPurcReportList);
	}
	
	/**
	 * 更新对象PhfPurcReport
	 * @param 实体对象
	 */
	public void updatePhfPurcReport(PhfPurcReport phfPurcReport) {
		dao.update(phfPurcReport);
	}
	
	/**
	 * 删除对象PhfPurcReport
	 * @param id数据组
	 */
	public void deletePhfPurcReport(String[] ids) {
		dao.delete(ids, PhfPurcReport.class);
	}	
}
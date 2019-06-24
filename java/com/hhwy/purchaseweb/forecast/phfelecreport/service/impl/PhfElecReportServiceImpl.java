package com.hhwy.purchaseweb.forecast.phfelecreport.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phfelecreport.service.PhfElecReportService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfElecReport;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchaseweb.forecast.phfelecreport.domain.PhfElecReportVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhfElecReportService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfElecReportServiceImpl implements PhfElecReportService {

	public static final Logger log = LoggerFactory.getLogger(PhfElecReportServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhfElecReport
	 * @param ID
	 * @return PhfElecReport
	 */
	public QueryResult<PhfElecReport> getPhfElecReportByPage(PhfElecReportVo phfElecReportVo) throws Exception{
		QueryResult<PhfElecReport> queryResult = new QueryResult<PhfElecReport>();
		long total = getPhfElecReportCountByParams(phfElecReportVo);
		List<PhfElecReport> phfElecReportList = getPhfElecReportListByParams(phfElecReportVo);
		queryResult.setTotal(total);
		queryResult.setData(phfElecReportList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfElecReport数量
	 * @param PhfElecReportVo
	 * @return Integer
	 */
	public Integer getPhfElecReportCountByParams(PhfElecReportVo phfElecReportVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfElecReport.sql.getPhfElecReportCountByParams",phfElecReportVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfElecReport列表
	 * @param PhfElecReportVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfElecReport> getPhfElecReportListByParams(PhfElecReportVo phfElecReportVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfElecReportVo == null){
			phfElecReportVo = new PhfElecReportVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfElecReport> phfElecReportList = (List<PhfElecReport>)dao.getBySql("phfElecReport.sql.getPhfElecReportListByParams",phfElecReportVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfElecReportList);
		return phfElecReportList;
	}
	/**
	 * 根据查询条件获取单个对象PhfElecReport
	 * @param PhfElecReportVo
	 * @return PhfElecReport
	 */
	public PhfElecReport getPhfElecReportOneByParams(PhfElecReportVo phfElecReportVo) throws Exception{
		PhfElecReport phfElecReport = null;
		List<PhfElecReport> phfElecReportList = getPhfElecReportListByParams(phfElecReportVo);
		if(phfElecReportList != null && phfElecReportList.size() > 0){
			phfElecReport = phfElecReportList.get(0);
		}
		return phfElecReport;
	}
	
	/**
	 * 根据ID获取对象PhfElecReport
	 * @param ID
	 * @return PhfElecReport
	 */
	public PhfElecReport getPhfElecReportById(String id) {
		return dao.findById(id, PhfElecReport.class);
	}	
	
	/**
	 * 添加对象PhfElecReport
	 * @param 实体对象
	 */
	public void savePhfElecReport(PhfElecReport phfElecReport) {
		dao.save(phfElecReport);
	}
	
	/**
	 * 添加对象PhfElecReport
	 * @param 实体对象
	 */
	public void savePhfElecReportList(List<PhfElecReport> phfElecReportList) {
		dao.saveList(phfElecReportList);
	}
	
	/**
	 * 更新对象PhfElecReport
	 * @param 实体对象
	 */
	public void updatePhfElecReport(PhfElecReport phfElecReport) {
		dao.update(phfElecReport);
	}
	
	/**
	 * 删除对象PhfElecReport
	 * @param id数据组
	 */
	public void deletePhfElecReport(String[] ids) {
		dao.delete(ids, PhfElecReport.class);
	}	
}
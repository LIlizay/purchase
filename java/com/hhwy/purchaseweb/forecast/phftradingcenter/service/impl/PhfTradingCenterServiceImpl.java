package com.hhwy.purchaseweb.forecast.phftradingcenter.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phftradingcenter.service.PhfTradingCenterService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfElecReport;
import com.hhwy.purchase.domain.PhfPurcReport;
import com.hhwy.purchase.domain.PhfTradingCenter;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchaseweb.forecast.phfelecreport.domain.PhfElecReportVo;
import com.hhwy.purchaseweb.forecast.phfelecreport.service.PhfElecReportService;
import com.hhwy.purchaseweb.forecast.phfpurcreport.domain.PhfPurcReportVo;
import com.hhwy.purchaseweb.forecast.phfpurcreport.service.PhfPurcReportService;
import com.hhwy.purchaseweb.forecast.phftradingcenter.domain.PhfTradingCenterVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PhfTradingCenterService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfTradingCenterServiceImpl implements PhfTradingCenterService {

	public static final Logger log = LoggerFactory.getLogger(PhfTradingCenterServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	@Autowired
	private PhfElecReportService phfElecReportService;
	
	@Autowired
	private PhfPurcReportService phfPurcReportService;
	/**
	 * 分页获取对象PhfTradingCenter
	 * @param ID
	 * @return PhfTradingCenter
	 */
	public QueryResult<PhfTradingCenter> getPhfTradingCenterByPage(PhfTradingCenterVo phfTradingCenterVo) throws Exception{
		String startTime = phfTradingCenterVo.getStartTime();
		String endTime = phfTradingCenterVo.getEndTime();
		if(startTime != null && !"".equals(startTime) && startTime.indexOf("-") != -1){
			startTime = startTime.replace("-", "");
			phfTradingCenterVo.setStartTime(startTime);
		}
		if(endTime != null && !"".equals(endTime) && endTime.indexOf("-") != -1){
			endTime = endTime.replace("-", "");
			phfTradingCenterVo.setEndTime(endTime);
		}
		QueryResult<PhfTradingCenter> queryResult = new QueryResult<PhfTradingCenter>();
		long total = getPhfTradingCenterCountByParams(phfTradingCenterVo);
		List<PhfTradingCenter> phfTradingCenterList = getPhfTradingCenterListByParams(phfTradingCenterVo);
		queryResult.setTotal(total);
		queryResult.setData(phfTradingCenterList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfTradingCenter数量
	 * @param PhfTradingCenterVo
	 * @return Integer
	 */
	public Integer getPhfTradingCenterCountByParams(PhfTradingCenterVo phfTradingCenterVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfTradingCenter.sql.getPhfTradingCenterCountByParams",phfTradingCenterVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfTradingCenter列表
	 * @param PhfTradingCenterVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfTradingCenter> getPhfTradingCenterListByParams(PhfTradingCenterVo phfTradingCenterVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfTradingCenterVo == null){
			phfTradingCenterVo = new PhfTradingCenterVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfTradingCenter> phfTradingCenterList = (List<PhfTradingCenter>)dao.getBySql("phfTradingCenter.sql.getPhfTradingCenterListByParams",phfTradingCenterVo);
		Parameter.isFilterData.set(false);
		for(PhfTradingCenter phfTradingCenter:phfTradingCenterList){
			BigDecimal divisor  = new BigDecimal("100000");
			phfTradingCenter.setTotalDealPq(phfTradingCenter.getTotalDealPq().divide(divisor));
			phfTradingCenter.setElecReportPq(phfTradingCenter.getElecReportPq().divide(divisor));
			phfTradingCenter.setCompUndealNum(phfTradingCenter.getCompUndealNum().divide(divisor));
			phfTradingCenter.setConsUndealNum(phfTradingCenter.getConsUndealNum().divide(divisor));
			phfTradingCenter.setPurcReportPq(phfTradingCenter.getPurcReportPq().divide(divisor));
		}
		ConvertListUtil.convert(phfTradingCenterList);
		return phfTradingCenterList;
	}
	/**
	 * 根据查询条件获取单个对象PhfTradingCenter
	 * @param PhfTradingCenterVo
	 * @return PhfTradingCenter
	 */
	public PhfTradingCenter getPhfTradingCenterOneByParams(PhfTradingCenterVo phfTradingCenterVo) throws Exception{
		PhfTradingCenter phfTradingCenter = null;
		List<PhfTradingCenter> phfTradingCenterList = getPhfTradingCenterListByParams(phfTradingCenterVo);
		if(phfTradingCenterList != null && phfTradingCenterList.size() > 0){
			phfTradingCenter = phfTradingCenterList.get(0);
		}
		return phfTradingCenter;
	}
	
	/**
	 * 根据ID获取对象PhfTradingCenter
	 * @param ID
	 * @return PhfTradingCenter
	 */
	public PhfTradingCenter getPhfTradingCenterById(String id) {
		return dao.findById(id, PhfTradingCenter.class);
	}	
	
	/**
	 * 添加对象PhfTradingCenter
	 * @param 实体对象
	 */
	public void savePhfTradingCenter(PhfTradingCenter phfTradingCenter) {
		dao.save(phfTradingCenter);
	}
	
	/**
	 * 添加对象PhfTradingCenter
	 * @param 实体对象
	 */
	public void savePhfTradingCenterList(List<PhfTradingCenter> phfTradingCenterList) {
		dao.saveList(phfTradingCenterList);
	}
	
	/**
	 * 更新对象PhfTradingCenter
	 * @param 实体对象
	 */
	public void updatePhfTradingCenter(PhfTradingCenter phfTradingCenter) {
		dao.update(phfTradingCenter);
	}
	
	/**
	 * 删除对象PhfTradingCenter
	 * @param id数据组
	 */
	public void deletePhfTradingCenter(String[] ids) {
		dao.delete(ids, PhfTradingCenter.class);
		
		//根据centerId删除当前两个表中的旧数据
		for(int i=0;i<ids.length;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", ids[i]);
			dao.deleteBySql("phfElecReport.sql.deleteInfo", map);
			dao.deleteBySql("phfPurcReport.sql.deleteInfo", map);
		}
	}

	/**
	 * 保存预测模块的信息
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public String saveForecastInfo(PhfTradingCenterVo phfTradingCenterVo) throws Exception {
		//查看当前年月是否已经存在
		PhfTradingCenterVo PhfTradingCenterVoOld=new PhfTradingCenterVo();
		PhfTradingCenterVoOld.getPhfTradingCenter().setYm(phfTradingCenterVo.getPhfTradingCenter().getYm());
		PhfTradingCenter phfTradingCenterOld=getPhfTradingCenterOneByParams(PhfTradingCenterVoOld);
		if(phfTradingCenterOld!=null){
			return "1";
		}
		//将亿/千瓦时换算为兆瓦时
		PhfTradingCenter phfTradingCenter=phfTradingCenterVo.getPhfTradingCenter();
		BigDecimal divisor  = new BigDecimal("100000");
		phfTradingCenter.setTotalDealPq(phfTradingCenter.getTotalDealPq().multiply(divisor));
		phfTradingCenter.setElecReportPq(phfTradingCenter.getElecReportPq().multiply(divisor));
		phfTradingCenter.setCompUndealNum(phfTradingCenter.getCompUndealNum().multiply(divisor));
		phfTradingCenter.setConsUndealNum(phfTradingCenter.getConsUndealNum().multiply(divisor));
		phfTradingCenter.setPurcReportPq(phfTradingCenter.getPurcReportPq().multiply(divisor));
		dao.save(phfTradingCenterVo.getPhfTradingCenter());
		
		List<PhfElecReport> phfElecReportList =phfTradingCenterVo.getPhfElecReportList();
		for(PhfElecReport phfElecReport:phfElecReportList){
			phfElecReport.setYm(phfTradingCenter.getYm());
			phfElecReport.setCenterId(phfTradingCenter.getId());
		}
		dao.saveList(phfElecReportList);
		
		List<PhfPurcReport> phfPurcReportList=phfTradingCenterVo.getPhfPurcReportList();
		for(PhfPurcReport phfPurcReport:phfPurcReportList){
			phfPurcReport.setYm(phfTradingCenter.getYm());
			phfPurcReport.setCenterId(phfTradingCenter.getId());
		}
		dao.saveList(phfPurcReportList);
		return null;
	}

	/**
	 * 根据id查询预测模块具体信息
	 * @throws Exception 
	 */
	@Override
	public PhfTradingCenterVo getForecast(String id) throws Exception {
		PhfTradingCenterVo phfTradingCenterVo=new PhfTradingCenterVo();
		PhfTradingCenter phfTradingCenter=dao.findById(id, PhfTradingCenter.class);
		BigDecimal divisor  = new BigDecimal("100000");
		phfTradingCenter.setTotalDealPq(phfTradingCenter.getTotalDealPq().divide(divisor));
		phfTradingCenter.setElecReportPq(phfTradingCenter.getElecReportPq().divide(divisor));
		phfTradingCenter.setCompUndealNum(phfTradingCenter.getCompUndealNum().divide(divisor));
		phfTradingCenter.setConsUndealNum(phfTradingCenter.getConsUndealNum().divide(divisor));
		phfTradingCenter.setPurcReportPq(phfTradingCenter.getPurcReportPq().divide(divisor));
		phfTradingCenterVo.setPhfTradingCenter(phfTradingCenter);
		
		PhfElecReportVo phfElecReportVo=new PhfElecReportVo();
		phfElecReportVo.getPhfElecReport().setCenterId(id);
		List<PhfElecReport> phfElecReportList=phfElecReportService.getPhfElecReportListByParams(phfElecReportVo);
	    phfTradingCenterVo.setPhfElecReportList(phfElecReportList);
			
		PhfPurcReportVo phfPurcReportVo=new PhfPurcReportVo();
		phfPurcReportVo.getPhfPurcReport().setCenterId(id);
		List<PhfPurcReport> phfPurcReportList=phfPurcReportService.getPhfPurcReportListByParams(phfPurcReportVo);
		phfTradingCenterVo.setPhfPurcReportList(phfPurcReportList);
		
		return phfTradingCenterVo;
	}

	/**
	 * 更新预测模块信息
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public String updateForecastInfo(PhfTradingCenterVo phfTradingCenterVo) throws Exception {
	    PhfTradingCenter phfTradingCenter=phfTradingCenterVo.getPhfTradingCenter();
	    //查看当前年月是否已经存在
        PhfTradingCenterVo PhfTradingCenterVoOld=new PhfTradingCenterVo();
        PhfTradingCenterVoOld.getPhfTradingCenter().setYm(phfTradingCenterVo.getPhfTradingCenter().getYm());
        List<PhfTradingCenter> phfTradingCenterOld=getPhfTradingCenterListByParams(PhfTradingCenterVoOld);
		if(phfTradingCenterOld.size()>1){
		    if(!phfTradingCenterOld.get(0).getId().equals(phfTradingCenter.getId())){
		        return "1";
		    }
           
        }
		
		//将亿/千瓦时换算为兆瓦时
		BigDecimal divisor  = new BigDecimal("100000");
		phfTradingCenter.setTotalDealPq(phfTradingCenter.getTotalDealPq().multiply(divisor));
		phfTradingCenter.setElecReportPq(phfTradingCenter.getElecReportPq().multiply(divisor));
		phfTradingCenter.setCompUndealNum(phfTradingCenter.getCompUndealNum().multiply(divisor));
		phfTradingCenter.setConsUndealNum(phfTradingCenter.getConsUndealNum().multiply(divisor));
		phfTradingCenter.setPurcReportPq(phfTradingCenter.getPurcReportPq().multiply(divisor));
		dao.update(phfTradingCenterVo.getPhfTradingCenter());
		
		//根据centerId删除当前两个表中的旧数据
		dao.deleteBySql("phfElecReport.sql.deleteInfo", phfTradingCenter);
		dao.deleteBySql("phfPurcReport.sql.deleteInfo", phfTradingCenter);
		
		List<PhfElecReport> phfElecReportList =phfTradingCenterVo.getPhfElecReportList();
		for(PhfElecReport phfElecReport:phfElecReportList){
			phfElecReport.setYm(phfTradingCenter.getYm());
			phfElecReport.setCenterId(phfTradingCenter.getId());
		}
		dao.saveList(phfElecReportList);
		
		List<PhfPurcReport> phfPurcReportList=phfTradingCenterVo.getPhfPurcReportList();
		for(PhfPurcReport phfPurcReport:phfPurcReportList){
			phfPurcReport.setYm(phfTradingCenter.getYm());
			phfPurcReport.setCenterId(phfTradingCenter.getId());
		}
		dao.saveList(phfPurcReportList);
		return null;
	}
}
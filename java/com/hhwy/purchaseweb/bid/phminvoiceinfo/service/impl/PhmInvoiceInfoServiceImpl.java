package com.hhwy.purchaseweb.bid.phminvoiceinfo.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.service.PhmInvoiceInfoService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoDetail;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PhmInvoiceInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmInvoiceInfoServiceImpl implements PhmInvoiceInfoService {

	public static final Logger log = LoggerFactory.getLogger(PhmInvoiceInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmInvoiceInfo
	 * @param ID
	 * @return PhmInvoiceInfo
	 */
	@SuppressWarnings("unchecked")
	public QueryResult<PhmInvoiceInfoDetail> getPhmInvoiceInfoByPage(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception{
		QueryResult<PhmInvoiceInfoDetail> queryResult = new QueryResult<PhmInvoiceInfoDetail>();
		String bgnYm = phmInvoiceInfoVo.getBgnYm();
		String endYm = phmInvoiceInfoVo.getEndYm();
		if(bgnYm != null && bgnYm.indexOf("-") != -1){
			phmInvoiceInfoVo.setBgnYm(bgnYm.replace("-", ""));
		}
		if(endYm != null && endYm.indexOf("-") != -1){
			phmInvoiceInfoVo.setEndYm(endYm.replace("-", ""));
		}
		Parameter.isFilterData.set(true);
		List<PhmInvoiceInfoDetail> phmInvoiceInfoDetailList = (List<PhmInvoiceInfoDetail>) dao.getBySql("phmInvoiceInfo.sql.getPhmInvoiceInfoDetailByPage", phmInvoiceInfoVo);
		ConvertListUtil.convert(phmInvoiceInfoDetailList);
		Object obj = dao.getOneBySQL("phmInvoiceInfo.sql.getPhmInvoiceInfoDetailCountByPage", phmInvoiceInfoVo);
		Parameter.isFilterData.set(false);
		long total = obj == null ? 0 : Integer.valueOf(obj.toString());
		queryResult.setTotal(total);
		queryResult.setData(phmInvoiceInfoDetailList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmInvoiceInfo数量
	 * @param PhmInvoiceInfoVo
	 * @return Integer
	 */
	public Integer getPhmInvoiceInfoCountByParams(PhmInvoiceInfoVo phmInvoiceInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmInvoiceInfo.sql.getPhmInvoiceInfoCountByParams",phmInvoiceInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmInvoiceInfo列表
	 * @param PhmInvoiceInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmInvoiceInfoDetail> getPhmInvoiceInfoListByParams(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmInvoiceInfoVo == null){
			phmInvoiceInfoVo = new PhmInvoiceInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmInvoiceInfoDetail> phmInvoiceInfoDetailList = (List<PhmInvoiceInfoDetail>)dao.getBySql("phmInvoiceInfo.sql.getPhmInvoiceInfoListByParams",phmInvoiceInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmInvoiceInfoDetailList);
		return phmInvoiceInfoDetailList;
	}
	/**
	 * 根据查询条件获取单个对象PhmInvoiceInfo
	 * @param PhmInvoiceInfoVo
	 * @return PhmInvoiceInfo
	 */
	public PhmInvoiceInfoDetail getPhmInvoiceInfoOneByParams(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception{
		PhmInvoiceInfoDetail phmInvoiceInfoDetail = null;
		List<PhmInvoiceInfoDetail> phmInvoiceInfoDetailList = getPhmInvoiceInfoListByParams(phmInvoiceInfoVo);
		if(phmInvoiceInfoDetailList != null && phmInvoiceInfoDetailList.size() > 0){
			phmInvoiceInfoDetail = phmInvoiceInfoDetailList.get(0);
		}
		return phmInvoiceInfoDetail;
	}
	
	/**
	 * 根据ID获取对象PhmInvoiceInfo
	 * @param ID
	 * @return PhmInvoiceInfo
	 */
	public PhmInvoiceInfo getPhmInvoiceInfoById(String id) {
		return dao.findById(id, PhmInvoiceInfo.class);
	}	
	
	/**
	 * 添加对象PhmInvoiceInfo
	 * @param 实体对象
	 */
	@Transactional
	public PhmInvoiceInfo savePhmInvoiceInfo(PhmInvoiceInfo phmInvoiceInfo) throws Exception {
		dao.save(phmInvoiceInfo);
		return phmInvoiceInfo;
	}
	
	/**
	 * 添加对象PhmInvoiceInfo
	 * @param 实体对象
	 */
	public void savePhmInvoiceInfoList(List<PhmInvoiceInfo> phmInvoiceInfoList) {
		dao.saveList(phmInvoiceInfoList);
	}
	
	/**
	 * 更新对象PhmInvoiceInfo
	 * @param 实体对象
	 */
	public PhmInvoiceInfo updatePhmInvoiceInfo(PhmInvoiceInfo phmInvoiceInfo) {
		dao.update(phmInvoiceInfo);
		return phmInvoiceInfo;
	}
	
	/**
	 * 删除对象PhmInvoiceInfo
	 * @param id数据组
	 */
	public void deletePhmInvoiceInfo(String[] ids) {
		dao.delete(ids, PhmInvoiceInfo.class);
	}	
}
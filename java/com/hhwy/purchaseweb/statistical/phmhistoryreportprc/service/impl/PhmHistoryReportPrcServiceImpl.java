package com.hhwy.purchaseweb.statistical.phmhistoryreportprc.service.impl;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.service.PhmHistoryReportPrcService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmHistoryReportPrc;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmHistoryReportPrcService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmHistoryReportPrcServiceImpl implements PhmHistoryReportPrcService {

	public static final Logger log = LoggerFactory.getLogger(PhmHistoryReportPrcServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmHistoryReportPrc
	 * @param ID
	 * @return PhmHistoryReportPrc
	 */
	public QueryResult<PhmHistoryReportPrc> getPhmHistoryReportPrcByPage(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception{
		QueryResult<PhmHistoryReportPrc> queryResult = new QueryResult<PhmHistoryReportPrc>();
		long total = getPhmHistoryReportPrcCountByParams(phmHistoryReportPrcVo);
		List<PhmHistoryReportPrc> phmHistoryReportPrcList = getPhmHistoryReportPrcListByParams(phmHistoryReportPrcVo);
		queryResult.setTotal(total);
		queryResult.setData(phmHistoryReportPrcList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPrc数量
	 * @param PhmHistoryReportPrcVo
	 * @return Integer
	 */
	public Integer getPhmHistoryReportPrcCountByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmHistoryReportPrc.sql.getPhmHistoryReportPrcCountByParams",phmHistoryReportPrcVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPrc列表
	 * @param PhmHistoryReportPrcVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmHistoryReportPrc> getPhmHistoryReportPrcListByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmHistoryReportPrcVo == null){
			phmHistoryReportPrcVo = new PhmHistoryReportPrcVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmHistoryReportPrc> phmHistoryReportPrcList = (List<PhmHistoryReportPrc>)dao.getBySql("phmHistoryReportPrc.sql.getPhmHistoryReportPrcListByParams",phmHistoryReportPrcVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmHistoryReportPrcList);
		return phmHistoryReportPrcList;
	}
	/**
	 * 根据查询条件获取单个对象PhmHistoryReportPrc
	 * @param PhmHistoryReportPrcVo
	 * @return PhmHistoryReportPrc
	 */
	public PhmHistoryReportPrc getPhmHistoryReportPrcOneByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception{
		PhmHistoryReportPrc phmHistoryReportPrc = null;
		List<PhmHistoryReportPrc> phmHistoryReportPrcList = getPhmHistoryReportPrcListByParams(phmHistoryReportPrcVo);
		if(phmHistoryReportPrcList != null && phmHistoryReportPrcList.size() > 0){
			phmHistoryReportPrc = phmHistoryReportPrcList.get(0);
		}
		return phmHistoryReportPrc;
	}
	
	/**
	 * 根据ID获取对象PhmHistoryReportPrc
	 * @param ID
	 * @return PhmHistoryReportPrc
	 */
	public PhmHistoryReportPrc getPhmHistoryReportPrcById(String id) {
		return dao.findById(id, PhmHistoryReportPrc.class);
	}	
	
	/**
	 * 添加对象PhmHistoryReportPrc
	 * @param 实体对象
	 */
	public void savePhmHistoryReportPrc(PhmHistoryReportPrc phmHistoryReportPrc) {
		dao.save(phmHistoryReportPrc);
	}
	
	/**
	 * 添加对象PhmHistoryReportPrc
	 * @param 实体对象
	 */
	public void savePhmHistoryReportPrcList(List<PhmHistoryReportPrc> phmHistoryReportPrcList) {
		dao.saveList(phmHistoryReportPrcList);
	}
	
	/**
	 * 更新对象PhmHistoryReportPrc
	 * @param 实体对象
	 */
	public void updatePhmHistoryReportPrc(PhmHistoryReportPrc phmHistoryReportPrc) {
		dao.update(phmHistoryReportPrc);
	}
	
	/**
	 * 删除对象PhmHistoryReportPrc
	 * @param id数据组
	 */
	public void deletePhmHistoryReportPrc(String[] ids) {
		dao.delete(ids, PhmHistoryReportPrc.class);
	}	
	
	/**
     * 根据年份获取历史报价信息
     * getPriceHistoryPage(描述这个方法的作用)<br/>
     * @param map
     * @return 
     * List<PhmHistoryReportPrcDetail>
     * @exception 
     * @since  1.0.0
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PhmHistoryReportPrcDetail> getPriceHistoryPage(Map<String,Object> map){
    	Parameter.isFilterData.set(true);
        List<PhmHistoryReportPrcDetail> list = (List<PhmHistoryReportPrcDetail>) dao.getBySql("phmHistoryReportPrc.sql.getPriceHistory",map);
        Parameter.isFilterData.set(false);
        return list;
    }
}
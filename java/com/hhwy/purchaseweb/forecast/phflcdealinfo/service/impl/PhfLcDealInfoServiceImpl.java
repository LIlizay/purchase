package com.hhwy.purchaseweb.forecast.phflcdealinfo.service.impl;

import java.util.List;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.forecast.phflcdealinfo.service.PhfLcDealInfoService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhfLcDealInfo;
import com.hhwy.purchaseweb.forecast.phflcdealinfo.domain.PhfLcDealInfoVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhfLcDealInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhfLcDealInfoServiceImpl implements PhfLcDealInfoService {

	public static final Logger log = LoggerFactory.getLogger(PhfLcDealInfoServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhfLcDealInfo
	 * @param ID
	 * @return PhfLcDealInfo
	 */
	public QueryResult<PhfLcDealInfo> getPhfLcDealInfoByPage(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception{
		QueryResult<PhfLcDealInfo> queryResult = new QueryResult<PhfLcDealInfo>();
		long total = getPhfLcDealInfoCountByParams(phfLcDealInfoVo);
		List<PhfLcDealInfo> phfLcDealInfoList = getPhfLcDealInfoListByParams(phfLcDealInfoVo);
		queryResult.setTotal(total);
		queryResult.setData(phfLcDealInfoList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhfLcDealInfo数量
	 * @param PhfLcDealInfoVo
	 * @return Integer
	 */
	public Integer getPhfLcDealInfoCountByParams(PhfLcDealInfoVo phfLcDealInfoVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phfLcDealInfo.sql.getPhfLcDealInfoCountByParams",phfLcDealInfoVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhfLcDealInfo列表
	 * @param PhfLcDealInfoVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhfLcDealInfo> getPhfLcDealInfoListByParams(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phfLcDealInfoVo == null){
			phfLcDealInfoVo = new PhfLcDealInfoVo();
		}
		Parameter.isFilterData.set(true);
		List<PhfLcDealInfo> phfLcDealInfoList = (List<PhfLcDealInfo>)dao.getBySql("phfLcDealInfo.sql.getPhfLcDealInfoListByParams",phfLcDealInfoVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phfLcDealInfoList);
		return phfLcDealInfoList;
	}
	/**
	 * 根据查询条件获取单个对象PhfLcDealInfo
	 * @param PhfLcDealInfoVo
	 * @return PhfLcDealInfo
	 */
	public PhfLcDealInfo getPhfLcDealInfoOneByParams(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception{
		PhfLcDealInfo phfLcDealInfo = null;
		List<PhfLcDealInfo> phfLcDealInfoList = getPhfLcDealInfoListByParams(phfLcDealInfoVo);
		if(phfLcDealInfoList != null && phfLcDealInfoList.size() > 0){
			phfLcDealInfo = phfLcDealInfoList.get(0);
		}
		return phfLcDealInfo;
	}
	
	/**
	 * 根据ID获取对象PhfLcDealInfo
	 * @param ID
	 * @return PhfLcDealInfo
	 */
	public PhfLcDealInfo getPhfLcDealInfoById(String id) {
		return dao.findById(id, PhfLcDealInfo.class);
	}	
	
	/**
	 * 添加对象PhfLcDealInfo
	 * @param 实体对象
	 */
	public void savePhfLcDealInfo(PhfLcDealInfo phfLcDealInfo) {
	    checkYear(phfLcDealInfo.getYear());
		dao.save(phfLcDealInfo);
	}
	
	   
    /**
     * 校验年份
     * checkYear(描述这个方法的作用)<br/>
     * @param year
     * @throws Exception 
     * void
     * @exception 
     * @since  1.0.0
     */
    private void checkYear(String year){
        PhfLcDealInfoVo phfLcDealInfoVo = new PhfLcDealInfoVo();
        phfLcDealInfoVo.getPhfLcDealInfo().setYear(year);
        int total = getPhfLcDealInfoCountByParams(phfLcDealInfoVo);
        if(total>0){
            throw new RuntimeException("已经存在【"+year+"】年数据，不能保存");
        }
    }
    
	/**
	 * 添加对象PhfLcDealInfo
	 * @param 实体对象
	 */
	public void savePhfLcDealInfoList(List<PhfLcDealInfo> phfLcDealInfoList) {
		dao.saveList(phfLcDealInfoList);
	}
	
	/**
	 * 更新对象PhfLcDealInfo
	 * @param 实体对象
	 */
	public void updatePhfLcDealInfo(PhfLcDealInfo phfLcDealInfo) {
		dao.update(phfLcDealInfo);
	}
	
	/**
	 * 删除对象PhfLcDealInfo
	 * @param id数据组
	 */
	public void deletePhfLcDealInfo(String[] ids) {
		dao.delete(ids, PhfLcDealInfo.class);
	}	
	
	/**
     * 获取近3年长协电价 
     * getPhfLcDealInfoByYear(描述这个方法的作用)<br/>
     * @param year
     * @return
     * @throws Exception 
     * List<PhfLcDealInfo>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<PhfLcDealInfo> getPhfLcDealInfoByYear(String year) {
    	Parameter.isFilterData.set(true);
    	List<PhfLcDealInfo> list = (List<PhfLcDealInfo>)dao.getBySql("phfLcDealInfo.sql.getPhfLcDealInfoByYear", year);
    	Parameter.isFilterData.set(false);
        return list;
    }
}
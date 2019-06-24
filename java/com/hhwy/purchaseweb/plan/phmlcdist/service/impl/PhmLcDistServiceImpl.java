package com.hhwy.purchaseweb.plan.phmlcdist.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.plan.phmlcdist.service.PhmLcDistService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.purchase.domain.PhmLcDist;
import com.hhwy.purchaseweb.plan.phmlcdist.domain.PhmLcDistVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PhmLcDistService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmLcDistServiceImpl implements PhmLcDistService {

	public static final Logger log = LoggerFactory.getLogger(PhmLcDistServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmLcDist
	 * @param ID
	 * @return PhmLcDist
	 */
	public QueryResult<PhmLcDist> getPhmLcDistByPage(PhmLcDistVo phmLcDistVo) throws Exception{
		QueryResult<PhmLcDist> queryResult = new QueryResult<PhmLcDist>();
		long total = getPhmLcDistCountByParams(phmLcDistVo);
		List<PhmLcDist> phmLcDistList = getPhmLcDistListByParams(phmLcDistVo);
		queryResult.setTotal(total);
		queryResult.setData(phmLcDistList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmLcDist数量
	 * @param PhmLcDistVo
	 * @return Integer
	 */
	public Integer getPhmLcDistCountByParams(PhmLcDistVo phmLcDistVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmLcDist.sql.getPhmLcDistCountByParams",phmLcDistVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmLcDist列表
	 * @param PhmLcDistVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmLcDist> getPhmLcDistListByParams(PhmLcDistVo phmLcDistVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmLcDistVo == null){
			phmLcDistVo = new PhmLcDistVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmLcDist> phmLcDistList = (List<PhmLcDist>)dao.getBySql("phmLcDist.sql.getPhmLcDistListByParams",phmLcDistVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmLcDistList);
		return phmLcDistList;
	}
	/**
	 * 根据查询条件获取单个对象PhmLcDist
	 * @param PhmLcDistVo
	 * @return PhmLcDist
	 */
	public PhmLcDist getPhmLcDistOneByParams(PhmLcDistVo phmLcDistVo) throws Exception{
		PhmLcDist phmLcDist = null;
		List<PhmLcDist> phmLcDistList = getPhmLcDistListByParams(phmLcDistVo);
		if(phmLcDistList != null && phmLcDistList.size() > 0){
			phmLcDist = phmLcDistList.get(0);
		}
		return phmLcDist;
	}
	
	/**
	 * 根据ID获取对象PhmLcDist
	 * @param ID
	 * @return PhmLcDist
	 */
	public PhmLcDist getPhmLcDistById(String id) {
		return dao.findById(id, PhmLcDist.class);
	}	
	
	   /**
     * 获取长协电量分月
     * getAgreMonthPq(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * List<PhmLcDist>
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<PhmLcDist> getAgreMonthPq(String id) throws Exception{
	    PhmLcDistVo phmLcDistVo = new PhmLcDistVo();
        phmLcDistVo.setPagingFlag(false);
        phmLcDistVo.getPhmLcDist().setSchemeId(id);
        List<PhmLcDist> list = getPhmLcDistListByParams(phmLcDistVo);
        if(list.size()==0){
            List<Map<String,Object>> list2 = (List<Map<String,Object>>) dao.getBySql("phmLcDist.sql.getAgreMonthPq", id);
            if(list2.size() == 0){
                return list;
            }
            Map<String,Object> obj = list2.get(0);
            String[] list3 = new String[]{"jan","feb","mar","apr","may","jun","jul","aug","sept","oct","nov","dece"};
            String schemeId = (String) obj.get("schemeId");
            String year = (String)obj.get("year");
            list = new ArrayList<>();
            for(int i=0;i<list3.length;i++){
                PhmLcDist phmLcDist = new PhmLcDist();
                String month = i+1+"";
                if(i<9){
                    month = "0"+month;
                }
                phmLcDist.setSchemeId(schemeId);
                phmLcDist.setYm(year+month);
                phmLcDist.setAgrePq((BigDecimal)obj.get(list3[i]));
                list.add(phmLcDist);
            }
        }
        return list;
    }
	
	/**
	 * 添加对象PhmLcDist
	 * @param 实体对象
	 */
	public void savePhmLcDist(PhmLcDist phmLcDist) {
		dao.save(phmLcDist);
	}
	
	/**
	 * 添加对象PhmLcDist
	 * @param 实体对象
	 */
	public void savePhmLcDistList(List<PhmLcDist> phmLcDistList) {
		dao.saveList(phmLcDistList);
	}
	
	/**
	 * 更新对象PhmLcDist
	 * @param 实体对象
	 */
	public void updatePhmLcDist(PhmLcDist phmLcDist) {
		dao.update(phmLcDist);
	}
	
	/**
	 * 删除对象PhmLcDist
	 * @param id数据组
	 */
	public void deletePhmLcDist(String[] ids) {
		dao.delete(ids, PhmLcDist.class);
	}	
	/**
     * 获取合同追踪情况
     * getTrackInfo(描述这个方法的作用)<br/>
     * @param schemeId
     * @return 
     * List<Map<String,Object>>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getTrackInfo(String schemeId){
        return (List<Map<String, Object>>) dao.getBySql("phmLcDist.sql.getTrackInfo", schemeId);
    }
}
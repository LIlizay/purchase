package com.hhwy.purchaseweb.bid.phmeleclist.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhwy.business.system.domain.UserInfo;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.bid.phmeleclist.service.PhmElecListService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.purchase.domain.PhmElecList;
import com.hhwy.purchaseweb.bid.phmeleclist.constants.PhmElecListConstants;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.ElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * PhmElecListService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmElecListServiceImpl implements PhmElecListService {

	public static final Logger log = LoggerFactory.getLogger(PhmElecListServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象PhmElecList
	 * @param ID
	 * @return PhmElecList
	 */
	public QueryResult<PhmElecListDetail> getPhmElecListByPage(PhmElecListVo phmElecListVo) throws Exception{
		QueryResult<PhmElecListDetail> queryResult = new QueryResult<PhmElecListDetail>();
		long total = getPhmElecListCountByParams(phmElecListVo);
		List<PhmElecListDetail> phmElecListList = getPhmElecListListByParams(phmElecListVo);
		queryResult.setTotal(total);
		queryResult.setData(phmElecListList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmElecList数量
	 * @param PhmElecListVo
	 * @return Integer
	 */
	public Integer getPhmElecListCountByParams(PhmElecListVo phmElecListVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmElecList.sql.getPhmElecListCountByParams",phmElecListVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmElecList列表
	 * @param PhmElecListVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmElecListDetail> getPhmElecListListByParams(PhmElecListVo phmElecListVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmElecListVo == null){
			phmElecListVo = new PhmElecListVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmElecListDetail> list = (List<PhmElecListDetail>)dao.getBySql("phmElecList.sql.getPhmElecListListByParams",phmElecListVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(list);
		return list;
	}
	
	/**
     * 分页获取对象PhmElecListDetail
     * @param PhmElecListVo
     * @return QueryResult
     */
    public QueryResult<ElecListDetail> getElecListDetailListByPage(PhmElecListVo phmElecListVo) throws Exception{
        QueryResult<ElecListDetail> queryResult = new QueryResult<>();
        long total = getElecListDetailCountByParams(phmElecListVo);
        List<ElecListDetail> list = getElecListDetailListByParams(phmElecListVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }

    /**
     * 根据查询条件获取对象PhmElecListDetail列表
     * @param PhmElecListVo
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<ElecListDetail> getElecListDetailListByParams(PhmElecListVo phmElecListVo) throws Exception{
        //当Vo为空时,初始化Vo对象,应用分页参数
        if(phmElecListVo == null){
            phmElecListVo = new PhmElecListVo();
        }
        Parameter.isFilterData.set(true);
        List<ElecListDetail> list = (List<ElecListDetail>)dao.getBySql("phmElecList.sql.getPhmElecListDetailListByParams",phmElecListVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 根据查询条件获取对象PhmElecListDetail数量
     * @param PhmElecListVo
     * @return Integer
     */
    public Integer getElecListDetailCountByParams(PhmElecListVo phmElecListVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("phmElecList.sql.getPhmElecListDetailCountByParams",phmElecListVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    
	/**
	 * 根据查询条件获取单个对象PhmElecList
	 * @param PhmElecListVo
	 * @return PhmElecList
	 */
	public PhmElecListDetail getPhmElecListOneByParams(PhmElecListVo phmElecListVo) throws Exception{
	    PhmElecListDetail phmElecList = null;
		List<PhmElecListDetail> phmElecListList = getPhmElecListListByParams(phmElecListVo);
		if(phmElecListList != null && phmElecListList.size() > 0){
			phmElecList = phmElecListList.get(0);
		}
		return phmElecList;
	}
	
	/**
	 * 根据ID获取对象PhmElecList
	 * @param ID
	 * @return PhmElecList
	 */
	public PhmElecList getPhmElecListById(String id) {
		return dao.findById(id, PhmElecList.class);
	}	
	
	/**
	 * 添加对象PhmElecList
	 * @param 实体对象
	 */
	public void savePhmElecList(PhmElecList phmElecList) {
		dao.save(phmElecList);
	}
	
	/**
	 * 校验用户是否存在数据
	 * checkList(描述这个方法的作用)<br/>
	 * @param phmElecList 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void checkList(PhmElecList phmElecList){
	    PhmElecListVo phmElecListVo = new PhmElecListVo();
	    phmElecListVo.getPhmElecList().setConsId(phmElecList.getConsId());
	    phmElecListVo.getPhmElecList().setYm(phmElecList.getYm());
	    int count = getPhmElecListCountByParams(phmElecListVo);
	    if(count!=0){
	        throw new RuntimeException("用户在【"+phmElecList.getYm()+"】已经存在数据，不可新增");
	    }
	}
	
	/**
	 * 添加对象PhmElecList
	 * @param 实体对象
	 */
	@Transactional
	public void savePhmElecListList(List<PhmElecList> phmElecListList) {
	    delete(phmElecListList.get(0));
	    String id = (String) dao.getOneBySQL("phmElecList.sql.getConsElecId", phmElecListList.get(0));
	    //如果不存在用户历史信息，则插入
	    if(id == null){
	        PhmElecList phmElecListDetail =  phmElecListList.get(0);
	        String consId = phmElecListDetail.getConsId();
	        String ym = phmElecListDetail.getYm();
	        String year = ym.substring(0,4);
	        Map<String,Object> map = new HashMap<>();
	        map.put("consId", consId);
	        UserInfo userInfo = SystemServiceUtil.getLoginUserInfo();
	        map.put("createUser", userInfo.getId());
	        map.put("updateUser", userInfo.getId());
	        map.put("orgNo", userInfo.getOrgId());
	        Date now = new Date();
	        map.put("createTime", now);
	        map.put("updateTime", now);
	        for(int i=1;i<=12;i++){
	            String newYm = null;
	            if(i<10){
	                newYm = year+"0"+i;
	            }else{
	                newYm = year+i;
	            }
	            map.put("ym", newYm);
	            String newId = PlatformTools.getID();
	            map.put("id", newId);
	            if(newYm.equals(ym)){
	                id = newId;
	            }
	            dao.saveBySql("phmElecList.sql.insertConsElec",map);
	        }
	    }
	    boolean flag = true;
	    for(PhmElecList phmElecList:phmElecListList){
	        //千瓦时转化为兆瓦时
	        BigDecimal totalPq = phmElecList.getTotalPq().divide(new BigDecimal(1000),6,BigDecimal.ROUND_HALF_UP);
	        phmElecList.setTotalPq(totalPq);
	        Map<String,Object> map = new HashMap<>();
            map.put("id", id);
	        if(PhmElecListConstants.METERING_TYPE_SHICHA.equals(phmElecList.getMeteringType())){
	            String readType =  phmElecList.getReadType();
	            switch (readType) {
                case PhmElecListConstants.READ_TYPE_TOTAL:
                    //更新最后一次抄表数据
                    dao.updateBySql("phmElecList.sql.updataCons", phmElecList);
                    break;
                case PhmElecListConstants.READ_TYPE_PEAK:
                    //更新用户历史信息
                    map.put("pqName", "peak_pq");
                    map.put("pqValue", phmElecList.getTotalPq());
                    map.put("amtName", "peak_amt");
                    map.put("amtValue", phmElecList.getTotalPq());
                    dao.updateBySql("phmElecList.sql.updateConsElec", map);
                    break;
                case PhmElecListConstants.READ_TYPE_PLAIN:
                    flag = false;
                    //更新用户历史信息
                    map.put("pqName", "plain_pq");
                    map.put("pqValue", phmElecList.getTotalPq());
                    map.put("amtName", "plain_amt");
                    map.put("amtValue", phmElecList.getTotalPq());
                    dao.updateBySql("phmElecList.sql.updateConsElec", map);
                    break;
    	        case PhmElecListConstants.READ_TYPE_VALLEY:
    	            //更新用户历史信息
                    map.put("pqName", "valley_pq");
                    map.put("pqValue", phmElecList.getTotalPq());
                    map.put("amtName", "valley_amt");
                    map.put("amtValue", phmElecList.getTotalPq());
                    dao.updateBySql("phmElecList.sql.updateConsElec", map);
                    break;
	            }
	        }else if(PhmElecListConstants.METERING_TYPE_TOTAL.equals(phmElecList.getMeteringType())&&flag){
	            //更新用户历史信息
                map.put("pqName", "plain_pq");
                map.put("pqValue", phmElecList.getTotalPq());
                map.put("amtName", "plain_amt");
                map.put("amtValue", phmElecList.getTotalPq());
                dao.updateBySql("phmElecList.sql.updateConsElec", map);
	        }
	    }
	    dao.saveList(phmElecListList);
	}
	
	/**
	 * 更新对象PhmElecList
	 * @param 实体对象
	 */
	public void updatePhmElecList(PhmElecList phmElecList) {
		dao.update(phmElecList);
	}
	
	/**
	 * 删除对象PhmElecList
	 * @param id数据组
	 */
	public void deletePhmElecList(String[] ids) {
		dao.delete(ids, PhmElecList.class);
	}	
	
	/**
     * 根据consid和年月删除
     * delete(描述这个方法的作用)<br/>
     * @param phmElecList 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void delete(PhmElecList phmElecList){
        dao.deleteBySql("phmElecList.sql.deletePhmElecList", phmElecList);
    }
    
    /**
     * 根据用户id获取表号
     * getMeterNo(描述这个方法的作用)<br/>
     * @param consId
     * @return 
     * String
     * @exception 
     * @since  1.0.0
     */
    public String getMeterNo(String consId){
        return (String) dao.getOneBySQL("phmElecList.sql.getMeterNo", consId);
    }
}
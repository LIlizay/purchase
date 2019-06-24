package com.hhwy.purchaseweb.archives.powerplant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhcContactsInfo;
import com.hhwy.purchase.domain.PhcElecInfo;
import com.hhwy.purchase.domain.PhcGeneratorSet;
import com.hhwy.purchaseweb.archives.powerplant.domain.PhcGeneratorSetDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantVo;
import com.hhwy.purchaseweb.archives.powerplant.service.PowerPlantService;

@Component
public class PowerPlantServiceImpl implements PowerPlantService{

    @Autowired
    DAO<?> dao;
    
    public void setDao(DAO<?> dao) {
        this.dao = dao;
    }
    /**
     * 分页查询电厂信息
     * getPowerPlantByPage(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return
     * @throws Exception 
     * QueryResult<PowerPlantDetail>
     * @exception 
     * @since  1.0.0
     */
    @Override
    public QueryResult<PowerPlantDetail> getPowerPlantByPage(PowerPlantVo powerPlantVo) throws Exception{
        QueryResult<PowerPlantDetail> queryResult = new QueryResult<PowerPlantDetail>();
        long total = getPowerPlantCountByParams(powerPlantVo);
        List<PowerPlantDetail> phmAgrePrcPqList = getPowerPlantListByParams(powerPlantVo);
        queryResult.setTotal(total);
        queryResult.setData(phmAgrePrcPqList);
        return queryResult;
    }

    /**
     * 根据条件查询电厂信息
     * getPowerPlantListByParams(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return
     * @throws Exception 
     * List<PowerPlantDetail>
     * @exception 
     * @since  1.0.0
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PowerPlantDetail> getPowerPlantListByParams(PowerPlantVo powerPlantVo) throws Exception{
    	Parameter.isFilterData.set(true);
        List<PowerPlantDetail> powerPlantList = (List<PowerPlantDetail>)dao.getBySql("powerplant.sql.getPowerPlantList",powerPlantVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(powerPlantList);
        return powerPlantList;
    }
    
    /**
     * 根据条件查询电厂数量
     * getPowerPlantCountByParams(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Integer getPowerPlantCountByParams(PowerPlantVo powerPlantVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("powerplant.sql.getPowerPlantCount",powerPlantVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    @SuppressWarnings("unchecked")
    private List<PhcGeneratorSetDetail> getPhcGeneratorSetListByParams(String id) throws Exception{
        List<PhcGeneratorSetDetail> phcGeneratorSetList = (List<PhcGeneratorSetDetail>)dao.getBySql("powerplant.sql.getPhcGeneratorSetListByParams",id);
        ConvertListUtil.convert(phcGeneratorSetList);
        return phcGeneratorSetList;
    }
    
    @SuppressWarnings("unchecked")
    private PhcContactsInfo getPhcContactsInfoOneByParams(String id) throws Exception{
        PhcContactsInfo phcContactsInfo = null;
        List<PhcContactsInfo> phcContactsInfoList = (List<PhcContactsInfo>) dao.getBySql("powerplant.sql.getPhcContactsInfoListByParams",id);
        if(phcContactsInfoList != null && phcContactsInfoList.size() > 0){
            phcContactsInfo = phcContactsInfoList.get(0);
        }
        return phcContactsInfo;
    }
    
    /**
     * 校验电厂
     * checkPowerPlant(描述这个方法的作用)<br/>
     * @param powerPlantVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public void checkPowerPlant(PowerPlantVo powerPlantVo){
    	Parameter.isFilterData.set(true);
        List<String> list = (List<String>) dao.getBySql("powerplant.sql.checkName", powerPlantVo.getPhcElecInfo());
        Parameter.isFilterData.set(false);
        if(list.size()>0){
            throw new RuntimeException("电厂名称重复");
        }
    }
    
    /**
     * 根据电厂id获取电厂详细信息
     * getPowerPlant(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * PowerPlantVo
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    @Override
    public PowerPlantVo getPowerPlant(String id) throws Exception{
        PowerPlantVo powerPlantVo = new PowerPlantVo();
        PhcElecInfo phcElecInfo = dao.findById(id, PhcElecInfo.class);
        powerPlantVo.setPhcElecInfo(phcElecInfo);
        PhcContactsInfo phcContactsInfo = getPhcContactsInfoOneByParams(id);
        powerPlantVo.setPhcContactsInfo(phcContactsInfo);
        List<PhcGeneratorSetDetail> list = getPhcGeneratorSetListByParams(id);
        powerPlantVo.setDetailList(list);
        return powerPlantVo;
    }
    
    /**
     * 保存电厂
     * savePowerPlant(描述这个方法的作用)<br/>
     * @param powerPlantVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    @Override
    @Transactional
    public void savePowerPlant(PowerPlantVo powerPlantVo){
        dao.save(powerPlantVo.getPhcElecInfo());
        String id = powerPlantVo.getPhcElecInfo().getId();
        PhcContactsInfo phcContactsInfo = powerPlantVo.getPhcContactsInfo();
        phcContactsInfo.setElecId(id);
        dao.save(phcContactsInfo);
        for(PhcGeneratorSet phcGeneratorSet:powerPlantVo.getList()){
            phcGeneratorSet.setElecId(id);
            dao.save(phcGeneratorSet);
        }
        if(powerPlantVo.getDeleteList().length>0){
            dao.delete(powerPlantVo.getDeleteList(), PhcGeneratorSet.class);
        }
    }
    
    /**
     * 删除电厂信息
     * deletePowerPlant(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    @Override
    @Transactional
    public void deletePowerPlant(String id){
        dao.deleteBySql("powerplant.sql.deletePhcContactsInfo", id);
        dao.deleteBySql("powerplant.sql.deletePhcGeneratorSet", id);
        dao.delete(new String[]{id}, PhcElecInfo.class);
    }
    
    /**
     * 
     * @Title: getPhcGeneratorSetList
     * @Description:(获取机组信息)
     * @param elecId
     * @return
     * @throws Exception 
     * List<Map<String,String>>
     * <b>创 建 人：</b>zhouqi<br/>
     * <b>创建时间:</b>2017年8月7日 下午5:21:16
     * <b>修 改 人：</b>zhouqi<br/>
     * <b>修改时间:</b>2017年8月7日 下午5:21:16
     * @since  1.0.0
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getPhcGeneratorSetList(String elecId)
			throws Exception {
		return (List<Map<String, String>>) dao.getBySql("powerplant.sql.getPhcGeneratorSetList", elecId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PhcGeneratorSetDetail> getPhcElecInfoById(String id) {
		List<PhcGeneratorSetDetail> list = (List<PhcGeneratorSetDetail>) dao.getBySql("powerplant.sql.getPhcGeneratorSetById", id);
		try {
			ConvertListUtil.convert(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list ;
	}
	
	/**
	  * @Title: getGeneratorByPowerId
	  * @Description: 根据电厂id获取机组列表
	  * @param id
	  * @return List<PhcGeneratorSet>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月20日 下午4:16:33
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月20日 下午4:16:33
	  * @since  1.0.0
	 */
	@Override
	public List<PhcGeneratorSet> getGeneratorByPowerId(String id) {
		@SuppressWarnings("unchecked")
		List<PhcGeneratorSet> list = (List<PhcGeneratorSet>) dao.getBySql("powerplant.sql.getGeneratorByPowerId", id);
		return list;
	}
}

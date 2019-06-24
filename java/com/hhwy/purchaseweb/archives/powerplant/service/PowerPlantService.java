package com.hhwy.purchaseweb.archives.powerplant.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhcGeneratorSet;
import com.hhwy.purchaseweb.archives.powerplant.domain.PhcGeneratorSetDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantDetail;
import com.hhwy.purchaseweb.archives.powerplant.domain.PowerPlantVo;
public interface PowerPlantService {

    
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
    public QueryResult<PowerPlantDetail> getPowerPlantByPage(PowerPlantVo powerPlantVo) throws Exception;

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
    public List<PowerPlantDetail> getPowerPlantListByParams(PowerPlantVo powerPlantVo) throws Exception;
    
    /**
     * 根据条件查询电厂数量
     * getPowerPlantCountByParams(描述这个方法的作用)<br/>
     * @param powerPlantVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getPowerPlantCountByParams(PowerPlantVo powerPlantVo);
    
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
    public PowerPlantVo getPowerPlant(String id) throws Exception;
    
    /**
     * 校验电厂
     * checkPowerPlant(描述这个方法的作用)<br/>
     * @param powerPlantVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void checkPowerPlant(PowerPlantVo powerPlantVo);
    
    /**
     * 保存电厂
     * savePowerPlant(描述这个方法的作用)<br/>
     * @param powerPlantVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void savePowerPlant(PowerPlantVo powerPlantVo);
    
    /**
     * 删除电厂信息
     * deletePowerPlant(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void deletePowerPlant(String id);
    
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
    public List<Map<String,String>> getPhcGeneratorSetList(String elecId) throws Exception;

   /**
     * @Title: getPhcElecInfoById
     * @Description: 根据机组id获取机组信息
     * @param id
     * @return List<PhcGeneratorSetDetail>
     * @throws
     * <b>创 建 人：</b>LiXinze<br/>
     * <b>创建时间:</b>2017年11月6日 下午4:29:06
     * <b>修 改 人：</b>LiXinze<br/>
     * <b>修改时间:</b>2017年11月6日 下午4:29:06
     * @since  1.0.0
    */
	public List<PhcGeneratorSetDetail> getPhcElecInfoById(String id);

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
	public List<PhcGeneratorSet> getGeneratorByPowerId(String id);
}

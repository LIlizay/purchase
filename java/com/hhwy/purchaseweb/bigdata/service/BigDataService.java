package com.hhwy.purchaseweb.bigdata.service;

import java.util.List;
import java.util.Map;

import com.hhwy.purchase.domain.PhmPlanConsRela;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ImportVo;
import com.hhwy.purchaseweb.bigdata.domain.TpowerPriceUser;
import com.hhwy.purchaseweb.bigdata.domain.TpowerScale;
import com.hhwy.selling.domain.ScConsElectricity;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.SmPpa;

public interface BigDataService {

	/**
	 * @Title: saveOrUpdateUserInfoList
	 * @Description: 用户档案同步
	 * @param userList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月24日 下午4:58:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月24日 下午4:58:25
	 * @since  1.0.0
	 */
	public void saveOrUpdateUserInfoList(List<TpowerPriceUser> userList);
	
	/**
	 * @Title: saveOrUpdateConsInfoList
	 * @Description: 用户档案同步
	 * @param consumerList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月24日 下午5:20:24
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月24日 下午5:20:24
	 * @since  1.0.0
	 */
	public void saveOrUpdateConsInfoList(List<ScConsumerInfo> consumerList);
	
	/**
	 * @Title: saveOrUpdatePowerPqByConsIdAndYear
	 * @Description: 更新历史用电信息后，根据用户id和year年份 更新大数据中的用户实际用电量
	 * @param consId,  year
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月26日 下午2:50:29
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月26日 下午2:50:29
	 * @since  1.0.0
	 */
	public void saveOrUpdatePowerPqByConsIdAndYear(String consId, String year);
	/**
	 * @Title: updateConsInfoList
	 * @Description: 用户档案同步
	 * @param consumerList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月24日 下午5:20:24
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月24日 下午5:20:24
	 * @since  1.0.0
	 */
	public void updateConsInfoList(List<ScConsumerInfo> consumerList);
	
	
	/**
	 * @Title: saveOrUpdatePowerPqByHistoryElecs
	 * @Description: 更新历史用电信息后，根据历史用电信息列表  更新大数据中的用户实际用电量
	 * 		因为历史用电量信息列表都是以年为单位保存，所以更新前先统计出 用户id列表及对应年份，据此去数据库查询最新数据
	 * 		因为历史信息可以导入，而且是多用户多年份导入，所以为了简化逻辑是去数据中查询相关数据，而不是在传入的list中获取
	 * @param List<?> list  暂时只支持:ScConsElectricity和ImportVo 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月26日 下午2:50:29
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月26日 下午2:50:29
	 * @since  1.0.0
	 */
	public void saveOrUpdatePowerPqByHistoryElecs(List<?> elecList);
	/**
	 * @Title: saveOrUpdatePowerPqByPlanRela
	 * @Description: 根据 月度购电计划和用户关联信息（内有实际用电量字段）列表来更新大数据中的用户实际用电量
	 * @param phmPlanConsRelaList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午7:46:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午7:46:03
	 * @since  1.0.0
	 */
	public void saveOrUpdatePowerPqByPlanRela(List<PhmPlanConsRela> phmPlanConsRelaList);
	
	/**
	 * @Title: saveOrUpdatePowerPqList
	 * @Description: 新增或更新  实际用电量  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdatePowerPqList(List<TpowerScale> powerScaleList);
	
	/**
	 * @Title: saveOrUpdatePlanPqBySmPpa
	 * @Description: 根据售电合同 新增或更新  申报电量  数据列表
	 * @param smPpa 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdatePlanPqBySmPpa(SmPpa smPpa);
	
	/**
	 * @Title: saveOrUpdatePlanPqListByExa
	 * @Description: 根据 月度委托电量审核 实体类列表  新增或更新  申报电量  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdatePlanPqListByExa(List<?> phmAgrePqExamineList);
	
	/**
	 * @Title: saveOrUpdatePlanPqList
	 * @Description: 新增或更新  申报电量  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdatePlanPqList(List<TpowerScale> powerScaleList);
	
	/**
	 * @Title: saveOrUpdateProducePlanList
	 * @Description: 新增或更新  产能  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdateProducePlanList(List<TpowerScale> powerScaleList);
	
	/**
	 * @Title: saveOrUpdateProducePlanList
	 * @Description: 根据consId和year 新增或更新  产能  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdateProducePlanList(String consId, String year);

	/**
	 * @Title: deleteProducePlanByParams
	 * @Description: 根据consId和year 删除  产能  数据列表
	 * @param consId
	 * @param year 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午4:17:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午4:17:26
	 * @since  1.0.0
	 */
	public void deleteProducePlanByParams(String consId, String year);
	
	
	
	
	
	
	
	
	
	/**
	 * 大数据预测电量查询
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPowerForecast(Map<String,String> params) throws Exception; 

	/**
	 * 大数据预测电价查询
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPriceForecast(Map<String,String> params) throws Exception; 
	
	/**
	 * 月度电价预测（每次预测重新抽取数据）
	 * @param ym
	 * @param consId
	 * @return
	 */
	public List<Map<String,Object>> getPriceForecast(String ym,String consId);
	
	/**
	 * 月度电量预测（每次预测重新抽取数据）
	 * @param ym
	 * @param consId
	 * @return
	 */
	public List<Map<String,Object>> getPowerForecast(String ym,String consId);

}

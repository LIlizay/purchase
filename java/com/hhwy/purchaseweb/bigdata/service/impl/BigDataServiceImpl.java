package com.hhwy.purchaseweb.bigdata.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.datasource.DataSourceContextHolder;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.PhfConsProductionPlan;
import com.hhwy.purchase.domain.PhmAgrePqExamine;
import com.hhwy.purchase.domain.PhmPlanConsRela;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;
import com.hhwy.purchaseweb.archives.scconselectricity.domain.ImportVo;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineDetail;
import com.hhwy.purchaseweb.bigdata.domain.TpowerPriceUser;
import com.hhwy.purchaseweb.bigdata.domain.TpowerScale;
import com.hhwy.purchaseweb.bigdata.service.BigDataService;
import com.hhwy.selling.domain.ScConsElectricity;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.SmPpa;

@Component
public class BigDataServiceImpl implements BigDataService {

	public static final Logger log = LoggerFactory.getLogger(BigDataServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	
	
	/**
	 * @Title: saveConsInfoList
	 * @Description: 用户档案同步
	 * @param userList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月24日 下午4:58:25
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月24日 下午4:58:25
	 * @since  1.0.0
	 */
	public void saveOrUpdateUserInfoList(List<TpowerPriceUser> userList){
		if(userList != null && userList.size() != 0) {
			try {
				ConvertListUtil.convert(userList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<TpowerPriceUser> addList = new ArrayList<>();
			List<TpowerPriceUser> updateList = new ArrayList<>();
			for (TpowerPriceUser user : userList) {
				if(user.getId() == null || "".equals(user.getId())) {
					addList.add(user);
				}else {
					updateList.add(user);
				}
			}
			if(addList.size() > 0) {
				dao.saveBySql("bigData.sql.saveConsInfoList", userList);
			}
			if(updateList.size() > 0) {
				dao.updateBySql("bigData.sql.updateConsInfo", updateList);
			}
		}
	}
	
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
	public void saveOrUpdateConsInfoList(List<ScConsumerInfo> consumerList){
		if(consumerList == null || consumerList.size() == 0){
			return;
		}
		List<TpowerPriceUser> userList = new ArrayList<>();
		List<String> consIds = new ArrayList<>();
		for (ScConsumerInfo consInfo : consumerList) {
			if(consInfo.getId() == null || "".equals(consInfo.getId())) {
				continue;
			}
			consIds.add(consInfo.getId());
		}
		Map<String, TpowerPriceUser> consIdUserMap = new HashMap<>();
		if(consIds.size() > 0) {
			@SuppressWarnings("unchecked")
			List<TpowerPriceUser> users = (List<TpowerPriceUser>)dao.getBySql("bigData.sql.getListByConsIds", consIds);
			for (TpowerPriceUser tpowerPriceUser : users) {
				consIdUserMap.put(tpowerPriceUser.getConsNo(),tpowerPriceUser);
			}
		}
		for (ScConsumerInfo consInfo : consumerList) {
			if(consInfo.getId() == null || "".equals(consInfo.getId())) {
				continue;
			}
			TpowerPriceUser user = consIdUserMap.get(consInfo.getId());
			if(user == null) {
				user = new TpowerPriceUser();
			}
			user.setConsNo(consInfo.getId());
			user.setConsName(consInfo.getConsName());
			user.setTradeCode(consInfo.getIndustryType());	//行业分类
			user.setElecTypeCode(consInfo.getElecTypeCode());		//用电类别
			user.setRegionCode(consInfo.getProvinceCode());		//省份
			userList.add(user);
		}
		saveOrUpdateUserInfoList(userList);
	}
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
	public void updateConsInfoList(List<ScConsumerInfo> consumerList){
		if(consumerList == null || consumerList.size() == 0){
			return;
		}
		List<TpowerPriceUser> userList = new ArrayList<>();
		for (ScConsumerInfo consInfo : consumerList) {
			if(consInfo.getId() == null || "".equals(consInfo.getId())) {
				continue;
			}
			TpowerPriceUser user = new TpowerPriceUser();
			user.setConsNo(consInfo.getId());
			user.setConsName(consInfo.getConsName());
			user.setTradeCode(consInfo.getIndustryType());	//行业分类
			user.setElecTypeCode(consInfo.getElecTypeCode());		//用电类别
			user.setRegionCode(consInfo.getProvinceCode());		//省份
			userList.add(user);
		}
		try {
			ConvertListUtil.convert(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.updateBySql("bigData.sql.updateConsInfo", userList);
	}
	
	
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
	public void saveOrUpdatePowerPqByHistoryElecs(List<?> list) {
		//用户id+year的去重
		Set<String> consIdYears = new HashSet<>();
		for (Object object : list) {
			String consIdYear = "";
			if(object instanceof ScConsElectricity){
				ScConsElectricity scConsElectricity = (ScConsElectricity)object;
				if(scConsElectricity.getConsId() == null || "".equals(scConsElectricity.getConsId()) 
						|| scConsElectricity.getYm() == null || "".equals(scConsElectricity.getYm())) {
					continue;
				}
				consIdYear = scConsElectricity.getConsId() + ":" + scConsElectricity.getYm().substring(0,4);
			}else{
				ImportVo inportVo = (ImportVo)object;
				if(inportVo.getConsId() == null || "".equals(inportVo.getConsId()) 
						|| inportVo.getYm() == null || "".equals(inportVo.getYm())) {
					continue;
				}
				consIdYear = inportVo.getConsId() + ":" + inportVo.getYm().substring(0,4);
			}
			consIdYears.add(consIdYear);
		}
		for (String consIdYear : consIdYears) {
			String[] section = consIdYear.split(":");
			//更新大数据表中实际用电量数据
			saveOrUpdatePowerPqByConsIdAndYear(section[0], section[1]);
		}
	}
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
	public void saveOrUpdatePowerPqByConsIdAndYear(String consId, String year) {
		if(consId == null || "".equals(consId) || year == null || "".equals(year)) {
			return;
		}
		Map<String,String> params = new HashMap<>();
		params.put("consNo", consId);
		params.put("year", year);
		@SuppressWarnings("unchecked")
		List<TpowerScale> historyElecs = (List<TpowerScale>) dao.getBySql("bigData.sql.getHistoryElecListByParams", params);
		if(historyElecs == null || historyElecs.size() == 0) {
			return;
		}
		for (int i = 0; i < historyElecs.size(); i++) {
			TpowerScale historyElec = historyElecs.get(i);
			if(new BigDecimal(-5).compareTo( historyElec.gettDeclarePq() ) == 0) {	//如果标志位为-5，则代表五个电量都为null，则删除此月的数据
				historyElecs.remove(historyElec);
				i = i - 1;
			}
		}
		saveOrUpdatePowerPqList(historyElecs);
	}
	/**
	 * @Title: saveOrUpdatePowerPqByPlanRela
	 * @Description: 月度购电流程中，根据 月度购电计划和用户关联信息（内有实际用电量字段）列表来更新大数据中的用户实际用电量
	 * @param phmPlanConsRelaList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午7:46:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午7:46:03
	 * @since  1.0.0
	 */
	public void saveOrUpdatePowerPqByPlanRela(List<PhmPlanConsRela> phmPlanConsRelaList) {
		if(phmPlanConsRelaList == null || phmPlanConsRelaList.size() ==0) {
			return;
		}
		String planId = phmPlanConsRelaList.get(0).getPlanId();
		PhmPurchasePlanMonth monthPlan = dao.findById(planId, PhmPurchasePlanMonth.class);
		String ym = monthPlan.getYm();
		List<TpowerScale> powerScaleList = new ArrayList<>();
		for (PhmPlanConsRela phmPlanConsRela : phmPlanConsRelaList) {
			TpowerScale scale = new TpowerScale();
			scale.setConsNo(phmPlanConsRela.getConsId());
			scale.setRcvblYm(ym);
			scale.settPq(phmPlanConsRela.getActualPq());
			powerScaleList.add(scale);
		}
		saveOrUpdatePowerPqList(powerScaleList);
	}
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
	public void saveOrUpdatePowerPqList(List<TpowerScale> powerScaleList){
		if(powerScaleList == null || powerScaleList.size() == 0){
			return;
		}
		@SuppressWarnings("unchecked")
		List<TpowerScale> powerScaleEntityList = (List<TpowerScale>) dao.getBySql("bigData.sql.getTpowerScaleListByParams", powerScaleList);
		if(powerScaleEntityList != null && powerScaleEntityList.size() > 0) {
			List<TpowerScale> addList = new ArrayList<>();
			List<TpowerScale> updateList = new ArrayList<>();
			for (TpowerScale powerScale : powerScaleList) {
				boolean flag = true;
				for (TpowerScale entity : powerScaleEntityList) {
					//判断是新增还是更新
					if(entity.getConsNo().equals(powerScale.getConsNo()) && 
							entity.getRcvblYm().equals(powerScale.getRcvblYm())) {
						updateList.add(powerScale);
						flag = false;
						break;
					}
				}
				if(flag) {
					addList.add(powerScale);
				}
			}
			if(addList.size() > 0) {
				dao.saveBySql("bigData.sql.savePowerScale", addList);
			}
			if(updateList.size() > 0) {
				dao.saveBySql("bigData.sql.updatePowerScale", updateList);
			}
		}else {
			dao.saveBySql("bigData.sql.savePowerScale", powerScaleList);
		}
	}
	
	
	
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
	public void saveOrUpdatePlanPqBySmPpa(SmPpa smPpa) {
		if(smPpa == null || smPpa.getConsId() == null){
			return;
		}
		String consId = smPpa.getConsId();
		if(smPpa.getEffectiveDate() == null || smPpa.getExpiryDate() == null) {
			return;
		}
		String year = smPpa.getEffectiveDate().substring(0, 4);
		if(consId == null || "".equals(consId) || year == null || "".equals(year)) {
			return;
		}
		int startYm = Integer.parseInt(smPpa.getEffectiveDate().substring(5, 7)) - 1;
		int endYm = Integer.parseInt(smPpa.getExpiryDate().substring(5, 7)) - 1;
		List<TpowerScale> powerScaleList = new ArrayList<>();
		for (int i = startYm; i <= endYm; i++) {
			BigDecimal planCount = null;
			String ym = null;
			switch (i) {
			case 0:
				planCount = smPpa.getJan();
				ym = year + "01";
				break;
			case 1:
				planCount = smPpa.getFeb();
				ym = year + "02";
				break;
			case 2:
				planCount = smPpa.getMar();
				ym = year + "03";
				break;
			case 3:
				planCount = smPpa.getApr();
				ym = year + "04";
				break;
			case 4:
				planCount = smPpa.getMay();
				ym = year + "05";
				break;
			case 5:
				planCount = smPpa.getJun();
				ym = year + "06";
				break;
			case 6:
				planCount = smPpa.getJul();
				ym = year + "07";
				break;
			case 7:
				planCount = smPpa.getAug();
				ym = year + "08";
				break;
			case 8:
				planCount = smPpa.getSept();
				ym = year + "09";
				break;
			case 9:
				planCount = smPpa.getOct();
				ym = year + "10";
				break;
			case 10:
				planCount = smPpa.getNov();
				ym = year + "11";
				break;
			default:
				planCount = smPpa.getDece();
				ym = year + "12";
				break;
			}
			
			TpowerScale powerScale = new TpowerScale();
			powerScale.setConsNo(smPpa.getConsId());
			powerScale.setRcvblYm(ym);
			powerScale.settDeclarePq(planCount);
			powerScaleList.add(powerScale);
		}
		saveOrUpdatePlanPqList(powerScaleList);
	}
	
	/**
	 * @Title: saveOrUpdatePowerPqList
	 * @Description: 根据 月度委托电量审核 实体类列表  新增或更新  申报电量  数据列表
	 * @param powerScaleList  PhmAgrePqExamineDetail或者PhmAgrePqExamine类型
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdatePlanPqListByExa(List<?> phmAgrePqExamineList){
		List<TpowerScale> powerScaleList = new ArrayList<>();
		for (Object object : phmAgrePqExamineList) {
			TpowerScale scale = new TpowerScale();
			if(object instanceof PhmAgrePqExamine) {
				PhmAgrePqExamine mAgrePqExamine = (PhmAgrePqExamine)object;
				scale.setConsNo(mAgrePqExamine.getConsId());
				scale.setRcvblYm(mAgrePqExamine.getYm());
				scale.settDeclarePq(mAgrePqExamine.getAgrePq());		//申报电量
			}else if(object instanceof PhmAgrePqExamineDetail) {
				PhmAgrePqExamineDetail mAgrePqExamine = (PhmAgrePqExamineDetail)object;
				scale.setConsNo(mAgrePqExamine.getConsId());
				scale.setRcvblYm(mAgrePqExamine.getYm());
				scale.settDeclarePq(mAgrePqExamine.getAgrePq());		//申报电量
			}
			powerScaleList.add(scale);
		}
		saveOrUpdatePlanPqList(powerScaleList);
	}
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
	public void saveOrUpdatePlanPqList(List<TpowerScale> powerScaleList){
		if(powerScaleList == null || powerScaleList.size() == 0){
			return;
		}
		@SuppressWarnings("unchecked")
		List<TpowerScale> powerScaleEntityList = (List<TpowerScale>) dao.getBySql("bigData.sql.getTpowerScaleListByParams", powerScaleList);
		if(powerScaleEntityList != null && powerScaleEntityList.size() > 0) {
			List<TpowerScale> addList = new ArrayList<>();
			List<TpowerScale> updateList = new ArrayList<>();
			for (TpowerScale powerScale : powerScaleList) {
				boolean flag = true;
				for (TpowerScale entity : powerScaleEntityList) {
					//判断是新增还是更新
					if(entity.getConsNo().equals(powerScale.getConsNo()) && 
							entity.getRcvblYm().equals(powerScale.getRcvblYm())) {
						updateList.add(powerScale);
						flag = false;
						break;
					}
				}
				if(flag) {
					addList.add(powerScale);
				}
			}
			
			if(addList.size() > 0) {
				dao.saveBySql("bigData.sql.saveDeclare", addList);
			}
			if(updateList.size() > 0) {
				dao.saveBySql("bigData.sql.updateDeclare", updateList);
			}
		}else {
			dao.saveBySql("bigData.sql.saveDeclare", powerScaleList);
		}
	}
	
	
	
	
	/**
	 * @Title: saveOrUpdatePowerPqList
	 * @Description: 新增或更新  产能  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdateProducePlanList(List<TpowerScale> powerScaleList){
		if(powerScaleList == null || powerScaleList.size() == 0){
			return;
		}
		@SuppressWarnings("unchecked")
		List<TpowerScale> powerScaleEntityList = (List<TpowerScale>) dao.getBySql("bigData.sql.getTpowerScaleListByParams", powerScaleList);
		if(powerScaleEntityList != null && powerScaleEntityList.size() > 0) {
			List<TpowerScale> addList = new ArrayList<>();
			List<TpowerScale> updateList = new ArrayList<>();
			for (TpowerScale powerScale : powerScaleList) {
				boolean flag = true;
				for (TpowerScale entity : powerScaleEntityList) {
					//判断是新增还是更新
					if(entity.getConsNo().equals(powerScale.getConsNo()) && 
							entity.getRcvblYm().equals(powerScale.getRcvblYm())) {
						updateList.add(powerScale);
						flag = false;
						break;
					}
				}
				if(flag) {
					addList.add(powerScale);
				}
			}
			if(addList.size() > 0) {
				dao.saveBySql("bigData.sql.saveProduce", addList);
			}
			if(updateList.size() > 0) {
				dao.saveBySql("bigData.sql.updateProduce", updateList);
			}
		}else {
			dao.saveBySql("bigData.sql.saveProduce", powerScaleList);
		}
	}
	
	/**
	 * @Title: saveOrUpdatePowerPqList
	 * @Description: 根据consId和year 新增或更新  产能  数据列表
	 * @param powerScaleList 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月25日 下午1:35:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月25日 下午1:35:00
	 * @since  1.0.0
	 */
	public void saveOrUpdateProducePlanList(String consId, String year){
		if(consId == null || "".equals(consId) || year == null || "".equals(year)) {
			return;
		}
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("year", year);
		PhfConsProductionPlan consProductionPlan = (PhfConsProductionPlan)dao.getOneBySQL("phfConsProductionPlan.sql.getConsProductionByConsIdAndYear",params);
		//如果数据库中没有对应数据库，则删除大数据中对应数据
		if(consProductionPlan == null || consProductionPlan.getConsId() == null){
			deleteProducePlanByParams(consId,year);
		}
		List<TpowerScale> powerScaleList = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			BigDecimal planCount = null;
			String ym = null;
			switch (i) {
			case 0:
				planCount = consProductionPlan.getJan();
				ym = consProductionPlan.getYear() + "01";
				break;
			case 1:
				planCount = consProductionPlan.getFeb();
				ym = consProductionPlan.getYear() + "02";
				break;
			case 2:
				planCount = consProductionPlan.getMar();
				ym = consProductionPlan.getYear() + "03";
				break;
			case 3:
				planCount = consProductionPlan.getApr();
				ym = consProductionPlan.getYear() + "04";
				break;
			case 4:
				planCount = consProductionPlan.getMay();
				ym = consProductionPlan.getYear() + "05";
				break;
			case 5:
				planCount = consProductionPlan.getJun();
				ym = consProductionPlan.getYear() + "06";
				break;
			case 6:
				planCount = consProductionPlan.getJul();
				ym = consProductionPlan.getYear() + "07";
				break;
			case 7:
				planCount = consProductionPlan.getAug();
				ym = consProductionPlan.getYear() + "08";
				break;
			case 8:
				planCount = consProductionPlan.getSept();
				ym = consProductionPlan.getYear() + "09";
				break;
			case 9:
				planCount = consProductionPlan.getOct();
				ym = consProductionPlan.getYear() + "10";
				break;
			case 10:
				planCount = consProductionPlan.getNov();
				ym = consProductionPlan.getYear() + "11";
				break;
			default:
				planCount = consProductionPlan.getDece();
				ym = consProductionPlan.getYear() + "12";
				break;
			}
			if(planCount == null) {
				continue;
			}
			TpowerScale powerScale = new TpowerScale();
			powerScale.setConsNo(consProductionPlan.getConsId());
			powerScale.setRcvblYm(ym);
			powerScale.setConsProducePlan(planCount);
			powerScaleList.add(powerScale);
		}
		saveOrUpdateProducePlanList(powerScaleList);
	}
	
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
	public void deleteProducePlanByParams(String consId, String year) {
		if(consId == null || "".equals(consId) || year == null || "".equals(year)) {
			return;
		}
		Map<String, String> params = new HashMap<>();
		params.put("consId", consId);
		params.put("year", year);
		dao.updateBySql("bigData.sql.deleteProduceData",params);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 大数据预测电量查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getPowerForecast(Map<String,String> params) throws Exception {
		DataSourceContextHolder.setDataSourceType("bigData");
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPowerResult", params);
		Parameter.isFilterData.set(false);
		DataSourceContextHolder.clearDataSourceType();
		return list;
	}
	
	/**
	 * 大数据预测电价查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getPriceForecast(Map<String,String> params) throws Exception {
		DataSourceContextHolder.setDataSourceType("bigData");
		Parameter.isFilterData.set(true);
		List<Map<String,Object>> list = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPrcResult", params);
		Parameter.isFilterData.set(false);
		DataSourceContextHolder.clearDataSourceType();
		return list; 
	}
	
	/**
	 * 月度电量预测
	 * @param ym
	 * @param consId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<Map<String,Object>> getPowerForecast(String ym,String consId){
		List<Map<String,Object>> list = null;
		try {
			String lastYm = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar calendar = Calendar.getInstance();
			if(StringUtils.isEmpty(ym)){		
				lastYm = sdf.format(calendar.getTime());
				calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
				ym = sdf.format(calendar.getTime());
			}else{
				int year = Integer.parseInt(ym.substring(0,4));
				int month = Integer.parseInt(ym.substring(4))-2;
				calendar.set(Calendar.YEAR,year);
				calendar.set(Calendar.MONTH,month);
				lastYm = sdf.format(calendar.getTime());
			}
			Map<String,String> params = new HashMap<String, String>();
			params.put("ym", ym);
			params.put("lastYm", lastYm);
			params.put("consId", consId);
			//用户信息
			Parameter.isFilterData.set(true);
			List<Map<String,Object>> consList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getConsInfo", params);
			//历时用电量
			List<Map<String,Object>> historyList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPowerScale", params);
			//交易中心交易数据
			List<Map<String,Object>> centerList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getAllDealInfo", params);
			//售电公司交易数据
			List<Map<String,Object>> compList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getCompanyDealInfo", params);
			//发电企业报价
			List<Map<String,Object>> elecPrcList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getSupportValueSection", params);
			//售电公司报价
			List<Map<String,Object>> compPrcList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getValueSection", params);
			//用户范围
			List<Map<String,Object>> planConsList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPlanCons", params);
			Parameter.isFilterData.set(false);
			//同步用户数据
			saveConsInfo(consList,params);
			//同步历时用电量
			saveHistoryPower(historyList,params);
			//同步交易中心交易数据
			saveAllDealInfo(centerList,params);
			//同步售电公司交易数据
			saveCompanyDealInfo(compList,params);
			//同步发电企业报价
			saveSupportPrcSection(elecPrcList,params);
			//同步售电公司报价
			savePrcSection(compPrcList,params);
			//预测电量
			savePowerForecast(planConsList,params);
			int i=0;
			while((list==null||list.isEmpty())&&i<5){
				list = getPowerForecast(params);
				if(list!=null&&!list.isEmpty()){
					break;
				}else{
					Thread.currentThread().sleep(1000);
				}
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 月度电价预测
	 * @param ym
	 * @param consId
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public List<Map<String,Object>> getPriceForecast(String ym,String consId){
		List<Map<String,Object>> list = null;
		try {
			String lastYm = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar calendar = Calendar.getInstance();
			if(StringUtils.isEmpty(ym)){		
				lastYm = sdf.format(calendar.getTime());
				calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
				ym = sdf.format(calendar.getTime());
			}else{
				int year = Integer.parseInt(ym.substring(0,4));
				int month = Integer.parseInt(ym.substring(4))-2;
				calendar.set(Calendar.YEAR,year);
				calendar.set(Calendar.MONTH,month);
				lastYm = sdf.format(calendar.getTime());
			}
			Map<String,String> params = new HashMap<String, String>();
			params.put("ym", ym);
			params.put("lastYm", lastYm);
			params.put("consId", consId);
			//用户信息
			Parameter.isFilterData.set(true);
			List<Map<String,Object>> consList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getConsInfo", params);
			//历时用电量
			List<Map<String,Object>> historyList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPowerScale", params);
			//交易中心交易数据
			List<Map<String,Object>> centerList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getAllDealInfo", params);
			//售电公司交易数据
			List<Map<String,Object>> compList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getCompanyDealInfo", params);
			//发电企业报价
			List<Map<String,Object>> elecPrcList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getSupportValueSection", params);
			//售电公司报价
			List<Map<String,Object>> compPrcList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getValueSection", params);
			//用户范围
			List<Map<String,Object>> planConsList = (List<Map<String,Object>>)dao.getBySql("bigData.sql.getPlanCons", params);
			Parameter.isFilterData.set(false);
			//同步用户数据
			saveConsInfo(consList,params);
			//同步历时用电量
			saveHistoryPower(historyList,params);
			//同步交易中心交易数据
			saveAllDealInfo(centerList,params);
			//同步售电公司交易数据
			saveCompanyDealInfo(compList,params);
			//同步发电企业报价
			saveSupportPrcSection(elecPrcList,params);
			//同步售电公司报价
			savePrcSection(compPrcList,params);
			//预测电量
			savePriceForecast(planConsList,params);
			int i=0;
			while((list==null||list.isEmpty())&&i<5){
				list = getPriceForecast(params);
				if(list!=null&&!list.isEmpty()){
					break;
				}else{
					Thread.currentThread().sleep(1000);
				}
				i++;
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 用户信息（t_power_price_user）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveConsInfo(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deleteConsInfo", params);
				dao.executeSql("bigData.sql.saveConsInfo", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 历史电量（t_power_scale）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveHistoryPower(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deletePowerScale", params);
				dao.executeSql("bigData.sql.savePowerScale", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * 交易中心交易信息（t_price_all_deal_info）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveAllDealInfo(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deleteCenterDealInfo", params);
				dao.executeSql("bigData.sql.saveAllDealInfo", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 售电公司交易信息（t_price_company_deal_info）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveCompanyDealInfo(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deleteCompDealInfo", params);
				dao.executeSql("bigData.sql.saveCompanyDealInfo", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
 
	/**
	 * 发电侧交易电价信息（t_price_support_value_section）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveSupportPrcSection(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				for(Map<String,Object> map:list){
					String propName = (String)map.get("propName");
					if(propName.indexOf("以上")>0){
						map.put("minValue", propName.replace("以上", ""));
					}else if(propName.indexOf("以下")>0){
						map.put("maxValue", propName.replace("以下", ""));
					}else if(propName.indexOf("-")>0){
						String[] str = propName.split("-");
						BigDecimal start = new BigDecimal(str[0]);
						BigDecimal end = new BigDecimal(str[1]);
						if(start.compareTo(end)>0){
							map.put("minValue", end);
							map.put("maxValue", start);
						}else{
							map.put("minValue", start);
							map.put("maxValue", end);
						}
					}
				}
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deleteElecPrc", params);
				dao.executeSql("bigData.sql.saveSupportValueSection", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * 用电侧交易电价信息（t_price_value_section）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void savePrcSection(List<Map<String,Object>> list,Map<String,String> params){
		try {
			if(list!=null&&!list.isEmpty()){
				for(Map<String,Object> map:list){
					String propName = (String)map.get("propName");
					if(propName.indexOf("以上")>0){
						map.put("minValue", propName.replace("以上", ""));
					}else if(propName.indexOf("以下")>0){
						map.put("maxValue", propName.replace("以下", ""));
					}else if(propName.indexOf("-")>0){
						String[] str = propName.split("-");
						BigDecimal start = new BigDecimal(str[0]);
						BigDecimal end = new BigDecimal(str[1]);
						if(start.compareTo(end)>0){
							map.put("minValue", end);
							map.put("maxValue", start);
						}else{
							map.put("minValue", start);
							map.put("maxValue", end);
						}
					}
				}
				DataSourceContextHolder.setDataSourceType("bigData");
				dao.executeSql("bigData.sql.deleteCompPrc", params);
				dao.executeSql("bigData.sql.saveValueSection", list);
				DataSourceContextHolder.clearDataSourceType();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 大数据预测电量新增
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void savePowerForecast(List<Map<String,Object>> list,Map<String,String> params){
		try {
			DataSourceContextHolder.setDataSourceType("bigData");
			dao.executeSql("bigData.sql.deletePqResult", params);
			dao.executeSql("bigData.sql.savePowerResult", list);
			DataSourceContextHolder.clearDataSourceType();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 大数据预测电价新增 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void savePriceForecast(List<Map<String,Object>> list,Map<String,String> params){
		try {
			DataSourceContextHolder.setDataSourceType("bigData");
			dao.executeSql("bigData.sql.deletePrcResult", params);
			dao.executeSql("bigData.sql.savePrcResult", list);
			DataSourceContextHolder.clearDataSourceType();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 工况信息维护（t_power_price_holiday、t_power_price_humidity、t_power_price_temperature）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveWorkCondition(){
		
	}
	
	/**
	 * 发电企业信息（t_power_price_support_company）
	 */
	public void saveElecInfo(){
		
	}
}

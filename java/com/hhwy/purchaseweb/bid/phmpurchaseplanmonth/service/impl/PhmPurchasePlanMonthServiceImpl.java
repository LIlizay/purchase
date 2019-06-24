package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhwy.business.market.pcode.domain.PCodeVo;
import com.hhwy.business.market.util.CodeUtil;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.PlatformTools;
import com.hhwy.purchase.domain.PhmAgrePqExamine;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.domain.PhmAgrePqExamineVo;
import com.hhwy.purchaseweb.bid.phmagrepqexamine.service.PhmAgrePqExamineService;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanConsRelaDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthDetail;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.service.PhmPurchasePlanMonthService;
import com.hhwy.purchaseweb.contants.BusinessContants;
import com.hhwy.purchaseweb.sms.service.SwSmsService;

/**
 * PhmPurchasePlanMonthService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class PhmPurchasePlanMonthServiceImpl implements PhmPurchasePlanMonthService {

	public static final Logger log = LoggerFactory.getLogger(PhmPurchasePlanMonthServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * phmAgrePqExamineService	委托电量审核表service
	 */
	@Autowired
	private PhmAgrePqExamineService phmAgrePqExamineService;
	
	/**
	 * 短信记录接口swSmsService
	 */
	@Autowired
	SwSmsService swSmsService;
	
	/**
	 * 分页获取对象PhmPurchasePlanMonth
	 * @param ID
	 * @return PhmPurchasePlanMonth
	 */
	public QueryResult<PhmPurchasePlanMonthDetail> getPhmPurchasePlanMonthByPage(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception{
		QueryResult<PhmPurchasePlanMonthDetail> queryResult = new QueryResult<PhmPurchasePlanMonthDetail>();
		long total = getPhmPurchasePlanMonthCountByParams(phmPurchasePlanMonthVo);
		List<PhmPurchasePlanMonthDetail> phmPurchasePlanMonthList = getPhmPurchasePlanMonthListByParams(phmPurchasePlanMonthVo);
		queryResult.setTotal(total);
		queryResult.setData(phmPurchasePlanMonthList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象PhmPurchasePlanMonth数量
	 * @param PhmPurchasePlanMonthVo
	 * @return Integer
	 */
	public Integer getPhmPurchasePlanMonthCountByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPurchasePlanMonth.sql.getPhmPurchasePlanMonthCountByParams",phmPurchasePlanMonthVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象PhmPurchasePlanMonth列表
	 * @param PhmPurchasePlanMonthVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PhmPurchasePlanMonthDetail> getPhmPurchasePlanMonthListByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPurchasePlanMonthVo == null){
			phmPurchasePlanMonthVo = new PhmPurchasePlanMonthVo();
		}
		Parameter.isFilterData.set(true);
		List<PhmPurchasePlanMonthDetail> phmPurchasePlanMonthList = (List<PhmPurchasePlanMonthDetail>)dao.getBySql("phmPurchasePlanMonth.sql.getPhmPurchasePlanMonthListByParams",phmPurchasePlanMonthVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(phmPurchasePlanMonthList);
		if("false".equals(phmPurchasePlanMonthVo.getFlag())){
			for(int i = phmPurchasePlanMonthList.size(); i > 0; i--){
				String status = phmPurchasePlanMonthList.get(i-1).getPlanStatus();
				if("03".equals(status) || "02".equals(status) || "01".equals(status) || "00".equals(status)){
					phmPurchasePlanMonthList.remove(i-1);
				}
			}
		}else{
			for(PhmPurchasePlanMonthDetail detail : phmPurchasePlanMonthList){
				//为了兼容以前的否点流程（以前的购电流程是有05、06、07状态）
				if("05".equals(detail.getPlanStatus()) || "06".equals(detail.getPlanStatus()) || "07".equals(detail.getPlanStatus())){
					detail.setPlanStatusName("已成交");
				}
			}
		}
		return phmPurchasePlanMonthList;
	}
	
	/**
	 * @Title: getPhmPurchasePlanMonthListByYm
	 * @Description: 根据年月获取（本月成交电量、本月代理总电量、长协电量、竞价电量）等数据
	 * @param ym
	 * @return
	 * @throws Exception 
	 * PhmPurchasePlanMonthDetail
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年10月26日 下午6:41:52
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年10月26日 下午6:41:52
	 * @since  1.0.0
	 */
	public PhmPurchasePlanMonthDetail getPhmPurchasePlanMonthListByYm(String ym) throws Exception{
		if(ym == null || "".equals(ym)){
			return null;
		}
		Parameter.isFilterData.set(true);
		PhmPurchasePlanMonthDetail phmPurchasePlanMonth = (PhmPurchasePlanMonthDetail) dao.getOneBySQL("phmPurchasePlanMonth.sql.getPhmPurchasePlanMonthListByYm",ym);
		Parameter.isFilterData.set(false);
		if(phmPurchasePlanMonth == null) {
			return new PhmPurchasePlanMonthDetail();
		}
		return phmPurchasePlanMonth;
	}
	/**
	 * 根据查询条件获取单个对象PhmPurchasePlanMonth
	 * @param PhmPurchasePlanMonthVo
	 * @return PhmPurchasePlanMonth
	 */
	public PhmPurchasePlanMonthDetail getPhmPurchasePlanMonthOneByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception{
		PhmPurchasePlanMonthDetail phmPurchasePlanMonthDetail = null;
		List<PhmPurchasePlanMonthDetail> phmPurchasePlanMonthList = getPhmPurchasePlanMonthListByParams(phmPurchasePlanMonthVo);
		if(phmPurchasePlanMonthList != null && phmPurchasePlanMonthList.size() > 0){
			phmPurchasePlanMonthDetail = phmPurchasePlanMonthList.get(0);
		}
		return phmPurchasePlanMonthDetail;
	}
	
	/**
	 * 根据ID获取对象PhmPurchasePlanMonth
	 * @param ID
	 * @return PhmPurchasePlanMonth
	 */
	public PhmPurchasePlanMonth getPhmPurchasePlanMonthById(String id) {
		return dao.findById(id, PhmPurchasePlanMonth.class);
	}	
	
	/**
	 * 添加对象PhmPurchasePlanMonth
	 * @param 实体对象
	 */
	@Transactional
	public void savePhmPurchasePlanMonth(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception{
		//交易信息保存
		PhmPurchasePlanMonth phmPurchasePlanMonth = phmPurchasePlanMonthVo.getPhmPurchasePlanMonth();
		phmPurchasePlanMonth.setPlanStatus(BusinessContants.SELL_MONTHBIDSTATUS00);
		dao.save(phmPurchasePlanMonth);
		
		//回退后的数据清理
		if(phmPurchasePlanMonthVo.getDelIds() != null && phmPurchasePlanMonthVo.getDelIds().length > 0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("planId", phmPurchasePlanMonth.getId());
			map.put("ids", phmPurchasePlanMonthVo.getDelIds());
			dao.deleteBySql("phmAgrePqExamine.sql.deletePlanByPlanIdAndConsIds", map);
		}
		PhmAgrePqExamineVo phmAgrePqExamineVo = new PhmAgrePqExamineVo();
		phmAgrePqExamineVo.setPlanId(phmPurchasePlanMonth.getId());
		phmAgrePqExamineVo.setPagingFlag(false);
		List<PhmPurchasePlanConsRelaDetail> detailList = phmAgrePqExamineService.getPlanConsListPageByPlanId(phmAgrePqExamineVo);
		Map<String, String> consIdToIdMap = new HashMap<>();
		if(detailList.size() != 0) {
			for (PhmPurchasePlanConsRelaDetail detail : detailList) {
				consIdToIdMap.put(detail.getConsId(), detail.getId());
			}
		}
		//用户信息保存
		List<PhmAgrePqExamine> examineList = phmPurchasePlanMonthVo.getExamineList();
		String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		for(PhmAgrePqExamine examine : examineList){
			examine.setPlanId(phmPurchasePlanMonth.getId());
			examine.setYm(phmPurchasePlanMonth.getYm());
			examine.setSubitemFlag("0");
			examine.setOrgNo(orgNo);
			if(consIdToIdMap.containsKey(examine.getConsId())) {
				examine.setId(consIdToIdMap.get(examine.getConsId()));
			}else if(examine.getId() == null || "".equals(examine.getId())) {
				examine.setId(PlatformTools.getID());
			}
		}
		dao.saveList(examineList);
	}
	
	/**
	 * 添加对象PhmPurchasePlanMonth
	 * @param 实体对象
	 */
	public void savePhmPurchasePlanMonthList(List<PhmPurchasePlanMonth> phmPurchasePlanMonthList) {
		dao.saveList(phmPurchasePlanMonthList);
	}
	
	/**
	 * 更新对象PhmPurchasePlanMonth
	 * @param 实体对象
	 */
	public void updatePhmPurchasePlanMonth(PhmPurchasePlanMonth phmPurchasePlanMonth) {
		dao.update(phmPurchasePlanMonth);
	}
	
	/**
	  * @Title: deletePhmPurchasePlanMonth
	  * @Description: 删除对象PhmPurchasePlanMonth
	  * @param phmPurchasePlanMonthVo
	  * @return boolean
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年6月27日 下午3:37:35
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年6月27日 下午3:37:35
	  * @since  1.0.0
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean deletePhmPurchasePlanMonth(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		if("false".equals(phmPurchasePlanMonthVo.getFlag())){//不忽略结算验证
			String ym = phmPurchasePlanMonthVo.getPhmPurchasePlanMonth().getYm();
			List<SmSettlementMonth> list = (List<SmSettlementMonth>) dao.getBySql("smSettlementMonth.sql.getSmSettlementMonthByYm", ym);
			if(list != null && list.size()>0){
				return false;
			}
		}
		String[] ids = {phmPurchasePlanMonthVo.getPhmPurchasePlanMonth().getId()};
		dao.delete(ids, PhmPurchasePlanMonth.class);
		String planId = phmPurchasePlanMonthVo.getPhmPurchasePlanMonth().getId();
		dao.deleteBySql("phmAgrePqExamine.sql.deletePlanByPlanId", planId);
		return true;
	}
	
	/**
	 * 根据计划id更新计划状态 BusinessContants.SELL_MONTHBIDSTATUS
	 */
	public void updatePlanStatusByPlanId(String planId,String planStatus) throws Exception {
		if(!StringUtils.isEmpty(planId)&&!StringUtils.isEmpty(planStatus)){
			Map<String,String> map = new HashMap<String, String>();
			map.put("planId", planId);
			map.put("planStatus", planStatus);
			dao.updateBySql("phmPurchasePlanMonth.sql.updatePlanStatus", map);
		}
	}
	
	/**
     * 提交
     * submit(描述这个方法的作用)<br/>
     * @param planId 
     * void
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
	@Transactional
    public void submit(String planId) throws Exception{
	    PhmPurchasePlanMonth phmPurchasePlanMonth = dao.findById(planId, PhmPurchasePlanMonth.class);
	    if(!BusinessContants.SELL_MONTHBIDSTATUS00.equals(phmPurchasePlanMonth.getPlanStatus())){
	        String name = getBusiTypeCodeNameByValue(phmPurchasePlanMonth.getPlanStatus());
            throw new RuntimeException("当前计划状态为【"+name+"】，不可提交！");
	    }
        updatePlanStatusByPlanId(planId, BusinessContants.SELL_MONTHBIDSTATUS01);
    }
	
	/**
     * 根据码值value获取busiTypeCode的name
     * getBusiTypeCodeNameByValue(描述这个方法的作用)<br/>
     * @param value
     * @return
     * @throws Exception 
     * String
     * @exception 
     * @since  1.0.0
     */
    private String getBusiTypeCodeNameByValue(String value) throws Exception{
        PCodeVo pCodeVo = new PCodeVo();
        pCodeVo.getpCode().setCodeType("sell_monthBidStatus");
        pCodeVo.setCacheType(ConstantsStatus.CACHE_TYPE_PCODE);
        pCodeVo.setDomain(ConstantsStatus.PCODE_DOMAIN_SELLING);
        pCodeVo.setValueIn(value);
        return CodeUtil.queryCodeListByParam(pCodeVo).get(0).getName();
    }

    /**
	  * @Title: recall
	  * @Description: 月度购电相关流程回退
	  * @param id
	  * @param planStatus
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年12月6日 下午1:50:50
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年12月6日 下午1:50:50
     * @throws Exception 
	  * @since  1.0.0
	 */
	@Override
	@Transactional
	public void recall(String id, String planStatus) throws Exception {
		String status = null;
//		if(planStatus.equals(BusinessContants.SELL_MONTHBIDSTATUS05)){
//			//已录入状态回退
//			status = BusinessContants.SELL_MONTHBIDSTATUS04;
//		}
		if(planStatus.equals(BusinessContants.SELL_MONTHBIDSTATUS04)){
			//已成交状态回退
			status = BusinessContants.SELL_MONTHBIDSTATUS03;
		}
		if(planStatus.equals(BusinessContants.SELL_MONTHBIDSTATUS03)){
			//竞价成交状态回退
			status = BusinessContants.SELL_MONTHBIDSTATUS02;
		}
		if(planStatus.equals(BusinessContants.SELL_MONTHBIDSTATUS02)){
			//已审核状态回退
			status = BusinessContants.SELL_MONTHBIDSTATUS01;
			deleteConsPowerView(id);
		}
		if(planStatus.equals(BusinessContants.SELL_MONTHBIDSTATUS01)){
			//已制定状态回退
			status = BusinessContants.SELL_MONTHBIDSTATUS00;
		}
		updatePlanStatusByPlanId(id, status);
	}
	
	public void deleteConsPowerView(String planId){
		dao.deleteBySql("phmPurchasePlanMonth.sql.deleteConsPowerView", planId);
	}

	/**
	 * 根据年月判断购电计划是否存在
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean getPhmPurchasePlanMonthByYm(String ym) {
		Parameter.isFilterData.set(true);
		List<PhmPurchasePlanMonth> list = (List<PhmPurchasePlanMonth>)dao.getBySql("phmPurchasePlanMonth.sql.getPhmPurchasePlanMonthByYm",ym);
		Parameter.isFilterData.set(false);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	  * @Title: getPqCountByPlanId
	  * @Description: 根据计划id获取本次委托电量、已成交电量、已申报电量
	  * @param id
	  * @return Map<String,Object>
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2018年5月26日 下午4:36:01
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2018年5月26日 下午4:36:01
	  * @since  1.0.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPqCountByPlanId(String id) {
		Map<String,Object> map = (Map<String, Object>) dao.getOneBySQL("phmPurchasePlanMonth.sql.getPqCountByPlanId", id);
		return map;
	}
}
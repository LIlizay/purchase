package com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.framework.exception.BusinessException;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmConsDeviationAmt;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.purchaseweb.settlement.base.smcompanycostdetail.service.SmCompanyCostDetailService;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.service.SmCompanyProfitService;
import com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.service.SmConsDeviationAmtService;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service.SmConsumerProfitService;
import com.hhwy.purchaseweb.settlement.base.smpurchasescheme.service.SmPurchaseSchemeService;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service.SmPurchaseSchemeDetailService;
import com.hhwy.purchaseweb.settlement.base.smsettlementmonth.service.SmSettlementMonthService;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain.SmPurchaseSchemeVoJs;
import com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.service.SmPurchaseSchemeServiceJs;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeServiceImplJs<br/>
 * <b>类 描 述：</b><br/>  江苏的月度购电结算的方案service实现类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月30日 下午3:48:51<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class SmPurchaseSchemeServiceImplJs implements SmPurchaseSchemeServiceJs {

	public static final Logger log = LoggerFactory.getLogger(SmPurchaseSchemeServiceImplJs.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * smPurchaseSchemeDetailService		普通省份的   结算方案详情的service，
	 * 				用于保存、更新或删除方案，方案详情列表，售电公司结算信息，用户结算信息列表（零售市场售电收入明细）
	 */
	@Autowired
	private SmPurchaseSchemeDetailService smPurchaseSchemeDetailService;
	
	/**
	 * smCompanyCostDetailService 江苏的“批发市场购电支出明细”service
	 */
	@Autowired
	private SmCompanyCostDetailService smCompanyCostDetailService;
	
	/**
	 * 月购电情况及本企业总收益 smCompanyProfitService
	 */
	@Autowired
	private SmCompanyProfitService smCompanyProfitService;
	
	/**
	 * smPurchaseSchemeService	月度结算方案service
	 */
	@Autowired
	private SmPurchaseSchemeService smPurchaseSchemeService;
	
	/**
	 * smConsDeviationAmtService 用户结算偏差费用信息 service
	 */
	@Autowired
	private SmConsDeviationAmtService smConsDeviationAmtService;
	
	/**
	 * smSettlementMonthService 结算主表service
	 */
	@Autowired
	private SmSettlementMonthService smSettlementMonthService;
	
	/**
	 * smConsumerProfitService 月度结算中的用户收益信息
	 */
	@Autowired
	private SmConsumerProfitService smConsumerProfitService;
	
	/**
	 * @Title: getSmPurchaseSchemeVoBySchemeId
	 * @Description: 江苏的根据方案id获取结算结果（包括 方案详情实体类列表、用户偏差费用计算相关信息、售电公司结算信息、用户结算信息（零售市场售电收入明细）、批发市场购电支出明细）
	 * 				因为前台弹框的参数是“方案实体对象”，所以在这里就不取“方案实体对象”了
	 * @param schemeId
	 * @return 
	 * SmPurchaseSchemeVoJs
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月31日 下午8:36:17
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月31日 下午8:36:17
	 * @since  1.0.0
	 */
	public SmPurchaseSchemeVoJs getSmPurchaseSchemeVoBySchemeId(String schemeId) throws Exception{
		if(schemeId == null || "".equals(schemeId)) {
			throw  new BusinessException("参数错误，获取失败！");
		}
		SmPurchaseSchemeVoJs schemeVoJs = new SmPurchaseSchemeVoJs();
		//通过接口获取此方案下的所有方案明细
		List<SmPurchaseSchemeDetailDetail> list = smPurchaseSchemeDetailService.getSchemeDetailBySchemeId(schemeId);
		schemeVoJs.setSmPurchaseSchemeDetailDetailList(list);
		
		//获取用户偏差费用计算相关信息列表		datagrid中获取
//		List<SmConsDeviationAmtDetail> amtDetailList = smConsDeviationAmtService.getConsDeviAmtBySchemeId(schemeId);
//		schemeVoJs.setSmConsDeviationAmtDetailList(amtDetailList);
		
		//调用售电公司收益service接口中的 获取售电公司收益和用户收益（即江苏的‘零售市场售电收入明细’）列表 的方法
		SmCompanyProfit companyProfit = smCompanyProfitService.getSmCompanyProfitBySchemeId(schemeId);
		List<SmConsumerProfitDetail> consumerProfitDetails =  smConsumerProfitService.getSmConsumerProfitListBySchemeId(schemeId);
		schemeVoJs.setSmCompanyProfit(companyProfit);
		schemeVoJs.setSmConsumerProfitDetailList(consumerProfitDetails);
		
		//获取批发市场购电支出明细列表
		List<SmCompanyCostDetail> costDetails = smCompanyCostDetailService.getCostDetailListBySchemeId(schemeId);
		schemeVoJs.setSmCompanyCostDetailList(costDetails);
		return schemeVoJs;
	}
	
	/**
	 * @Title: saveOrUpdateSmPurchaseSchemeVoJs
	 * @Description: 江苏的新增或修改计划下的方案,可能还包含结算主表的新增。
	 * 			包含：方案，方案详情列表，售电公司结算信息，用户结算信息列表（零售市场售电收入明细）
	 * 					，批发市场购电支出明细，月度结算用户偏差惩罚费用信息 列表
	 * @param smPurchaseSchemeVoJs 
	 * @throws Exception 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:23:20
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月30日 下午4:23:20
	 * @since  1.0.0
	 */
	@Override
	@Transactional
	public void saveOrUpdateSmPurchaseSchemeVoJs(SmPurchaseSchemeVoJs smPurchaseSchemeVoJs) throws Exception{
		String settleId = smPurchaseSchemeVoJs.getSettleId();
		String ym = smPurchaseSchemeVoJs.getYm();
		if(ym == null || "".equals(ym)) {
			throw new BusinessException("年月为空，保存失败。请刷新重试！");
		}
		ym = ym.replaceAll("-", "");
		if(settleId == null || "".equals(settleId)) {
			settleId = smSettlementMonthService.getSmSettlementMonthIdByYm(ym);
			if(settleId == null || "".equals(settleId)) {		//如果没有本月的结算，则新增一个结算主表信息
				SmSettlementMonth settlement = new SmSettlementMonth();
				settlement.setYm(ym);
				smSettlementMonthService.saveSmSettlementMonth(settlement);
				settleId = settlement.getId();
			}
		}
		smPurchaseSchemeVoJs.setSettleId(settleId);
		
		SmPurchaseScheme smPurchaseScheme = smPurchaseSchemeVoJs.getSmPurchaseScheme();
		smPurchaseScheme.setSettleId(settleId);
		smPurchaseScheme.setYm(ym);
		smPurchaseScheme.setSchemeStatus(0);	//设置为“草稿”状态
		if(smPurchaseScheme.getId() != null && !"".equals(smPurchaseScheme.getId().trim())){	//更新
			//先删除结算方案所有相关数据
			deleteAllSettlementInfo(new String[]{smPurchaseScheme.getId()});
		}else{
			smPurchaseScheme.setId(null);
		}
		dao.save(smPurchaseScheme);			//保存结算方案
		
		String schemeId = smPurchaseScheme.getId();
		
		
		//保存月度结算用户偏差惩罚费用信息 列表
		List<SmConsDeviationAmt> smConsDeviationAmtList = smPurchaseSchemeVoJs.getSmConsDeviationAmtList();
		for (SmConsDeviationAmt smConsDeviationAmt : smConsDeviationAmtList) {
			smConsDeviationAmt.setSchemeId(schemeId);
		}
		dao.saveList(smConsDeviationAmtList);
		
		//保存月度结算 售电公司收益信息
		SmCompanyProfit smCompanyProfit = smPurchaseSchemeVoJs.getSmCompanyProfit();
		if(smCompanyProfit != null) {
			smCompanyProfit.setSchemeId(schemeId);
			smCompanyProfit.setYm(ym);
			dao.save(smPurchaseSchemeVoJs.getSmCompanyProfit());
		}
		
		//保存 用户收益列表信息
		List<SmConsumerProfit> smConsumerProfitList = smPurchaseSchemeVoJs.getSmConsumerProfitList();
		if(smConsumerProfitList != null && smConsumerProfitList.size() != 0) {
			for (SmConsumerProfit smConsumerProfit : smConsumerProfitList) {
				smConsumerProfit.setSchemeId(schemeId);
			}
			dao.saveList(smConsumerProfitList);
		}
		
		//保存月度结算方案明细列表
		List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList = smPurchaseSchemeVoJs.getSmPurchaseSchemeDetailList();
		for(SmPurchaseSchemeDetail detail:smPurchaseSchemeDetailList){
			detail.setSchemeId(schemeId);
		}
		dao.saveList(smPurchaseSchemeDetailList);
		
		//在保存江苏特有的表(批发市场购电支出明细)相关数据
		List<SmCompanyCostDetail> smCompanyCostDetailList = smPurchaseSchemeVoJs.getSmCompanyCostDetailList();
		if(smCompanyCostDetailList != null && smCompanyCostDetailList.size() != 0) {
			for (SmCompanyCostDetail smCompanyCostDetail : smCompanyCostDetailList) {
				smCompanyCostDetail.setSchemeId(schemeId);
			}
			smCompanyCostDetailService.saveSmCompanyCostDetailList(smCompanyCostDetailList);
		}
	}
	
	/**
	 * @Title: deleteAllSettlementInfo
	 * @Description: 江苏结算中 根据方案id删除 方案、方案详情信息列表、公司收益信息、用户收益信息列表（零售市场售电收入明细），批发市场购电支出明细
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:36:38
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午4:36:38
	 * @since  1.0.0
	 */
	@Override
	@Transactional
	public void deleteAllSettlementInfo(String[] schemeIds) {
		//先删除江苏特有的表相关数据
		smCompanyCostDetailService.deleteSmCompanyCostDetailBySchemeId(schemeIds);
		//根据方案id删除月度结算用户偏差惩罚费用信息表信息
		smConsDeviationAmtService.deleteConsDevAmtBySchemeIds(schemeIds);
		//再调用普通结算中的删除结算数据的接口
		smPurchaseSchemeService.deleteSchemeInfo(schemeIds);
	}
}